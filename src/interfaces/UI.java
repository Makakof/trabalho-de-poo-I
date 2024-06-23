package interfaces;

import java.time.LocalDate;

public class UI implements InterfaceUsuario{



    @Override
    public void exibirMenuPrincipal() {

    }

    @Override
    public void exibirMenuGerenciaCliente() {

    }

    @Override
    public void exibirMenuGerenciaTarifas() {

    }

    @Override
    public void exibirSubMenuGerenciaVeiculos() {

    }

    @Override
    public void exibirMenuGerenciaVagas() {

    }

    @Override
    public void exibirMenuGerenciaEstacionamento() {

    }

    @Override
    public void exibir(String msg) {

    }

    @Override
    public byte selecionarByte(String msg) {
        return 0;
    }

    @Override
    public int selecionarInt(String msg) {
        return 0;
    }

    @Override
    public double selecionarDouble(String msg) {
        return 0;
    }

    @Override
    public byte selecionarByte() {
        return 0;
    }

    @Override
    public String selecionarString(String msg) {
        return null;
    }

    @Override
    public String selecionarString() {
        return null;
    }

    @Override
    public LocalDate selecionarData(String msg) {
        return null;
    }
}
