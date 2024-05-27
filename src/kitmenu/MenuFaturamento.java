package kitmenu;

import dados.Repositorio;
import ingressos.TicketEstacionamento;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class MenuFaturamento
{
    private final UI terminal;

    public MenuFaturamento()
    {
        this.terminal = UI.getInstance();
    }

    public void realizarFaturamento()
    {
        ArrayList<TicketEstacionamento> tickets = Repositorio.getInstance().getLogTickets();
        String dataInicio, dataFim;
        double faturamento;

        dataInicio = terminal.selecionarString("Digite a data de inicio (dia/mes/ano): ");
        dataFim = terminal.selecionarString("Digite a data de fim (dia/mes/ano): ");

        faturamento = calculaFaturamento(dataInicio, dataFim);
        terminal.exibir("O faturamento no periodo selecionado foi de " + faturamento);
    }

    private double calculaFaturamento(String dataInicioString, String dataFimString)
    {
        ArrayList<TicketEstacionamento> tickets = Repositorio.getInstance().getLogTickets();
        double soma = 0.0;
        LocalDateTime dataInicio = LocalDateTime.parse(dataInicioString);
        LocalDateTime dataFim = LocalDateTime.parse(dataFimString);

        for(TicketEstacionamento ticketAtual : tickets)
        {
            if(dataInicio.isAfter(ticketAtual.getDataInicio()) && dataFim.isBefore(ticketAtual.getDataFim()))
            {
                soma += ticketAtual.getTotalPagar();
            }
        }
        return soma;
    }

}
