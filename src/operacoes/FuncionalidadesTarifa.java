package operacoes;

import enums.DiaDaSemana;
import interfaces.InterfaceUsuario;
import interfaces.Terminal;
import tarifacao.TarifaEstacionamento;
import tarifacao.TarifaHorista;
import tarifacao.TarifaMensalista;
import utilitarios.StringUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FuncionalidadesTarifa {

    private final InterfaceUsuario interfaceUsuario;

    public FuncionalidadesTarifa() {interfaceUsuario = Terminal.getInstance();}

    public static TarifaMensalista buscarTarifaMensalista(List<TarifaEstacionamento> tarifas) {
        TarifaMensalista tarifa = null;

        for (TarifaEstacionamento tarifaAtual : tarifas) { //olha pra todas as tarifas

            if (tarifaAtual instanceof TarifaMensalista) { //procura pelo tipo de tarifa especificado

                if (tarifa == null)
                    tarifa = (TarifaMensalista) tarifaAtual;
                else {
                    if (tarifa.getDataInicio().isBefore(tarifaAtual.getDataInicio()))
                        tarifa = (TarifaMensalista) tarifaAtual;
                }
            }
        }

        return tarifa;
    }

    public static TarifaHorista buscarTarifaHorista(List<TarifaEstacionamento> tarifas) {

        TarifaHorista tarifa = null;
        boolean achouDia = false;

        for (TarifaEstacionamento tarifaAtual : tarifas) { //olha pra todas as tarifas

            if (tarifaAtual instanceof TarifaHorista) { //procura pelo tipo de tarifa especificado

                for (DiaDaSemana diasTarifa : ((TarifaHorista) tarifaAtual).getDiaDaSemana()) {

                    DiaDaSemana diaAtual = DiaDaSemana.valueOf(StringUtils.getDiaDaSemana(LocalDateTime.now()));

                    if (diaAtual == diasTarifa) { //verifica se a tarifa é valida pro dia atual
                        achouDia = true;
                    }
                }

                if (achouDia) {

                    if (tarifa == null)
                        tarifa = (TarifaHorista) tarifaAtual;

                    else {

                        if (tarifa.getDataInicio().isBefore(tarifaAtual.getDataInicio()))
                            tarifa = (TarifaHorista) tarifaAtual;
                    }
                }
            }
        }

        return tarifa;
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
        List<DiaDaSemana> dias = new ArrayList<>();

        primeiraHora = interfaceUsuario.selecionarDouble("Valor Primeira Hora: ");
        horaSubsequente = interfaceUsuario.selecionarDouble("Valor Hora Subsequente: ");

        dias = inicializarDiasDaSemana();

        return new TarifaHorista(primeiraHora, horaSubsequente, dias);
    }

    public List<DiaDaSemana> inicializarDiasDaSemana() {

        List<DiaDaSemana> diaDaSemana = new ArrayList<>();
        interfaceUsuario.exibir("Exemplo (SEGUNDA,TERCA,QUARTA)");
        String string = interfaceUsuario.selecionarString("Digite os dias da semana :");
        string = StringUtils.formatarPadraoCapturaDeDados(string);
        string = StringUtils.retirarAcentosESinais(string);

        String[] partes = string.split(",");

        for(int i = 0; i<partes.length; i++){
            diaDaSemana.add(DiaDaSemana.valueOf(partes[i]));
        }

        return diaDaSemana;
    }

}
