package kitmenu;

import automovel.Cor;
import automovel.Modelo;
import automovel.Veiculo;
import enums.TipoVeiculo;
import excecoes.EstacionamentoException;
import ingressos.TicketEstacionamento;
import interfaces.InterfaceUsuario;
import interfaces.Terminal;
import operacoes.FuncionalidadesTarifa;
import operacoes.FuncionalidadesVeiculos;
import utilitarios.StringUtils;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class SubMenuGerenciarVeiculos
{

    public static void editarVeiculos(byte opcao, ArrayList<Veiculo> veiculos, ArrayList<TicketEstacionamento> tickets)
    {
        InterfaceUsuario interfaceUsuario = Terminal.getInstance();
        FuncionalidadesVeiculos funcVeiculos = new FuncionalidadesVeiculos();
        Veiculo veiculo;
        String nomeCor, placa;

        switch (opcao) {

            case 1: //ver veiculos

                if (veiculos.isEmpty()) {
                    throw new EstacionamentoException("O cliente não possui veiculos cadastrados");
                }
                for (Veiculo veiculoAtual : veiculos)
                    interfaceUsuario.exibir(veiculoAtual.toString());

                break;
            case 2: //adicionar veiculo

                veiculo = funcVeiculos.cadastraVeiculo();

                Veiculo veiculoExiste = funcVeiculos.consultarVeiculo(veiculos, veiculo.getPlaca());

                if (veiculoExiste != null)
                    throw new EstacionamentoException("Ja existe um veiculo cadastrado com a placa: " + veiculo.getPlaca());

                veiculos.add(veiculo);
                interfaceUsuario.exibir("Veiculo cadastrado com sucesso!");

                break;
            case 3: //excluir veiculo

                placa = interfaceUsuario.selecionarString("Digite a placa do veiculo que vai ser excluido: ");
                funcVeiculos.excluiVeiculo(veiculos, StringUtils.formatarPadraoCapturaDeDados(placa), tickets);
                interfaceUsuario.exibir("Veiculo excluido com sucesso!");

                break;
            case 4: //atualizar cor

                placa = interfaceUsuario.selecionarString("Digite a placa do veiculo que vai ser alterada a cor: ");
                placa = StringUtils.formatarPadraoCapturaDeDados(placa);
                veiculo = funcVeiculos.consultarVeiculo(veiculos, placa);
                if (veiculo != null) {
                    nomeCor = interfaceUsuario.selecionarString("Digite a nova cor: ");
                    Cor cor = new Cor(nomeCor);
                    veiculo.setCor(cor);
                    interfaceUsuario.exibir("Cor alterada com sucesso");
                }

                break;
            case 5: //voltar
                break;
            default:
                throw new EstacionamentoException("Opção inválida de menu");
        }
    }
}
