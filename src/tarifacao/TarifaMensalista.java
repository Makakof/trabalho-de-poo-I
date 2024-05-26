package tarifacao;

import enums.DiaDaSemana;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TarifaMensalista extends TarifaEstacionamento{

    private double valorIntegral;

    public TarifaMensalista(double valorIntegral, ArrayList<DiaDaSemana> diaDaSemana) {
        super(diaDaSemana);
        this.valorIntegral = valorIntegral;
    }
}
