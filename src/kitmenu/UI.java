package kitmenu;

import enums.TipoVeiculo;
import enums.VagaStatus;

import java.util.Scanner;

public class UI {

    private static UI INSTANCE;

    private UI(){};

    // Singleton
    public static synchronized UI getInstance()
    {
        if(INSTANCE == null)
            INSTANCE = new UI();

        return INSTANCE;
    }

    public void menuPrincipal() {
        exibir("================================================");
        exibir("# 1 - Gerenciar clientes");
        exibir("# 2 - Gerenciar vagas");
        exibir("# 3 - Gerenciar estacionamento");
        exibir("# 4 - Cadastros gerais");
        exibir("# 5 - Consultar total faturado em um periodo");
        exibir("# 6 - Sair do Programa");
        exibir("================================================");
    }

    public void menuGerenciaCliente() {
        exibir("================================================");
        exibir("# 1 - Cadastrar");
        exibir("# 2 - Consultar por documento");
        exibir("# 3 - Excluir");
        exibir("# 4 - Editar");
        exibir("# 5 - Gerenciar veículos");
        exibir("# 6 - Listar todos os cadastros");
        exibir("# 7 - Voltar");
        exibir("================================================");

    }

    public void menuGerenciaTarifas() {
        exibir("================================================");
        exibir("# 1 - Cadastrar");
        exibir("# 2 - Editar");
        exibir("================================================");

    }

    public void subMenuGerenciaVeiculos() {
        exibir("================================================");
        exibir("# 1 - Ver veículos do cliente");
        exibir("# 2 - Adicionar veículo");
        exibir("# 3 - Excluir veículo");
        exibir("# 4 - Atualizar cor do veículo");
        exibir("================================================");

    }

    public void menuGerenciaVagas() {
        exibir("================================================");
        exibir("# 1 - Cadastrar");
        exibir("# 2 - Consultar por numero");
        exibir("# 3 - Excluir");
        exibir("# 4 - Editar");
        exibir("# 5 - Alterar disponibilidade");
        exibir("# 6 - Voltar");
        exibir("================================================");

    }

    public void menuGerenciaEstacionamento() {
        exibir("================================================");
        exibir("# 1 - Estacionar");
        exibir("# 2 - Retirar");
        exibir("# 3 - Listar todas as vagas disponíveis");
        exibir("# 4 - Gerenciar tarifas");
        exibir("# 5 - Voltar");
        exibir("================================================");
    }

    public void exibir(String msg) {
        System.out.println(msg);
    }

    public byte selecionarByte(String msg) {
        Scanner scanner = new Scanner(System.in);
        byte opcao;

        System.out.print(msg);
        opcao = scanner.nextByte();

        return opcao;
    }

    public int selecionarInt(String msg) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        System.out.print(msg);
        opcao = scanner.nextInt();

        return opcao;
    }

    public double selecionarDouble(String msg) {
        Scanner scanner = new Scanner(System.in);
        double opcao;

        System.out.print(msg);
        opcao = scanner.nextDouble();

        return opcao;
    }

    public byte selecionarByte() {
        Scanner scanner = new Scanner(System.in);
        byte opcao;

        opcao = scanner.nextByte();
        return opcao;
    }

    public String selecionarString(String msg) {
        Scanner scanner = new Scanner(System.in);
        String string;

        System.out.print(msg);
        string = scanner.nextLine();

        return string;
    }

    public String selecionarString() {
        Scanner scanner = new Scanner(System.in);
        String string;

        string = scanner.nextLine();
        return string;
    }

}
