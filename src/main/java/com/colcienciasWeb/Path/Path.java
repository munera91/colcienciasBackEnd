/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.Path;

/**
 *
 * @author Usuario
 */
public class Path {
    public class PathFinca{
        public static final String FINCA = "/finca";
        public static final String FINCA_ID = "/finca/{ID}";
    }
    public class PathVacuno{
        public static final String VACUNO = "/vacuno";
        public static final String VACUNO_ID = "/vacuno/{idVacuno}";
        public static final String VACUNOS = "/vacunos/{idPredio}";
    }
    public class PathPredio{
        public static final String PREDIO = "/predio";
        public static final String PREDIO_ID = "/predio/{idPredio}";
        public static final String PREDIOS = "/predios/{idFinca}";
    }
    
    public class PathMunicipio{
        public static final String MUNICIPIOS =  "/municipios/{idDepartamento}";
    }
}
