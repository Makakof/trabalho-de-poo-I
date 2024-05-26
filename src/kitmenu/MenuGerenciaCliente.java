package kitmenu;


import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import dados.Repositorio;
import excecoes.EstacionamentoException;
import ingressos.TicketEstacionaBem;

import java.util.ArrayList;


public class MenuGerenciaCliente {

    private UI terminal;

    public MenuGerenciaCliente() {
        this.terminal = UI.getInstance();
    }

    public void gerenciarCliente() {
        SubMenuEditarVeiculos subMenuEditarVeiculos = new SubMenuEditarVeiculos();
        ArrayList<Cliente> clientes = Repositorio.getInstance().getClientes();
        ArrayList<TicketEstacionaBem> tickets = Repositorio.getInstance().getTickets();
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

                    if (cliente == null)
                        throw new EstacionamentoException("Nenhum cliente cadastrado com este documento: " + documento);

                    terminal.exibir(cliente.toString());

                    break;
                case 3:

                    documento = terminal.selecionarString("Digite o documento do cliente que deseja excluir: ");
                    cliente = consultaCliente(clientes, documento);

                    if(cliente == null)
                        throw new EstacionamentoException("Nenhum cliente cadastrado com este documento: " + documento);

                    if (verificaTicketCliente(cliente.getVeiculos(), tickets) != 0)
                        throw new EstacionamentoException("Não é possivel excluir clientes que possuem carros estacionados");

                    clientes.remove(cliente);

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

                    if (clienteAtual == null) {
                        throw new EstacionamentoException("Nenhum cliente cadastrado com este documento: " + documento);

                    }

                    terminal.subMenuGerenciaVeiculos();
                    opcaoSubmenu = terminal.selecionarByte();
                    subMenuEditarVeiculos.editarVeiculos(opcaoSubmenu, clienteAtual.getVeiculos(), tickets);

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
        SubMenuEditarVeiculos subMenuEditarVeiculos = new SubMenuEditarVeiculos();
        for(Veiculo veiculo : veiculos)
            if(subMenuEditarVeiculos.verificaSeOVeiculoTemTicket(tickets, veiculo) == 1)
                return 1;

        return 0;
    }

    public Cliente cadastrarCliente() {
        SubMenuEditarVeiculos subMenuEditarVeiculos = new SubMenuEditarVeiculos();
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
                veiculo = subMenuEditarVeiculos.cadastraVeiculo();

                Veiculo veiculoExiste = subMenuEditarVeiculos.consultarVeiculo(cliente.getVeiculos(), veiculo.getPlaca());

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
}
