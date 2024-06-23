package kitmenu;

import dados.Repositorio;
import excecoes.EstacionamentoException;
import interfaces.InterfaceUsuario;
import interfaces.Terminal;
import operacoes.FuncionalidadesTarifa;
import tarifacao.TarifaEstacionamento;
import java.util.List;

public class SubMenuGerenciaTarifas
{
    public static void gerenciarTarifas()
    {
        FuncionalidadesTarifa funcTarifa = new FuncionalidadesTarifa();
        InterfaceUsuario interfaceUsuario = Repositorio.getInstance().getUI();;
        byte opcao;
        List<TarifaEstacionamento> tarifas = Repositorio.getInstance().getTarifas();
        TarifaEstacionamento tarifa;

        interfaceUsuario.exibirMenuGerenciaTarifas();
        opcao = interfaceUsuario.selecionarByte("Digite a opção desejada: ");

        switch (opcao)
        {
            case 1:

                tarifa = funcTarifa.cadastrarTarifa();
                tarifas.add(tarifa);

                break;
            default:
                throw new EstacionamentoException("Opção inválida de menu");
        }
    }
}

