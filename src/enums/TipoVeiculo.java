package enums;



public enum TipoVeiculo
{
    CARRO (1),
    MOTO (0.5),
    ONIBUS (1.5);

    public double percentualTarifa;

    TipoVeiculo(double percentualTarifa)
    {
        this.percentualTarifa = percentualTarifa;
    }

    public double getPercentualTarifa(){
        return percentualTarifa;
    }
}
