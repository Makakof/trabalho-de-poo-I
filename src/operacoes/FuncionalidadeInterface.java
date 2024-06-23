package operacoes;

import dados.Repositorio;

import java.util.Scanner;

public class FuncionalidadeInterface {

    public static void EscolheInterface(){
        Scanner scanner = new Scanner(System.in);
        String escolhaFeita;
        System.out.print("Digite Terminal ou Grafica para escolher qual interface usar: ");
        escolhaFeita = scanner.nextLine();
        Repositorio.getInstance().setInterface(escolhaFeita);
    }
}
