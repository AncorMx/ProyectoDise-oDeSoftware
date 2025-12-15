package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import entities.CitaDisponible;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DAO para gestionar citas disponibles en MongoDB
 * 
 * @author Sistema
 */
public class CitaDisponibleDAO {
    private final MongoDatabase database;
    private final MongoCollection<Document> coleccion;

    public CitaDisponibleDAO() {
        this.database = conexion.ConexionMongoDB.getInstancia().getDatabase();
        this.coleccion = database.getCollection("citasDisponibles");
    }

    /**
     * Obtiene todas las citas disponibles
     * 
     * @return Lista de citas disponibles
     */
    public List<CitaDisponible> obtenerCitasDisponibles() {
        List<CitaDisponible> citas = new ArrayList<>();

        try {
            // Filtrar solo las citas disponibles
            coleccion.find(Filters.eq("disponible", true))
                    .sort(new Document("fecha", 1).append("hora", 1))
                    .forEach(doc -> {
                        CitaDisponible cita = documentToEntity(doc);
                        citas.add(cita);
                    });
        } catch (Exception e) {
            System.err.println("Error al obtener citas disponibles: " + e.getMessage());
            e.printStackTrace();
        }

        return citas;
    }

    /**
     * Marca una cita como ocupada
     * 
     * @param idCita    ID de la cita
     * @param idUsuario ID del usuario que reserva
     * @return true si se marcó exitosamente
     */
    public boolean marcarCitaOcupada(String idCita, String idUsuario) {
        try {
            ObjectId citaObjId = new ObjectId(idCita);
            ObjectId usuarioObjId = idUsuario != null ? new ObjectId(idUsuario) : null;

            // Actualizar disponibilidad y asignar usuario
            coleccion.updateOne(
                    Filters.eq("_id", citaObjId),
                    Updates.combine(
                            Updates.set("disponible", false),
                            Updates.set("idUsuario", usuarioObjId)));

            return true;
        } catch (Exception e) {
            System.err.println("Error al marcar cita como ocupada: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Libera una cita (la marca como disponible nuevamente)
     * 
     * @param idCita ID de la cita
     * @return true si se liberó exitosamente
     */
    public boolean liberarCita(String idCita) {
        try {
            ObjectId citaObjId = new ObjectId(idCita);

            coleccion.updateOne(
                    Filters.eq("_id", citaObjId),
                    Updates.combine(
                            Updates.set("disponible", true),
                            Updates.set("idUsuario", null)));

            return true;
        } catch (Exception e) {
            System.err.println("Error al liberar cita: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Verifica si una cita específica está disponible
     * 
     * @param idCita ID de la cita
     * @return true si está disponible
     */
    public boolean estaCitaDisponible(String idCita) {
        try {
            ObjectId citaObjId = new ObjectId(idCita);
            Document doc = coleccion.find(
                    Filters.and(
                            Filters.eq("_id", citaObjId),
                            Filters.eq("disponible", true)))
                    .first();

            return doc != null;
        } catch (Exception e) {
            System.err.println("Error al verificar disponibilidad de cita: " + e.getMessage());
            return false;
        }
    }

    /**
     * Convierte un Document de MongoDB a una entidad CitaDisponible
     */
    private CitaDisponible documentToEntity(Document doc) {
        CitaDisponible cita = new CitaDisponible();
        cita.setId(doc.getObjectId("_id"));
        cita.setFecha(doc.getDate("fecha"));
        cita.setHora(doc.getString("hora"));
        cita.setDisponible(doc.getBoolean("disponible", true));
        cita.setIdUsuario(doc.getObjectId("idUsuario"));
        return cita;
    }

    /**
     * Convierte una entidad CitaDisponible a un Document de MongoDB
     */
    private Document entityToDocument(CitaDisponible cita) {
        Document doc = new Document();
        if (cita.getId() != null) {
            doc.append("_id", cita.getId());
        }
        doc.append("fecha", cita.getFecha());
        doc.append("hora", cita.getHora());
        doc.append("disponible", cita.isDisponible());
        doc.append("idUsuario", cita.getIdUsuario());
        return doc;
    }
}
