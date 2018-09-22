/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Data;

import com.colcienciasWeb.Model.Finca;
import com.colcienciasWeb.Model.Predio;
import com.colcienciasWeb.Model.Vacuno;
import java.util.ArrayList;


import java.util.List;

/**
 *
 * @author Usuario
 */
public class Data {
    
    private static ArrayList<Finca> listaFincas;
    private static ArrayList<Vacuno> listaVacunos;
    private static ArrayList<Predio> listaPredios;
    
    
    public static List<Finca> getListaFinca(){
        return listaFincas;
    }
    
    public static void setListaFinca (List<Finca> listaFinca){
        Data.listaFincas = listaFincas;
    }

    public static List<Vacuno> getListaVacunos() {
        return listaVacunos;
    }

    public static void setListaVacunos(List<Vacuno> listaVacuno) {
        Data.listaVacunos = listaVacunos;
    }

    public static ArrayList<Predio> getListaPredios() {
        return listaPredios;
    }

    public static void setListaPredios(ArrayList<Predio> listaPredios) {
        Data.listaPredios = listaPredios;
    }
    
    
    
}
