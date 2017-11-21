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

        Thread thread = new Thread(new RecebeMensagem(clienteSocket.getInputStream(), cliente));
        thread.start();
        while (thread.isAlive()){}
    }

    private List<Tarefa> getTarefas() throws IOException {
        cliente.iniciaMatriz();
        return cliente.divideTarefas();
    }
}
