package operacoes;

import ingressos.TicketEstacionamento;
import ingressos.TicketHorista;
import ingressos.TicketMensalista;
import tarifacao.TarifaMensalista;
import java.time.LocalDateTime;
import java.util.List;

public class FuncionalidadesTicket {

    public static TicketMensalista buscarTicketMensalista(List<TicketEstacionamento> tickets, String placa) {

        LocalDateTime dataAtual = LocalDateTime.now();

        for (TicketEstacionamento ticket : tickets) {

            if (placa.equals(ticket.getVeiculo().getPlaca()) && ticket.getTarifa() instanceof TarifaMensalista) {

                if (dataAtual.isBefore(ticket.getDataFim())) {
                    return (TicketMensalista) ticket;
                }
            }
        }

        return null;
    }

    public static TicketHorista buscarTicketHorista(List<TicketEstacionamento> tickets, String placa) {

        for (TicketEstacionamento ticket : tickets) {

            if (placa.equals(ticket.getVeiculo().getPlaca()) && ticket.getDataFim() == null) {
                return (TicketHorista) ticket;
            }

        }

        return null;
    }
}
