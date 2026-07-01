<%-- Página inicial JSP que redirige al listado principal de clientes. --%>
<%
    response.sendRedirect("ClienteServlet?accion=listar"); // Envía al usuario al servlet usando método GET.
%>
