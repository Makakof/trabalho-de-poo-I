package automovel;

import utilitarios.StringUtil;

public class Modelo
{
    private String nomeModelo;

    public Modelo() {
    }

    public Modelo(String nomeModelo)
    {
        this.nomeModelo = StringUtil.formatarModelo(nomeModelo);
    }

    public String getNomeModelo() {
        return nomeModelo;
    }

    public String toString()
    {
        return nomeModelo;
    }
}
