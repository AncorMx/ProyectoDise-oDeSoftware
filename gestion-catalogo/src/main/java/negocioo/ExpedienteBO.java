package negocioo;

import gestion.catalogo.dtos.ExpedienteDTO;
import conexion.ConexionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static com.mongodb.client.model.Filters.*;

/**
 * Implementación de IExpedienteBO
 * Gestiona expedientes médicos con MongoDB
 * Composición fuerte: el expediente pertenece a la mascota
 * 
 * @author angel
 */
public class ExpedienteBO implements IExpedienteBO {

    private final MongoCollection<Document> coleccion;
    private static final String COLLECTION_NAME = "expedientes_medicos";

    public ExpedienteBO() {
        MongoDatabase database = ConexionMongoDB.getInstancia().getDatabase();
        this.coleccion = database.getCollection(COLLECTION_NAME);
    }

    @Override
    public ExpedienteDTO crearExpediente(String idMascota) {
        if (idMascota == null || idMascota.isEmpty()) {
            throw new IllegalArgumentException("ID de mascota no puede ser nulo");
        }

        ExpedienteDTO expediente = new ExpedienteDTO();
        expediente.setCodigo(generarCodigoExpediente());
        expediente.setIdMascota(idMascota);
        // Valores por defecto
        expediente.setCondicion("Saludable");
        expediente.setNivelEnergia("Media");
        expediente.setVacunaRabia(false);
        expediente.setVacunaDesparasitacionExterna(false);
        expediente.setVacunaBordetella(false);
        expediente.setVacunaDesparasitacionInterna(false);
        expediente.setVacunaMultiple(false);
        expediente.setEsterilizado(false);
        expediente.setAlergias("Ninguna");
        expediente.setCondicionesEspeciales("Ninguna");

        Document doc = convertirADocument(expediente);
        coleccion.insertOne(doc);

        System.out.println("Expediente creado: " + expediente.getCodigo());
        return expediente;
    }

    @Override
    public ExpedienteDTO crearExpedienteConDatos(String idMascota,
            gestion.catalogo.dtos.ExpedienteMedicoTemporal datos) {
        System.out.println(">>> ExpedienteBO.crearExpedienteConDatos invocada");
        if (datos == null) {
            System.out.println("DATOS NULOS, creando por defecto");
            return crearExpediente(idMascota);
        }
        System.out.println("Datos recibidos: " + datos.getCondicion());
        if (idMascota == null || idMascota.isEmpty()) {
            throw new IllegalArgumentException("ID de mascota no puede ser nulo");
        }

        ExpedienteDTO expediente = new ExpedienteDTO();
        expediente.setCodigo(generarCodigoExpediente());
        expediente.setIdMascota(idMascota);

        // Mapear datos temporales a DTO persistente
        if (datos != null) {
            expediente.setCondicion(datos.getCondicion());
            expediente.setNivelEnergia(datos.getNivelEnergia());
            expediente.setVacunaRabia(datos.isVacunaRabia());
            expediente.setVacunaDesparasitacionExterna(datos.isVacunaDesparasitacionExterna());
            expediente.setVacunaBordetella(datos.isVacunaBordetella());
            expediente.setVacunaDesparasitacionInterna(datos.isVacunaDesparasitacionInterna());
            expediente.setVacunaMultiple(datos.isVacunaMultiple());
            expediente.setEsterilizado(datos.isEsterilizado());
            expediente.setAlergias(datos.getAlergias());
            expediente.setCondicionesEspeciales(datos.getCondicionesEspeciales());
        }

        Document doc = convertirADocument(expediente);
        coleccion.insertOne(doc);

        System.out.println("Expediente creado con datos: " + expediente.getCodigo());
        return expediente;
    }

