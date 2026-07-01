package com.mycompany.morales_picture_web.dao; // Define el paquete del DAO.

import com.mycompany.morales_picture_web.conexion.ConexionBD; // Importa la clase que abre la conexión a MySQL.
import com.mycompany.morales_picture_web.modelo.Cliente; // Importa el modelo Cliente.
import java.sql.Connection; // Importa la conexión JDBC.
import java.sql.PreparedStatement; // Importa sentencias SQL parametrizadas.
import java.sql.ResultSet; // Importa el resultado de las consultas.
import java.sql.SQLException; // Importa errores SQL.
import java.util.ArrayList; // Importa una lista modificable.
import java.util.List; // Importa la interfaz de listas.

/**
 * Clase: ClienteDAO.
 * Descripción: Maneja las operaciones CRUD de clientes en MySQL.
 */
public class ClienteDAO { // Inicia la clase encargada de hablar con la base de datos.

    public List<Cliente> listar() { // Método que consulta todos los clientes registrados.
        List<Cliente> lista = new ArrayList<>(); // Crea una lista vacía donde se guardarán los clientes.
        String sql = "SELECT id, nombre, telefono, correo, servicio FROM cliente ORDER BY id DESC"; // Consulta SQL para traer clientes.

        try (Connection con = ConexionBD.obtenerConexion(); // Abre la conexión con MySQL.
             PreparedStatement ps = con.prepareStatement(sql); // Prepara la consulta SQL.
             ResultSet rs = ps.executeQuery()) { // Ejecuta la consulta y recibe los resultados.

            while (rs.next()) { // Recorre cada fila encontrada en la tabla.
                Cliente cliente = new Cliente(); // Crea un objeto Cliente vacío.
                cliente.setId(rs.getInt("id")); // Copia el id desde la base de datos.
                cliente.setNombre(rs.getString("nombre")); // Copia el nombre desde la base de datos.
                cliente.setTelefono(rs.getString("telefono")); // Copia el teléfono desde la base de datos.
                cliente.setCorreo(rs.getString("correo")); // Copia el correo desde la base de datos.
                cliente.setServicio(rs.getString("servicio")); // Copia el servicio desde la base de datos.
                lista.add(cliente); // Agrega el cliente a la lista.
            } // Cierra el recorrido de filas.
        } catch (SQLException e) { // Captura cualquier error de conexión o consulta.
            throw new RuntimeException("Error al listar clientes: " + e.getMessage(), e); // Envía el error al servlet para mostrarlo.
        } // Cierra el bloque try-catch.

        return lista; // Devuelve la lista de clientes al servlet.
    } // Cierra el método listar.

    public Cliente buscarPorId(int id) { // Método que busca un cliente por su identificador.
        Cliente cliente = null; // Declara el cliente que se devolverá.
        String sql = "SELECT id, nombre, telefono, correo, servicio FROM cliente WHERE id = ?"; // Consulta SQL con parámetro.

        try (Connection con = ConexionBD.obtenerConexion(); // Abre la conexión con MySQL.
             PreparedStatement ps = con.prepareStatement(sql)) { // Prepara la consulta.

            ps.setInt(1, id); // Coloca el id recibido en el primer signo de pregunta.

            try (ResultSet rs = ps.executeQuery()) { // Ejecuta la consulta.
                if (rs.next()) { // Verifica si encontró un cliente.
                    cliente = new Cliente(); // Crea el objeto cliente.
                    cliente.setId(rs.getInt("id")); // Asigna el id encontrado.
                    cliente.setNombre(rs.getString("nombre")); // Asigna el nombre encontrado.
                    cliente.setTelefono(rs.getString("telefono")); // Asigna el teléfono encontrado.
                    cliente.setCorreo(rs.getString("correo")); // Asigna el correo encontrado.
                    cliente.setServicio(rs.getString("servicio")); // Asigna el servicio encontrado.
                } // Cierra la validación de resultado.
            } // Cierra el ResultSet.
        } catch (SQLException e) { // Captura errores de SQL.
            throw new RuntimeException("Error al buscar cliente: " + e.getMessage(), e); // Envía el error al servlet para mostrarlo.
        } // Cierra el bloque try-catch.

        return cliente; // Devuelve el cliente encontrado o null.
    } // Cierra el método buscarPorId.

    public boolean agregar(Cliente cliente) { // Método que inserta un nuevo cliente.
        String sql = "INSERT INTO cliente (nombre, telefono, correo, servicio) VALUES (?, ?, ?, ?)"; // Sentencia SQL de inserción.

        try (Connection con = ConexionBD.obtenerConexion(); // Abre la conexión con MySQL.
             PreparedStatement ps = con.prepareStatement(sql)) { // Prepara la sentencia SQL.

            ps.setString(1, cliente.getNombre()); // Coloca el nombre en el primer parámetro.
            ps.setString(2, cliente.getTelefono()); // Coloca el teléfono en el segundo parámetro.
            ps.setString(3, cliente.getCorreo()); // Coloca el correo en el tercer parámetro.
            ps.setString(4, cliente.getServicio()); // Coloca el servicio en el cuarto parámetro.
            return ps.executeUpdate() > 0; // Ejecuta la inserción y devuelve true si insertó una fila.
        } catch (SQLException e) { // Captura errores de SQL.
            throw new RuntimeException("Error al agregar cliente: " + e.getMessage(), e); // Envía el error al servlet para mostrarlo.
        } // Cierra el bloque try-catch.
    } // Cierra el método agregar.

    public boolean actualizar(Cliente cliente) { // Método que actualiza un cliente existente.
        String sql = "UPDATE cliente SET nombre = ?, telefono = ?, correo = ?, servicio = ? WHERE id = ?"; // Sentencia SQL de actualización.

        try (Connection con = ConexionBD.obtenerConexion(); // Abre la conexión con MySQL.
             PreparedStatement ps = con.prepareStatement(sql)) { // Prepara la sentencia SQL.

            ps.setString(1, cliente.getNombre()); // Coloca el nuevo nombre.
            ps.setString(2, cliente.getTelefono()); // Coloca el nuevo teléfono.
            ps.setString(3, cliente.getCorreo()); // Coloca el nuevo correo.
            ps.setString(4, cliente.getServicio()); // Coloca el nuevo servicio.
            ps.setInt(5, cliente.getId()); // Coloca el id del cliente que se va a modificar.
            return ps.executeUpdate() > 0; // Ejecuta la actualización y devuelve true si modificó una fila.
        } catch (SQLException e) { // Captura errores de SQL.
            throw new RuntimeException("Error al actualizar cliente: " + e.getMessage(), e); // Envía el error al servlet para mostrarlo.
        } // Cierra el bloque try-catch.
    } // Cierra el método actualizar.

    public boolean eliminar(int id) { // Método que elimina un cliente por id.
        String sql = "DELETE FROM cliente WHERE id = ?"; // Sentencia SQL de eliminación.

        try (Connection con = ConexionBD.obtenerConexion(); // Abre la conexión con MySQL.
             PreparedStatement ps = con.prepareStatement(sql)) { // Prepara la sentencia SQL.

            ps.setInt(1, id); // Coloca el id del cliente que se va a eliminar.
            return ps.executeUpdate() > 0; // Ejecuta la eliminación y devuelve true si borró una fila.
        } catch (SQLException e) { // Captura errores de SQL.
            throw new RuntimeException("Error al eliminar cliente: " + e.getMessage(), e); // Envía el error al servlet para mostrarlo.
        } // Cierra el bloque try-catch.
    } // Cierra el método eliminar.
} // Cierra la clase ClienteDAO.
