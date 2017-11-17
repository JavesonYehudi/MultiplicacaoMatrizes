package br.com.uerj;

import br.com.uerj.controle.Cliente;
import br.com.uerj.modelo.Matriz;
import br.com.uerj.modelo.Resultado;
import br.com.uerj.modelo.Tarefa;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;


public class ClienteSocket {
    private Cliente cliente = new Cliente();

    public static void main(String[] args) {
        try {
            new ClienteSocket( "127.0.0.1",12345).executa();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String host;
    private int porta;

    public ClienteSocket(String host, int porta) {
        this.host = host;
        this.porta = porta;
    }

    public void executa() throws IOException {

        Socket clienteSocket = new Socket(this.host, this.porta);
        System.out.println("O cliente se conectou ao servidor!");

        List<Tarefa> tarefas = this.getTarefas();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clienteSocket.getOutputStream());
        objectOutputStream.writeObject(tarefas);

        Thread thread = new Thread(new RecebeMensagem(clienteSocket.getInputStream()));
        thread.start();
        while (thread.isAlive()){}
    }

    private List<Tarefa> getTarefas() throws IOException {
        cliente.iniciaMatriz();
        return cliente.divideTarefas();
    }

    public class RecebeMensagem implements Runnable{
        private InputStream inputStream;

        public RecebeMensagem(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        public void run(){

            ObjectInputStream objectInputStream = null;
            try {
                objectInputStream = new ObjectInputStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Resultado resultado;
            while(true) {
                try {
                    resultado = (Resultado) objectInputStream.readObject();
                    Matriz matriz = cliente.geraMatrizResultante(resultado);

                    System.out.println(matriz);
                } catch (IOException | ClassNotFoundException e) {}
            }
        }
    }
}
