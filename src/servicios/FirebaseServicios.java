/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.StorageException;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.ConfiguracionFirebase;
import modelos.PdfEmail;

/**
 *
 * @author DESARROLLO-3
 */
public class FirebaseServicios {

    private FileInputStream refreshToken;

    private FirebaseOptions options;

    private FirebaseApp defaultApp;

    private Bucket bucket;

    private InputStream testFile;

    private Blob blob;

    private String urlBackend;

    public FirebaseServicios(String urlBackend) {
        this.urlBackend = urlBackend;
    }

    public PdfEmail subirArchivos(ConfiguracionFirebase configuracionFirebase, PdfEmail pdfEmail) {

        PdfEmailServicio pdfEmailServicio = new PdfEmailServicio(this.urlBackend);
        LogCorreoServicio logCorreoServicio = new LogCorreoServicio(this.urlBackend);

        try {
            refreshToken = new FileInputStream(configuracionFirebase.getRutaArchivo());
        } catch (FileNotFoundException ex) {
            pdfEmail.setInformeEstado("Error");
            pdfEmail.setError("Error al buscar la ruta del archivo de firebase: " + ex.getMessage());
            pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
            logCorreoServicio.guardarLogCorreo(pdfEmail.getLogCorreo());
            System.out.println("Error al buscar la ruta del archivo de firebase: " + ex.getMessage());
            return pdfEmail;
        }

        try {
            options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setDatabaseUrl(configuracionFirebase.getDatabaseUrl())
                    .setStorageBucket(configuracionFirebase.getStorageBucket())
                    .build();
        } catch (IOException ex) {
            pdfEmail.setInformeEstado("Error");
            pdfEmail.setError("Error al configurar firebase: " + ex.getMessage());
            pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
            logCorreoServicio.guardarLogCorreo(pdfEmail.getLogCorreo());
            System.out.println("Error al configurar firebase: " + ex.getMessage());
            return pdfEmail;
        }

        try {
            if (FirebaseApp.getApps().size() > 0) {
                defaultApp = FirebaseApp.initializeApp(options);
             }else {
                defaultApp = FirebaseApp.getApps().get(0); // if already initialized, use that one
             }
            //defaultApp = FirebaseApp.initializeApp(options);
            bucket = StorageClient.getInstance().bucket();
        } catch (IllegalArgumentException ex) {
            pdfEmail.setInformeEstado("Error");
            pdfEmail.setError("Error al configurar StorageClient: " + ex.getMessage());
            pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
            logCorreoServicio.guardarLogCorreo(pdfEmail.getLogCorreo());
            System.out.println("Error al configurar StorageClient: " + ex.getMessage());
            return pdfEmail;
        } catch (IllegalStateException ex){
            pdfEmail.setInformeEstado("Error");
            pdfEmail.setError("Error al configurar StorageClient: " + ex.getMessage());
            pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
            logCorreoServicio.guardarLogCorreo(pdfEmail.getLogCorreo());
            System.out.println("Error al configurar StorageClient: " + ex.getMessage());
            return pdfEmail;
        }
        
        try {
            testFile = new FileInputStream(pdfEmail.getRutaArchivo());
        } catch (FileNotFoundException ex) {
            pdfEmail.setInformeEstado("Error");
            pdfEmail.setError("Archivo no encontrado (firebase): " + ex.getMessage());
            pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
            logCorreoServicio.guardarLogCorreo(pdfEmail.getLogCorreo());
            System.out.println("Archivo no encontrado (firebase): " + ex.getMessage());
            return pdfEmail;
        }

        try{
            blob = bucket.create(pdfEmail.getNombreArchivo(), testFile, Bucket.BlobWriteOption.userProject(configuracionFirebase.getProjectId()));
        }catch(StorageException ex){
            pdfEmail.setInformeEstado("Error");
            pdfEmail.setError("Error al subir archivos a firebase: " + ex);
            pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
            logCorreoServicio.guardarLogCorreo(pdfEmail.getLogCorreo());
            System.out.println("Error al subir archivos a firebase: " + ex);
            return pdfEmail;
        }
        

        String link = "https://docs.google.com/gview?url=https://storage.googleapis.com/"
                + blob.getBucket() + "/" + blob.getName() + "&embedded=true";

        pdfEmail.setUrlArchivo(link);
        pdfEmail.setInformeEstado("Subido a Firebase");
        pdfEmailServicio.cambiarEstatusInforme(pdfEmail);
        logCorreoServicio.guardarLogCorreo(pdfEmail.getLogCorreo());
        
        System.out.println("Pdf subido a firebase");

        return pdfEmail;
    }
}
