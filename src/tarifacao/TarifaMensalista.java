package tarifacao;

import enums.DiaDaSemana;
import enums.HoristaMensalista;
import utilitarios.DataUtil;

import java.util.ArrayList;

public class TarifaMensalista extends TarifaEstacionamento{

    private double valorIntegral;

    public TarifaMensalista(double valorIntegral, ArrayList<DiaDaSemana> diaDaSemana, HoristaMensalista modoDeEstacionar) {
        super(diaDaSemana, modoDeEstacionar);
        this.valorIntegral = valorIntegral;
    }

    public double getValorIntegral() {
        return valorIntegral;
    }

    public String toString()
    {
        return "\nData inicio: " + DataUtil.formatarData(this.getDataInicio()) +
                "\nDias: " + this.getDiaDaSemana() +
                "\nValor integral: " + this.getValorIntegral();
    }
}
