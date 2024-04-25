package tarifacao;

public class ValorHora
{
    private double primeiraHora;
    private double horaSubsequente;

    public ValorHora(double primeiraHora, double horaSubsequente) {
        this.primeiraHora = primeiraHora;
        this.horaSubsequente = horaSubsequente;
    }

    public ValorHora() {
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
