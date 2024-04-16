package automovel;

public class Cor
{
    private String nomeCor;

    public Cor() {
    }

    /*
    Passando tudo em caps lock para evitar do usuario digitar depois em letra minuscula e dar diferença na pesquisa
    e além disse retira os espaços da string com a mesma finalidade
    */
    public Cor(String nomeCor)
    {
        this.nomeCor = nomeCor.toUpperCase().replaceAll("\\s", "");
    }

    public String getNomeCor() {
        return nomeCor;
    }

    public void setNomeCor(String nomeCor) {
        this.nomeCor = nomeCor;
    }

    public String toString()
    {
        return nomeCor;
    }
}
