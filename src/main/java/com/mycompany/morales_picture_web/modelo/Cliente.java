package com.mycompany.morales_picture_web.modelo; // Define el paquete del modelo.

/**
 * Clase: Cliente.
 * Descripción: Representa un cliente del estudio fotográfico Morales Picture.
 */
public class Cliente { // Inicia la clase que guarda los datos del cliente.

    private int id; // Identificador único del cliente en la base de datos.
    private String nombre; // Nombre completo del cliente.
    private String telefono; // Teléfono o número de contacto del cliente.
    private String correo; // Correo electrónico del cliente.
    private String servicio; // Servicio fotográfico solicitado por el cliente.

    public Cliente() { // Constructor vacío necesario para formularios, JSP y servlets.
    } // Cierra el constructor vacío.

    public Cliente(int id, String nombre, String telefono, String correo, String servicio) { // Constructor con todos los campos.
        this.id = id; // Asigna el identificador recibido.
        this.nombre = nombre; // Asigna el nombre recibido.
        this.telefono = telefono; // Asigna el teléfono recibido.
        this.correo = correo; // Asigna el correo recibido.
        this.servicio = servicio; // Asigna el servicio recibido.
    } // Cierra el constructor completo.

    public int getId() { // Método que devuelve el id del cliente.
        return id; // Retorna el identificador actual.
    } // Cierra getId.

    public void setId(int id) { // Método que modifica el id del cliente.
        this.id = id; // Guarda el nuevo identificador.
    } // Cierra setId.

    public String getNombre() { // Método que devuelve el nombre.
        return nombre; // Retorna el nombre actual.
    } // Cierra getNombre.

    public void setNombre(String nombre) { // Método que modifica el nombre.
        this.nombre = nombre; // Guarda el nuevo nombre.
    } // Cierra setNombre.

    public String getTelefono() { // Método que devuelve el teléfono.
        return telefono; // Retorna el teléfono actual.
    } // Cierra getTelefono.

    public void setTelefono(String telefono) { // Método que modifica el teléfono.
        this.telefono = telefono; // Guarda el nuevo teléfono.
    } // Cierra setTelefono.

    public String getCorreo() { // Método que devuelve el correo.
        return correo; // Retorna el correo actual.
    } // Cierra getCorreo.

    public void setCorreo(String correo) { // Método que modifica el correo.
        this.correo = correo; // Guarda el nuevo correo.
    } // Cierra setCorreo.

    public String getServicio() { // Método que devuelve el servicio.
        return servicio; // Retorna el servicio actual.
    } // Cierra getServicio.

    public void setServicio(String servicio) { // Método que modifica el servicio.
        this.servicio = servicio; // Guarda el nuevo servicio.
    } // Cierra setServicio.
} // Cierra la clase Cliente.
