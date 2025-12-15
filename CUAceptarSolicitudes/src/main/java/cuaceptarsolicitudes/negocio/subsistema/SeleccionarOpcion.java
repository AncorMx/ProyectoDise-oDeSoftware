package cuaceptarsolicitudes.negocio.subsistema;

import cuaceptarsolicitudes.negocio.dtos.SolicitudDTO;
import cuaceptarsolicitudes.negocio.adaptadores.AdaptadorSolicitud;
import cuaceptarsolicitudes.negocio.BO.SolicitudBO;
import cuaceptarsolicitudes.negocio.BO.ISolicitudBO;
import conexion.ConexionMongoDB;
import daos.SolicitudAdopcionDAO;
import daos.MascotaDAO;
import daos.UsuarioDAO;
import daos.CitaDAO;
import entities.SolicitudAdopcion;
import entities.Mascota;
import entities.Usuario;
import entities.Cita;
import infraestructura.dto.CorreoDTO;
import infraestructura.sistemacorreo.FachadaCorreo;
import infraestructura.sistemacorreo.ISistemaCorreo;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Subsistema que maneja las solicitudes de adopcion.
 * 
 * Que hace cada accion:
 * - Aceptar: Acepta la solicitud, marca la mascota como adoptada y envia correo
 * - Rechazar: Rechaza la solicitud, libera la mascota y envia correo
 * - Modificar: Pide al usuario que corrija algo y le envia un correo
 */
public class SeleccionarOpcion implements ISeleccionarOpcion {

    private final SolicitudAdopcionDAO solicitudDAO;
    private final MascotaDAO mascotaDAO;
    private final UsuarioDAO usuarioDAO;
    private final CitaDAO citaDAO;
    private final ISolicitudBO solicitudBO;
    private final ISistemaCorreo sistemaCorreo;

    public SeleccionarOpcion() {
        this.solicitudDAO = new SolicitudAdopcionDAO(ConexionMongoDB.getInstancia().getDatabase());
        this.mascotaDAO = new MascotaDAO(ConexionMongoDB.getInstancia().getDatabase());
        this.usuarioDAO = new UsuarioDAO(ConexionMongoDB.getInstancia().getDatabase());
        this.citaDAO = new CitaDAO(ConexionMongoDB.getInstancia().getDatabase());
        this.solicitudBO = new SolicitudBO();
        this.sistemaCorreo = new FachadaCorreo();
    }

    // Constructor para testing
    public SeleccionarOpcion(SolicitudAdopcionDAO solicitudDAO, MascotaDAO mascotaDAO,
            UsuarioDAO usuarioDAO, ISolicitudBO solicitudBO, ISistemaCorreo sistemaCorreo) {
        this.solicitudDAO = solicitudDAO;
        this.mascotaDAO = mascotaDAO;
        this.usuarioDAO = usuarioDAO;
        this.citaDAO = new CitaDAO(ConexionMongoDB.getInstancia().getDatabase());
        this.solicitudBO = solicitudBO;
        this.sistemaCorreo = sistemaCorreo;
    }

    @Override
    public List<SolicitudDTO> obtenerTodasLasSolicitudes() {
        List<SolicitudAdopcion> solicitudes = solicitudDAO.buscarTodas();
        List<SolicitudDTO> solicitudesDTO = new ArrayList<>();

        for (SolicitudAdopcion solicitud : solicitudes) {
            // Traigo el usuario y la mascota para completar el DTO
            Usuario usuario = null;
            Mascota mascota = null;

            if (solicitud.getIdUsuario() != null) {
                usuario = usuarioDAO.buscarPorId(solicitud.getIdUsuario());
            }

            if (solicitud.getIdMascota() != null) {
                mascota = mascotaDAO.buscarPorId(solicitud.getIdMascota());
            }

            SolicitudDTO dto = AdaptadorSolicitud.entidadADTO(solicitud, usuario, mascota);

            // Busco la cita para obtener la fecha
            // Primero intento por idCita, si no existe busco por usuario+mascota
            try {
                Cita citaEncontrada = null;

                // Metodo 1: Buscar por idCita si existe
                if (solicitud.getIdCita() != null) {
                    citaEncontrada = citaDAO.buscarPorId(solicitud.getIdCita());
                }

                // Metodo 2: Si no hay idCita o no se encontro, busco por usuario+mascota
                if (citaEncontrada == null && solicitud.getIdUsuario() != null && solicitud.getIdMascota() != null) {
                    java.util.List<Cita> citasUsuario = citaDAO.buscarPorUsuario(solicitud.getIdUsuario());
                    for (Cita c : citasUsuario) {
                        if (c.getIdMascota() != null && c.getIdMascota().equals(solicitud.getIdMascota())) {
                            citaEncontrada = c;
                            dto.setIdCita(c.getId().toString());
                            break;
                        }
                    }
                }

                // Pongo la fecha de la cita si la encontre
                if (citaEncontrada != null && citaEncontrada.getFechaHora() != null) {
                    dto.setFechaCita(citaEncontrada.getFechaHora());
                }
            } catch (Exception e) {
                System.err.println("Error al obtener cita: " + e.getMessage());
            }

            solicitudesDTO.add(dto);
        }

        return solicitudesDTO;
    }

