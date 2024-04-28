package kitmenu;

import automovel.Cor;
import automovel.Modelo;
import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import enums.TipoVeiculo;
import excecoes.EstacionamentoException;
import ingressos.TicketEstacionaBem;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class MenuGerenciaCliente {

    private UI terminal;

    public MenuGerenciaCliente() {
    }

    public MenuGerenciaCliente(UI terminal) {
        this.terminal = terminal;
    }

    public static String formatarString(String string) {
        return string.toUpperCase().replaceAll("\\s", "");
    }

    public void gerenciarCliente(ArrayList<Cliente> clientes, ArrayList<TicketEstacionaBem> tickets) {
        Cliente cliente;
        String documento;
        byte opcao;

        terminal.menuGerenciaCliente();
        opcao = terminal.selecionarByte("Digite a opção desejada: ");

        do {
            switch (opcao) {
                case 1:
                    cliente = cadastrarCliente();
                    clientes.add(cliente);
                    break;
                case 2:
                    documento = terminal.selecionarString("Digite o documento do cliente que deseja pesquisar: ");
                    cliente = consultaCliente(clientes, documento);
                    if (cliente != null) terminal.exibir(cliente.toString());
                    else terminal.exibir("Cliente não cadastrado");
                    break;
                case 3:

                    documento = terminal.selecionarString("Digite o documento do cliente que deseja excluir: ");
                    cliente = consultaCliente(clientes, documento);
                    if(cliente != null){
                        if(verificaTicketCliente(cliente.getVeiculos(), tickets) == 0)
                            clientes.remove(cliente);
                        else terminal.exibir("O cliente possui ticket não pago!");
                    }

                    break;
                case 4:
                    String novoNome, novoDocumento;

                    documento = terminal.selecionarString("\nDigite o documento do cliente que deseja editar: ");
                    cliente = consultaCliente(clientes, documento);
                    if (cliente != null) {

                        terminal.exibir("Nome antigo: " + cliente.getNome());
                        novoNome = terminal.selecionarString("Novo nome: ");
                        cliente.setNome(novoNome);

                        terminal.exibir("Documento antigo: " + cliente.getDocumento());
                        novoDocumento = terminal.selecionarString("Novo documento: ");
                        cliente.setDocumento(novoDocumento);

                    } else terminal.exibir("Cliente não cadastrado");

                    break;
                case 5:
                    byte opcaoSubmenu;

                    documento = terminal.selecionarString("\nDigite o documento do cliente que deseja ver os veículos: ");
                    Cliente clienteAtual = consultaCliente(clientes, documento);

                    if (clienteAtual != null) {
                        terminal.subMenuGerenciaVeiculos();
                        opcaoSubmenu = terminal.selecionarByte("Digite a opção desejada: ");
                        editarVeiculos(opcaoSubmenu, clienteAtual.getVeiculos(), tickets);

                    } else {
                        throw new EstacionamentoException("Documento " + documento + " não existe!");
                    }
                    break;
                case 6:

                    for (Cliente pessoa : clientes) {
                        terminal.exibir(pessoa.toString());
                        pessoa.mostraVeiculos();
                    }

                    break;

            }
            terminal.menuGerenciaCliente();
            opcao = terminal.selecionarByte("Digite a opção desejada: ");
        } while (opcao != 7);
    }

    public byte verificaTicketCliente(ArrayList<Veiculo> veiculos, ArrayList<TicketEstacionaBem> tickets)
    {
        for(Veiculo veiculo : veiculos)
            if(verificaSeOVeiculoTemTicket(tickets, veiculo) == 1)
                return 1;

        return 0;
    }

    public Cliente cadastrarCliente() {
        Veiculo veiculo;
        String nome, documento;
        int qtdCarros;

        nome = terminal.selecionarString("Digite nome do cliente: ");
        documento = terminal.selecionarString("Digite o documento do cliente: ");
        Cliente cliente = new Cliente(nome, documento);

        qtdCarros = terminal.selecionarByte("O cliente possui quantos veículos: ");

        if (qtdCarros > 0) {
            for (int i = 0; i < qtdCarros; i++) {

                terminal.exibir("\nDados do veiculo #" + (i+1));
                veiculo = cadastraVeiculo();

                Veiculo veiculoExiste = consultarVeiculo(cliente.getVeiculos(), veiculo.getPlaca());

                if (veiculoExiste != null)
                    throw new EstacionamentoException("Ja existe um veiculo cadastrado com a placa: " + veiculo.getPlaca());

                cliente.addVeiculo(veiculo);
            }
        }
        return cliente;
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


    public void editarVeiculos(byte opcao, ArrayList<Veiculo> veiculos, ArrayList<TicketEstacionaBem> tickets) {
        Veiculo veiculo = new Veiculo();
        String nomeCor, placa;

        switch (opcao) {

            case 1:

                if (veiculos.isEmpty()) {
                    throw new EstacionamentoException("O cliente não possui veiculos cadastrados");
                }
                for (Veiculo veiculoAtual : veiculos)
                    terminal.exibir(veiculoAtual.toString());

                break;
            case 2:

                veiculo = cadastraVeiculo();

                Veiculo veiculoExiste = consultarVeiculo(veiculos, veiculo.getPlaca());

                if (veiculoExiste != null)
                    throw new EstacionamentoException("Ja existe um veiculo cadastrado com a placa: " + veiculo.getPlaca());

                veiculos.add(veiculo);
                terminal.exibir("Veiculo cadastrado com sucesso!");

                break;
            case 3:

                placa = terminal.selecionarString("Digite a placa do veiculo que vai ser excluido: ");
                excluiVeiculo(veiculos, formatarString(placa), tickets);
                terminal.exibir("Veiculo excluido com sucesso!");

                break;
            case 4:

                placa = terminal.selecionarString("Digite a placa do veiculo que vai ser alterada a cor: ");
                placa = Veiculo.formatarString(placa);
                veiculo = consultarVeiculo(veiculos, placa);
                if (veiculo != null) {
                    nomeCor = terminal.selecionarString("Digite a nova cor: ");
                    Cor cor = new Cor(nomeCor);
                    veiculo.setCor(cor);
                    terminal.exibir("Cor alterada com sucesso");
                }

                break;
            default:
                throw new EstacionamentoException("Opção inválida de menu");
        }
    }

    public Veiculo cadastraVeiculo() {
        String placa, modelo, cor, tipo;

        placa = terminal.selecionarString("Digite a placa do veiculo: ");

        modelo = terminal.selecionarString("Digite o modelo do veiculo: ");
        Modelo modeloCarro = new Modelo(modelo);

        cor = terminal.selecionarString("Digite a cor do veiculo: ");
        Cor corCarro = new Cor(cor);

        tipo = terminal.selecionarString("Digite se seu veiculo é carro ou moto: ");
        tipo = formatarString(tipo);


        return new Veiculo(placa, corCarro, modeloCarro, TipoVeiculo.valueOf(tipo));
    }

    public void excluiVeiculo(ArrayList<Veiculo> veiculos, String placa, ArrayList<TicketEstacionaBem> tickets) {

        Veiculo veiculo = consultarVeiculo(veiculos, placa);

        if (veiculo == null) {
            throw new EstacionamentoException("A placa: " + placa + " não existe");
        }

        if (verificaSeOVeiculoTemTicket(tickets, veiculo) == 1){
            throw new InputMismatchException("O veiculo possui ticket não pago!");
        }
        veiculos.remove(veiculo);
    }

    public byte verificaSeOVeiculoTemTicket(ArrayList<TicketEstacionaBem> tikets, Veiculo veiculo) {
        for (TicketEstacionaBem ticket : tikets) {

            String placaVeiculoTicket = ticket.getVeiculo().getPlaca();

            if (veiculo.getPlaca().equals(placaVeiculoTicket))
                return 1;
        }
        return 0;
    }

}
