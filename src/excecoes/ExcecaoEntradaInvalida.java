package excecoes;

public class ExcecaoEntradaInvalida extends ExcecaoAbstrata {
    public ExcecaoEntradaInvalida(String message, String menuDoErro, int codigoDoErro) {
        super(message, menuDoErro, codigoDoErro);
    }
    @Override
    public String toString()
    {
        return "Erro na entrada de dados: " + super.getMessage() + "\nOcorrido em: " + super.getOndeOcorreu() +
                "\nTipo: " + super.getCodigoDoErro();
    }
}
