package excecoes;

public abstract class ExcecaoAbstrata extends RuntimeException
{
    private final int codigoDoErro;
    private final String ondeOcorreu;
    public ExcecaoAbstrata(String message, String ondeOcorreu, int codigoDoErro)
    {
        super(message);
        this.ondeOcorreu = ondeOcorreu;
        this.codigoDoErro = codigoDoErro;
    }

    public int getCodigoDoErro() {
        return codigoDoErro;
    }

    public String getOndeOcorreu() {
        return ondeOcorreu;
    }

    public String toString(){
        return "";
    }

}
