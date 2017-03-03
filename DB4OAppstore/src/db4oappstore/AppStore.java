/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db4oappstore;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author josed
 */
public class AppStore {
    final static String DBAppStore = "DBAppStore.yap";
    private String nombre = "AppStore";
    private ArrayList<db4oappstore.Desarrollador> desarrolladores = new ArrayList<db4oappstore.Desarrollador>(); 
    private ArrayList<db4oappstore.Aplicacion> aplicaciones = new ArrayList<db4oappstore.Aplicacion>(); 
    private ArrayList<db4oappstore.Usuario> usuarios = new ArrayList<db4oappstore.Usuario>(); 
    
    
    
    
    //----------------CONSTRUCTORES------------------------
    public AppStore() {
    }
    
    public AppStore(String nombre, ArrayList<Desarrollador> desarrolladores, ArrayList<Aplicacion> aplicaciones, ArrayList<Usuario> Usuarios) {
        this.nombre = nombre;
        this.desarrolladores = desarrolladores;
        this.aplicaciones = aplicaciones;
        this.usuarios = usuarios;
    }
    
    public AppStore(boolean crear) {
        //Añadimos desarrolladores.
        desarrolladores.add(new db4oappstore.Desarrollador("dev001", "Spotify"));
        desarrolladores.add(new db4oappstore.Desarrollador("dev002", "King"));
        desarrolladores.add(new db4oappstore.Desarrollador("dev003", "Apple"));
        desarrolladores.add(new db4oappstore.Desarrollador("dev004", "Google"));
        
        //Añadimos aplicaciones.
        aplicaciones.add(new db4oappstore.Aplicacion("Spotify Music", "Música", desarrolladores.get(0)));
        aplicaciones.add(new db4oappstore.Aplicacion("Candy Crush Saga", "Entretenimiento", desarrolladores.get(1)));
        aplicaciones.add(new db4oappstore.Aplicacion("Candy Crush Soda Saga", "Entretenimiento", desarrolladores.get(1)));
        aplicaciones.add(new db4oappstore.Aplicacion("Keynote", "Productividad", desarrolladores.get(2)));
        aplicaciones.add(new db4oappstore.Aplicacion("Google Chrome", "Utilidades", desarrolladores.get(3)));
        
        //Añadimos usuarios.
        usuarios.add(new db4oappstore.Usuario("josedm92", "Jose Delgado"));
            usuarios.get(0).setAplicacion(aplicaciones.get(0)); //Añadimos la app al array del usuario.
            usuarios.get(0).setAplicacion(aplicaciones.get(3)); //Añadimos la app al array del usuario.
            usuarios.get(0).setAplicacion(aplicaciones.get(4)); //Añadimos la app al array del usuario.
        usuarios.add(new db4oappstore.Usuario("martap89@hotmail.com", "Marta Pérez"));
            usuarios.get(1).setAplicacion(aplicaciones.get(0)); //Añadimos la app al array del usuario.
            usuarios.get(1).setAplicacion(aplicaciones.get(1)); //Añadimos la app al array del usuario.
            usuarios.get(1).setAplicacion(aplicaciones.get(3)); //Añadimos la app al array del usuario.
        usuarios.add(new db4oappstore.Usuario("jorge97@gmail.com", "Jorge Montes"));
            usuarios.get(2).setAplicacion(aplicaciones.get(2)); //Añadimos la app al array del usuario.
            usuarios.get(2).setAplicacion(aplicaciones.get(4)); //Añadimos la app al array del usuario.     
    }
    
    
    //------------------------OPERACIONES-------------------------------
    
    //Leer base de datos.
    public static AppStore leerDeArchivo (ObjectContainer db) throws FileNotFoundException {
            AppStore appstore = new AppStore("AppStore", null, null, null);
            ObjectSet<AppStore> busquedaBD = db.queryByExample(appstore);
            AppStore encontrado = null;
                        
            if(busquedaBD.isEmpty()){
                System.out.println("AppStore no encontrada");
            }
            else{
                while(busquedaBD.hasNext()){
                    encontrado = busquedaBD.next();
                }
            }
            
            return encontrado;
    }

    //Escribir base de datos
    public static void escribirEnArchivo (AppStore appstore, ObjectContainer db) throws FileNotFoundException{
        db.store(appstore);
    }
    
