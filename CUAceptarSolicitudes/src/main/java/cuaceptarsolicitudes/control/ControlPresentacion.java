package cuaceptarsolicitudes.control;

import cuaceptarsolicitudes.negocio.dtos.ResultadoOperacion;
import cuaceptarsolicitudes.negocio.dtos.SolicitudDTO;
import cuaceptarsolicitudes.presentacion.JPSolicitudesCU;
import cuaceptarsolicitudes.presentacion.JDinfoSolicitud;
import cuaceptarsolicitudes.presentacion.JDMsgModificacion;
import cuaceptarsolicitudes.presentacion.FrmCorreoConfirmacion;

import javax.swing.JPanel;
import java.awt.Frame;
import java.util.List;

/**
 * Fachada principal del módulo de aceptar solicitudes (Boundary).
 * Proporciona una interfaz simplificada para la capa de presentación,
 * maneja excepciones y traduce errores a mensajes de usuario.
 * Coordina la navegación entre las pantallas del caso de uso.
 */
public class ControlPresentacion {

    private final ControlSubsistemas controlSubsistemas;

    // Referencias a las Boundaries
    private JPanel panelContenedor;
    private JPSolicitudesCU panelSolicitudes;
    private FrmCorreoConfirmacion panelCorreoConfirmacion;
    private Runnable inicioCallback;

    public ControlPresentacion() {
        // Inicializar con implementación por defecto
        this.controlSubsistemas = new ControlSubsistemas();
    }

    // Constructor con inyección de dependencias
    public ControlPresentacion(ControlSubsistemas controlSubsistemas) {
        this.controlSubsistemas = controlSubsistemas;
    }

    /**
     * Establece el panel contenedor donde se mostrarán las pantallas.
     * 
     * @param panelContenedor JPanel contenedor principal
     */
    public void setPanelContenedor(JPanel panelContenedor) {
        this.panelContenedor = panelContenedor;
    }

    /**
     * Establece el callback para regresar al menú de inicio.
     * 
     * @param callback Runnable que se ejecuta al regresar al inicio
     */
    public void setInicioCallback(Runnable callback) {
        this.inicioCallback = callback;
    }

    /**
     * Muestra el panel de solicitudes de adopción.
     * Crea y configura el panel si no existe.
     */
    public void mostrarPanelSolicitudes() {
        if (panelSolicitudes == null) {
            panelSolicitudes = new JPSolicitudesCU();
            panelSolicitudes.setControlPresentacion(this);
            panelSolicitudes.setInicioListener(() -> regresarAlInicio());
        }

        if (panelContenedor != null) {
            panelContenedor.removeAll();
            panelContenedor.add(panelSolicitudes);
            panelContenedor.revalidate();
            panelContenedor.repaint();
        }
    }

    /**
     * Muestra el diálogo con los detalles de una solicitud.
     * 
     * @param solicitud   SolicitudDTO con los datos a mostrar
     * @param parentFrame Frame padre para el diálogo
     */
    public void mostrarDetallesSolicitud(SolicitudDTO solicitud, Frame parentFrame) {
        if (solicitud != null && parentFrame != null) {
            JDinfoSolicitud dialog = new JDinfoSolicitud(parentFrame, true, solicitud);
            dialog.setLocationRelativeTo(parentFrame);
            dialog.setVisible(true);
        }
    }

    /**
     * Regresa al menú de inicio.
     * Ejecuta el callback de inicio si está configurado.
     */
    public void regresarAlInicio() {
        if (inicioCallback != null) {
            inicioCallback.run();
        }
    }

    /**
     * Actualiza la tabla de solicitudes en el panel actual.
     */
    public void actualizarVista() {
        if (panelSolicitudes != null) {
            // El panel recargará las solicitudes
            mostrarPanelSolicitudes();
        }
    }

    /**
     * Obtiene todas las solicitudes de adopción.
     * 
     * @return Lista de solicitudes o lista vacía en caso de error
     */
    public List<SolicitudDTO> obtenerSolicitudes() {
        try {
            return controlSubsistemas.obtenerSolicitudes();
        } catch (Exception e) {
            System.err.println("Error al obtener solicitudes: " + e.getMessage());
            return List.of(); // Retornar lista vacía en caso de error
        }
    }

