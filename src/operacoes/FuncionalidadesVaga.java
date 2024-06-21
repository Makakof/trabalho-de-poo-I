package operacoes;

import enums.TipoVeiculo;
import ingressos.TicketEstacionamento;
import interfaces.InterfaceUsuario;
import interfaces.Terminal;
import modelagem.Vaga;
import utilitarios.StringUtils;

import java.util.ArrayList;

public class FuncionalidadesVaga {

    private final InterfaceUsuario interfaceUsuario;

    public FuncionalidadesVaga() {
        this.interfaceUsuario = Terminal.getInstance();
    }

    public void alterarDisponibilidade(Vaga vaga) {

        interfaceUsuario.exibir("1-DISPONIVEL 2-INDISPONIVEL");
        int status = interfaceUsuario.selecionarInt("escolha uma opção para alterar a disponibilidade: ");

        if (status == 1) {
            vaga.liberar();
        }else {
            vaga.proibirAcesso();
        }
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
        tipo = StringUtils.formatarPadraoCapturaDeDados(tipo);

        return new Vaga(numeroVaga, rua, TipoVeiculo.valueOf(tipo));
    }

    public boolean verificaSeAVagaTemTicket(Vaga vaga, ArrayList<TicketEstacionamento> tickets)
    {
        for(TicketEstacionamento ticket : tickets)
            if(vaga.getNumeroVaga() == ticket.getVaga().getNumeroVaga())
                return true;
        return false;
    }
}