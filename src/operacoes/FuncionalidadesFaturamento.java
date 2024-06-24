package operacoes;

import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import dados.Repositorio;
import ingressos.TicketEstacionamento;
import ingressos.TicketHorista;
import interfaces.InterfaceUsuario;



import java.time.LocalDate;
import java.util.List;

public class FuncionalidadesFaturamento
{
    public static void faturamentoPorPeriodo()
    {
        InterfaceUsuario interfaceUsuario = Repositorio.getInstance().getUI();
        LocalDate dataInicio, dataFim;
        double faturamento;

        dataInicio = interfaceUsuario.selecionarData("Digite a data de inicio (dia/mes/ano): ");
        dataFim = interfaceUsuario.selecionarData("Digite a data de fim (dia/mes/ano): ");

        faturamento = calcularFaturamentoPorPeriodo(dataInicio, dataFim);
        interfaceUsuario.exibir("O faturamento no periodo selecionado foi de " + faturamento);
    }

    private static double calcularFaturamentoPorPeriodo(LocalDate dataInicio, LocalDate dataFim)
    {
        List<TicketEstacionamento> tickets = Repositorio.getInstance().getTickets();
        double soma = 0.0;

        for(TicketEstacionamento ticket : tickets)
        {
            if((dataInicio.isBefore(ticket.getDataInicio().toLocalDate()) || (dataInicio.equals(ticket.getDataInicio().toLocalDate()))) && (ticket.getDataFim() != null))
            {

                if (ticket instanceof TicketHorista){

                    if ((dataFim.isAfter(ticket.getDataFim().toLocalDate())) || (dataFim.equals(ticket.getDataFim().toLocalDate()))){

                        soma += ticket.getTotalPagar();
                    }
                }else{

                    soma += ticket.getTotalPagar();
                }

            }
        }
        return soma;
    }

    public static void faturamentoPorVeiculo()
    {
        InterfaceUsuario interfaceUsuario = Repositorio.getInstance().getUI();

        //percorre todos os clientes cadastrados
        for (Cliente cliente : Repositorio.getInstance().getClientes()){

            //percorre os veiculos de cada cliente
            for (Veiculo veiculo : cliente.getVeiculos()){

                interfaceUsuario.exibir(veiculo + "\nTotal pago pra empresa: " + calcularFaturamentoPorVeiculo(veiculo));

            }
        }
    }

    public static double calcularFaturamentoPorVeiculo(Veiculo veiculo){

        double soma = 0.0;

        for (TicketEstacionamento ticket : Repositorio.getInstance().getTickets()){

            if (ticket.getVeiculo().getPlaca().equals(veiculo.getPlaca()) && ticket.getDataFim() != null){
                soma += ticket.getTotalPagar();
            }
        }

        return soma;
    }

}
