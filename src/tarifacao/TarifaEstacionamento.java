package tarifacao;

import enums.DiaDaSemana;
import utilitarios.DataUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class TarifaEstacionamento {

    private LocalDateTime dataInicio;
    private double valorPrimeiraHora;
    private double valorHoraSubsequente;
    private ArrayList<DiaDaSemana> diaDaSemana;


    public TarifaEstacionamento(double valorPrimeiraHora, double valorHoraSubsequente, ArrayList<DiaDaSemana> diaDaSemana) {
        this.dataInicio = LocalDateTime.now();
        this.valorPrimeiraHora = valorPrimeiraHora;
        this.valorHoraSubsequente = valorHoraSubsequente;
        this.diaDaSemana = diaDaSemana;
    }

    public TarifaEstacionamento (ArrayList<DiaDaSemana> diaDaSemana) {
        this.dataInicio = LocalDateTime.now();
        this.diaDaSemana = diaDaSemana;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
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
        return "\nData inicio: " + DataUtil.formatarData(dataInicio) +
                "\nDias: " + diaDaSemana +
                "\nValor 1ºhora: " + valorPrimeiraHora +
                "\nValor após 1ºhora: " + valorHoraSubsequente;
    }
}
