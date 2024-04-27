package modelagem;

import enums.TipoVeiculo;
import enums.VagaStatus;

public class Vaga
{
    private int numeroVaga;
    private String rua;
    private VagaStatus status;
    private TipoVeiculo tipoVeiculo;
    //este campo serve para que a vaga saiba qual tipo de veiculo pode ser estacionado nela

    public Vaga() {
    }

    public Vaga(int numeroVaga, String rua, TipoVeiculo tipoVeiculo) {
        this.numeroVaga = numeroVaga;
        this.rua = rua;
        this.tipoVeiculo = tipoVeiculo;
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

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
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
