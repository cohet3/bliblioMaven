package com.corenetworks.persistencia;

import com.corenetworks.modelo.Tienda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccesoVentas extends Conexion{
    //ALTAS/BAJAS/CAMBIOS/CONSULTAS
    public boolean insertar(Tienda c) throws SQLException, ClassNotFoundException {

        //1.Declarar variables
        PreparedStatement sentencia;
        int resultado = 0;
        String sql = "insert into tiendas ( direccion, ventas) values (?,?);";
        //2. Abrir la conexion
        abrirConexion();
        //3. Crear el Statement
        sentencia = miConexion.prepareStatement(sql);
        sentencia.setString(1,c.getDireccion());
        sentencia.setBigDecimal(2, c.getVentas());

        //4. Consultar
        resultado = sentencia.executeUpdate();

        return resultado>0;

    }

    public boolean eliminar(int id) throws SQLException, ClassNotFoundException {
        //1.Declarar variables
        Statement sentencia;
        int resultado = 0;
        String sql = "Delete from tiendas where tienda_id=" + id;
        //2. Abrir la conexion
        abrirConexion();
        //3. Crear el Statement
        sentencia = miConexion.createStatement();
        //4. Eliminar
        resultado = sentencia.executeUpdate(sql);
        //5. Devolver el resultado
        return resultado > 0;
    }

    public boolean modificar(Tienda id) throws SQLException, ClassNotFoundException {

        //1.Declarar variables
        PreparedStatement sentencia;
        int resultado = 0;
        ResultSet rejilla;
        String sql = "update ventas set direccion = ? where customer_id=?";
        //2. Abrir la conexion
        abrirConexion();
        //3. Crear el Statement
        sentencia = miConexion.prepareStatement(sql);
        sentencia.setString(1, id.getDireccion());
        sentencia.setBigDecimal(2,id.getVentas());


        //4. Consultar
        resultado = sentencia.executeUpdate();

        return resultado>0;

    }
    public Tienda obtenerUno(int id) throws SQLException, ClassNotFoundException {

        //1.Declarar variables
        PreparedStatement sentencia;
        Tienda resultado = null;
        ResultSet rejilla;
        String sql = "Select tienda_id, direccion, ventas from tiendas where tienda_id = ?";
        //2. Abrir la conexion
        abrirConexion();
        //3. Crear el Statement
        sentencia = miConexion.prepareStatement(sql);
        sentencia.setInt(1, id);
        //4. Consultar
        rejilla = sentencia.executeQuery();
        //5. recoger los valores
        if (rejilla.next()) {
            String valorMoney = rejilla.getString("ventas");
            System.out.println(valorMoney);
            //realizar los ajustes al formato , por ejemplo, quitar el símbolo de euro y el separador de miles
            valorMoney = valorMoney.replace("€", "").replace(".", "").replace(",", ".");
            System.out.println(valorMoney);
            resultado = new Tienda(rejilla.getInt("tienda_id")
                    , rejilla.getString("direccion")
                    , rejilla.getBigDecimal("ventas"));
        }

        //5. Devolver el resultado
        return resultado;

    }

    public List<Tienda> obtenerTodos() throws SQLException, ClassNotFoundException {

        //1.Declarar variables
        Statement sentencia;
        List<Tienda> clientes = new ArrayList<>();
        ResultSet rejilla;
        String sql = "Select tienda_id, direccion, ventas from tiendas";
        //2. Abrir la conexion
        abrirConexion();
        //3. Crear el Statement
        sentencia = miConexion.createStatement();
        //4. Consultar
        rejilla = sentencia.executeQuery(sql);
        //5. recoger los valores
        while (rejilla.next()) {
            clientes.add( new Tienda(rejilla.getInt("tienda_id")
                    , rejilla.getString("direccion")
                    , rejilla.getBigDecimal("ventas")));
        }

        //5. Devolver el resultado
        return clientes;

    }


}