    //Mostrar datos
    public void mostrarUsuarios(){
        for(int i = 0; i < usuarios.size(); ++i){
            System.out.println(i + " - " + usuarios.get(i).printUsuario());
        }
    }
    
    public void mostrarUsuariosYAplicaciones(){
        for(int i = 0; i < this.usuarios.size(); ++i){
            System.out.println(i + " - " + this.usuarios.get(i).printUsuario());
            for(int j = 0; j < this.usuarios.get(i).aplicaciones.size(); ++j){
                System.out.println("   "+ j + " - " + this.usuarios.get(i).aplicaciones.get(j).printAplicacion());
            }
        }
    }

    public void mostrarAplicaciones(){
        for(int i = 0; i < aplicaciones.size(); ++i){
            System.out.println(i + " - " + aplicaciones.get(i).printAplicacion());
        }
    }  

    public void mostrarDesarrolladores(){
        for(int i = 0; i < desarrolladores.size(); ++i){
            System.out.println(i + " - " + desarrolladores.get(i).printDesarrollador());
        }
    }
       
   
    //Búsqueda
    public int buscaUsuario(String email){
        int pos = -1;
        boolean encontrado = false;
        
        for (int i = 0; i<usuarios.size() && encontrado == false; ++i){
            if(usuarios.get(i).getEmail().equals(email)){
                encontrado = true;
                pos = i;
            }
        }
        
        return pos;
    }
    
    public int buscaAplicacion(String nombre){
        int pos = -1;
        boolean encontrado = false;
        
        for (int i = 0; i<aplicaciones.size() && encontrado == false; ++i){
            if(aplicaciones.get(i).getNombre_aplicacion().equals(nombre)){
                encontrado = true;
                pos = i;
            }
        }
        
        return pos;
    }
    
    public int buscaDesarrollador(String id){
        int pos = -1;
        boolean encontrado = false;
        
        for (int i = 0; i<desarrolladores.size() && encontrado == false; ++i){
            if(desarrolladores.get(i).getId_desarrollador().equals(id)){
                encontrado = true;
                pos = i;
            }
        }
        
        return pos;
    }
    
    
    //Operaciones sobre Usuarios
    public void addUsuario(){
        Scanner input = new Scanner(System.in);
        
        System.out.println("Introduzca el email del usuario: ");
        String email = input.nextLine();
        System.out.println("Introduzca el nombre del usuario: ");
        String nombre = input.nextLine();
                
        this.usuarios.add(new db4oappstore.Usuario(email, nombre));
        System.out.println("Desarrollador " + nombre + " añadido correctamente");
    }
        
    public void addAplicacionAUsuario() {
        Scanner input = new Scanner(System.in);
        int pos_usuario = -1;
        int pos_aplicacion = -1;
        String usuario = "", aplicacion = "";
        
        this.mostrarUsuarios();
        do {
            System.out.println("Introduzca el email del usuario al que se le agregará la aplicación: ");
            usuario = input.nextLine();
            pos_usuario = this.buscaUsuario(usuario);
            if (pos_usuario == -1) {
                System.out.println("Usuario no encontrado");
            }
        } while(pos_usuario == -1);     
        
        this.mostrarAplicaciones();
        do {
            System.out.println("Introduzca el nombre de la aplicación: ");
            aplicacion = input.nextLine();
            pos_aplicacion = this.buscaAplicacion(aplicacion);
            if (pos_aplicacion == -1) {
                System.out.println("Aplicacion no encontrada");
            }
        } while(pos_aplicacion == -1);     
        
                
        this.usuarios.get(pos_usuario).setAplicacion(this.aplicaciones.get(pos_aplicacion));
        System.out.println("Aplicacion " + aplicacion + " agregada correctamente al usuario " + usuario);
    }
    
    public boolean modificarUsuario(){
        Scanner input = new Scanner(System.in);
        boolean modificado = false;
        int pos = -1;
        
        System.out.println("Introduzca el email del Usuario a modificar");
        String modificar = input.nextLine();
        
        pos = this.buscaUsuario(modificar);
        if (pos != -1) {
            System.out.println("Se va a modificar el Usuario" + usuarios.get(pos).getNombre());
            System.out.println("\nIntroduzca el nuevo correo electrónico (en blanco: no modificar): ");
            String email = input.nextLine();
            if(email.equals("")){
                email = this.usuarios.get(pos).getEmail();
            }

            System.out.println("Introduzca el nuevo nombre (en blanco: no modificar): ");
            String nombre = input.nextLine();
            if(nombre.equals("")){
                nombre = this.usuarios.get(pos).getNombre();
            }

            this.usuarios.get(pos).setEmail(email);
            this.usuarios.get(pos).setNombre(nombre);

            modificado = true;
        }
        
        return modificado;
    } 
    
