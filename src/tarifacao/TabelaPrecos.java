package tarifacao;

public class TabelaPrecos
{
    private double primeiraHora;
    private double horaSubsequente;

    public TabelaPrecos(double primeiraHora, double horaSubsequente) {
        this.primeiraHora = primeiraHora;
        this.horaSubsequente = horaSubsequente;
    }

    public TabelaPrecos() {
    }

    public double getPrimeiraHora() {
        return primeiraHora;
    }

    public void setPrimeiraHora(double primeiraHora) {
        this.primeiraHora = primeiraHora;
    }

    public double getHoraSubsequente() {
        return horaSubsequente;
    }

    public void setHoraSubsequente(double horaSubsequente) {
        this.horaSubsequente = horaSubsequente;
    }
}
