package automovel;

public class Veiculo
{
    private String placa;
    private Cor cor;
    private Modelo modelo;

    public Veiculo() {
    }

    public Veiculo(String placa, Cor cor, Modelo modelo) {
        this.placa = placa.toUpperCase().replaceAll("\\s", "");
        this.cor = cor;
        this.modelo = modelo;
    }

    // Um veículo precisa de pelo menos placa e modelo
    public Veiculo(String placa, Modelo modelo) {
        this.placa = placa.toUpperCase().replaceAll("\\s", "");
        this.modelo = modelo;
    }

    /* Ausencia dos setters é porque não faz sentido deixar eles serem alterados de alguma maneira se não na própria
    instanciação do objeto */

    public String getPlaca() {
        return placa;
    }

    public Cor getCor() {
        return cor;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public String toString()
    {
        return "\nPlaca: " + placa + "\nCor: " + cor + "\nModelo: " + modelo;
    }
}
