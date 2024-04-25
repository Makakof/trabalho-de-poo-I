package ingressos;

import automovel.Veiculo;
import modelagem.Vaga;
import tarifacao.TarifaEstacionaBem;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TicketEstacionaBem
{
    private Vaga vaga;
    private Veiculo veiculo;
    private TarifaEstacionaBem tarifaEstacionaBem;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private double totalPagar;

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
    public LocalDateTime getDataFim() {
        return dataInicio;
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
            totalPagar = tarifaEstacionaBem.getValorPrimeiraHora();
            totalPagar += tarifaEstacionaBem.getValorHoraSubsequente() * (diferencaHoras - 1);
        }
        else
            totalPagar = tarifaEstacionaBem.getValorPrimeiraHora();

        this.totalPagar = totalPagar;
    }
}
