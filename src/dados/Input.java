package dados;

import automovel.Cor;
import automovel.Modelo;
import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import enums.DiaDaSemana;
import enums.TipoVeiculo;
import modelagem.Vaga;
import tarifacao.TarifaEstacionamento;
import tarifacao.TarifaHorista;
import tarifacao.TarifaMensalista;

import java.util.ArrayList;
import java.util.List;

public class Input {

    public static void inputClientes(List<Cliente> clientes){

        // Cadastrando 10 clientes
        for (int i = 1; i <= 10; i++) {
            Cliente cliente = new Cliente(("Cliente " + i), ("DOCUMENTO" + i));

            // Criando um carro para o cliente
            Veiculo carro = new Veiculo(("ABC" + i), new Cor("PRATA"), new Modelo("SEDAN"), TipoVeiculo.CARRO);
            cliente.getVeiculos().add(carro);

            // Criando uma moto para o cliente
            Veiculo moto = new Veiculo(("XYZ" + i), new Cor("PRETA"), new Modelo("BIZ"), TipoVeiculo.MOTO);
            cliente.getVeiculos().add(moto);

            clientes.add(cliente);
        }
    }

    public static void inputVagas (List<Vaga> vagas){

        //cadastrar 20 vagas
        for (int i = 1; i <= 20; i++){

            TipoVeiculo tipo;

            if (i % 2 == 0)
                tipo = TipoVeiculo.CARRO;
            else
                tipo = TipoVeiculo.MOTO;

            Vaga vaga = new Vaga(i, "RUA1", tipo);
            vagas.add(vaga);
        }
    }

    public static void inputTarifas (List<TarifaEstacionamento> tarifas){

        List<DiaDaSemana> dias = new ArrayList<>();
        dias.add(DiaDaSemana.DOMINGO);
        tarifas.add(new TarifaHorista(10, 12, dias));

        tarifas.add(new TarifaMensalista(120));
    }
}
