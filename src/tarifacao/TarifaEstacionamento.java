package tarifacao;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class TarifaEstacionamento implements Serializable {

    private LocalDateTime dataInicio;

    public TarifaEstacionamento() {
        this.dataInicio = LocalDateTime.now();
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

}

