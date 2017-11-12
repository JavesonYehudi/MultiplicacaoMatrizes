package br.com.uerj;

import br.com.uerj.controle.Sacola;
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


            try {
                List<Tarefa> tarefas = (List<Tarefa>)objectInputStream.readObject();
                Sacola sacola = new Sacola();
                sacola.setTarefas(tarefas);
                objectOutputStream.writeObject("Tarefas recebidas e adicionadas a Sacola");

                sacola.executaTarefas();
                sacola.getResultado().forEach(resultado -> {
                    try {
                        int valor = resultado.get();
                        System.out.println("Resultado da Tarefa: " + valor);
                        objectOutputStream.writeObject(valor);
                    } catch (IOException | InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                });
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            entrada.close();
        }
    }
}
