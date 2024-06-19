package tarifacao;

import java.time.LocalDateTime;

public abstract class TarifaEstacionamento {

    private LocalDateTime dataInicio;

    public TarifaEstacionamento() {
        this.dataInicio = LocalDateTime.now();
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

}

