package utilitarios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtil
{
    public static DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    public static String formatarData(LocalDateTime data)
    {
        return data.format(formatadorData);
    }
}
