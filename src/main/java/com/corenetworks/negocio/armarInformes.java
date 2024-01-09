package com.corenetworks.negocio;

import com.corenetworks.modelo.Autor;
import com.corenetworks.modelo.Libro;
import com.corenetworks.persistencia.AccesoLibro;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class armarInformes {
    public void armarInforme(String dni) throws IOException, SQLException, ClassNotFoundException {
        try(FileWriter fSalida= new FileWriter("libros_por_autor.txt");
            PrintWriter altoNivel= new PrintWriter(fSalida)){
            //1.Declarar variables
            AccesoLibro acl = new AccesoLibro();
            List<Libro> libros;
            //1.Obtenemos los datos del autor
            Autor a1 =acl.obtenerUnAutor(dni);
            //2. Arma el cabecero
            altoNivel.format("Nombre del autor: %s DNI: %s %n %n", a1.getDni(), a1.getNombre());
            altoNivel.format("%-20s %s %n", "ISBN", "TITULO");
            altoNivel.println("-".repeat(50)+"\n");
            libros= acl.obtenerLibrosPorAutor(dni);
            //realiza el detalle
            for (Libro elemento:
            libros){
                altoNivel.format("%-20s %s %n", elemento.getIsbn(), elemento.getTitulo());
            }
        }
    }
}
