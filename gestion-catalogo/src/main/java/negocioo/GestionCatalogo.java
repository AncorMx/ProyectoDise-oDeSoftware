package negocioo;

import negocioo.ExpedienteBO;
import entities.Mascota;
import gestion.catalogo.dtos.CatalogoDTO;
import persistencia.IMascotaDAO;
import persistencia.MascotaDAO;

import java.util.List;

/**
 * Implementación de la lógica de negocio para gestión de catálogo.
 * Coordina entre la capa de persistencia y aplica reglas de negocio.
 * Incluye composición fuerte con ExpedienteBO.
 * 
 * @author angel
 */
public class GestionCatalogo implements IGestionCatalogo {

    private final IMascotaDAO mascotaDAO;
    private final ObjetoNegocio objetoNegocio;
    private final IExpedienteBO expedienteBO; // expediente pertenece a mascota

    public GestionCatalogo() {
        this.mascotaDAO = new MascotaDAO();
        this.objetoNegocio = new ObjetoNegocio();
        this.expedienteBO = new ExpedienteBO();
    }

    // Constructor con inyección de dependencias
    public GestionCatalogo(IMascotaDAO mascotaDAO, ObjetoNegocio objetoNegocio, IExpedienteBO expedienteBO) {
        this.mascotaDAO = mascotaDAO;
        this.objetoNegocio = objetoNegocio;
        this.expedienteBO = expedienteBO;
    }

    @Override
    public List<CatalogoDTO> obtenerCatalogoCompleto() {
        List<Mascota> mascotas = mascotaDAO.obtenerTodasMascotas();
        return Adaptadores.listEntidadADTO(mascotas);
    }

    @Override
    public CatalogoDTO obtenerDetalleMascota(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }

        Mascota mascota = mascotaDAO.obtenerMascotaPorId(id);
        return Adaptadores.entidadADTO(mascota);
    }

    @Override
    public String registrarNuevaMascota(CatalogoDTO mascotaDTO) {
        // Validar datos
        objetoNegocio.validarDatosMascota(mascotaDTO);

        // Preparar para registro
        objetoNegocio.prepararParaRegistro(mascotaDTO);

        // Convertir a entidad
        Mascota mascota = Adaptadores.dtoAEntidad(mascotaDTO);

        // Guardar en BD
        boolean exito = mascotaDAO.agregarMascota(mascota);

        if (exito && mascota.getId() != null) {
            String idMascota = mascota.getId().toString();
            mascotaDTO.setId(idMascota);

            // Crear expediente médico automáticamente
            try {
                if (mascotaDTO.getDatosExpediente() != null) {
                    // Si hay datos recopilados en el formulario, usarlos
                    expedienteBO.crearExpedienteConDatos(idMascota, mascotaDTO.getDatosExpediente());
                    System.out.println("Expediente médico creado con datos iniciales para mascota: " + idMascota);
                } else {
                    // Crear expediente por defecto
                    expedienteBO.crearExpediente(idMascota);
                    System.out.println("Expediente médico por defecto creado para mascota: " + idMascota);
                }
            } catch (Exception e) {
                System.err.println("Error al crear expediente para mascota: " + idMascota);
                System.err.println("  " + e.getMessage());
                // No fallar el registro completo si el expediente falla
            }

            return idMascota;
        }

        throw new RuntimeException("No se pudo registrar la mascota");
    }

    @Override
    public boolean modificarMascota(CatalogoDTO mascotaDTO) {
        if (mascotaDTO == null || mascotaDTO.getId() == null) {
            throw new IllegalArgumentException("El ID de la mascota es obligatorio para modificar");
        }

        // Validar datos
        objetoNegocio.validarDatosMascota(mascotaDTO);

        // Validar estado si está presente
        if (mascotaDTO.getEstado() != null && !objetoNegocio.esEstadoValido(mascotaDTO.getEstado())) {
            throw new IllegalArgumentException("El estado '" + mascotaDTO.getEstado() + "' no es válido");
        }

        // Convertir a entidad
        Mascota mascota = Adaptadores.dtoAEntidad(mascotaDTO);

        // Actualizar en BD
        return mascotaDAO.actualizarMascota(mascota);
    }

    @Override
    public boolean darBajaMascota(String id) {
        if (!objetoNegocio.puedeEliminarMascota(id)) {
            throw new IllegalArgumentException("No se puede eliminar la mascota con ID: " + id);
        }

        // En baja logica mantenemos el expediente
        /*
         * try {
         * expedienteBO.eliminarExpediente(id);
         * System.out.println("Expediente médico eliminado para mascota: " + id);
         * } catch (Exception e) {
         * System.err.println("Error al eliminar expediente: " + e.getMessage());
         * // Continuar con la eliminación de la mascota de todos modos
         * }
         */

        // Eliminar mascota
        return mascotaDAO.eliminarMascota(id);
    }

    @Override
    public boolean actualizarExpedienteMedico(String id, String expediente) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID de la mascota es obligatorio");
        }

        if (expediente == null || expediente.trim().isEmpty()) {
            throw new IllegalArgumentException("El expediente médico no puede estar vacío");
        }

        // Usar ExpedienteBO para agregar entrada al historial médico
        boolean resultado = expedienteBO.agregarEntradaHistorial(id, expediente);

        if (resultado) {
            System.out.println("Expediente actualizado para mascota: " + id);
        } else {
            System.err.println("No se pudo actualizar expediente para mascota: " + id);
        }

        return resultado;
    }

    @Override
    public List<CatalogoDTO> filtrarPorEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            return obtenerCatalogoCompleto();
        }

        List<Mascota> mascotas = mascotaDAO.buscarPorEstado(estado);
        return Adaptadores.listEntidadADTO(mascotas);
    }

    @Override
    public List<CatalogoDTO> filtrarPorEspecie(String especie) {
        if (especie == null || especie.trim().isEmpty()) {
            return obtenerCatalogoCompleto();
        }

        List<Mascota> mascotas = mascotaDAO.buscarPorEspecie(especie);
        return Adaptadores.listEntidadADTO(mascotas);
    }

    @Override
    public List<CatalogoDTO> obtenerMascotasDisponibles() {
        List<Mascota> mascotas = mascotaDAO.obtenerMascotasDisponibles();
        return Adaptadores.listEntidadADTO(mascotas);
    }
}
