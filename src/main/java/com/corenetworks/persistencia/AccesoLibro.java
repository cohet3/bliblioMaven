package com.corenetworks.persistencia;

import com.corenetworks.modelo.Autor;
import com.corenetworks.modelo.Libro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccesoLibro extends Conexion{
    //aqui van los metodos para modificar la BBDD


    public Libro obtenerUno(String isbn) throws SQLException, ClassNotFoundException {

        //1.Declarar variables
        PreparedStatement sentencia;
        Libro resultado = null;
        ResultSet rejilla;
        String sql = "Select isbn, titulo from libros where isbn = ?";
        //2. Abrir la conexion
        abrirConexion();
        //3. Crear el Statement
        sentencia = miConexion.prepareStatement(sql);
        sentencia.setString(1, isbn);
        //4. Consultar
        rejilla = sentencia.executeQuery();
        //5. recoger los valores
        if (rejilla.next()) {
            resultado = new Libro(rejilla.getString("isbn")
                    , rejilla.getString("titulo"));
        }

        //5. Devolver el resultado
        return resultado;

    }
    public List<Libro> obtenerTodos() throws SQLException, ClassNotFoundException {

        //1.Declarar variables
        Statement sentencia;
        List<Libro> libros = new ArrayList<>();
        ResultSet rejilla;
        String sql = "Select isbn, titulo from libros";
        //2. Abrir la conexion
        abrirConexion();
        //3. Crear el Statement
        sentencia = miConexion.createStatement();
        //4. Consultar
        rejilla = sentencia.executeQuery(sql);
        //5. recoger los valores
        while (rejilla.next()) {
            libros.add( new Libro(rejilla.getString("isbn")
                    , rejilla.getString("titulo")));
        }

        //5. Devolver el resultado
        return libros;

    }
    public boolean insertar(String isbn, String titulo) throws SQLException, ClassNotFoundException {

        //1.Declarar variables
        PreparedStatement sentencia;
        int resultado = 0;
        String sql = "insert into libros (isbn, titulo) values (?,?);";
        //2. Abrir la conexion
        abrirConexion();
        //3. Crear el Statement
        sentencia = miConexion.prepareStatement(sql);
        sentencia.setString(1, Arrays.toString(isbn.getBytes()));
        sentencia.setString(2, Arrays.toString(titulo.getBytes()));

        //4. Consultar
        resultado = sentencia.executeUpdate();

        return resultado>1;

    }

    public List<Libro> obtenerLibrosPorAutor(String dni) throws SQLException, ClassNotFoundException {
        //1.Declarar variables
        PreparedStatement sentencia;
        List<Libro> resultado = new ArrayList<>();
        ResultSet rejilla;
        String sql = "Select la.isbn, titulo, la.dni from libros l inner join libros_autores la on l.isbn = la.isbn where la.dni = ?";
        //2. Abrir la conexion
        abrirConexion();
        //3. Crear el Statement
        sentencia = miConexion.prepareStatement(sql);
        sentencia.setString(1,dni);
        //4. Consultar
        rejilla = sentencia.executeQuery();
        while (rejilla.next()){
            resultado.add(new Libro(
                    rejilla.getString("isbn"),
                    rejilla.getString("titulo")
            ));
        }
        //5. Devolver el resultado
        return resultado;
    }

    public Autor obtenerUnAutor(String dni) throws SQLException, ClassNotFoundException {
        //1.Declarar variables
        PreparedStatement sentencia;
        Autor resultado = null;
        ResultSet rejilla;
        String sql = "Select nombre from autores where dni=?";
        //2. Abrir la conexion
        abrirConexion();
        //3. Crear el Statement
        sentencia = miConexion.prepareStatement(sql);
        sentencia.setString(1,dni);
        //4. Consultar
        rejilla = sentencia.executeQuery();
        if (rejilla.next()){
            resultado=new Autor(
                    dni,
                    rejilla.getString("nombre")
            );
        }
        //5. Devolver el resultado
        return resultado;
    }



}
