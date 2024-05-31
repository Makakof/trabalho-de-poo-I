package kitmenu;


import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import dados.Repositorio;
import enums.DiaDaSemana;
import enums.HoristaMensalista;
import enums.VagaStatus;
import excecoes.EstacionamentoException;
import ingressos.TicketEstacionamento;
import ingressos.TicketHorista;
import ingressos.TicketMensalista;
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
        ArrayList<Cliente> clientes = Repositorio.getInstance().getClientes();
        ArrayList<Vaga> vagas = Repositorio.getInstance().getVagas();
        ArrayList<TarifaEstacionamento> tarifas = Repositorio.getInstance().getTarifas();

        byte opcao;

        do {

            terminal.menuGerenciaEstacionamento();
            opcao = terminal.selecionarByte("Digite a opção desejada: ");

            switch (opcao) {
                case 1: // estacionar

                    TicketEstacionamento ticket = estacionar(clientes, tarifas, vagas);

                    if (ticket.getTarifa().getModoDeEstacionar() == HoristaMensalista.MENSALISTA && ticket.getDataFim() == null){

                        ticket.encerrarTicket();
                        tickets.add(ticket);

                    }else
                        tickets.add(ticket);

                    break;
                case 2: // retirar

                    retirar(tickets);
                    break;
                case 3: // listar vagas

                    listarVagas(vagas);
                    break;
                case 4: // gerenciar tarifas
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

        modoDeEstacionar = terminal.selecionarString("Estacionar como HORISTA ou MENSALISTA: ");

        if (modoDeEstacionar.equals(HoristaMensalista.MENSALISTA.name())){

            TicketEstacionamento ticket = buscarTicketMensalista(Repositorio.getInstance().getTickets(), veiculo.getPlaca());

            if (ticket != null)
                return ticket;
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

        tipo = HoristaMensalista.valueOf(modoDeEstacionar);

        tarifa = buscarTarifa(tarifas, tipo);

        if (tipo == HoristaMensalista.HORISTA)
            return new TicketHorista(cliente, vaga, veiculo, tarifa);
        else
            return new TicketMensalista(cliente, vaga, veiculo, tarifa);
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

    public TicketEstacionamento buscarTicketMensalista (ArrayList<TicketEstacionamento> tickets, String placa){

        LocalDateTime dataAtual = LocalDateTime.now();

        for (TicketEstacionamento ticket : tickets){

            if(placa.equals(ticket.getVeiculo().getPlaca()) && HoristaMensalista.MENSALISTA.equals(ticket.getTarifa().getModoDeEstacionar())){

                if (dataAtual.isBefore(ticket.getDataFim())){
                    return ticket;
                }
            }
        }

        return null;
    }

    public TicketEstacionamento buscarTicketHorista (ArrayList<TicketEstacionamento> tickets, String placa) {

        for (TicketEstacionamento ticket : tickets) {

            if (placa.equals(ticket.getVeiculo().getPlaca()) && ticket.getDataFim() == null){
                return ticket;
            }

        }

        return null;
    }

    public void retirar(ArrayList<TicketEstacionamento> tickets) {

        long totalHoras;
        String placa;
        TicketEstacionamento ticket;

        placa = terminal.selecionarString("Digite a placa do veiculo: ");

        ticket = buscarTicketHorista(tickets, placa);

        if (ticket == null)
            ticket = buscarTicketMensalista(tickets, placa);

        if (ticket == null)
            terminal.exibir("Ticket não cadastrado!");

        else if (ticket.getTarifa().getModoDeEstacionar() == HoristaMensalista.HORISTA) {

            ticket.encerrarTicket();

            totalHoras = Util.calcularHoras(ticket.getDataInicio(), ticket.getDataFim());
            terminal.exibir("Tempo total: " + totalHoras);
            terminal.exibir("Total a pagar: " + ticket.getTotalPagar());

            ticket.getVaga().setVagaStatus(VagaStatus.DISPONIVEL);
            terminal.exibir("Ticket encerrado com sucesso!");

        }

        terminal.exibir("Carro pronto para ser retirado!");


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




}
