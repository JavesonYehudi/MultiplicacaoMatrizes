package br.com.uerj;

import br.com.uerj.controle.Cliente;
import br.com.uerj.modelo.Matriz;
import br.com.uerj.modelo.Resultado;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class RecebeMensagem implements Runnable{
    private InputStream inputStream;
    private Cliente cliente;

    public RecebeMensagem(InputStream inputStream, Cliente cliente) {
        this.inputStream = inputStream;
        this.cliente = cliente;
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