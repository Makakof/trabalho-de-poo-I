package kitmenu;

import ingressos.TicketEstacionaBem;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class MenuFaturamento
{
    private UI terminal;

    public MenuFaturamento(UI terminal)
    {
        this.terminal = terminal;
    }

    public double realizaFaturamento(ArrayList<TicketEstacionaBem> tickets)
    {
        String dataInicio, dataFim;
        double faturamento;

        dataInicio = terminal.selecionarString("Digite a data de inicio: ");
        dataFim = terminal.selecionarString("Digite a data de fim: ");

        faturamento = calculaFaturamento(tickets,dataInicio, dataFim);
        return faturamento;
    }

    private double calculaFaturamento(ArrayList<TicketEstacionaBem> tickets, String dataInicioString, String dataFimString)
    {
        double soma = 0.0;
        LocalDateTime dataInicio = LocalDateTime.parse(dataInicioString);
        LocalDateTime dataFim = LocalDateTime.parse(dataFimString);

        for(TicketEstacionaBem ticketAtual : tickets)
        {
            if(dataInicio.isAfter(ticketAtual.getDataInicio()) && dataFim.isBefore(ticketAtual.getDataFim()))
            {
                // TODO soma += ticketAtual.getValorTotal();
            }
        }

        return 0.0;
    }

}
