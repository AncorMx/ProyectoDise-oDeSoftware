package cubuscarmascotaideal.persistencia.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import cubuscarmascotaideal.persistencia.entities.ResultadoMascotaIdeal;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DAO para guardar y buscar resultados de busqueda de mascota ideal
 */
public class ResultadoMascotaIdealDAO {

    private final MongoCollection<Document> collection;

    public ResultadoMascotaIdealDAO(MongoDatabase database) {
        this.collection = database.getCollection("resultados_mascota_ideal");
    }

    /**
     * Guarda un resultado de busqueda en la base de datos
     */
    public ObjectId guardar(ResultadoMascotaIdeal resultado) {
        Document doc = convertirResultadoADocumento(resultado);
        collection.insertOne(doc);
        return doc.getObjectId("_id");
    }

    /**
     * Guarda varios resultados de una sola vez
     */
    public void guardarTodos(List<ResultadoMascotaIdeal> resultados) {
        // Si la lista esta vacia, no hago nada
        if (resultados == null || resultados.isEmpty()) {
            return;
        }

        // Convierto cada resultado a documento
        List<Document> documentos = new ArrayList<>();
        for (ResultadoMascotaIdeal resultado : resultados) {
            documentos.add(convertirResultadoADocumento(resultado));
        }

        // Guardo todos de una vez
        collection.insertMany(documentos);
    }

    /**
     * Busca todos los resultados guardados de un usuario
     */
    public List<ResultadoMascotaIdeal> buscarPorUsuario(ObjectId idUsuario) {
        List<ResultadoMascotaIdeal> resultados = new ArrayList<>();

        for (Document doc : collection.find(Filters.eq("idUsuario", idUsuario))) {
            resultados.add(convertirDocumentoAResultado(doc));
        }

        return resultados;
    }

    /**
     * Borra todos los resultados anteriores de un usuario
     */
    public void eliminarPorUsuario(ObjectId idUsuario) {
        collection.deleteMany(Filters.eq("idUsuario", idUsuario));
    }

    /**
     * Convierte un documento de MongoDB a objeto ResultadoMascotaIdeal
     */
    private ResultadoMascotaIdeal convertirDocumentoAResultado(Document doc) {
        ResultadoMascotaIdeal resultado = new ResultadoMascotaIdeal();

        resultado.setId(doc.getObjectId("_id"));
        resultado.setIdUsuario(doc.getObjectId("idUsuario"));
        resultado.setIdMascota(doc.getObjectId("idMascota"));

        // Manejo el porcentaje con cuidado por si viene null
        Double porcentaje = doc.getDouble("porcentajeCompatibilidad");
        resultado.setPorcentajeCompatibilidad(porcentaje != null ? porcentaje : 0.0);

        // Convierto la fecha de Date a LocalDateTime
        Date fecha = doc.getDate("fechaBusqueda");
        if (fecha != null) {
            resultado.setFechaBusqueda(
                    fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        }

        return resultado;
    }

    /**
     * Convierte un objeto ResultadoMascotaIdeal a documento de MongoDB
     */
    private Document convertirResultadoADocumento(ResultadoMascotaIdeal resultado) {
        Document doc = new Document();

        // Si ya tiene ID, lo guardo
        if (resultado.getId() != null) {
            doc.append("_id", resultado.getId());
        }

        // Guardo todos los campos del resultado
        doc.append("idUsuario", resultado.getIdUsuario())
                .append("idMascota", resultado.getIdMascota())
                .append("porcentajeCompatibilidad", resultado.getPorcentajeCompatibilidad());

        // Guardo la fecha (si no tiene, uso la fecha actual)
        if (resultado.getFechaBusqueda() != null) {
            doc.append("fechaBusqueda",
                    Date.from(resultado.getFechaBusqueda().atZone(ZoneId.systemDefault()).toInstant()));
        } else {
            doc.append("fechaBusqueda", new Date());
        }

        return doc;
    }
}