    public boolean eliminarUsuario(){
        boolean eliminado = false;
        Scanner input = new Scanner(System.in);
        int pos = -1;
        
        System.out.println("Introduzca el email del Usuario a eliminar");
        String eliminar = input.nextLine();
        
        pos = this.buscaUsuario(eliminar);
        if (pos != -1) {
            this.usuarios.remove(pos);
            eliminado = true;
        }
            
        return eliminado;
    }    
    
    
    
    //Operaciones sobre Aplicaciones
    public void addAplicacion(){
        Scanner input = new Scanner(System.in);
        int pos = -1;
        
        System.out.println("Introduzca el nombre de la Aplicación: ");
        String nombre = input.nextLine();
        System.out.println("Introduzca la categoría de la Aplicación: ");
        String categoria = input.nextLine();
        
        do {
            System.out.println("Introduzca el Id del desarrollador: ");
            String desarrollador = input.nextLine();
            pos = this.buscaDesarrollador(desarrollador);
            if (pos == -1) {
                System.out.println("Desarrollador no encontrado");
            }
        } while(pos == -1);     
        
        
        this.aplicaciones.add(new db4oappstore.Aplicacion(nombre, categoria, this.desarrolladores.get(pos)));
        System.out.println("Aplicacion " + nombre + " añadida correctamente");
    }
    
    public boolean modificarAplicacion(){
        Scanner input = new Scanner(System.in);
        boolean modificado = false;
        int pos = -1;
        
        System.out.println("Introduzca el nombre de la Aplicación a modificar");
        String modificar = input.nextLine();
        
        pos = this.buscaAplicacion(modificar);
        if (pos != -1) {
            System.out.println("Se va a modificar la Aplicación" + aplicaciones.get(pos).getNombre_aplicacion());
            System.out.println("\nIntroduzca el nuevo nombre (en blanco: no modificar): ");
            String nombre = input.nextLine();
            if(nombre.equals("")){
                nombre = this.aplicaciones.get(pos).getNombre_aplicacion();
            }

            System.out.println("Introduzca la nueva categoría (en blanco: no modificar): ");
            String categoria = input.nextLine();
            if(categoria.equals("")){
                categoria = this.aplicaciones.get(pos).getCategoria();
            }

            this.aplicaciones.get(pos).setNombre_aplicacion(nombre);
            this.aplicaciones.get(pos).setCategoria(categoria);

            modificado = true;
        }
        
        return modificado;
    }
    
    public boolean eliminarAplicacion(){
        boolean eliminado = false;
        Scanner input = new Scanner(System.in);
        int pos = -1;
        
        System.out.println("Introduzca el nombre de la Aplicación a eliminar");
        String eliminar = input.nextLine();
        
        pos = this.buscaAplicacion(eliminar);
        if (pos != -1) {
            this.aplicaciones.remove(pos);
            eliminado = true;
        }
            
        return eliminado;
    }
        
 
  
    //Operaciones sobre Desarrolladores
    public void addDesarrollador(){
        Scanner input = new Scanner(System.in);
        
        System.out.println("Introduzca el Id del desarrollador: ");
        String id = input.nextLine();
        System.out.println("Introduzca el nombre del desarrollador: ");
        String nombre = input.nextLine();
                
        this.desarrolladores.add(new db4oappstore.Desarrollador(id, nombre));
        
        System.out.println("Desarrollador " + nombre + " añadido correctamente");
    }
    
