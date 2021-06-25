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
public class LogCorreo {

    private Long id;
    private Long empresaId;
    private String destinatarios;
    private String estado;
    private String detalle;
    private String observacion;
    private boolean usarConvenio;
    private String imgConvenio;
    private String nombreArchivo;
    private String pacienteNombreApellido;

    public LogCorreo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(String destinatarios) {
        this.destinatarios = destinatarios;
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

    public boolean isUsarConvenio() {
        return usarConvenio;
    }

    public void setUsarConvenio(boolean usarConvenio) {
        this.usarConvenio = usarConvenio;
    }

    public String getImgConvenio() {
        return imgConvenio;
    }

    public void setImgConvenio(String imgConvenio) {
        this.imgConvenio = imgConvenio;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getPacienteNombreApellido() {
        return pacienteNombreApellido;
    }

    public void setPacienteNombreApellido(String pacienteNombreApellido) {
        this.pacienteNombreApellido = pacienteNombreApellido;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

}
