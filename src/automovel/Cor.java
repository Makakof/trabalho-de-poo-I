package automovel;

import utilitarios.StringUtil;

public class Cor
{
    private String nomeCor;

    public Cor() {
    }

    public Cor(String nomeCor)
    {
        this.nomeCor = StringUtil.formatarCor(nomeCor);
    }

    public String getNomeCor() {
        return nomeCor;
    }

    public void setNomeCor(String nomeCor) {
        this.nomeCor = nomeCor;
    }

    public String toString()
    {
        return nomeCor;
    }
}
