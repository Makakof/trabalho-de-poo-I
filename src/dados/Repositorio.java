package dados;

import cliente.estacionabem.Cliente;
import ingressos.TicketEstacionamento;
import modelagem.Vaga;
import tarifacao.TarifaEstacionamento;


import java.util.ArrayList;

public class Repositorio
{
    private static Repositorio INSTANCE;

    private final ArrayList<Cliente> clientes;
    private final ArrayList<Vaga> vagas;
    private final ArrayList<TicketEstacionamento> tickets;
    private final ArrayList<TarifaEstacionamento> tarifas;

    public static synchronized Repositorio getInstance()
    {
        if(INSTANCE == null)
            INSTANCE = new Repositorio();

        return INSTANCE;
    }

    private Repositorio(){
        clientes = new ArrayList<>();
        vagas = new ArrayList<>();
        tickets = new ArrayList<>();
        tarifas = new ArrayList<>();
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Vaga> getVagas() {
        return vagas;
    }

    public ArrayList<TicketEstacionamento> getTickets() {
        return tickets;
    }

    public ArrayList<TarifaEstacionamento> getTarifas() {return tarifas;}
}
