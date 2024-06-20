package kitmenu;

import dados.Repositorio;
import enums.TipoVeiculo;
import enums.VagaStatus;
import excecoes.EstacionamentoException;
import ingressos.TicketEstacionamento;
import interfaces.InterfaceUsuario;
import interfaces.Terminal;
import modelagem.Vaga;
import utilitarios.StringUtils;

import java.util.ArrayList;

public class MenuGerenciaVagas {

    private final InterfaceUsuario interfaceUsuario;

    public MenuGerenciaVagas() {
        this.interfaceUsuario = Terminal.getInstance();
    }

    public void GerenciarVagas() {
        ArrayList<Vaga> vagas = Repositorio.getInstance().getVagas();
        ArrayList<TicketEstacionamento> tickets = Repositorio.getInstance().getTickets();
        byte opcao;
        int numeroVaga;
        String rua;
        Vaga vaga;


        do {

            interfaceUsuario.menuGerenciaVagas();
            opcao = interfaceUsuario.selecionarByte("Digite a opção desejada: ");

            switch (opcao) {
                case 1:

                    vaga = cadastrarVaga(vagas);

                    Vaga vagaExiste = consultarVaga(vagas, vaga.getNumeroVaga());

                    if(vagaExiste != null)
                        throw new EstacionamentoException("Esta vaga já existe!");

                    vagas.add(vaga);
                    interfaceUsuario.exibir("Vaga cadastrada com sucesso!");

                    break;
                case 2:
                    numeroVaga = interfaceUsuario.selecionarInt("Digite o numero da vaga: ");
                    vaga = consultarVaga(vagas, numeroVaga);
                    if (vaga == null)
                        throw new EstacionamentoException("Não existe vaga cadastrada com o numero: " + numeroVaga);

                    interfaceUsuario.exibir(vaga.toString());

                    break;
                case 3:

                    numeroVaga = interfaceUsuario.selecionarInt("Digite o numero da vaga: ");
                    vaga = consultarVaga(vagas, numeroVaga);
                    if (vaga != null) {
                        throw new EstacionamentoException("Não existe vaga cadastrada com o numero: " + numeroVaga);
                    }
                    if (verificaTicketVaga(vaga, tickets)) {
                        throw new EstacionamentoException("Não é possivel exluir uma vaga que possui um carro estacionado");
                    }
                    vagas.remove(vaga);
                    interfaceUsuario.exibir("Vaga excluida com sucesso!");

                    break;
                case 4:
                    numeroVaga = interfaceUsuario.selecionarInt("Digite o numero da vaga: ");
                    vaga = consultarVaga(vagas, numeroVaga);

                    if (vaga == null) {
                        throw new EstacionamentoException("Não existe vaga cadastrada com o numero: " + numeroVaga);
                    }

                    interfaceUsuario.exibir("Rua atual: " + vaga.getRua());
                    rua = interfaceUsuario.selecionarString("Rua nova: ");

                    interfaceUsuario.exibir("Numero atual: " + vaga.getNumeroVaga());
                    int numeroVagaNovo = interfaceUsuario.selecionarInt("Numero novo: ");

                    Vaga verificaVaga = consultarVaga(vagas, numeroVagaNovo);

                    if (verificaVaga != null) {
                        throw new EstacionamentoException("Ja existe vaga cadastrada com o numero: " + numeroVaga);
                    }

                    vaga.setNumeroVaga(numeroVagaNovo);
                    vaga.setRua(rua);

                    break;
                case 5:
                    numeroVaga = interfaceUsuario.selecionarInt("Digite o numero da vaga: ");
                    vaga = consultarVaga(vagas, numeroVaga);

                    if (vaga == null)
                        throw new EstacionamentoException("Não existe vaga cadastrada com o numero: " + numeroVaga);

                    alterarDisponibilidade(vaga);
                    break;
                case 6: //voltar
                    break;
                default:
                    throw new EstacionamentoException("Opção inválida de menu");
            }
        } while (opcao != 6);
    }

    public void alterarDisponibilidade(Vaga vagas) {

        interfaceUsuario.exibir("1-DISPONIVEL 2-INDISPONIVEL 3-OCUPADA");
        int status = interfaceUsuario.selecionarInt("escolha uma opção para alterar a disponibilidade: ");
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

        int numeroVaga = interfaceUsuario.selecionarInt("Digite o numero da vaga: ");
        String rua = interfaceUsuario.selecionarString("Digite o nome da rua: ");

        String tipo = interfaceUsuario.selecionarString("Digite qual tipo de veiculo pode estacionar na vaga (CARRO, MOTO, ou ONIBUS): ");
        tipo = StringUtils.formatarTipo(tipo);

        return new Vaga(numeroVaga, rua, TipoVeiculo.valueOf(tipo));
    }

    public boolean verificaTicketVaga(Vaga vaga, ArrayList<TicketEstacionamento> tickets)
    {
        for(TicketEstacionamento ticket : tickets)
            if(vaga.getNumeroVaga() == ticket.getVaga().getNumeroVaga())
                return true;
        return false;
    }

}
