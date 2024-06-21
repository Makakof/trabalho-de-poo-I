package interfaces;

import utilitarios.Mensagens;

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

    @Override
    public void menuPrincipal() {
        exibir(Mensagens.MENU_PRINCIPAL);
    }

    @Override
    public void menuGerenciaCliente() {
        exibir(Mensagens.MENU_GERENCIA_CLIENTE);

    }

    @Override
    public void menuGerenciaTarifas() {
        exibir(Mensagens.GERENCIA_TARIFAS);

    }

    @Override
    public void subMenuGerenciaVeiculos() {
        exibir(Mensagens.SUB_MENU_GERENCIA_VEICULO);

    }

    @Override
    public void menuGerenciaVagas() {
        exibir(Mensagens.MENU_GERENCIA_VAGAS);

    }

    @Override
    public void menuGerenciaEstacionamento() {
        exibir(Mensagens.MENU_GERENCIA_ESTACIONAMENTO);
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
