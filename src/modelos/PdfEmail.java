/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DESARROLLO-3
 */
public class PdfEmail {
    
    private Long informeId;
    
    private Long empresaId;
    
    private String enlace;
    
    private String rutaArchivo;
    
    private String nombreArchivo;
    
    private String pacienteCorreo;
    
    private String informeEstado;
    
    private String error;
    
    private String urlArchivo;
    
    private String pacienteNombreApellido;

    private boolean enviarCorreo;
    
    private boolean subirFirebase;
    
    private String pathConvenio;
    
    private boolean usarConvenio;
    
    private boolean guardarInforme;
    
    private List<String>urlImagenes;
	
    
    public PdfEmail() {
        this.urlImagenes = new ArrayList<String>();
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

    public boolean isSubirFirebase() {
        return subirFirebase;
    }

    public void setSubirFirebase(boolean subirFirebase) {
        this.subirFirebase = subirFirebase;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getUrlArchivo() {
        return urlArchivo;
    }

    public void setUrlArchivo(String urlArchivo) {
        this.urlArchivo = urlArchivo;
    }

    public String getPathConvenio() {
        return pathConvenio;
    }

    public void setPathConvenio(String pathConvenio) {
        this.pathConvenio = pathConvenio;
    }

    public boolean isUsarConvenio() {
        return usarConvenio;
    }

    public void setUsarConvenio(boolean usarConvenio) {
        this.usarConvenio = usarConvenio;
    }

    public String getPacienteNombreApellido() {
        return pacienteNombreApellido;
    }

    public void setPacienteNombreApellido(String pacienteNombreApellido) {
        this.pacienteNombreApellido = pacienteNombreApellido;
    }

    public boolean isGuardarInforme() {
        return guardarInforme;
    }

    public void setGuardarInforme(boolean guardarInforme) {
        this.guardarInforme = guardarInforme;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public List<String> getUrlImagenes() {
        return urlImagenes;
    }

    public void setUrlImagenes(List<String> urlImagenes) {
        this.urlImagenes = urlImagenes;
    }
  
    public LogCorreo getLogCorreo(){
        LogCorreo logCorreo= new LogCorreo();
        Informe informe = new Informe(this.informeId);
        
        logCorreo.setInforme(informe);
        logCorreo.setDestinatarios(this.pacienteCorreo);
        logCorreo.setEstado(this.informeEstado);
        logCorreo.setDetalle("Envio Automatico");
        logCorreo.setObservacion(this.error);
        
        return logCorreo;
    }
}
