
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entities.ExpedienteMedico;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * DAO para gestionar expedientes médicos en MongoDB
 * 
 * @author angel
 */
public class ExpedienteMedicoDAO {

    private final MongoCollection<Document> collection;

    /**
     * Constructor del DAO de ExpedienteMedico
     * 
     * @param database Base de datos MongoDB a utilizar
     */
    public ExpedienteMedicoDAO(MongoDatabase database) {
        this.collection = database.getCollection("expedientes_medicos");
    }

    /**
     * Guarda un nuevo expediente médico en la base de datos
     * 
     * @param expediente Expediente médico a guardar
     * @return ObjectId generado para el expediente guardado
     */
    public ObjectId guardar(ExpedienteMedico expediente) {
        Document doc = expedienteToDocument(expediente);
        collection.insertOne(doc);
        return doc.getObjectId("_id");
    }

    /**
     * Busca un expediente por el ID de la mascota
     * 
     * @param mascotaId ObjectId de la mascota
     * @return Expediente médico encontrado o null si no existe
     */
    /**
     * Busca un expediente por el ID de la mascota
     * Soporta tanto esquema antiguo (mascotaId: ObjectId) como nuevo (idMascota:
     * String)
     * 
     * @param mascotaId ObjectId de la mascota
     * @return Expediente médico encontrado o null si no existe
     */
    public ExpedienteMedico buscarPorMascotaId(ObjectId mascotaId) {
        System.out.println("DEBUG DAO: Buscando expediente para MascotaID (ObjectId): " + mascotaId);
        System.out.println("DEBUG DAO: Buscando expediente para idMascota (String): " + mascotaId.toHexString());

        // Buscar por ambos campos posibles
        Document doc = collection.find(Filters.or(
                Filters.eq("mascotaId", mascotaId),
                Filters.eq("idMascota", mascotaId.toHexString()))).first();

        if (doc == null) {
            System.out.println("DEBUG DAO: No se encontró ningún expediente.");
            return null;
        }
        System.out.println("DEBUG DAO: Expediente encontrado! ID: " + doc.get("_id"));
        return documentToExpediente(doc);
    }

    /**
     * Actualiza un expediente médico existente
     * 
     * @param expediente Expediente con datos actualizados
     */
    public void actualizar(ExpedienteMedico expediente) {
        if (expediente.getId() != null) {
            Document doc = expedienteToDocument(expediente);
            collection.replaceOne(Filters.eq("_id", expediente.getId()), doc);
        }
    }

    /**
     * Convierte un Document de MongoDB a una entidad ExpedienteMedico
     * 
     * @param doc Document de MongoDB
     * @return Entidad ExpedienteMedico
     */
    private ExpedienteMedico documentToExpediente(Document doc) {
        ExpedienteMedico expediente = new ExpedienteMedico();
        expediente.setId(doc.getObjectId("_id"));

        // Manejo híbrido de ID de mascota
        if (doc.containsKey("mascotaId")) {
            expediente.setMascotaId(doc.getObjectId("mascotaId"));
        } else if (doc.containsKey("idMascota")) {
            try {
                expediente.setMascotaId(new ObjectId(doc.getString("idMascota")));
            } catch (Exception e) {
                // Si falla conversión, loguear pero continuar (id será null)
                System.err.println("Error convirtiendo idMascota string a ObjectId: " + doc.getString("idMascota"));
            }
        }

        expediente.setCondicion(doc.getString("condicion"));
        expediente.setNivelEnergia(doc.getString("nivelEnergia"));
        expediente.setVacunaRabia(doc.getBoolean("vacunaRabia", false));
        expediente.setVacunaDesparasitacionExterna(doc.getBoolean("vacunaDesparasitacionExterna", false));
        expediente.setVacunaBordetella(doc.getBoolean("vacunaBordetella", false));
        expediente.setVacunaDesparasitacionInterna(doc.getBoolean("vacunaDesparasitacionInterna", false));
        expediente.setVacunaMultiple(doc.getBoolean("vacunaMultiple", false));
        expediente.setEsterilizado(doc.getBoolean("esterilizado", false));
        expediente.setAlergias(doc.getString("alergias"));
        expediente.setCondicionesEspeciales(doc.getString("condicionesEspeciales"));
        return expediente;
    }

    /**
     * Convierte una entidad ExpedienteMedico a un Document de MongoDB
     * 
     * @param expediente Entidad a convertir
     * @return Document para MongoDB
     */
    private Document expedienteToDocument(ExpedienteMedico expediente) {
        Document doc = new Document();
        if (expediente.getId() != null) {
            doc.append("_id", expediente.getId());
        }
        // Guardar ambos estilos para máxima compatibilidad
        doc.append("mascotaId", expediente.getMascotaId())
                .append("idMascota", expediente.getMascotaId() != null ? expediente.getMascotaId().toString() : null)
                .append("condicion", expediente.getCondicion())
                .append("nivelEnergia", expediente.getNivelEnergia())
                .append("vacunaRabia", expediente.isVacunaRabia())
                .append("vacunaDesparasitacionExterna", expediente.isVacunaDesparasitacionExterna())
                .append("vacunaBordetella", expediente.isVacunaBordetella())
                .append("vacunaDesparasitacionInterna", expediente.isVacunaDesparasitacionInterna())
                .append("vacunaMultiple", expediente.isVacunaMultiple())
                .append("esterilizado", expediente.isEsterilizado())
                .append("alergias", expediente.getAlergias())
                .append("condicionesEspeciales", expediente.getCondicionesEspeciales());
        return doc;
    }
}