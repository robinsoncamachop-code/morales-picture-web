<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.morales_picture_web.modelo.Cliente"%>
<%
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes"); // Recibe desde el servlet la lista de clientes.
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Clientes registrados - Morales Picture</title>
        <link rel="stylesheet" href="assets/css/estilos.css">
    </head>
    <body>
        <main class="contenedor">
            <section class="encabezado">
                <div>
                    <p class="marca">Morales Picture</p>
                    <h1>Clientes Registrados</h1>
                </div>
                <a class="boton boton-principal" href="ClienteServlet?accion=nuevo">Nuevo cliente</a>
            </section>

            <section class="panel">
                <%
                    String error = (String) request.getAttribute("error"); // Recibe un posible error enviado por el servlet.
                    if (error != null) { // Verifica si existe error para mostrar.
                %>
                <p class="mensaje-error"><%= error%></p>
                <%
                    } // Cierra la validación del error.
                %>
                <table class="tabla-clientes">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Teléfono</th>
                            <th>Correo</th>
                            <th>Servicio</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (clientes != null && !clientes.isEmpty()) { // Verifica si hay clientes para mostrar.
                                for (Cliente cliente : clientes) { // Recorre la lista de clientes.
                        %>
                        <tr>
                            <td><%= cliente.getId()%></td>
                            <td><%= cliente.getNombre()%></td>
                            <td><%= cliente.getTelefono()%></td>
                            <td><%= cliente.getCorreo()%></td>
                            <td><%= cliente.getServicio()%></td>
                            <td class="acciones">
                                <a class="boton boton-secundario" href="ClienteServlet?accion=editar&id=<%= cliente.getId()%>">Editar</a>
                                <a class="boton boton-peligro" href="ClienteServlet?accion=eliminar&id=<%= cliente.getId()%>" onclick="return confirm('¿Deseas eliminar este cliente?')">Eliminar</a>
                            </td>
                        </tr>
                        <%
                                } // Cierra el recorrido de clientes.
                            } else { // Si no hay clientes, muestra un mensaje.
                        %>
                        <tr>
                            <td colspan="6" class="sin-datos">No hay clientes registrados todavía.</td>
                        </tr>
                        <%
                            } // Cierra la condición de clientes vacíos.
                        %>
                    </tbody>
                </table>
            </section>
        </main>
    </body>
</html>
