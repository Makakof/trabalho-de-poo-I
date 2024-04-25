package kitmenu;

import java.lang.reflect.Array;
import java.util.ArrayList;

import enums.DiaDaSemana;
import modelagem.Vaga;

public class MenuGerenciaEstacionamento {

    private Ui terminal;

    public MenuGerenciaEstacionamento(){}

    public MenuGerenciaEstacionamento(Ui terminal){
        this.terminal = terminal;
    }

    public void gerenciaEstacionamento(ArrayList<Vaga> vagas){

        byte opcao;

        do {

            terminal.menuGerenciaEstacionamento();
            opcao = terminal.selecionarByte("Digite a opção desejada: ");

            switch(opcao){
                case 1:

                    break;
                case 2:


                    break;
                case 3:

                    listarVagas(vagas);
                    break;
                case 4:

                    break;
            }
        }while(opcao != 5);
    }

    public void estacionar(){

    }

    public void retirar(){

    }

    public void listarVagas (ArrayList<Vaga> vagas){
        for (Vaga vaga : vagas)
            terminal.exibir(vaga.toString());
    }

    public void gerenciarTarifas (){


    }
}
