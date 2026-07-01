package com.mycompany.morales_picture_web.servlets; // Define el paquete de servlets.

import com.mycompany.morales_picture_web.dao.ClienteDAO; // Importa el DAO que realiza el CRUD.
import com.mycompany.morales_picture_web.modelo.Cliente; // Importa el modelo Cliente.
import java.io.IOException; // Importa errores de entrada y salida.
import jakarta.servlet.ServletException; // Importa errores propios de servlets en Tomcat 11.
import jakarta.servlet.annotation.WebServlet; // Importa la anotación Jakarta para mapear el servlet.
import jakarta.servlet.http.HttpServlet; // Importa la clase base de servlets HTTP en Jakarta.
import jakarta.servlet.http.HttpServletRequest; // Importa el objeto de solicitud HTTP en Jakarta.
import jakarta.servlet.http.HttpServletResponse; // Importa el objeto de respuesta HTTP en Jakarta.

/**
 * Clase: ClienteServlet.
 * Descripción: Controla las acciones web para registrar, listar, editar y eliminar clientes.
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"}) // Publica el servlet en la URL /ClienteServlet.
public class ClienteServlet extends HttpServlet { // Inicia el servlet que atiende peticiones GET y POST.

    private final ClienteDAO clienteDAO = new ClienteDAO(); // Crea el DAO para reutilizarlo en las acciones.

    @Override // Indica que se sobrescribe el método doGet de HttpServlet.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // Atiende solicitudes GET.
        String accion = request.getParameter("accion"); // Lee la acción enviada por la URL.

        if (accion == null) { // Verifica si no llegó ninguna acción.
            accion = "listar"; // Usa listar como acción por defecto.
        } // Cierra la validación de acción nula.

        switch (accion) { // Evalúa la acción solicitada.
            case "nuevo": // Caso para mostrar formulario de registro.
                request.getRequestDispatcher("registrar.jsp").forward(request, response); // Envía al JSP de registro.
                break; // Termina este caso.
            case "editar": // Caso para mostrar formulario de edición.
                mostrarFormularioEditar(request, response); // Llama al método que carga el cliente.
                break; // Termina este caso.
            case "eliminar": // Caso para eliminar un cliente.
                eliminarCliente(request, response); // Llama al método que elimina.
                break; // Termina este caso.
            default: // Caso por defecto.
                listarClientes(request, response); // Lista los clientes registrados.
                break; // Termina este caso.
        } // Cierra el switch.
    } // Cierra doGet.

    @Override // Indica que se sobrescribe el método doPost de HttpServlet.
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // Atiende formularios enviados por POST.
        request.setCharacterEncoding("UTF-8"); // Permite recibir tildes y caracteres especiales.
        String accion = request.getParameter("accion"); // Lee la acción enviada desde el formulario.

        if ("actualizar".equals(accion)) { // Verifica si el formulario pide actualizar.
            actualizarCliente(request, response); // Llama al método de actualización.
        } else { // Si no es actualizar, se considera registro nuevo.
            agregarCliente(request, response); // Llama al método de registro.
        } // Cierra la decisión de acción POST.
    } // Cierra doPost.

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // Método que prepara el listado.
        try { // Inicia un bloque para controlar errores de base de datos.
            request.setAttribute("clientes", clienteDAO.listar()); // Guarda la lista de clientes para usarla en JSP.
        } catch (RuntimeException e) { // Captura errores enviados por el DAO.
            request.setAttribute("error", e.getMessage()); // Envía el mensaje de error a la página JSP.
        } // Cierra el bloque de control de errores.
        request.getRequestDispatcher("listar.jsp").forward(request, response); // Muestra la página de listado.
    } // Cierra listarClientes.

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // Método que prepara el formulario de edición.
        int id = Integer.parseInt(request.getParameter("id")); // Convierte el id recibido por URL a número.
        try { // Inicia un bloque para controlar errores de base de datos.
            Cliente cliente = clienteDAO.buscarPorId(id); // Busca el cliente en la base de datos.
            request.setAttribute("cliente", cliente); // Envía el cliente encontrado al JSP.
        } catch (RuntimeException e) { // Captura errores enviados por el DAO.
            request.setAttribute("error", e.getMessage()); // Envía el mensaje de error a la página JSP.
        } // Cierra el bloque de control de errores.
        request.getRequestDispatcher("editar.jsp").forward(request, response); // Muestra la página de edición.
    } // Cierra mostrarFormularioEditar.

    private void agregarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException { // Método que registra un cliente nuevo.
        try { // Inicia un bloque para controlar errores de registro.
            Cliente cliente = leerClienteDesdeFormulario(request); // Construye el cliente con los datos del formulario.
            clienteDAO.agregar(cliente); // Guarda el cliente en MySQL.
            response.sendRedirect("ClienteServlet?accion=listar"); // Redirige al listado para ver el resultado.
        } catch (RuntimeException e) { // Captura errores enviados por el DAO.
            request.setAttribute("error", e.getMessage()); // Envía el error al formulario.
            try { // Inicia el bloque para reenviar a JSP.
                request.getRequestDispatcher("registrar.jsp").forward(request, response); // Regresa al formulario mostrando el error.
            } catch (ServletException ex) { // Captura errores del reenvío.
                throw new IOException(ex); // Convierte el error para que el método pueda reportarlo.
            } // Cierra el bloque de reenvío.
        } // Cierra el bloque de control de errores.
    } // Cierra agregarCliente.

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException { // Método que modifica un cliente.
        try { // Inicia un bloque para controlar errores de actualización.
            Cliente cliente = leerClienteDesdeFormulario(request); // Construye el cliente con los datos del formulario.
            cliente.setId(Integer.parseInt(request.getParameter("id"))); // Agrega el id del cliente que se actualiza.
            clienteDAO.actualizar(cliente); // Actualiza el cliente en MySQL.
            response.sendRedirect("ClienteServlet?accion=listar"); // Redirige al listado actualizado.
        } catch (RuntimeException e) { // Captura errores enviados por el DAO.
            request.setAttribute("error", e.getMessage()); // Envía el error al formulario.
            try { // Inicia el bloque para reenviar a JSP.
                request.getRequestDispatcher("editar.jsp").forward(request, response); // Regresa al formulario mostrando el error.
            } catch (ServletException ex) { // Captura errores del reenvío.
                throw new IOException(ex); // Convierte el error para que el método pueda reportarlo.
            } // Cierra el bloque de reenvío.
        } // Cierra el bloque de control de errores.
    } // Cierra actualizarCliente.

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException { // Método que elimina un cliente.
        int id = Integer.parseInt(request.getParameter("id")); // Convierte el id recibido por URL a número.
        try { // Inicia un bloque para controlar errores de eliminación.
            clienteDAO.eliminar(id); // Elimina el cliente en MySQL.
            response.sendRedirect("ClienteServlet?accion=listar"); // Redirige al listado actualizado.
        } catch (RuntimeException e) { // Captura errores enviados por el DAO.
            response.sendRedirect("ClienteServlet?accion=listar&error=" + e.getMessage()); // Regresa al listado con el error.
        } // Cierra el bloque de control de errores.
    } // Cierra eliminarCliente.

    private Cliente leerClienteDesdeFormulario(HttpServletRequest request) { // Método auxiliar para leer campos repetidos.
        Cliente cliente = new Cliente(); // Crea un cliente vacío.
        cliente.setNombre(request.getParameter("nombre")); // Lee el nombre enviado desde el formulario.
        cliente.setTelefono(request.getParameter("telefono")); // Lee el teléfono enviado desde el formulario.
        cliente.setCorreo(request.getParameter("correo")); // Lee el correo enviado desde el formulario.
        cliente.setServicio(request.getParameter("servicio")); // Lee el servicio enviado desde el formulario.
        return cliente; // Devuelve el cliente armado.
    } // Cierra leerClienteDesdeFormulario.
} // Cierra la clase ClienteServlet.
