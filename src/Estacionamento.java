
import automovel.Cor;
import automovel.Modelo;
import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import dados.Repositorio;
import enums.DiaDaSemana;
import enums.HoristaMensalista;
import enums.TipoVeiculo;
import excecoes.EstacionamentoException;

import kitmenu.MenuGerenciaCliente;
import kitmenu.MenuGerenciaEstacionamento;
import kitmenu.MenuGerenciaVagas;
import kitmenu.MenuFaturamento;
import interfaces.Terminal;
import modelagem.Vaga;
import tarifacao.TarifaEstacionamento;
import tarifacao.TarifaHorista;
import tarifacao.TarifaMensalista;
import utilitarios.UpdateUtils;

import java.util.ArrayList;


public class Estacionamento
{
    public static void main(String[] args)
    {

        Terminal terminal = Terminal.getInstance();
        MenuGerenciaCliente menuGerenciaCliente = new MenuGerenciaCliente();
        MenuGerenciaVagas menuGerenciaVagas = new MenuGerenciaVagas();
        MenuGerenciaEstacionamento menuGerenciaEstacionamento = new MenuGerenciaEstacionamento();
        MenuFaturamento menuFaturamento = new MenuFaturamento();
        byte opcaoPrincipal;

        UpdateUtils.atualizarStatusDasVagas(Repositorio.getInstance().getTickets());

        //entradas para testar
        Cliente cliente = new Cliente("rafael", "000");
        cliente.addVeiculo(new Veiculo("A0", new Cor("preto"), new Modelo("corsa"), TipoVeiculo.CARRO));
        Vaga vaga = new Vaga(10, "rio de janeiro", TipoVeiculo.CARRO);

        Repositorio.getInstance().getClientes().add(cliente);
        Repositorio.getInstance().getVagas().add(vaga);

        ArrayList<DiaDaSemana> dias = new ArrayList<>();
        dias.add(DiaDaSemana.DOMINGO);

        TarifaEstacionamento tarifa = new TarifaHorista(10, 12, dias, HoristaMensalista.HORISTA);
        Repositorio.getInstance().getTarifas().add(tarifa);

        tarifa = new TarifaMensalista(120, dias, HoristaMensalista.MENSALISTA);
        Repositorio.getInstance().getTarifas().add(tarifa);

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
            }while(opcaoPrincipal != 6);
        }
        catch (EstacionamentoException msg){
            System.out.println(msg);
        }
    }
}