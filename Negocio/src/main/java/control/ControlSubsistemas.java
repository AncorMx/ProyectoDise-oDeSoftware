package control;

import DTOS.CitaDTO;
import DTOS.CitaDisponibleDTO;
import DTOS.SolicitudAdopcionDTO;
import DTOS.UsuarioDTO;
import ObjetoNegocio.CitaDisponibleBO;
import ObjetoNegocio.ICitaDisponibleBO;
import negocio.adopcionesdto.*;
import negocio.subsistemas.iniciosesion.FachadaInicioSesion;
import negocio.subsistemas.iniciosesion.IInicioSesion;
import negocio.subsistemas.mascotas.FachadaMascotas;
import negocio.subsistemas.mascotas.IMascotas;
import DTOS.MascotaDTO;
import infraestructura.dto.CorreoDTO;
import infraestructura.sistemacorreo.FachadaCorreo;
import infraestructura.sistemacorreo.ISistemaCorreo;

import java.util.List;

/**
 * Controla y coordina todos los subsistemas de negocio
 */
public class ControlSubsistemas {

    private IInicioSesion subsistemaInicioSesion;
    private IMascotas subsistemaMascotas;
    private ControlAdopcion controlAdopcion;
    private ControlCita controlCita;
    private ICitaDisponibleBO citaDisponibleBO;
    private ISistemaCorreo subsistemaCorreo;

    public ControlSubsistemas() {
        this.subsistemaInicioSesion = new FachadaInicioSesion();
        this.subsistemaMascotas = new FachadaMascotas();
        this.controlAdopcion = new ControlAdopcion();
        this.controlCita = new ControlCita();
        this.citaDisponibleBO = new CitaDisponibleBO();
        this.subsistemaCorreo = new FachadaCorreo();
    }

    // --- Inicio de Sesion ---

    public UsuarioDTO validarLogin(String correo, String password) throws Exception {
        return subsistemaInicioSesion.iniciarSesion(correo, password);
    }

    public void registrarUsuario(UsuarioDTO usuario) throws Exception {
        subsistemaInicioSesion.registrarUsuario(usuario);
    }

    public void actualizarUsuario(UsuarioDTO usuario) throws Exception {
        subsistemaInicioSesion.actualizarUsuario(usuario);
    }

    /**
     * Crea el usuario administrador si no existe
     */
    public void InicializarAdminUser() {
        try {
            // Intento hacer login con el admin
            try {
                subsistemaInicioSesion.iniciarSesion("admin@gmail.com", "admin");
                return; // Ya existe, no hago nada
            } catch (Exception e) {
                // No existe, lo creo
            }

            // Creo el usuario admin
            UsuarioDTO admin = new UsuarioDTO();
            admin.setContrasena("admin");

            DTOS.InfoPersonalDTO info = new DTOS.InfoPersonalDTO();
            info.setNombre("Administrador");
            info.setCorreo("admin@gmail.com");
            admin.setInfoPersonal(info);

            subsistemaInicioSesion.registrarUsuario(admin);
            System.out.println("Admin creado correctamente");

        } catch (Exception e) {
            System.err.println("No se pudo inicializar el usuario Admin: " + e.getMessage());
        }
    }

    // --- Mascotas ---
    public MascotaDTO obtenerMascotaPorId(String id) {
        try {
            return subsistemaMascotas.buscarMascotaPorId(id);
        } catch (Exception e) {
            System.err.println("Error al buscar mascota: " + e.getMessage());
            return null;
        }
    }

    // --- Citas Disponibles ---
    public List<CitaDisponibleDTO> obtenerCitasDisponibles() {
        return citaDisponibleBO.obtenerCitasDisponibles();
    }

    public boolean reservarCita(String idCita, String idUsuario) {
        return citaDisponibleBO.reservarCita(idCita, idUsuario);
    }

    public boolean verificarDisponibilidadCita(String idCita) {
        return citaDisponibleBO.verificarDisponibilidad(idCita);
    }

    /**
     * Libera una cita que fue reservada
     */
    public boolean liberarCita(String idCita) {
        return citaDisponibleBO.liberarCita(idCita);
    }

