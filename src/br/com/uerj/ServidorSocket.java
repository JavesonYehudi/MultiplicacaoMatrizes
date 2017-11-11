package br.com.uerj;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        System.out.println("Servidor aberto!");

        while(true){
            Socket cliente = servidor.accept();

            // adiciona saida do cliente à lista
            //PrintStream ps = new PrintStream(cliente.getOutputStream());
            //this.clientes.add(ps);

            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            entrada.lines().forEach(s -> System.out.println(s));

            entrada.close();
        }
    }
}
