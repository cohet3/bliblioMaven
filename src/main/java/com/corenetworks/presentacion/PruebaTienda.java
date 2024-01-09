package com.corenetworks.presentacion;

import com.corenetworks.modelo.Tienda;
import com.corenetworks.persistencia.AccesoVentas;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

public class PruebaTienda {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        AccesoVentas aV1= new AccesoVentas();
        try {
            //BigDecimal n1 = new BigDecimal(31.90);
            //System.out.println("se dio de alta? "+ aV1.insertar(new Tienda(0, "callePiruleta", n1)));
            System.out.println("Ver uno ->"+aV1.obtenerUno(1));
            System.out.println("Ver todas las tiendas _>"+ aV1.obtenerTodos());
            //System.out.println("Eliminar la tienda ->" + aV1.eliminar(2));
            BigDecimal n1 = new BigDecimal(5.50);
            Tienda t1 = new Tienda(1, "calleRotonda", n1);
            System.out.println(aV1.modificar(t1));

        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }
}
