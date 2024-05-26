package tarifacao;

import enums.DiaDaSemana;

import java.util.ArrayList;

public class TarifaHorista extends TarifaEstacionamento{

    public TarifaHorista(double valorPrimeiraHora, double valorHoraSubsequente, ArrayList<DiaDaSemana> diaDaSemana){
        super(valorPrimeiraHora, valorHoraSubsequente, diaDaSemana);
    }

}
