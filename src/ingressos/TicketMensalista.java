package ingressos;

import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import modelagem.Vaga;
import tarifacao.TarifaEstacionamento;
import tarifacao.TarifaMensalista;

import java.time.LocalDateTime;

public class TicketMensalista extends TicketEstacionamento {


    public TicketMensalista (Cliente cliente, Vaga vaga, Veiculo veiculo, TarifaEstacionamento tarifa){
        super(cliente, vaga, veiculo, tarifa);
    }

    public void encerrarTicket(){
        this.setDataFim(LocalDateTime.now().plusDays(30)); // acrescenta 30 dias de validade ao ticket
        double totoalPagar = calcularTotalPagar();
        this.setTotalPagar(totoalPagar);
    }

    public double calcularTotalPagar(){

        return ((TarifaMensalista) this.getTarifa()).getValorIntegral();
    }


}
