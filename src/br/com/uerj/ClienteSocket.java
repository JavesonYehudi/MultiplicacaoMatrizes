package br.com.uerj;

import br.com.uerj.controle.Cliente;
import br.com.uerj.modelo.Tarefa;

import java.io.IOException;
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
        PrintStream saida = new PrintStream(clienteSocket.getOutputStream());
        saida.println("Teste");
        /*System.out.println("O cliente se conectou ao servidor!");
        Cliente cliente = new Cliente();
        cliente.iniciaMatriz();
        List<Tarefa> tarefas = cliente.divideTarefas();
        System.out.println(tarefas);*/
    }
}
