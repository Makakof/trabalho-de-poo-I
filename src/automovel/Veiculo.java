package automovel;

import enums.TipoVeiculo;

public class Veiculo
{
    private String placa;
    private Cor cor;
    private Modelo modelo;

    private TipoVeiculo tipoVeiculo;

    public Veiculo() {
    }

    public Veiculo(String placa, Cor cor, Modelo modelo, TipoVeiculo tipoVeiculo) {
        this.placa = formatarString(placa);
        this.cor = cor;
        this.modelo = modelo;
        this.tipoVeiculo = tipoVeiculo;
    }

    // Um veículo precisa de pelo menos placa e modelo
    public Veiculo(String placa, Modelo modelo, TipoVeiculo tipoVeiculo) {
        this.placa = formatarString(placa);
        this.modelo = modelo;
        this.tipoVeiculo = tipoVeiculo;
    }

    /* Ausencia dos setters é porque não faz sentido deixar eles serem alterados de alguma maneira se não na própria
    instanciação do objeto */

    public String getPlaca() {
        return placa;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }


    public static String formatarString(String string)
    {
        return string.toUpperCase().replaceAll("\\s", "");
    }

    public String toString()
    {
        return "\nPlaca: " + placa + "\nCor: " + cor + "\nModelo: " + modelo;
    }
}
