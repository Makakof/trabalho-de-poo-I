package interfaces;

import enums.*;

import java.time.LocalDate;
import java.util.Scanner;

import static utilitarios.DataUtils.formatarDataSemHora;

public class Terminal implements InterfaceUsuario {

    private static Terminal INSTANCE;

    private Terminal(){};

    // Singleton
    public static synchronized Terminal getInstance()
    {
        if(INSTANCE == null)
            INSTANCE = new Terminal();

        return INSTANCE;
    }

    public void exibirMenuPrincipal() {
        exibir(OpcaoMenuPrincipal.OPCAO1.getDescricao() +
                OpcaoMenuPrincipal.OPCAO2.getDescricao() +
                OpcaoMenuPrincipal.OPCAO3.getDescricao() +
                OpcaoMenuPrincipal.OPCAO4.getDescricao() +
                OpcaoMenuPrincipal.OPCAO5.getDescricao() +
                OpcaoMenuPrincipal.SAIR.getDescricao());
    }

    @Override
    public void exibirMenuGerenciaCliente() {
        exibir(OpcaoMenuGerenciaCliente.OPCAO1.getDescricao() +
                OpcaoMenuGerenciaCliente.OPCAO2.getDescricao() +
                OpcaoMenuGerenciaCliente.OPCAO3.getDescricao() +
                OpcaoMenuGerenciaCliente.OPCAO4.getDescricao() +
                OpcaoMenuGerenciaCliente.OPCAO5.getDescricao() +
                OpcaoMenuGerenciaCliente.OPCAO6.getDescricao() +
                OpcaoMenuGerenciaCliente.SAIR.getDescricao());

    }

    @Override
    public void exibirMenuGerenciaTarifas() {
        exibir(OpcaoMenuGerenciaTarifas.OPCAO1.getDescricao());

    }

    @Override
    public void exibirSubMenuGerenciaVeiculos() {
        exibir(OpcaoSubMenuGerenciaVeiculo.OPCAO1.getDescricao() +
                OpcaoSubMenuGerenciaVeiculo.OPCAO2.getDescricao() +
                OpcaoSubMenuGerenciaVeiculo.OPCAO3.getDescricao() +
                OpcaoSubMenuGerenciaVeiculo.OPCAO4.getDescricao() +
                OpcaoSubMenuGerenciaVeiculo.OPCAO5.getDescricao());

    }

    @Override
    public void exibirMenuGerenciaVagas() {
        exibir(OpcaoMenuGerenciaVagas.OPCAO1.getDescricao() +
                OpcaoMenuGerenciaVagas.OPCAO2.getDescricao() +
                OpcaoMenuGerenciaVagas.OPCAO3.getDescricao() +
                OpcaoMenuGerenciaVagas.OPCAO4.getDescricao() +
                OpcaoMenuGerenciaVagas.OPCAO5.getDescricao() +
                OpcaoMenuGerenciaVagas.SAIR.getDescricao());

    }

    @Override
    public void exibirMenuGerenciaEstacionamento() {
        exibir(OpcaoMenuGerenciaEstacionamento.OPCAO1.getDescricao() +
                OpcaoMenuGerenciaEstacionamento.OPCAO2.getDescricao() +
                OpcaoMenuGerenciaEstacionamento.OPCAO3.getDescricao() +
                OpcaoMenuGerenciaEstacionamento.OPCAO4.getDescricao() +
                OpcaoMenuGerenciaEstacionamento.SAIR.getDescricao());
    }

    @Override
    public void exibir(String msg) {
        System.out.println(msg);
    }

    @Override
    public byte selecionarByte(String msg) {
        Scanner scanner = new Scanner(System.in);
        byte opcao;

        System.out.print(msg);
        opcao = scanner.nextByte();

        return opcao;
    }

    @Override
    public int selecionarInt(String msg) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        System.out.print(msg);
        opcao = scanner.nextInt();

        return opcao;
    }

    @Override
    public double selecionarDouble(String msg) {
        Scanner scanner = new Scanner(System.in);
        double opcao;

        System.out.print(msg);
        opcao = scanner.nextDouble();

        return opcao;
    }

    @Override
    public byte selecionarByte() {
        Scanner scanner = new Scanner(System.in);
        byte opcao;

        opcao = scanner.nextByte();
        return opcao;
    }

    @Override
    public String selecionarString(String msg) {
        Scanner scanner = new Scanner(System.in);
        String string;

        System.out.print(msg);
        string = scanner.nextLine();

        return string;
    }

    @Override
    public String selecionarString() {
        Scanner scanner = new Scanner(System.in);
        String string;

        string = scanner.nextLine();
        return string;
    }

    public LocalDate selecionarData(String msg) {
        Scanner scanner = new Scanner(System.in);
        String string;

        System.out.print(msg);
        string = scanner.nextLine();

        return LocalDate.parse(string, formatarDataSemHora);
    }

}
