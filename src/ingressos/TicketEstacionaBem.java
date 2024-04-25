package ingressos;

import automovel.Veiculo;
import modelagem.Vaga;
import tarifacao.TarifaEstacionamento;


import java.time.LocalDateTime;

public class TicketEstacionaBem
{
    private Vaga vaga;
    private Veiculo veiculo;
    private TarifaEstacionamento tarifaEstacionamento;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    public TicketEstacionaBem() {
    }

    public TicketEstacionaBem(Vaga vaga, Veiculo veiculo, TarifaEstacionamento tarifaEstacionamento, LocalDateTime dataInicio)
    {
        this.vaga = vaga;
        this.veiculo = veiculo;
        this.tarifaEstacionamento = tarifaEstacionamento;
        this.dataInicio = LocalDateTime.now();
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
}