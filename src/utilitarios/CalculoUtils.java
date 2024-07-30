package utilitarios;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CalculoUtils {
    public static long calcularTempoEstacionado(LocalDateTime dataInicio, LocalDateTime dataFim) {

        long diferencaMinutos = ChronoUnit.MINUTES.between(dataInicio, dataFim);

        double diferencaHoras = diferencaMinutos / 60.0;

        return (long) Math.ceil(diferencaHoras);
    }
}
