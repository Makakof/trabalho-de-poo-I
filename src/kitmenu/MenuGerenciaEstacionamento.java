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
import operacoes.FuncionalidadesVaga;
import tarifacao.TarifaEstacionamento;
import tarifacao.TarifaMensalista;
import java.util.List;

public class MenuGerenciaEstacionamento {

    public static void gerenciarEstacionamento() {
        
        InterfaceUsuario interfaceUsuario = Terminal.getInstance();
        FuncionalidadesEstacionamento funcEstacionamento = Repositorio.getInstance().getFuncEstacionamento();
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

                    TicketEstacionamento ticket = funcEstacionamento.estacionar(clientes, tarifas, vagas);

                    if (ticket.getTarifa() instanceof TarifaMensalista && ticket.getDataFim() == null) {

                        ticket.encerrarTicket();
                        tickets.add(ticket);

                    } else
                        tickets.add(ticket);

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
