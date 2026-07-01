<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.morales_picture_web.modelo.Cliente"%>
<%
    Cliente cliente = (Cliente) request.getAttribute("cliente"); // Recibe el cliente enviado desde el servlet.
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Editar cliente - Morales Picture</title>
        <link rel="stylesheet" href="assets/css/estilos.css">
    </head>
    <body>
        <main class="contenedor">
            <section class="encabezado">
                <div>
                    <p class="marca">Morales Picture</p>
                    <h1>Editar Cliente</h1>
                </div>
                <a class="boton boton-secundario" href="ClienteServlet?accion=listar">Volver</a>
            </section>

            <section class="panel formulario-panel">
                <%
                    String error = (String) request.getAttribute("error"); // Recibe un posible error enviado por el servlet.
                    if (error != null) { // Verifica si existe error para mostrar.
                %>
                <p class="mensaje-error"><%= error%></p>
                <%
                    } // Cierra la validación del error.
                %>
                <%
                    if (cliente != null) { // Verifica si el cliente existe antes de mostrar el formulario.
                %>
                <form action="ClienteServlet" method="post" class="formulario">
                    <input type="hidden" name="accion" value="actualizar">
                    <input type="hidden" name="id" value="<%= cliente.getId()%>">

                    <label for="nombre">Nombre completo</label>
                    <input type="text" id="nombre" name="nombre" value="<%= cliente.getNombre()%>" required>

                    <label for="telefono">Teléfono</label>
                    <input type="text" id="telefono" name="telefono" value="<%= cliente.getTelefono()%>" required>

                    <label for="correo">Correo electrónico</label>
                    <input type="email" id="correo" name="correo" value="<%= cliente.getCorreo()%>" required>

                    <label for="servicio">Servicio fotográfico</label>
                    <input type="text" id="servicio" name="servicio" value="<%= cliente.getServicio()%>" required>

                    <button class="boton boton-principal" type="submit">Actualizar cliente</button>
                </form>
                <%
                    } else { // Si el cliente no existe, muestra un mensaje.
                %>
                <p class="sin-datos">No se encontró el cliente solicitado.</p>
                <%
                    } // Cierra la condición.
                %>
            </section>
        </main>
    </body>
</html>
