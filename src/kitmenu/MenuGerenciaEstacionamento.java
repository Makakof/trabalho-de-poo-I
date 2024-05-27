package kitmenu;


import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import dados.Repositorio;
import enums.DiaDaSemana;
import enums.HoristaMensalista;
import enums.VagaStatus;
import excecoes.EstacionamentoException;
import ingressos.TicketEstacionamento;
import modelagem.Vaga;
import tarifacao.TarifaEstacionamento;
import utilitarios.Util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class MenuGerenciaEstacionamento {

    private final UI terminal;

    public MenuGerenciaEstacionamento() {
        this.terminal = UI.getInstance();
    }


    public void gerenciarEstacionamento() {
        SubMenuGerenciaTarifas subMenuGerenciaTarifas = new SubMenuGerenciaTarifas();
        ArrayList<TicketEstacionamento> tickets = Repositorio.getInstance().getTickets();
        ArrayList<TicketEstacionamento> logTickets = Repositorio.getInstance().getLogTickets();
        ArrayList<Cliente> clientes = Repositorio.getInstance().getClientes();
        ArrayList<Vaga> vagas = Repositorio.getInstance().getVagas();
        ArrayList<TarifaEstacionamento> tarifas = Repositorio.getInstance().getTarifas();

        byte opcao;

        do {

            terminal.menuGerenciaEstacionamento();
            opcao = terminal.selecionarByte("Digite a opção desejada: ");

            switch (opcao) {
                case 1:

                    TicketEstacionamento ticket = estacionar(clientes, tarifas, vagas);
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

    public TicketEstacionamento estacionar(ArrayList<Cliente> clientes, ArrayList<TarifaEstacionamento> tarifas, ArrayList<Vaga> vagas) {

        int numeroDaVaga;
        String documento, placa, modoDeEstacionar;
        Cliente cliente;
        Veiculo veiculo;
        Vaga vaga;
        TarifaEstacionamento tarifa;
        HoristaMensalista tipo;

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

        modoDeEstacionar = terminal.selecionarString("Estacionar como HORISTA ou MENSALISTA: ");

        vaga.setVagaStatus(VagaStatus.OCUPADA);

        tipo = HoristaMensalista.valueOf(modoDeEstacionar);

        tarifa = buscarTarifa(tarifas, tipo);

        return new TicketEstacionamento(cliente, vaga, veiculo, tarifa);
    }

    public TarifaEstacionamento buscarTarifa (ArrayList<TarifaEstacionamento> tarifas, HoristaMensalista modoDeEstacionar){

        TarifaEstacionamento tarifa = null;
        boolean achouDia = false;

        for(TarifaEstacionamento tarifaAtual : tarifas){ //olha pra todas as tarifas

            if(tarifaAtual.getModoDeEstacionar().equals(modoDeEstacionar)){ //procura pelo tipo de tarifa especificado

                for (DiaDaSemana dia : tarifaAtual.getDiaDaSemana()){

                    if(Util.diaDaSemanaString(LocalDateTime.now()).equals(dia.name())){ //verifica se a tarifa é valida pro dia atual
                        achouDia = true;
                    }
                }

                if (achouDia) {

                    if (tarifa == null)
                        tarifa = tarifaAtual;

                    else {

                        if (tarifa.getDataInicio().isBefore(tarifaAtual.getDataInicio()))
                            tarifa = tarifaAtual;
                    }
                }
            }
        }
        return tarifa;
    }

    public void retirar(ArrayList<TicketEstacionamento> tickets, ArrayList<TicketEstacionamento> logTickets) {

        long totalHoras;
        String placa;
        TicketEstacionamento ticket, ticketCopia;

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

    public TicketEstacionamento consultarTicket(ArrayList<TicketEstacionamento> tickets, String placa) {
        for (TicketEstacionamento ticket : tickets) {
            if (placa.equals(ticket.getVeiculo().getPlaca())) return ticket;

        }

        return null;
    }



}
