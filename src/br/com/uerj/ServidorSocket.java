package br.com.uerj;

import br.com.uerj.controle.Sacola;
import br.com.uerj.modelo.Resultado;
import br.com.uerj.modelo.Tarefa;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class ServidorSocket {
    public static void main(String args[]){
        try {
            new ServidorSocket(12345).executa();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int porta;
    private List<PrintStream> clientes;

    public ServidorSocket(int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<PrintStream>();
    }

    public void executa() throws IOException{
        ServerSocket servidor = new ServerSocket(porta);
        System.out.println("Servidor iniciado!");

        while(true){
            Socket cliente = servidor.accept();

            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            ObjectInputStream objectInputStream = new ObjectInputStream(cliente.getInputStream());

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(cliente.getOutputStream());
            Resultado resultados = new Resultado();

            try {
                List<Tarefa> tarefas = (List<Tarefa>)objectInputStream.readObject();
                Sacola sacola = new Sacola();
                sacola.setTarefas(tarefas);
                //objectOutputStream.writeChars("Tarefas recebidas e adicionadas a Sacola");

                sacola.executaTarefas();
                sacola.getResultado().forEach(resultado -> {
                    try {
                        resultados.addResultado(resultado.get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                });

                objectOutputStream.writeObject(resultados);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            entrada.close();
        }
    }
}
