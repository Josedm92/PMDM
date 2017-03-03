/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db4oappstore;

/**
 *
 * @author josed
 */
public class Desarrollador {
    protected String id_desarrollador = "";
    protected String nombre_desarrollador = "";
    
    
    public Desarrollador (String id, String nombre) {
        this.id_desarrollador = id;
        this.nombre_desarrollador = nombre;
    }

    public String getId_desarrollador() {
        return id_desarrollador;
    }

    public void setId_desarrollador(String id_desarrollador) {
        this.id_desarrollador = id_desarrollador;
    }

    public String getNombre_desarrollador() {
        return nombre_desarrollador;
    }

    public void setNombre_desarrollador(String nombre_desarrollador) {
        this.nombre_desarrollador = nombre_desarrollador;
    }  
    
    public String printDesarrollador() {
        return "Id del desarrollador: " + id_desarrollador + ", Nombre: " + nombre_desarrollador;
    }
    
    
}