    @Override
    public boolean aceptarSolicitud(String idSolicitud, String idAdmin) throws Exception {
        if (!solicitudBO.puedeAceptarSolicitud(idSolicitud)) {
            throw new IllegalArgumentException("La solicitud no puede ser aceptada");
        }

        // 1. Buscar solicitud
        SolicitudAdopcion solicitud = solicitudDAO.buscarPorId(new ObjectId(idSolicitud));
        if (solicitud == null) {
            throw new IllegalArgumentException("Solicitud no encontrada");
        }

        // 2. Actualizar estado de la solicitud
        solicitud.setEstado("APROBADA");
        solicitudDAO.actualizar(solicitud);

        // 3. Marcar mascota como adoptada
        if (solicitud.getIdMascota() != null) {
            Mascota mascota = mascotaDAO.buscarPorId(solicitud.getIdMascota());
            if (mascota != null) {
                mascota.setDisponible(false);
                mascota.setEstado("adoptada");
                mascotaDAO.actualizar(mascota);
                System.out.println(" Mascota " + mascota.getNombre() + " marcada como adoptada");
            }
        }

        // 4. Obtener usuario para enviar correo
        Usuario usuario = null;
        if (solicitud.getIdUsuario() != null) {
            usuario = usuarioDAO.buscarPorId(solicitud.getIdUsuario());
        }

        // 5. Enviar correo de aceptacion
        if (usuario != null && usuario.getInfoPersonal() != null) {
            enviarCorreoAceptacion(usuario, solicitud);
        }

        System.out.println("Solicitud aceptada exitosamente");
        return true;
    }

    @Override
    public boolean rechazarSolicitud(String idSolicitud, String idAdmin) throws Exception {
        if (!solicitudBO.puedeRechazarSolicitud(idSolicitud)) {
            throw new IllegalArgumentException("La solicitud no puede ser rechazada");
        }

        // 1. Buscar solicitud
        SolicitudAdopcion solicitud = solicitudDAO.buscarPorId(new ObjectId(idSolicitud));
        if (solicitud == null) {
            throw new IllegalArgumentException("Solicitud no encontrada");
        }

        // 2. Actualizar estado de la solicitud
        solicitud.setEstado("RECHAZADA");
        solicitudDAO.actualizar(solicitud);

        // 3. Liberar mascota (hacerla disponible nuevamente)
        if (solicitud.getIdMascota() != null) {
            Mascota mascota = mascotaDAO.buscarPorId(solicitud.getIdMascota());
            if (mascota != null) {
                mascota.setDisponible(true);
                mascota.setEstado("disponible");
                mascotaDAO.actualizar(mascota);
                System.out.println("Mascota " + mascota.getNombre() + " liberada (disponible)");
            }
        }

        // 4. Obtener usuario para enviar correo
        Usuario usuario = null;
        if (solicitud.getIdUsuario() != null) {
            usuario = usuarioDAO.buscarPorId(solicitud.getIdUsuario());
        }

        // 5. Enviar correo de rechazo
        if (usuario != null && usuario.getInfoPersonal() != null) {
            enviarCorreoRechazo(usuario, solicitud);
        }

        System.out.println(" Solicitud rechazada exitosamente");
        return true;
    }

    @Override
    public boolean modificarSolicitud(String idSolicitud, String idAdmin, String razonModificacion) throws Exception {
        if (!solicitudBO.puedeModificarSolicitud(idSolicitud)) {
            throw new IllegalArgumentException("La solicitud no puede ser modificada");
        }

        // 1. Buscar solicitud
        SolicitudAdopcion solicitud = solicitudDAO.buscarPorId(new ObjectId(idSolicitud));
        if (solicitud == null) {
            throw new IllegalArgumentException("Solicitud no encontrada");
        }

        // 2. Actualizar estado a pendiente de modificacion
        solicitud.setEstado("Requiere Modificacion");
        solicitud.setMensajeCorreccion(razonModificacion);
        solicitudDAO.actualizar(solicitud);

        // 3. Obtener usuario para enviar correo
        Usuario usuario = null;
        if (solicitud.getIdUsuario() != null) {
            usuario = usuarioDAO.buscarPorId(solicitud.getIdUsuario());
        }

        // 4. Enviar correo notificando la modificacion requerida
        if (usuario != null && usuario.getInfoPersonal() != null) {
            enviarCorreoModificacion(usuario, solicitud, razonModificacion);
        }

        System.out.println("Solicitud marcada como pendiente de modificacion");
        return true;
    }

