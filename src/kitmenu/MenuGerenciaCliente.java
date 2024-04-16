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

        opcao = terminal.selecionarByte("Digite a opção desejada: ");

        do
        {
            switch (opcao) {
                case 1:
                    clientes.add(cadastrarCliente());
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
                    clientes.remove(consultarIndexCliente(clientes,documento));
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
                        opcaoSubmenu = terminal.selecionarByte();
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
            opcao = terminal.selecionarByte("Digite a opção desejada: ");
        }while(opcao != 7);
    }

    public Cliente cadastrarCliente() {

        String nome, documento;
        int qtdCarros;

        nome = terminal.selecionarString("Digite nome do cliente: ");
        documento = terminal.selecionarString("Digite o documento do cliente: ");
        Cliente cliente = new Cliente(nome, documento);

        qtdCarros = terminal.selecionarByte("O cliente possui quantos veículos: ");

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

    public int consultarIndexCliente(ArrayList<Cliente> clientes, String documento) {

        for (Cliente clienteAtual : clientes) {
            if (clienteAtual.getDocumento().equals(documento))
                return clientes.indexOf(clienteAtual);
        }

        return -1;
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

        placa = terminal.selecionarString("Digite a placa do carro: ");

        modelo = terminal.selecionarString("Digite o modelo do carro: ");
        Modelo modeloCarro = new Modelo(modelo);

        cor = terminal.selecionarString("Digite a cor do carro: ");
        Cor corCarro = new Cor(cor);

        return new Veiculo(placa, corCarro, modeloCarro);
    }
}
