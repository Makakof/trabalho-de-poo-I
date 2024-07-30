package dados;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Arquivos {

    public static boolean verificaSeArquivoExiste(String caminhoArquivo) {

        File arquivo = new File(caminhoArquivo);

        return arquivo.exists();
    }

    public static void criaArquivo(String caminhoArquivo) throws IOException {

        try {
            Files.createFile(Paths.get(caminhoArquivo));

        } catch (IOException e) {
            throw new IOException();
        }
    }

    public static <T> void lerDoArquivo(List<T> arrays, String caminhoArquivo) {

        if (Arquivos.verificaSeArquivoExiste(caminhoArquivo)) {


            ObjectInputStream arquivo = null;
            try {

                arquivo = new ObjectInputStream(new FileInputStream(caminhoArquivo));

                while (true) {
                    T array = (T) arquivo.readObject();
                    arrays.add(array);
                }

            } catch (EOFException e) {

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            } catch (IOException e) {
                e.printStackTrace();

            } finally {

                try {
                    arquivo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            for(T array : arrays) {
                System.out.println(array.toString());
                mostraVeiculos(((Cliente) array).getVeiculos());
                System.out.println("----------------");
            }

        }
    }

    public static void mostraVeiculos(List<Veiculo> veiculos){

        for(Veiculo veiculo : veiculos) {
            System.out.println(veiculo.toString());
        }
    }

    public static void escreverNoArquivo(List<Cliente> arrays, String caminhoArquivo){
        if (!verificaSeArquivoExiste(caminhoArquivo)){
            try {
                criaArquivo(caminhoArquivo);
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        ObjectOutputStream arquivo = null;

        try{
            arquivo = new ObjectOutputStream(new FileOutputStream(caminhoArquivo));


            for (T array : arrays){
                arquivo.writeObject(array);
            }

        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                arquivo.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void inicializarDadosDoSistema(){

        Repositorio repositorio = Repositorio.getInstance();

        lerDoArquivo(repositorio.getClientes(), "Dados_Clientes.dat");

        lerDoArquivo(repositorio.getVagas(), "Dados_Vagas.dat");

        lerDoArquivo(repositorio.getTickets(), "Dados_Tickets.dat");

        lerDoArquivo(repositorio.getTarifas(), "Dados_Tarifas.dat");

    }

    public static void salvarDadosDoSistema (){

        Repositorio repositorio = Repositorio.getInstance();

        escreverNoArquivo(repositorio.getClientes(), "Dados_Clientes.dat");

        escreverNoArquivo(repositorio.getVagas(), "Dados_Vagas.dat");

        escreverNoArquivo(repositorio.getTickets(), "Dados_Tickets.dat");

        escreverNoArquivo(repositorio.getTarifas(), "Dados_Tarifas.dat");
    }
}
