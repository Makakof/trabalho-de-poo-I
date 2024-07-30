package operacoes;

import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import dados.Repositorio;
import enums.VagaStatus;
import excecoes.ExcecaoAbstrata;
import excecoes.ExcecaoEntradaInvalida;
import excecoes.ExcecaoEstacionamento;
import ingressos.TicketEstacionamento;
import ingressos.TicketHorista;
import ingressos.TicketMensalista;
import interfaces.InterfaceUsuario;
import modelagem.Vaga;
import tarifacao.TarifaEstacionamento;
import tarifacao.TarifaHorista;
import tarifacao.TarifaMensalista;
import utilitarios.CalculoUtils;
import utilitarios.StringUtils;
import java.util.List;

public class FuncionalidadesEstacionamento {

    private final InterfaceUsuario interfaceUsuario;

    public FuncionalidadesEstacionamento() {
        interfaceUsuario = Repositorio.getInstance().getUI();
    }

    public TicketEstacionamento estacionar(List<Cliente> clientes, List<TarifaEstacionamento> tarifas, List<Vaga> vagas)
    {
        int numeroDaVaga;
        String documento, placa, modoDeEstacionar;
        Cliente cliente;
        Veiculo veiculo;
        Vaga vaga;

        documento = interfaceUsuario.selecionarString("Motorista digite um documento: ");
        documento = StringUtils.formatarPadraoCapturaDeDados(documento);
        cliente = consultarCliente(clientes, documento);

        if (cliente == null) {
            throw new ExcecaoEntradaInvalida("Cliente não cadastrado","Funcionalidade Estacionamento",2);
        }

        placa = interfaceUsuario.selecionarString("Digite o numero da placa do veiculo: ");
        placa = StringUtils.formatarPadraoCapturaDeDados(placa);
        veiculo = consultarVeiculo(cliente.getVeiculos(), placa);

        if (veiculo == null) {
            throw new ExcecaoEntradaInvalida("Veiculo não cadastrado","Funcionalidade Estacionamento",2);
        }

        modoDeEstacionar = interfaceUsuario.selecionarString("Estacionar como HORISTA ou MENSALISTA: ");
        modoDeEstacionar = StringUtils.formatarPadraoCapturaDeDados(modoDeEstacionar);

        if (modoDeEstacionar.equals("MENSALISTA")) {

            TicketEstacionamento ticket = FuncionalidadesTicket.buscarTicketMensalista(Repositorio.getInstance().getTickets(), veiculo.getPlaca());

            if (ticket != null)
                return ticket;
        }

        numeroDaVaga = interfaceUsuario.selecionarInt("Digite o numero da vaga: ");
        vaga = FuncionalidadesVaga.consultarVaga(vagas, numeroDaVaga);

        if (vaga == null) {
            throw new ExcecaoEntradaInvalida("Vaga não cadastrado","Funcionalidade Estacionamento",2);
        }

        if (vaga.getTipoVeiculo() != veiculo.getTipoVeiculo()) {
            throw new ExcecaoEntradaInvalida("Veiculo não cadastrado","Funcionalidade Estacionamento",2);
        }

        if (vaga.getStatus() != VagaStatus.DISPONIVEL) {
            throw new ExcecaoEstacionamento("Vaga OCUPADA ou INDISPONIVEL!","Funcionalidade Estacionamento",3);
        }


        if (modoDeEstacionar.equals("HORISTA")) {

            TarifaHorista tarifa = FuncionalidadesTarifa.buscarTarifaHorista(tarifas);

            if (tarifa == null) {
                throw new ExcecaoEstacionamento("Nenhuma tarifa mensalista encontrada!","Funcionalidade Estacionamento",3);
            }

            return new TicketHorista(cliente, vaga, veiculo, tarifa);
        } else {

            TarifaMensalista tarifa = FuncionalidadesTarifa.buscarTarifaMensalista(tarifas);

            if (tarifa == null) {
                throw new ExcecaoEstacionamento("Nenhuma tarifa horista encontrada!","Funcionalidade Estacionamento",3);

            return new TicketMensalista(cliente, vaga, veiculo, tarifa);
        }
    }

    public void retirar(List<TicketEstacionamento> tickets) {

        long totalHoras;
        String placa;
        TicketEstacionamento ticket;

        placa = interfaceUsuario.selecionarString("Digite a placa do veiculo: ");
        placa = StringUtils.formatarPadraoCapturaDeDados(placa);

        ticket = FuncionalidadesTicket.buscarTicketHorista(tickets, placa);

        if (ticket == null)
            ticket = FuncionalidadesTicket.buscarTicketMensalista(tickets, placa);

        if (ticket == null)
            throw new ExcecaoEstacionamento("Nenhum ticket encontrado!","Funcionalidade Estacionamento",3);

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

    public Cliente consultarCliente(List<Cliente> clientes, String documento) {

        for (Cliente clienteAtual : clientes) {
            if (clienteAtual.getDocumento().equals(documento)) return clienteAtual;
        }

        return null;
    }

    public Veiculo consultarVeiculo(List<Veiculo> veiculos, String placa) {

        for (Veiculo veiculoAtual : veiculos) {
            if (veiculoAtual.getPlaca().equals(placa)) return veiculoAtual;
        }

        return null;
    }
}
