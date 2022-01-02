/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author digetbi
 */
public class LogInformeRclinic {

    private Long id;
    private String estado;
    private String detalle;
    private String observacion;
    private InformeRclinic informeRclinic;

    public LogInformeRclinic() {
    }

    public LogInformeRclinic(InformeRclinic informeRclinic) {
        this.informeRclinic = informeRclinic;
    }

     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public InformeRclinic getInformeRclinic() {
        return informeRclinic;
    }

    public void setInformeRclinic(InformeRclinic informeRclinic) {
        this.informeRclinic = informeRclinic;
    }

}