    // --- Flujo de Adopcion ---
    public void procesarSolicitudCompleta(SolicitudAdopcionDTO solicitud, CitaDTO cita) throws Exception {
        // 0. Guardo el ID de la cita en la solicitud
        if (cita != null && cita.getId() != null) {
            solicitud.setIdCita(cita.getId());
            System.out.println("→ ID de cita guardado en solicitud: " + cita.getId());
        }

        // 1. Guardar la solicitud de adopción (Negocio -> Persistencia)
        controlAdopcion.crearSolicitud(solicitud);

        // 2. Actualizar estado de la mascota a NO DISPONIBLE
        if (solicitud.getMascota() != null) {
            MascotaDTO mascota = solicitud.getMascota();
            mascota.setDisponible(false);
            subsistemaMascotas.actualizarMascota(mascota);
            System.out.println("Mascota " + mascota.getId() + " marcada como NO disponible.");
        }

        // 3. Agendar la cita y notificar (Negocio -> Infraestructura)
        // Asumiendo que obtenemos el correo del usuario del DTO
        String correoUsuario = "usuario@ejemplo.com";
        if (solicitud != null && solicitud.getUsuario() != null && solicitud.getUsuario().getInfoPersonal() != null) {
            correoUsuario = solicitud.getUsuario().getInfoPersonal().getCorreo();
        }
        controlCita.agendarCita(cita, correoUsuario);

        // 4. Enviar correo de confirmación
        enviarCorreoConfirmacion(solicitud);

        System.out.println("Flujo completo de solicitud finalizado exitosamente.");
    }

    /**
     * Busca todas las solicitudes de un usuario
     */
    public List<SolicitudAdopcionDTO> buscarSolicitudesPorUsuario(String idUsuario) throws Exception {
        return controlAdopcion.buscarSolicitudesPorUsuario(idUsuario);
    }

    /**
     * Actualiza el estado de una solicitud
     */
    public void actualizarEstadoSolicitud(String idSolicitud, String nuevoEstado) throws Exception {
        controlAdopcion.actualizarEstadoSolicitud(idSolicitud, nuevoEstado);
    }

    public SolicitudAdopcionDTO buscarSolicitudPorId(String idSolicitud) throws Exception {
        return controlAdopcion.buscarSolicitudPorId(idSolicitud);
    }

    /**
     * Cancela una cita y libera la mascota y la cita asociada
     */
    public void cancelarCitaDeSolicitud(String idSolicitud) throws Exception {
        liberarRecursosDeSolicitud(idSolicitud, true);
        controlAdopcion.actualizarEstadoSolicitud(idSolicitud, "Cita Cancelada");
    }

    /**
     * Cancela una solicitud completa y libera la mascota
     */
    public void cancelarSolicitudAdopcion(String idSolicitud) throws Exception {
        liberarRecursosDeSolicitud(idSolicitud, false);
        controlAdopcion.actualizarEstadoSolicitud(idSolicitud, "Cancelada");
    }

    /**
     * Libera la mascota y opcionalmente la cita de una solicitud
     */
    private void liberarRecursosDeSolicitud(String idSolicitud, boolean liberarCita) throws Exception {
        SolicitudAdopcionDTO solicitud = controlAdopcion.buscarSolicitudPorId(idSolicitud);

        if (solicitud == null)
            return;

        // Libero la mascota
        if (solicitud.getMascota() != null) {
            MascotaDTO mascota = solicitud.getMascota();
            mascota.setDisponible(true);
            subsistemaMascotas.actualizarMascota(mascota);
            System.out.println("Mascota " + mascota.getId() + " liberada (disponible)");
        }

        // Libero la cita si es necesario
        if (liberarCita && solicitud.getIdCita() != null && !solicitud.getIdCita().isEmpty()) {
            if (citaDisponibleBO.liberarCita(solicitud.getIdCita())) {
                System.out.println("Cita " + solicitud.getIdCita() + " liberada");
            } else {
                System.err.println("No se pudo liberar la cita: " + solicitud.getIdCita());
            }
        }
    }

    private void enviarCorreoConfirmacion(SolicitudAdopcionDTO solicitud) {
        try {
            if (solicitud != null && solicitud.getUsuario() != null
                    && solicitud.getUsuario().getInfoPersonal() != null) {
                String destinatario = solicitud.getUsuario().getInfoPersonal().getCorreo();
                String nombreUsuario = solicitud.getUsuario().getInfoPersonal().getNombre();
                String nombreMascota = (solicitud.getMascota() != null) ? solicitud.getMascota().getNombre()
                        : "la mascota";

                String asunto = "Confirmacion de Solicitud de Adopcion - La Vida es Bella";
                String mensaje = "Hola " + nombreUsuario + ",\n\n" +
                        "Tu solicitud de adopcion para " + nombreMascota + " ha sido recibida exitosamente.\n" +
                        "Nos pondremos en contacto contigo pronto para los siguientes pasos.\n\n" +
                        "Gracias por querer dar un hogar a uno de nuestros amigos peludos.\n\n" +
                        "Atentamente,\n" +
                        "El equipo de La Vida es Bella";

                CorreoDTO correo = new CorreoDTO(destinatario, asunto, mensaje);
                subsistemaCorreo.enviarCorreo(correo);
            }
        } catch (Exception e) {
            System.err.println("Error al enviar correo de confirmacion: " + e.getMessage());
        }
    }
}
