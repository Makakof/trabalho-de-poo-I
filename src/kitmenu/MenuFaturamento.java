package kitmenu;

import dados.Repositorio;
import ingressos.TicketEstacionamento;
import interfaces.InterfaceUsuario;
import interfaces.Terminal;


import java.time.LocalDate;
import java.util.ArrayList;

import static utilitarios.DataUtils.formatarDataSemHora;

public class MenuFaturamento
{
    private final InterfaceUsuario interfaceUsuario;

    public MenuFaturamento()
    {
        this.interfaceUsuario = Terminal.getInstance();
    }

    public void realizarFaturamento()
    {
        String dataInicio, dataFim;
        double faturamento;

        dataInicio = interfaceUsuario.selecionarString("Digite a data de inicio (dia/mes/ano): ");
        dataFim = interfaceUsuario.selecionarString("Digite a data de fim (dia/mes/ano): ");

        faturamento = calcularFaturamento(dataInicio, dataFim);
        interfaceUsuario.exibir("O faturamento no periodo selecionado foi de " + faturamento);
    }

    private double calcularFaturamento(String dataInicioString, String dataFimString)
    {
        ArrayList<TicketEstacionamento> tickets = Repositorio.getInstance().getTickets();
        double soma = 0.0;
        LocalDate dataInicio = LocalDate.parse(dataInicioString, formatarDataSemHora);
        LocalDate dataFim = LocalDate.parse(dataFimString, formatarDataSemHora);

        for(TicketEstacionamento ticket : tickets)
        {
            if(dataInicio.isAfter(ticket.getDataInicio().toLocalDate()) && (ticket.getDataFim() != null && dataFim.isBefore(ticket.getDataFim().toLocalDate())))
            {
                soma += ticket.getTotalPagar();
            }
        }
        return soma;
    }

}
