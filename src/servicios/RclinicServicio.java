/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import modelos.InformeRclinic;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import modelos.LogInformeRclinic;
/**
 *
 * @author digetbi
 */
public class RclinicServicio {

    String apiRclinic = "";
    String apiDimpacs = "";

    public RclinicServicio(String urlRclinic,String urlDimpacs) {
        this.apiRclinic = urlRclinic + "citas/get_remote_img/";
        this.apiDimpacs = urlDimpacs;
    }
    
    public void enviarInforme(InformeRclinic informeRclinic) {
        InformeRclinicServicio informeRclinicServicio = new InformeRclinicServicio(this.apiDimpacs);
        LogInformeRclinic logInformeRclinic = new LogInformeRclinic(informeRclinic);
        
        String resultado = "";
        Gson g = new Gson();
        String json = g.toJson(informeRclinic.getMapInformeRclinic());
        
        try {
            URL url = new URL(apiRclinic);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                resultado = response.toString();
                logInformeRclinic.setEstado("Enviado");
                logInformeRclinic.setDetalle(resultado);
                informeRclinicServicio.logInformeRclinic(logInformeRclinic);
                System.out.println("RClinic enviado correctamente");
            }

        } catch (IOException e) {
            e.toString();
            logInformeRclinic.setEstado("Error");
            logInformeRclinic.setObservacion(e.getMessage());
            logInformeRclinic.setDetalle(json);
            informeRclinicServicio.logInformeRclinic(logInformeRclinic);
            System.out.println(e.toString());
        }

    }

}
