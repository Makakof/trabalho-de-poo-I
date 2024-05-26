
import excecoes.EstacionamentoException;

import kitmenu.MenuGerenciaCliente;
import kitmenu.MenuGerenciaEstacionamento;
import kitmenu.MenuGerenciaVagas;
import kitmenu.MenuFaturamento;
import kitmenu.UI;



public class Estacionamento
{
    public static void main(String[] args)
    {

        UI terminal = UI.getInstance();
        MenuGerenciaCliente menuGerenciaCliente = new MenuGerenciaCliente();
        MenuGerenciaVagas menuGerenciaVagas = new MenuGerenciaVagas();
        MenuGerenciaEstacionamento menuGerenciaEstacionamento = new MenuGerenciaEstacionamento();
        MenuFaturamento menuFaturamento = new MenuFaturamento();
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
                        menuGerenciaCliente.gerenciarCliente();
                        break;
                    case 2:
                        menuGerenciaVagas.GerenciarVagas();
                        break;
                    case 3:
                        menuGerenciaEstacionamento.gerenciarEstacionamento();
                        break;
                    case 4:
                        break;
                    case 5:
                        menuFaturamento.realizarFaturamento();
                        break;
                }
            }while(opcaoPrincipal != 6);
        }
        catch (EstacionamentoException msg){
            System.out.println(msg);
        }
    }
}