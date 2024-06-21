package enums;

public enum OpcaoMenuGerenciaEstacionamento {
    OPCAO1 ("#1 - Estacionar"),
    OPCAO2 ("\n# 2 - Retirar"),
    OPCAO3 ("\n# 3 - Listas todas as vagas disponiveis"),
    OPCAO4 ("\n# 4 - Gerenciar tarifas"),
    SAIR ("\n# 5 - Voltar");

    private String descricao;

    OpcaoMenuGerenciaEstacionamento(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
