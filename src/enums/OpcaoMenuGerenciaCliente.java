package enums;

public enum OpcaoMenuGerenciaCliente {
    OPCAO1 ("# 1 - Cadastrar"),
    OPCAO2 ("\n# 2 - Procurar por documento"),
    OPCAO3 ("\n# 3 - Excluir"),
    OPCAO4 ("\n# 4 - Editar"),
    OPCAO5 ("\n# 5 - Gerenciar veiculos"),
    OPCAO6 ("\n# 6 - Listas todos os cadastros"),
    SAIR ("\n# 7 - Voltar");

    private String descricao;

    OpcaoMenuGerenciaCliente(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
