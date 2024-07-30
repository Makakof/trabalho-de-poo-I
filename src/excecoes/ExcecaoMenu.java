package excecoes;

public class ExcecaoMenu extends ExcecaoAbstrata{
    public ExcecaoMenu(String message, String ondeOcorreu, int codigoDoErro) {
        super(message, ondeOcorreu, codigoDoErro);
    }

    @Override
    public String toString()
    {
        return "Opção inválida \nOcorrido em: " + super.getOndeOcorreu();
    }
}
