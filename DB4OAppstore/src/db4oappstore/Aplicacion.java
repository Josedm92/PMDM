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
public class Aplicacion extends Desarrollador {
    protected String nombre_aplicacion = "";
    protected String categoria = "";

    public Aplicacion(String nombre, String categoria, Desarrollador desarrollador) {
        super(desarrollador.id_desarrollador, desarrollador.nombre_desarrollador);
        this.nombre_aplicacion = nombre;
        this.categoria = categoria;
    }

    public String getNombre_aplicacion() {
        return nombre_aplicacion;
    }

    public void setNombre_aplicacion(String nombre_aplicacion) {
        this.nombre_aplicacion = nombre_aplicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public String printAplicacion() {
        return "nombre: " + nombre_aplicacion + ", Categoría de la aplicación: " + categoria;
    }
    
}
