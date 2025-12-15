package cubuscarmascotaideal.persistencia.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import cubuscarmascotaideal.persistencia.entities.Encuesta;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DAO para guardar y buscar encuestas en MongoDB
 */
public class EncuestaDAO {

    private final MongoCollection<Document> collection;

    public EncuestaDAO(MongoDatabase database) {
        this.collection = database.getCollection("encuestas");
    }

    /**
     * Guarda una encuesta en la base de datos
     */
    public ObjectId guardar(Encuesta encuesta) {
        Document doc = convertirEncuestaADocumento(encuesta);
        collection.insertOne(doc);
        return doc.getObjectId("_id");
    }

    /**
     * Busca la encuesta más reciente de un usuario
     */
    public Encuesta buscarPorUsuario(ObjectId idUsuario) {
        Document doc = collection.find(Filters.eq("idUsuario", idUsuario))
                .sort(new Document("fechaRespuesta", -1))
                .first();

        if (doc == null) {
            return null;
        }

        return convertirDocumentoAEncuesta(doc);
    }

    /**
     * Busca todas las encuestas de un usuario
     */
    public List<Encuesta> buscarTodasPorUsuario(ObjectId idUsuario) {
        List<Encuesta> encuestas = new ArrayList<>();

        for (Document doc : collection.find(Filters.eq("idUsuario", idUsuario))
                .sort(new Document("fechaRespuesta", -1))) {
            encuestas.add(convertirDocumentoAEncuesta(doc));
        }

        return encuestas;
    }

    /**
     * Elimina las encuestas viejas, deja solo la más reciente
     */
    public void eliminarAnterioresPorUsuario(ObjectId idUsuario) {
        // Busco la encuesta más reciente
        Document masReciente = collection.find(Filters.eq("idUsuario", idUsuario))
                .sort(new Document("fechaRespuesta", -1))
                .first();

        // Si encontré una, borro todas las demás
        if (masReciente != null) {
            ObjectId idMasReciente = masReciente.getObjectId("_id");
            collection.deleteMany(Filters.and(
                    Filters.eq("idUsuario", idUsuario),
                    Filters.ne("_id", idMasReciente)));
        }
    }

    /**
     * Convierte un documento de MongoDB a objeto Encuesta
     */
    private Encuesta convertirDocumentoAEncuesta(Document doc) {
        Encuesta encuesta = new Encuesta();

        encuesta.setId(doc.getObjectId("_id"));
        encuesta.setIdUsuario(doc.getObjectId("idUsuario"));
        encuesta.setTamano(doc.getString("tamano"));
        encuesta.setNivelActividad(doc.getString("nivelActividad"));
        encuesta.setPeludo(doc.getBoolean("peludo", false));
        encuesta.setCostoMantenimiento(doc.getString("costoMantenimiento"));
        encuesta.setTieneAlergias(doc.getBoolean("tieneAlergias", false));
        encuesta.setViveEnDepartamento(doc.getBoolean("viveEnDepartamento", false));

        // Convierto la fecha de Date a LocalDateTime
        Date fecha = doc.getDate("fechaRespuesta");
        if (fecha != null) {
            encuesta.setFechaRespuesta(
                    fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        }

        return encuesta;
    }

    /**
     * Convierte un objeto Encuesta a documento de MongoDB
     */
    private Document convertirEncuestaADocumento(Encuesta encuesta) {
        Document doc = new Document();

        // Si ya tiene ID, lo guardo
        if (encuesta.getId() != null) {
            doc.append("_id", encuesta.getId());
        }

        // Guardo todos los campos de la encuesta
        doc.append("idUsuario", encuesta.getIdUsuario())
                .append("tamano", encuesta.getTamano())
                .append("nivelActividad", encuesta.getNivelActividad())
                .append("peludo", encuesta.isPeludo())
                .append("costoMantenimiento", encuesta.getCostoMantenimiento())
                .append("tieneAlergias", encuesta.isTieneAlergias())
                .append("viveEnDepartamento", encuesta.isViveEnDepartamento());

        // Guardo la fecha (si no tiene, uso la fecha actual)
        if (encuesta.getFechaRespuesta() != null) {
            doc.append("fechaRespuesta",
                    Date.from(encuesta.getFechaRespuesta().atZone(ZoneId.systemDefault()).toInstant()));
        } else {
            doc.append("fechaRespuesta", new Date());
        }

        return doc;
    }
}
