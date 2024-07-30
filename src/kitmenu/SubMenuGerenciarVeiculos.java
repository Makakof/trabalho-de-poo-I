package kitmenu;

import automovel.Cor;
import automovel.Veiculo;
import dados.Repositorio;
import excecoes.ExcecaoAbstrata;
import excecoes.ExcecaoEntradaInvalida;
import excecoes.ExcecaoMenu;
import ingressos.TicketEstacionamento;
import interfaces.InterfaceUsuario;
import operacoes.FuncionalidadesVeiculos;
import utilitarios.StringUtils;
import java.util.List;


public class SubMenuGerenciarVeiculos
{

    public static void editarVeiculos(byte opcao, List<Veiculo> veiculos, List<TicketEstacionamento> tickets)
    {
        InterfaceUsuario interfaceUsuario = Repositorio.getInstance().getUI();;
        FuncionalidadesVeiculos funcVeiculos = new FuncionalidadesVeiculos();
        Veiculo veiculo;
        String nomeCor, placa;

        switch (opcao) {

            case 1: //ver veiculos

                if (veiculos.isEmpty()) {
                    throw new ExcecaoEntradaInvalida("O cliente não possui veiculos cadastrados","Menu Gerencia Veiculos",2);
                }

                FuncionalidadesVeiculos.mostraVeiculos(veiculos);

                break;
            case 2: //adicionar veiculo

                veiculo = funcVeiculos.cadastraVeiculo();

                Veiculo veiculoExiste = funcVeiculos.consultarVeiculo(veiculos, veiculo.getPlaca());

                if (veiculoExiste != null)
                    throw new ExcecaoEntradaInvalida("Ja existe um veiculo cadastrado com a placa","Menu Gerencia Veiculos",2);

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
                throw new ExcecaoMenu("Opção inválida","Menu Gerencia Veiculos",1);
        }
    }
}