    public boolean modificarDesarrollador(){
        Scanner input = new Scanner(System.in);
        boolean modificado = false;
        int pos = -1;
        
        System.out.println("Introduzca el ID del Desarrollador a modificar");
        String modificar = input.nextLine();
        
        pos = this.buscaDesarrollador(modificar);
        if (pos != -1) {
            System.out.println("Se va a modificar el Desarrollador" + desarrolladores.get(pos).getNombre_desarrollador());
            System.out.println("\nIntroduzca el nuevo ID (en blanco: no modificar): ");
            String id = input.nextLine();
            if(id.equals("")){
                id = this.desarrolladores.get(pos).getId_desarrollador();
            }

            System.out.println("Introduzca el nuevo nombre (en blanco: no modificar): ");
            String nombre = input.nextLine();
            if(nombre.equals("")){
                nombre = this.desarrolladores.get(pos).getNombre_desarrollador();
            }

            this.desarrolladores.get(pos).setId_desarrollador(id);
            this.desarrolladores.get(pos).setNombre_desarrollador(nombre);

            modificado = true;
        }
        
        return modificado;
    }
        
    public boolean eliminarDesarrollador(){
        boolean eliminado = false;
        Scanner input = new Scanner(System.in);
        int pos = -1;
        
        System.out.println("Introduzca el ID del Desarrollador a eliminar");
        String eliminar = input.nextLine();
        
        pos = this.buscaDesarrollador(eliminar);
        if (pos != -1) {
            this.desarrolladores.remove(pos);
            eliminado = true;
        }
            
        return eliminado;
    }
        
    
    
    //-------------------------MENÚS-----------------------------------
    public void mostrarMenuMostrarDatos() {
        System.out.println("App Store - Menú Mostrar Datos");
        System.out.println("\nElija una opción:");
        System.out.println("1 - Todos los Usuarios");
        System.out.println("2 - Todos los Usuarios y sus Aplicaciones");
        System.out.println("3 - Todas las Aplicaciones");
        System.out.println("4 - Todos los Desarrolladores");
        System.out.println("5 - Volver atrás");
    }  
    
    public void mostrarMenuDesarrollador() {
        System.out.println("App Store - Menú Desarrolladores");
        System.out.println("\nElija una opción:");
        System.out.println("1 - Añadir un nuevo Desarrollador");
        System.out.println("2 - Modificar un Desarrollador");
        System.out.println("3 - Eliminar un Desarrollador");
        System.out.println("4 - Volver atrás");
    }
    
    public void mostrarMenuAplicacion() {
        System.out.println("App Store - Menú Aplicaciones");
        System.out.println("\nElija una opción:");
        System.out.println("1 - Añadir una nueva Aplicacion");
        System.out.println("2 - Modificar una Aplicacion");
        System.out.println("3 - Eliminar una Aplicacion");
        System.out.println("4 - Volver atrás");
    }
        
    public void mostrarMenuUsuario() {
        System.out.println("App Store - Menú Usuarios");
        System.out.println("\nElija una opción:");
        System.out.println("1 - Añadir un nuevo usuario");
        System.out.println("2 - Añadir una aplicación a la lista de un usuario");
        System.out.println("3 - Modificar un usuario");
        System.out.println("4 - Eliminar un usuario");
        System.out.println("5 - Volver atrás");
    }
    
