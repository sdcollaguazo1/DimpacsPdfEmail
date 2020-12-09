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

/**
 *
 * @author DESARROLLO-3
 */
public class DimpacsPdfEmail {

    /**
     * @param args the command line arguments
     */
   
    
    public static void main(String[] args){
        
        PdfCopy pdfCopy = new PdfCopy();    
        PdfEmailServicio pdfEmailServicio = new PdfEmailServicio();
        
        PdfEmail[] listPdfEmail = pdfEmailServicio.getListPdfEmail();
        
        for (PdfEmail pdfEmail : listPdfEmail) {
            String resultado = pdfCopy.guardarInforme(pdfEmail);
            
            if (resultado == "OK") {    
                if(pdfEmail.isSubirFirebase()){
                    pdfEmail = subirArchivos(pdfEmail);
                }
                
                if (pdfEmail.isEnviarCorreo()) {
                    enviarEmail(pdfEmail);                                     
                }

            }
        } 
            
    }
    
    private static void enviarEmail(PdfEmail pdfEmail){
        Email email = new Email();  
        EmailSenderService emailSender = new EmailSenderService();
        PdfEmailServicio pdfEmailServicio = new PdfEmailServicio();
        
        Configuracion[] listConfiguracionEmail = pdfEmailServicio.getConfiguracionEmail();
        
        email = email.convertirConfiguracionToEmail(listConfiguracionEmail);
        emailSender.sendEmail(email,pdfEmail);
    }
    
    private static PdfEmail  subirArchivos(PdfEmail pdfEmail){
        PdfEmailServicio pdfEmailServicio = new PdfEmailServicio();
        FirebaseServicios firebaseServicios = new FirebaseServicios();
        
        ConfiguracionFirebase configuracionFirebase = new ConfiguracionFirebase();  
         
        Configuracion[] listConfiguracionFirebase = pdfEmailServicio.getConfiguracionFirebase();
        configuracionFirebase = configuracionFirebase.convertirConfiguracionFirebase(listConfiguracionFirebase);
        
        return firebaseServicios.subirArchivos(configuracionFirebase,pdfEmail);
    }
}
