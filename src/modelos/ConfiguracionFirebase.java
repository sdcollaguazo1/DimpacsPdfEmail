/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author DESARROLLO-3
 */
public class ConfiguracionFirebase {
    
    private String rutaArchivo;
    
    private String databaseUrl;
    
    private String storageBucket;
    
    private String projectId;

    public ConfiguracionFirebase() {
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public String getStorageBucket() {
        return storageBucket;
    }

    public void setStorageBucket(String storageBucket) {
        this.storageBucket = storageBucket;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    
    public ConfiguracionFirebase convertirConfiguracionFirebase(Configuracion[] listConfiguracionFirebase){
        ConfiguracionFirebase configuracionFirebase = new ConfiguracionFirebase();
        for (Configuracion configuracion : listConfiguracionFirebase) {
            switch (configuracion.getId().intValue()) {
                case 18:
                    configuracionFirebase.setRutaArchivo(configuracion.getValor());
                    break;
                case 19:
                    configuracionFirebase.setDatabaseUrl(configuracion.getValor());
                    break;
                case 20:
                    configuracionFirebase.setStorageBucket(configuracion.getValor());
                    break;
                case 21:
                    configuracionFirebase.setProjectId(configuracion.getValor());
                    break;
                
            }

        }
        return configuracionFirebase;
    }
}
