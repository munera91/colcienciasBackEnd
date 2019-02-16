package com.colcienciasWeb.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Representa una fecha .
 *
 * @author Santiago Román
 * @version 6/10/2018
 */
public class Fecha {

    public static Calendar cal = Calendar.getInstance();
    public static int QUINCENA88 = 1, QUINCENA96 = 2, QUINCENA104 = 3, QUINCENA80 = 4;
    
    /** el dia. */
    private int date;
    
    /** El mes. */
    private int month;
    
    /** El año. */
    private int year;

    /**
     * Instancia un nuevo objeto Fecha.
     *
     * @param year Year
     * @param month Month
     * @param day Day
     */
    public Fecha(int year, int month, int day) {
        setYear(year);
        setMonth(month);
        setDate(day);
    }

    public static Fecha valueOf(String s) {
        //Llamamos al valueof de la clase Date de java
        if (s == null || s.equals("")) {
            return null;
        }
        java.sql.Date d = java.sql.Date.valueOf(s);
        return new Fecha(d.getYear() + 1900, d.getMonth() + 1, d.getDate());
    }

    public Fecha copy() {
        return new Fecha(this.getYear(), this.getMonth(), this.getDate());
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + date;
    }

    public String toStringLetras() {
        return date + " de " + MesIntAString(month) + " de " + year;
    }

    /**
     * retorna la cantidad de dias entre dos fechas
     *
     * @param fecha1 Fecha 1
     * @param fecha2 Fecha 2
     * @return the int
     */
    public static int restarFechas(Fecha fecha1, Fecha fecha2) {
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


    /**
     * Retorna el numero del dia de la semana.
     *
     * @param dia Dia
     * @param year Year
     * @param month Month
     * @param quincena Quincena
     * @param festivos1 Festivos 1
     * @param festivos2 Festivos 2
     * @return num dia
     */
    public static int getNumDia(String dia, int year, int month, int quincena,
            Integer[] festivos1, Integer[] festivos2) {
        int sum = 0;
        int inicio = quincena == 1 ? 1 : 16;
        int fin = quincena == 1 ? 15 : maxDiaMes(year, month);
        int d;
        if (dia.equals("T")) {
            return fin - inicio + 1;
        } else if (dia.equals("Q")) {
            return 1;
        } else if (dia.equals("L")) {
            d = Calendar.MONDAY;
        } else if (dia.equals("M")) {
            d = Calendar.TUESDAY;
        } else if (dia.equals("W")) {
            d = Calendar.WEDNESDAY;
        } else if (dia.equals("J")) {
            d = Calendar.THURSDAY;
        } else if (dia.equals("V")) {
            d = Calendar.FRIDAY;
        } else if (dia.equals("S")) {
            d = Calendar.SATURDAY;
        } else if (dia.equals("D")) {
            d = Calendar.SUNDAY;
        } else if (dia.equals("F")) {
            return quincena == 1 ? festivos1.length : festivos2.length;
        } else {
            return 0;
        }
        for (int i = inicio; i <= fin; i++) {
            cal.set(year, month - 1, i);
            if (cal.get(Calendar.DAY_OF_WEEK) == d) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * Aumentar en un dia una fecha.
     *
     * @param t T
     */
    public static void aumentarDia(Fecha t) {
        t.setDate(t.getDate() + 1);  //aumentamos el dia
        //Si el dia aumentado se pasa de los dias del mes actual, se aumenta el mes y el dia se pone en 1
        if (t.getDate() > maxDiaMes(t.getYear(), t.getMonth())) {
            t.setMonth(t.getMonth() + 1);
            t.setDate(1);
            //Si el mes se pasa a 13, aumentamos el año y el mes seria 1
            if (t.getMonth() > 12) {
                t.setYear(t.getYear() + 1);
                t.setMonth(1);
            }
        }
    }

    /**
     * Retorna los dias maximos de una mes.
     *
     * @param year Year
     * @param m M
     * @return the int
     */
    public static int maxDiaMes(int year, int m) {
        switch (m) {
            //Meses con 31 dias
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            //Si es febrero, debemos mirar si el año es bisiesto
            case 2:
                if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
                    return 29;
                }
                return 28;
            //Resto de meses 30 dias
            default:
                return 30;
        }
    }


    /**
     * Convierte un mes en formato texto a numero
     *
     * @param mes Mes
     * @return the int
     */
    public static int MesStringAInt(String mes) {
        //Ignoramos mayusculas y minusclas
        if (mes.equalsIgnoreCase("Enero")) {
            return 1;
        } else if (mes.equalsIgnoreCase("Febrero")) {
            return 2;
        } else if (mes.equalsIgnoreCase("Marzo")) {
            return 3;
        } else if (mes.equalsIgnoreCase("Abril")) {
            return 4;
        } else if (mes.equalsIgnoreCase("Mayo")) {
            return 5;
        } else if (mes.equalsIgnoreCase("Junio")) {
            return 6;
        } else if (mes.equalsIgnoreCase("Julio")) {
            return 7;
        } else if (mes.equalsIgnoreCase("Agosto")) {
            return 8;
        } else if (mes.equalsIgnoreCase("Septiembre")) {
            return 9;
        } else if (mes.equalsIgnoreCase("Octubre")) {
            return 10;
        } else if (mes.equalsIgnoreCase("Noviembre")) {
            return 11;
        } else if (mes.equalsIgnoreCase("Diciembre")) {
            return 12;
        } else {
            return 1;
        }
    }

    /**
     * convierte un mes en formato numero a texto
     *
     * @param mes Mes
     * @return the string
     */
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

    /**
     * Retorna el nombre del dia en español a partir del nombre en ingles
     *
     * @param dia Dia
     * @return nombre dia from english
     */
    public static String getNombreDiaFromEnglish(String dia) {
        System.out.println(dia);
        //Ignoramos mayusculas y minusclas
        if (dia.trim().equalsIgnoreCase("monday")) {
            return "Lunes";
        } else if (dia.trim().equalsIgnoreCase("tuesday")) {
            return "Martes";
        } else if (dia.trim().equalsIgnoreCase("wednesday")) {
            return "Miercoles";
        } else if (dia.trim().equalsIgnoreCase("thursday")) {
            return "Jueves";
        } else if (dia.trim().equalsIgnoreCase("friday")) {
            return "Viernes";
        } else if (dia.trim().equalsIgnoreCase("saturday")) {
            return "Sabado";
        } else if (dia.trim().equalsIgnoreCase("sunday")) {
            return "Domingo";
        } else {
            return "ND";
        }
    }

    /**
     * Retorna fecha actual.
     *
     * @return fecha actual
     */
    public static Fecha getFechaActual() {
        Calendar calendario = Calendar.getInstance();
        int year = calendario.get(Calendar.YEAR);
        int month = calendario.get(Calendar.MONTH) + 1; //Empieza mes 0
        int day = calendario.get(Calendar.DATE);
        return new Fecha(year, month, day);
    }

    /**
     * Retorna el tipo de dia de una fecha
     *
     * @param year Year
     * @param month Month
     * @param date Date
     * @return type day
     */
    public static String getTypeDay(int year, int month, int date) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, date);
        int tDia = cal.get(Calendar.DAY_OF_WEEK);
        switch (tDia) {
            case Calendar.SUNDAY:
                return "D";
            case Calendar.MONDAY:
                return "L";
            case Calendar.TUESDAY:
                return "M";
            case Calendar.WEDNESDAY:
                return "W";
            case Calendar.THURSDAY:
                return "J";
            case Calendar.FRIDAY:
                return "V";
            case Calendar.SATURDAY:
                return "S";
            default:
                return "U";
        }
    }

