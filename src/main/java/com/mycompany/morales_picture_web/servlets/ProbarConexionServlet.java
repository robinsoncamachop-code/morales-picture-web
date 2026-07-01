package com.mycompany.morales_picture_web.servlets; // Define el paquete de servlets.

import com.mycompany.morales_picture_web.conexion.ConexionBD; // Importa la clase que conecta con MySQL.
import java.io.IOException; // Importa errores de entrada y salida.
import java.sql.SQLException; // Importa errores de conexión SQL.
import jakarta.servlet.ServletException; // Importa errores propios de servlets en Tomcat 11.
import jakarta.servlet.annotation.WebServlet; // Importa la anotación Jakarta para mapear el servlet.
import jakarta.servlet.http.HttpServlet; // Importa la clase base de servlets HTTP.
import jakarta.servlet.http.HttpServletRequest; // Importa el objeto de solicitud HTTP.
import jakarta.servlet.http.HttpServletResponse; // Importa el objeto de respuesta HTTP.

/**
 * Clase: ProbarConexionServlet.
 * Descripción: Permite comprobar desde el navegador si MySQL conecta correctamente.
 */
@WebServlet(name = "ProbarConexionServlet", urlPatterns = {"/ProbarConexionServlet"}) // Publica el servlet en la URL /ProbarConexionServlet.
public class ProbarConexionServlet extends HttpServlet { // Inicia el servlet de prueba de conexión.

    @Override // Indica que se sobrescribe el método doGet.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // Atiende la prueba por método GET.
        try { // Inicia un bloque para probar la conexión.
            boolean conectado = ConexionBD.probarConexion(); // Llama a la clase de conexión para verificar MySQL.
            request.setAttribute("conectado", conectado); // Envía el resultado positivo al JSP.
            request.setAttribute("mensaje", "Conexión exitosa con MySQL."); // Envía un mensaje claro de éxito.
        } catch (SQLException e) { // Captura errores SQL.
            request.setAttribute("conectado", false); // Envía el resultado negativo al JSP.
            request.setAttribute("mensaje", e.getMessage()); // Envía el mensaje exacto del error.
        } // Cierra el bloque de prueba.

        request.getRequestDispatcher("conexion.jsp").forward(request, response); // Muestra el resultado en una página JSP.
    } // Cierra doGet.
} // Cierra la clase ProbarConexionServlet.
