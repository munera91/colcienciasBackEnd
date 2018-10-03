/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.DAO;

import com.colcienciasWeb.Model.Finca;
import com.colcienciasWeb.Model.Municipio;
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

    public ArrayList<Vacuno> getVacunos(String idPredio) throws SQLException, Exception {
        ArrayList<Vacuno> listavacunos = new ArrayList();
        PreparedStatement st;
        ResultSet result;
        st = this.getConexion().prepareCall("SELECT V.\"ID_VACUNO\", V.\"RAZA\", V.\"PREDIO\", \"CATEGORIA\", (SELECT \"PESO\"\n"
                + "FROM public.\"VACUNO_PESO\"\n"
                + "WHERE \"PREDIO\" = '" + idPredio + "'  AND \"VACUNO_ID\" = V.\"ID_VACUNO\" "
                + "ORDER BY \"FECHA_REGISTRO\" desc limit 1) AS PESO\n"
                + "FROM public.\"VACUNO\" V WHERE \"PREDIO\" = '" + idPredio + "' ");
        result = st.executeQuery();
        while (result.next()) {
            listavacunos.add(getVacunoBYID(result.getInt("ID_VACUNO")));
        }
        st.close();
        return listavacunos;
    }

    public ArrayList<Predio> getPredios(String idFinca) throws SQLException, Exception {
        ArrayList<Predio> listaPredios = new ArrayList();
        PreparedStatement st;
        ResultSet result;
        st = this.getConexion().prepareCall("SELECT \"ID_PREDIO\", \"DESCRIPCION\", \"TIPO_ALIMENTACION\", \"TIPO_TERRENO\", \"FINCA\"\n"
                + "FROM public.\"PREDIO\"\n"
                + "WHERE \"FINCA\" = '" + idFinca + "' ");
        result = st.executeQuery();
        while (result.next()) {
            listaPredios.add(getPredioBYID(result.getInt("ID_PREDIO")));
        }
        st.close();
        return listaPredios;
    }

    public ArrayList<Municipio> getMunicipios(String idDepartamento) throws SQLException, Exception {
        ArrayList<Municipio> listaMunicipios = new ArrayList();
        PreparedStatement st;
        ResultSet result;
        st = this.getConexion().prepareCall("SELECT \"idMUNICIPIO\", nombre FROM public.\"MUNICIPIO\" "
                + "WHERE \"idDepartamento\" = '" + idDepartamento + "'");
        result = st.executeQuery();
        while (result.next()) {
            listaMunicipios.add(new Municipio(result.getString("idMunicipio"), result.getString("nombre")));
        }
        st.close();
        return listaMunicipios;
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

        st = this.getConexion().prepareCall("SELECT V.\"ID_VACUNO\", V.\"RAZA\", (SELECT \"PESO\"\n"
                + "FROM public.\"VACUNO_PESO\"\n"
                + "WHERE \"VACUNO_ID\" = V.\"ID_VACUNO\" ORDER BY \"FECHA_REGISTRO\" desc limit 1) AS PESO,P.\"ID_PREDIO\" AS IDPREDIO,\n"
                + "P.\"DESCRIPCION\" AS PREDIO,\n"
                + "C.\"ID_CATEGORIA\" AS  IDCATEGORIA ,C.\"DESCRIPCION\" AS  CATEGORIA\n"
                + "FROM public.\"VACUNO\" V \n"
                + "INNER JOIN public.\"PREDIO\" P ON (P.\"ID_PREDIO\" = V.\"PREDIO\")\n"
                + "INNER JOIN public.\"CATEGORIA_VACUNO\" C ON (C.\"ID_CATEGORIA\" = V.\"CATEGORIA\")\n"
                + "WHERE V.\"ID_VACUNO\" = " + idVacuno + "");
        result2 = st.executeQuery();
        while (result2.next()) {
            vacuno = new Vacuno(result2.getInt("ID_VACUNO"), result2.getString("RAZA"),
                    result2.getDouble("PESO"), pesoVacunoHabilitado(result2.getInt("ID_VACUNO")),
                    result2.getInt("IDPREDIO"), result2.getString("PREDIO"),
                    result2.getInt("IDCATEGORIA"), result2.getString("CATEGORIA"));
        }
        st.close();
        return vacuno;
    }

    public Boolean pesoVacunoHabilitado(int idVacuno) throws Exception {
        Boolean habilitado = true;
        PreparedStatement st;
        ResultSet result2;
        st = this.getConexion().prepareCall("SELECT \"VACUNO_ID\", \"PESO\", \"MES\", \"ANIO\", \"FECHA_REGISTRO\"\n"
                + "FROM public.\"VACUNO_PESO\"\n"
                + "WHERE \"MES\" = EXTRACT (MONTH FROM CURRENT_DATE) AND \"ANIO\" = EXTRACT (YEAR FROM CURRENT_DATE) \n"
                + "AND \"VACUNO_ID\" = " + idVacuno + "");
        result2 = st.executeQuery();
        while (result2.next()) {
            habilitado = false;
        }
        st.close();
        return habilitado;
    }

    public Predio getPredioBYID(int idPredio) throws Exception {
        Predio predio = null;
        PreparedStatement st;
        ResultSet result2;
        st = this.getConexion().prepareCall("SELECT P.\"ID_PREDIO\", P.\"DESCRIPCION\", A.\"ID_TIPO_ALIMENTACION\" AS IDALIMENTO ,A.\"DESCRIPCION\" AS ALIMENTO,T.\"ID_TIPO_TERRENO\" AS IDTERRENO ,T.\"DESCRIPCION\" AS TERRENO,F.\"NOMBRE\",\n"
                + "F.\"ID_FINCA\" AS IDFINCA ,F.\"NOMBRE\" AS FINCA FROM public.\"PREDIO\" P\n"
                + "INNER JOIN public.\"TIPO_ALIMENTACION\" A ON (A.\"ID_TIPO_ALIMENTACION\" = P.\"TIPO_ALIMENTACION\")\n"
                + "INNER JOIN public.\"TIPO_TERRENO\" T ON (T.\"ID_TIPO_TERRENO\" = P.\"TIPO_TERRENO\")\n"
                + "INNER JOIN public.\"FINCA\" F ON (F.\"ID_FINCA\" = P.\"FINCA\")\n"
                + "WHERE P.\"ID_PREDIO\" = " + idPredio + "");
        result2 = st.executeQuery();
        while (result2.next()) {
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
        ResultSet result;
        Integer idVacuno = null;
        st = this.getConexion().prepareStatement("INSERT INTO public.\"VACUNO\"(\"ID_VACUNO\", \"RAZA\", \"PESO\","
                + " \"PREDIO\", \"CATEGORIA\")\n"
                + "VALUES (default,'" + vacuno.getRaza() + "'," + vacuno.getPeso() + "," + vacuno.getIdPredio() + ","
                + " " + vacuno.getIdCategoria() + ") RETURNING \"ID\" ");
        result = st.executeQuery();
        while (result.next()) {
            idVacuno = result.getInt("ID_VACUNO");
        }
        st.close();
        if (idVacuno != null) {
            st = this.getConexion().prepareStatement("INSERT INTO public.\"VACUNO_PESO\"(\"VACUNO_ID\", \"PESO\","
                    + " \"MES\", \"ANIO\", \"FECHA_REGISTRO\") VALUES (" + idVacuno + "," + vacuno.getPeso() + ","
                    + " EXTRACT(MONTH FROM CURRENT_DATE), EXTRACT(YEAR FROM CURRENT_DATE), CURRENT_DATE)");
            st.executeUpdate();
        }
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
        PreparedStatement st, st2, st3;
        ResultSet result;
        String idAlimento = null;
        st = this.getConexion().prepareStatement("UPDATE public.\"VACUNO\"\n"
                + "SET  \"PREDIO\"= " + vacuno.getIdPredio() + ","
                + "\"CATEGORIA\"= '" + vacuno.getIdCategoria() + "'\n"
                + "WHERE \"ID_VACUNO\" = " + vacuno.getID() + "");
        st.executeUpdate();
        st.close();

        if (vacuno.getActualizarPeso()) {

            st3 = this.getConexion().prepareStatement("SELECT \"TIPO_ALIMENTACION\"\n"
                    + "FROM public.\"PREDIO\"\n"
                    + "WHERE \"ID_PREDIO\" = " + vacuno.getIdPredio() + "");
            result = st3.executeQuery();
            while (result.next()) {
                idAlimento = result.getString("TIPO_ALIMENTACION");
            }
            st2 = this.getConexion().prepareStatement("INSERT INTO public.\"VACUNO_PESO\"(\"VACUNO_ID\","
                    + " \"PESO\", \"MES\", \"ANIO\", \"FECHA_REGISTRO\")\n"
                    + "VALUES (" + vacuno.getID() + ", " + vacuno.getPeso() + ",EXTRACT (MONTH FROM CURRENT_DATE),"
                    + "EXTRACT (YEAR FROM CURRENT_DATE), CURRENT_DATE), '" + idAlimento + "',"
                    + "" + vacuno.getIdPredio() + "");
            st2.executeUpdate();
            st2.close();
        }
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
                + "" + predio.getIdTipoTerreno() + ", " + predio.getIdFinca() + " )");
        st.executeUpdate();
        st.close();
    }

    public void actualizarPredio(Predio predio) throws Exception {
        PreparedStatement st;
        System.out.println("UPDATE public.\"PREDIO\"\n"
                + "\"DESCRIPCION\"='" + predio.getDescripcion() + "', \"TIPO_ALIMENTACION\"=" + predio.getIdTipoAlimentacion() + ","
                + " \"TIPO_TERRENO\"=" + predio.getIdTipoTerreno() + "\n"
                + " WHERE \"ID_PREDIO\" =  '" + predio.getID() + "'");
        st = this.getConexion().prepareStatement("UPDATE public.\"PREDIO\" SET \n"
                + "\"DESCRIPCION\"='" + predio.getDescripcion() + "', \"TIPO_ALIMENTACION\"=" + predio.getIdTipoAlimentacion() + ","
                + " \"TIPO_TERRENO\"=" + predio.getIdTipoTerreno() + "\n"
                + " WHERE \"ID_PREDIO\" =  '" + predio.getID() + "'");
        st.executeUpdate();
        st.close();
    }

    public void eliminarPredio(String idPredio) throws Exception {
        PreparedStatement st;
        st = this.getConexion().prepareStatement("DELETE FROM public.\"PREDIO\" "
                + "WHERE \"ID_PREDIO\" = '" + idPredio + "'");
        st.executeUpdate();
        st.close();
    }

}
