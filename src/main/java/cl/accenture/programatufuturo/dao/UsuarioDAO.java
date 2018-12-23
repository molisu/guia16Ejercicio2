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

// case DAO de usuario
public class UsuarioDAO {

    // unico atributo la Conexion previamente creada
    private Conexion conexion;

    // En el constructor por defecto iniciamos una nueva Conexion en mi caso rellenando los atributos
    public UsuarioDAO() {
        this.conexion = new Conexion("com.mysql.jdbc.Driver", "localhost", "g16e2", "root", "sextape0223", 3306);
    }

    // Almacenar Usuario, no retornamos nada, recibimos un Usuario
    public void almacenarUsuario(Usuario u) throws SinConexionException {
        try{

            // en mi SQL hago un INSERT TO, y VALUES, con signos de interrogacion en los valores de las columnas
            // y a continuación les dadamos el valor
            final String SQL = "INSERT INTO usuario(username, password, intentosFallidos, correoElectronico) VALUES (?,?,?,?)";


            PreparedStatement ps = conexion.getConexion().prepareStatement(SQL);

            // ingresamos los valores con Set, segun el lugar del signo,
            // y un get del Usuario recibido según corresponda
            ps.setString(1,u.getUsername());
            ps.setString(2,u.getPassword());
            ps.setInt(3,u.getIntentosFallidos());
            ps.setString(4,u.getCorreoElectronico());

            // Ejecutamos el almacenamiento
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Obetener todos los ususarios, retornamos una List de Usuario,
    public List<Usuario> obtenerAll() throws SinConexionException {

        // Creo mi List y la inicializo como una ArrayList
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {

            // Selecciono todas las columnas, de la tabla usuario
            final String SQL = "SELECT * FROM usuario";

            // Creo mi PreparedStatement, con la conexion con mi SQL
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(SQL);

            // en el ResultSet ejecuto la Query del ps.
            ResultSet rs = ps.executeQuery();

            // Mientras rs, siga teniendo respuestas, entonces
            while (rs.next()){

                // creo un nuevo usuario SIN PARAMETROS
                Usuario user = new Usuario();

                // Asigno sus parametros al objeto previamente creado
                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setIntentosFallidos(rs.getInt(3));
                user.setCorreoElectronico(rs.getString(4));

                // añado mi usuario con sus atributos ya ingresados en mi list
                usuarios.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    // Eliminar un usuario según su Username, no retorno nada, recibo un String (Username del usuario)
    public void eliminarUsuarioxUsername(String u) throws SinConexionException {
        try {

            // Elimino todas las columnas de la tabla usuario, donde el Username
            // sea igual a ? (valor ingresado a contiuacion)
            final String SQL = "DELETE * FROM usuario WHERE username = ?";

            // creo un Statement, conecto y creo Statement
            Statement sentenciaDelete  = this.conexion.getConexion().createStatement();

            // variable resultado de esto, que sería la variable del
            // Statement ejecutando la modificacion del SQL.
            int resultadoDelete = sentenciaDelete.executeUpdate(SQL);

            // creo un PS, del SQL
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(SQL);

            // le asigno valor a mi '?' que en este caso será el String que nos entregan (u)
            ps.setString(1,u);

            // ejecuto la modificacion
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
