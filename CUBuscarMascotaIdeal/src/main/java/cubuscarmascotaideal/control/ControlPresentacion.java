package cubuscarmascotaideal.control;

import cubuscarmascotaideal.negocio.dtos.EncuestaDTO;
import cubuscarmascotaideal.negocio.dtos.MascotaResultadoDTO;
import cubuscarmascotaideal.negocio.dtos.ResultadoOperacion;
import cubuscarmascotaideal.presentacion.JPEncuesta;
import cubuscarmascotaideal.presentacion.JPPerfilCUBuscar;
import org.bson.types.ObjectId;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;

/**
 * Controlador principal del modulo de busqueda de mascotas.
 * Coordina la navegacion entre pantallas y maneja las operaciones de busqueda.
 */
public class ControlPresentacion {

    private final ControlSubsistemas controlSubsistemas;

    // Las pantallas del modulo
    private JPanel panelContenedor;
    private JPPerfilCUBuscar panelPerfil;
    private JPEncuesta panelEncuesta;
    private Runnable menuCallback;

    public ControlPresentacion() {
        this.controlSubsistemas = new ControlSubsistemas();
    }

    public ControlPresentacion(ControlSubsistemas controlSubsistemas) {
        this.controlSubsistemas = controlSubsistemas;
    }

    /**
     * Establece donde se mostraran las pantallas
     */
    public void setPanelContenedor(JPanel panelContenedor) {
        this.panelContenedor = panelContenedor;
    }

    /**
     * Establece que hacer cuando el usuario quiere regresar al menu
     */
    public void setMenuCallback(Runnable callback) {
        this.menuCallback = callback;
    }

    /**
     * Muestra el panel de perfil del usuario
     */
    public void mostrarPerfil() {
        // Si no existe el panel, lo creo
        if (panelPerfil == null) {
            panelPerfil = new JPPerfilCUBuscar();
            panelPerfil.setControlPresentacion(this);
            panelPerfil.setCerrarSesionListener(() -> regresarAlMenu());
        }

        // Muestro el panel en el contenedor
        if (panelContenedor != null) {
            panelContenedor.removeAll();
            panelContenedor.setLayout(new BorderLayout());
            panelContenedor.add(panelPerfil, BorderLayout.CENTER);
            panelContenedor.revalidate();
            panelContenedor.repaint();
        }
    }

    /**
     * Muestra el panel de encuesta para buscar mascotas
     */
    public void mostrarEncuesta() {
        // Si no existe el panel, lo creo
        if (panelEncuesta == null) {
            panelEncuesta = new JPEncuesta();
            panelEncuesta.setControlPresentacion(this);
        }

        // Muestro el panel en el contenedor
        if (panelContenedor != null) {
            panelContenedor.removeAll();
            panelContenedor.setLayout(new BorderLayout());
            panelContenedor.add(panelEncuesta, BorderLayout.CENTER);
            panelContenedor.revalidate();
            panelContenedor.repaint();
        }
    }

    /**
     * Muestra los resultados de búsqueda
     */
    public void mostrarResultados(List<MascotaResultadoDTO> mascotas) {
        mostrarPerfil();
    }

    /**
     * Regresa al menú principal
     */
    public void regresarAlMenu() {
        if (menuCallback != null) {
            menuCallback.run();
        }
    }

    /**
     * Establece los datos del usuario en el perfil
     */
    public void setDatosUsuario(String nombre, String correo, String curp) {
        if (panelPerfil != null) {
            panelPerfil.setDatosUsuario(nombre, correo, curp);
        }
    }

    /**
     * Busca mascotas que sean compatibles con las preferencias del usuario.
     * Solo regresa mascotas con al menos 50% de compatibilidad.
     */
    public List<MascotaResultadoDTO> buscarMascotasCompatibles(EncuestaDTO encuesta) {
        try {
            // Verifico que la encuesta no sea nula
            if (encuesta == null) {
                System.err.println("Encuesta no puede ser nula");
                return List.of();
            }

            return controlSubsistemas.buscarMascotasCompatibles(encuesta);

        } catch (Exception e) {
            System.err.println("Error al buscar mascotas: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    /**
     * Obtiene todas las mascotas disponibles con su porcentaje de compatibilidad
     */
    public List<MascotaResultadoDTO> obtenerTodasConCompatibilidad(EncuestaDTO encuesta) {
        try {
            if (encuesta == null) {
                return List.of();
            }

            return controlSubsistemas.obtenerTodasConCompatibilidad(encuesta);

        } catch (Exception e) {
            System.err.println("Error al obtener mascotas: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Valida que la encuesta este completa
     */
    public ResultadoOperacion validarEncuesta(EncuestaDTO encuesta) {
        if (encuesta == null) {
            return ResultadoOperacion.error("Falta la encuesta");
        }

        // Valido campos requeridos
        if (encuesta.getTamano() == null || encuesta.getTamano().trim().isEmpty()) {
            return ResultadoOperacion.error("Selecciona un tamano");
        }

        if (encuesta.getNivelActividad() == null || encuesta.getNivelActividad().trim().isEmpty()) {
            return ResultadoOperacion.error("Selecciona el nivel de actividad");
        }

        if (encuesta.getCostoMantenimiento() == null || encuesta.getCostoMantenimiento().trim().isEmpty()) {
            return ResultadoOperacion.error("Selecciona el costo de mantenimiento");
        }

        return ResultadoOperacion.exito("OK");
    }

    /**
     * Guarda los resultados de busqueda en la base de datos
     */
    public void guardarResultados(String idUsuario, List<MascotaResultadoDTO> resultados) {
        try {
            if (idUsuario == null || idUsuario.isEmpty()) {
                System.err.println("ID de usuario no valido");
                return;
            }
            controlSubsistemas.guardarResultados(new ObjectId(idUsuario), resultados);
        } catch (Exception e) {
            System.err.println("Error al guardar resultados: " + e.getMessage());
        }
    }

    /**
     * Obtiene los resultados que el usuario guardo anteriormente
     */
    public List<MascotaResultadoDTO> obtenerResultadosGuardados(String idUsuario) {
        try {
            if (idUsuario == null || idUsuario.isEmpty()) {
                return List.of();
            }
            return controlSubsistemas.obtenerResultadosGuardados(new ObjectId(idUsuario));
        } catch (Exception e) {
            System.err.println("Error al obtener resultados guardados: " + e.getMessage());
            return List.of();
        }
    }
}
