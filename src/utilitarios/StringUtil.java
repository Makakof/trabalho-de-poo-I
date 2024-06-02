package utilitarios;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class StringUtil
{
    public static String formatarPlaca(String string)
    {
        return string.toUpperCase().replaceAll("\\s", "");
    }
    public static String formatarCor(String string){return string.toUpperCase().replaceAll("\\s", "");}
    public static String formatarModelo(String string)
    {
        return string.toUpperCase().replaceAll("\\s", "");
    }
    public static String formatarTipo(String string)
    {
        return string.toUpperCase().replaceAll("\\s", "");
    }
    public static String formatarDiaDaSemana(String string)
    {
        return string.toUpperCase().replaceAll("\\s", "");
    }

    public static String diaDaSemanaString(LocalDateTime data){

        String dia = data.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()).toUpperCase();
        dia = dia.replace("-FEIRA", "");
        return dia;
    }
}
