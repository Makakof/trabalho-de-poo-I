package ingressos;

import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import modelagem.Vaga;
import tarifacao.TarifaEstacionamento;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TicketEstacionamento
{
    private Cliente cliente;
    private Vaga vaga;
    private Veiculo veiculo;
    private TarifaEstacionamento tarifaEstacionamento;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private double totalPagar;

    public TicketEstacionamento() {
    }

    public TicketEstacionamento(Cliente cliente, Vaga vaga, Veiculo veiculo, TarifaEstacionamento tarifaEstacionamento)
    {
        this.cliente = cliente;
        this.vaga = vaga;
        this.veiculo = veiculo;
        this.tarifaEstacionamento = tarifaEstacionamento;
        this.dataInicio = LocalDateTime.now();
    }

    public Cliente getCliente() {
        return cliente;
    }
    public Vaga getVaga() {
        return vaga;
    }
    public Veiculo getVeiculo() {
        return veiculo;
    }
    public TarifaEstacionamento getTarifa() {
        return tarifaEstacionamento;
    }
    public LocalDateTime getDataInicio() {
        return dataInicio;
    }
    public LocalDateTime getDataFim() {
        return dataFim;
    }
    public double getTotalPagar() {return totalPagar;}

    public void encerrarTicket()
    {
        this.dataFim = LocalDateTime.now();

        double totalPagar;
        long diferencaHoras;

        diferencaHoras = dataInicio.until(dataFim, ChronoUnit.HOURS);

        if(diferencaHoras > 1)
        {
            totalPagar = tarifaEstacionamento.getValorPrimeiraHora();
            totalPagar += tarifaEstacionamento.getValorHoraSubsequente() * (diferencaHoras - 1);
        }
        else
            totalPagar = tarifaEstacionamento.getValorPrimeiraHora();

        this.totalPagar = totalPagar;
    }
}
