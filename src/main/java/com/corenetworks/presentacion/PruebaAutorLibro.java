package com.corenetworks.presentacion;

import com.corenetworks.persistencia.AccesoLibro;

import java.sql.SQLException;

public class PruebaAutorLibro {
    public static void main(String[] args) {
        AccesoLibro aL1= new AccesoLibro();

        try {
            System.out.println(aL1.obtenerTodos());
            System.out.println(aL1.obtenerUno("23456"));
            System.out.println(aL1.insertar("56789", "El Quijote"));
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

    }
    // = new CrearInforme();

}
