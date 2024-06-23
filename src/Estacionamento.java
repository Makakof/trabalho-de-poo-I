
import cliente.estacionabem.Cliente;
import dados.Input;
import dados.Repositorio;
import excecoes.EstacionamentoException;

import utilitarios.Arquivos;

import kitmenu.MenuPrincipal;
import operacoes.FuncionalidadeInterface;

import utilitarios.UpdateUtils;
import java.util.ArrayList;
import java.util.List;



public class Estacionamento
{
    public static void main(String[] args)
    {
        UpdateUtils.atualizarStatusDasVagas(Repositorio.getInstance().getTickets());
        FuncionalidadeInterface.EscolheInterface();

        Input.inputClientes(Repositorio.getInstance().getClientes());

        Arquivos.escreverNoArquivo(Repositorio.getInstance().getClientes(), "Veiculos.dat");

        List<Cliente> clientes = new ArrayList<>();
        Arquivos.lerDoArquivo(clientes, "Veiculos.dat");

        try 
        {
            MenuPrincipal.menuPrincial();
        }
        catch (EstacionamentoException msg){
            System.out.println(msg);
        }
    }
}