package modelagem;

import enums.TipoVeiculo;
import enums.VagaStatus;

import java.io.Serializable;

public class Vaga implements Serializable
{
    private int numeroVaga;
    private String rua;
    private VagaStatus status;
    private TipoVeiculo tipoVeiculo;


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

    public void ocupar(){
        status = VagaStatus.OCUPADA;
    }

    public void liberar (){
        status = VagaStatus.DISPONIVEL;
    }

    public void proibirAcesso(){
        status = VagaStatus.INDISPONIVEL;
    }

    public String toString(){
        return "Numero: " + numeroVaga + "\nRua: " + rua + "\nStatus: " + status;
    }
}
