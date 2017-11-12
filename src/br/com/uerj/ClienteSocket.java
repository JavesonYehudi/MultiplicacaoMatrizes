package br.com.uerj;

import br.com.uerj.controle.Cliente;
import br.com.uerj.modelo.Tarefa;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;


public class ClienteSocket {

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
        System.out.println(tarefas);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clienteSocket.getOutputStream());
        objectOutputStream.writeObject(tarefas);
    }

    private List<Tarefa> getTarefas() throws IOException {
        Cliente cliente = new Cliente();
        cliente.iniciaMatriz();
        return cliente.divideTarefas();
    }
}
