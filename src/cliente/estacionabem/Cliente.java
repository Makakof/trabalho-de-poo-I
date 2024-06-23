package cliente.estacionabem;

import automovel.Veiculo;
import interfaces.InterfaceUsuario;
import interfaces.Terminal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {

    transient private final InterfaceUsuario interfaceUsuario;

    private String nome;
    private String documento;
    private List<Veiculo> veiculos = new ArrayList<>();

    public Cliente(String nome, String documento) {
        this.nome = nome;
        this.documento = documento;
        this.interfaceUsuario = Terminal.getInstance();
    }

    public Cliente(String nome, String documento, List<Veiculo> veiculos) {
        this.nome = nome;
        this.documento = documento;
        this.veiculos = veiculos;
        this.interfaceUsuario = Terminal.getInstance();
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDocumento(String documento){
        this.documento = documento;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void addVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public String toString() {
        return "Nome: " + nome + "\nDocumento: " + documento + "\nVeiculos: ";
    }

    public void mostraVeiculos(){

        for(Veiculo veiculo : veiculos) {
            interfaceUsuario.exibir(veiculo.toString());
        }
    }

}
