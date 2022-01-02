/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author digetbi
 */
public class InformeRclinic {
    private Long id;
    private String nroOrdenAS400;
    private String cedula;
    private String codigoAs400;
    private String textoInforme;
    private String textoConclusion;
    private List<String> listDiagnosticos;
    private String fechaEnvio;
    private String estado;
    private String codigoImagenologo;
    private Boolean inactivo;

    public InformeRclinic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNroOrdenAS400() {
        return nroOrdenAS400;
    }

    public void setNroOrdenAS400(String nroOrdenAS400) {
        this.nroOrdenAS400 = nroOrdenAS400;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCodigoAs400() {
        return codigoAs400;
    }

    public void setCodigoAs400(String codigoAs400) {
        this.codigoAs400 = codigoAs400;
    }

    public String getTextoInforme() {
        return textoInforme;
    }

    public void setTextoInforme(String textoInforme) {
        this.textoInforme = textoInforme;
    }

    public String getTextoConclusion() {
        return textoConclusion;
    }

    public void setTextoConclusion(String textoConclusion) {
        this.textoConclusion = textoConclusion;
    }

    public List<String> getListDiagnosticos() {
        return listDiagnosticos;
    }

    public void setListDiagnosticos(List<String> listDiagnosticos) {
        this.listDiagnosticos = listDiagnosticos;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoImagenologo() {
        return codigoImagenologo;
    }

    public void setCodigoImagenologo(String codigoImagenologo) {
        this.codigoImagenologo = codigoImagenologo;
    }

    public Boolean getInactivo() {
        return inactivo;
    }

    public void setInactivo(Boolean inactivo) {
        this.inactivo = inactivo;
    }

    public Map<String, Object> getMapInformeRclinic(){
        Map<String, Object> elements = new HashMap();
        elements.put("orden_no", this.getNroOrdenAS400());
        elements.put("fecha_envio", this.getFechaEnvio());
        elements.put("texto_informe", this.getTextoInforme());
        elements.put("texto_conclusion", this.getTextoConclusion());
        elements.put("diagnosticos", this.getListDiagnosticos());
        elements.put("codigo_imagenologo", this.getCodigoImagenologo());
        return elements;
    }
    
}
