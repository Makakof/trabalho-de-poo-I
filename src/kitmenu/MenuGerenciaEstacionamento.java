package kitmenu;


import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import dados.Repositorio;
import enums.DiaDaSemana;
import enums.VagaStatus;
import excecoes.EstacionamentoException;
import ingressos.TicketEstacionamento;
import ingressos.TicketHorista;
import ingressos.TicketMensalista;
import interfaces.InterfaceUsuario;
import interfaces.Terminal;
import modelagem.Vaga;
import tarifacao.TarifaEstacionamento;
import tarifacao.TarifaHorista;
import tarifacao.TarifaMensalista;
import utilitarios.CalculoUtils;
import utilitarios.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MenuGerenciaEstacionamento {

    private final InterfaceUsuario interfaceUsuario;

    public MenuGerenciaEstacionamento() {
        this.interfaceUsuario = Terminal.getInstance();
    }


    public void gerenciarEstacionamento() {
        SubMenuGerenciaTarifas subMenuGerenciaTarifas = new SubMenuGerenciaTarifas();
        ArrayList<TicketEstacionamento> tickets = Repositorio.getInstance().getTickets();
        ArrayList<Cliente> clientes = Repositorio.getInstance().getClientes();
        ArrayList<Vaga> vagas = Repositorio.getInstance().getVagas();
        ArrayList<TarifaEstacionamento> tarifas = Repositorio.getInstance().getTarifas();

        byte opcao;

        do {

            interfaceUsuario.menuGerenciaEstacionamento();
            opcao = interfaceUsuario.selecionarByte("Digite a opção desejada: ");

            switch (opcao) {
                case 1: // estacionar

                    TicketEstacionamento ticket = estacionar(clientes, tarifas, vagas);

                    if (ticket.getTarifa() instanceof TarifaMensalista && ticket.getDataFim() == null) {

                        ticket.encerrarTicket();
                        tickets.add(ticket);

                    } else
                        tickets.add(ticket);

                    break;
                case 2: // retirar

                    retirar(tickets);
                    break;
                case 3: // listar vagas

                    if (vagas.isEmpty()) {
                        throw new EstacionamentoException("O estacionamento não possui vagas cadastradas");
                    }
                    listarVagas(vagas);
                    break;
                case 4: // gerenciar tarifas
                    subMenuGerenciaTarifas.gerenciarTarifas();
                    break;
                case 5: //voltar
                    break;
                default:
                    throw new EstacionamentoException("Opção inválida de menu");
            }
        } while (opcao != 5);
    }

    public TicketEstacionamento estacionar(ArrayList<Cliente> clientes, ArrayList<TarifaEstacionamento> tarifas, ArrayList<Vaga> vagas) {

        int numeroDaVaga;
        String documento, placa, modoDeEstacionar;
        Cliente cliente;
        Veiculo veiculo;
        Vaga vaga;

        documento = interfaceUsuario.selecionarString("Motorista digite um documento: ");
        cliente = consultarCliente(clientes, documento);

        if (cliente == null) {
            throw new EstacionamentoException("Cliente não cadastrado");
        }
        placa = interfaceUsuario.selecionarString("Digite o numero da placa do veiculo: ");
        placa = StringUtils.formatarPadraoCapturaDeDados(placa);
        veiculo = consultarVeiculo(cliente.getVeiculos(), placa);

        if (veiculo == null) {
            throw new EstacionamentoException("Veiculo não cadastrado");
        }

        modoDeEstacionar = interfaceUsuario.selecionarString("Estacionar como HORISTA ou MENSALISTA: ");
        modoDeEstacionar = StringUtils.formatarPadraoCapturaDeDados(modoDeEstacionar);

        if (modoDeEstacionar.equals("MENSALISTA")) {

            TicketEstacionamento ticket = buscarTicketMensalista(Repositorio.getInstance().getTickets(), veiculo.getPlaca());

            if (ticket != null)
                return ticket;
        }

        numeroDaVaga = interfaceUsuario.selecionarInt("Digite o numero da vaga: ");
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

        if (modoDeEstacionar.equals("MENSALISTA")) {

            TarifaHorista tarifa = buscarTarifaHorista(tarifas);

            return new TicketHorista(cliente, vaga, veiculo, tarifa);
        } else {

            TarifaMensalista tarifa = buscarTarifaMensalista(tarifas);

            return new TicketMensalista(cliente, vaga, veiculo, tarifa);
        }
    }

    public TarifaMensalista buscarTarifaMensalista(ArrayList<TarifaEstacionamento> tarifas) {
        TarifaMensalista tarifa = null;

        for (TarifaEstacionamento tarifaAtual : tarifas) { //olha pra todas as tarifas

            if (tarifaAtual instanceof TarifaMensalista) { //procura pelo tipo de tarifa especificado

                if (tarifa == null)
                    tarifa = (TarifaMensalista) tarifaAtual;
                else {
                    if (tarifa.getDataInicio().isBefore(tarifaAtual.getDataInicio()))
                        tarifa = (TarifaMensalista) tarifaAtual;
                }
            }
        }

        return tarifa;
    }

    public TarifaHorista buscarTarifaHorista(ArrayList<TarifaEstacionamento> tarifas) {

        TarifaHorista tarifa = null;
        boolean achouDia = false;

        for (TarifaEstacionamento tarifaAtual : tarifas) { //olha pra todas as tarifas

            if (tarifaAtual instanceof TarifaHorista) { //procura pelo tipo de tarifa especificado

                for (DiaDaSemana diasTarifa : ((TarifaHorista) tarifaAtual).getDiaDaSemana()) {

                    DiaDaSemana diaAtual = DiaDaSemana.valueOf(StringUtils.getDiaDaSemana(LocalDateTime.now()));

                    if (diaAtual == diasTarifa) { //verifica se a tarifa é valida pro dia atual
                        achouDia = true;
                    }
                }

                if (achouDia) {

                    if (tarifa == null)
                        tarifa = (TarifaHorista) tarifaAtual;

                    else {

                        if (tarifa.getDataInicio().isBefore(tarifaAtual.getDataInicio()))
                            tarifa = (TarifaHorista) tarifaAtual;
                    }
                }
            }
        }

        return tarifa;
    }

    public TicketMensalista buscarTicketMensalista(ArrayList<TicketEstacionamento> tickets, String placa) {

        LocalDateTime dataAtual = LocalDateTime.now();

        for (TicketEstacionamento ticket : tickets) {

            if (placa.equals(ticket.getVeiculo().getPlaca()) && ticket.getTarifa() instanceof TarifaMensalista) {

                if (dataAtual.isBefore(ticket.getDataFim())) {
                    return (TicketMensalista) ticket;
                }
            }
        }

        return null;
    }

    public TicketHorista buscarTicketHorista(ArrayList<TicketEstacionamento> tickets, String placa) {

        for (TicketEstacionamento ticket : tickets) {

            if (placa.equals(ticket.getVeiculo().getPlaca()) && ticket.getDataFim() == null) {
                return (TicketHorista) ticket;
            }

        }

        return null;
    }

    public void retirar(ArrayList<TicketEstacionamento> tickets) {

        long totalHoras;
        String placa;
        TicketEstacionamento ticket;

        placa = interfaceUsuario.selecionarString("Digite a placa do veiculo: ");
        placa = StringUtils.formatarPadraoCapturaDeDados(placa);

        ticket = buscarTicketHorista(tickets, placa);

        if (ticket == null)
            ticket = buscarTicketMensalista(tickets, placa);

        if (ticket == null)
            interfaceUsuario.exibir("Ticket não cadastrado!");

        else if (ticket.getTarifa() instanceof TarifaHorista) {

            ticket.encerrarTicket();

            totalHoras = CalculoUtils.calcularTempoEstacionado(ticket.getDataInicio(), ticket.getDataFim());
            interfaceUsuario.exibir("Tempo total: " + totalHoras);
            interfaceUsuario.exibir("Total a pagar: " + ticket.getTotalPagar());

            ticket.getVaga().liberar();
            interfaceUsuario.exibir("Ticket encerrado com sucesso!");

        }

        interfaceUsuario.exibir("Carro pronto para ser retirado!");


    }

    public void listarVagas(ArrayList<Vaga> vagas) {
        for (Vaga vaga : vagas)
            if (vaga.getStatus() == VagaStatus.DISPONIVEL)
                interfaceUsuario.exibir(vaga.toString());
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


}
