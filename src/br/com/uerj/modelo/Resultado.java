package br.com.uerj.modelo;

import java.io.Serializable;

public class Resultado implements Serializable{
    private Matriz matriz;

    public Matriz getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
    }

    @Override
    public String toString() {
        return "Resultado{" +
                "matriz=" + matriz +
                '}';
    }
}
