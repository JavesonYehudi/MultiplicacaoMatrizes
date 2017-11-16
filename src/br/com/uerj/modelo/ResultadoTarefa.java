package br.com.uerj.modelo;

import java.io.Serializable;

public class ResultadoTarefa implements Serializable{
    private Tarefa tarefa;
    private int resultado;
    private boolean finalizado;
    private String mensagem = "Finalizado com sucesso";

    public ResultadoTarefa(Tarefa tarefa, int resultado, boolean finalizado) {
        this.tarefa = tarefa;
        this.resultado = resultado;
        this.finalizado = finalizado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ResultadoTarefa(Tarefa tarefa, int resultado, boolean finalizado, String mensagem) {

        this.tarefa = tarefa;
        this.resultado = resultado;
        this.finalizado = finalizado;
        this.mensagem = mensagem;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    @Override
    public String toString() {
        return "ResultadoTarefa{" +
                "tarefa=" + tarefa +
                ", resultado=" + resultado +
                '}';
    }
}
