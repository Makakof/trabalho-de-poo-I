package tarifacao;

import enums.DiaDaSemana;
import enums.HoristaMensalista;
import utilitarios.DataUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;


public abstract class TarifaEstacionamento {

    private LocalDateTime dataInicio;
    private double valorPrimeiraHora;
    private double valorHoraSubsequente;
    private ArrayList<DiaDaSemana> diaDaSemana;
    private HoristaMensalista modoDeEstacionar;


    public TarifaEstacionamento(double valorPrimeiraHora, double valorHoraSubsequente, ArrayList<DiaDaSemana> diaDaSemana, HoristaMensalista modoDeEstacionar) {
        this.dataInicio = LocalDateTime.now();
        this.valorPrimeiraHora = valorPrimeiraHora;
        this.valorHoraSubsequente = valorHoraSubsequente;
        this.diaDaSemana = diaDaSemana;
        this.modoDeEstacionar = modoDeEstacionar;
    }

    public TarifaEstacionamento (ArrayList<DiaDaSemana> diaDaSemana, HoristaMensalista modoDeEstacionar) {
        this.dataInicio = LocalDateTime.now();
        this.diaDaSemana = diaDaSemana;
        this.modoDeEstacionar = modoDeEstacionar;
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

    public HoristaMensalista getModoDeEstacionar() {
        return modoDeEstacionar;
    }

    public String toString()
    {
        return "\nData inicio: " + DataUtil.formatarData(dataInicio) +
                "\nDias: " + diaDaSemana +
                "\nValor 1ºhora: " + valorPrimeiraHora +
                "\nValor após 1ºhora: " + valorHoraSubsequente;
    }
}
