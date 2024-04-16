package ingressos;

import automovel.Veiculo;
import modelagem.Vaga;
import tarifacao.TarifaEstacionaBem;


import java.time.LocalDateTime;

public class TicketEstacionaBem
{
    private Vaga vaga;
    private Veiculo veiculo;
    private TarifaEstacionaBem tarifaEstacionaBem;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    public TicketEstacionaBem() {
    }

    public TicketEstacionaBem(Vaga vaga, Veiculo veiculo, TarifaEstacionaBem tarifaEstacionaBem, LocalDateTime dataInicio)
    {
        this.vaga = vaga;
        this.veiculo = veiculo;
        this.tarifaEstacionaBem = tarifaEstacionaBem;
        this.dataInicio = LocalDateTime.now();
    }

    public Vaga getVaga() {
        return vaga;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public TarifaEstacionaBem getTarifa() {
        return tarifaEstacionaBem;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }
}
