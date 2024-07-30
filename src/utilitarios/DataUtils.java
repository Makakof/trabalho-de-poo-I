package utilitarios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtils
{
    public static DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static DateTimeFormatter formatarDataSemHora = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String formatarData(LocalDateTime data)
    {
        return data.format(formatadorData);
    }

    // converte os
    public static boolean equalsData(LocalDateTime dataInicio, LocalDateTime dataFim){

        LocalDate inicio = dataInicio.toLocalDate();
        LocalDate fim = dataFim.toLocalDate();

        return inicio.equals(fim);
    }
}
