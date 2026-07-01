<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Boolean conectado = (Boolean) request.getAttribute("conectado"); // Recibe si la conexión fue exitosa o no.
    String mensaje = (String) request.getAttribute("mensaje"); // Recibe el mensaje enviado por el servlet.
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Prueba de conexión - Morales Picture</title>
        <link rel="stylesheet" href="assets/css/estilos.css">
    </head>
    <body>
        <main class="contenedor">
            <section class="encabezado">
                <div>
                    <p class="marca">Morales Picture</p>
                    <h1>Prueba de Conexión</h1>
                </div>
                <a class="boton boton-secundario" href="ClienteServlet?accion=listar">Volver</a>
            </section>

            <section class="panel">
                <p class="<%= Boolean.TRUE.equals(conectado) ? "mensaje-ok" : "mensaje-error"%>">
                    <%= mensaje%>
                </p>
            </section>
        </main>
    </body>
</html>
