package kitmenu;

import enums.DiaDaSemana;
import enums.TipoVeiculo;
import enums.VagaStatus;
import excecoes.EstacionamentoException;
import ingressos.TicketEstacionaBem;
import modelagem.Vaga;

import java.util.ArrayList;

public class MenuGerenciaVagas {

    private UI terminal;

    public MenuGerenciaVagas(UI terminal) {
        this.terminal = terminal;
    }

    public static String formatarString(String string)
    {
        return string.toUpperCase().replaceAll("\\s", "");
    }

    public void GerenciarVagas(ArrayList<Vaga> vagas, ArrayList<TicketEstacionaBem> tickets) {

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

                    Vaga vagaExiste = consultarVaga(vagas, vaga.getNumeroVaga());

                    if(vagaExiste != null)
                        throw new EstacionamentoException("Esta vaga já existe!");

                    vagas.add(vaga);
                    terminal.exibir("Vaga cadastrada com sucesso!");

                    break;
                case 2:
                    numeroVaga = terminal.selecionarInt("Digite o numero da vaga: ");
                    vaga = consultarVaga(vagas, numeroVaga);
                    if (vaga == null)
                        throw new EstacionamentoException("Não existe vaga cadastrada com o numero: " + numeroVaga);

                    terminal.exibir(vaga.toString());

                    break;
                case 3:

                    numeroVaga = terminal.selecionarInt("Digite o numero da vaga: ");
                    vaga = consultarVaga(vagas, numeroVaga);
                    if (vaga != null) {
                        throw new EstacionamentoException("Não existe vaga cadastrada com o numero: " + numeroVaga);
                    }
                    if (verificaTicketVaga(vaga, tickets) != 0) {
                        throw new EstacionamentoException("Não é possivel exluir uma vaga que possui um carro estacionado");
                    }
                    vagas.remove(vaga);
                    terminal.exibir("Vaga excluida com sucesso!");

                    break;
                case 4:
                    numeroVaga = terminal.selecionarInt("Digite o numero da vaga: ");
                    vaga = consultarVaga(vagas, numeroVaga);

                    if (vaga == null) {
                        throw new EstacionamentoException("Não existe vaga cadastrada com o numero: " + numeroVaga);
                    }

                    terminal.exibir("Rua atual: " + vaga.getRua());
                    rua = terminal.selecionarString("Rua nova: ");

                    terminal.exibir("Numero atual: " + vaga.getNumeroVaga());
                    int numeroVagaNovo = terminal.selecionarInt("Numero novo: ");

                    Vaga verificaVaga = consultarVaga(vagas, numeroVagaNovo);

                    if (verificaVaga != null) {
                        throw new EstacionamentoException("Ja existe vaga cadastrada com o numero: " + numeroVaga);
                    }

                    vaga.setNumeroVaga(numeroVagaNovo);
                    vaga.setRua(rua);

                    break;
                case 5:
                    numeroVaga = terminal.selecionarInt("Digite o numero da vaga: ");
                    vaga = consultarVaga(vagas, numeroVaga);

                    if (vaga == null)
                        throw new EstacionamentoException("Não existe vaga cadastrada com o numero: " + numeroVaga);

                    alterarDisponibilidade(vaga);
                    break;
            }

            terminal.menuGerenciaVagas();
            opcao = terminal.selecionarByte("Digite a opção desejada: ");
        } while (opcao != 6);
    }

    public void alterarDisponibilidade(Vaga vagas) {

        terminal.exibir("1-DISPONIVEL 2-INDISPONIVEL 3-OCUPADA");
        int status = terminal.selecionarInt("escolha uma opção para alterar a disponibilidade: ");
        vagas.setVagaStatus(VagaStatus.values()[status]);
    }

    public Vaga consultarVaga(ArrayList<Vaga> vagas, int numero) {

        for (Vaga vaga : vagas) {
            if (vaga.getNumeroVaga() == numero)
                return vaga;
        }

        return null;
    }

    public Vaga cadastrarVaga(ArrayList<Vaga> vagas){

        int numeroVaga = terminal.selecionarInt("Digite o numero da vaga: ");
        String rua = terminal.selecionarString("Digite o nome da rua: ");

        String tipo = terminal.selecionarString("Digite qual tipo de veiculo pode estacionar na vaga (CARRO ou MOTO): ");
        tipo = formatarString(tipo);

        return new Vaga(numeroVaga, rua, TipoVeiculo.valueOf(tipo));
    }

    public byte verificaTicketVaga(Vaga vaga, ArrayList<TicketEstacionaBem> tickets)
    {
        for(TicketEstacionaBem ticket : tickets)
            if(vaga.getNumeroVaga() == ticket.getVaga().getNumeroVaga())
                return 1;
        return 0;
    }

}
