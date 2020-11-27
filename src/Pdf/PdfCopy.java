/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pdf;

import dimpacspdfemail.DimpacsPdfEmail;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.PdfEmail;
import servicios.PdfEmailServicio;

/**
 *
 * @author DESARROLLO-3
 */
public class PdfCopy {
    
    public String guardarInforme(PdfEmail pdfEmail){
       PdfEmailServicio pdfEmailServicio = new PdfEmailServicio();
       URL url = null; 
        try {
            url = new URL(pdfEmail.getEnlace());
        } catch (MalformedURLException ex) {
            pdfEmail.setInformeEstado("Error al guardar el informe: "+ex.getMessage());
            pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
            return (ex.getMessage());
        }
       InputStream in; 
        try {
            in = url.openStream();
            Files.copy(in, Paths.get(pdfEmail.getRutaArchivo()), StandardCopyOption.REPLACE_EXISTING); 
            in.close(); 
        } catch (IOException ex) {
            pdfEmail.setInformeEstado("Error al guardar el informe: "+ex.getMessage());
            pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
            return (ex.getMessage());
        }
        
        pdfEmail.setInformeEstado("Pdf guardado");
        pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
        System.out.println("Pdf guardado");
        return "OK";
    }
}
