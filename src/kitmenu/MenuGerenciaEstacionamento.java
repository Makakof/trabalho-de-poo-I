package kitmenu;


import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import dados.Repositorio;
import enums.DiaDaSemana;
import enums.TipoVeiculo;
import enums.VagaStatus;
import excecoes.EstacionamentoException;
import ingressos.TicketEstacionaBem;
import modelagem.Vaga;
import tarifacao.TarifaEstacionaBem;
import tarifacao.TabelaPrecos;
import tarifacao.TarifaEstacionamento;
import tarifacao.TarifaHorista;
import tarifacao.TarifaMensalista;
import utilitarios.StringUtil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class MenuGerenciaEstacionamento {

    private UI terminal;

    public MenuGerenciaEstacionamento() {
        this.terminal = UI.getInstance();
    }


    public void gerenciarEstacionamento() {
        SubMenuGerenciaTarifas subMenuGerenciaTarifas = new SubMenuGerenciaTarifas();
        ArrayList<TicketEstacionaBem> tickets = Repositorio.getInstance().getTickets();
        ArrayList<TicketEstacionaBem> logTickets = Repositorio.getInstance().getLogTickets();
        ArrayList<Cliente> clientes = Repositorio.getInstance().getClientes();
        ArrayList<Vaga> vagas = Repositorio.getInstance().getVagas();

        byte opcao;

        do {

            terminal.menuGerenciaEstacionamento();
            opcao = terminal.selecionarByte("Digite a opção desejada: ");

            switch (opcao) {
                case 1:

                    TicketEstacionaBem ticket = estacionar(clientes, tickets, vagas);
                    if (ticket != null) tickets.add(ticket);

                    break;
                case 2:

                    retirar(tickets, logTickets);
                    break;
                case 3:

                    listarVagas(vagas);
                    break;
                case 4:
                    subMenuGerenciaTarifas.gerenciarTarifas();
                    break;
            }
        } while (opcao != 5);
    }

    public TicketEstacionaBem estacionar(ArrayList<Cliente> clientes, ArrayList<TicketEstacionaBem> tickets, ArrayList<Vaga> vagas) {

        int numeroDaVaga;
        String documento, placa;
        Cliente cliente;
        Veiculo veiculo;
        Vaga vaga;
        TarifaEstacionaBem tarifa;

        documento = terminal.selecionarString("Motorista digite um documento: ");
        cliente = consultarCliente(clientes, documento);

        if (cliente == null) {
            throw new EstacionamentoException("Cliente não cadastrado");
        }
        placa = terminal.selecionarString("Digite o numero da placa do veiculo: ");
        veiculo = consultarVeiculo(cliente.getVeiculos(), placa);

        if (veiculo == null) {
            throw new EstacionamentoException("Veiculo não cadastrado");
        }
        numeroDaVaga = terminal.selecionarInt("Digite o numero da vaga: ");
        vaga = consultarVaga(vagas, numeroDaVaga);

        if (vaga == null) {
            throw new EstacionamentoException("Vaga de numero: " + numeroDaVaga + " não cadastrada");
        }

        if (vaga.getTipoVeiculo() != veiculo.getTipoVeiculo()) {
            throw new EstacionamentoException("O veiculo não condiz com o tipo de veiculo da vaga");
        }

        if (vaga.getStatus() != VagaStatus.DISPONIVEL) {
            throw new EstacionamentoException("Vaga OCUPADA ou INDISPONIVEL!");
        }

        vaga.setVagaStatus(VagaStatus.OCUPADA);

        if(veiculo.getTipoVeiculo() == TipoVeiculo.CARRO)
            tarifa = new TarifaEstacionaBem();
        else
            tarifa = new TarifaEstacionaBem();

        return new TicketEstacionaBem(cliente, vaga, veiculo, tarifa);
    }

    public void retirar(ArrayList<TicketEstacionaBem> tickets, ArrayList<TicketEstacionaBem> logTickets) {

        long totalHoras;
        String placa;
        TicketEstacionaBem ticket, ticketCopia;

        placa = terminal.selecionarString("Digite a placa do veiculo: ");
        ticket = consultarTicket(tickets, placa);

        if (ticket != null) {

            ticket.encerrarTicket();

            totalHoras = calcularHoras(ticket.getDataInicio(), ticket.getDataFim());
            terminal.exibir("Tempo total: " + totalHoras);
            terminal.exibir("Total a pagar: " + ticket.getTotalPagar());

            logTickets.add(ticket);
            tickets.remove(ticket);

            ticket.getVaga().setVagaStatus(VagaStatus.DISPONIVEL);

            terminal.exibir("Ticket encerrado com sucesso!");


        } else terminal.exibir("Ticket não cadastrado!");
    }

    public long calcularHoras(LocalDateTime dataInicio, LocalDateTime dataFim){

        return dataInicio.until(dataFim, ChronoUnit.HOURS);
    }

    public void listarVagas(ArrayList<Vaga> vagas) {
        for (Vaga vaga : vagas)
            if (vaga.getStatus() == VagaStatus.DISPONIVEL)
                terminal.exibir(vaga.toString());
    }


    public Cliente consultarCliente(ArrayList<Cliente> clientes, String documento) {

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
