import cliente.estacionabem.Cliente;
import excecoes.EstacionamentoException;
import ingressos.TicketEstacionaBem;
import kitmenu.MenuGerenciaCliente;
import kitmenu.MenuGerenciaEstacionamento;
import kitmenu.MenuGerenciaVagas;
import kitmenu.MenuFaturamento;
import kitmenu.UI;
import modelagem.Vaga;
import tarifacao.TabelaPrecos;
import java.util.ArrayList;


public class Estacionamento
{
    public static void main(String[] args)
    {

        UI terminal = UI.getInstance();
        ArrayList<Cliente> clientes = new ArrayList<>();
        MenuGerenciaCliente menuGerenciaCliente = new MenuGerenciaCliente(terminal);
        TabelaPrecos[] valorHorasCarro = new TabelaPrecos[7];
        TabelaPrecos[] valorHorasMoto = new TabelaPrecos[7];
        ArrayList<Vaga> vagas = new ArrayList<>();
        MenuGerenciaVagas menuGerenciaVagas = new MenuGerenciaVagas(terminal);
        ArrayList<TicketEstacionaBem> tickets = new ArrayList<>();
        ArrayList<TicketEstacionaBem> logTickets = new ArrayList<>();
        MenuGerenciaEstacionamento menuGerenciaEstacionamento = new MenuGerenciaEstacionamento(terminal);
        MenuFaturamento menuFaturamento = new MenuFaturamento(terminal);

        byte opcaoPrincipal;

        try 
        {
            do 
            {
                terminal.menuPrincipal();
                opcaoPrincipal = terminal.selecionarByte("Digite a opção desejada: ");

                switch (opcaoPrincipal)
                {
                    case 1:
                        menuGerenciaCliente.gerenciarCliente(clientes, tickets);
                        break;
                    case 2:
                        menuGerenciaVagas.GerenciarVagas(vagas, tickets);
                        break;
                    case 3:
                        menuGerenciaEstacionamento.gerenciarEstacionamento(clientes, tickets, logTickets, vagas, valorHorasCarro,valorHorasMoto);
                        break;
                    case 4:
                        break;
                    case 5:
                        menuFaturamento.realizarFaturamento(logTickets);
                        break;
                }
            }while(opcaoPrincipal != 6);
        }
        catch (EstacionamentoException msg){
            System.out.println(msg);
        }
    }
}