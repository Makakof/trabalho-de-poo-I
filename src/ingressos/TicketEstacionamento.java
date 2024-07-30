package ingressos;

import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import modelagem.Vaga;
import tarifacao.TarifaEstacionamento;
import utilitarios.DataUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class TicketEstacionamento implements Serializable
{
    private Cliente cliente;
    private Vaga vaga;
    private Veiculo veiculo;
    private TarifaEstacionamento tarifa;
    private LocalDateTime dataInicio;
    protected LocalDateTime dataFim;
    protected double totalPagar;

    public TicketEstacionamento(Cliente cliente, Vaga vaga, Veiculo veiculo, TarifaEstacionamento tarifa)
    {
        this.cliente = cliente;
        vaga.ocupar();
        this.vaga = vaga;
        this.veiculo = veiculo;
        this.tarifa = tarifa;
        this.dataInicio = LocalDateTime.now();
        this.dataFim = null;
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
        return tarifa;
    }
    public LocalDateTime getDataInicio() {
        return dataInicio;
    }
    public LocalDateTime getDataFim() {
        return dataFim;
    }
    public double getTotalPagar() {return totalPagar;}

    public abstract void encerrarTicket();

    public abstract double calcularTotalPagar();

    public String toString() {
        return "\nCliente: " + cliente +
                "\nVaga: " + vaga +
                "\nVeiculo: " + veiculo +
                "\nTarifa: " + tarifa +
                "\nData inicio: " + DataUtils.formatarData(dataInicio) +
                "\nData fim: " + DataUtils.formatarData(dataFim) +
                "\nTotal pagar: " + totalPagar;
    }
}
