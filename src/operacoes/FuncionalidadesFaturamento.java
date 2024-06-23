package operacoes;

import dados.Repositorio;
import ingressos.TicketEstacionamento;
import interfaces.InterfaceUsuario;



import java.time.LocalDate;
import java.util.ArrayList;

public class FuncionalidadesFaturamento
{
    public static void realizarFaturamento()
    {
        InterfaceUsuario interfaceUsuario = Repositorio.getInstance().getUI();
        LocalDate dataInicio, dataFim;
        double faturamento;

        dataInicio = interfaceUsuario.selecionarData("Digite a data de inicio (dia/mes/ano): ");
        dataFim = interfaceUsuario.selecionarData("Digite a data de fim (dia/mes/ano): ");

        faturamento = calcularFaturamento(dataInicio, dataFim);
        interfaceUsuario.exibir("O faturamento no periodo selecionado foi de " + faturamento);
    }

    private static double calcularFaturamento(LocalDate dataInicio, LocalDate dataFim)
    {
        ArrayList<TicketEstacionamento> tickets = Repositorio.getInstance().getTickets();
        double soma = 0.0;

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
