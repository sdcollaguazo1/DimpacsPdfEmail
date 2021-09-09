/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import modelos.Email;
import modelos.PdfEmail;
import servicios.PdfEmailServicio;

/**
 *
 * @author DESARROLLO-3
 */
public class EmailSenderService {

    private Session session;
    private String urlBackend;

    public EmailSenderService(String urlBackend) {
        this.urlBackend = urlBackend;
    }

    public void sendEmail(Email email, PdfEmail pdfEmail) {
        PdfEmailServicio pdfEmailServicio = new PdfEmailServicio(this.urlBackend);
        Address [] destinatarios = stringToAddress(pdfEmail.getPacienteCorreo(),pdfEmail,pdfEmailServicio);
        Address [] destinatariosCorreoCopia = stringToAddress(email.getCorreoCopiaOculta(),pdfEmail,pdfEmailServicio);
        String asunto = email.getSubject();
        try {

            //Propiedades para el envio de correo
            Properties prop = new Properties();
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.connectiontimeout", 1000);
           
            if (email.getSeguridad().equals("Si")) {
                //Habilitar en caso de seguridad SSL/TLS 
                prop.put("mail.smtp.socketFactory.class",
                        "javax.net.ssl.SSLSocketFactory");
                prop.put("mail.smtp.socketFactory.port", email.getPuerto());
                prop.put("mail.smtp.socketFactory.fallback","false");
            } else {
                prop.put("mail.smtp.ssl.trust", email.getHost());
            }

            //Inicializamos el mensaje con datos del email
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email.getUsuario()));
            message.addRecipients(Message.RecipientType.TO, destinatarios);
            
            
            if(email.getUsarCorreoCopiaOculta().equals("Si")){
                message.addRecipients(Message.RecipientType.BCC,destinatariosCorreoCopia);
            }
            
            if(email.isPacienteAsunto()){
                asunto = asunto+" "+pdfEmail.getPacienteNombreApellido();
            }
 
            message.setSubject(asunto);

            //Declaramos el multipart, para agregar varias partes al correo
            MimeMultipart multipart = new MimeMultipart("related");

            // primera parte mensaje html
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<H1>" + email.getSubject() + "</H1>"
                    + "<img src=\"cid:image\">"
                    + "<p>" + email.getMensaje() + "</p>";

            //Agregar enlace de firebase en caso de subir
            if (pdfEmail.isSubirFirebase()) {
                htmlText = htmlText
                        + "<a href=\"" + pdfEmail.getUrlArchivo() + "\" target=\"_blank\">Descargar informe</a>";
            }
            messageBodyPart.setContent(htmlText, "text/html");
            // Añadimos el hml al multipart
            multipart.addBodyPart(messageBodyPart);

            // Segunda parte agregamos la imagen 
            BodyPart imagenBodyPart = new MimeBodyPart();
            DataSource fds = null;
            if(pdfEmail.isUsarConvenio()){
                fds = new FileDataSource(pdfEmail.getPathConvenio());
            }else{
                fds = new FileDataSource(email.getPathLogo());
            }
            
            imagenBodyPart.setDataHandler(new DataHandler(fds));
            imagenBodyPart.setHeader("Content-ID", "<image>");
            // añadimos la imagen al multipart
            multipart.addBodyPart(imagenBodyPart);

            //if (!pdfEmail.isSubirFirebase()) {
                //Tercera parte adjuntamos el pdf
                BodyPart pdfBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(pdfEmail.getRutaArchivo());
                pdfBodyPart.setDataHandler(new DataHandler(source));
                pdfBodyPart.setFileName(pdfEmail.getNombreArchivo());
                //añadimos el pdf al multipart
                multipart.addBodyPart(pdfBodyPart);
            //}
            // Agregamos el multipart al contenido del mensaje
            message.setContent(multipart);

            //Agregamos una sesion con las propiedad para el envio
            Session session = Session.getInstance(prop, null);
            //Agregamos un transport para el envio con la sesion
            Transport transport = session.getTransport("smtp");
            transport.connect(email.getHost(), email.getPuerto(), email.getUsuario(), email.getClave());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (NoSuchProviderException ex) {
            pdfEmail.setInformeEstado("Error");
            pdfEmail.setError("Error al enviar el correo: " + ex.getMessage());
            pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
            System.out.println("NoSuchProviderException: " + ex.getMessage());
            return;

        } catch (AuthenticationFailedException ex) {
            pdfEmail.setInformeEstado("Error");
            pdfEmail.setError("Error al enviar el correo: " + ex.getMessage());
            pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
            System.out.println("AuthenticationFailedException: " + ex.getMessage());
            return;

        } catch (MessagingException ex) {
            pdfEmail.setInformeEstado("Error");
            pdfEmail.setError("Error al enviar el correo: " + ex.getMessage());
            pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
            System.out.println("MessagingException: " + ex.getMessage());
            return;

        }
        pdfEmail.setInformeEstado("Email enviado");
        pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
        System.out.println("Email Enviado");

    }

    
    public Address [] stringToAddress(String destinatarioString,PdfEmail pdfEmail,PdfEmailServicio pdfEmailServicio){
        String[] destinatarios = destinatarioString.split(",");
        
        Address[] destinos = new Address[destinatarios.length];
        for(int i=0;i<destinos.length;i++){
            try {
                destinos[i]=new InternetAddress(destinatarios[i]);
            } catch (AddressException ex) {
                pdfEmail.setInformeEstado("Error");
                pdfEmail.setError("Error al enviar el correo: " + ex.getMessage());
                pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
                System.out.println("AddressException: " + ex.getMessage());
            }
        }
        return destinos;
    }
}
