package persistencia;

import com.mongodb.client.MongoDatabase;
import conexion.ConexionMongoDB;
import entities.Mascota;
import persistencia.IMascotaDAO;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del DAO de mascotas para el módulo de gestión de catálogo.
 * Reutiliza la conexión MongoDB compartida y trabaja con entities.Mascota.
 */
public class MascotaDAO implements IMascotaDAO {

    private final daos.MascotaDAO mascotaDAOCompartido;

    public MascotaDAO() {
        // Reutilizar la conexión compartida
        MongoDatabase database = ConexionMongoDB.getInstancia().getDatabase();
        this.mascotaDAOCompartido = new daos.MascotaDAO(database);
    }

    @Override
    public List<Mascota> obtenerTodasMascotas() {
        return mascotaDAOCompartido.buscarTodas();
    }

    @Override
    public Mascota obtenerMascotaPorId(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return mascotaDAOCompartido.buscarPorId(objectId);
        } catch (IllegalArgumentException e) {
            System.err.println("ID inválido: " + id);
            return null;
        }
    }

    @Override
    public boolean agregarMascota(Mascota mascota) {
        try {
            ObjectId generatedId = mascotaDAOCompartido.guardar(mascota);
            if (generatedId != null) {
                mascota.setId(generatedId);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error al agregar mascota: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarMascota(Mascota mascota) {
        try {
            mascotaDAOCompartido.actualizar(mascota);
            return true;
        } catch (Exception e) {
            System.err.println("Error al actualizar mascota: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarMascota(String id) {
        try {
            // Implementar eliminación lógica (cambiar estado a "baja")
            Mascota mascota = obtenerMascotaPorId(id);
            if (mascota != null) {
                mascota.setEstado("baja");
                mascota.setDisponible(false);
                return actualizarMascota(mascota);
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error al eliminar mascota: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Mascota> buscarPorEstado(String estado) {
        List<Mascota> todasMascotas = obtenerTodasMascotas();
        List<Mascota> mascotasFiltradas = new ArrayList<>();

        for (Mascota mascota : todasMascotas) {
            if (estado.equalsIgnoreCase(mascota.getEstado())) {
                mascotasFiltradas.add(mascota);
            }
        }

        return mascotasFiltradas;
    }

    @Override
    public List<Mascota> buscarPorEspecie(String especie) {
        List<Mascota> todasMascotas = obtenerTodasMascotas();
        List<Mascota> mascotasFiltradas = new ArrayList<>();

        for (Mascota mascota : todasMascotas) {
            if (especie.equalsIgnoreCase(mascota.getEspecie())) {
                mascotasFiltradas.add(mascota);
            }
        }

        return mascotasFiltradas;
    }

    @Override
    public List<Mascota> obtenerMascotasDisponibles() {
        return mascotaDAOCompartido.buscarDisponibles();
    }
}
