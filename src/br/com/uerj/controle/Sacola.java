package br.com.uerj.controle;

import br.com.uerj.modelo.Tarefa;

import java.util.List;

public class Sacola {
    private static List<Tarefa> tarefas;

    public Sacola() {
    }

    public static List<Tarefa> getTarefas() {
        return tarefas;
    }

    public static void setTarefas(List<Tarefa> tarefas) {
        Sacola.tarefas = tarefas;
    }

}
