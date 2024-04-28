package kitmenu;


import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import enums.DiaDaSemana;
import enums.TipoVeiculo;
import enums.VagaStatus;
import excecoes.EstacionamentoException;
import ingressos.TicketEstacionaBem;
import modelagem.Vaga;
import tarifacao.TarifaEstacionaBem;
import tarifacao.TabelaPrecos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class MenuGerenciaEstacionamento {

    private UI terminal;

    public MenuGerenciaEstacionamento(UI terminal) {
        this.terminal = terminal;
    }

    public static String formatarString(String string)
    {
        return string.toUpperCase().replaceAll("\\s", "");
    }

    public void gerenciarEstacionamento(ArrayList<Cliente> clientes, ArrayList<TicketEstacionaBem> tickets, ArrayList<TicketEstacionaBem> logTickets, ArrayList<Vaga> vagas, TabelaPrecos[] valorHorasCarro, TabelaPrecos[] valorHorasMoto) {

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

                    TabelaPrecos[] valorHoras = selecionarArray(valorHorasCarro, valorHorasMoto);
                    gerenciarTarifas(valorHoras);

                    break;
            }
        } while (opcao != 5);
    }

    public TicketEstacionaBem estacionar(ArrayList<Cliente> clientes, ArrayList<TicketEstacionaBem> tickets, ArrayList<Vaga> vagas, TabelaPrecos[] valorHorasCarro, TabelaPrecos[] valorHorasMoto) {

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

        vaga.setVagaStatus("OCUPADA");

        if(veiculo.getTipoVeiculo() == TipoVeiculo.CARRO)
            tarifa = new TarifaEstacionaBem(valorHorasCarro);
        else
            tarifa = new TarifaEstacionaBem(valorHorasMoto);

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

            ticket.getVaga().setVagaStatus("DISPONIVEL");

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

    public void gerenciarTarifas(TabelaPrecos[] valorHoras) {

        byte opcao;

        terminal.menuGerenciaTarifas();
        opcao = terminal.selecionarByte("Digite a opção desejada: ");

        switch (opcao)
        {
            case 1:
                cadastrarTarifa(valorHoras);
                break;
            case 2:
                editarTarifa(valorHoras);
                break;
        }
    }

    public void cadastrarTarifa(TabelaPrecos[] valorHoras){

        double primeiraHora, horaSubsequente;

        for(DiaDaSemana dia : DiaDaSemana.values()){

            terminal.exibir(dia.name());
            primeiraHora = terminal.selecionarDouble("Valor Primeira Hora: ");
            horaSubsequente = terminal.selecionarDouble("Valor Hora Subsequente: ");

            TabelaPrecos tabelaPrecos = new TabelaPrecos(primeiraHora, horaSubsequente);
            valorHoras[dia.getValorOpcao()] = tabelaPrecos;
        }
    }

    public void editarTarifa(TabelaPrecos[] valorHoras){

        mostraTabelaPrecos(valorHoras);
        String diaDaSemana = terminal.selecionarString("Digite o dia da semana que deseja alterar: ");
        double primeiraHora = terminal.selecionarDouble("Valor primeira hora: ");
        double horaSubsequente = terminal.selecionarDouble("Valor hora subsequente: ");

        diaDaSemana = formatarString(diaDaSemana);
        DiaDaSemana dia = DiaDaSemana.valueOf(diaDaSemana);

        valorHoras[dia.getValorOpcao()].setPrimeiraHora(primeiraHora);
        valorHoras[dia.getValorOpcao()].setHoraSubsequente(horaSubsequente);
    }

    public void mostraTabelaPrecos(TabelaPrecos[] valorHoras){

        terminal.exibir("*************************");
        for(DiaDaSemana dia: DiaDaSemana.values()){
            terminal.exibir(dia.name());
            terminal.exibir(valorHoras[dia.getValorOpcao()].toString());
            terminal.exibir("--------------------------");
        }

    }

    public TabelaPrecos[] selecionarArray(TabelaPrecos[] valorHorasCarro, TabelaPrecos[] valorHorasMoto)
    {
        String tipo = terminal.selecionarString("Digite o tipo do veiculo (CARRO ou MOTO): ");
        tipo = formatarString(tipo);

        if (TipoVeiculo.CARRO == TipoVeiculo.valueOf(tipo))
            return valorHorasCarro;
        else
            return valorHorasMoto;
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
