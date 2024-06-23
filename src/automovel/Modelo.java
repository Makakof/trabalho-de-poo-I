package automovel;

import utilitarios.StringUtils;

import java.io.Serializable;

public class Modelo implements Serializable
{
    private String nomeModelo;

    public Modelo() {
    }

    public Modelo(String nomeModelo)
    {
        this.nomeModelo = StringUtils.formatarPadraoCapturaDeDados(nomeModelo);
    }

    public String getNomeModelo() {
        return nomeModelo;
    }

    public String toString()
    {
        return nomeModelo;
    }
}
