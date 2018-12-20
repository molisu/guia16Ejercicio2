package cl.accenture.programatufuturo.dao;

import cl.accenture.programatufuturo.exceptions.SinConexionException;
import cl.accenture.programatufuturo.model.Usuario;
import jdk.nashorn.internal.runtime.UserAccessorProperty;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Conexion conexion;

    public UsuarioDAO() {
        this.conexion = new Conexion("com.mysql.jdbc.Driver", "localhost", "g16e2", "root", "sextape0223", 3306);
    }

    public void almacenarUsuario(Usuario u) throws SinConexionException {
        try{
            final String SQL = "INSERT INTO usuario(username, password, intentosFallidos, correoElectronico) VALUES (?,?,?,?)";

            PreparedStatement ps = conexion.getConexion().prepareStatement(SQL);
            ps.setString(1,u.getUsername());
            ps.setString(2,u.getPassword());
            ps.setInt(3,u.getIntentosFallidos());
            ps.setString(4,u.getCorreoElectronico());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> obtenerAll() throws SinConexionException {
        List<Usuario> canciones = new ArrayList<Usuario>();
        try {
            final String SQL = "SELECT * FROM usuario";
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Usuario user = new Usuario();
                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setIntentosFallidos(rs.getInt(3));
                user.setCorreoElectronico(rs.getString(4));
                canciones.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return canciones;
    }

    public void eliminarUsuarioxUsername(String u) throws SinConexionException {
        try {
            final String SQL = "DELETE * FROM usuario WHERE username = ?";
            Statement sentenciaDelete  = this.conexion.getConexion().createStatement();
            int resultadoDelete = sentenciaDelete.executeUpdate(SQL);
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(SQL);
            ps.setString(1,u);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
