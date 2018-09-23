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
        st.close();
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
        st.close();
        return listavacunos;
    }

    public ArrayList<Predio> getPredios(String idFinca) throws SQLException, Exception {
        ArrayList<Predio> listavacunos = new ArrayList();
        PreparedStatement st;
        ResultSet result;
        st = this.getConexion().prepareCall("SELECT \"ID_PREDIO\", \"DESCRIPCION\", \"TIPO_ALIMENTACION\", \"TIPO_TERRENO\", \"FINCA\"\n"
                + "FROM public.\"PREDIO\"\n"
                + "WHERE \"FINCA\" = '" + idFinca + "' ");
        result = st.executeQuery();
        while (result.next()) {
            listavacunos.add(getPredioBYID(result.getInt("ID_PREDIO")));
        }
        st.close();
        return listavacunos;
    }

    public Finca getFincaBYID(String idFinca) throws Exception {
        Finca finca = null;
        PreparedStatement st;
        ResultSet result2;
        st = this.getConexion().prepareCall("SELECT F.\"ID_FINCA\", F.\"NOMBRE\", F.\"HECTAREAS\", F.\"DIRECCION\", F.\"NOMBRE_PROPIETARIO\",\n"
                + " M.\"idMUNICIPIO\" AS IDMUNICIPIO, M.\"nombre\" as nombremunicipio \n"
                + "FROM public.\"FINCA\" F\n"
                + "INNER JOIN public.\"MUNICIPIO\" M ON (M.\"idMUNICIPIO\" = F.\"MUNICIPIO\")\n"
                + "WHERE F.\"ID_FINCA\" = " + idFinca + "");
        result2 = st.executeQuery();
        while (result2.next()) {
            finca = new Finca(result2.getInt("ID_FINCA"), result2.getString("NOMBRE"),
                    result2.getDouble("HECTAREAS"), result2.getString("DIRECCION"),
                    result2.getString("NOMBRE_PROPIETARIO"), result2.getInt("idMUNICIPIO"),
                    result2.getString("nombremunicipio"));
        }
        st.close();
        return finca;
    }

    public Vacuno getVacunoBYID(int idVacuno) throws Exception {
        Vacuno vacuno = null;
        PreparedStatement st;
        ResultSet result2;

        st = this.getConexion().prepareCall("SELECT V.\"ID_VACUNO\", V.\"RAZA\", V.\"PESO\",P.\"ID_PREDIO\" AS IDPREDIO,"
                + "P.\"DESCRIPCION\" AS PREDIO,"
                + "C.\"ID_CATEGORIA\" AS  IDCATEGORIA ,C.\"DESCRIPCION\" AS  CATEGORIA \n"
                + "FROM public.\"VACUNO\" V \n"
                + "INNER JOIN public.\"PREDIO\" P ON (P.\"ID_PREDIO\" = V.\"PREDIO\")\n"
                + "INNER JOIN public.\"CATEGORIA_VACUNO\" C ON (C.\"ID_CATEGORIA\" = V.\"CATEGORIA\")\n"
                + "WHERE V.\"ID_VACUNO\" = " + idVacuno + "");
        result2 = st.executeQuery();
        if (result2.first()) {
            vacuno = new Vacuno(result2.getInt("ID_VACUNO"), result2.getString("RAZA"),
                    result2.getDouble("PESO"), result2.getInt("IDPREDIO"), result2.getString("PREDIO"),
                    result2.getInt("IDCATEGORIA"), result2.getString("CATEGORIA"));
        }
        st.close();
        return vacuno;
    }

    public Predio getPredioBYID(int idPredio) throws Exception {
        Predio predio = null;
        PreparedStatement st;
        ResultSet result2;

        st = this.getConexion().prepareCall("SELECT P.\"ID_PREDIO\", P.\"DESCRIPCION\", A.\"ID_TIPO_ALIMENTACION\" AS IDALIMENTO ,A.\"DESCRIPCION\" AS ALIMENTO,"
                + "T.\"ID_TIPO_TERRENO\" AS IDTERRENO ,T.\"DESCRIPCION\" AS TERRENO,F.\"NOMBRE\"\n"
                + "F.\"ID_FINCA\" AS IDFINCA ,F.\"NOMBRE\" AS FINCA"
                + "FROM public.\"PREDIO\" P\n"
                + "INNER JOIN public.\"TIPO_ALIMENTACION\" A ON (A.\"ID_TIPO_ALIMENTACION\" = P.\"TIPO_ALIMENTACION\")\n"
                + "INNER JOIN public.\"TIPO_TERRENO\" T ON (T.\"ID_TIPO_TERRENO\" = P.\"TIPO_TERRENO\")\n"
                + "INNER JOIN puclic.\"FINCA\" F ON (F.\"ID_FINCA\" = P.\"FINCA\")\n"
                + "WHERE P.\"ID_PREDIO\" = " + idPredio + "");

        result2 = st.executeQuery();
        if (result2.first()) {
            predio = new Predio(result2.getInt("ID_PREDIO"), result2.getString("DESCRIPCION"),
                    result2.getInt("IDALIMENTO"), result2.getString("ALIMENTO"),
                    result2.getInt("IDTERRENO"), result2.getString("TERRENO"),
                    result2.getInt("IDFINCA"), result2.getString("FINCA"));
        }
        st.close();
        return predio;
    }

    public void insertarFinca(Finca finca) throws Exception {
        PreparedStatement st;
        st = this.getConexion().prepareStatement("INSERT INTO PUBLIC.\"FINCA\"(\"ID_FINCA\",\"NOMBRE\","
                + "\"HECTAREAS\",\"DIRECCION\",\"NOMBRE_PROPIETARIO\",\"MUNICIPIO\") \n"
                + "VALUES(DEFAULT,'" + finca.getNombre() + "'," + finca.getHectareas() + ","
                + "'" + finca.getDireccion() + "','" + finca.getNombrePropietario() + "','" + finca.getIdMunicipio() + "' )");
        st.executeUpdate();
        st.close();
    }

    public void insertarVacuno(Vacuno vacuno) throws Exception {
        PreparedStatement st;
        st = this.getConexion().prepareStatement("INSERT INTO public.\"VACUNO\"(\"ID_VACUNO\", \"RAZA\", \"PESO\","
                + " \"PREDIO\", \"CATEGORIA\")\n"
                + "VALUES (default,'" + vacuno.getRaza() + "'," + vacuno.getPeso() + "," + vacuno.getIdPredio() + ","
                + " " + vacuno.getIdCategoria() + ")");
        st.executeUpdate();
        st.close();
    }

    public void actualizarFinca(Finca finca) throws Exception {
        PreparedStatement st;
        st = this.getConexion().prepareStatement("UPDATE public.\"FINCA\"\n"
                + " SET \"NOMBRE\"='" + finca.getNombre() + "', \"HECTAREAS\"=" + finca.getHectareas() + ","
                + " \"DIRECCION\"='" + finca.getDireccion() + "', \"NOMBRE_PROPIETARIO\"='" + finca.getNombrePropietario() + "', \n"
                + " \"MUNICIPIO\"='" + finca.getIdMunicipio() + "' \n"
                + "WHERE \"ID_FINCA\" = '" + finca.getID() + "'");
        st.executeUpdate();
        st.close();
    }

    public void actualizarVacuno(Vacuno vacuno) throws Exception {
        PreparedStatement st;
        st = this.getConexion().prepareStatement("UPDATE public.\"VACUNO\"\n"
                + "SET \"PESO\"=" + vacuno.getPeso() + ", \"PREDIO\"=" + vacuno.getIdPredio() + ","
                + " \"CATEGORIA\"=" + vacuno.getIdCategoria() + "\n"
                + " WHERE \"ID_VACUNO\" =  '" + vacuno.getID() + "'");
        st.executeUpdate();
        st.close();
    }

    public void eliminarFinca(Finca finca) throws Exception {
        PreparedStatement st;
        st = this.getConexion().prepareStatement("DELETE FROM public.\"FINCA\" "
                + "WHERE \"ID_FINCA\" = '" + finca.getID() + "'");
        st.executeUpdate();
        st.close();
    }

    public void eliminarVacuno(Vacuno vacuno) throws Exception {
        PreparedStatement st;
        st = this.getConexion().prepareStatement("DELETE FROM public.\"VACUNO\" "
                + "WHERE \"ID_VACUNO\" = '" + vacuno.getID() + "'");
        st.executeUpdate();
        st.close();
    }

    public void insertarPredio(Predio predio) throws Exception {
        PreparedStatement st;

        st = this.getConexion().prepareStatement("INSERT INTO public.\"PREDIO\"(\"ID_PREDIO\", \"DESCRIPCION\","
                + "\"TIPO_ALIMENTACION\", \"TIPO_TERRENO\", \"FINCA\")\n"
                + "VALUES (DEFAULT,'" + predio.getDescripcion() + "'," + predio.getIdTipoAlimentacion() + ","
                + "" + predio.getIdTipoTerreno() + ", " + predio.getIdFinca() + "");
        st.executeUpdate();
        st.close();
    }

    public void actualizarPredio(Predio predio) throws Exception {
        PreparedStatement st;

        st = this.getConexion().prepareStatement("UPDATE public.\"PREDIO\"\n"
                + "\"DESCRIPCION\"='" + predio.getDescripcion() + "', \"TIPO_ALIMENTACION\"=" + predio.getIdTipoAlimentacion() + ","
                + " \"TIPO_TERRENO\"=" + predio.getIdTipoTerreno() + "\n"
                + " WHERE \"ID_PREDIO\" =  '" + predio.getID() + "'");
        st.executeUpdate();
        st.close();
    }

    public void eliminarPredio(Predio predio) throws Exception {
        PreparedStatement st;
        st = this.getConexion().prepareStatement("DELETE FROM public.\"PREDIO\" "
                + "WHERE \"ID_PREDIO\" = '" + predio.getID() + "'");
        st.executeUpdate();
        st.close();
    }

}
