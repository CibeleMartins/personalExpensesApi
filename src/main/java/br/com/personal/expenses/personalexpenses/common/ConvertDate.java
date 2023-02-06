package br.com.personal.expenses.personalexpenses.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {
    
    // método static permite que não instancie a classe p/ utilização
    public static String convertDateForDateHour(Date date) {
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");

        String dateFormated = format.format(date);

        return dateFormated;
    }
}
