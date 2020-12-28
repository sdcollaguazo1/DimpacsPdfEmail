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
import modelos.ConfiguracionFirebase;
import modelos.Email;
import modelos.PdfEmail;
import servicios.FirebaseServicios;
import servicios.PdfEmailServicio;
import servicios.RecuperarEstudiosServicios;

/**
 *
 * @author DESARROLLO-3
 */
public class DimpacsPdfEmail {

    /**
     * @param args the command line arguments
     */
   
    
    public static void main(String[] args){
        
        if(args.length == 0){
         System.exit(0);
        }        
        //String urlBackend = "http://localhost:8080/";
        String urlBackend = args[0];
        
        PdfCopy pdfCopy = new PdfCopy(urlBackend);    
        PdfEmailServicio pdfEmailServicio = new PdfEmailServicio(urlBackend);
        RecuperarEstudiosServicios recuperarEstudiosServicios = new RecuperarEstudiosServicios();
        
        recuperarEstudiosServicios.getRecuperarEstudios();
        
        PdfEmail[] listPdfEmail = pdfEmailServicio.getListPdfEmail();
        
        for (PdfEmail pdfEmail : listPdfEmail) {
            String resultado = pdfCopy.guardarInforme(pdfEmail);
            
            if (resultado == "OK") {    
                if(pdfEmail.isSubirFirebase()){
                    pdfEmail = subirArchivos(pdfEmail,urlBackend);
                }
                
                if (pdfEmail.isEnviarCorreo()) {
                    enviarEmail(pdfEmail,urlBackend);                                     
                }

            }
        } 
            
    }
    
    private static void enviarEmail(PdfEmail pdfEmail,String urlBackend){
        
        Email email = new Email();  
        EmailSenderService emailSender = new EmailSenderService(urlBackend);
        PdfEmailServicio pdfEmailServicio = new PdfEmailServicio(urlBackend);
        
        Configuracion[] listConfiguracionEmail = pdfEmailServicio.getConfiguracionEmail();
        
        email = email.convertirConfiguracionToEmail(listConfiguracionEmail);
        emailSender.sendEmail(email,pdfEmail);
    }
    
    private static PdfEmail  subirArchivos(PdfEmail pdfEmail,String urlBackend){
        
        PdfEmailServicio pdfEmailServicio = new PdfEmailServicio(urlBackend);
        FirebaseServicios firebaseServicios = new FirebaseServicios(urlBackend);
        
        ConfiguracionFirebase configuracionFirebase = new ConfiguracionFirebase();  
         
        Configuracion[] listConfiguracionFirebase = pdfEmailServicio.getConfiguracionFirebase();
        configuracionFirebase = configuracionFirebase.convertirConfiguracionFirebase(listConfiguracionFirebase);
        
        return firebaseServicios.subirArchivos(configuracionFirebase,pdfEmail);
    }
}
