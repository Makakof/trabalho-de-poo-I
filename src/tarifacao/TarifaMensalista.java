package tarifacao;

import enums.DiaDaSemana;
import enums.HoristaMensalista;
import java.util.ArrayList;

public class TarifaMensalista extends TarifaEstacionamento{

    private double valorIntegral;

    public TarifaMensalista(double valorIntegral, ArrayList<DiaDaSemana> diaDaSemana, HoristaMensalista modoDeEstacionar) {
        super(diaDaSemana, modoDeEstacionar);
        this.valorIntegral = valorIntegral;
    }
}
