package edu.academik.telus.jdbc.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mario Batres
 */
public class Main {

    private static void ejecutarQuery1(Connection conn) throws SQLException {

        String queryString = "select * from cliente";

        // try with resource ---> el va  a cerrar el stmt
        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {

            try (ResultSet rs = stmt.executeQuery()) {

                int totalColumnas = rs.getMetaData().getColumnCount();

                while (rs.next()) {

                    for (int i = 1; i <= totalColumnas; i++) {

                        System.out.print(rs.getObject(i));

                        System.out.print("\t");
                    }

                    System.out.println();
                }
            }
        }
    }

    private static void ejecutarQuery2(Connection conn) throws SQLException {

        String queryString = "select tipo, count(*) total from cliente group by tipo order by 1";

        // try with resource ---> el va  a cerrar el stmt
        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int tipo = rs.getInt("tipo");

                    int total = rs.getInt("total");

                    System.out.println("tipo = " + tipo + ", total = " + total);
                }
            }
        }
    }

    private static void ejecutarQuery3(Connection conn, Integer clienteId) throws SQLException {

        String queryString = "select \n"
                + "	c.cliente_id,\n"
                + "	c.nombre cliente_nombre,\n"
                + "	v.venta_id, \n"
                + "	v.fecha_factura,\n"
                + "	v.numero \n"
                + "from venta v \n"
                + "inner join cliente c on c.cliente_id = v.cliente_id\n"
                + "where c.cliente_id = ?";

        // try with resource ---> el va  a cerrar el stmt
        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {

            stmt.setInt(1, clienteId);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    //int clienteId = rs.getInt("cliente_id");
                    String clienteNombre = rs.getString("cliente_nombre");
                    int ventaId = rs.getInt("venta_id");
                    Timestamp fechaFactura = rs.getTimestamp("fecha_factura");
                    String numero = rs.getString("numero");

                    System.out.println(clienteNombre + "\t" + ventaId + "\t" + fechaFactura + "\t" + numero);
                }
            }
        }
    }

    private static List<ClienteVenta> buscarClienteVentas(Connection conn) throws SQLException {

        List<ClienteVenta> clienteVentaList = new ArrayList<>();

        String queryString = "select \n"
                + "	c.cliente_id,\n"
                + "	c.nombre cliente_nombre,\n"
                + "	v.venta_id, \n"
                + "	v.fecha_factura,\n"
                + "	v.numero \n"
                + "from venta v \n"
                + "inner join cliente c on c.cliente_id = v.cliente_id\n";

        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    int clienteId = rs.getInt("cliente_id");

                    String clienteNombre = rs.getString("cliente_nombre");

                    int ventaId = rs.getInt("venta_id");

                    //java.sql.Date 2020-08-08
                    //java.sql.Timestamp  2020-08-08 12:12:12
                    //java.sql.Time  12:12:12
                    Timestamp fechaFactura = rs.getTimestamp("fecha_factura");

                    String numero = rs.getString("numero");

                    ClienteVenta clienteVenta = new ClienteVenta();

                    clienteVenta.setClienteId(clienteId);
                    clienteVenta.setNumero(numero);
                    clienteVenta.setVentaId(ventaId);
                    clienteVenta.setClienteNombre(clienteNombre);

                    /*if (fechaFactura != null) {
                        clienteVenta.setFechaFactura(fechaFactura.toLocalDateTime());
                    } */
                    // Optional.ofNullable(fechaFactura).ifPresent(ff -> clienteVenta.setFechaFactura(ff.toLocalDateTime()));
                    Optional.ofNullable(fechaFactura).map(ff -> ff.toLocalDateTime()).ifPresent(clienteVenta::setFechaFactura);

                    clienteVentaList.add(clienteVenta);
                }
            }
        }

        return clienteVentaList;
    }

    /**
     * +---------------+--------------+------+-----+---------------------+-------------------------------+
     * | Field | Type | Null | Key | Default | Extra |
     * +---------------+--------------+------+-----+---------------------+-------------------------------+
     * | cliente_id | int(11) | NO | PRI | NULL | auto_increment | | codigo |
     * varchar(255) | YES | | NULL | | | nit | varchar(15) | YES | | NULL | | |
     * nombre | varchar(255) | YES | | NULL | | | direccion | varchar(255) | YES
     * | | NULL | | | fecha_ingreso | timestamp | NO | | current_timestamp() |
     * on update current_timestamp() | | tipo | smallint(6) | YES | | NULL | |
     * +---------------+--------------+------+-----+---------------------+-------------------------------+
     *
     * +---------------+--------------+------+-----+---------------------+-------------------------------+
     *
     */
    private static void insertarCliente(Connection conn,
            String codigo,
            String nit,
            String nombre,
            String direccion,
            LocalDateTime fechaIngreso,
            Integer tipo) throws SQLException {

        //StringBuilder
        String queryString = "insert into cliente (codigo, nit, nombre, direccion, fecha_ingreso, tipo) values (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {

            stmt.setString(1, codigo);
            stmt.setString(2, nit);
            stmt.setString(3, nombre);
            stmt.setString(4, direccion);

            Date fechaIngresoDate = Date.from(fechaIngreso.atZone(ZoneId.systemDefault()).toInstant());

            stmt.setTimestamp(5, new Timestamp(fechaIngresoDate.getTime()));

            stmt.setInt(6, tipo);

            int registros = stmt.executeUpdate();

            System.out.println(registros + " actualizados.");
        }
    }

    private static void updateCliente(Connection conn, int clienteId, String nombre, String direccion) throws SQLException {

        String queryString = "update cliente set nombre = ?, direccion = ? where cliente_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {

            stmt.setString(1, nombre);
            stmt.setString(2, direccion);
            stmt.setInt(3, clienteId);

            int registros = stmt.executeUpdate();

            System.out.println(registros + " actualizados.");
        }
    }

    public static void eliminarCliente(Connection conn, int clienteId) throws SQLException {

        String queryString = "delete from cliente where cliente_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {
            stmt.setInt(1, clienteId);

            int registros = stmt.executeUpdate();

            System.out.println(registros + " actualizados.");
        }
    }

    public static void main(String[] args) {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/telus1", "root", "felicia");

            System.out.println("Base de datos conectada!!!");

            ejecutarQuery1(conn);
            //ejecutarQuery2(conn);
            //ejecutarQuery3(conn, 81);
            //          var clienteVentaList = buscarClienteVentas(conn);
            //clienteVentaList.stream().forEach(System.out::println);
            //insertarCliente(conn, "0000", "30108268", "Mario", "Guatemala", LocalDateTime.now(), 1);
//            updateCliente(conn, 103, "Mario Batres", "Zona 21, Guatemala");
            // eliminarCliente(conn, 103);

            conn.close();

        } catch (SQLException ex) {

            //esto en lugar de ex.printStackTrace()
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error", ex);

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex1) {
                    System.err.println(ex1.getMessage());
                }
            }
        }
    }
}
