package kitmenu;

import automovel.Cor;
import automovel.Modelo;
import automovel.Veiculo;
import enums.TipoVeiculo;
import excecoes.EstacionamentoException;
import ingressos.TicketEstacionamento;
import utilitarios.StringUtil;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class SubMenuGerenciarVeiculos
{
    private final UI terminal;

    SubMenuGerenciarVeiculos()
    {
        this.terminal = UI.getInstance();
    }

    public void editarVeiculos(byte opcao, ArrayList<Veiculo> veiculos, ArrayList<TicketEstacionamento> tickets)
    {
        Veiculo veiculo;
        String nomeCor, placa;

        switch (opcao) {

            case 1: //ver veiculos

                if (veiculos.isEmpty()) {
                    throw new EstacionamentoException("O cliente não possui veiculos cadastrados");
                }
                for (Veiculo veiculoAtual : veiculos)
                    terminal.exibir(veiculoAtual.toString());

                break;
            case 2: //adicionar veiculo

                veiculo = cadastraVeiculo();

                Veiculo veiculoExiste = consultarVeiculo(veiculos, veiculo.getPlaca());

                if (veiculoExiste != null)
                    throw new EstacionamentoException("Ja existe um veiculo cadastrado com a placa: " + veiculo.getPlaca());

                veiculos.add(veiculo);
                terminal.exibir("Veiculo cadastrado com sucesso!");

                break;
            case 3: //excluir veiculo

                placa = terminal.selecionarString("Digite a placa do veiculo que vai ser excluido: ");
                excluiVeiculo(veiculos, StringUtil.formatarPlaca(placa), tickets);
                terminal.exibir("Veiculo excluido com sucesso!");

                break;
            case 4: //atualizar cor

                placa = terminal.selecionarString("Digite a placa do veiculo que vai ser alterada a cor: ");
                placa = StringUtil.formatarPlaca(placa);
                veiculo = consultarVeiculo(veiculos, placa);
                if (veiculo != null) {
                    nomeCor = terminal.selecionarString("Digite a nova cor: ");
                    Cor cor = new Cor(nomeCor);
                    veiculo.setCor(cor);
                    terminal.exibir("Cor alterada com sucesso");
                }

                break;
            case 5: //voltar
                break;
            default:
                throw new EstacionamentoException("Opção inválida de menu");
        }
    }

    public Veiculo consultarVeiculo(ArrayList<Veiculo> veiculos, String placa) {
        for (Veiculo veiculoAtual : veiculos) {
            if (veiculoAtual.getPlaca().equals(placa)) return veiculoAtual;
        }

        return null;
    }


    public Veiculo cadastraVeiculo() {
        String placa, modelo, cor, tipo;

        placa = terminal.selecionarString("Digite a placa do veiculo: ");
        placa = StringUtil.formatarPlaca(placa);

        modelo = terminal.selecionarString("Digite o modelo do veiculo: ");
        Modelo modeloCarro = new Modelo(modelo);

        cor = terminal.selecionarString("Digite a cor do veiculo: ");
        Cor corCarro = new Cor(cor);

        tipo = terminal.selecionarString("Digite o tipo (CARRO, MOTO, ONIBUS) do veiculo: ");
        tipo = StringUtil.formatarTipo(tipo);

        return new Veiculo(placa, corCarro, modeloCarro, TipoVeiculo.valueOf(tipo));
    }

    public void excluiVeiculo(ArrayList<Veiculo> veiculos, String placa, ArrayList<TicketEstacionamento> tickets) {

        Veiculo veiculo = consultarVeiculo(veiculos, placa);

        if (veiculo == null) {
            throw new EstacionamentoException("A placa: " + placa + " não existe");
        }

        if (verificaSeOVeiculoTemTicket(tickets, veiculo)){
            throw new InputMismatchException("O veiculo possui ticket não pago!");
        }
        veiculos.remove(veiculo);
    }

    public boolean verificaSeOVeiculoTemTicket(ArrayList<TicketEstacionamento> tickets, Veiculo veiculo) {
        for (TicketEstacionamento ticket : tickets) {

            String placaVeiculoTicket = ticket.getVeiculo().getPlaca();

            if (veiculo.getPlaca().equals(placaVeiculoTicket))
                return true;
        }
        return false;
    }
}
