package utilitarios;

import enums.HoristaMensalista;
import enums.VagaStatus;
import ingressos.TicketEstacionamento;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;

public class Util {

    public static String diaDaSemanaString(LocalDateTime data){

        String dia = data.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()).toUpperCase();
        dia = dia.replace("-FEIRA", "");
        dia = dia.replace("Á", "A");
        return dia;
    }

    public static long calcularHoras(LocalDateTime dataInicio, LocalDateTime dataFim) {

        long diferencaHoras = dataInicio.until(dataFim, ChronoUnit.HOURS);

        return (long) Math.ceil(diferencaHoras);
    }

    public static void atualizarStatusDasVagas(ArrayList<TicketEstacionamento> tickets) {

        for (TicketEstacionamento ticket : tickets){

            if (ticket.getTarifa().getModoDeEstacionar() == HoristaMensalista.MENSALISTA && ticket.getDataFim().isBefore(LocalDateTime.now()) && ticket.getVaga().getStatus() == VagaStatus.OCUPADA){

                ticket.getVaga().setVagaStatus(VagaStatus.DISPONIVEL);
            }

        }

    }
}
