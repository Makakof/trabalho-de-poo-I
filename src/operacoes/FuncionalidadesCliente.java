package operacoes;

import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import dados.Repositorio;
import excecoes.ExcecaoAbstrata;
import excecoes.ExcecaoEntradaInvalida;
import ingressos.TicketEstacionamento;
import interfaces.InterfaceUsuario;
import interfaces.Terminal;
import utilitarios.StringUtils;

import java.util.List;

public class FuncionalidadesCliente {

    private final InterfaceUsuario interfaceUsuario;

    public FuncionalidadesCliente() {
        interfaceUsuario = Repositorio.getInstance().getUI();
    }

    public static boolean verificaSeOClienteTemTicket(List<Veiculo> veiculos, List<TicketEstacionamento> tickets)
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
        documento = StringUtils.formatarPadraoCapturaDeDados(documento);
        Cliente cliente = new Cliente(nome, documento);

        qtdCarros = interfaceUsuario.selecionarByte("O cliente possui quantos veículos: ");

        if (qtdCarros > 0) {
            for (int i = 0; i < qtdCarros; i++) {

                interfaceUsuario.exibir("\nDados do veiculo #" + (i+1));
                veiculo = funcVeiculos.cadastraVeiculo();

                Veiculo veiculoExiste = funcVeiculos.consultarVeiculo(cliente.getVeiculos(), veiculo.getPlaca());

                if (veiculoExiste != null)
                    throw new ExcecaoEntradaInvalida("Ja existe um veiculo cadastrado com a placa","Funcionalidade Cliente",2);

                cliente.addVeiculo(veiculo);
            }
        }
        return cliente;
    }

    public Cliente consultaCliente(List<Cliente> clientes, String documento) {

        for (Cliente clienteAtual : clientes) {
            if (clienteAtual.getDocumento().equals(documento)) return clienteAtual;
        }

        return null;
    }

    public void listarCadastros(List<Cliente> clientes){
        for (Cliente pessoa : clientes) {
            interfaceUsuario.exibir(pessoa.toString());
            FuncionalidadesVeiculos.mostraVeiculos(pessoa.getVeiculos());
        }
    }

    public void editarCliente(List<Cliente> clientes, String documento){

        FuncionalidadesCliente funcCliente = new FuncionalidadesCliente();

        Cliente cliente = funcCliente.consultaCliente(clientes, documento);

        if(cliente == null)
            throw new ExcecaoEntradaInvalida("Cliente não cadastrado","Funcionalidade Cliente",2);

        interfaceUsuario.exibir("Nome antigo: " + cliente.getNome());
        String novoNome = interfaceUsuario.selecionarString("Novo nome: ");
        cliente.setNome(novoNome);

        interfaceUsuario.exibir("Documento antigo: " + cliente.getDocumento());
        String novoDocumento = interfaceUsuario.selecionarString("Novo documento: ");
        cliente.setDocumento(novoDocumento);

    }
}
