import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import kitmenu.MenuGerenciaCliente;
import kitmenu.MenuGerenciaEstacionamento;
import kitmenu.MenuGerenciaVagas;
import kitmenu.UI;
import modelagem.Vaga;

import java.util.ArrayList;


public class Estacionamento {
    public static void main(String[] args) {
        UI terminal = UI.getInstance();

        ArrayList<Veiculo> veiculos = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        MenuGerenciaCliente menuGerenciaCliente = new MenuGerenciaCliente(terminal);

        ArrayList<Vaga> vagas = new ArrayList<>();
        MenuGerenciaVagas menuGerenciaVagas = new MenuGerenciaVagas(terminal);

        MenuGerenciaEstacionamento menuGerenciaEstacionamento = new MenuGerenciaEstacionamento(terminal);

        byte opcaoPrincipal;

        do {

            terminal.menuPrincipal();
            opcaoPrincipal = terminal.selecionarByte("Digite a opção desejada: ");

            switch (opcaoPrincipal) {
                case 1:

                    menuGerenciaCliente.gerenciaCliente(clientes, veiculos);
                    break;
                case 2:

                    menuGerenciaVagas.gerenciaVagas(vagas);
                    break;
                case 3:

                    menuGerenciaEstacionamento.gerenciaEstacionamento(vagas);
                    break;
            }

        } while (opcaoPrincipal != 6);
    }
}