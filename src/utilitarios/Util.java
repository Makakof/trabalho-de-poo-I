package utilitarios;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Util {

    public static String diaDaSemanaString(LocalDateTime data){

        String dia = data.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()).toUpperCase();
        dia = dia.replace("-FEIRA", "");
        return dia;
    }
}
