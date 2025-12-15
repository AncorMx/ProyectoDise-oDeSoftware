package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entities.SolicitudAdopcion;
import entities.RazonesAntecedentes;
import org.bson.Document;
import org.bson.types.ObjectId;
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
        this.collection = database.getCollection("solicitudes");
    }

    /**
     * Guarda una nueva solicitud de adopcion
     */
    public ObjectId guardar(SolicitudAdopcion solicitud) {
        Document doc = convertirSolicitudADocumento(solicitud);
        collection.insertOne(doc);
        return doc.getObjectId("_id");
    }

    /**
     * Actualiza una solicitud de adopcion existente
     */
    public void actualizar(SolicitudAdopcion solicitud) {
        Document doc = convertirSolicitudADocumento(solicitud);
        collection.replaceOne(Filters.eq("_id", solicitud.getId()), doc);
    }

    /**
     * Busca una solicitud por su ID
     */
    public SolicitudAdopcion buscarPorId(ObjectId id) {
        Document doc = collection.find(Filters.eq("_id", id)).first();
        if (doc == null) {
            return null;
        }
        return convertirDocumentoASolicitud(doc);
    }

    /**
     * Busca todas las solicitudes de un usuario
     */
    public List<SolicitudAdopcion> buscarPorUsuario(ObjectId idUsuario) {
        List<SolicitudAdopcion> solicitudes = new ArrayList<>();
        for (Document doc : collection.find(Filters.eq("idUsuario", idUsuario))) {
            solicitudes.add(convertirDocumentoASolicitud(doc));
        }
        return solicitudes;
    }

    /**
     * Busca todas las solicitudes en el sistema
     */
    public List<SolicitudAdopcion> buscarTodas() {
        List<SolicitudAdopcion> solicitudes = new ArrayList<>();
        for (Document doc : collection.find()) {
            solicitudes.add(convertirDocumentoASolicitud(doc));
        }
        return solicitudes;
    }

    /**
     * Convierte un documento de MongoDB a entidad SolicitudAdopcion
     */
    private SolicitudAdopcion convertirDocumentoASolicitud(Document doc) {
        SolicitudAdopcion solicitud = new SolicitudAdopcion();
        solicitud.setId(doc.getObjectId("_id"));
        solicitud.setIdUsuario(doc.getObjectId("idUsuario"));
        solicitud.setIdMascota(doc.getObjectId("idMascota"));
        solicitud.setEstado(doc.getString("estado"));

        Date fecha = doc.getDate("fechaSolicitud");
        if (fecha != null) {
            solicitud.setFechaSolicitud(fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        }

        Document razonesDoc = doc.get("razones", Document.class);
        if (razonesDoc != null) {
            RazonesAntecedentes razones = new RazonesAntecedentes();
            razones.setMotivoAdopcion(razonesDoc.getString("motivoAdopcion"));
            razones.setAntecedentesMascotas(razonesDoc.getString("antecedentesMascotas"));
            razones.setAceptaSeguimiento(razonesDoc.getBoolean("aceptaSeguimiento", false));
            solicitud.setRazones(razones);
        }

        solicitud.setMensajeCorreccion(doc.getString("mensajeCorreccion"));
        solicitud.setIdCita(doc.getObjectId("idCita"));

        return solicitud;
    }

    /**
     * Convierte una entidad SolicitudAdopcion a documento de MongoDB
     */
    private Document convertirSolicitudADocumento(SolicitudAdopcion solicitud) {
        Document doc = new Document();
        if (solicitud.getId() != null) {
            doc.append("_id", solicitud.getId());
        }
        doc.append("idUsuario", solicitud.getIdUsuario())
                .append("idMascota", solicitud.getIdMascota())
                .append("estado", solicitud.getEstado());

        if (solicitud.getFechaSolicitud() != null) {
            doc.append("fechaSolicitud",
                    Date.from(solicitud.getFechaSolicitud().atZone(ZoneId.systemDefault()).toInstant()));
        }

        if (solicitud.getRazones() != null) {
            Document razonesDoc = new Document()
                    .append("motivoAdopcion", solicitud.getRazones().getMotivoAdopcion())
                    .append("antecedentesMascotas", solicitud.getRazones().getAntecedentesMascotas())
                    .append("aceptaSeguimiento", solicitud.getRazones().isAceptaSeguimiento());
            doc.append("razones", razonesDoc);
        }

        if (solicitud.getMensajeCorreccion() != null) {
            doc.append("mensajeCorreccion", solicitud.getMensajeCorreccion());
        }

        if (solicitud.getIdCita() != null) {
            doc.append("idCita", solicitud.getIdCita());
        }

        return doc;
    }
}