    // --- Metodos privados para envio de correos ---

    private void enviarCorreoAceptacion(Usuario usuario, SolicitudAdopcion solicitud) {
        try {
            String destinatario = usuario.getInfoPersonal().getCorreo();
            String nombreUsuario = usuario.getInfoPersonal().getNombre();

            Mascota mascota = null;
            String nombreMascota = "tu nueva mascota";
            if (solicitud.getIdMascota() != null) {
                mascota = mascotaDAO.buscarPorId(solicitud.getIdMascota());
                if (mascota != null) {
                    nombreMascota = mascota.getNombre();
                }
            }

            String asunto = "Tu solicitud de adopcion ha sido aceptada";

            // Obtener fecha de cita si existe
            String infoCita = "";
            if (solicitud.getFechaSolicitud() != null) {
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                        .ofPattern("dd/MM/yyyy HH:mm");
                infoCita = "\n\nFecha de tu cita: " + solicitud.getFechaSolicitud().format(formatter);
            }

            String mensaje = String.format(
                    "Hola %s,\n\n" +
                            "¡Tenemos excelentes noticias! Tu solicitud de adopción para %s ha sido ACEPTADA.%s\n\n" +
                            "Por favor, preséntate en la fecha y hora indicada en nuestra oficina.\n\n" +
                            "Gracias por dar un hogar a uno de nuestros amigos peludos.\n\n" +
                            "Atentamente,\n" +
                            "El equipo de La Vida es Bella",
                    nombreUsuario, nombreMascota, infoCita);

            CorreoDTO correo = new CorreoDTO(destinatario, asunto, mensaje);
            sistemaCorreo.enviarCorreo(correo);
            System.out.println("Correo de aceptacion enviado a: " + destinatario);
        } catch (Exception e) {
            System.err.println("Error al enviar correo de aceptacion: " + e.getMessage());
        }
    }

    private void enviarCorreoRechazo(Usuario usuario, SolicitudAdopcion solicitud) {
        try {
            String destinatario = usuario.getInfoPersonal().getCorreo();
            String nombreUsuario = usuario.getInfoPersonal().getNombre();

            Mascota mascota = null;
            String nombreMascota = "la mascota";
            if (solicitud.getIdMascota() != null) {
                mascota = mascotaDAO.buscarPorId(solicitud.getIdMascota());
                if (mascota != null) {
                    nombreMascota = mascota.getNombre();
                }
            }

            String asunto = "Actualizacion sobre tu solicitud de adopcion";
            String mensaje = String.format(
                    "Hola %s,\n\n" +
                            "Lamentamos informarte que tu solicitud de adopción para %s no ha sido aprobada en esta ocasión.\n\n"
                            +
                            "Te invitamos a revisar nuestro catálogo de mascotas disponibles y solicitar la adopción de otra mascota.\n\n"
                            +
                            "Si tienes alguna pregunta, no dudes en contactarnos.\n\n" +
                            "Atentamente,\n" +
                            "El equipo de La Vida es Bella",
                    nombreUsuario, nombreMascota);

            CorreoDTO correo = new CorreoDTO(destinatario, asunto, mensaje);
            sistemaCorreo.enviarCorreo(correo);
            System.out.println("Correo de rechazo enviado a: " + destinatario);
        } catch (Exception e) {
            System.err.println("Error al enviar correo de rechazo: " + e.getMessage());
        }
    }

    private void enviarCorreoModificacion(Usuario usuario, SolicitudAdopcion solicitud, String razonModificacion) {
        try {
            String destinatario = usuario.getInfoPersonal().getCorreo();
            String nombreUsuario = usuario.getInfoPersonal().getNombre();

            Mascota mascota = null;
            String nombreMascota = "la mascota";
            if (solicitud.getIdMascota() != null) {
                mascota = mascotaDAO.buscarPorId(solicitud.getIdMascota());
                if (mascota != null) {
                    nombreMascota = mascota.getNombre();
                }
            }

            String asunto = "Se requiere modificacion en tu solicitud de adopcion";
            String mensaje = String.format(
                    "Hola %s,\n\n" +
                            "Tu solicitud de adopción para %s requiere algunas modificaciones.\n\n" +
                            "Razon: %s\n\n" +
                            "Por favor, ingresa al sistema y actualiza tu solicitud con la informacion requerida.\n\n" +
                            "Si tienes alguna pregunta, no dudes en contactarnos.\n\n" +
                            "Atentamente,\n" +
                            "El equipo de La Vida es Bella",
                    nombreUsuario, nombreMascota, razonModificacion);

            CorreoDTO correo = new CorreoDTO(destinatario, asunto, mensaje);
            sistemaCorreo.enviarCorreo(correo);
            System.out.println("Correo de modificacion enviado a: " + destinatario);
        } catch (Exception e) {
            System.err.println("Error al enviar correo de modificacion: " + e.getMessage());
        }
    }
}
