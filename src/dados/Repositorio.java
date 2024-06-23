package dados;

import cliente.estacionabem.Cliente;
import ingressos.TicketEstacionamento;
import interfaces.Grafica;
import interfaces.InterfaceUsuario;
import interfaces.Terminal;
import interfaces.UI;
import modelagem.Vaga;
import operacoes.FuncionalidadesEstacionamento;
import operacoes.FuncionalidadesTarifa;
import operacoes.FuncionalidadesVaga;
import tarifacao.TarifaEstacionamento;


import java.util.ArrayList;
import java.util.List;

public class Repositorio
{
    private static Repositorio INSTANCE;

    private final List<Cliente> clientes;
    private final List<Vaga> vagas;
    private final List<TicketEstacionamento> tickets;
    private final List<TarifaEstacionamento> tarifas;
    private final FuncionalidadesVaga funcVaga;
    private final FuncionalidadesEstacionamento funcEstacionamento;
    private final FuncionalidadesTarifa funcTarifas;
    private final InterfaceUsuario UI;
    private static String interfaceEscolhida;


    public static synchronized Repositorio getInstance()
    {
        if(INSTANCE == null)
            INSTANCE = new Repositorio();

        return INSTANCE;
    }

    private Repositorio(){
        this.UI = new UI();
        clientes = new ArrayList<>();
        vagas = new ArrayList<>();
        tickets = new ArrayList<>();
        tarifas = new ArrayList<>();
        funcVaga = new FuncionalidadesVaga();
        funcEstacionamento = new FuncionalidadesEstacionamento();
        funcTarifas = new FuncionalidadesTarifa();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Vaga> getVagas() {
        return vagas;
    }

    public List<TicketEstacionamento> getTickets() {
        return tickets;
    }

    public List<TarifaEstacionamento> getTarifas() {return tarifas;}

    public FuncionalidadesVaga getFuncVaga() {return funcVaga;}
    public FuncionalidadesEstacionamento getFuncEstacionamento() {return funcEstacionamento;}
    public FuncionalidadesTarifa getFuncTarifas() {return funcTarifas;}
    public InterfaceUsuario getUI()
    {
        if(interfaceEscolhida.equalsIgnoreCase("grafica"))
            return Grafica.getInstance();

        return Terminal.getInstance();
    }
    public void setInterface(String decisao)
    {
        interfaceEscolhida = decisao;
    }
}
