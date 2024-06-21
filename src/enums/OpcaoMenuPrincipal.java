package enums;

public enum OpcaoMenuPrincipal {
    OPCAO1 ("# 1 - Gerenciar clientes"),
    OPCAO2 ("\n# 2 - Gerenciar vagas"),
    OPCAO3 ("\n# 3 - Gerenciar estacionamento"),
    OPCAO4 ("\n# 4 - Cadastros gerais"),
    OPCAO5 ("\n# 5 - Consultar total faturado em um periodo"),
    SAIR ("\n# 6 - Sair do Programa");

    private String descricao;

    OpcaoMenuPrincipal(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
