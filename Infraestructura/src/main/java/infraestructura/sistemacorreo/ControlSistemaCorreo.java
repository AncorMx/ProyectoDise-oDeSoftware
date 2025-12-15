/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infraestructura.sistemacorreo;

import infraestructura.dto.CorreoDTO;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Control para validacion y envio de correos con SMTP
 */
public class ControlSistemaCorreo {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public ControlSistemaCorreo() {
    }

    /**
     * Valida formato de correo electronico
     */
    public boolean validarCorreo(String correo) {
        if (correo == null || correo.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(correo).matches();
    }

    /**
     * Envia un correo usando SMTP
     */
    public void enviarCorreo(CorreoDTO correo) throws Exception {
        if (correo == null) {
            throw new Exception("El objeto correo no puede ser nulo.");
        }

        if (!validarCorreo(correo.getDestinatario())) {
            throw new Exception("Direccion de correo invalida: " + correo.getDestinatario());
        }

        if (correo.getAsunto() == null || correo.getAsunto().isEmpty()) {
            throw new Exception("El asunto no puede estar vacio");
        }

        if (correo.getMensaje() == null || correo.getMensaje().isEmpty()) {
            throw new Exception("El mensaje no puede estar vacio");
        }

        enviarCorreoSMTP(correo);
    }

    private void enviarCorreoSMTP(CorreoDTO correo) throws Exception {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", String.valueOf(ConfiguracionCorreo.SMTP_AUTH));
            props.put("mail.smtp.starttls.enable", String.valueOf(ConfiguracionCorreo.SMTP_STARTTLS));
            props.put("mail.smtp.host", ConfiguracionCorreo.SMTP_HOST);
            props.put("mail.smtp.port", ConfiguracionCorreo.SMTP_PORT);
            props.put("mail.smtp.ssl.trust", "*");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                            ConfiguracionCorreo.CORREO_REMITENTE,
                            ConfiguracionCorreo.PASSWORD_REMITENTE);
                }
            });

            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(
                    ConfiguracionCorreo.CORREO_REMITENTE,
                    ConfiguracionCorreo.NOMBRE_REMITENTE));
            mensaje.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(correo.getDestinatario()));
            mensaje.setSubject(correo.getAsunto());
            mensaje.setText(correo.getMensaje());

            Transport.send(mensaje);

        } catch (MessagingException e) {
            throw new Exception("Error al enviar el correo: " + e.getMessage(), e);
        }
    }
}
