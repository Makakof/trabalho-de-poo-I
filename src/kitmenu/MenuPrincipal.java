package kitmenu;

import dados.Repositorio;
import enums.OpcaoMenuPrincipal;
import excecoes.EstacionamentoException;
import operacoes.FuncionalidadesFaturamento;
import dados.Arquivos;

public class MenuPrincipal {
    public static void menuPrincial()
    {
        byte opcao;
        do
        {
            Repositorio.getInstance().getUI().exibirMenuPrincipal();
            opcao = Repositorio.getInstance().getUI().selecionarByte("Digite a opção desejada: ");

            switch (opcao)
            {
                case 1:
                    MenuGerenciaCliente.gerenciarCliente();
                    break;
                case 2:
                    MenuGerenciaVagas.gerenciarVagas();
                    break;
                case 3:
                    MenuGerenciaEstacionamento.gerenciarEstacionamento();
                    break;
                case 4:
                    Repositorio.getInstance().getUI().exibir("Sem funcionalidades no momento");
                    break;
                case 5:
                    FuncionalidadesFaturamento.faturamentoPorPeriodo();
                    break;
                case 6:
                    FuncionalidadesFaturamento.faturamentoPorVeiculo();
                    break;
                case 7: //voltar
                    Arquivos.salvarDadosDoSistema();

                    break;
                default:
                    throw new EstacionamentoException("Opção inválida de menu");
            }
        }while(opcao != OpcaoMenuPrincipal.SAIR.ordinal()+1);
    }
}
