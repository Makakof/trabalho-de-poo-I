package enums;

public enum OpcaoMenuGerenciaTarifas {
    OPCAO1 ("# 1 - Cadastrar tarifa");

    private String descricao;

    OpcaoMenuGerenciaTarifas(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
