package tarifacao;

import enums.DiaDaSemana;
import enums.HoristaMensalista;

import java.util.ArrayList;

public class TarifaHorista extends TarifaEstacionamento{

    public TarifaHorista(double valorPrimeiraHora, double valorHoraSubsequente, ArrayList<DiaDaSemana> diaDaSemana, HoristaMensalista modoDeEstacionar){
        super(valorPrimeiraHora, valorHoraSubsequente, diaDaSemana, modoDeEstacionar);
    }

    public void calcula(){
    }
}
