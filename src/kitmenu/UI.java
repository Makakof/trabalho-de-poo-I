package kitmenu;

import java.util.Scanner;

public class UI {
    public void menuPrincipal() {
        exibe("================================================");
        exibe("# 1 - Gerenciar clientes");
        exibe("# 2 - Gerenciar vagas");
        exibe("# 3 - Gerenciar estacionamento");
        exibe("# 4 - Cadastros gerais");
        exibe("# 5 - Consultar total faturado em um periodo");
        exibe("# 6 - Sair do Programa");
        exibe("================================================");

    }

    public void menuGerenciaCliente() {
        exibe("================================================");
        exibe("# 1 - Cadastrar");
        exibe("# 2 - Consultar por documento");
        exibe("# 3 - Excluir");
        exibe("# 4 - Editar");
        exibe("# 5 - Gerenciar veículos");
        exibe("# 6 - Listar todos os cadastros");
        exibe("# 7 - Voltar");
        exibe("================================================");

    }

    public void menuUpdate() {
        exibe("================================================");
        exibe("# 1 - Cadastrar");
        exibe("# 2 - Consultar por documento");
        exibe("================================================");

    }

    public void subMenuGerenciaVeiculos() {
        exibe("================================================");
        exibe("# 1 - Ver veículos do cliente");
        exibe("# 2 - Adicionar veículo");
        exibe("# 3 - Excluir veículo");
        exibe("# 4 - Atualizar veículo");
        exibe("================================================");

    }

    public void menuGerenciaVagas() {
        exibe("================================================");
        exibe("# 1 - Cadastrar");
        exibe("# 2 - Consultar por numero");
        exibe("# 3 - Excluir");
        exibe("# 4 - Editar");
        exibe("# 5 - Alterar disponibilidade");
        exibe("# 6 - Voltar");
        exibe("================================================");

    }

    public void menuGerenciaEstacionamento() {
        exibe("================================================");
        exibe("# 1 - Estacionar");
        exibe("# 2 - Retirar");
        exibe("# 3 - Listar todas as vagas disponíveis");
        exibe("# 4 - Gerenciar tarifas");
        exibe("# 5 - Voltar");
        exibe("================================================");
    }

    public void exibe(String msg) {
        System.out.println(msg);
    }

    public byte selecionaByte(String msg) {
        Scanner scanner = new Scanner(System.in);
        byte opcao;

        System.out.print(msg);
        opcao = scanner.nextByte();

        return opcao;
    }

    public byte selecionaByte() {
        Scanner scanner = new Scanner(System.in);
        byte opcao;

        opcao = scanner.nextByte();
        return opcao;
    }

    public String selecionaString(String msg) {
        Scanner scanner = new Scanner(System.in);
        String string;

        System.out.print(msg);
        string = scanner.nextLine();

        return string;
    }

    public String selecionaString() {
        Scanner scanner = new Scanner(System.in);
        String string;

        string = scanner.nextLine();
        return string;
    }
}