    @Override
    public ExpedienteDTO obtenerExpediente(String idMascota) {
        try {
            Document doc = coleccion.find(eq("idMascota", idMascota)).first();
            return doc != null ? convertirADTO(doc) : null;
        } catch (Exception e) {
            System.err.println("Error al obtener expediente: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean actualizarExpediente(ExpedienteDTO expediente) {
        try {
            Document doc = convertirADocument(expediente);
            coleccion.replaceOne(eq("idMascota", expediente.getIdMascota()), doc);
            return true;
        } catch (Exception e) {
            System.err.println("Error al actualizar expediente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean agregarEntradaHistorial(String idMascota, String entrada) {
        // Método simplificado o deprecated ya que ahora manejamos estructura fija
        // Podríamos mapear esto a observaciones o condiciones especiales si es necesario
        ExpedienteDTO expediente = obtenerExpediente(idMascota);
        if (expediente == null)
            return false;

        String actual = expediente.getCondicionesEspeciales();
        expediente.setCondicionesEspeciales(actual + " | " + entrada);
        return actualizarExpediente(expediente);
    }

    @Override
    public boolean eliminarExpediente(String idMascota) {
        try {
            coleccion.deleteOne(eq("idMascota", idMascota));
            System.out.println("✓ Expediente eliminado (composición)");
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar expediente: " + e.getMessage());
            return false;
        }
    }

    private String generarCodigoExpediente() {
        return "EXP-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
    }

    private Document convertirADocument(ExpedienteDTO dto) {
        Document doc = new Document();
        doc.append("codigo", dto.getCodigo());
        doc.append("idMascota", dto.getIdMascota());
        doc.append("condicion", dto.getCondicion());
        doc.append("nivelEnergia", dto.getNivelEnergia());
        doc.append("vacunaRabia", dto.isVacunaRabia());
        doc.append("vacunaDesparasitacionExterna", dto.isVacunaDesparasitacionExterna());
        doc.append("vacunaBordetella", dto.isVacunaBordetella());
        doc.append("vacunaDesparasitacionInterna", dto.isVacunaDesparasitacionInterna());
        doc.append("vacunaMultiple", dto.isVacunaMultiple());
        doc.append("esterilizado", dto.isEsterilizado());
        doc.append("alergias", dto.getAlergias());
        doc.append("condicionesEspeciales", dto.getCondicionesEspeciales());
        doc.append("fechaCreacion", dto.getFechaCreacion().toString());
        doc.append("fechaUltimaActualizacion", dto.getFechaUltimaActualizacion().toString());
        return doc;
    }

    private ExpedienteDTO convertirADTO(Document doc) {
        ExpedienteDTO dto = new ExpedienteDTO();
        dto.setCodigo(doc.getString("codigo"));
        dto.setIdMascota(doc.getString("idMascota"));

        dto.setCondicion(doc.getString("condicion"));
        dto.setNivelEnergia(doc.getString("nivelEnergia"));
        dto.setVacunaRabia(doc.getBoolean("vacunaRabia", false));
        dto.setVacunaDesparasitacionExterna(doc.getBoolean("vacunaDesparasitacionExterna", false));
        dto.setVacunaBordetella(doc.getBoolean("vacunaBordetella", false));
        dto.setVacunaDesparasitacionInterna(doc.getBoolean("vacunaDesparasitacionInterna", false));
        dto.setVacunaMultiple(doc.getBoolean("vacunaMultiple", false));
        dto.setEsterilizado(doc.getBoolean("esterilizado", false));
        dto.setAlergias(doc.getString("alergias"));
        dto.setCondicionesEspeciales(doc.getString("condicionesEspeciales"));

        try {
            dto.setFechaCreacion(LocalDateTime.parse(doc.getString("fechaCreacion")));
            dto.setFechaUltimaActualizacion(LocalDateTime.parse(doc.getString("fechaUltimaActualizacion")));
        } catch (Exception e) {
            dto.setFechaCreacion(LocalDateTime.now());
            dto.setFechaUltimaActualizacion(LocalDateTime.now());
        }

        return dto;
    }
}
