package cl.accenture.programatufuturo.model;

import sun.rmi.transport.ObjectTable;

// clase Usuario
public class Usuario {

    // Con sus atributos respectivos segun la tabla SQL
    private String username, password, correoElectronico;
    private int intentosFallidos;

    // Cosntructor por defecto
    public Usuario() {
        this.username = "";
        this.password= "";
        this.correoElectronico = "";
        this.intentosFallidos = 0;
    }

    // Constructor con parametros
    public Usuario(String username, String password, String correoElectronico, int intentosFallidos) {
        this.username = username;
        this.password = password;
        this.correoElectronico = correoElectronico;
        this.intentosFallidos = intentosFallidos;
    }


    // getters y setter para mostrar o modificar según corresponda
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


    // equals modificado para comparar 2 usuarios
    // segun su Username, usamos .equal en este caso ya que son Strings
    public boolean equals(Object o) {
        if(o instanceof Usuario) {
            Usuario user = (Usuario) o;
            return this.getUsername().equals(user.getUsername());
        }
        return false;
    }
}
