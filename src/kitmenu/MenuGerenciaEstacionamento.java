package kitmenu;

import cliente.estacionabem.Cliente;
import dados.Repositorio;
import enums.OpcaoMenuGerenciaEstacionamento;
import excecoes.ExcecaoAbstrata;
import excecoes.ExcecaoEntradaInvalida;
import excecoes.ExcecaoEstacionamento;
import excecoes.ExcecaoMenu;
import ingressos.TicketEstacionamento;
import interfaces.InterfaceUsuario;
import modelagem.Vaga;
import operacoes.FuncionalidadesEstacionamento;
import operacoes.FuncionalidadesTicket;
import operacoes.FuncionalidadesVaga;
import tarifacao.TarifaEstacionamento;

import java.util.List;

public class MenuGerenciaEstacionamento {

    public static void gerenciarEstacionamento() {
        
        InterfaceUsuario interfaceUsuario = Repositorio.getInstance().getUI();;
        FuncionalidadesEstacionamento funcEstacionamento = new FuncionalidadesEstacionamento();
        List<TicketEstacionamento> tickets = Repositorio.getInstance().getTickets();
        List<Cliente> clientes = Repositorio.getInstance().getClientes();
        List<Vaga> vagas = Repositorio.getInstance().getVagas();
        List<TarifaEstacionamento> tarifas = Repositorio.getInstance().getTarifas();

        byte opcao;

        do {

            interfaceUsuario.exibirMenuGerenciaEstacionamento();
            opcao = interfaceUsuario.selecionarByte("Digite a opção desejada: ");

            switch (opcao) {
                case 1: // estacionar

                    try {
                        TicketEstacionamento ticket = funcEstacionamento.estacionar(clientes, tarifas, vagas);
                        FuncionalidadesTicket.verificaTicket(ticket, tickets);
                    }
                    catch (ExcecaoEntradaInvalida | ExcecaoEstacionamento e)
                    {
                        interfaceUsuario.exibir(e.toString());
                    }

                    break;
                case 2: // retirar

                    funcEstacionamento.retirar(tickets);
                    break;
                case 3: // listar vagas

                    if (vagas.isEmpty()) {
                        throw new ExcecaoEstacionamento("O estacionamento não possui vagas cadastradas","Menu Gerencia Estacionamento",3);
                    }
                    FuncionalidadesVaga.listarVagas(vagas);
                    break;
                case 4: // gerenciar tarifas
                    SubMenuGerenciaTarifas.gerenciarTarifas();
                    break;
                case 5: //voltar
                    break;
                default:
                    throw new ExcecaoMenu("Opção inválida","Menu Gerencia Estacionamento",1);
            }
        } while (opcao != OpcaoMenuGerenciaEstacionamento.SAIR.ordinal()+1);
    }
}
