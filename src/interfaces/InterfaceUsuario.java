package interfaces;

import java.time.LocalDate;

public interface InterfaceUsuario
{
    public void exibirMenuPrincipal();
    public void exibirMenuGerenciaCliente();
    public void exibirMenuGerenciaTarifas();
    public void exibirSubMenuGerenciaVeiculos();
    public void exibirMenuGerenciaVagas();
    public void exibirMenuGerenciaEstacionamento();
    public void exibir(String msg);
    public byte selecionarByte(String msg);
    public int selecionarInt(String msg);
    public double selecionarDouble(String msg);
    public byte selecionarByte();
    public String selecionarString(String msg);
    public String selecionarString();
    public LocalDate selecionarData(String msg);
}
