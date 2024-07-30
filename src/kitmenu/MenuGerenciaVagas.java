package kitmenu;

import dados.Repositorio;
import excecoes.ExcecaoAbstrata;
import excecoes.ExcecaoEntradaInvalida;
import excecoes.ExcecaoEstacionamento;
import excecoes.ExcecaoMenu;
import ingressos.TicketEstacionamento;
import interfaces.InterfaceUsuario;
import modelagem.Vaga;
import operacoes.FuncionalidadesVaga;
import java.util.List;

public class MenuGerenciaVagas {

    public static void gerenciarVagas() {
        InterfaceUsuario interfaceUsuario = Repositorio.getInstance().getUI();;
        FuncionalidadesVaga funcVagas = new FuncionalidadesVaga();
        List<Vaga> vagas = Repositorio.getInstance().getVagas();
        List<TicketEstacionamento> tickets = Repositorio.getInstance().getTickets();
        byte opcao;
        int numeroVaga;
        String rua;
        Vaga vaga;


        do {

            interfaceUsuario.exibirMenuGerenciaVagas();
            opcao = interfaceUsuario.selecionarByte("Digite a opção desejada: ");

            switch (opcao) {
                case 1: //cadastrar vaga

                    vaga = funcVagas.cadastrarVaga(vagas);

                    Vaga vagaExiste = FuncionalidadesVaga.consultarVaga(vagas, vaga.getNumeroVaga());

                    if(vagaExiste != null)
                        throw new ExcecaoEntradaInvalida("Esta vaga já existe!","Menu Gerencia Vagas",2);

                    vagas.add(vaga);
                    interfaceUsuario.exibir("Vaga cadastrada com sucesso!");

                    break;
                case 2: // consultar vaga
                    numeroVaga = interfaceUsuario.selecionarInt("Digite o numero da vaga: ");
                    vaga = FuncionalidadesVaga.consultarVaga(vagas, numeroVaga);
                    if (vaga == null)
                        throw new ExcecaoEntradaInvalida("Não existe vaga cadastrada com o numero","Menu Gerencia Vagas",2);

                    interfaceUsuario.exibir(vaga.toString());

                    break;
                case 3: // excluir vaga

                    numeroVaga = interfaceUsuario.selecionarInt("Digite o numero da vaga: ");
                    vaga = FuncionalidadesVaga.consultarVaga(vagas, numeroVaga);
                    if (vaga != null) {
                        throw new ExcecaoEntradaInvalida("Não existe vaga cadastrada com o numero","Menu Gerencia Vagas",2);
                    }
                    if (funcVagas.verificaSeAVagaTemTicket(vaga, tickets)) {
                        throw new ExcecaoEstacionamento("Não é possivel exluir uma vaga que possui um carro estacionado","Menu Gerencia Vagas",3);
                    }
                    vagas.remove(vaga);
                    interfaceUsuario.exibir("Vaga excluida com sucesso!");

                    break;
                case 4: // atualizar dados da vaga
                    numeroVaga = interfaceUsuario.selecionarInt("Digite o numero da vaga: ");
                    vaga = FuncionalidadesVaga.consultarVaga(vagas, numeroVaga);

                    if (vaga == null) {
                        throw new ExcecaoEntradaInvalida("Não existe vaga cadastrada com o numero","Menu Gerencia Vagas",2);
                    }

                    interfaceUsuario.exibir("Rua atual: " + vaga.getRua());
                    rua = interfaceUsuario.selecionarString("Rua nova: ");

                    interfaceUsuario.exibir("Numero atual: " + vaga.getNumeroVaga());
                    int numeroVagaNovo = interfaceUsuario.selecionarInt("Numero novo: ");

                    Vaga verificaVaga = FuncionalidadesVaga.consultarVaga(vagas, numeroVagaNovo);

                    if (verificaVaga != null) {
                        throw new ExcecaoEstacionamento("Ja existe vaga cadastrada com o numero","Menu Gerencia Vagas",3);
                    }

                    vaga.setNumeroVaga(numeroVagaNovo);
                    vaga.setRua(rua);

                    break;
                case 5: // alterar disponibilidade da vaga
                    numeroVaga = interfaceUsuario.selecionarInt("Digite o numero da vaga: ");
                    vaga = FuncionalidadesVaga.consultarVaga(vagas, numeroVaga);

                    if (vaga == null)
                        throw new ExcecaoEntradaInvalida("Não existe vaga cadastrada com o numero","Menu Gerencia Vagas",2);

                    funcVagas.alterarDisponibilidade(vaga);
                    break;
                case 6: //voltar
                    break;
                default:
                    throw new ExcecaoMenu("Opção inválida","Menu Gerencia Vagas",1);
            }
        } while (opcao != 6);
    }
}
