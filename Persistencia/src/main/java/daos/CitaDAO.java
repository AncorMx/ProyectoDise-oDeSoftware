package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entities.Cita;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DAO para manejar citas en MongoDB
 */
public class CitaDAO {

    private final MongoCollection<Document> collection;

    public CitaDAO(MongoDatabase database) {
        this.collection = database.getCollection("citas");
    }

    /**
     * Guarda una nueva cita
     */
    public ObjectId guardar(Cita cita) {
        Document doc = convertirCitaADocumento(cita);
        collection.insertOne(doc);
        return doc.getObjectId("_id");
    }

    /**
     * Busca una cita por su ID
     */
    public Cita buscarPorId(ObjectId id) {
        Document doc = collection.find(Filters.eq("_id", id)).first();
        if (doc == null) {
            return null;
        }
        return convertirDocumentoACita(doc);
    }

    /**
     * Busca todas las citas de un usuario
     */
    public List<Cita> buscarPorUsuario(ObjectId idUsuario) {
        List<Cita> citas = new ArrayList<>();
        for (Document doc : collection.find(Filters.eq("idUsuario", idUsuario))) {
            citas.add(convertirDocumentoACita(doc));
        }
        return citas;
    }

    /**
     * Convierte un documento de MongoDB a entidad Cita
     */
    private Cita convertirDocumentoACita(Document doc) {
        Cita cita = new Cita();
        cita.setId(doc.getObjectId("_id"));
        cita.setIdUsuario(doc.getObjectId("idUsuario"));
        cita.setIdMascota(doc.getObjectId("idMascota"));

        Date fecha = doc.getDate("fechaHora");
        if (fecha != null) {
            cita.setFechaHora(fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        }

        return cita;
    }

    /**
     * Convierte una entidad Cita a documento de MongoDB
     */
    private Document convertirCitaADocumento(Cita cita) {
        Document doc = new Document();
        if (cita.getId() != null) {
            doc.append("_id", cita.getId());
        }
        doc.append("idUsuario", cita.getIdUsuario())
                .append("idMascota", cita.getIdMascota());

        if (cita.getFechaHora() != null) {
            doc.append("fechaHora", Date.from(cita.getFechaHora().atZone(ZoneId.systemDefault()).toInstant()));
        }

        return doc;
    }
}
