package ObjetoNegocio;

import DTOS.SolicitudAdopcionDTO;
import DTOS.UsuarioDTO;
import DTOS.InfoPersonalDTO;
import daos.UsuarioDAO;
import conexion.ConexionMongoDB;
import entities.Usuario;
import org.bson.types.ObjectId;

/**
 * Maneja la logica de negocio de los usuarios
 */
public class UsuarioBO implements IUsuarioBO {

    private UsuarioDAO usuarioDAO;

    public UsuarioBO() {
        this.usuarioDAO = new UsuarioDAO(ConexionMongoDB.getInstancia().getDatabase());
    }

    @Override
    public void registraUsuario(UsuarioDTO usuarioDTO) {
        // Valido los datos antes de guardar
        if (usuarioDTO != null && usuarioDTO.getInfoPersonal() != null &&
                usuarioDTO.getInfoPersonal().getCorreo() != null) {

            // Verifico si el correo ya existe
            if (usuarioDAO.existeCorreo(usuarioDTO.getInfoPersonal().getCorreo())) {
                throw new RuntimeException("El correo ya esta registrado");
            }

            // Convierto DTO a entidad
            Usuario usuario = convertirAEntidad(usuarioDTO);

            // Guardo en base de datos
            ObjectId id = usuarioDAO.guardar(usuario);
            System.out.println("Usuario guardado con ID: " + id);

            // Actualizo el DTO con el ID generado
            usuarioDTO.setId(id.toHexString());
        }
    }

    /**
     * Busca y valida un usuario por correo y contrasena
     */
    public UsuarioDTO buscarYValidarUsuario(String correo, String password) {
        Usuario usuario = usuarioDAO.buscarPorCorreo(correo);

        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        if (!usuario.getContrasena().equals(password)) {
            throw new RuntimeException("Contrasena incorrecta");
        }

        return convertirADTO(usuario);
    }

    @Override
    public void generaSolicitud(UsuarioDTO usuario, SolicitudAdopcionDTO solicitud) {
        if (solicitud != null) {
            solicitud.setUsuario(usuario);
        }
    }

    @Override
    public void adoptaMascota(UsuarioDTO usuario, Long idMascota) {
        // Lógica final para vincular mascota al usuario
        System.out.println("Usuario " + usuario.getId() + " adopto mascota " + idMascota);
    }

    @Override
    public void registraCorreo(UsuarioDTO usuario, String correo) {
        if (usuario != null && usuario.getInfoPersonal() != null) {
            usuario.getInfoPersonal().setCorreo(correo);
        }
    }

    @Override
    public SolicitudAdopcionDTO obtieneSolicitudAdopcion(UsuarioDTO usuario) {
        // Buscaria la solicitud activa del usuario
        return new SolicitudAdopcionDTO();
    }

    @Override
    public void actualizarUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO != null && usuarioDTO.getId() != null) {
            Usuario usuario = convertirAEntidad(usuarioDTO);
            usuario.setId(new ObjectId(usuarioDTO.getId()));
            usuarioDAO.actualizar(usuario);
        }
    }

    /**
     * Convierte una entidad Usuario a UsuarioDTO
     */
    private UsuarioDTO convertirADTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();

        if (usuario.getId() != null) {
            dto.setId(usuario.getId().toHexString());
        }

        dto.setContrasena(usuario.getContrasena());

        if (usuario.getInfoPersonal() != null) {
            InfoPersonalDTO infoDTO = new InfoPersonalDTO();
            infoDTO.setNombre(usuario.getInfoPersonal().getNombre());
            infoDTO.setCorreo(usuario.getInfoPersonal().getCorreo());
            infoDTO.setCurp(usuario.getInfoPersonal().getCurp());
            infoDTO.setDireccion(usuario.getInfoPersonal().getDireccion());
            dto.setInfoPersonal(infoDTO);
        }

        if (usuario.getInfoVivienda() != null) {
            DTOS.InfoViviendaDTO infoViviendaDTO = new DTOS.InfoViviendaDTO();
            infoViviendaDTO.setTipoVivienda(usuario.getInfoVivienda().getTipoVivienda());
            infoViviendaDTO.setCondicionesHogar(usuario.getInfoVivienda().getCondicionesHogar());
            infoViviendaDTO.setTieneOtrasMascotas(usuario.getInfoVivienda().isTieneOtrasMascotas());
            infoViviendaDTO.setTieneNinos(usuario.getInfoVivienda().isTieneNinos());
            infoViviendaDTO.setTiempoDisponibilidad(usuario.getInfoVivienda().getTiempoDisponibilidad());
            infoViviendaDTO.setUrlImagenVivienda(usuario.getInfoVivienda().getUrlImagenVivienda());
            dto.setInfoVivienda(infoViviendaDTO);
        }

        return dto;
    }

    /**
     * Convierte un UsuarioDTO a entidad Usuario
     */
    private Usuario convertirAEntidad(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setContrasena(dto.getContrasena());

        if (dto.getInfoPersonal() != null) {
            entities.InfoPersonal infoPersonal = new entities.InfoPersonal();
            infoPersonal.setNombre(dto.getInfoPersonal().getNombre());
            infoPersonal.setCorreo(dto.getInfoPersonal().getCorreo());
            infoPersonal.setCurp(dto.getInfoPersonal().getCurp());
            infoPersonal.setDireccion(dto.getInfoPersonal().getDireccion());
            usuario.setInfoPersonal(infoPersonal);
        }

        if (dto.getInfoVivienda() != null) {
            entities.InfoVivienda infoVivienda = new entities.InfoVivienda();
            infoVivienda.setTipoVivienda(dto.getInfoVivienda().getTipoVivienda());
            infoVivienda.setCondicionesHogar(dto.getInfoVivienda().getCondicionesHogar());
            infoVivienda.setTieneOtrasMascotas(dto.getInfoVivienda().isTieneOtrasMascotas());
            infoVivienda.setTieneNinos(dto.getInfoVivienda().isTieneNinos());
            infoVivienda.setTiempoDisponibilidad(dto.getInfoVivienda().getTiempoDisponibilidad());
            infoVivienda.setUrlImagenVivienda(dto.getInfoVivienda().getUrlImagenVivienda());
            usuario.setInfoVivienda(infoVivienda);
        }

        return usuario;
    }
}