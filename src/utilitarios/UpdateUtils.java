package utilitarios;

import enums.VagaStatus;
import ingressos.TicketEstacionamento;
import tarifacao.TarifaMensalista;

import java.time.LocalDateTime;
import java.util.List;

public class UpdateUtils {
    public static void atualizarStatusDasVagas(List<TicketEstacionamento> tickets) {

        for (TicketEstacionamento ticket : tickets){

            if (ticket.getTarifa() instanceof TarifaMensalista && ticket.getDataFim().isBefore(LocalDateTime.now()) && ticket.getVaga().getStatus() == VagaStatus.OCUPADA){

                ticket.getVaga().liberar();
            }
        }
    }
}
