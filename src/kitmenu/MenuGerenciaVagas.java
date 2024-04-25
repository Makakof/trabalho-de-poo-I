package kitmenu;

import enums.VagaStatus;
import modelagem.Vaga;

import java.util.ArrayList;

public class MenuGerenciaVagas {

    private UI terminal;

    public MenuGerenciaVagas(UI terminal) {
        this.terminal = terminal;
    }

    public void GerenciaVagas(ArrayList<Vaga> vagas) {

        byte opcao;
        int numeroVaga;
        String rua;
        Vaga vaga;

        terminal.menuGerenciaVagas();
        opcao = terminal.selecionarByte("Digite a opção desejada: ");

        do {

            switch (opcao) {
                case 1:

                    vaga = cadastrarVaga(vagas);
                    if(vaga != null)
                        vagas.add(vaga);
                    else
                        terminal.exibir("Esta vaga já existe!");

                    break;
                case 2:

                    numeroVaga = terminal.selecionarInt("Digite o numero da vaga: ");
                    vaga = buscarVaga(vagas, numeroVaga);
                    if (vaga != null)
                        terminal.exibir(vaga.toString());

                    else
                        terminal.exibir("Não existe vaga cadastrada com este numero!");

                    break;
                case 3:

                    numeroVaga = terminal.selecionarInt("Digite o numero da vaga: ");
                    vaga = buscarVaga(vagas, numeroVaga);
                    if (vaga != null) {
                        vagas.remove(vaga);
                        terminal.exibir("Vaga excluida com sucesso!");
                    }
                    else
                        terminal.exibir("Não existe vaga cadastrada com este numero!");

                    break;
                case 4:

                    numeroVaga = terminal.selecionarInt("Digite o numero da vaga: ");
                    vaga = buscarVaga(vagas, numeroVaga);

                    if (vaga != null){

                        terminal.exibir("Rua atual: " + vaga.getRua());
                        rua = terminal.selecionarString("Rua nova: ");

                        terminal.exibir("Numero atual: " + vaga.getNumeroVaga());
                        int numeroVagaNovo = terminal.selecionarInt("Numero novo: ");

                        Vaga verificaVaga = buscarVaga(vagas, numeroVagaNovo);

                        if(verificaVaga == null){
                            vaga.setNumeroVaga(numeroVagaNovo);
                            vaga.setRua(rua);
                        }
                        else
                            terminal.exibir("Já existe uma vaga cadastrada com este numero!");
                    }
                    else
                        terminal.exibir("Não existe vaga cadastrada com este numero!");

                    break;
                case 5:

                    numeroVaga = terminal.selecionarInt("Digite o numero da vaga: ");
                    vaga = buscarVaga(vagas, numeroVaga);

                    if (vaga != null)
                        alterarDisponibilidade(vaga);

                    else
                        terminal.exibir("Não existe vaga cadastrada com este numero!");

                    break;
            }

            terminal.menuGerenciaVagas();
            opcao = terminal.selecionarByte("Digite a opção desejada: ");
        } while (opcao != 6);
    }

    public void alterarDisponibilidade(Vaga vagas) {

        terminal.exibir("1-DISPONIVEL 2-INDISPONIVEL 3-OCUPADA");
        String status = terminal.selecionarString("Alterar disponibilidade para: ");
        vagas.setVagaStatus(status);
    }

    public Vaga buscarVaga(ArrayList<Vaga> vagas, int numero) {

        for (Vaga vaga : vagas) {
            if (vaga.getNumeroVaga() == numero)
                return vaga;
        }

        return null;
    }

    public Vaga cadastrarVaga(ArrayList<Vaga> vagas){

        int numeroVaga = terminal.selecionarInt("Digite o numero da vaga: ");
        String rua = terminal.selecionarString("Digite o nome da rua: ");

        Vaga vaga = buscarVaga(vagas, numeroVaga);
        if (vaga == null){
            vaga = new Vaga(numeroVaga, rua);
            return vaga;
        }

        return null;
    }
}
