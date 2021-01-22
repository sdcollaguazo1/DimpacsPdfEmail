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
public class Email {

    private String host;
    private int puerto;
    private String usuario;
    private String clave;
    private String subject;
    private String mensaje;
    private String pathLogo;
    private String seguridad;
    private String usarCorreoCopiaOculta;
    private String correoCopiaOculta;
    
    public Email() {

    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getPathLogo() {
        return pathLogo;
    }

    public void setPathLogo(String pathLogo) {
        this.pathLogo = pathLogo;
    }

    public String getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(String seguridad) {
        this.seguridad = seguridad;
    }

    public String getUsarCorreoCopiaOculta() {
        return usarCorreoCopiaOculta;
    }

    public void setUsarCorreoCopiaOculta(String usarCorreoCopiaOculta) {
        this.usarCorreoCopiaOculta = usarCorreoCopiaOculta;
    }

    public String getCorreoCopiaOculta() {
        return correoCopiaOculta;
    }

    public void setCorreoCopiaOculta(String correoCopiaOculta) {
        this.correoCopiaOculta = correoCopiaOculta;
    }
    
    

    public Email convertirConfiguracionToEmail(Configuracion[] listConfiguracion) {
        Email email = new Email();
        for (Configuracion configuracion : listConfiguracion) {
            switch (configuracion.getId().intValue()) {
                case 5:
                    email.setPathLogo(configuracion.getValor());
                    break;
                case 11:
                    email.setHost(configuracion.getValor());
                    break;
                case 12:
                    email.setPuerto(Integer.valueOf(configuracion.getValor()));
                    break;
                case 13:
                    email.setUsuario(configuracion.getValor());
                    break;
                case 14:
                    email.setClave(configuracion.getValor());
                    break;
                case 15:
                    email.setSubject(configuracion.getValor());
                    break;
                case 16:
                    email.setMensaje(configuracion.getValor());
                    break;
                case 36:
                    email.setSeguridad(configuracion.getValor());
                    break;
                case 37:
                    email.setUsarCorreoCopiaOculta(configuracion.getValor());
                    break;
                case 38:
                    email.setCorreoCopiaOculta(configuracion.getValor());
                    break;
            }

        }
        return email;
    }
}
