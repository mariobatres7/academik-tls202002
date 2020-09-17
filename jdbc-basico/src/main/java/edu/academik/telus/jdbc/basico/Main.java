package edu.academik.telus.jdbc.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

    public static void main(String[] args) {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/telus1", "root", "felicia");

            System.out.println("Base de datos conectada!!!");

            ejecutarQuery1(conn);
            ejecutarQuery2(conn);
            ejecutarQuery3(conn, 81);

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
