package cuaceptarsolicitudes.persistencia.dao;

import cuaceptarsolicitudes.persistencia.entities.Cita;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DAO para operaciones CRUD de Cita.
 */
public class CitaDAO {

    private final MongoCollection<Document> collection;

    public CitaDAO(MongoDatabase database) {
        this.collection = database.getCollection("citas");
    }

    public Cita buscarPorId(ObjectId id) {
        Document doc = collection.find(new Document("_id", id)).first();
        return doc != null ? documentToCita(doc) : null;
    }

    public List<Cita> buscarPorUsuario(ObjectId idUsuario) {
        List<Cita> citas = new ArrayList<>();
        for (Document doc : collection.find(new Document("idUsuario", idUsuario))) {
            citas.add(documentToCita(doc));
        }
        return citas;
    }

    public ObjectId insertar(Cita cita) {
        Document doc = citaToDocument(cita);
        collection.insertOne(doc);
        return doc.getObjectId("_id");
    }

    public void actualizar(Cita cita) {
        Document doc = citaToDocument(cita);
        collection.replaceOne(new Document("_id", cita.getId()), doc);
    }

    private Document citaToDocument(Cita cita) {
        Document doc = new Document();
        if (cita.getId() != null) {
            doc.append("_id", cita.getId());
        }
        doc.append("idUsuario", cita.getIdUsuario());
        doc.append("idMascota", cita.getIdMascota());
        doc.append("estado", cita.getEstado());
        if (cita.getFechaHora() != null) {
            doc.append("fechaHora", Date.from(cita.getFechaHora()
                    .atZone(ZoneId.systemDefault()).toInstant()));
        }
        return doc;
    }

    private Cita documentToCita(Document doc) {
        Cita cita = new Cita();
        cita.setId(doc.getObjectId("_id"));
        cita.setIdUsuario(doc.getObjectId("idUsuario"));
        cita.setIdMascota(doc.getObjectId("idMascota"));
        cita.setEstado(doc.getString("estado"));
        Date fecha = doc.getDate("fechaHora");
        if (fecha != null) {
            cita.setFechaHora(LocalDateTime.ofInstant(
                    fecha.toInstant(), ZoneId.systemDefault()));
        }
        return cita;
    }
}
