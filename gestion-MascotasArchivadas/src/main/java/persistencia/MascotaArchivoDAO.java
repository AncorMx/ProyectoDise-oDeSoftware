package persistencia;

import com.mongodb.client.MongoDatabase;
import conexion.ConexionMongoDB;
import entities.Mascota;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del DAO de mascotas archivadas.
 * Reutiliza la conexión MongoDB compartida y trabaja con entities.Mascota.
 * Se enfoca en mascotas con estado "baja".
 */
public class MascotaArchivoDAO implements IMascotaArchivoDAO {

    private final daos.MascotaDAO mascotaDAOCompartido;

    /**
     * Constructor por defecto.
     * Inicializa la conexión a la base de datos reutilizando la instancia
     * compartida.
     */
    public MascotaArchivoDAO() {
        // Reutilizar la conexión compartida
        MongoDatabase database = ConexionMongoDB.getInstancia().getDatabase();
        this.mascotaDAOCompartido = new daos.MascotaDAO(database);
    }

    @Override
    public List<Mascota> obtenerMascotasArchivadas() {
        // Obtener todas las mascotas y filtrar las archivadas (estado = "baja")
        List<Mascota> todasMascotas = mascotaDAOCompartido.buscarTodas();
        List<Mascota> mascotasArchivadas = new ArrayList<>();

        for (Mascota mascota : todasMascotas) {
            String estado = mascota.getEstado() != null ? mascota.getEstado().toLowerCase() : "";
            if (estado.startsWith("baja")) {
                mascotasArchivadas.add(mascota);
            }
        }

        return mascotasArchivadas;
    }

    @Override
    public Mascota obtenerMascotaPorId(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            Mascota mascota = mascotaDAOCompartido.buscarPorId(objectId);

            // Verificar que la mascota esté archivada
            if (mascota != null) {
                String estado = mascota.getEstado() != null ? mascota.getEstado().toLowerCase() : "";
                if (estado.startsWith("baja")) {
                    return mascota;
                }
            }
            return null;
        } catch (IllegalArgumentException e) {
            System.err.println("ID inválido: " + id);
            return null;
        }
    }

    @Override
    public boolean eliminarPermanente(String id) {
        try {
            ObjectId objectId = new ObjectId(id);

            // Verificar que la mascota esté archivada antes de eliminar
            Mascota mascota = mascotaDAOCompartido.buscarPorId(objectId);
            if (mascota == null) {
                System.err.println("Mascota no encontrada: " + id);
                return false;
            }

            String estado = mascota.getEstado() != null ? mascota.getEstado().toLowerCase() : "";
            if (!estado.startsWith("baja")) {
                System.err.println("Solo se pueden eliminar permanentemente mascotas archivadas");
                return false;
            }

            // Eliminación física de la base de datos
            mascotaDAOCompartido.eliminar(objectId);
            System.out.println("Mascota eliminada permanentemente: " + id);
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar permanentemente mascota: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean reactivarMascota(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            Mascota mascota = mascotaDAOCompartido.buscarPorId(objectId);

            if (mascota == null) {
                System.err.println("Mascota no encontrada: " + id);
                return false;
            }

            String estado = mascota.getEstado() != null ? mascota.getEstado().toLowerCase() : "";
            if (!estado.startsWith("baja")) {
                System.err.println("Solo se pueden reactivar mascotas archivadas");
                return false;
            }

            // Cambiar estado a disponible
            mascota.setEstado("disponible");
            mascota.setDisponible(true);

            mascotaDAOCompartido.actualizar(mascota);
            System.out.println("Mascota reactivada: " + id);
            return true;
        } catch (Exception e) {
            System.err.println("Error al reactivar mascota: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Mascota> buscarPorEspecie(String especie) {
        List<Mascota> mascotasArchivadas = obtenerMascotasArchivadas();
        List<Mascota> mascotasFiltradas = new ArrayList<>();

        for (Mascota mascota : mascotasArchivadas) {
            if (especie.equalsIgnoreCase(mascota.getEspecie())) {
                mascotasFiltradas.add(mascota);
            }
        }

        return mascotasFiltradas;
    }
}
