
import dados.Repositorio;
import excecoes.EstacionamentoException;
import kitmenu.MenuPrincipal;
import operacoes.FuncionalidadeInterface;
import utilitarios.UpdateUtils;




public class Estacionamento
{
    public static void main(String[] args)
    {
        UpdateUtils.atualizarStatusDasVagas(Repositorio.getInstance().getTickets());
        FuncionalidadeInterface.EscolheInterface();

        try 
        {
            MenuPrincipal.menuPrincial();
        }
        catch (EstacionamentoException msg){
            System.out.println(msg);
        }
    }
}