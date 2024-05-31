package ingressos;

import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import enums.TipoVeiculo;
import modelagem.Vaga;
import tarifacao.TarifaEstacionamento;
import tarifacao.TarifaHorista;
import utilitarios.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TicketHorista extends TicketEstacionamento {


    public TicketHorista(Cliente cliente, Vaga vaga, Veiculo veiculo, TarifaEstacionamento tarifa){
        super(cliente, vaga, veiculo, tarifa);
    }

    public void encerrarTicket() {
        this.setDataFim(LocalDateTime.now());
        double totalPagar = calcularTotalPagar();
        this.setTotalPagar(totalPagar);
    }

    public double calcularTotalPagar(){

        double totalPagar;
        long diferencaHoras;
        LocalDateTime inicioDoDia;

        if(!virouDia(this.getDataInicio(), this.getDataFim())) {

            diferencaHoras = Util.calcularHoras(this.getDataInicio(), this.getDataFim());
            totalPagar = calcularTotal(diferencaHoras, (TarifaHorista) this.getTarifa());
        }
        else{

            inicioDoDia = this.getDataInicio().plusDays(1); //acrescenta um dia
            inicioDoDia = inicioDoDia.toLocalDate().atStartOfDay(); //inicializa a variavel com a virada do dia(00:00)

            diferencaHoras = Util.calcularHoras(this.getDataInicio(), inicioDoDia); //horas antes do dia virar
            totalPagar = calcularTotal(diferencaHoras, (TarifaHorista) this.getTarifa());

            diferencaHoras = Util.calcularHoras(inicioDoDia, this.getDataFim()); //horas de multa
            totalPagar += calcularMulta(diferencaHoras, (TarifaHorista) this.getTarifa());

        }

        return totalPagar;
    }

    public double calcularMulta(long diferencaHoras, TarifaHorista tarifa) {

       return (tarifa.getValorPrimeiraHora() * 2) * diferencaHoras;
    }

    public double calcularTotal(long diferencaHoras, TarifaHorista tarifa) {
        double totalPagar;

        double valorPrimeiraHora = tarifa.getValorPrimeiraHora();
        double valorHoraSubsequente = tarifa.getValorHoraSubsequente();

        if (this.getVeiculo().getTipoVeiculo() == TipoVeiculo.MOTO){

            valorPrimeiraHora = tarifa.getValorPrimeiraHora() / 2;
            valorHoraSubsequente = tarifa.getValorHoraSubsequente() / 2;

        }else if (this.getVeiculo().getTipoVeiculo() == TipoVeiculo.ONIBUS){

            valorPrimeiraHora = tarifa.getValorPrimeiraHora() / 2;
            valorHoraSubsequente = tarifa.getValorHoraSubsequente() / 2;

        }

        if (diferencaHoras > 1) {
            totalPagar = valorPrimeiraHora;
            totalPagar += valorHoraSubsequente * (diferencaHoras - 1);
        } else
            totalPagar = valorPrimeiraHora;

        return totalPagar;
    }

    public boolean virouDia (LocalDateTime dataInicio, LocalDateTime dataFim){

        LocalDate inicio = dataInicio.toLocalDate();
        LocalDate fim = dataFim.toLocalDate();

        return inicio.equals(fim);
    }

}
