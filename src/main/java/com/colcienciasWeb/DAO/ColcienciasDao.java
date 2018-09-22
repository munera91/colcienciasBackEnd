/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.DAO;

import com.colcienciasWeb.Model.Finca;
import com.colcienciasWeb.Model.Predio;
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
                + "  \"MUNICIPIO\"\n"
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
        st = this.getConexion().prepareCall("SELECT \"ID_VACUNO\", \"RAZA\", \"PESO\", \"PREDIO\", \"CATEGORIA\"\n"
                + "FROM public.\"VACUNO\"");
        result = st.executeQuery();
        while (result.next()) {
            listavacunos.add(getVacunoBYID(result.getInt("ID_VACUNO")));
        }
        return listavacunos;
    }

    public Finca getFincaBYID(String idFinca) throws Exception {
        Finca finca = null;
        PreparedStatement st;
        ResultSet result2;

        st = this.getConexion().prepareCall("SELECT \"ID_FINCA\", \"NOMBRE\", \"HECTAREAS\","
                + " \"DIRECCION\", \"NOMBRE_PROPIETARIO\", \n"
                + " \"MUNICIPIO\" FROM public.\"FINCA\""
                + "WHERE \"ID_FINCA\" = '" + idFinca + "'");
        result2 = st.executeQuery();
        while (result2.next()) {
            finca = new Finca(result2.getInt("ID_FINCA"), result2.getString("NOMBRE"),
                    result2.getDouble("HECTAREAS"), result2.getString("DIRECCION"),
                    result2.getString("NOMBRE_PROPIETARIO"), result2.getString("MUNICIPIO"));
        }

        return finca;
    }

    public Vacuno getVacunoBYID(int idVacuno) throws Exception {
        Vacuno vacuno = null;
        PreparedStatement st;
        ResultSet result2;

        st = this.getConexion().prepareCall("SELECT V.\"ID_VACUNO\", V.\"RAZA\", V.\"PESO\", P.\"DESCRIPCION\" AS PREDIO,"
                + " C.\"DESCRIPCION\" AS  CATEGORIA \n"
                + "FROM public.\"VACUNO\" V \n"
                + "INNER JOIN public.\"PREDIO\" P ON (P.\"ID_PREDIO\" = V.\"PREDIO\")\n"
                + "INNER JOIN public.\"CATEGORIA_VACUNO\" C ON (C.\"ID_CATEGORIA\" = V.\"CATEGORIA\")\n"
                + "WHERE V.\"ID_VACUNO\" = " + idVacuno + "");
        result2 = st.executeQuery();
        if (result2.first()) {
            vacuno = new Vacuno(result2.getInt("ID_VACUNO"), result2.getString("RAZA"),
                    result2.getDouble("PESO"), result2.getString("PREDIO"),);
        }

        return vacuno;
    }

    public Predio getPredioBYID(int idPredio) throws Exception {
        Predio predio = null;
        PreparedStatement st;
        ResultSet result2;

        st = this.getConexion().prepareCall("SELECT P.\"ID_PREDIO\", P.\"DESCRIPCION\", A.\"DESCRIPCION\" AS ALIMENTO, T.\"DESCRIPCION\",\n"
                + "       F.\"NOMBRE\"\n"
                + "FROM public.\"PREDIO\" P\n"
                + "INNER JOIN public.\"TIPO_ALIMENTACION\" A ON (A.\"ID_TIPO_ALIMENTACION\" = P.\"TIPO_ALIMENTACION\")\n"
                + "INNER JOIN public.\"TIPO_TERRENO\" T ON (T.\"ID_TIPO_TERRENO\" = P.\"TIPO_TERRENO\")\n"
                + "INNER JOIN puclic.\"FINCA\" F ON (F.\"ID_FINCA\" = P.\"FINCA\")\n"
                + "WHERE P.\"ID_PREDIO\" = ??");
        
        result2 = st.executeQuery();
        if(result2.first()){
            predio = new Predio(result2.getInt("ID_PREDIO"), result2.getString("DESCRIPCION"),
                    result2.getString("ALIMENTO"), result2.getString("ALIMENTO"), idPredio);
        }
    }

    public void insertarFinca(Finca finca) throws Exception {
        PreparedStatement st, st2;
        ResultSet result2;
        st = this.getConexion().prepareStatement("INSERT INTO PUBLIC.\"FINCA\"(\"ID_FINCA\",\"NOMBRE\","
                + "\"HECTAREAS\",\"DIRECCION\",\"NOMBRE_PROPIETARIO\",\"MUNICIPIO\") \n"
                + "VALUES(DEFAULT,'" + finca.getNombre() + "'," + finca.getHectareas() + ","
                + "'" + finca.getDireccion() + "','" + finca.getNombrePropietario() + "','" + finca.getMunicipio() + "' ");
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
                + " \"MUNICIPIO\"='" + finca.getMunicipio() + "' \n"
                + "WHERE \"ID_FINCA\" = '" + finca.getID() + "'");
        st.executeUpdate();
    }

    public void actualizarVacuno(Vacuno vacuno) throws Exception {
        PreparedStatement st;

        st = this.getConexion().prepareStatement("UPDATE public.\"VACUNO\"\n"
                + "  SET \"RAZA\"='" + vacuno.getRaza() + "', \"NUMERO_PARTOS\"=" + vacuno.getNumeroPartos() + ","
                + " \"PESO\"=" + vacuno.getPeso() + ", \"FINCA\"='" + vacuno.getFinca() + "'\n"
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
