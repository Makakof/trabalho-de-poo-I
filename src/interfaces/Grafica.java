package interfaces;

import utilitarios.Mensagens;

import javax.swing.*;
import java.time.LocalDate;


public class Grafica implements InterfaceUsuario{
    private static Grafica INSTANCE;

    private Grafica(){};

    // Singleton
    public static synchronized Grafica getInstance()
    {
        if(INSTANCE == null)
            INSTANCE = new Grafica();

        return INSTANCE;
    }
    @Override
    public void exibirMenuPrincipal() {
        exibir(Mensagens.MENU_PRINCIPAL);
    }

    @Override
    public void exibirMenuGerenciaCliente() {
        exibir(Mensagens.MENU_GERENCIA_CLIENTE);
    }

    @Override
    public void exibirMenuGerenciaTarifas() {
        exibir(Mensagens.GERENCIA_TARIFAS);
    }

    @Override
    public void exibirSubMenuGerenciaVeiculos() {
        exibir(Mensagens.SUB_MENU_GERENCIA_VEICULO);
    }

    @Override
    public void exibirMenuGerenciaVagas() {
        exibir(Mensagens.MENU_GERENCIA_VAGAS);
    }

    @Override
    public void exibirMenuGerenciaEstacionamento() {
        exibir(Mensagens.MENU_GERENCIA_ESTACIONAMENTO);
    }

    @Override
    public void exibir(String msg) {
        JOptionPane.showMessageDialog(null,msg);
    }

    @Override
    public byte selecionarByte(String msg) {
        String string;
        byte opcao;

        string = JOptionPane.showInputDialog(null, msg);
        opcao = Byte.parseByte(string);

        return opcao;
    }

    @Override
    public int selecionarInt(String msg) {
        String string;
        int opcao;

        string = JOptionPane.showInputDialog(null, msg);
        opcao = Integer.parseInt(string);

        return opcao;
    }

    @Override
    public double selecionarDouble(String msg) {
        String string;
        double opcao;

        string = JOptionPane.showInputDialog(null, msg);
        opcao = Double.parseDouble(string);

        return opcao;
    }

    @Override
    public byte selecionarByte() {
        String string;
        byte opcao;

        string = JOptionPane.showInputDialog(null);
        opcao = Byte.parseByte(string);

        return opcao;
    }

    @Override
    public String selecionarString(String msg) {
        String string;

        string = JOptionPane.showInputDialog(null, msg);

        return string;
    }

    @Override
    public String selecionarString() {
        String string;

        string = JOptionPane.showInputDialog(null);

        return string;
    }

    @Override
    public LocalDate selecionarData(String msg) {
        return null;
    }
}
