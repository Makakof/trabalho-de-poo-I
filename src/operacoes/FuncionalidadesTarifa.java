package operacoes;

import dados.Repositorio;
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

    public FuncionalidadesTarifa() {interfaceUsuario = Repositorio.getInstance().getUI();}

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
        tipoTarifa = StringUtils.formatarPadraoCapturaDeDados(tipoTarifa);

        if (tipoTarifa.equals("MENSALISTA"))
            tarifa = cadastrarTarifaMensalista();
        else
            tarifa = cadastrarTarifaHorista();

        return tarifa;
    }


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

        for (String parte : partes) {
            diaDaSemana.add(DiaDaSemana.valueOf(parte));
        }

        return diaDaSemana;
    }

}
