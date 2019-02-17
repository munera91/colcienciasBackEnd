/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.DAO;

import com.colcienciasWeb.Model.ConsultaSimulacion;
import com.colcienciasWeb.Model.Fecha;
import com.colcienciasWeb.Model.Finca;
import com.colcienciasWeb.Model.HistoricoVacuno;
import com.colcienciasWeb.Model.Municipio;
import com.colcienciasWeb.Model.Predio;
import com.colcienciasWeb.Model.PropiedadAlimento;
import com.colcienciasWeb.Model.RegistroSimulacion;
import com.colcienciasWeb.Model.Simulacion;
import com.colcienciasWeb.Model.Vacuno;
import com.colcienciasWeb.Model.jpa.Usuario;
import com.colcienciasWeb.utilities.Conexion;
import com.colcienciasWeb.utilities.Utilities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juliana. Maldonado
 */
public class ColcienciasDao extends Conexion {

    public String reestablecerPassword(Usuario usuario) {
        PreparedStatement st;
        String respuesta = "No fue posible actualizar la contraseña";
        try {
            st = this.getConexion().prepareStatement("UPDATE public.\"USUARIO\"\n"
                    + " SET \"PASSWORD\"='" + usuario.getPassword() + "' WHERE "
                    + "\"EMAIL\" = '" + usuario.getEmail() + "'");
            st.executeUpdate();
            respuesta = "Contraseña actualizada correctamente";
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ColcienciasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    public String validarUsuario(Usuario usuario) throws SQLException {
        String respuesta = "Datos incorrectos";
        PreparedStatement st;
        ResultSet result;
        st = this.getConexion().prepareCall("SELECT * FROM public.\"USUARIO\" "
                + "WHERE \"EMAIL\" = '" + usuario.getEmail() + "' ");
        result = st.executeQuery();
        while (result.next()) {
            if (usuario.getPassword().equals(result.getString("PASSWORD"))) {
                respuesta = "usuario Valido";
            }
        }
        st.close();
        return respuesta;
    }

    public Usuario obtenerUsuario(String idUsuario) throws SQLException {
        Usuario usuario = null;
        PreparedStatement st;
        ResultSet result2;
        st = this.getConexion().prepareCall("SELECT * FROM public.\"USUARIO\" U "
                + " WHERE U.\"IDENTIFICACION\" = '" + idUsuario + "' ");
        result2 = st.executeQuery();
        while (result2.next()) {
            usuario = new Usuario(idUsuario, result2.getString("NOMBRE"),
                    result2.getString("EMAIL"), result2.getString("PASSWORD"));
        }
        st.close();
        return usuario;
    }

    public ArrayList<Finca> getFincas() throws SQLException, Exception {
        ArrayList<Finca> listaFincas = new ArrayList();
        PreparedStatement st;
        ResultSet result;
        st = this.getConexion().prepareCall("SELECT \"ID_FINCA\" FROM public.\"FINCA\" "
                + "WHERE \"ELIMINADO\" = FALSE ");
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
                + "FROM public.\"VACUNO_HISTORICO\"\n"
                + "WHERE \"PREDIO\" = '" + idPredio + "'  AND \"VACUNO_ID\" = V.\"ID_VACUNO\" "
                + "ORDER BY \"FECHA_REGISTRO\" desc limit 1) AS PESO\n"
                + "FROM public.\"VACUNO\" V WHERE \"PREDIO\" = '" + idPredio + "' "
                + "AND \"ELIMINADO\" = FALSE ");
        result = st.executeQuery();
        while (result.next()) {
            listavacunos.add(getVacunoBYID(result.getInt("ID_VACUNO")));
        }
        st.close();
        return listavacunos;
    }

    public ArrayList<Vacuno> getVacunosBYSimulacion(Integer idSimulacion) throws SQLException, Exception {
        ArrayList<Vacuno> listavacunos = new ArrayList();
        Vacuno vaca = null;
        PreparedStatement st;
        ResultSet result;
        st = this.getConexion().prepareCall("SELECT H.\"ID_VACUNO\", H.\"PESO\", S.\"ID_PREDIO\"\n"
                + "FROM public.\"SIMULACION_VACUNO\"H\n"
                + "INNER JOIN public.\"SIMULACION\" S ON (H.\"ID_SIMULACION\" = S.\"ID_SIMULACION\")\n"
                + "WHERE H.\"ID_SIMULACION\" = "+idSimulacion+"");
        result = st.executeQuery();
        while (result.next()) {
            vaca = getVacunoBYID(result.getInt("ID_VACUNO"));
            vaca.setPeso(result.getDouble("PESO"));
            vaca.setIdPredio(result.getInt("ID_PREDIO"));
            listavacunos.add(vaca);
        }
        st.close();
        return listavacunos;
    }

    public ArrayList<Predio> getPredios(String idFinca) throws SQLException, Exception {
        ArrayList<Predio> listaPredios = new ArrayList();
        PreparedStatement st;
        ResultSet result;
        st = this.getConexion().prepareCall("SELECT \"ID_PREDIO\"\n"
                + "FROM public.\"PREDIO\"\n"
                + "WHERE \"FINCA\" = '" + idFinca + "' "
                + "AND \"ELIMINADO\" = FALSE");
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
                + "FROM public.\"VACUNO_HISTORICO\"\n"
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

    public ArrayList<HistoricoVacuno> getHistoricoVacuno(String idVacuno) {
        ArrayList<HistoricoVacuno> historicoVacuno = new ArrayList<>();
        PreparedStatement st;
        ResultSet result;
        try {
            st = this.getConexion().prepareCall("SELECT \"VACUNO_ID\", \"MES\", \"ANIO\", \"PESO\", \"ALIMENTO\", A.\"DESCRIPCION\" AS \"DESCALIMENTO\",\"PREDIO\" , P.\"DESCRIPCION\" AS \"DESCPREDIO\",\"FECHA_REGISTRO\"\n"
                    + "FROM public.\"VACUNO_HISTORICO\" VH INNER JOIN public.\"TIPO_ALIMENTACION\" A ON (A.\"ID_TIPO_ALIMENTACION\" = VH.\"ALIMENTO\")\n"
                    + "INNER JOIN public.\"PREDIO\" P ON (P.\"ID_PREDIO\" = VH.\"PREDIO\") WHERE \"VACUNO_ID\" = " + idVacuno + ""
                    + " ORDER BY \"MES\", \"ANIO\" desc");

            System.out.println("SELECT \"VACUNO_ID\", \"MES\", \"ANIO\", \"PESO\", \"ALIMENTO\", A.\"DESCRIPCION\" AS \"DESCALIMENTO\",\"PREDIO\" , P.\"DESCRIPCION\" AS \"DESCPREDIO\",\"FECHA_REGISTRO\"\n"
                    + "FROM public.\"VACUNO_HISTORICO\" VH INNER JOIN public.\"TIPO_ALIMENTACION\" A ON (A.\"ID_TIPO_ALIMENTACION\" = VH.\"ALIMENTO\")\n"
                    + "INNER JOIN public.\"PREDIO\" P ON (P.\"ID_PREDIO\" = VH.\"PREDIO\") WHERE \"VACUNO_ID\" = " + idVacuno + ""
                    + " ORDER BY \"MES\", \"ANIO\" desc");
            result = st.executeQuery();
            while (result.next()) {
                historicoVacuno.add(new HistoricoVacuno(result.getInt("VACUNO_ID"), Utilities.MesIntAString(result.getInt("MES")), result.getInt("ANIO"),
                        result.getDouble("PESO"), result.getString("ALIMENTO"), result.getString("DESCALIMENTO"),
                        result.getInt("PREDIO"), result.getString("DESCPREDIO")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColcienciasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return historicoVacuno;
    }

    public Boolean pesoVacunoHabilitado(int idVacuno) throws Exception {
        Boolean habilitado = true;
        PreparedStatement st;
        ResultSet result2;
        st = this.getConexion().prepareCall("SELECT \"VACUNO_ID\", \"PESO\", \"MES\", \"ANIO\", \"FECHA_REGISTRO\"\n"
                + "FROM public.\"VACUNO_HISTORICO\"\n"
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
                + "F.\"ID_FINCA\" AS IDFINCA ,F.\"NOMBRE\" AS FINCA, P.\"CANTIDAD_MAX\" FROM public.\"PREDIO\" P\n"
                + "INNER JOIN public.\"TIPO_ALIMENTACION\" A ON (A.\"ID_TIPO_ALIMENTACION\" = P.\"TIPO_ALIMENTACION\")\n"
                + "INNER JOIN public.\"TIPO_TERRENO\" T ON (T.\"ID_TIPO_TERRENO\" = P.\"TIPO_TERRENO\")\n"
                + "INNER JOIN public.\"FINCA\" F ON (F.\"ID_FINCA\" = P.\"FINCA\")\n"
                + "WHERE P.\"ID_PREDIO\" = " + idPredio + "");
        System.out.println(st);
        result2 = st.executeQuery();
        while (result2.next()) {
            predio = new Predio(result2.getInt("ID_PREDIO"), result2.getString("DESCRIPCION"),
                    result2.getInt("IDALIMENTO"), result2.getString("ALIMENTO"),
                    result2.getInt("IDTERRENO"), result2.getString("TERRENO"),
                    result2.getInt("IDFINCA"), result2.getString("FINCA"), result2.getInt("CANTIDAD_MAX"));
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

    public String insertarVacuno(Vacuno vacuno) throws Exception {
        PreparedStatement st, st3;
        ResultSet result;
        ResultSet result2;
        Integer idVacuno = null, cantMaximaPredio = 0;
        String idAlimento = null;
        String mensaje = "Vacuno registrado correctamente";
        st3 = this.getConexion().prepareStatement("SELECT \"TIPO_ALIMENTACION\",\"CANTIDAD_MAX\" \n"
                + "FROM public.\"PREDIO\"\n"
                + "WHERE \"ID_PREDIO\" = " + vacuno.getIdPredio() + "");
        result2 = st3.executeQuery();
        while (result2.next()) {
            idAlimento = result2.getString("TIPO_ALIMENTACION");
            cantMaximaPredio = result2.getInt("CANTIDAD_MAX");
        }
        System.out.println("CantMaximaPredio " + cantMaximaPredio);
        System.out.println("CantActualPredio" + getCantVacunosRegistradosByPredio(vacuno.getIdPredio()));
        if (cantMaximaPredio > getCantVacunosRegistradosByPredio(vacuno.getIdPredio())) {
            st = this.getConexion().prepareStatement("INSERT INTO public.\"VACUNO\"(\"ID_VACUNO\", \"RAZA\", "
                    + " \"PREDIO\", \"CATEGORIA\")\n"
                    + "VALUES (default,'" + vacuno.getRaza() + "'," + vacuno.getIdPredio() + ","
                    + " " + vacuno.getIdCategoria() + ") RETURNING \"ID_VACUNO\" ");
            result = st.executeQuery();
            while (result.next()) {
                idVacuno = result.getInt("ID_VACUNO");
            }
            st.close();
            if (idVacuno != null) {
                st = this.getConexion().prepareStatement("INSERT INTO public.\"VACUNO_HISTORICO\"(\"VACUNO_ID\", \"PESO\","
                        + " \"MES\", \"ANIO\", \"FECHA_REGISTRO\", \"ALIMENTO\", \"PREDIO\") "
                        + "VALUES (" + idVacuno + "," + vacuno.getPeso() + ","
                        + " EXTRACT(MONTH FROM CURRENT_DATE), EXTRACT(YEAR FROM CURRENT_DATE), CURRENT_DATE,"
                        + " '" + idAlimento + "' , " + vacuno.getIdPredio() + ")");
                st.executeUpdate();
            }
            st.close();
        } else {
            mensaje = "No fue posible registrar el vacuno, el predio está lleno";
        }
        return mensaje;
    }

    public int getCantVacunosRegistradosByPredio(int idPredio) {
        PreparedStatement st1;
        ResultSet result;
        int cantVacunos = 0;
        try {
            st1 = this.getConexion().prepareStatement("SELECT COUNT(*) AS cantactual FROM public.\"VACUNO\" "
                    + "WHERE \"PREDIO\" = " + idPredio + "");
            result = st1.executeQuery();
            while (result.next()) {
                cantVacunos = result.getInt("cantactual");
            }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(ColcienciasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantVacunos;
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
            st2 = this.getConexion().prepareStatement("INSERT INTO public.\"VACUNO_HISTORICO\"(\"VACUNO_ID\","
                    + " \"PESO\", \"MES\", \"ANIO\", \"FECHA_REGISTRO\", \"ALIMENTO\", \"PREDIO\")\n"
                    + "VALUES (" + vacuno.getID() + ", " + vacuno.getPeso() + ",EXTRACT (MONTH FROM CURRENT_DATE),"
                    + "EXTRACT (YEAR FROM CURRENT_DATE), CURRENT_DATE, '" + idAlimento + "',"
                    + "" + vacuno.getIdPredio() + ") ");
            st2.executeUpdate();
            st2.close();
        }
    }

    public String eliminarFinca(Finca finca) throws Exception {
        PreparedStatement st;
        String eliminado;
        ArrayList<Predio> listapredio = new ArrayList();

        listapredio = getPredios(String.valueOf(finca.getID()));
        if (listapredio.size() > 0) {
            eliminado = "No se puede eliminar la finca, ya que tiene predios registrados\n"
                    + "en ella. Primero elimine los predios.";
        } else {
            st = this.getConexion().prepareStatement("UPDATE public.\"FINCA\"\n"
                    + "   SET \"ELIMINADO\"= TRUE\n"
                    + " WHERE \"ID_FINCA\" = " + finca.getID() + "");
            st.executeUpdate();
            st.close();
            eliminado = "Finca eliminada correctamente.";
        }
        return eliminado;
    }

    public String eliminarVacuno(String idVacuno) throws Exception {
        PreparedStatement st;
        String eliminado = "";
        try {
            st = this.getConexion().prepareStatement("UPDATE public.\"VACUNO\"\n"
                    + "   SET \"ELIMINADO\"= TRUE\n"
                    + " WHERE \"ID_VACUNO\" = '" + idVacuno + "' ");
            st.executeUpdate();
            eliminado = "Vacuno eliminado correctamente";
            st.close();
        } catch (SQLException ex) {
            eliminado = "No fue posible eliminar el vacuno";
            Logger.getLogger(ColcienciasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eliminado;
    }

    public void insertarPredio(Predio predio) throws Exception {
        PreparedStatement st;
        st = this.getConexion().prepareStatement("INSERT INTO public.\"PREDIO\"(\"ID_PREDIO\", \"DESCRIPCION\","
                + "\"TIPO_ALIMENTACION\", \"TIPO_TERRENO\", \"FINCA\",\"CANTIDAD_MAX\")\n"
                + "VALUES (DEFAULT,'" + predio.getDescripcion() + "'," + predio.getIdTipoAlimentacion() + ","
                + "" + predio.getIdTipoTerreno() + ", " + predio.getIdFinca() + ", " + predio.getCantidadMax() + " )");
        st.executeUpdate();
        st.close();
    }

    public void actualizarPredio(Predio predio) throws Exception {
        PreparedStatement st;
        st = this.getConexion().prepareStatement("UPDATE public.\"PREDIO\" SET \n"
                + "\"DESCRIPCION\"= '" + predio.getDescripcion() + "', \"TIPO_ALIMENTACION\"=" + predio.getIdTipoAlimentacion() + ","
                + " \"TIPO_TERRENO\"= " + predio.getIdTipoTerreno() + ",\n"
                + " \"CANTIDAD_MAX\"= " + predio.getCantidadMax() + "\n"
                + " WHERE \"ID_PREDIO\" =  '" + predio.getID() + "'");
        st.executeUpdate();
        st.close();
    }

    public String eliminarPredio(String idPredio) throws Exception {
        PreparedStatement st;
        String eliminado;
        ArrayList<Vacuno> listavacunos = new ArrayList();

        listavacunos = getVacunos(idPredio);
        if (listavacunos.size() > 0) {
            eliminado = "No se puede eliminar el predio, ya que tiene vacunos registrados\n"
                    + "en el. Primero elimine o mueva los vacunos a otro predio.";
        } else {
            st = this.getConexion().prepareStatement("UPDATE public.\"PREDIO\"\n"
                    + "   SET \"ELIMINADO\"= TRUE\n"
                    + " WHERE \"ID_PREDIO\" = " + idPredio + "");
            st.executeUpdate();
            st.close();
            eliminado = "Predio eliminado correctamente.";
        }
        return eliminado;
    }

    public PropiedadAlimento getNutritionalTable(String tipoAlimento) {
        PreparedStatement st1;
        ResultSet result;
        PropiedadAlimento table = null;
        try {
            st1 = this.getConexion().prepareStatement("SELECT \"TIPO_ALIMENTO\", \"PROTEINA_CRUDA\", \"PROTEINA_DIGESTIVA\", \"FIBRA_CRUDA\", \n"
                    + "  \"CARBOHIDRATO\", \"EXTRACTOR_ETERO\"\n"
                    + "  FROM public.\"PROPIEDADES_ALIMENTO\"\n"
                    + "  WHERE \"TIPO_ALIMENTO\" = '" + tipoAlimento + "'");
            result = st1.executeQuery();
            while (result.next()) {
                table = new PropiedadAlimento(tipoAlimento, result.getDouble("PROTEINA_CRUDA"),
                        result.getDouble("PROTEINA_DIGESTIVA"),
                        result.getDouble("FIBRA_CRUDA"), result.getDouble("CARBOHIDRATO"),
                        result.getDouble("EXTRACTOR_ETERO"));
            }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(ColcienciasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return table;
    }

    public double getCantidadAlimento(Vacuno vacuno) {
        PreparedStatement st1;
        ResultSet result;
        double alimento = 0.0;
        try {
            if (vacuno.getIdCategoria() != 4) {
                st1 = this.getConexion().prepareStatement("SELECT  \"CANTIDAD\"\n"
                        + "FROM public.\"CONSUMO_ALIMENTO\"\n"
                        + "WHERE \"CATEGORIA_VACUNO\" = '" + vacuno.getIdCategoria() + "'");
                result = st1.executeQuery();
                while (result.next()) {
                    alimento = result.getDouble("CANTIDAD");
                }
                result.close();
            } else {
                st1 = this.getConexion().prepareStatement("SELECT \"CATEGORIA_VACUNO\", \"CANTIDAD\"\n"
                        + "FROM public.\"CONSUMO_ALIMENTO\"\n"
                        + "WHERE \"CATEGORIA_VACUNO\" = '" + vacuno.getIdCategoria() + "'");
                result = st1.executeQuery();
                while (result.next()) {
                    alimento = (result.getDouble("CANTIDAD") * vacuno.getPeso());
                }
                result.close();

            }
        } catch (SQLException ex) {
            Logger.getLogger(ColcienciasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alimento;
    }

    public double getCantidadLiquido(Vacuno vacuno) {
        PreparedStatement st1;
        ResultSet result;
        double liquido = 0.0;
        try {
            if (vacuno.getIdCategoria() != 4) {
                st1 = this.getConexion().prepareStatement("SELECT  \"CANTIDAD\"\n"
                        + "FROM public.\"CONSUMO_LIQUIDO\"\n"
                        + "WHERE \"CATEGORIA_VACUNO\" = '" + vacuno.getIdCategoria() + "'");
                result = st1.executeQuery();
                while (result.next()) {
                    liquido = result.getDouble("CANTIDAD");
                }
                result.close();
            } else {
                st1 = this.getConexion().prepareStatement("SELECT \"CANTIDAD\"\n"
                        + "FROM public.\"CONSUMO_LIQUIDO\"\n"
                        + "WHERE \"CATEGORIA_VACUNO\" = '" + vacuno.getIdCategoria() + "'");
                result = st1.executeQuery();
                while (result.next()) {
                    liquido = (result.getDouble("CANTIDAD") * vacuno.getPeso());
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ColcienciasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liquido;
    }

    public double getCantidadHeces(Vacuno vacuno) {
        PreparedStatement st1;
        ResultSet result;
        double heces = 0.0;
        try {
            if (vacuno.getIdCategoria() != 4) {
                st1 = this.getConexion().prepareStatement("SELECT  \"CANTIDAD\"\n"
                        + "FROM public.\"CANTIDAD_HECES\"\n"
                        + "WHERE \"CATEGORIA_VACUNO\" = '" + vacuno.getIdCategoria() + "'");
                result = st1.executeQuery();
                while (result.next()) {
                    heces = result.getDouble("CANTIDAD");
                }
                result.close();
            } else {
                st1 = this.getConexion().prepareStatement("SELECT  \"CANTIDAD\"\n"
                        + "FROM public.\"CANTIDAD_HECES\"\n"
                        + "WHERE \"CATEGORIA_VACUNO\" = '" + vacuno.getIdCategoria() + "'");
                result = st1.executeQuery();
                while (result.next()) {
                    heces = (result.getDouble("CANTIDAD") * vacuno.getPeso());
                }
                result.close();

            }
        } catch (SQLException ex) {
            Logger.getLogger(ColcienciasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return heces;
    }

    public Simulacion getSimulacion(int idPredio) {
        Simulacion simulacion = null;
        PreparedStatement st1;
        ResultSet result;
        try {
            st1 = this.getConexion().prepareStatement("SELECT P.\"ID_PREDIO\", P.\"DESCRIPCION\", T.\"DESCRIPCION\" AS \"TIPOTERRENO\", COUNT(V.\"ID_VACUNO\") filter (WHERE V.\"CATEGORIA\" = '1') as \"TOTALMAMONES\",\n"
                    + "COUNT(V.\"ID_VACUNO\") filter (WHERE V.\"CATEGORIA\" = '2') as \"TOTALDESTETADOS\",COUNT(V.\"ID_VACUNO\") filter (WHERE V.\"CATEGORIA\" = '3') as \"NOVILLOS\",\n"
                    + "COUNT(V.\"ID_VACUNO\") filter (WHERE V.\"CATEGORIA\" = '4') as \"VACAS\",COUNT(V.\"ID_VACUNO\") as \"CANTBOVINOS\",TA.\"DESCRIPCION\" AS \"TIPOALIMENTO\", \n"
                    + "PA.\"PROTEINA_CRUDA\", PA.\"PROTEINA_DIGESTIVA\", PA.\"FIBRA_CRUDA\",PA.\"CARBOHIDRATO\", PA.\"EXTRACTOR_ETERO\" FROM public.\"PREDIO\" P\n"
                    + "INNER JOIN public.\"TIPO_TERRENO\" T ON (T.\"ID_TIPO_TERRENO\" = P.\"TIPO_TERRENO\")\n"
                    + "INNER JOIN public.\"TIPO_ALIMENTACION\" TA ON (TA.\"ID_TIPO_ALIMENTACION\" = P.\"TIPO_ALIMENTACION\")\n"
                    + "INNER JOIN public.\"PROPIEDADES_ALIMENTO\" PA ON (TA.\"ID_TIPO_ALIMENTACION\" = PA.\"TIPO_ALIMENTO\")\n"
                    + "INNER JOIN public.\"VACUNO\" V ON (V.\"PREDIO\" = P.\"ID_PREDIO\")\n"
                    + "INNER JOIN public.\"CATEGORIA_VACUNO\" C ON (C.\"ID_CATEGORIA\" = V.\"CATEGORIA\") \n"
                    + "WHERE P.\"ID_PREDIO\" = " + idPredio + " GROUP BY P.\"ID_PREDIO\",T.\"DESCRIPCION\", "
                    + "TA.\"DESCRIPCION\", PA.\"TIPO_ALIMENTO\"");
            result = st1.executeQuery();
            while (result.next()) {
                simulacion = new Simulacion(result.getInt("ID_PREDIO"), result.getString("DESCRIPCION"),
                        result.getString("TIPOTERRENO"), result.getInt("TOTALMAMONES"), result.getInt("TOTALDESTETADOS"),
                        result.getInt("NOVILLOS"), result.getInt("VACAS"), result.getInt("CANTBOVINOS"),
                        result.getString("TIPOALIMENTO"), result.getDouble("PROTEINA_CRUDA"), result.getDouble("PROTEINA_DIGESTIVA"),
                        result.getDouble("FIBRA_CRUDA"), result.getDouble("CARBOHIDRATO"), result.getDouble("EXTRACTOR_ETERO"));
            }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(ColcienciasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return simulacion;
    }

    public void guardarSimulacion(Simulacion simulacion, ArrayList<Vacuno> vacunos) {
        try {
            PreparedStatement st1, st2 = null;
            ResultSet result, result2;
            st1 = this.getConexion().prepareStatement("INSERT INTO public.\"SIMULACION\"(\n"
                    + "\"ID_SIMULACION\", \"ID_PREDIO\", \"TOTAL_MAMONES\", \"TOTAL_DESTETADOS\", \n"
                    + "\"TOTAL_NOVILLOS\", \"TOTAL_VACAS\", \"TOTAL_BOVINOS\", \"TIPO_ALIMENTO\", \n"
                    + "\"consAguaMamones\", \"consAguaDestetados\", \"consAguaNovillos\", \n"
                    + "\"consAguaVacas\", \"consAlimentoMamones\", \"consAlimentDestetados\", \n"
                    + "\"consAlimentoNovillos\", \"consAlimentoVacas\", \"orinaMamones\", \n"
                    + "\"orinaDestetados\", \"orinaNovillos\", \"orinaVacas\", \"contaminacionMamones\", \n"
                    + "\"contaminacionDestetados\", \"contaminacionNovillos\", \"contaminacionVacas\", \n"
                    + "\"FECHA\", \"totalCO2\", \"totalCH4\", \"totalNO2\")\n"
                    + " VALUES (DEFAULT," + simulacion.getIdpredio() + "," + simulacion.getTotalMamones() + "," + simulacion.getTotalDestetados() + ", \n"
                    + "" + simulacion.getTotalNovillos() + "," + simulacion.getTotalVacas() + "," + simulacion.getCantidadBovinos() + ",'" + simulacion.getTipoAlimento() + "', \n"
                    + "" + simulacion.getConsAguaMamones() + "," + simulacion.getConsAguaDestetados() + "," + simulacion.getConsAguaNovillos() + ", \n"
                    + "" + simulacion.getConsAguaVacas() + "," + simulacion.getConsAlimentoMamones() + "," + simulacion.getConsAlimentDestetados() + ", \n"
                    + "" + simulacion.getConsAlimentoNovillos() + "," + simulacion.getConsAlimentoVacas() + "," + simulacion.getOrinaMamones() + ", \n"
                    + "" + simulacion.getOrinaDestetados() + "," + simulacion.getOrinaNovillos() + "," + simulacion.getOrinaVacas() + "," + simulacion.getContaminacionMamones() + ", \n"
                    + "" + simulacion.getContaminacionDestetados() + "," + simulacion.getContaminacionNovillos() + "," + simulacion.getContaminacionVacas() + ", \n"
                    + "CURRENT_DATE," + simulacion.getTotalCO2() + "," + simulacion.getTotalCH4() + "," + simulacion.getTotalNO2() + ") RETURNING \"ID_SIMULACION\"");
            result = st1.executeQuery();
            while (result.next()) {
                for (int i = 0; i < vacunos.size(); i++) {
                    st2 = this.getConexion().prepareStatement("INSERT INTO public.\"SIMULACION_VACUNO\"(\n"
                            + " \"ID_SIMULACION\", \"ID_VACUNO\", \"PESO\")\n"
                            + " VALUES (" + result.getInt("ID_SIMULACION") + "," + vacunos.get(i).getID() + "," + vacunos.get(i).getPeso() + ")");
                    st2.executeUpdate();
                }
            }
            st2.close();
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(ColcienciasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Simulacion getSimulacionBYID(int idsimulacion) {
        Simulacion simulacion = null;
        Predio predio = null;
        PropiedadAlimento alimento = null;
        PreparedStatement st1;
        ResultSet result;
        try {
            st1 = this.getConexion().prepareStatement("SELECT \"ID_PREDIO\", \"TOTAL_MAMONES\", \"TOTAL_DESTETADOS\", \n"
                    + " \"TOTAL_NOVILLOS\", \"TOTAL_VACAS\", \"TOTAL_BOVINOS\", \"TIPO_ALIMENTO\", \n"
                    + " \"consAguaMamones\", \"consAguaDestetados\", \"consAguaNovillos\", \n"
                    + " \"consAguaVacas\", \"consAlimentoMamones\", \"consAlimentDestetados\", \n"
                    + " \"consAlimentoNovillos\", \"consAlimentoVacas\", \"orinaMamones\", \n"
                    + "  \"orinaDestetados\", \"orinaNovillos\", \"orinaVacas\", \"contaminacionMamones\", \n"
                    + "  \"contaminacionDestetados\", \"contaminacionNovillos\", \"contaminacionVacas\", \n"
                    + "  \"FECHA\", \"totalCO2\", \"totalCH4\", \"totalNO2\"\n"
                    + "  FROM public.\"SIMULACION\" WHERE \"ID_SIMULACION\" = " + idsimulacion + "");
            result = st1.executeQuery();
            while (result.next()) {
                predio = getPredioBYID(result.getInt("ID_PREDIO"));
                alimento = getNutritionalTable(result.getString("TIPO_ALIMENTO"));
                simulacion = new Simulacion(result.getInt("ID_PREDIO"), predio.getDescripcion(),
                        predio.getDescTipoAlimentacion(), result.getInt("TOTAL_MAMONES"), result.getInt("TOTAL_DESTETADOS"),
                        result.getInt("TOTAL_NOVILLOS"), result.getInt("TOTAL_VACAS"), result.getInt("TOTAL_BOVINOS"),
                        alimento.getTipoAlimento(), alimento.getProteinaCruda(), alimento.getProteinaDigestiva(),
                        alimento.getFibraCruda(), alimento.getCarbohidrato(), alimento.getExtractorEtero(), result.getDouble("consAguaMamones"),
                        result.getDouble("consAguaDestetados"), result.getDouble("consAguaNovillos"),
                        result.getDouble("consAguaVacas"), result.getDouble("consAlimentoMamones"), result.getDouble("consAlimentDestetados"),
                        result.getDouble("consAlimentoNovillos"), result.getDouble("consAlimentoVacas"), result.getDouble("orinaMamones"),
                        result.getDouble("orinaDestetados"), result.getDouble("orinaNovillos"), result.getDouble("orinaVacas"),
                        result.getDouble("contaminacionMamones"), result.getDouble("contaminacionDestetados"), result.getDouble("contaminacionNovillos"),
                        result.getDouble("contaminacionVacas"), result.getDouble("totalCO2"),
                        result.getDouble("totalCH4"), result.getDouble("totalNO2"));

            }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(ColcienciasDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ColcienciasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return simulacion;
    }

    public ArrayList<RegistroSimulacion> getSimulaciones(ConsultaSimulacion parametros) {
        ArrayList<RegistroSimulacion> simulaciones = null;
        RegistroSimulacion registro = null;
        PreparedStatement st1;
        ResultSet result;
        try {
            st1 = this.getConexion().prepareStatement("SELECT S.\"ID_SIMULACION\", S.\"FECHA\",P.\"DESCRIPCION\" AS PREDIO ,"
                    + "S.\"TOTAL_BOVINOS\", S.\"TIPO_ALIMENTO\"\n"
                    + "FROM public.\"SIMULACION\" S\n"
                    + "INNER JOIN public.\"PREDIO\"P ON (S.\"ID_PREDIO\" = P.\"ID_PREDIO\")\n"
                    + "WHERE P.\"ID_FINCA\" = " + parametros.getIdFinca() + " AND "
                    + "S.\"FECHA\" BETWEEN('" + parametros.getFechaInicio() + "' AND '" + parametros.getFechaFin() + "')");
            result = st1.executeQuery();
            while (result.next()) {
                registro = new RegistroSimulacion(result.getInt("ID_SIMULACION"), Fecha.valueOf(result.getDate("FECHA").toString()),
                        result.getString("PREDIO"), result.getInt("TOTAL_BOVINOS"), result.getString("TIPO_ALIMENTO"));
                simulaciones.add(registro);
            }
            st1.close();

        } catch (SQLException ex) {
            Logger.getLogger(ColcienciasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return simulaciones;
    }

}
