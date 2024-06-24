package kitmenu;

import cliente.estacionabem.Cliente;
import dados.Repositorio;
import enums.OpcaoMenuGerenciaEstacionamento;
import excecoes.EstacionamentoException;
import ingressos.TicketEstacionamento;
import interfaces.InterfaceUsuario;
import interfaces.Terminal;
import modelagem.Vaga;
import operacoes.FuncionalidadesEstacionamento;
import operacoes.FuncionalidadesTicket;
import operacoes.FuncionalidadesVaga;
import tarifacao.TarifaEstacionamento;
import tarifacao.TarifaMensalista;
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
                    catch (EstacionamentoException msg)
                    {
                        System.out.println(msg);
                        MenuGerenciaEstacionamento.gerenciarEstacionamento();
                    }

                    break;
                case 2: // retirar

                    funcEstacionamento.retirar(tickets);
                    break;
                case 3: // listar vagas

                    if (vagas.isEmpty()) {
                        throw new EstacionamentoException("O estacionamento não possui vagas cadastradas");
                    }
                    FuncionalidadesVaga.listarVagas(vagas);
                    break;
                case 4: // gerenciar tarifas
                    SubMenuGerenciaTarifas.gerenciarTarifas();
                    break;
                case 5: //voltar
                    break;
                default:
                    throw new EstacionamentoException("Opção inválida de menu");
            }
        } while (opcao != OpcaoMenuGerenciaEstacionamento.SAIR.ordinal()+1);
    }
}