    public void mostrarMenuPrincipal() {
        System.out.println("App Store - Menú principal");
        System.out.println("\nElija una opción:");
        System.out.println("1 - Mostrar datos de la BD");
        System.out.println("2 - Operaciones sobre Usuarios");
        System.out.println("3 - Operaciones sobre Aplicaciones");
        System.out.println("4 - Operaciones sobre Desarrolladores");
        System.out.println("5 - Salir");
    }
    
    
    public static void main(String[] args) throws FileNotFoundException {
        boolean crear = true, eliminado = false, modificado = false;
        AppStore appstore;
        ObjectContainer db = null;
        File fichero = new File("DBAppStore.yap");
        int entrada = 0; 
        
        
        if(fichero.exists()){
            System.out.println("Cargando base de datos...");
            appstore = new AppStore();
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBAppStore);
            appstore = leerDeArchivo(db);
            System.out.println("\nSe ha cargado la base de datos.");
        }
        else{
            System.out.println("No hay ningúna base de datos creada anteriormente, se procederá a su creación.");
            appstore = new AppStore(crear);
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBAppStore);
            escribirEnArchivo(appstore, db);
            System.out.println("Base de datos creada y guardada correctamente.");
        }
        
        
        while(entrada != -1){
            Scanner input = new Scanner(System.in);
            appstore.mostrarMenuPrincipal();
            switch(input.nextLine()){
                case "1": //Mostrar BD
                    appstore.mostrarMenuMostrarDatos();
                    switch(input.nextLine()){
                        case "1": //usuarios  
                            appstore.mostrarUsuarios();
                            break;
                        case "2": //usuarios y aplicaciones 
                            appstore.mostrarUsuariosYAplicaciones();
                            break;
                        case "3": //aplicaciones  
                            appstore.mostrarAplicaciones();
                            break;
                        case "4": //desarrolladores  
                            appstore.mostrarDesarrolladores();
                            break;
                        case "5": //atrás  
                            break;
                        default:    
                            System.out.println("Opción incorrecta");
                            break;
                    }
                    break;
                case "2": //------------------------------Usuarios-------------------------   
                    appstore.mostrarMenuUsuario();
                    switch(input.nextLine()){
                        case "1": //añadir usuario
                            appstore.addUsuario();
                            escribirEnArchivo(appstore, db);
                            break;
                        case "2": //añadir aplicacion al usuario
                            appstore.addAplicacionAUsuario();
                            escribirEnArchivo(appstore, db);
                            break;
                        case "3": //modificar 
                            appstore.mostrarUsuarios();
                            modificado = appstore.modificarUsuario();
                            if (modificado) {
                                System.out.println("Usuario modificado correctamente.");
                                escribirEnArchivo(appstore, db);
                            } else {
                                System.out.println("Datos incorrectos");
                            }                            
                            break;
                        case "4": //eliminar
                            appstore.mostrarUsuarios();
                            eliminado = appstore.eliminarUsuario();
                            if (eliminado) {
                                System.out.println("Usuario eliminado correctamente.");
                                escribirEnArchivo(appstore, db);
                            } else {
                                System.out.println("Datos incorrectos");
                            }                            
                            break;
                        case "5": //atrás  
                            break;
                        default:    
                            System.out.println("Opción incorrecta");
                            break;
                    }
                    break;
                case "3": //------------------------------Aplicaciones--------------------------------     
                    appstore.mostrarMenuAplicacion();
                    switch(input.nextLine()){
                        case "1": //añadir
                            appstore.addAplicacion();
                            escribirEnArchivo(appstore, db);
                            break;
                        case "2": //modificar 
                            appstore.mostrarAplicaciones();
                            modificado = appstore.modificarAplicacion();
                            if (modificado) {
                                System.out.println("Aplicacion modificada correctamente.");
                                escribirEnArchivo(appstore, db);
                            } else {
                                System.out.println("Datos incorrectos");
                            }                            
                            break;
                        case "3": //eliminar  
                            appstore.mostrarAplicaciones();
                            eliminado = appstore.eliminarAplicacion();
                            if (eliminado) {
                                System.out.println("Aplicación eliminada correctamente.");
                                escribirEnArchivo(appstore, db);
                            } else {
                                System.out.println("Datos incorrectos");
                            }                            
                            break;
                        case "4": //atrás  
                            break;
                        default:    
                            System.out.println("Opción incorrecta");
                            break;
                    }
                    break;
                case "4": //--------------------------Desarrolladores---------------------------------------
                    appstore.mostrarMenuDesarrollador();
                    switch(input.nextLine()){
                        case "1": //añadir  
                            appstore.addDesarrollador();
                            escribirEnArchivo(appstore, db);
                            break;
                        case "2": //modificar 
                            appstore.mostrarDesarrolladores();
                            modificado = appstore.modificarDesarrollador();
                            if (modificado) {
                                System.out.println("Desarrollador modificado correctamente.");
                                escribirEnArchivo(appstore, db);
                            } else {
                                System.out.println("Datos incorrectos");
                            }                            
                            break;
                        case "3": //eliminar  
                            appstore.mostrarDesarrolladores();
                            eliminado = appstore.eliminarDesarrollador();
                            if (eliminado) {
                                System.out.println("Desarrollador eliminado correctamente.");
                                escribirEnArchivo(appstore, db);
                            } else {
                                System.out.println("Datos incorrectos");
                            }                            
                            break;
                        case "4": //atrás  
                            break;
                        default:    
                            System.out.println("Opción incorrecta");
                            break;
                    }
                    break;
                case "5":
                    entrada = -1;
                    break;
                default: 
                    System.out.println("Opción no válida");
        }
         
    }
    escribirEnArchivo(appstore, db);
    db.close();
}
    

}