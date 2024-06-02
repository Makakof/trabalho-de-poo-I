package kitmenu;

import dados.Repositorio;
import enums.DiaDaSemana;
import enums.HoristaMensalista;
import interfaces.Terminal;
import tarifacao.TarifaEstacionamento;
import tarifacao.TarifaHorista;
import tarifacao.TarifaMensalista;
import utilitarios.StringUtil;

import java.util.ArrayList;

public class SubMenuGerenciaTarifas
{
    private final Terminal terminal;

    public SubMenuGerenciaTarifas()
    {
        this.terminal = Terminal.getInstance();
    }

    public void gerenciarTarifas()
    {
        byte opcao;
        ArrayList<TarifaEstacionamento> tarifas = Repositorio.getInstance().getTarifas();
        TarifaEstacionamento tarifa;

        terminal.menuGerenciaTarifas();
        opcao = terminal.selecionarByte("Digite a opção desejada: ");

        switch (opcao)
        {
            case 1:

                tarifa = cadastrarTarifa();
                tarifas.add(tarifa);

                break;
            case 2:

                for (TarifaEstacionamento tarifaAtual : tarifas) {
                    System.out.println(tarifaAtual);
                }
                break;
        }
    }

    public TarifaEstacionamento cadastrarTarifa(){

        TarifaEstacionamento tarifa;

        String tipoTarifa = terminal.selecionarString("Cadastrar uma tarifa do tipo HORISTA ou MENSALISTA: ");

        if (HoristaMensalista.HORISTA.name().equals(tipoTarifa))
            tarifa = cadastrarTarifaMensalista();
        else
            tarifa = cadastrarTarifaHorista();

        return tarifa;
    }

    public void editarTarifa(){

        String diaDaSemana = terminal.selecionarString("Digite o dia da semana que deseja alterar: ");
        double primeiraHora = terminal.selecionarDouble("Valor primeira hora: ");
        double horaSubsequente = terminal.selecionarDouble("Valor hora subsequente: ");

        diaDaSemana = StringUtil.formatarDiaDaSemana(diaDaSemana);
        DiaDaSemana dia = DiaDaSemana.valueOf(diaDaSemana);
    }


    public TarifaMensalista cadastrarTarifaMensalista () {

        double valorIntegral;
        ArrayList<DiaDaSemana> dias = new ArrayList<>();

        valorIntegral = terminal.selecionarDouble("Valor integral: ");

        dias = inicializarDiasDaSemana();

        return new TarifaMensalista(valorIntegral, dias, HoristaMensalista.MENSALISTA);
    }

    public TarifaHorista cadastrarTarifaHorista () {

        double primeiraHora, horaSubsequente;
        ArrayList<DiaDaSemana> dias = new ArrayList<>();

        primeiraHora = terminal.selecionarDouble("Valor Primeira Hora: ");
        horaSubsequente = terminal.selecionarDouble("Valor Hora Subsequente: ");

        dias = inicializarDiasDaSemana();

        return new TarifaHorista(primeiraHora, horaSubsequente, dias, HoristaMensalista.HORISTA);
    }

    public ArrayList<DiaDaSemana> inicializarDiasDaSemana() {

        ArrayList<DiaDaSemana> diaDaSemana = new ArrayList<>();
        terminal.exibir("Exemplo (SEGUNDA,TERCA,QUARTA)");
        terminal.exibir("Digite os dias da semana dessa tarifa separados por virgula e sem pontuação:");
        String string = terminal.selecionarString();
        string = StringUtil.formatarDiaDaSemana(string);

        String[] partes = string.split(",");

        for(int i = 0; i<partes.length; i++){
            diaDaSemana.add(DiaDaSemana.valueOf(partes[i]));
        }

        return diaDaSemana;
    }
}

