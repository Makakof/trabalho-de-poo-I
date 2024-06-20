package kitmenu;


import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import dados.Repositorio;
import excecoes.EstacionamentoException;
import ingressos.TicketEstacionamento;
import interfaces.InterfaceUsuario;
import interfaces.Terminal;

import java.util.ArrayList;


public class MenuGerenciaCliente {

    private final InterfaceUsuario interfaceUsuario;

    public MenuGerenciaCliente() {
        this.interfaceUsuario = Terminal.getInstance();
    }

    public void gerenciarCliente() {
        SubMenuGerenciarVeiculos subMenuEditarVeiculos = new SubMenuGerenciarVeiculos();
        ArrayList<Cliente> clientes = Repositorio.getInstance().getClientes();
        ArrayList<TicketEstacionamento> tickets = Repositorio.getInstance().getTickets();
        Cliente cliente;
        String documento;
        byte opcao;

        do {

            interfaceUsuario.menuGerenciaCliente();
            opcao = interfaceUsuario.selecionarByte("Digite a opção desejada: ");

            switch (opcao) {
                case 1:

                    cliente = cadastrarCliente();
                    clientes.add(cliente);
                    break;
                case 2:
                    documento = interfaceUsuario.selecionarString("Digite o documento do cliente que deseja pesquisar: ");
                    cliente = consultaCliente(clientes, documento);

                    if (cliente == null)
                        throw new EstacionamentoException("Nenhum cliente cadastrado com este documento: " + documento);

                    interfaceUsuario.exibir(cliente.toString());

                    break;
                case 3:

                    documento = interfaceUsuario.selecionarString("Digite o documento do cliente que deseja excluir: ");
                    cliente = consultaCliente(clientes, documento);

                    if(cliente == null)
                        throw new EstacionamentoException("Nenhum cliente cadastrado com este documento: " + documento);

                    if (verificaSeOClienteTemTicket(cliente.getVeiculos(), tickets))
                        throw new EstacionamentoException("Não é possivel excluir clientes que possuem carros estacionados");

                    clientes.remove(cliente);

                    break;
                case 4:
                    String novoNome, novoDocumento;

                    documento = interfaceUsuario.selecionarString("\nDigite o documento do cliente que deseja editar: ");
                    cliente = consultaCliente(clientes, documento);
                    if (cliente != null) {

                        interfaceUsuario.exibir("Nome antigo: " + cliente.getNome());
                        novoNome = interfaceUsuario.selecionarString("Novo nome: ");
                        cliente.setNome(novoNome);

                        interfaceUsuario.exibir("Documento antigo: " + cliente.getDocumento());
                        novoDocumento = interfaceUsuario.selecionarString("Novo documento: ");
                        cliente.setDocumento(novoDocumento);

                    } else interfaceUsuario.exibir("Cliente não cadastrado");

                    break;
                case 5:
                    byte opcaoSubmenu;

                    documento = interfaceUsuario.selecionarString("\nDigite o documento do cliente que deseja ver os veículos: ");
                    Cliente clienteAtual = consultaCliente(clientes, documento);

                    if (clienteAtual == null) {
                        throw new EstacionamentoException("Nenhum cliente cadastrado com este documento: " + documento);

                    }

                    interfaceUsuario.subMenuGerenciaVeiculos();
                    opcaoSubmenu = interfaceUsuario.selecionarByte();
                    subMenuEditarVeiculos.editarVeiculos(opcaoSubmenu, clienteAtual.getVeiculos(), tickets);

                    break;
                case 6:

                    for (Cliente pessoa : clientes) {
                        interfaceUsuario.exibir(pessoa.toString());
                        pessoa.mostraVeiculos();
                    }

                    break;
                case 7:
                    break;
                default:
                    throw new EstacionamentoException("Opção inválida de menu");

            }
        } while (opcao != 7);
    }

    public boolean verificaSeOClienteTemTicket(ArrayList<Veiculo> veiculos, ArrayList<TicketEstacionamento> tickets)
    {
        SubMenuGerenciarVeiculos subMenuEditarVeiculos = new SubMenuGerenciarVeiculos();
        for(Veiculo veiculo : veiculos)
            if(subMenuEditarVeiculos.verificaSeOVeiculoTemTicket(tickets, veiculo))
                return true;

        return false;
    }

    public Cliente cadastrarCliente() {
        SubMenuGerenciarVeiculos subMenuEditarVeiculos = new SubMenuGerenciarVeiculos();
        Veiculo veiculo;
        String nome, documento;
        int qtdCarros;

        nome = interfaceUsuario.selecionarString("Digite nome do cliente: ");
        documento = interfaceUsuario.selecionarString("Digite o documento do cliente: ");
        Cliente cliente = new Cliente(nome, documento);

        qtdCarros = interfaceUsuario.selecionarByte("O cliente possui quantos veículos: ");

        if (qtdCarros > 0) {
            for (int i = 0; i < qtdCarros; i++) {

                interfaceUsuario.exibir("\nDados do veiculo #" + (i+1));
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
