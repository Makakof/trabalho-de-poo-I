package utilitarios;

import enums.*;

public class Mensagens
{
    public static String MENU_PRINCIPAL = OpcaoMenuPrincipal.OPCAO1.getDescricao() +
                OpcaoMenuPrincipal.OPCAO2.getDescricao() +
                        OpcaoMenuPrincipal.OPCAO3.getDescricao() +
                        OpcaoMenuPrincipal.OPCAO4.getDescricao() +
                        OpcaoMenuPrincipal.OPCAO5.getDescricao() +
                        OpcaoMenuPrincipal.OPCAO6.getDescricao() +
                        OpcaoMenuPrincipal.SAIR.getDescricao();

    public static String MENU_GERENCIA_CLIENTE = OpcaoMenuGerenciaCliente.OPCAO1.getDescricao() +
            OpcaoMenuGerenciaCliente.OPCAO2.getDescricao() +
            OpcaoMenuGerenciaCliente.OPCAO3.getDescricao() +
            OpcaoMenuGerenciaCliente.OPCAO4.getDescricao() +
            OpcaoMenuGerenciaCliente.OPCAO5.getDescricao() +
            OpcaoMenuGerenciaCliente.OPCAO6.getDescricao() +
            OpcaoMenuGerenciaCliente.SAIR.getDescricao();

    public static String GERENCIA_TARIFAS = OpcaoMenuGerenciaTarifas.OPCAO1.getDescricao();

    public static String SUB_MENU_GERENCIA_VEICULO = OpcaoSubMenuGerenciaVeiculo.OPCAO1.getDescricao() +
            OpcaoSubMenuGerenciaVeiculo.OPCAO2.getDescricao() +
            OpcaoSubMenuGerenciaVeiculo.OPCAO3.getDescricao() +
            OpcaoSubMenuGerenciaVeiculo.OPCAO4.getDescricao() +
            OpcaoSubMenuGerenciaVeiculo.OPCAO5.getDescricao();

    public static String MENU_GERENCIA_VAGAS = OpcaoMenuGerenciaVagas.OPCAO1.getDescricao() +
            OpcaoMenuGerenciaVagas.OPCAO2.getDescricao() +
            OpcaoMenuGerenciaVagas.OPCAO3.getDescricao() +
            OpcaoMenuGerenciaVagas.OPCAO4.getDescricao() +
            OpcaoMenuGerenciaVagas.OPCAO5.getDescricao() +
            OpcaoMenuGerenciaVagas.SAIR.getDescricao();

    public static String MENU_GERENCIA_ESTACIONAMENTO = OpcaoMenuGerenciaEstacionamento.OPCAO1.getDescricao() +
            OpcaoMenuGerenciaEstacionamento.OPCAO2.getDescricao() +
            OpcaoMenuGerenciaEstacionamento.OPCAO3.getDescricao() +
            OpcaoMenuGerenciaEstacionamento.OPCAO4.getDescricao() +
            OpcaoMenuGerenciaEstacionamento.SAIR.getDescricao();
}
