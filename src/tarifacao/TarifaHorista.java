package tarifacao;

import enums.DiaDaSemana;
import utilitarios.DataUtil;

import java.util.ArrayList;

public class TarifaHorista extends TarifaEstacionamento{

    private double valorPrimeiraHora;
    private double valorHoraSubsequente;
    private ArrayList<DiaDaSemana> diaDaSemana;

    public TarifaHorista(double valorPrimeiraHora, double valorHoraSubsequente, ArrayList<DiaDaSemana> diaDaSemana){
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

    public ArrayList<DiaDaSemana> getDiaDaSemana() {
        return diaDaSemana;
    }

    public String toString()
    {
        return "\nData inicio: " + DataUtil.formatarData(this.getDataInicio()) +
                "\nDias: " + diaDaSemana +
                "\nValor 1ºhora: " + valorPrimeiraHora +
                "\nValor após 1ºhora: " + valorHoraSubsequente;
    }

}
