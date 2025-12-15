package negocio.subsistemas.iniciosesion;

import DTOS.UsuarioDTO;
import DTOS.InfoPersonalDTO;
import ObjetoNegocio.IUsuarioBO;
import ObjetoNegocio.UsuarioBO;
import infraestructura.dto.CorreoDTO;
import infraestructura.sistemacorreo.FachadaCorreo;

/**
 * Fachada para el subsistema de inicio de sesión
 * 
 * @author System
 */
public class FachadaInicioSesion implements IInicioSesion {

    private IUsuarioBO usuarioBO;
    private FachadaCorreo fachadaCorreo;

    public FachadaInicioSesion() {
        this.usuarioBO = new UsuarioBO();
        this.fachadaCorreo = new FachadaCorreo();
    }

    @Override
    public UsuarioDTO iniciarSesion(String correo, String password) throws Exception {
        if (correo == null || correo.trim().isEmpty()) {
            throw new Exception("El correo es requerido");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new Exception("La contraseña es requerida");
        }

        try {
            // Delegar al BO para buscar y validar usuario
            return usuarioBO.buscarYValidarUsuario(correo, password);
        } catch (RuntimeException e) {
            // Convertir RuntimeException a Exception con el mensaje
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void registrarUsuario(UsuarioDTO usuarioDTO) throws Exception {
        if (usuarioDTO == null) {
            throw new Exception("El usuario no puede ser nulo");
        }

        if (usuarioDTO.getInfoPersonal() == null ||
                usuarioDTO.getInfoPersonal().getCorreo() == null ||
                usuarioDTO.getInfoPersonal().getCorreo().trim().isEmpty()) {
            throw new Exception("El correo es requerido");
        }

        if (usuarioDTO.getContrasena() == null || usuarioDTO.getContrasena().trim().isEmpty()) {
            throw new Exception("La contraseña es requerida");
        }

        try {
            // Delegar al BO para el registro
            usuarioBO.registraUsuario(usuarioDTO);
            System.out.println("Usuario registrado exitosamente");

            // Enviar correo de bienvenida
            try {
                CorreoDTO correoBienvenida = generarCorreoBienvenida(usuarioDTO);
                fachadaCorreo.enviarCorreo(correoBienvenida);
                System.out.println("Correo de bienvenida enviado a: " + usuarioDTO.getInfoPersonal().getCorreo());
            } catch (Exception emailException) {
                // Si falla el envío del correo, lanzar excepción con prefijo especial
                // para que la UI sepa que el usuario SÍ se registró pero el correo falló.
                throw new Exception("REGISTRO_EXITOSO_CORREO_FALLIDO: " + emailException.getMessage());
            }
        } catch (RuntimeException e) {
            // Convertir RuntimeException a Exception con el mensaje
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void actualizarUsuario(UsuarioDTO usuarioDTO) throws Exception {
        if (usuarioDTO == null) {
            throw new Exception("El usuario no puede ser nulo");
        }

        try {
            usuarioBO.actualizarUsuario(usuarioDTO);
            System.out.println("Usuario actualizado exitosamente");
        } catch (RuntimeException e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Genera un correo de bienvenida para un nuevo usuario.
     * 
     * @param usuarioDTO Usuario nuevo registrado
     * @return CorreoDTO con el mensaje de bienvenida
     */
    private CorreoDTO generarCorreoBienvenida(UsuarioDTO usuarioDTO) {
        String nombre = usuarioDTO.getInfoPersonal().getNombre();
        String correo = usuarioDTO.getInfoPersonal().getCorreo();

        String asunto = "¡Bienvenido a La Vida Es Bella!";

        String mensaje = "¡Hola " + nombre + "!\n\n"
                + "Nos alegra mucho que te hayas unido a nuestra comunidad de La Vida Es Bella.\n\n"
                + "Ahora puedes:\n"
                + "- Explorar mascotas disponibles para adopción\n"
                + "- Enviar solicitudes de adopción\n"
                + "- Contactarnos para cualquier consulta\n\n"
                + "Gracias por darle una segunda oportunidad a nuestros amigos peludos.\n\n"
                + "¡Te esperamos!\n\n"
                + "El equipo de La Vida Es Bella";

        return new CorreoDTO(correo, asunto, mensaje);
    }
}
