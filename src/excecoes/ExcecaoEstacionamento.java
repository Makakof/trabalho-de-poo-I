package excecoes;

public class ExcecaoEstacionamento extends ExcecaoAbstrata{
    public ExcecaoEstacionamento(String message, String ondeOcorreu, int codigoDoErro) {
        super(message, ondeOcorreu, codigoDoErro);
    }

    @Override
    public String toString()
    {
        return "Proibido: " + super.getMessage() + "\nOcorrido em: " + super.getOndeOcorreu() +
                "\nTipo: " + super.getCodigoDoErro();
    }
}
