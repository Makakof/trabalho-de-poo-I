import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import ingressos.TicketEstacionaBem;
import kitmenu.MenuGerenciaCliente;
import kitmenu.MenuGerenciaEstacionamento;
import kitmenu.MenuGerenciaVagas;
import kitmenu.UI;
import modelagem.Vaga;
import tarifacao.ValorHora;
import java.util.ArrayList;
import java.util.Timer;


public class Estacionamento
{
    public static void main(String[] args)
    {

        UI terminal = UI.getInstance();
        ArrayList<Cliente> clientes = new ArrayList<>();
        MenuGerenciaCliente menuGerenciaCliente = new MenuGerenciaCliente(terminal);
        ValorHora[] valorHoras = new ValorHora[7];
        ArrayList<Vaga> vagas = new ArrayList<>();
        MenuGerenciaVagas menuGerenciaVagas = new MenuGerenciaVagas(terminal);
        ArrayList<TicketEstacionaBem> tickets = new ArrayList<>();
        ArrayList<TicketEstacionaBem> logTickets = new ArrayList<>();
        MenuGerenciaEstacionamento menuGerenciaEstacionamento = new MenuGerenciaEstacionamento(terminal);

        byte opcaoPrincipal;

        terminal.menuPrincipal();
        opcaoPrincipal = terminal.selecionarByte("Digite a opção desejada: ");

        do {
            switch (opcaoPrincipal) {
                case 1:
                    menuGerenciaCliente.gerenciaCliente(clientes, tickets);
                    break;
                case 2:
                    menuGerenciaVagas.GerenciaVagas(vagas, tickets);
                    break;
                case 3:
                    menuGerenciaEstacionamento.gerenciaEstacionamento(clientes, tickets, logTickets, vagas, valorHoras);
                    break;

            }

            terminal.menuPrincipal();
            opcaoPrincipal = terminal.selecionarByte("Digite a opção desejada: ");
        } while (opcaoPrincipal != 6);
    }
}