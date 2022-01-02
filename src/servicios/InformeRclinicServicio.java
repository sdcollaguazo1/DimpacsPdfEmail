/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import modelos.InformeRclinic;
import modelos.LogInformeRclinic;

/**
 *
 * @author digetbi
 */
public class InformeRclinicServicio {
    String apiDimpacs = "";

    public InformeRclinicServicio(String urlBackend) {
        this.apiDimpacs = urlBackend+"api/informeRclinic";
    }
    
    public InformeRclinic[] getListInformeRclinic() {
        InformeRclinic[] listInformeRclinic = null;
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
                    listInformeRclinic = g.fromJson(output, InformeRclinic[].class);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return listInformeRclinic;
    }
    
    public void logInformeRclinic(LogInformeRclinic LogInformeRclinic){
        String resultado = "";
        
        Gson g = new Gson();
        String json = g.toJson(LogInformeRclinic);
        
        try {
            URL url = new URL(apiDimpacs+"/log");
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
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
