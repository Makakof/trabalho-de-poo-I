package tarifacao;

import utilitarios.DataUtil;

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
        return "\nData inicio: " + DataUtil.formatarData(this.getDataInicio()) +
                "\nValor integral: " + this.getValorIntegral();
    }
}
