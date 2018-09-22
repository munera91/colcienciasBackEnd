/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.DAO;

import com.colcienciasWeb.Model.Finca;
import com.colcienciasWeb.Model.Vacuno;
import com.colcienciasWeb.utilities.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Juliana. Maldonado
 */
public class ColcienciasDao extends Conexion {

    public ArrayList<Finca> getFincas() throws SQLException, Exception {
        ArrayList<Finca> listaFincas = new ArrayList();
        PreparedStatement st;
        ResultSet result;
        st = this.getConexion().prepareCall("SELECT \"ID_FINCA\", \"NOMBRE\", \"HECTAREAS\", \"DIRECCION\","
                + " \"NOMBRE_PROPIETARIO\", \n"
                + "  \"MUNICIPIO\",\"TIPO_TERRENO\"\n"
                + " FROM public.\"FINCA\"");
        result = st.executeQuery();
        while (result.next()) {
            listaFincas.add(getFincaBYID(result.getString("ID_FINCA")));
        }
        return listaFincas;
    }

    public ArrayList<Vacuno> getVacunos() throws SQLException, Exception {
        ArrayList<Vacuno> listavacunos = new ArrayList();
        PreparedStatement st;
        ResultSet result;
        st = this.getConexion().prepareCall("SELECT \"ID_VACUNO\", \"RAZA\", \"NUMERO_PARTOS\", \"PESO\", \"FINCA\"\n"
                + " FROM public.\"VACUNO\"");
        result = st.executeQuery();
        while (result.next()) {
            listavacunos.add(getVacunoBYID(result.getString("ID_VACUNO")));
        }
        return listavacunos;
    }

    public Finca getFincaBYID(String idFinca) throws Exception {
        Finca finca = null;
        PreparedStatement st;
        ResultSet result2;

        st = this.getConexion().prepareCall("SELECT \"ID_FINCA\", \"NOMBRE\", \"HECTAREAS\","
                + " \"DIRECCION\", \"NOMBRE_PROPIETARIO\", \n"
                + " \"MUNICIPIO\",\"TIPO_TERRENO\" FROM public.\"FINCA\""
                + "WHERE \"ID_FINCA\" = '" + idFinca + "'");
        result2 = st.executeQuery();
        while (result2.next()) {
            finca = new Finca(result2.getString("ID_FINCA"), result2.getString("NOMBRE"),
                    result2.getDouble("HECTAREAS"), result2.getString("DIRECCION"),
                    result2.getString("NOMBRE_PROPIETARIO"), result2.getString("MUNICIPIO"), result2.getString("TIPO_TERRENO"));
        }

        return finca;
    }

    public Vacuno getVacunoBYID(String idVacuno) throws Exception {
        Vacuno vacuno = null;
        PreparedStatement st;
        ResultSet result2;

        st = this.getConexion().prepareCall("SELECT \"ID_VACUNO\", \"RAZA\", \"NUMERO_PARTOS\", \"PESO\", \"FINCA\"\n"
                + " FROM public.\"VACUNO\""
                + "WHERE \"ID_VACUNO\" = '" + idVacuno + "'");
        result2 = st.executeQuery();
        while (result2.next()) {
            vacuno = new Vacuno(result2.getString("ID_VACUNO"), result2.getString("RAZA"),
                    result2.getInt("NUMERO_PARTOS"), result2.getDouble("PESO"),
                    result2.getString("FINCA"));
        }

        return vacuno;
    }

    public void insertarFinca(Finca finca) throws Exception {
        PreparedStatement st, st2;
        ResultSet result2;
        String id = "";
        int idFinca = 1;
        st2 = this.getConexion().prepareStatement("SELECT MAX(\"ID_FINCA\")\n"
                + " FROM public.\"FINCA\" ");
        result2 = st2.executeQuery();
        while (result2.next()) {
            id = result2.getString("max");
            idFinca += Integer.parseInt(id);
            id = Integer.toString(idFinca);
        }
        st = this.getConexion().prepareStatement("INSERT INTO PUBLIC.\"FINCA\"(\"ID_FINCA\",\"NOMBRE\","
                + "\"HECTAREAS\",\"DIRECCION\",\"NOMBRE_PROPIETARIO\",\"MUNICIPIO\",\"TIPO_TERRENO\") \n"
                + "VALUES('" + id + "','" + finca.getNombre() + "'," + finca.getHectareas() + ","
                + "'" + finca.getDireccion() + "','" + finca.getNombrePropietario() + "','" + finca.getMunicipio() + "',"
                + "'" + finca.getTipoTerreno() + "')");
        st.executeUpdate();

    }

    public void insertarVacuno(Vacuno vacuno) throws Exception {
        PreparedStatement st, st2;
        ResultSet result2;
        String id = "";
        int idVacuno = 1;
        st2 = this.getConexion().prepareStatement("SELECT MAX(\"ID_VACUNO\")\n"
                + " FROM public.\"VACUNO\" ");
        result2 = st2.executeQuery();
        while (result2.next()) {
            id = result2.getString("max");
            idVacuno += Integer.parseInt(id);
            id = Integer.toString(idVacuno);
        }
        st = this.getConexion().prepareStatement("INSERT INTO PUBLIC.\"VACUNO\"(\"ID_VACUNO\",\"RAZA\",\"NUMERO_PARTOS\""
                + ",\"PESO\",\"FINCA\")\n"
                + "VALUES('" + id + "','" + vacuno.getRaza() + "'," + vacuno.getNumeroPartos() + "," + vacuno.getPeso() + ","
                + "'" + vacuno.getFinca() + "')");
        st.executeUpdate();

    }

    public void actualizarFinca(Finca finca) throws Exception {
        PreparedStatement st;
        st = this.getConexion().prepareStatement("UPDATE public.\"FINCA\"\n"
                + " SET \"NOMBRE\"='" + finca.getNombre() + "', \"HECTAREAS\"=" + finca.getHectareas() + ","
                + " \"DIRECCION\"='" + finca.getDireccion() + "', \"NOMBRE_PROPIETARIO\"='" + finca.getNombrePropietario() + "', \n"
                + " \"MUNICIPIO\"='" + finca.getMunicipio() + "',\"TIPO_TERRENO\"='" + finca.getTipoTerreno() + "'\n"
                + "WHERE \"ID_FINCA\" = '" + finca.getID() + "'");
        st.executeUpdate();
    }

    public void actualizarVacuno(Vacuno vacuno) throws Exception {
        PreparedStatement st;

        st = this.getConexion().prepareStatement("UPDATE public.\"VACUNO\"\n"
                + "  SET \"RAZA\"='"+ vacuno.getRaza() +"', \"NUMERO_PARTOS\"="+ vacuno.getNumeroPartos() +","
                        + " \"PESO\"="+ vacuno.getPeso() +", \"FINCA\"='"+ vacuno.getFinca() +"'\n"
                + "WHERE \"ID_VACUNO\" = '" + vacuno.getID() + "'");
        st.executeUpdate();
    }

    public void eliminarFinca(Finca finca) throws Exception {
        PreparedStatement st;
        st = this.getConexion().prepareStatement("DELETE FROM public.\"FINCA\" "
                + "WHERE \"ID_FINCA\" = '" + finca.getID() + "'");
        st.executeUpdate();
    }
    
    public void eliminarVacuno(Vacuno vacuno) throws Exception {
        PreparedStatement st;
        st = this.getConexion().prepareStatement("DELETE FROM public.\"VACUNO\" "
                + "WHERE \"ID_VACUNO\" = '" + vacuno.getID() + "'");
        st.executeUpdate();
    }

}
