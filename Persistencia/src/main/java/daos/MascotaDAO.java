/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entities.Mascota;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para manejar mascotas en MongoDB
 */
public class MascotaDAO {

    private final MongoCollection<Document> collection;

    public MascotaDAO(MongoDatabase database) {
        this.collection = database.getCollection("mascotas");
    }

    /**
     * Guarda una nueva mascota
     */
    public ObjectId guardar(Mascota mascota) {
        Document doc = convertirMascotaADocumento(mascota);
        collection.insertOne(doc);
        return doc.getObjectId("_id");
    }

    /**
     * Busca una mascota por su ID
     */
    public Mascota buscarPorId(ObjectId id) {
        Document doc = collection.find(Filters.eq("_id", id)).first();
        if (doc == null) {
            return null;
        }
        return convertirDocumentoAMascota(doc);
    }

    /**
     * Obtiene todas las mascotas
     */
    public List<Mascota> buscarTodas() {
        List<Mascota> mascotas = new ArrayList<>();
        for (Document doc : collection.find()) {
            mascotas.add(convertirDocumentoAMascota(doc));
        }
        return mascotas;
    }

    /**
     * Obtiene mascotas disponibles para adopcion
     */
    public List<Mascota> buscarDisponibles() {
        List<Mascota> mascotas = new ArrayList<>();
        for (Document doc : collection.find(Filters.eq("disponible", true))) {
            mascotas.add(convertirDocumentoAMascota(doc));
        }
        return mascotas;
    }

    /**
     * Busca mascotas por su estado de disponibilidad
     * 
     * @param disponible true para buscar mascotas disponibles, false para no
     *                   disponibles
     * @return Lista de mascotas con el estado de disponibilidad especificado
     */
    public List<Mascota> buscarPorDisponibilidad(boolean disponible) {
        List<Mascota> mascotas = new ArrayList<>();
        for (Document doc : collection.find(Filters.eq("disponible", disponible))) {
            mascotas.add(convertirDocumentoAMascota(doc));
        }
        return mascotas;
    }

    /**
     * Busca mascotas disponibles filtradas por especie
     * 
     * @param especie Especie a buscar (exact match)
     * @return Lista de mascotas disponibles de esa especie
     */
    public List<Mascota> buscarDisponiblesPorEspecie(String especie) {
        List<Mascota> mascotas = new ArrayList<>();
        // Filtro compuesto: disponible = true AND especie = especie
        for (Document doc : collection
                .find(Filters.and(Filters.eq("disponible", true), Filters.eq("especie", especie)))) {
            mascotas.add(convertirDocumentoAMascota(doc));
        }
        return mascotas;
    }

    /**
     * Convierte un documento de MongoDB a entidad Mascota
     */
    private Mascota convertirDocumentoAMascota(Document doc) {
        Mascota mascota = new Mascota();
        mascota.setId(doc.getObjectId("_id"));
        mascota.setNombre(doc.getString("nombre"));
        mascota.setEspecie(doc.getString("especie"));
        mascota.setEstadoSalud(doc.getString("estadoSalud"));
        mascota.setPersonalidad(doc.getString("personalidad"));
        mascota.setUrlImagen(doc.getString("urlImagen"));
        mascota.setEdad(doc.getInteger("edad"));
        mascota.setDisponible(doc.getBoolean("disponible"));
        mascota.setEstado(doc.getString("estado"));

        // Nuevos campos
        mascota.setColor(doc.getString("color"));
        mascota.setRaza(doc.getString("raza"));
        Double peso = doc.getDouble("peso");
        mascota.setPeso(peso != null ? peso : 0.0);

        // Campos para búsqueda de mascota ideal
        mascota.setTamano(doc.getString("tamano"));
        mascota.setNivelActividad(doc.getString("nivelActividad"));
        Boolean peludo = doc.getBoolean("peludo");
        mascota.setPeludo(peludo != null ? peludo : false);
        mascota.setCostoMantenimiento(doc.getString("costoMantenimiento"));
        mascota.setDescripcion(doc.getString("descripcion"));

        return mascota;
    }

    /**
     * Actualiza una mascota existente
     */
    public void actualizar(Mascota mascota) {
        if (mascota.getId() != null) {
            Document doc = convertirMascotaADocumento(mascota);
            collection.replaceOne(Filters.eq("_id", mascota.getId()), doc);
        }
    }

    /**
     * Elimina permanentemente una mascota de la base de datos
     */
    public boolean eliminar(ObjectId id) {
        if (id == null) {
            return false;
        }

        long deletedCount = collection.deleteOne(Filters.eq("_id", id)).getDeletedCount();
        return deletedCount > 0;
    }

    /**
     * Convierte una entidad Mascota a documento de MongoDB
     */
    private Document convertirMascotaADocumento(Mascota mascota) {
        Document doc = new Document();
        if (mascota.getId() != null) {
            doc.append("_id", mascota.getId());
        }
        doc.append("nombre", mascota.getNombre())
                .append("especie", mascota.getEspecie())
                .append("estadoSalud", mascota.getEstadoSalud())
                .append("personalidad", mascota.getPersonalidad())
                .append("urlImagen", mascota.getUrlImagen())
                .append("edad", mascota.getEdad())
                .append("disponible", mascota.isDisponible())
                .append("estado", mascota.getEstado())
                // Campos adicionales
                .append("color", mascota.getColor())
                .append("raza", mascota.getRaza())
                .append("peso", mascota.getPeso())
                // Campos para busqueda de mascota ideal
                .append("tamano", mascota.getTamano())
                .append("nivelActividad", mascota.getNivelActividad())
                .append("peludo", mascota.isPeludo())
                .append("costoMantenimiento", mascota.getCostoMantenimiento())
                .append("descripcion", mascota.getDescripcion());
        return doc;
    }
}
