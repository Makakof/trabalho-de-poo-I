package dados;

import cliente.estacionabem.Cliente;
import ingressos.TicketEstacionaBem;
import modelagem.Vaga;


import java.util.ArrayList;

public class Repositorio
{
    private static Repositorio INSTANCE;

    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Vaga> vagas = new ArrayList<>();
    private ArrayList<TicketEstacionaBem> tickets = new ArrayList<>();
    private ArrayList<TicketEstacionaBem> logTickets = new ArrayList<>();

    public static synchronized Repositorio getInstance()
    {
        if(INSTANCE == null)
            INSTANCE = new Repositorio();

        return INSTANCE;
    }

    private Repositorio(){}

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Vaga> getVagas() {
        return vagas;
    }

    public ArrayList<TicketEstacionaBem> getTickets() {
        return tickets;
    }

    public ArrayList<TicketEstacionaBem> getLogTickets() {
        return logTickets;
    }
}
