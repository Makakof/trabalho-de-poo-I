package kitmenu;


import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import enums.VagaStatus;
import ingressos.TicketEstacionaBem;
import modelagem.Vaga;
import tarifacao.TarifaEstacionaBem;
import tarifacao.ValorHora;

import java.time.LocalDateTime;
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

                    estacionar(clientes, tickets, vagas, valorHoras);
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

    public void estacionar(ArrayList<Cliente> clientes, ArrayList<TicketEstacionaBem> tickets, ArrayList<Vaga> vagas, ValorHora[] valorHoras) {

        int numeroRua;
        String documento, placa;
        Cliente cliente;
        Veiculo veiculo;
        Vaga vaga;
        TarifaEstacionaBem tarifa;

        documento = terminal.selecionarString("Motorista digite um documento: ");
        cliente = consultaCliente(clientes, documento);

        if (cliente != null) {
            placa = terminal.selecionarString("Digite o numero da placa do carro: ");
            veiculo = consultarVeiculo(cliente.getVeiculos(), placa);

            if (veiculo != null) {
                numeroRua = terminal.selecionarInt("Digite o numero da vaga: ");
                vaga = buscarVaga(vagas, numeroRua);

                if (vaga != null) {
                    if (vaga.getStatus() == VagaStatus.DISPONIVEL) {
                        //TODO verifcar o tipo de veiculo que a vaga aceita
                        vaga.setVagaStatus("OCUPADA");
                        tarifa = new TarifaEstacionaBem(valorHoras);
                        TicketEstacionaBem ticket = new TicketEstacionaBem(vaga, veiculo, tarifa, LocalDateTime.now());
                        tickets.add(ticket);

                    }
                } else terminal.exibir("Vaga não cadastrada!");
            } else terminal.exibir("Carro não cadastrado!");
        } else terminal.exibir("Cliente não cadastrado!");


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
            if (clienteAtual.getDocumento().equals(documento)) return clienteAtual;
        }

        return null;
    }

    public Veiculo consultarVeiculo(ArrayList<Veiculo> veiculos, String placa) {

        for (Veiculo veiculoAtual : veiculos) {
            if (veiculoAtual.getPlaca().equals(placa)) return veiculoAtual;
        }

        return null;
    }

    public Vaga buscarVaga(ArrayList<Vaga> vagas, int numero) {

        for (Vaga vaga : vagas) {
            if (vaga.getNumeroVaga() == numero) return vaga;
        }

        return null;
    }

}
