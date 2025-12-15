package cuaceptarsolicitudes.persistencia.dao;

import cuaceptarsolicitudes.persistencia.entities.Mascota;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de Mascota.
 */
public class MascotaDAO {

    private final MongoCollection<Document> collection;

    public MascotaDAO(MongoDatabase database) {
        this.collection = database.getCollection("mascotas");
    }

    public Mascota buscarPorId(ObjectId id) {
        Document doc = collection.find(new Document("_id", id)).first();
        return doc != null ? documentToMascota(doc) : null;
    }

    public List<Mascota> buscarTodas() {
        List<Mascota> mascotas = new ArrayList<>();
        for (Document doc : collection.find()) {
            mascotas.add(documentToMascota(doc));
        }
        return mascotas;
    }

    public List<Mascota> buscarDisponibles() {
        List<Mascota> mascotas = new ArrayList<>();
        for (Document doc : collection.find(new Document("disponible", true))) {
            mascotas.add(documentToMascota(doc));
        }
        return mascotas;
    }

    public ObjectId insertar(Mascota mascota) {
        Document doc = mascotaToDocument(mascota);
        collection.insertOne(doc);
        return doc.getObjectId("_id");
    }

    public void actualizar(Mascota mascota) {
        Document doc = mascotaToDocument(mascota);
        collection.replaceOne(new Document("_id", mascota.getId()), doc);
    }

    private Document mascotaToDocument(Mascota mascota) {
        Document doc = new Document();
        if (mascota.getId() != null) {
            doc.append("_id", mascota.getId());
        }
        doc.append("nombre", mascota.getNombre());
        doc.append("especie", mascota.getEspecie());
        doc.append("raza", mascota.getRaza());
        doc.append("edad", mascota.getEdad());
        doc.append("sexo", mascota.getSexo());
        doc.append("disponible", mascota.isDisponible());
        doc.append("estado", mascota.getEstado());
        doc.append("tamano", mascota.getTamano());
        doc.append("peludo", mascota.isPeludo());
        doc.append("nivelActividad", mascota.getNivelActividad());
        doc.append("costoMantenimiento", mascota.getCostoMantenimiento());
        return doc;
    }

    private Mascota documentToMascota(Document doc) {
        Mascota mascota = new Mascota();
        mascota.setId(doc.getObjectId("_id"));
        mascota.setNombre(doc.getString("nombre"));
        mascota.setEspecie(doc.getString("especie"));
        mascota.setRaza(doc.getString("raza"));
        mascota.setEdad(doc.getInteger("edad", 0));
        mascota.setSexo(doc.getString("sexo"));
        mascota.setDisponible(doc.getBoolean("disponible", true));
        mascota.setEstado(doc.getString("estado"));
        mascota.setTamano(doc.getString("tamano"));
        mascota.setPeludo(doc.getBoolean("peludo", false));
        mascota.setNivelActividad(doc.getString("nivelActividad"));
        mascota.setCostoMantenimiento(doc.getString("costoMantenimiento"));
        return mascota;
    }
}
