package tarifacao;

import utilitarios.DataUtils;

public class TarifaMensalista extends TarifaEstacionamento{

    private double valorIntegral;

    public TarifaMensalista(double valorIntegral) {
        super();
        this.valorIntegral = valorIntegral;
    }

    public double getValorIntegral() {
        return valorIntegral;
    }

    public String toString()
    {
        return "\nData inicio: " + DataUtils.formatarData(this.getDataInicio()) +
                "\nValor integral: " + this.getValorIntegral();
    }
}
