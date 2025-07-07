package com.leandro.medcontrol.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HorarioUtils {
    private static final String FORMATO_HORA = "HH:mm";
    // Valida se o horário está no formato HH:mm
    public static boolean validarHorario(String horario) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_HORA);
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(horario);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    // Formata um Date para String no formato HH:mm
    public static String formatarHorario(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_HORA);
        return sdf.format(date);
    }
}