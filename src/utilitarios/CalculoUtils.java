package utilitarios;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CalculoUtils {
    public static long calcularHoras(LocalDateTime dataInicio, LocalDateTime dataFim) {

        long diferencaHoras = dataInicio.until(dataFim, ChronoUnit.HOURS);

        return (long) Math.ceil(diferencaHoras);
    }
}
