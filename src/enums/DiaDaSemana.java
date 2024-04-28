package enums;

public enum DiaDaSemana {
    SEGUNDA (0),
    TERCA (1),
    QUARTA (2),
    QUINTA (3),
    SEXTA (4),
    SÁBADO(5),
    DOMINGO (6);

    private int valorOpcao;

    DiaDaSemana(int valorOpcao){
        this.valorOpcao = valorOpcao;
    }

    public int getValorOpcao() {
        return valorOpcao;
    }
}
