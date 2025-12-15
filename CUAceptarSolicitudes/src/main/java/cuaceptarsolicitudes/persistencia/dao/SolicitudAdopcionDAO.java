package cuaceptarsolicitudes.persistencia.dao;

import cuaceptarsolicitudes.persistencia.entities.SolicitudAdopcion;
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
 * DAO para manejar solicitudes de adopcion en MongoDB
 */
public class SolicitudAdopcionDAO {

    private final MongoCollection<Document> collection;

    public SolicitudAdopcionDAO(MongoDatabase database) {
        this.collection = database.getCollection("solicitudes_adopcion");
    }

    public List<SolicitudAdopcion> buscarTodas() {
        List<SolicitudAdopcion> solicitudes = new ArrayList<>();
        for (Document doc : collection.find()) {
            solicitudes.add(convertirDocumentoASolicitud(doc));
        }
        return solicitudes;
    }

    public SolicitudAdopcion buscarPorId(ObjectId id) {
        Document doc = collection.find(new Document("_id", id)).first();
        return doc != null ? convertirDocumentoASolicitud(doc) : null;
    }

    public List<SolicitudAdopcion> buscarPorUsuario(ObjectId idUsuario) {
        List<SolicitudAdopcion> solicitudes = new ArrayList<>();
        for (Document doc : collection.find(new Document("idUsuario", idUsuario))) {
            solicitudes.add(convertirDocumentoASolicitud(doc));
        }
        return solicitudes;
    }

    public ObjectId insertar(SolicitudAdopcion solicitud) {
        Document doc = convertirSolicitudADocumento(solicitud);
        collection.insertOne(doc);
        return doc.getObjectId("_id");
    }

    public void actualizar(SolicitudAdopcion solicitud) {
        Document doc = convertirSolicitudADocumento(solicitud);
        collection.replaceOne(new Document("_id", solicitud.getId()), doc);
    }

    public void eliminar(ObjectId id) {
        collection.deleteOne(new Document("_id", id));
    }

    /**
     * Convierte un objeto SolicitudAdopcion a documento de MongoDB
     */
    private Document convertirSolicitudADocumento(SolicitudAdopcion solicitud) {
        Document doc = new Document();

        if (solicitud.getId() != null) {
            doc.append("_id", solicitud.getId());
        }

        doc.append("idUsuario", solicitud.getIdUsuario())
                .append("idMascota", solicitud.getIdMascota())
                .append("idCita", solicitud.getIdCita())
                .append("estado", solicitud.getEstado())
                .append("mensajeCorreccion", solicitud.getMensajeCorreccion());

        if (solicitud.getFechaSolicitud() != null) {
            doc.append("fechaSolicitud", Date.from(solicitud.getFechaSolicitud()
                    .atZone(ZoneId.systemDefault()).toInstant()));
        }

        return doc;
    }

    /**
     * Convierte un documento de MongoDB a objeto SolicitudAdopcion
     */
    private SolicitudAdopcion convertirDocumentoASolicitud(Document doc) {
        SolicitudAdopcion solicitud = new SolicitudAdopcion();

        solicitud.setId(doc.getObjectId("_id"));
        solicitud.setIdUsuario(doc.getObjectId("idUsuario"));
        solicitud.setIdMascota(doc.getObjectId("idMascota"));
        solicitud.setIdCita(doc.getObjectId("idCita"));
        solicitud.setEstado(doc.getString("estado"));
        solicitud.setMensajeCorreccion(doc.getString("mensajeCorreccion"));

        Date fecha = doc.getDate("fechaSolicitud");
        if (fecha != null) {
            solicitud.setFechaSolicitud(LocalDateTime.ofInstant(
                    fecha.toInstant(), ZoneId.systemDefault()));
        }

        return solicitud;
    }
}
