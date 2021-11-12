/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pdf;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import modelos.PdfEmail;
import servicios.LogCorreoServicio;
import servicios.PdfEmailServicio;

/**
 *
 * @author DESARROLLO-3
 */
public class PdfCopy {
    
    private String urlBackend;

    public PdfCopy(String urlBackend) {
        this.urlBackend = urlBackend;
    }
           
    public String guardarInforme(PdfEmail pdfEmail){
       PdfEmailServicio pdfEmailServicio = new PdfEmailServicio(this.urlBackend);
       LogCorreoServicio logCorreoServicio = new LogCorreoServicio(this.urlBackend);
       URL url = null; 
        try {
            url = new URL(pdfEmail.getEnlace());
        } catch (MalformedURLException ex) {
            pdfEmail.setInformeEstado("Error");
            pdfEmail.setError("Error al guardar el informe: "+ex.getMessage());
            pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
            logCorreoServicio.guardarLogCorreo(pdfEmail.getLogCorreo());
            return (ex.getMessage());
        }
       InputStream in; 
        try {
            in = url.openStream();
            Files.copy(in, Paths.get(pdfEmail.getRutaArchivo()), StandardCopyOption.REPLACE_EXISTING); 
            in.close(); 
        } catch (IOException ex) {
            pdfEmail.setInformeEstado("Error");
            pdfEmail.setError("Error al guardar el informe: "+ex.getMessage());
            pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
            logCorreoServicio.guardarLogCorreo(pdfEmail.getLogCorreo());
            return (ex.getMessage());
        }
        
        pdfEmail.setInformeEstado("Pdf generado");
        pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
        logCorreoServicio.guardarLogCorreo(pdfEmail.getLogCorreo());
        System.out.println("Pdf generado");
        return "OK";
    }
}
