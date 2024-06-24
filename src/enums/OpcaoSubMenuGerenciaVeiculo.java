package enums;

public enum OpcaoSubMenuGerenciaVeiculo {
    OPCAO1 ("# 1 - Ver veiculos do cliente"),
    OPCAO2 ("\n# 2 - Adicionar veiculo"),
    OPCAO3 ("\n# 3 - Excluir veiculo"),
    OPCAO4 ("\n# 4 - Alterar cor do veiculo"),
    OPCAO5 ("\n# 5 - Voltar");

    private String descricao;

    OpcaoSubMenuGerenciaVeiculo(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
