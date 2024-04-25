package kitmenu;

import automovel.Cor;
import automovel.Modelo;
import automovel.Veiculo;
import cliente.estacionabem.Cliente;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class MenuGerenciaCliente {

    private Ui terminal;

    public MenuGerenciaCliente() {
    }

    public MenuGerenciaCliente(Ui terminal) {
        this.terminal = terminal;
    }

    public void gerenciaCliente(ArrayList<Cliente> clientes, ArrayList<Veiculo> veiculos) {
        Cliente cliente;
        String documento;
        int index;
        byte opcao;

        do {

            terminal.menuGerenciaCliente();
            opcao = terminal.selecionarByte("Digite a opção desejada: ");

            switch (opcao) {
                case 1:
                    cliente = cadastrarCliente();
                    clientes.add(cliente);
                    break;
                case 2:
                    documento = terminal.selecionarString("Digite o documento do cliente que deseja pesquisar: ");
                    cliente = consultaCliente(clientes, documento);
                    if (cliente != null)
                        terminal.exibir(cliente.toString());
                    else
                        terminal.exibir("Cliente não cadastrado");
                    break;
                case 3:

                    documento = terminal.selecionarString("Digite o documento do cliente que deseja excluir: ");

                    index = consultarIndexCliente(clientes, documento);
                    clientes.remove(index);

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

                    } else
                        terminal.exibir("Cliente não cadastrado");

                    break;
                case 5:
                    byte opcaoSubmenu;

                    documento = terminal.selecionarString("\nDigite o documento do cliente que deseja ver os veículos: ");
                    Cliente clienteAtual = consultaCliente(clientes, documento);

                    if (clienteAtual != null) {

                        terminal.subMenuGerenciaVeiculos();
                        opcaoSubmenu = terminal.selecionarByte("Digite a opção desejada: ");
                        subMenuGerenciaClientes(opcaoSubmenu, clienteAtual.getVeiculos());

                    } else
                        throw new InvalidParameterException("Documento " + documento + " não existe!");

                    break;
                case 6:

                    for (Cliente pessoa : clientes) {
                        System.out.println(pessoa);
                        pessoa.mostraVeiculos();
                    }

                    break;

            }
        } while (opcao != 7);
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

                terminal.exibir("Dados do carro " + i);
                veiculo = cadastraVeiculo();
                cliente.addVeiculo(veiculo);
            }
        }
        return cliente;
    }

    public Cliente consultaCliente(ArrayList<Cliente> clientes, String documento) {

        for (Cliente clienteAtual : clientes) {
            if (clienteAtual.getDocumento().equals(documento))
                return clienteAtual;
        }

        return null;
    }

    public int consultarIndexCliente(ArrayList<Cliente> clientes, String documento) {

        for (Cliente clienteAtual : clientes) {
            if (clienteAtual.getDocumento().equals(documento))
                return clientes.indexOf(clienteAtual);
        }

        return -1;
    }

    public int consultarIndexVeiculo(ArrayList<Veiculo> veiculos, String placa) {

        for (Veiculo veiculoAtual : veiculos) {
            if (veiculoAtual.getPlaca().equals(placa))
                return veiculos.indexOf(veiculoAtual);
        }

        return -1;
    }


    public void subMenuGerenciaClientes(byte opcao, ArrayList<Veiculo> veiculos) {
        Veiculo veiculo;
        String cor, placa;
        int index;

        switch (opcao) {

            case 1:

                if (!veiculos.isEmpty()) {
                    for (Veiculo veiculoAtual : veiculos)
                        System.out.println(veiculoAtual);
                } else
                    terminal.exibir("O cliente não possui veiculos cadastrados");

                break;
            case 2:

                veiculo = cadastraVeiculo();
                veiculos.add(veiculo);

                break;
            case 3:

                placa = terminal.selecionarString("Digite a placa do veiculo que vai ser excluido: ");
                index = consultarIndexVeiculo(veiculos, placa);

                if (index != -1)
                    veiculos.remove(index);
                else
                    throw new InputMismatchException("A placa: " + placa + " não existe");

                break;
            case 4:

                placa = terminal.selecionarString("Digite a placa do veiculo que vai ser alterada a cor: ");
                index = consultarIndexVeiculo(veiculos, placa);

                if (index != -1) {
                    cor = terminal.selecionarString("Digite a nova cor: ");
                }

                break;

        }
    }

    public Veiculo cadastraVeiculo() {

        String placa, modelo, cor;

        placa = terminal.selecionarString("Digite a placa do carro: ");

        modelo = terminal.selecionarString("Digite o modelo do carro: ");
        Modelo modeloCarro = new Modelo(modelo);

        cor = terminal.selecionarString("Digite a cor do carro: ");
        Cor corCarro = new Cor(cor);

        return new Veiculo(placa, corCarro, modeloCarro);
    }
}
