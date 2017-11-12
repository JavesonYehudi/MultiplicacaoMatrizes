package br.com.uerj.controle;

import br.com.uerj.modelo.Resultado;
import br.com.uerj.modelo.Tarefa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Sacola {
    private List<Tarefa> tarefas = new ArrayList<>();
    private List<ExecutorDeTarefa> executoresDeTarefas = new ArrayList<>();
    private List<Future<Integer>> resultados = new ArrayList<>();

    public void executaTarefas(){
        tarefas.listIterator().forEachRemaining(tarefa -> {
            executoresDeTarefas.add(new ExecutorDeTarefa(tarefa));
        });

        executoresDeTarefas.forEach(executorDeTarefa -> {
            try{
                ExecutorService executor = Executors.newCachedThreadPool();
                resultados.add(executor.submit(executorDeTarefa));
            }catch (Exception e){
                this.addTarefa(executorDeTarefa.getTarefa());
            }
        });

    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public List<Future<Integer>> getResultado(){
        return resultados;
    }

    public void addTarefa(Tarefa tarefa){
        tarefas.add(tarefa);
    }

    public void removeTarefa(Tarefa tarefa){
        tarefas.remove(tarefa);
    }
}
