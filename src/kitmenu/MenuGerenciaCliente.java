package kitmenu;

import automovel.Cor;
import automovel.Modelo;
import automovel.Veiculo;
import cliente.estacionabem.Cliente;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class MenuGerenciaCliente {

    private UI terminal;

    public MenuGerenciaCliente(UI terminal) {
        this.terminal = terminal;
    }

    public void gerenciaCliente(ArrayList<Cliente> clientes, ArrayList<Veiculo> veiculos) {
        Cliente cliente;
        String documento;
        byte opcao;

        opcao = terminal.selecionaByte("Digite a opção desejada: ");

        do
        {
            switch (opcao) {
                case 1:

                    clientes.add(cadastraCliente());

                    break;
                case 2:

                    documento = terminal.selecionaString("Digite o documento do cliente que deseja pesquisar: ");
                    cliente = consultaCliente(clientes, documento);
                    if (cliente != null)
                        terminal.exibe(cliente.toString());
                    else
                        terminal.exibe("Cliente não cadastrado");

                    break;
                case 3:

                    documento = terminal.selecionaString("Digite o documento do cliente que deseja excluir: ");
//                    clientes.removeIf(x -> (x.getDocumento().equals(documento)));
                    /*
                    TODO ARRUMAR ESSE LAMBDA
                     */

                    break;
                case 4:

                    String novoNome, novoDocumento;
                    documento = terminal.selecionaString("\nDigite o documento do cliente que deseja editar: ");
                    cliente = consultaCliente(clientes, documento);
                    if (cliente != null) {

                        terminal.exibe("Nome antigo: " + cliente.getNome());
                        novoNome = terminal.selecionaString("Novo nome: ");
                        cliente.alteraNome(novoNome);

                        terminal.exibe("Documento antigo: " + cliente.getDocumento());
                        novoDocumento = terminal.selecionaString("Novo documento: ");
                        cliente.alteraDocumento(novoDocumento);

                    } else
                        terminal.exibe("Cliente não cadastrado");

                    break;
                case 5:
                    byte opcaoSubmenu;

                    documento = terminal.selecionaString("\nDigite o documento do cliente que deseja ver os veículos: ");

                    Cliente clienteAtual = consultaCliente(clientes, documento);
                    if (clienteAtual != null) {
                        terminal.subMenuGerenciaVeiculos();
                        opcaoSubmenu = terminal.selecionaByte();
    //                    clienteAtual.addVeiculo(subMenuEstacionaBem(opcaoSubmenu, veiculos)); // oq isso faz ?
                    } else {
                        throw new InvalidParameterException("Documento " + documento + " não existe!");
                    }

                    break;
                case 6:
                    clientes.forEach(System.out::println);
                    break;

            }
            terminal.menuGerenciaCliente();
            opcao = terminal.selecionaByte("Digite a opção desejada: ");
        }while(opcao != 7);
    }

    public Cliente cadastraCliente() {

        String nome, documento;
        int qtdCarros;

        nome = terminal.selecionaString("Digite nome do cliente: ");
        documento = terminal.selecionaString("Digite o documento do cliente: ");
        Cliente cliente = new Cliente(nome, documento);

        qtdCarros = terminal.selecionaByte("O cliente possui quantos veículos: ");

        if (qtdCarros > 0) {
            for (int i = 0; i < qtdCarros; i++) {

                cliente.addVeiculo(cadastraVeiculo());
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


    public ArrayList<Veiculo> subMenuEstacionaBem(byte opcao, ArrayList<Veiculo> veiculos) {
        switch (opcao) {
            case 1:
                for (Veiculo veiculo : veiculos)
                    System.out.println(veiculo);
                break;
            case 2:
                veiculos.add(cadastraVeiculo());
                break;
            case 3:
                System.out.print("Digite a placa do veiculo que vai ser excluido: ");
                break;
        }

        return veiculos;
    }

    public Veiculo cadastraVeiculo() {

        String placa, modelo, cor;

        placa = terminal.selecionaString("Digite a placa do carro: ");

        modelo = terminal.selecionaString("Digite o modelo do carro: ");
        Modelo modeloCarro = new Modelo(modelo);

        cor = terminal.selecionaString("Digite a cor do carro: ");
        Cor corCarro = new Cor(cor);

        return new Veiculo(placa, corCarro, modeloCarro);
    }
}
