package utilitarios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class DataUtil
{
    public static DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static DateTimeFormatter formatarDataSemHora = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String formatarData(LocalDateTime data)
    {
        return data.format(formatadorData);
    }
}
