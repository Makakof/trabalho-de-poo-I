package modelagem;

import enums.VagaStatus;

public class Vaga
{
    private int numeroVaga;
    private String rua;
    private VagaStatus status;

    public Vaga() {
    }

    public Vaga(int numeroVaga, String rua, VagaStatus status) {
        this.numeroVaga = numeroVaga;
        this.rua = rua;
        this.status = status;
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
}
