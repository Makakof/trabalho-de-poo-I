package automovel;

public class Cor
{
    private String nomeCor;

    public Cor() {
    }

    public Cor(String nomeCor)
    {
        this.nomeCor = formatarString(nomeCor);
    }

    public String getNomeCor() {
        return nomeCor;
    }

    public void setNomeCor(String nomeCor) {
        this.nomeCor = nomeCor;
    }

    /*
    Passando tudo em caps lock para evitar do usuario digitar depois em letra minuscula e dar diferença na pesquisa
    e além disse retira os espaços da string com a mesma finalidade
    */
    public static String formatarString(String string)
    {
        return string.toUpperCase().replaceAll("\\s", "");
    }
    public String toString()
    {
        return nomeCor;
    }
}
