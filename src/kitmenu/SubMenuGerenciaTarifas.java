package kitmenu;

import dados.Repositorio;
import excecoes.ExcecaoAbstrata;
import excecoes.ExcecaoMenu;
import interfaces.InterfaceUsuario;
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
                throw new ExcecaoMenu("Opção inválida de menu","Menu Gerencia Tarifas",2);
        }
    }
}

