package operacoes;

import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import excecoes.EstacionamentoException;
import ingressos.TicketEstacionamento;
import interfaces.InterfaceUsuario;
import interfaces.Terminal;

import java.util.ArrayList;

public class FuncionalidadesCliente {

    private final InterfaceUsuario interfaceUsuario;

    public FuncionalidadesCliente() {
        interfaceUsuario = Terminal.getInstance();
    }

    public static boolean verificaSeOClienteTemTicket(ArrayList<Veiculo> veiculos, ArrayList<TicketEstacionamento> tickets)
    {
        for(Veiculo veiculo : veiculos)
            if(FuncionalidadesVeiculos.verificaSeOVeiculoTemTicket(tickets, veiculo))
                return true;

        return false;
    }

    public Cliente cadastrarCliente() {
        FuncionalidadesVeiculos funcVeiculos = new FuncionalidadesVeiculos();
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
                veiculo = funcVeiculos.cadastraVeiculo();

                Veiculo veiculoExiste = funcVeiculos.consultarVeiculo(cliente.getVeiculos(), veiculo.getPlaca());

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

    public void listarCadastros(ArrayList<Cliente> clientes){
        for (Cliente pessoa : clientes) {
            interfaceUsuario.exibir(pessoa.toString());
            pessoa.mostraVeiculos();
        }
    }

    public void editarCliente(ArrayList<Cliente> clientes, String documento){

        FuncionalidadesCliente funcCliente = new FuncionalidadesCliente();

        Cliente cliente = funcCliente.consultaCliente(clientes, documento);
        if (cliente != null) {

            interfaceUsuario.exibir("Nome antigo: " + cliente.getNome());
            String novoNome = interfaceUsuario.selecionarString("Novo nome: ");
            cliente.setNome(novoNome);

            interfaceUsuario.exibir("Documento antigo: " + cliente.getDocumento());
            String novoDocumento = interfaceUsuario.selecionarString("Novo documento: ");
            cliente.setDocumento(novoDocumento);

        } else interfaceUsuario.exibir("Cliente não cadastrado");
    }
}
