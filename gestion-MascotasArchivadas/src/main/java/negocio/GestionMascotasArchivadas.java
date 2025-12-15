package negocio;

import entities.Mascota;
import gestion.mascotasarchivadas.dtos.MascotaArchivoDTO;
import negocio.IGestionMascotasArchivadas;
import negocio.ObjetoNegocio;
import persistencia.IMascotaArchivoDAO;
import persistencia.MascotaArchivoDAO;

import java.util.List;

/**
 * Implementación de la lógica de negocio para gestión de mascotas archivadas.
 * Coordina entre la capa de persistencia y aplica reglas de negocio.
 * 
 * @author angel
 */
public class GestionMascotasArchivadas implements IGestionMascotasArchivadas {

    private final IMascotaArchivoDAO mascotaArchivoDAO;
    private final daos.ExpedienteMedicoDAO expedienteMedicoDAO;
    private final ObjetoNegocio objetoNegocio;

    /**
     * Constructor por defecto.
     * Inicializa DAOs y objetos de negocio.
     */
    public GestionMascotasArchivadas() {
        this.mascotaArchivoDAO = new MascotaArchivoDAO();
        this.objetoNegocio = new ObjetoNegocio();
        com.mongodb.client.MongoDatabase database = conexion.ConexionMongoDB.getInstancia().getDatabase();
        this.expedienteMedicoDAO = new daos.ExpedienteMedicoDAO(database);
    }

    // Constructor con inyección de dependencias para testing
    /**
     * Constructor con inyección de dependencias para testing.
     * 
     * @param mascotaArchivoDAO DAO de mascotas archivadas.
     * @param objetoNegocio     Objeto de negocio.
     */
    public GestionMascotasArchivadas(IMascotaArchivoDAO mascotaArchivoDAO, ObjetoNegocio objetoNegocio) {
        this.mascotaArchivoDAO = mascotaArchivoDAO;
        this.objetoNegocio = objetoNegocio;
        com.mongodb.client.MongoDatabase database = conexion.ConexionMongoDB.getInstancia().getDatabase();
        this.expedienteMedicoDAO = new daos.ExpedienteMedicoDAO(database);
    }

    @Override
    public List<MascotaArchivoDTO> obtenerMascotasArchivadas() {
        try {
            List<Mascota> mascotas = mascotaArchivoDAO.obtenerMascotasArchivadas();
            return Adaptadores.listEntidadADTO(mascotas);
        } catch (Exception e) {
            System.err.println("Error al obtener mascotas archivadas: " + e.getMessage());
            e.printStackTrace();
            return List.of(); // Retornar lista vacía en caso de error
        }
    }

    @Override
    public MascotaArchivoDTO obtenerDetalleMascota(String id) {
        if (id == null || id.trim().isEmpty()) {
            System.err.println("ID de mascota inválido");
            return null;
        }

        try {
            Mascota mascota = mascotaArchivoDAO.obtenerMascotaPorId(id);
            if (mascota == null)
                return null;

            MascotaArchivoDTO dto = Adaptadores.entidadADTO(mascota);

            // Buscar expediente médico asociado
            entities.ExpedienteMedico expediente = expedienteMedicoDAO.buscarPorMascotaId(mascota.getId());
            if (expediente != null) {
                dto.setAlergias(expediente.getAlergias());
                dto.setCondicionesEspeciales(expediente.getCondicionesEspeciales());
                dto.setNivelEnergia(expediente.getNivelEnergia());

                // Construir string de vacunas para el UI
                StringBuilder vacunas = new StringBuilder();
                if (expediente.isVacunaRabia())
                    vacunas.append("Rabia, ");
                if (expediente.isVacunaDesparasitacionExterna())
                    vacunas.append("Desparasitación Externa, ");
                if (expediente.isVacunaBordetella())
                    vacunas.append("Bordetella, ");
                if (expediente.isVacunaDesparasitacionInterna())
                    vacunas.append("Desparasitación Interna, ");
                if (expediente.isVacunaMultiple())
                    vacunas.append("Múltiple/Quíntuple, ");
                if (expediente.isEsterilizado())
                    vacunas.append("Esterilizado, ");

                dto.setExpedienteMedico(vacunas.toString());
            } else {
                // Asignar valores por defecto para mostrar en la interfaz
                dto.setAlergias("Sin registros");
                dto.setCondicionesEspeciales("Sin registros");
                dto.setNivelEnergia("Desconocido");
                dto.setExpedienteMedico("");
            }

            return dto;
        } catch (Exception e) {
            System.err.println("Error al obtener detalle de mascota: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean eliminarPermanente(String id) {
        // Validar con reglas de negocio
        if (!objetoNegocio.puedeEliminarPermanente(id)) {
            System.err.println("No se puede eliminar permanentemente la mascota con ID: " + id);
            return false;
        }

        try {
            boolean resultado = mascotaArchivoDAO.eliminarPermanente(id);

            if (resultado) {
                System.out.println("Mascota eliminada permanentemente: " + id);
            } else {
                System.err.println("No se pudo eliminar la mascota: " + id);
            }

            return resultado;
        } catch (Exception e) {
            System.err.println("Error al eliminar permanentemente mascota: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean reactivarMascota(String id) {
        if (id == null || id.trim().isEmpty()) {
            System.err.println("ID de mascota inválido");
            return false;
        }

        try {
            // Obtener datos de la mascota para validar
            Mascota mascota = mascotaArchivoDAO.obtenerMascotaPorId(id);
            if (mascota == null) {
                System.err.println("Mascota no encontrada: " + id);
                return false;
            }

            MascotaArchivoDTO dto = Adaptadores.entidadADTO(mascota);

            // Validar con reglas de negocio
            if (!objetoNegocio.puedeReactivar(dto)) {
                System.err.println("No se puede reactivar la mascota con ID: " + id);
                return false;
            }

            // Preparar para reactivación (cambia estado y disponibilidad)
            objetoNegocio.prepararParaReactivacion(dto);

            // Reactivar en la base de datos
            boolean resultado = mascotaArchivoDAO.reactivarMascota(id);

            if (resultado) {
                System.out.println("Mascota reactivada exitosamente: " + id);
            } else {
                System.err.println("No se pudo reactivar la mascota: " + id);
            }

            return resultado;
        } catch (Exception e) {
            System.err.println("Error al reactivar mascota: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<MascotaArchivoDTO> filtrarPorEspecie(String especie) {
        if (especie == null || especie.trim().isEmpty()) {
            System.out.println("Especie vacía, retornando todas las mascotas archivadas");
            return obtenerMascotasArchivadas();
        }

        try {
            List<Mascota> mascotas = mascotaArchivoDAO.buscarPorEspecie(especie);
            return Adaptadores.listEntidadADTO(mascotas);
        } catch (Exception e) {
            System.err.println("Error al filtrar por especie: " + e.getMessage());
            e.printStackTrace();
            return List.of(); // Retornar lista vacía en caso de error
        }
    }
}
