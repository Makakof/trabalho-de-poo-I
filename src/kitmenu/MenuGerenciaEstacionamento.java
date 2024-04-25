package kitmenu;


import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import ingressos.TicketEstacionaBem;
import modelagem.Vaga;
import tarifacao.ValorHora;

import java.util.ArrayList;

public class MenuGerenciaEstacionamento {

    UI terminal = UI.getInstance();

    public MenuGerenciaEstacionamento(UI terminal) {
        this.terminal = terminal;
    }

    public void gerenciaEstacionamento(ArrayList<Cliente> clientes, ArrayList<Veiculo> veiculos, ArrayList<TicketEstacionaBem> tickets, ArrayList<Vaga> vagas, ValorHora[] valorHoras) {

        byte opcao;

        do {

            terminal.menuGerenciaEstacionamento();
            opcao = terminal.selecionarByte("Digite a opção desejada: ");

            switch (opcao) {
                case 1:


                    break;
                case 2:


                    break;
                case 3:

                    listarVagas(vagas);
                    break;
                case 4:


                    break;
            }
        } while (opcao != 5);
    }

    public void estacionar(ArrayList<Cliente> clientes, ArrayList<Veiculo> veiculos, ArrayList<TicketEstacionaBem> tickets, ArrayList<Vaga> vagas, ValorHora[] valorHoras) {

        String documento;
        Cliente cliente;

        documento = terminal.selecionarString("Motorista digite um documento: ");
        cliente = consultaCliente(clientes, documento);

        if(cliente != null)
        {

        }
        else
            terminal.exibir("Cliente não cadastrado!");


    }

    public void retirar() {
    }

    public void listarVagas(ArrayList<Vaga> vagas) {
        for (Vaga vaga : vagas)
            terminal.exibir(vagas.toString());
    }

    public void gerenciarTarifas() {
    }

    public Cliente consultaCliente(ArrayList<Cliente> clientes, String documento) {

        for (Cliente clienteAtual : clientes) {
            if (clienteAtual.getDocumento().equals(documento))
                return clienteAtual;
        }

        return null;
    }

}
