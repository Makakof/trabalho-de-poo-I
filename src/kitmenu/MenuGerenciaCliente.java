package kitmenu;

import cliente.estacionabem.Cliente;
import dados.Repositorio;
import enums.OpcaoMenuGerenciaCliente;
import excecoes.EstacionamentoException;
import ingressos.TicketEstacionamento;
import interfaces.InterfaceUsuario;
import interfaces.Terminal;
import operacoes.FuncionalidadesCliente;
import java.util.List;

public class MenuGerenciaCliente {

    public static void gerenciarCliente() {
        InterfaceUsuario interfaceUsuario = Repositorio.getInstance().getUI();;
        FuncionalidadesCliente funcCliente = new FuncionalidadesCliente();
        List<Cliente> clientes = Repositorio.getInstance().getClientes();
        List<TicketEstacionamento> tickets = Repositorio.getInstance().getTickets();
        Cliente cliente;
        String documento;
        byte opcao;

        do {

            interfaceUsuario.exibirMenuGerenciaCliente();
            opcao = interfaceUsuario.selecionarByte("Digite a opção desejada: ");

            switch (opcao) {
                case 1: // cadastrar cliente

                    cliente = funcCliente.cadastrarCliente();
                    clientes.add(cliente);
                    break;
                case 2: // mostrar informações cliente

                    documento = interfaceUsuario.selecionarString("Digite o documento do cliente que deseja pesquisar: ");
                    cliente = funcCliente.consultaCliente(clientes, documento);

                    if (cliente == null)
                        throw new EstacionamentoException("Nenhum cliente cadastrado com este documento: " + documento);

                    interfaceUsuario.exibir(cliente.toString());

                    break;
                case 3: // excluir cliente

                    documento = interfaceUsuario.selecionarString("Digite o documento do cliente que deseja excluir: ");
                    cliente = funcCliente.consultaCliente(clientes, documento);

                    if(cliente == null)
                        throw new EstacionamentoException("Nenhum cliente cadastrado com este documento: " + documento);

                    if (FuncionalidadesCliente.verificaSeOClienteTemTicket(cliente.getVeiculos(), tickets))
                        throw new EstacionamentoException("Não é possivel excluir clientes que possuem carros estacionados");

                    clientes.remove(cliente);

                    break;
                case 4: // editar informações cliente

                    documento = interfaceUsuario.selecionarString("\nDigite o documento do cliente que deseja editar: ");
                    funcCliente.editarCliente(clientes, documento);

                    break;
                case 5: // chamada do menu para veiculos
                    byte opcaoSubmenu;

                    documento = interfaceUsuario.selecionarString("\nDigite o documento do cliente que deseja ver os veículos: ");
                    Cliente clienteAtual = funcCliente.consultaCliente(clientes, documento);

                    if (clienteAtual == null) {
                        throw new EstacionamentoException("Nenhum cliente cadastrado com este documento: " + documento);

                    }

                    interfaceUsuario.exibirSubMenuGerenciaVeiculos();
                    opcaoSubmenu = interfaceUsuario.selecionarByte();
                    SubMenuGerenciarVeiculos.editarVeiculos(opcaoSubmenu, clienteAtual.getVeiculos(), tickets);

                    break;
                case 6:

                    funcCliente.listarCadastros(clientes);

                    break;
                case 7:
                    break;
                default:
                    throw new EstacionamentoException("Opção inválida de menu");

            }
        } while (opcao != OpcaoMenuGerenciaCliente.SAIR.ordinal()+1);
    }
}
