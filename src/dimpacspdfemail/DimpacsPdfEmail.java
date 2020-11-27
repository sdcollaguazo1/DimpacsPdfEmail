/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dimpacspdfemail;

import Email.EmailSenderService;
import Pdf.PdfCopy;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Configuracion;
import modelos.Email;
import modelos.PdfEmail;
import org.json.JSONArray;
import org.json.JSONObject;
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
        PdfEmailServicio pdfEmailServicio = new PdfEmailServicio();
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
        } 
            
    }

}
