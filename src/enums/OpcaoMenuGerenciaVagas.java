package enums;

public enum OpcaoMenuGerenciaVagas {
    OPCAO1 ("#1 - Cadastrar"),
    OPCAO2 ("\n# 2 - Consultar por numero"),
    OPCAO3 ("\n# 3 - Excluir"),
    OPCAO4 ("\n# 4 - Editar"),
    OPCAO5 ("\n# 5 - Alterar disponibilidade"),
    SAIR ("\n# 6 - Voltar");

    private String descricao;

    OpcaoMenuGerenciaVagas(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
