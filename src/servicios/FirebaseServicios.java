/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;


/**
 *
 * @author DESARROLLO-3
 */
public class FirebaseServicios {
    
    public void conectar() throws IOException {
     
      FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setDatabaseUrl("https://dimpacs.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

    }
}
