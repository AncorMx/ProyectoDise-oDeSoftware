package cubuscarmascotaideal.negocio.subsistema;

import cubuscarmascotaideal.negocio.dtos.EncuestaDTO;
import cubuscarmascotaideal.negocio.dtos.MascotaResultadoDTO;
import cubuscarmascotaideal.negocio.adaptadores.AdaptadorMascotaResultado;
import cubuscarmascotaideal.negocio.BO.EncuestaBO;
import cubuscarmascotaideal.negocio.BO.IEncuestaBO;
import conexion.ConexionMongoDB;
import cubuscarmascotaideal.persistencia.daos.ResultadoMascotaIdealDAO;
import cubuscarmascotaideal.persistencia.entities.ResultadoMascotaIdeal;
import daos.MascotaDAO;
import entities.Mascota;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Clase que busca mascotas compatibles segun las preferencias del usuario
 */
public class BuscarMascota implements IBuscarMascota {

    private MascotaDAO mascotaDAO;
    private IEncuestaBO encuestaBO;
    private ResultadoMascotaIdealDAO resultadoDAO;

    public BuscarMascota() {
        this.mascotaDAO = new MascotaDAO(ConexionMongoDB.getInstancia().getDatabase());
        this.encuestaBO = new EncuestaBO();
        this.resultadoDAO = new ResultadoMascotaIdealDAO(ConexionMongoDB.getInstancia().getDatabase());
    }

    public BuscarMascota(MascotaDAO mascotaDAO, IEncuestaBO encuestaBO) {
        this.mascotaDAO = mascotaDAO;
        this.encuestaBO = encuestaBO;
        this.resultadoDAO = new ResultadoMascotaIdealDAO(ConexionMongoDB.getInstancia().getDatabase());
    }

    @Override
    public List<MascotaResultadoDTO> buscarMascotasCompatibles(EncuestaDTO encuesta) {
        // Valido que la encuesta este bien llenada
        if (!encuestaBO.validarEncuesta(encuesta)) {
            System.err.println("La encuesta no es valida");
            return new ArrayList<>();
        }

        // Traigo todas las mascotas que estan disponibles
        List<Mascota> mascotasDisponibles = mascotaDAO.buscarPorDisponibilidad(true);

        // Calculo que tan compatible es cada mascota
        List<MascotaResultadoDTO> resultados = new ArrayList<>();
        for (Mascota mascota : mascotasDisponibles) {
            double porcentaje = calcularCompatibilidad(encuesta, mascota);

            // Solo incluyo las mascotas con al menos 50% de compatibilidad
            if (porcentaje >= 50.0) {
                MascotaResultadoDTO resultado = AdaptadorMascotaResultado.entidadADTO(mascota, porcentaje);
                resultados.add(resultado);
            }
        }

        // Ordeno de mayor a menor compatibilidad
        ordenarPorCompatibilidad(resultados);

        return resultados;
    }

    @Override
    public List<MascotaResultadoDTO> obtenerTodasConCompatibilidad(EncuestaDTO encuesta) {
        // Valido que la encuesta este bien
        if (!encuestaBO.validarEncuesta(encuesta)) {
            return new ArrayList<>();
        }

        // Traigo todas las mascotas disponibles
        List<Mascota> todasMascotas = mascotaDAO.buscarPorDisponibilidad(true);

        // Calculo compatibilidad para todas (sin filtrar por porcentaje)
        List<MascotaResultadoDTO> resultados = new ArrayList<>();
        for (Mascota mascota : todasMascotas) {
            double porcentaje = calcularCompatibilidad(encuesta, mascota);
            MascotaResultadoDTO resultado = AdaptadorMascotaResultado.entidadADTO(mascota, porcentaje);
            resultados.add(resultado);
        }

        // Ordeno de mayor a menor compatibilidad
        ordenarPorCompatibilidad(resultados);

        return resultados;
    }

    /**
     * Guarda los resultados de busqueda de un usuario en la base de datos
     */
    public void guardarResultados(ObjectId idUsuario, List<MascotaResultadoDTO> resultados) {
        // Verifico que tenga datos validos
        if (idUsuario == null || resultados == null || resultados.isEmpty()) {
            return;
        }

        // Primero borro los resultados viejos del usuario
        resultadoDAO.eliminarPorUsuario(idUsuario);

        // Convierto los DTOs a entidades para guardar
        List<ResultadoMascotaIdeal> entidades = new ArrayList<>();
        for (MascotaResultadoDTO dto : resultados) {
            if (dto.getId() != null && !dto.getId().isEmpty()) {
                ResultadoMascotaIdeal entidad = new ResultadoMascotaIdeal(
                        idUsuario,
                        new ObjectId(dto.getId()),
                        dto.getPorcentajeCompatibilidad());
                entidades.add(entidad);
            }
        }

        // Guardo todos los resultados nuevos
        resultadoDAO.guardarTodos(entidades);
    }

