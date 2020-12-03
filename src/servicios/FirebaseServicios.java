/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.DatabaseReference;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Paciente;

/**
 *
 * @author DESARROLLO-3
 */
public class FirebaseServicios {

    public void conectar() throws IOException {

        FileInputStream refreshToken = new FileInputStream("C:\\Temp\\uploads\\firebase\\keyFirebase.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .setDatabaseUrl("https://dimpacs.firebaseio.com")
                .setStorageBucket("dimpacs.appspot.com")
                .build();

        FirebaseApp defaultApp = FirebaseApp.initializeApp(options);

        System.out.println(defaultApp.getName());

        // Retrieve services by passing the defaultApp variable...
        FirebaseAuth defaultAuth = FirebaseAuth.getInstance(defaultApp);
        FirebaseDatabase defaultDatabase = FirebaseDatabase.getInstance(defaultApp);

// ... or use the equivalent shorthand notation
        defaultAuth = FirebaseAuth.getInstance();
        defaultDatabase = FirebaseDatabase.getInstance();
        
        
        Bucket bucket = StorageClient.getInstance().bucket();
        
        InputStream testFile = new FileInputStream("C:\\Temp\\uploads\\informes\\informe.pdf");
            String blobString = "paciente/" + "informe.pdf";        

        bucket.create(blobString, testFile , Bucket.BlobWriteOption.userProject("dimpacs"));
        /*DatabaseReference ref = defaultDatabase.getReference();

        DatabaseReference usersRef = ref.child("pacientes");

        
        Map<String, Paciente> users = new HashMap<>();
        users.put("alanisawesome", new Paciente("1"));
        users.put("gracehop", new Paciente("2"));

        usersRef.setValueAsync(users);*/


        //Firestore db = FirestoreClient.getFirestore();
        
        
        //ApiFuture<QuerySnapshot> query = db.collection("usuarios").get();

        
        /*QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseServicios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(FirebaseServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
          System.out.println("User: " + document.getId());
          System.out.println("Nombre: " + document.getString("nombre"));

        }*/
    }
}
