package tarifacao;

import enums.DiaDaSemana;
import utilitarios.DataUtils;

import java.util.ArrayList;
import java.util.List;

public class TarifaHorista extends TarifaEstacionamento{

    private double valorPrimeiraHora;
    private double valorHoraSubsequente;
    private List<DiaDaSemana> diaDaSemana;

    public TarifaHorista(double valorPrimeiraHora, double valorHoraSubsequente, List<DiaDaSemana> diaDaSemana){
        super();
        this.valorPrimeiraHora = valorPrimeiraHora;
        this.valorHoraSubsequente = valorHoraSubsequente;
        this.diaDaSemana = diaDaSemana;
    }

    public double getValorPrimeiraHora() {
        return valorPrimeiraHora;
    }

    public double getValorHoraSubsequente() {
        return valorHoraSubsequente;
    }

    public List<DiaDaSemana> getDiaDaSemana() {
        return diaDaSemana;
    }

    public String toString()
    {
        return "\nData inicio: " + DataUtils.formatarData(this.getDataInicio()) +
                "\nDias: " + diaDaSemana +
                "\nValor 1ºhora: " + valorPrimeiraHora +
                "\nValor após 1ºhora: " + valorHoraSubsequente;
    }

}
