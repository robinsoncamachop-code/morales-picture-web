package com.mycompany.morales_picture_web.conexion; // Define el paquete donde queda la clase de conexión.

import java.sql.Connection; // Importa la clase que representa una conexión activa con la base de datos.
import java.sql.DriverManager; // Importa el administrador que abre la conexión JDBC.
import java.sql.SQLException; // Importa la excepción que puede ocurrir al trabajar con SQL.

/**
 * Clase: ConexionBD.
 * Descripción: Centraliza la conexión de Morales Picture con MySQL.
 */
public class ConexionBD { // Inicia la clase pública que será usada por el DAO.

    private static final String URL = "jdbc:mysql://localhost:3306/morales_picture_db?useSSL=false&serverTimezone=UTC"; // Ruta de la base de datos MySQL.
    private static final String USUARIO = "root"; // Usuario de MySQL; cámbialo si tu usuario es diferente.
    private static final String CLAVE = "123456"; // Contraseña de MySQL; escribe aquí tu clave si tienes una.

    public static Connection obtenerConexion() throws SQLException { // Método público que entrega una conexión lista para usar.
        try { // Inicia un bloque para cargar manualmente el controlador JDBC.
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carga el driver oficial de MySQL Connector/J.
        } catch (ClassNotFoundException e) { // Captura el error si el driver no está disponible.
            throw new SQLException("No se encontró el driver de MySQL en el proyecto.", e); // Convierte el error a SQLException para mostrarlo mejor.
        } // Cierra el bloque de carga del driver.
        return DriverManager.getConnection(URL, USUARIO, CLAVE); // Abre y devuelve la conexión usando los datos anteriores.
    } // Cierra el método obtenerConexion.

    public static boolean probarConexion() throws SQLException { // Método que permite verificar si MySQL responde.
        try (Connection con = obtenerConexion()) { // Abre una conexión temporal y la cierra automáticamente.
            return con != null && !con.isClosed(); // Devuelve true si la conexión existe y está abierta.
        } // Cierra el try con recursos.
    } // Cierra el método probarConexion.
} // Cierra la clase ConexionBD.
