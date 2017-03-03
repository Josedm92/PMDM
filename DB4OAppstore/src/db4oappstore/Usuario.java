/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db4oappstore;

import java.util.ArrayList;

/**
 *
 * @author josed
 */
public class Usuario {
    protected String email;
    protected String nombre;
    public ArrayList<Aplicacion> aplicaciones = new ArrayList<Aplicacion>();

    public Usuario(String email, String nombre) {
        this.email = email;
        this.nombre = nombre;
    }  
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        aplicaciones.add(aplicacion);
    }
    
    public ArrayList<Aplicacion> getAplicaciones(){
        return this.aplicaciones;
    }
    
    public String printUsuario() {
        return "Email: " + email + ", Nombre = " + nombre;
    }
      
}
