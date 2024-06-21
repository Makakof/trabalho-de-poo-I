package utilitarios;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class StringUtils
{
    // deixa todas as letras em MAIUSCULO e retira os espaços em brando
    public static String formatarPadraoCapturaDeDados(String string)
    {
        return string.toUpperCase().replaceAll("\\s+", "");
    }

    public static String retirarAcentosESinais (String string){

        string = string.replace("á", "a");
        string = string.replace("ç", "c");
        string = string.replace("Á", "A");
        string = string.replace("Ç", "C");

        return string;
    }



    public static String getDiaDaSemana(LocalDateTime data){

        String dia = data.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()).toUpperCase();
        dia = dia.replace("-FEIRA", "");
        dia = dia.replace("Á", "A");
        return dia;
    }
}
