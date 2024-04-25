package automovel;

public class Modelo
{
    private String nomeModelo;

    public Modelo() {
    }

    /*
    Passando tudo em caps lock para evitar do usuario digitar depois em letra minuscula e dar diferença na pesquisa
    e além disse retira os espaços da string com a mesma finalidade
    */
    public Modelo(String nomeModelo)
    {
        this.nomeModelo = formatarString(nomeModelo);
    }

    public String getNomeModelo() {
        return nomeModelo;
    }

    public String toString()
    {
        return nomeModelo;
    }

    public static String formatarString(String string)
    {
        return string.toUpperCase().replaceAll("\\s", "");
    }
}
