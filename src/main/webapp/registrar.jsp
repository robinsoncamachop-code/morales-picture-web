<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registrar cliente - Morales Picture</title>
        <link rel="stylesheet" href="assets/css/estilos.css">
    </head>
    <body>
        <main class="contenedor">
            <section class="encabezado">
                <div>
                    <p class="marca">Morales Picture</p>
                    <h1>Registrar Cliente</h1>
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
                <form action="ClienteServlet" method="post" class="formulario">
                    <input type="hidden" name="accion" value="agregar">

                    <label for="nombre">Nombre completo</label>
                    <input type="text" id="nombre" name="nombre" placeholder="Ej: Laura Martínez" required>

                    <label for="telefono">Teléfono</label>
                    <input type="text" id="telefono" name="telefono" placeholder="Ej: 3001234567" required>

                    <label for="correo">Correo electrónico</label>
                    <input type="email" id="correo" name="correo" placeholder="cliente@correo.com" required>

                    <label for="servicio">Servicio fotográfico</label>
                    <select id="servicio" name="servicio" required>
                        <option value="">Selecciona un servicio</option>
                        <option value="Sesión familiar">Sesión familiar</option>
                        <option value="Fotografía de producto">Fotografía de producto</option>
                        <option value="Evento social">Evento social</option>
                        <option value="Retrato profesional">Retrato profesional</option>
                    </select>

                    <button class="boton boton-principal" type="submit">Guardar cliente</button>
                </form>
            </section>
        </main>
    </body>
</html>
