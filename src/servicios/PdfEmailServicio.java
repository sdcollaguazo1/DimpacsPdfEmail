/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import modelos.Configuracion;
import modelos.PdfEmail;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author DESARROLLO-3
 */
public class PdfEmailServicio {

    String apiDimpacs = "http://localhost:8080/api/pdfEmail";

    public PdfEmail[] getListPdfEmail() {
        PdfEmail[] listPdfEmail = null;
        Gson g = new Gson();
        try {

            URL url = new URL(apiDimpacs);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                String output;

                while ((output = br.readLine()) != null) {
                    listPdfEmail = g.fromJson(output, PdfEmail[].class);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return listPdfEmail;
    }

    public String cambiarEstatusInforme(PdfEmail pdfEmail) {
        String resultado = "";
        Gson g = new Gson();      
        String json = g.toJson(pdfEmail);
      
        try {
            URL url = new URL(apiDimpacs);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setDoOutput(true); 
            conn.setDoInput(true);
            
            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);			
            }
            
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                resultado = response.toString();
            }
            
        } catch (IOException e) {
            return e.toString();
        }

        return resultado;
    }
    
    public Configuracion[] getConfiguracionEmail() {
        Configuracion[] configuracion = null;
        Gson g = new Gson();
        try {

            URL url = new URL(apiDimpacs+"/configuracion");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                String output;

                while ((output = br.readLine()) != null) {
                    configuracion = g.fromJson(output, Configuracion[].class);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return configuracion;
    }
}
