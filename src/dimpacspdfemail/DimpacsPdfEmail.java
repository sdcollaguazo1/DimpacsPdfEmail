/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dimpacspdfemail;

import Email.EmailSenderService;
import Email.LogCorreoSenderService;
import Pdf.PdfCopy;
import java.io.IOException;
import modelos.Configuracion;
import modelos.ConfiguracionFirebase;
import modelos.Email;
import modelos.InformeRclinic;
import modelos.LogCorreo;
import modelos.PdfEmail;
import servicios.FirebaseServicios;
import servicios.InformeRclinicServicio;
import servicios.LogCorreoServicio;
import servicios.PdfEmailServicio;
import servicios.RclinicServicio;
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
        RecuperarEstudiosServicios recuperarEstudiosServicios = new RecuperarEstudiosServicios(urlBackend);
        LogCorreoServicio logCorreoServicio = new LogCorreoServicio(urlBackend);
        InformeRclinicServicio informeRclinicServicio = new InformeRclinicServicio(urlBackend);
        
        recuperarEstudiosServicios.getRecuperarEstudios();
        
        PdfEmail[] listPdfEmail = pdfEmailServicio.getListPdfEmail();
        LogCorreo[] listLogCorreo = logCorreoServicio.getLogCorreoEnviar();
        InformeRclinic[] listInformeRclinic = informeRclinicServicio.getListInformeRclinic();
        
        for (PdfEmail pdfEmail : listPdfEmail) {
            String resultado = "OK";
            
            if(pdfEmail.isGuardarInforme()){
                resultado = pdfCopy.guardarInforme(pdfEmail);
            }
            
            if (resultado == "OK") {    
                if(pdfEmail.isSubirFirebase()){
                    pdfEmail = subirArchivos(pdfEmail,urlBackend);
                }
                
                boolean existError = pdfEmail.getError() != null;
                if (pdfEmail.isEnviarCorreo() && !existError) {
                    enviarEmail(pdfEmail,urlBackend);                                     
                }

            }
        } 
        
        //PROCESO DE REENVIO DE CORREO
        for(LogCorreo logCorreo : listLogCorreo){
            enviarMailLog(logCorreo,urlBackend);
        }
        
        //PROCESO DE ENVIO DE INFORMES A RCLINIC
        for(InformeRclinic informeRclinic:listInformeRclinic){
            enviarRclinic(informeRclinic,urlBackend);
        }
            
    }
    
    private static void enviarEmail(PdfEmail pdfEmail,String urlBackend){
        
        Email email = new Email();  
        EmailSenderService emailSender = new EmailSenderService(urlBackend);
        PdfEmailServicio pdfEmailServicio = new PdfEmailServicio(urlBackend);
        
        Configuracion[] listConfiguracionEmail = pdfEmailServicio.getConfiguracionEmail(pdfEmail.getEmpresaId());
        
        email = email.convertirConfiguracionToEmail(listConfiguracionEmail);
        emailSender.sendEmail(email,pdfEmail);
    }
    
     private static void enviarMailLog(LogCorreo logCorreo,String urlBackend){
        
        Email email = new Email();  
        LogCorreoSenderService emailSender = new LogCorreoSenderService(urlBackend);
        PdfEmailServicio pdfEmailServicio = new PdfEmailServicio(urlBackend);
        
        Configuracion[] listConfiguracionEmail = pdfEmailServicio.getConfiguracionEmail(logCorreo.getEmpresaId());
        
        email = email.convertirConfiguracionToEmail(listConfiguracionEmail);
        emailSender.sendEmail(email,logCorreo);
    }
    
     
    private static PdfEmail  subirArchivos(PdfEmail pdfEmail,String urlBackend){
        
        PdfEmailServicio pdfEmailServicio = new PdfEmailServicio(urlBackend);
        FirebaseServicios firebaseServicios = new FirebaseServicios(urlBackend);
        
        ConfiguracionFirebase configuracionFirebase = new ConfiguracionFirebase();  
         
        Configuracion[] listConfiguracionFirebase = pdfEmailServicio.getConfiguracionFirebase(pdfEmail.getEmpresaId());
        configuracionFirebase = configuracionFirebase.convertirConfiguracionFirebase(listConfiguracionFirebase);
        
        return firebaseServicios.subirArchivos(configuracionFirebase,pdfEmail);
    }
    
    private static void enviarRclinic(InformeRclinic informeRclinic,String urlBackend){
        
        RclinicServicio rclinicServicio = new RclinicServicio("http://192.168.200.218/",urlBackend);
        rclinicServicio.enviarInforme(informeRclinic);
    }
}
