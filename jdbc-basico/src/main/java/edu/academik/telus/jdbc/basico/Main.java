package edu.academik.telus.jdbc.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static void main(String[] args) {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/telus1", "root", "felicia");

            System.out.println("Base de datos conectada!!!");

            ejecutarQuery1(conn);

            conn.close();

        } catch (SQLException ex) {

            System.err.println(ex.getMessage());

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