    /**
     * Acepta una solicitud de adopción.
     * 
     * @param idSolicitud ID de la solicitud a aceptar
     * @param idAdmin     ID del administrador que acepta la solicitud
     * @return Resultado de la operación
     */
    public ResultadoOperacion aceptarSolicitud(String idSolicitud, String idAdmin) {
        try {
            // Validar ID
            if (idSolicitud == null || idSolicitud.trim().isEmpty()) {
                return ResultadoOperacion.error("El ID de la solicitud es obligatorio");
            }

            boolean exito = controlSubsistemas.aceptarSolicitud(idSolicitud, idAdmin);

            if (exito) {
                return ResultadoOperacion.exito("Solicitud aceptada exitosamente. Se ha enviado un correo al usuario.");
            } else {
                return ResultadoOperacion.error("No se pudo aceptar la solicitud");
            }

        } catch (IllegalArgumentException e) {
            return ResultadoOperacion.error("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al aceptar solicitud: " + e.getMessage());
            e.printStackTrace();
            return ResultadoOperacion.error("Error inesperado al aceptar la solicitud: " + e.getMessage());
        }
    }

    /**
     * Rechaza una solicitud de adopción.
     * 
     * @param idSolicitud ID de la solicitud a rechazar
     * @param idAdmin     ID del administrador que rechaza la solicitud
     * @return Resultado de la operación
     */
    public ResultadoOperacion rechazarSolicitud(String idSolicitud, String idAdmin) {
        try {
            // Validar ID
            if (idSolicitud == null || idSolicitud.trim().isEmpty()) {
                return ResultadoOperacion.error("El ID de la solicitud es obligatorio");
            }

            boolean exito = controlSubsistemas.rechazarSolicitud(idSolicitud, idAdmin);

            if (exito) {
                return ResultadoOperacion
                        .exito("Solicitud rechazada exitosamente. Se ha enviado un correo al usuario.");
            } else {
                return ResultadoOperacion.error("No se pudo rechazar la solicitud");
            }

        } catch (IllegalArgumentException e) {
            return ResultadoOperacion.error("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al rechazar solicitud: " + e.getMessage());
            e.printStackTrace();
            return ResultadoOperacion.error("Error inesperado al rechazar la solicitud: " + e.getMessage());
        }
    }

    /**
     * Marca una solicitud como pendiente de modificación.
     * 
     * @param idSolicitud       ID de la solicitud
     * @param idAdmin           ID del administrador que solicita la modificación
     * @param razonModificacion Razón por la cual se requiere modificación
     * @return Resultado de la operación
     */
    public ResultadoOperacion modificarSolicitud(String idSolicitud, String idAdmin, String razonModificacion) {
        try {
            // Validar parámetros
            if (idSolicitud == null || idSolicitud.trim().isEmpty()) {
                return ResultadoOperacion.error("El ID de la solicitud es obligatorio");
            }

            if (razonModificacion == null || razonModificacion.trim().isEmpty()) {
                return ResultadoOperacion.error("La razón de modificación es obligatoria");
            }

            boolean exito = controlSubsistemas.modificarSolicitud(idSolicitud, idAdmin, razonModificacion);

            if (exito) {
                return ResultadoOperacion
                        .exito("Solicitud marcada como pendiente de modificación. Se ha enviado un correo al usuario.");
            } else {
                return ResultadoOperacion.error("No se pudo modificar la solicitud");
            }

        } catch (IllegalArgumentException e) {
            return ResultadoOperacion.error("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al modificar solicitud: " + e.getMessage());
            e.printStackTrace();
            return ResultadoOperacion.error("Error inesperado al modificar la solicitud: " + e.getMessage());
        }
    }

    /**
     * Muestra el diálogo de modificación para ingresar la razón.
     * 
     * @param parentFrame Frame padre para el diálogo
     * @return El texto ingresado, o null si se canceló
     */
    public String mostrarDialogoModificacion(Frame parentFrame) {
        JDMsgModificacion dialog = new JDMsgModificacion(parentFrame, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
        return dialog.getMensaje();
    }

    /**
     * Muestra el panel de confirmación de correo enviado.
     * 
     * @param correoUsuario Correo del usuario al que se envió la notificación
     */
    public void mostrarCorreoConfirmacion(String correoUsuario) {
        if (panelCorreoConfirmacion == null) {
            panelCorreoConfirmacion = new FrmCorreoConfirmacion();
        }

        panelCorreoConfirmacion.setCorreo(correoUsuario);
        panelCorreoConfirmacion.setOnVolver(() -> mostrarPanelSolicitudes());

        if (panelContenedor != null) {
            panelContenedor.removeAll();
            panelContenedor.add(panelCorreoConfirmacion);
            panelContenedor.revalidate();
            panelContenedor.repaint();
        }
    }
}
