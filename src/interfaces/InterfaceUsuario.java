package interfaces;

import java.time.LocalDate;

public interface InterfaceUsuario
{
    public void menuPrincipal();
    public void menuGerenciaCliente();
    public void menuGerenciaTarifas();
    public void subMenuGerenciaVeiculos();
    public void menuGerenciaVagas();
    public void menuGerenciaEstacionamento();
    public void exibir(String msg);
    public byte selecionarByte(String msg);
    public int selecionarInt(String msg);
    public double selecionarDouble(String msg);
    public byte selecionarByte();
    public String selecionarString(String msg);
    public String selecionarString();
    public LocalDate selecionarData(String msg);
}
