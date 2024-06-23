package dados;

import automovel.Cor;
import automovel.Modelo;
import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import enums.TipoVeiculo;
import java.util.List;

public class Input {

    public static void inputClientes(List<Cliente> clientes){

        // Cadastrando 10 clientes
        for (int i = 1; i <= 10; i++) {
            Cliente cliente = new Cliente(("Cliente " + i), ("Documento " + i));

            // Criando um carro para o cliente
            Veiculo carro = new Veiculo(("ABC" + i), new Cor("PRATA"), new Modelo("SEDAN"), TipoVeiculo.CARRO);
            cliente.getVeiculos().add(carro);

            // Criando uma moto para o cliente
            Veiculo moto = new Veiculo(("XYZ" + i), new Cor("PRETA"), new Modelo("BIZ"), TipoVeiculo.MOTO);
            cliente.getVeiculos().add(moto);

            clientes.add(cliente);
        }
    }
}
