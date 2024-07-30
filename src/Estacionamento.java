
import dados.Repositorio;

import excecoes.ExcecaoEntradaInvalida;
import excecoes.ExcecaoEstacionamento;
import excecoes.ExcecaoMenu;
import dados.Arquivos;
import kitmenu.MenuPrincipal;
import operacoes.FuncionalidadeInterface;
import utilitarios.UpdateUtils;

import java.time.ZoneId;

public class Estacionamento
{
    public static void main(String[] args)
    {
        ZoneId.of("America/Sao_Paulo");

        UpdateUtils.atualizarStatusDasVagas(Repositorio.getInstance().getTickets());

        FuncionalidadeInterface.EscolheInterface();

        Arquivos.inicializarDadosDoSistema();

        try 
        {
            MenuPrincipal.menuPrincial();
        }
        catch (ExcecaoMenu | ExcecaoEstacionamento | ExcecaoEntradaInvalida e){
            System.out.println(e.toString());
        }
    }
}