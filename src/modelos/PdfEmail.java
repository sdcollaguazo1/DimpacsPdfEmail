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
public class PdfEmail {
    
    private Long informeId;
    
    private String enlace;
    
    private String rutaArchivo;
    
    private String nombreArchivo;
    
    private String pacienteCorreo;
    
    private String informeEstado;

    private boolean enviarCorreo;

    public PdfEmail() {
        
    }   
    

    public Long getInformeId() {
        return informeId;
    }

    public void setInformeId(Long informeId) {
        this.informeId = informeId;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getPacienteCorreo() {
        return pacienteCorreo;
    }

    public void setPacienteCorreo(String pacienteCorreo) {
        this.pacienteCorreo = pacienteCorreo;
    }

    public String getInformeEstado() {
        return informeEstado;
    }

    public void setInformeEstado(String informeEstado) {
        this.informeEstado = informeEstado;
    }

    public boolean isEnviarCorreo() {
        return enviarCorreo;
    }

    public void setEnviarCorreo(boolean enviarCorreo) {
        this.enviarCorreo = enviarCorreo;
    }
    
    
    
}
