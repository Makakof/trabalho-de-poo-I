
import dados.Repositorio;
import enums.OpcaoMenuPrincipal;
import excecoes.EstacionamentoException;

import kitmenu.MenuGerenciaCliente;
import kitmenu.MenuGerenciaEstacionamento;
import kitmenu.MenuGerenciaVagas;
import kitmenu.MenuFaturamento;
import interfaces.Terminal;
import utilitarios.UpdateUtils;



public class Estacionamento
{
    public static void main(String[] args)
    {

        Terminal terminal = Terminal.getInstance();
        MenuGerenciaCliente menuGerenciaCliente = new MenuGerenciaCliente();
        MenuGerenciaEstacionamento menuGerenciaEstacionamento = new MenuGerenciaEstacionamento();
        MenuFaturamento menuFaturamento = new MenuFaturamento();
        byte opcaoPrincipal;

        UpdateUtils.atualizarStatusDasVagas(Repositorio.getInstance().getTickets());


        try 
        {
            do 
            {
                terminal.exibirMenuPrincipal();
                opcaoPrincipal = terminal.selecionarByte("Digite a opção desejada: ");

                switch (opcaoPrincipal)
                {
                    case 1:
                        menuGerenciaCliente.gerenciarCliente();
                        break;
                    case 2:
                        MenuGerenciaVagas.GerenciarVagas();
                        break;
                    case 3:
                        menuGerenciaEstacionamento.gerenciarEstacionamento();
                        break;
                    case 4:
                        terminal.exibir("Sem funcionalidades no momento");
                        break;
                    case 5:
                        menuFaturamento.realizarFaturamento();
                        break;
                    case 6: //voltar
                        break;
                    default:
                        throw new EstacionamentoException("Opção inválida de menu");
                }
            }while(opcaoPrincipal != OpcaoMenuPrincipal.SAIR.ordinal()+1);
        }
        catch (EstacionamentoException msg){
            System.out.println(msg);
        }
    }
}