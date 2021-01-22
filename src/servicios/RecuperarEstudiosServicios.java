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
import java.net.HttpURLConnection;
import java.net.URL;
import modelos.PdfEmail;

/**
 *
 * @author DESARROLLO-3
 */
public class RecuperarEstudiosServicios {

    String apiDimpacs = "";
    

    public RecuperarEstudiosServicios(String urlBackend) {
        this.apiDimpacs = urlBackend+"api/recuperarEstudios";
    }
    
    public void getRecuperarEstudios() {
       
        try {

            URL url = new URL(apiDimpacs);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                String output;

                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        
    }
    
}
