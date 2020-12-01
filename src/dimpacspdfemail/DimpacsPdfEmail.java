/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dimpacspdfemail;

import Email.EmailSenderService;
import Pdf.PdfCopy;
import java.io.IOException;
import modelos.Configuracion;
import modelos.Email;
import modelos.PdfEmail;
import servicios.FirebaseServicios;
import servicios.PdfEmailServicio;

/**
 *
 * @author DESARROLLO-3
 */
public class DimpacsPdfEmail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        firebase();
        /*PdfEmailServicio pdfEmailServicio = new PdfEmailServicio();
        Configuracion[] listConfiguracion = pdfEmailServicio.getConfiguracionEmail();
        
        PdfCopy pdfCopy = new PdfCopy();
        EmailSenderService emailSender = new EmailSenderService();        
        Email email = new Email(); 
        
        email = email.convertirConfiguracionToEmail(listConfiguracion);
        
        PdfEmail[] listPdfEmail = pdfEmailServicio.getListPdfEmail();
        for (PdfEmail pdfEmail : listPdfEmail) {
            String resultado = pdfCopy.guardarInforme(pdfEmail);
            
            if (resultado == "OK") {    
                
                if (pdfEmail.isEnviarCorreo()) {
                    emailSender.sendEmail(email,pdfEmail);                    
                }

            }
        } */
            
    }
    
    private static void firebase() throws IOException{
        FirebaseServicios firebaseServicios = new FirebaseServicios();
        firebaseServicios.conectar();
    }

}
