package modelagem;

import enums.VagaStatus;

public class Vaga
{
    private int numeroVaga;
    private String rua;
    private VagaStatus status;

    public Vaga() {
    }

    public Vaga(int numeroVaga, String rua) {
        this.numeroVaga = numeroVaga;
        this.rua = rua;
        this.status = VagaStatus.DISPONIVEL;
    }

    public int getNumeroVaga() {
        return numeroVaga;
    }

    public String getRua() {
        return rua;
    }

    public VagaStatus getStatus(){
        return status;
    }

    public void setNumeroVaga(int numeroVaga) {
        this.numeroVaga = numeroVaga;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setVagaStatus (String status){
        this.status = VagaStatus.valueOf(status);
    }

    public String toString(){
        return "Numero: " + numeroVaga + "\nRua: " + rua + "\nStatus: " + status;
    }
}
