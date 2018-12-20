package cl.accenture.programatufuturo.model;

import sun.rmi.transport.ObjectTable;

public class Usuario {

    private String username, password, correoElectronico;
    private int intentosFallidos;

    public Usuario() {
        this.username = "";
        this.password= "";
        this.correoElectronico = "";
        this.intentosFallidos = 0;
    }

    public Usuario(String username, String password, String correoElectronico, int intentosFallidos) {
        this.username = username;
        this.password = password;
        this.correoElectronico = correoElectronico;
        this.intentosFallidos = intentosFallidos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public boolean equals(Object o) {
        if(o instanceof Usuario) {
            Usuario user = (Usuario) o;
            return this.getUsername().equals(user.getUsername());
        }
        return false;
    }
}
