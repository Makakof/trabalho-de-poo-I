package kitmenu;


import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import enums.TipoVeiculo;
import enums.VagaStatus;
import ingressos.TicketEstacionaBem;
import modelagem.Vaga;
import tarifacao.TarifaEstacionaBem;
import tarifacao.TabelaPrecos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class MenuGerenciaEstacionamento {

    UI terminal = UI.getInstance();

    public MenuGerenciaEstacionamento(UI terminal) {
        this.terminal = terminal;
    }

    public void gerenciaEstacionamento(ArrayList<Cliente> clientes, ArrayList<TicketEstacionaBem> tickets, ArrayList<TicketEstacionaBem> logTickets, ArrayList<Vaga> vagas, TabelaPrecos[] valorHorasCarro, TabelaPrecos[] valorHorasMoto) {

        byte opcao;

        do {

            terminal.menuGerenciaEstacionamento();
            opcao = terminal.selecionarByte("Digite a opção desejada: ");

            switch (opcao) {
                case 1:

                    TicketEstacionaBem ticket = estacionar(clientes, tickets, vagas, valorHorasCarro, valorHorasMoto);
                    if (ticket != null) tickets.add(ticket);

                    break;
                case 2:

                    retirar(tickets, logTickets);
                    break;
                case 3:

                    listarVagas(vagas);
                    break;
                case 4:

//                    gerenciarTarifas(valorHorasCarro, valorHorasMoto);
                    break;
                case 5:

                    for (TicketEstacionaBem ticketFor : tickets)
                        terminal.exibir(ticketFor.toString());

                    break;
            }
        } while (opcao != 5);
    }

    public TicketEstacionaBem estacionar(ArrayList<Cliente> clientes, ArrayList<TicketEstacionaBem> tickets, ArrayList<Vaga> vagas, TabelaPrecos[] valorHorasCarro, TabelaPrecos[] valorHorasMoto) {

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
                vaga = consultarVaga(vagas, numeroRua);

                if (vaga != null) {
                    if (vaga.getTipoVeiculo() == veiculo.getTipoVeiculo()) {
                        if (vaga.getStatus() == VagaStatus.DISPONIVEL) {

                            vaga.setVagaStatus("OCUPADA");

                            if (vaga.getTipoVeiculo() == TipoVeiculo.CARRO)
                                tarifa = new TarifaEstacionaBem(valorHorasCarro);
                            else
                                tarifa = new TarifaEstacionaBem(valorHorasMoto);

                            return new TicketEstacionaBem(cliente, vaga, veiculo, tarifa);

                        } else terminal.exibir("Vaga OCUPADA ou INDISPONIVEL!");
                    } else terminal.exibir("O veiculo não condiz com o tipo de veiculo da vaga!");
                } else terminal.exibir("Vaga não cadastrada!");
            } else terminal.exibir("Carro não cadastrado!");
        } else terminal.exibir("Cliente não cadastrado!");

        return null;
    }

    public void retirar(ArrayList<TicketEstacionaBem> tickets, ArrayList<TicketEstacionaBem> logTickets) {

        long totalHoras;
        String placa;
        TicketEstacionaBem ticket, ticketCopia;

        placa = terminal.selecionarString("Digite a placa do veiculo: ");
        ticket = consultarTicket(tickets, placa);

        if (ticket != null) {

            ticket.encerrarTicket();

            ticketCopia = (TicketEstacionaBem) tickets.clone();

            totalHoras = calculaHoras(ticket.getDataInicio(), ticket.getDataFim());
            terminal.exibir("Tempo total: " + totalHoras);
            terminal.exibir("Total a pagar: " + ticket.getTotalPagar());

            tickets.remove(ticket);

            logTickets.add(ticketCopia);

            terminal.exibir("Ticket encerrado com sucesso!");


        } else terminal.exibir("Ticket não cadastrado!");
    }

    public long calculaHoras(LocalDateTime dataInicio, LocalDateTime dataFim){

        return dataInicio.until(dataFim, ChronoUnit.HOURS);
    }

    public void listarVagas(ArrayList<Vaga> vagas) {
        for (Vaga vaga : vagas)
            terminal.exibir(vaga.toString());
    }

    public void gerenciarTarifas() {

        byte opcao;

        terminal.menuGerenciaTarifas();
        opcao = terminal.selecionarByte("Digite a opção desejada: ");

        switch (opcao)
        {
            case 1:
                break;
            case 2:
                break;
        }
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

    public Vaga consultarVaga(ArrayList<Vaga> vagas, int numero) {

        for (Vaga vaga : vagas) {
            if (vaga.getNumeroVaga() == numero) return vaga;
        }

        return null;
    }

    public TicketEstacionaBem consultarTicket(ArrayList<TicketEstacionaBem> tickets, String placa) {
        for (TicketEstacionaBem ticket : tickets) {
            if (placa.equals(ticket.getVeiculo().getPlaca())) return ticket;

        }

        return null;
    }



}
