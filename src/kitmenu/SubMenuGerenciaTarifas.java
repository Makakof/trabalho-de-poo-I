package kitmenu;

import dados.Repositorio;
import enums.DiaDaSemana;
import excecoes.EstacionamentoException;
import interfaces.InterfaceUsuario;
import interfaces.Terminal;
import operacoes.FuncionalidadesTarifa;
import tarifacao.TarifaEstacionamento;
import tarifacao.TarifaHorista;
import tarifacao.TarifaMensalista;
import utilitarios.StringUtils;

import java.util.ArrayList;

public class SubMenuGerenciaTarifas
{
    public static void gerenciarTarifas()
    {
        FuncionalidadesTarifa funcTarifa = Repositorio.getInstance().getFuncTarifas();
        InterfaceUsuario interfaceUsuario = Terminal.getInstance();
        byte opcao;
        ArrayList<TarifaEstacionamento> tarifas = Repositorio.getInstance().getTarifas();
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

