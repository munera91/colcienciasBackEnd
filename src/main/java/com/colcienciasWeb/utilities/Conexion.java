package com.colcienciasWeb.utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static Connection getConexion() {
        String url = "jdbc:postgresql://localhost:5432/colciencias";
        String usuario = "colciencias";
        String clave = "hola";
        String driver = "org.postgresql.Driver";
        return getConexion(url, usuario, clave, driver);
    }

    public static Connection getConexion(String url, String usuario,
            String clave, String driver) {

        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, clave);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }
    
    public void Cerrar() throws Exception {

        Connection con = null;
        try {
            if (con != null) {
                if (con.isClosed() == false) {
                    con.close();
                }
            }
        } catch (Exception e) {
            throw e;
        }

    }
}
