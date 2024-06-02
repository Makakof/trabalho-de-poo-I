package utilitarios;

import enums.HoristaMensalista;
import enums.VagaStatus;
import ingressos.TicketEstacionamento;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UpdateUtils {
    public static void atualizarStatusDasVagas(ArrayList<TicketEstacionamento> tickets) {

        for (TicketEstacionamento ticket : tickets){

            if (ticket.getTarifa().getModoDeEstacionar() == HoristaMensalista.MENSALISTA && ticket.getDataFim().isBefore(LocalDateTime.now()) && ticket.getVaga().getStatus() == VagaStatus.OCUPADA){

                ticket.getVaga().setVagaStatus(VagaStatus.DISPONIVEL);
            }
        }
    }
}
