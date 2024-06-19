package kitmenu;

import dados.Repositorio;
import enums.DiaDaSemana;
import excecoes.EstacionamentoException;
import interfaces.InterfaceUsuario;
import interfaces.Terminal;
import tarifacao.TarifaEstacionamento;
import tarifacao.TarifaHorista;
import tarifacao.TarifaMensalista;
import utilitarios.StringUtil;

import java.util.ArrayList;

public class SubMenuGerenciaTarifas
{
    private final InterfaceUsuario interfaceUsuario;

    public SubMenuGerenciaTarifas()
    {
        this.interfaceUsuario = Terminal.getInstance();
    }

    public void gerenciarTarifas()
    {
        byte opcao;
        ArrayList<TarifaEstacionamento> tarifas = Repositorio.getInstance().getTarifas();
        TarifaEstacionamento tarifa;

        interfaceUsuario.menuGerenciaTarifas();
        opcao = interfaceUsuario.selecionarByte("Digite a opção desejada: ");

        switch (opcao)
        {
            case 1:

                tarifa = cadastrarTarifa();
                tarifas.add(tarifa);

                break;
            default:
                throw new EstacionamentoException("Opção inválida de menu");
        }
    }

    public TarifaEstacionamento cadastrarTarifa(){

        TarifaEstacionamento tarifa;

        String tipoTarifa = interfaceUsuario.selecionarString("Cadastrar uma tarifa do tipo HORISTA ou MENSALISTA: ");

        if (tipoTarifa.equals("MENSALISTA"))
            tarifa = cadastrarTarifaMensalista();
        else
            tarifa = cadastrarTarifaHorista();

        return tarifa;
    }

//    public void editarTarifa(){
//
//        String diaDaSemana = interfaceUsuario.selecionarString("Digite o dia da semana que deseja alterar: ");
//        double primeiraHora = interfaceUsuario.selecionarDouble("Valor primeira hora: ");
//        double horaSubsequente = interfaceUsuario.selecionarDouble("Valor hora subsequente: ");
//
//        diaDaSemana = StringUtil.formatarDiaDaSemana(diaDaSemana);
//        DiaDaSemana dia = DiaDaSemana.valueOf(diaDaSemana);
//    }


    public TarifaMensalista cadastrarTarifaMensalista () {

        double valorIntegral;

        valorIntegral = interfaceUsuario.selecionarDouble("Valor integral: ");

        return new TarifaMensalista(valorIntegral);
    }

    public TarifaHorista cadastrarTarifaHorista () {

        double primeiraHora, horaSubsequente;
        ArrayList<DiaDaSemana> dias = new ArrayList<>();

        primeiraHora = interfaceUsuario.selecionarDouble("Valor Primeira Hora: ");
        horaSubsequente = interfaceUsuario.selecionarDouble("Valor Hora Subsequente: ");

        dias = inicializarDiasDaSemana();

        return new TarifaHorista(primeiraHora, horaSubsequente, dias);
    }

    public ArrayList<DiaDaSemana> inicializarDiasDaSemana() {

        ArrayList<DiaDaSemana> diaDaSemana = new ArrayList<>();
        interfaceUsuario.exibir("Exemplo (SEGUNDA,TERCA,QUARTA)");
        interfaceUsuario.exibir("Digite os dias da semana dessa tarifa separados por virgula e sem pontuação:");
        String string = interfaceUsuario.selecionarString();
        string = StringUtil.formatarDiaDaSemana(string);

        String[] partes = string.split(",");

        for(int i = 0; i<partes.length; i++){
            diaDaSemana.add(DiaDaSemana.valueOf(partes[i]));
        }

        return diaDaSemana;
    }
}

