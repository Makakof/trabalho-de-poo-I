package operacoes;

import automovel.Cor;
import automovel.Modelo;
import automovel.Veiculo;
import dados.Repositorio;
import enums.TipoVeiculo;
import excecoes.ExcecaoAbstrata;
import excecoes.ExcecaoEntradaInvalida;
import ingressos.TicketEstacionamento;
import interfaces.InterfaceUsuario;
import utilitarios.StringUtils;

import java.util.InputMismatchException;
import java.util.List;

public class FuncionalidadesVeiculos {

    private final InterfaceUsuario interfaceUsuario;

    public FuncionalidadesVeiculos() {
        interfaceUsuario = Repositorio.getInstance().getUI();
    }

    public Veiculo consultarVeiculo(List<Veiculo> veiculos, String placa) {

        for (Veiculo veiculoAtual : veiculos) {
            if (veiculoAtual.getPlaca().equals(placa)) return veiculoAtual;
        }

        return null;
    }


    public Veiculo cadastraVeiculo() {
        String placa, modelo, cor, tipo;

        placa = interfaceUsuario.selecionarString("Digite a placa do veiculo: ");
        placa = StringUtils.formatarPadraoCapturaDeDados(placa);

        modelo = interfaceUsuario.selecionarString("Digite o modelo do veiculo: ");
        Modelo modeloCarro = new Modelo(modelo);

        cor = interfaceUsuario.selecionarString("Digite a cor do veiculo: ");
        Cor corCarro = new Cor(cor);

        tipo = interfaceUsuario.selecionarString("Digite o tipo (CARRO, MOTO, ONIBUS) do veiculo: ");
        tipo = StringUtils.formatarPadraoCapturaDeDados(tipo);

        return new Veiculo(placa, corCarro, modeloCarro, TipoVeiculo.valueOf(tipo));
    }

    public void excluiVeiculo(List<Veiculo> veiculos, String placa, List<TicketEstacionamento> tickets) {

        Veiculo veiculo = consultarVeiculo(veiculos, placa);

        if (veiculo == null) {
            throw new ExcecaoEntradaInvalida("A placa não existe","Funcionalidade Veiculos",2);
        }

        if (verificaSeOVeiculoTemTicket(tickets, veiculo)){
            throw new InputMismatchException("O veiculo possui ticket não pago!");
        }
        veiculos.remove(veiculo);
    }

    public static boolean verificaSeOVeiculoTemTicket(List<TicketEstacionamento> tickets, Veiculo veiculo) {
        for (TicketEstacionamento ticket : tickets) {

            String placaVeiculoTicket = ticket.getVeiculo().getPlaca();

            if (veiculo.getPlaca().equals(placaVeiculoTicket))
                return true;
        }
        return false;
    }

    public static void mostraVeiculos(List<Veiculo> veiculos){
        InterfaceUsuario interfaceUsuario = Repositorio.getInstance().getUI();

        interfaceUsuario.exibir("Veiculos: ");

        for(Veiculo veiculo : veiculos) {
            interfaceUsuario.exibir(veiculo.toString());
        }
    }
}
