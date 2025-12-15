/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entities.Usuario;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * DAO para manejar usuarios en MongoDB
 */
public class UsuarioDAO {

    private final MongoCollection<Document> collection;

    public UsuarioDAO(MongoDatabase database) {
        this.collection = database.getCollection("usuarios");
    }

    /**
     * Busca un usuario por correo electronico
     */
    public Usuario buscarPorCorreo(String correo) {
        Document doc = collection.find(Filters.eq("infoPersonal.correo", correo)).first();
        if (doc == null) {
            return null;
        }
        return convertirDocumentoAUsuario(doc);
    }

    /**
     * Busca un usuario por su ID
     */
    public Usuario buscarPorId(ObjectId id) {
        Document doc = collection.find(Filters.eq("_id", id)).first();
        if (doc == null) {
            return null;
        }
        return convertirDocumentoAUsuario(doc);
    }

    /**
     * Guarda un nuevo usuario
     */
    public ObjectId guardar(Usuario usuario) {
        Document doc = convertirUsuarioADocumento(usuario);
        collection.insertOne(doc);
        return doc.getObjectId("_id");
    }

    /**
     * Actualiza un usuario existente
     */
    public void actualizar(Usuario usuario) {
        if (usuario.getId() == null) {
            throw new IllegalArgumentException("El usuario debe tener un ID para ser actualizado");
        }

        Document doc = convertirUsuarioADocumento(usuario);
        doc.remove("_id");

        collection.updateOne(Filters.eq("_id", usuario.getId()), new Document("$set", doc));
    }

    /**
     * Verifica si existe un usuario con el correo dado
     */
    public boolean existeCorreo(String correo) {
        return collection.countDocuments(Filters.eq("infoPersonal.correo", correo)) > 0;
    }

    /**
     * Convierte un documento de MongoDB a entidad Usuario
     */
    private Usuario convertirDocumentoAUsuario(Document doc) {
        Usuario usuario = new Usuario();
        usuario.setId(doc.getObjectId("_id"));
        usuario.setContrasena(doc.getString("contrasena"));

        Document infoPersonalDoc = doc.get("infoPersonal", Document.class);
        if (infoPersonalDoc != null) {
            entities.InfoPersonal infoPersonal = new entities.InfoPersonal();
            infoPersonal.setNombre(infoPersonalDoc.getString("nombre"));
            infoPersonal.setCorreo(infoPersonalDoc.getString("correo"));
            infoPersonal.setCurp(infoPersonalDoc.getString("curp"));
            infoPersonal.setDireccion(infoPersonalDoc.getString("direccion"));
            usuario.setInfoPersonal(infoPersonal);
            usuario.setInfoPersonal(infoPersonal);
        }

        Document infoViviendaDoc = doc.get("infoVivienda", Document.class);
        if (infoViviendaDoc != null) {
            entities.InfoVivienda infoVivienda = new entities.InfoVivienda();
            infoVivienda.setTipoVivienda(infoViviendaDoc.getString("tipoVivienda"));
            infoVivienda.setCondicionesHogar(infoViviendaDoc.getString("condicionesHogar"));
            infoVivienda.setTieneOtrasMascotas(infoViviendaDoc.getBoolean("tieneOtrasMascotas", false));
            infoVivienda.setTieneNinos(infoViviendaDoc.getBoolean("tieneNinos", false));
            infoVivienda.setTiempoDisponibilidad(infoViviendaDoc.getString("tiempoDisponibilidad"));
            infoVivienda.setUrlImagenVivienda(infoViviendaDoc.getString("urlImagenVivienda"));
            usuario.setInfoVivienda(infoVivienda);
        }

        return usuario;
    }

    /**
     * Convierte una entidad Usuario a documento de MongoDB
     */
    private Document convertirUsuarioADocumento(Usuario usuario) {
        Document doc = new Document();

        if (usuario.getId() != null) {
            doc.append("_id", usuario.getId());
        }

        doc.append("contrasena", usuario.getContrasena());

        if (usuario.getInfoPersonal() != null) {
            Document infoPersonalDoc = new Document()
                    .append("nombre", usuario.getInfoPersonal().getNombre())
                    .append("correo", usuario.getInfoPersonal().getCorreo())
                    .append("curp", usuario.getInfoPersonal().getCurp())
                    .append("direccion", usuario.getInfoPersonal().getDireccion());
            doc.append("infoPersonal", infoPersonalDoc);
        }

        if (usuario.getInfoVivienda() != null) {
            Document infoViviendaDoc = new Document()
                    .append("tipoVivienda", usuario.getInfoVivienda().getTipoVivienda())
                    .append("condicionesHogar", usuario.getInfoVivienda().getCondicionesHogar())
                    .append("tieneOtrasMascotas", usuario.getInfoVivienda().isTieneOtrasMascotas())
                    .append("tieneNinos", usuario.getInfoVivienda().isTieneNinos())
                    .append("tiempoDisponibilidad", usuario.getInfoVivienda().getTiempoDisponibilidad())
                    .append("urlImagenVivienda", usuario.getInfoVivienda().getUrlImagenVivienda());
            doc.append("infoVivienda", infoViviendaDoc);
        }

        return doc;
    }
}