    /**
     * Obtiene los resultados que el usuario guardo anteriormente
     */
    public List<MascotaResultadoDTO> obtenerResultadosGuardados(ObjectId idUsuario) {
        // Si no hay usuario, regreso vacio
        if (idUsuario == null) {
            return new ArrayList<>();
        }

        // Busco los resultados que guarde antes
        List<ResultadoMascotaIdeal> guardados = resultadoDAO.buscarPorUsuario(idUsuario);
        List<MascotaResultadoDTO> resultados = new ArrayList<>();

        // Por cada resultado, traigo la info completa de la mascota
        for (ResultadoMascotaIdeal guardado : guardados) {
            Mascota mascota = mascotaDAO.buscarPorId(guardado.getIdMascota());

            // Solo lo agrego si la mascota todavia existe y esta disponible
            if (mascota != null && mascota.isDisponible()) {
                MascotaResultadoDTO dto = AdaptadorMascotaResultado.entidadADTO(
                        mascota,
                        guardado.getPorcentajeCompatibilidad());
                resultados.add(dto);
            }
        }

        // Ordeno por compatibilidad
        ordenarPorCompatibilidad(resultados);

        return resultados;
    }

    /**
     * Calcula que tan compatible es una mascota con lo que el usuario busca.
     * Uso 6 criterios, cada uno vale aproximadamente 16.67 puntos.
     */
    private double calcularCompatibilidad(EncuestaDTO encuesta, Mascota mascota) {
        int puntos = 0;
        int totalCriterios = 6;

        // Criterio 1: Tamano de la mascota
        if (sonIguales(encuesta.getTamano(), mascota.getTamano())) {
            puntos++;
        }

        // Criterio 2: Nivel de actividad
        if (sonIguales(encuesta.getNivelActividad(), mascota.getNivelActividad())) {
            puntos++;
        }

        // Criterio 3: Si quiere mascota peluda o no
        if (encuesta.isPeludo() == mascota.isPeludo()) {
            puntos++;
        }

        // Criterio 4: Costo de mantenimiento
        if (sonIguales(encuesta.getCostoMantenimiento(), mascota.getCostoMantenimiento())) {
            puntos++;
        }

        // Criterio 5: Alergias - si tiene alergias, mejor que no sea peluda
        if (encuesta.isTieneAlergias()) {
            if (!mascota.isPeludo()) {
                puntos++;
            }
        } else {
            // Sin alergias, cualquier mascota esta bien
            puntos++;
        }

        // Criterio 6: Tipo de vivienda - departamento vs casa
        if (encuesta.isViveEnDepartamento()) {
            // En departamento es mejor mascota pequena o mediana
            String tamano = mascota.getTamano();
            if (tamano != null && (tamano.equalsIgnoreCase("pequeño") || tamano.equalsIgnoreCase("mediano"))) {
                puntos++;
            }
        } else {
            // En casa cualquier tamano esta bien
            puntos++;
        }

        // Calculo el porcentaje final
        return (puntos * 100.0) / totalCriterios;
    }

    /**
     * Verifica si dos textos son iguales (sin importar mayusculas/minusculas)
     */
    private boolean sonIguales(String texto1, String texto2) {
        if (texto1 == null || texto2 == null) {
            return false;
        }
        return texto1.equalsIgnoreCase(texto2);
    }

    /**
     * Ordena la lista de mayor a menor porcentaje de compatibilidad
     */
    private void ordenarPorCompatibilidad(List<MascotaResultadoDTO> lista) {
        Collections.sort(lista, new Comparator<MascotaResultadoDTO>() {
            @Override
            public int compare(MascotaResultadoDTO m1, MascotaResultadoDTO m2) {
                // Ordeno de mayor a menor, por eso resto al revés
                return Double.compare(m2.getPorcentajeCompatibilidad(), m1.getPorcentajeCompatibilidad());
            }
        });
    }
}
