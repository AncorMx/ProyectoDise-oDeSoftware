package cuaceptarsolicitudes.persistencia.dao;

import cuaceptarsolicitudes.persistencia.entities.Usuario;
import cuaceptarsolicitudes.persistencia.entities.InfoPersonal;
import cuaceptarsolicitudes.persistencia.entities.InfoVivienda;
import cuaceptarsolicitudes.persistencia.entities.RazonesAntecedentes;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * DAO para operaciones CRUD de Usuario.
 */
public class UsuarioDAO {

    private final MongoCollection<Document> collection;

    public UsuarioDAO(MongoDatabase database) {
        this.collection = database.getCollection("usuarios");
    }

    public Usuario buscarPorId(ObjectId id) {
        Document doc = collection.find(new Document("_id", id)).first();
        return doc != null ? documentToUsuario(doc) : null;
    }

    public Usuario buscarPorCorreo(String correo) {
        Document doc = collection.find(new Document("infoPersonal.correo", correo)).first();
        return doc != null ? documentToUsuario(doc) : null;
    }

    public ObjectId insertar(Usuario usuario) {
        Document doc = usuarioToDocument(usuario);
        collection.insertOne(doc);
        return doc.getObjectId("_id");
    }

    public void actualizar(Usuario usuario) {
        Document doc = usuarioToDocument(usuario);
        collection.replaceOne(new Document("_id", usuario.getId()), doc);
    }

    private Document usuarioToDocument(Usuario usuario) {
        Document doc = new Document();
        if (usuario.getId() != null) {
            doc.append("_id", usuario.getId());
        }
        if (usuario.getInfoPersonal() != null) {
            Document infoDoc = new Document()
                    .append("nombre", usuario.getInfoPersonal().getNombre())
                    .append("correo", usuario.getInfoPersonal().getCorreo())
                    .append("telefono", usuario.getInfoPersonal().getTelefono())
                    .append("direccion", usuario.getInfoPersonal().getDireccion())
                    .append("curp", usuario.getInfoPersonal().getCurp());
            doc.append("infoPersonal", infoDoc);
        }
        if (usuario.getInfoVivienda() != null) {
            Document vivDoc = new Document()
                    .append("tipoVivienda", usuario.getInfoVivienda().getTipoVivienda())
                    .append("tienePatio", usuario.getInfoVivienda().isTienePatio())
                    .append("tieneOtrasMascotas", usuario.getInfoVivienda().isTieneOtrasMascotas())
                    .append("descripcionOtrasMascotas", usuario.getInfoVivienda().getDescripcionOtrasMascotas());
            doc.append("infoVivienda", vivDoc);
        }
        return doc;
    }

    private Usuario documentToUsuario(Document doc) {
        Usuario usuario = new Usuario();
        usuario.setId(doc.getObjectId("_id"));

        Document infoDoc = doc.get("infoPersonal", Document.class);
        if (infoDoc != null) {
            InfoPersonal info = new InfoPersonal();
            info.setNombre(infoDoc.getString("nombre"));
            info.setCorreo(infoDoc.getString("correo"));
            info.setTelefono(infoDoc.getString("telefono"));
            info.setDireccion(infoDoc.getString("direccion"));
            info.setCurp(infoDoc.getString("curp"));
            usuario.setInfoPersonal(info);
        }

        Document vivDoc = doc.get("infoVivienda", Document.class);
        if (vivDoc != null) {
            InfoVivienda viv = new InfoVivienda();
            viv.setTipoVivienda(vivDoc.getString("tipoVivienda"));
            viv.setTienePatio(vivDoc.getBoolean("tienePatio", false));
            viv.setTieneOtrasMascotas(vivDoc.getBoolean("tieneOtrasMascotas", false));
            viv.setDescripcionOtrasMascotas(vivDoc.getString("descripcionOtrasMascotas"));
            usuario.setInfoVivienda(viv);
        }

        return usuario;
    }
}
