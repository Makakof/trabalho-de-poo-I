package ingressos;

import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import modelagem.Vaga;
import tarifacao.TarifaEstacionamento;
import tarifacao.TarifaMensalista;

public class TicketMensalista extends TicketEstacionamento {


    public TicketMensalista (Cliente cliente, Vaga vaga, Veiculo veiculo, TarifaEstacionamento tarifa){
        super(cliente, vaga, veiculo, tarifa);
    }

    public void encerrarTicket(){

    }

    public double calcularTotalPagar(){

        double totalPagar;

        totalPagar = ((TarifaMensalista) this.getTarifa()).getValorIntegral();
    }


}
