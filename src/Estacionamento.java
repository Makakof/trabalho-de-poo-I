import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import kitmenu.MenuGerenciaCliente;
import kitmenu.UI;

import java.util.ArrayList;


public class Estacionamento
{
    public static void main(String[] args)
    {
        UI terminal = UI.getInstance();
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        MenuGerenciaCliente menuGerenciaCliente = new MenuGerenciaCliente(terminal);
        byte opcaoPrincipal;

        terminal.menuPrincipal();
        opcaoPrincipal = terminal.selecionarByte("Digite a opção desejada: ");

        String string;

        do
        {
            switch (opcaoPrincipal)
            {
                case 1:
                    terminal.menuGerenciaCliente();
                    menuGerenciaCliente.gerenciaCliente(clientes, veiculos);
                    break;
                case 2:
                    terminal.menuGerenciaVagas();
                    opcaoPrincipal = terminal.selecionarByte("Digite a opção desejada: ");
                    // menuEstacionaBem.menuGerenciaVagas(opcaoPrincipal);
                    break;
                case 3:
                    terminal.menuGerenciaEstacionamento();
                    opcaoPrincipal = terminal.selecionarByte("Digite a opção desejada: ");
                    //menuEstacionaBem.menuGerenciaCliente(opcaoPrincipal, clientes);
                    break;
            }

        }while(opcaoPrincipal != 6);
    }
}