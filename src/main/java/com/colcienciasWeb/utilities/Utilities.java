/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colcienciasWeb.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Usuario
 */
public class Utilities {

    public static String MesIntAString(int mes) {
        switch (mes) {
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
            default:
                return "Enero";
        }
    }

    public static double redondear2Decimales(double d) {
        //Multipliacmos el double por 100 y sacamos su parte entera,
        //luego dividir entre 100.0 y así obtenemos el numero como decimal
        //redondeado con 2 cifras decimales
        int aux1 = (int) Math.round(d * 100);
        return aux1 / 100.0;
    }

    /**
     * Retorna el MD5 de un string .
     *
     * @param message Message
     * @return string message digest
     */
    public static String getStringMessageDigest(String message) {
        byte[] digest = null;
        byte[] buffer = message.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error creando Digest");
        }
        return toHexadecimal(digest);
    }

    /**
     * Convierte un array de bites a hexadecimal.
     *
     * @param digest Digest
     * @return the string
     */
    private static String toHexadecimal(byte[] digest) {
        String hash = "";
        for (byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) {
                hash += "0";
            }
            hash += Integer.toHexString(b);
        }
        return hash;
    }
    
    public static int restarFechas(java.util.Date fecha1, java.util.Date fecha2) {
        GregorianCalendar t1 = new GregorianCalendar(fecha1.getYear(), fecha1.getMonth(), fecha1.getDate());
        GregorianCalendar t2 = new GregorianCalendar(fecha2.getYear(), fecha2.getMonth(), fecha2.getDate());
        int dias;
        if (t1.get(Calendar.YEAR) == t2.get(Calendar.YEAR)) {
            dias = t2.get(Calendar.DAY_OF_YEAR) - t1.get(Calendar.DAY_OF_YEAR);
        } else {
            int diasAnyo = t1.isLeapYear(t1.get(Calendar.YEAR)) ? 366 : 365;
            int rangoAnyos = t2.get(Calendar.YEAR) - t1.get(Calendar.YEAR);
            dias = (rangoAnyos * diasAnyo) + (t2.get(Calendar.DAY_OF_YEAR) - t1.get(Calendar.DAY_OF_YEAR));
        }
        return dias;
    }

}
