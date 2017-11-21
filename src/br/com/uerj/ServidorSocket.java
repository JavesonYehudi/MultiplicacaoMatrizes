package br.com.uerj;

import br.com.uerj.controle.Sacola;
import br.com.uerj.modelo.Tarefa;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServidorSocket {
    public static void main(String[] args){
        try {
            new ServidorSocket(12345).executa();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int porta;

    public ServidorSocket(int porta) {
        this.porta = porta;
    }

    public void executa() throws IOException{
        ServerSocket servidor = new ServerSocket(porta);
        System.out.println("Servidor iniciado!");
        Sacola sacola = new Sacola();

        while(true){
            Socket cliente = servidor.accept();

            ObjectInputStream objectInputStream = new ObjectInputStream(cliente.getInputStream());

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(cliente.getOutputStream());

            try {
                List<Tarefa> tarefas = (List<Tarefa>)objectInputStream.readObject();
                sacola.addSocket(cliente, tarefas);
                sacola.executaTarefas(cliente);

                objectOutputStream.writeObject(sacola.getResultado(cliente));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            objectInputStream.close();
            objectOutputStream.close();
            cliente.close();
        }
    }
}
