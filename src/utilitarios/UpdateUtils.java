package utilitarios;

import enums.VagaStatus;
import ingressos.TicketEstacionamento;
import tarifacao.TarifaMensalista;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UpdateUtils {
    public static void atualizarStatusDasVagas(ArrayList<TicketEstacionamento> tickets) {

        for (TicketEstacionamento ticket : tickets){

            if (ticket.getTarifa() instanceof TarifaMensalista && ticket.getDataFim().isBefore(LocalDateTime.now()) && ticket.getVaga().getStatus() == VagaStatus.OCUPADA){

                ticket.getVaga().setVagaStatus(VagaStatus.DISPONIVEL);
            }
        }
    }
}