    /**
     * Retorna el nombre del dia a parti de la inciial
     *
     * @param d D
     * @return nombre dia
     */
    public static String getNombreDia(String d) {
        if (d.equals("L")) {
            return "Lunes";
        } else if (d.equals("M")) {
            return "Martes";
        } else if (d.equals("W")) {
            return "Miercoles";
        } else if (d.equals("J")) {
            return "Jueves";
        } else if (d.equals("V")) {
            return "Viernes";
        } else if (d.equals("S")) {
            return "Sabado";
        } else if (d.equals("D")) {
            return "Domingo";
        } else if (d.equals("Q")) {
            return "Quincena";
        } else if (d.equals("F")) {
            return "Festivo";
        } else if (d.equals("T")) {
            return "Dia";
        } else {
            return "?";
        }
    }

    /**
     * Chequea si el dia es domingo.
     *
     * @param year Year
     * @param month Month
     * @param day Day
     * @return true, si es domingo
     */
    public static boolean isDomingo(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        int tDia = cal.get(Calendar.DAY_OF_WEEK);
        return tDia == Calendar.SUNDAY;
    }

    /**
     * Retorna el mes anterior.
     *
     * @param year Year
     * @param month Month
     * @return mes anterior
     */
    public static Fecha getMesAnterior(int year, int month) {
        if (month > 1) {
            return new Fecha(year, month - 1, 1);
        } else {
            return new Fecha(year - 1, 12, 1);
        }
    }

    /**
     * Retorna el mes siguiente.
     *
     * @param year Year
     * @param month Month
     * @return mes siguiente
     */
    public static Fecha getMesSiguiente(int year, int month) {
        if (month < 12) {
            return new Fecha(year, month + 1, 1);
        } else {
            return new Fecha(year + 1, 1, 1);
        }
    }

    /**
     * Retorna el dia habil siguiente.
     *
     * @param fecha Fecha
     * @param festivos Festivos
     * @return dia habil siguiente
     */
   

//    public static int restar(String fechaIni, String fechaFin) {
//        Date dateIni = new Date(fechaIni);
//        Date dateFin = new Date(fechaFin);
//        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
//        String fechaInicioString = df.format(dateIni);
//        try {
//            dateIni = df.parse(fechaInicioString);
//        } catch (ParseException ex) {
//            return 0;
//        }
//
//        String fechaFinalString = df.format(dateFin);
//        try {
//            dateFin = df.parse(fechaFinalString);
//        } catch (ParseException ex) {
//            return 0;
//        }
//
//        long fechaInicialMs = dateIni.getTime();
//        long fechaFinalMs = dateFin.getTime();
//        long diferencia = fechaFinalMs - fechaInicialMs;
//        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
//        return ((int) dias);
//    }
    /**
 * Retorna dia.
 *
 * @return el dia
 */
    public int getDate() {
        return date;
    }

    /**
     * Establece dia.
     *
     * @param date el nuevo dia
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * Retorna mes.
     *
     * @return el mes
     */
    public int getMonth() {
        return month;
    }

    /**
     * Establece mes.
     *
     * @param month el nuevo mes
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Retorna año.
     *
     * @return el año
     */
    public int getYear() {
        return year;
    }

    /**
     * Establece año.
     *
     * @param year el nuevo año
     */
    public void setYear(int year) {
        this.year = year;
    }
}
