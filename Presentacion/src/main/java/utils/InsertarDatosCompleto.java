package utils;

import DTOS.MascotaDTO;
import DTOS.UsuarioDTO;
import DTOS.InfoPersonalDTO;
import negocio.subsistemas.mascotas.FachadaMascotas;
import negocio.subsistemas.mascotas.IMascotas;
import negocio.subsistemas.iniciosesion.FachadaInicioSesion;
import negocio.subsistemas.iniciosesion.IInicioSesion;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Script completo para insertar todos los datos de prueba usando DTOs
 * Combina mascotas, usuarios y citas en un solo archivo
 * Usa la capa de negocio para validar que todo funcione correctamente
 * 
 * @author angel
 */
public class InsertarDatosCompleto {

    public static void main(String[] args) {
        try {
            insertarMascotas();
            insertarUsuarioPrueba();
            insertarCitasDisponibles();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserta 6 mascotas de prueba usando DTOs y la fachada de mascotas
     * Esto valida que toda la lógica de negocio funcione correctamente
     */
    private static void insertarMascotas() {
        System.out.println("Insertando mascotas...");
        IMascotas fachadaMascotas = new FachadaMascotas();

        try {
            // Mascota 1: Max - Perro
            MascotaDTO mascota1 = new MascotaDTO();
            mascota1.setNombre("Max");
            mascota1.setEspecie("Perro");
            mascota1.setEdad(3);
            mascota1.setEstadoSalud("Excelente - Vacunas al día");
            mascota1.setPersonalidad("Juguetón y amigable con niños");
            mascota1.setUrlImagen("/images/mascotas/perro1.jpg");
            mascota1.setDisponible(true);
            mascota1.setEstado("Disponible");
            // Campos para búsqueda de mascota ideal
            mascota1.setTamano("mediano");
            mascota1.setNivelActividad("alto");
            mascota1.setPeludo(true);
            mascota1.setCostoMantenimiento("medio");
            mascota1.setDescripcion("Perro juguetón perfecto para familias activas");
            fachadaMascotas.registrarMascota(mascota1);

            // Mascota 2: Luna - Gato
            MascotaDTO mascota2 = new MascotaDTO();
            mascota2.setNombre("Luna");
            mascota2.setEspecie("Gato");
            mascota2.setEdad(2);
            mascota2.setEstadoSalud("Saludable - Esterilizada");
            mascota2.setPersonalidad("Tranquila e independiente");
            mascota2.setUrlImagen("/images/mascotas/gato1.jpg");
            mascota2.setDisponible(true);
            mascota2.setEstado("Disponible");
            // Campos para búsqueda de mascota ideal
            mascota2.setTamano("pequeño");
            mascota2.setNivelActividad("bajo");
            mascota2.setPeludo(true);
            mascota2.setCostoMantenimiento("bajo");
            mascota2.setDescripcion("Gata tranquila, ideal para apartamentos");
            fachadaMascotas.registrarMascota(mascota2);

            // Mascota 3: Rocky - Perro
            MascotaDTO mascota3 = new MascotaDTO();
            mascota3.setNombre("Rocky");
            mascota3.setEspecie("Perro");
            mascota3.setEdad(5);
            mascota3.setEstadoSalud("Buena - Requiere ejercicio diario");
            mascota3.setPersonalidad("Enérgico y leal");
            mascota3.setUrlImagen("/images/mascotas/perro2.jpg");
            mascota3.setDisponible(true);
            mascota3.setEstado("Disponible");
            // Campos para búsqueda de mascota ideal
            mascota3.setTamano("grande");
            mascota3.setNivelActividad("alto");
            mascota3.setPeludo(true);
            mascota3.setCostoMantenimiento("alto");
            mascota3.setDescripcion("Perro enérgico que necesita mucho ejercicio y espacio");
            fachadaMascotas.registrarMascota(mascota3);

            // Mascota 4: Michi - Gato
            MascotaDTO mascota4 = new MascotaDTO();
            mascota4.setNombre("Michi");
            mascota4.setEspecie("Gato");
            mascota4.setEdad(1);
            mascota4.setEstadoSalud("Excelente - Joven y saludable");
            mascota4.setPersonalidad("Curioso y juguetón");
            mascota4.setUrlImagen("/images/mascotas/gato2.jpg");
            mascota4.setDisponible(true);
            mascota4.setEstado("Disponible");
            // Campos para búsqueda de mascota ideal
            mascota4.setTamano("pequeño");
            mascota4.setNivelActividad("medio");
            mascota4.setPeludo(true);
            mascota4.setCostoMantenimiento("bajo");
            mascota4.setDescripcion("Gatito joven y curioso, fácil de cuidar");
            fachadaMascotas.registrarMascota(mascota4);

            // Mascota 5: Piolín - Pájaro
            MascotaDTO mascota5 = new MascotaDTO();
            mascota5.setNombre("Piolín");
            mascota5.setEspecie("Pájaro");
            mascota5.setEdad(1);
            mascota5.setEstadoSalud("Saludable");
            mascota5.setPersonalidad("Cantarín y sociable");
            mascota5.setUrlImagen("/images/mascotas/pajaro1.jpg");
            mascota5.setDisponible(true);
            mascota5.setEstado("Disponible");
            // Campos para búsqueda de mascota ideal
            mascota5.setTamano("pequeño");
            mascota5.setNivelActividad("medio");
            mascota5.setPeludo(false);
            mascota5.setCostoMantenimiento("bajo");
            mascota5.setDescripcion("Pájaro cantarín, perfecto para espacios pequeños");
            fachadaMascotas.registrarMascota(mascota5);

            // Mascota 6: Verde - Reptil
            MascotaDTO mascota6 = new MascotaDTO();
            mascota6.setNombre("Verde");
            mascota6.setEspecie("Reptil");
            mascota6.setEdad(2);
            mascota6.setEstadoSalud("Buena - Requiere terrario");
            mascota6.setPersonalidad("Tranquilo y de bajo mantenimiento");
            mascota6.setUrlImagen("/images/mascotas/reptil1.jpg");
            mascota6.setDisponible(true);
            mascota6.setEstado("Disponible");
            // Campos para búsqueda de mascota ideal
            mascota6.setTamano("mediano");
            mascota6.setNivelActividad("bajo");
            mascota6.setPeludo(false);
            mascota6.setCostoMantenimiento("medio");
            mascota6.setDescripcion("Reptil tranquilo de bajo mantenimiento");
            fachadaMascotas.registrarMascota(mascota6);

            System.out.println("6 mascotas insertadas correctamente\n");

        } catch (Exception e) {
            System.err.println("Error al insertar mascotas: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Inserta un usuario de prueba usando DTOs
     * Usuario: demo@ejemplo.com / 123456
     */
    private static void insertarUsuarioPrueba() {
        IInicioSesion fachadaInicioSesion = new FachadaInicioSesion();

        try {
            // Crear InfoPersonal
            InfoPersonalDTO infoPersonal = new InfoPersonalDTO();
            infoPersonal.setNombre("Usuario Demo");
            infoPersonal.setCurp("DEMO123456ABCD01");
            infoPersonal.setDireccion("De la campiña 3006");
            infoPersonal.setCorreo("demo@ejemplo.com");

            // Crear Usuario
            UsuarioDTO usuario = new UsuarioDTO();
            usuario.setInfoPersonal(infoPersonal);
            usuario.setContrasena("123456"); // En producción usar hash

            // Registrar usuario
            fachadaInicioSesion.registrarUsuario(usuario);

        } catch (Exception e) {
            // Si ya existe, no es error
            if (e.getMessage().contains("ya existe") || e.getMessage().contains("duplicate")) {
            } else {
                System.err.println("Error al insertar usuario: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Inserta citas disponibles para los próximos 15 días hábiles
     * Genera 4-6 citas por día en horarios aleatorios
     * Usa la capa BO para validar que el sistema funcione
     */
    private static void insertarCitasDisponibles() {
        System.out.println("Insertando citas disponibles...");

        try {
            // Usar directamente la conexión MongoDB para insertar
            com.mongodb.client.MongoDatabase database = conexion.ConexionMongoDB.getInstancia().getDatabase();
            com.mongodb.client.MongoCollection<org.bson.Document> coleccion = database
                    .getCollection("citasDisponibles");

            // Limpiar citas existentes
            coleccion.deleteMany(new org.bson.Document());

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, 2);

            String[] horas = { "09:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00", "17:00" };
            Random rand = new Random();

            int citasInsertadas = 0;

            for (int i = 0; i < 15; i++) {
                int diaSemana = cal.get(Calendar.DAY_OF_WEEK);

                // Solo días hábiles
                if (diaSemana != Calendar.SATURDAY && diaSemana != Calendar.SUNDAY) {
                    Date fecha = cal.getTime();
                    int citasPorDia = 4 + rand.nextInt(3); // 4-6 citas por día

                    for (int j = 0; j < citasPorDia; j++) {
                        String hora = horas[rand.nextInt(horas.length)];

                        // Crear Document directamente
                        org.bson.Document citaDoc = new org.bson.Document()
                                .append("fecha", fecha)
                                .append("hora", hora)
                                .append("disponible", true)
                                .append("idUsuario", null);

                        // Insertar en MongoDB
                        coleccion.insertOne(citaDoc);
                        citasInsertadas++;
                    }
                }
                cal.add(Calendar.DAY_OF_YEAR, 1);
            }

            System.out.println(citasInsertadas + " citas insertadas correctamente\n");

        } catch (Exception e) {
            System.err.println("Error al insertar citas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
