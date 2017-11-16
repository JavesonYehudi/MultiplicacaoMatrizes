package br.com.uerj.controle;

import br.com.uerj.modelo.Resultado;
import br.com.uerj.modelo.ResultadoTarefa;
import br.com.uerj.modelo.Tarefa;

import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Sacola extends ExecutorDeTarefa{
    private static ExecutorService executor = Executors.newCachedThreadPool();
    private static Map<Socket, List<Tarefa>> tarefas = new HashMap<>();

    private Map<Socket, List<Future<ResultadoTarefa>>> futures = new HashMap<>();
    private Map<Socket, Resultado> resultados = new HashMap<>();

    private List<ExecutorDeTarefa> executoresDeTarefas = new ArrayList<>();

    public void addSocket(Socket socket, List<Tarefa> tarefas){
        this.tarefas.put(socket, tarefas);
        this.futures.put(socket, new ArrayList<>());
        this.resultados.put(socket, new Resultado());
    }

    public void executaTarefas(Socket socket){
        for (Iterator<Tarefa> iterator = this.tarefas.get(socket).iterator(); iterator.hasNext();){
            Tarefa tarefa = iterator.next();
            this.executoresDeTarefas.add(new ExecutorDeTarefa(tarefa, socket));
        }

        this.executoresDeTarefas.forEach(executorDeTarefa -> {
            this.futures.get(socket).add(executaTarefa(executorDeTarefa));
        });

    }

    private Future<ResultadoTarefa> executaTarefa(ExecutorDeTarefa executorDeTarefa){
        return executor.submit(executorDeTarefa);
    }

    public Resultado getResultado(Socket socket){

        for (Iterator<Future<ResultadoTarefa>> iterator = futures.get(socket).iterator(); iterator.hasNext();){
            Future<ResultadoTarefa> future = iterator.next();
            try {
                ResultadoTarefa resultadoTarefa = validaResultadoDaTarefa(future.get(), socket);
                resultados.get(socket).addResultado(resultadoTarefa);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return resultados.get(socket);
    }

    private ResultadoTarefa validaResultadoDaTarefa(ResultadoTarefa resultadoTarefa, Socket socket){
        if(!resultadoTarefa.isFinalizado()){
            System.out.println("Houve um erro: " + resultadoTarefa.getMensagem());
            System.out.println("Executando a tarefa novamente.");
            try {
                ExecutorDeTarefa executorDeTarefa = new ExecutorDeTarefa(resultadoTarefa.getTarefa(), socket);
                return validaResultadoDaTarefa(this.executaTarefa(executorDeTarefa).get(), socket);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return resultadoTarefa;
    }

    public static void addTarefa(Socket socket, Tarefa tarefa){
        tarefas.get(socket).add(tarefa);
    }

    public static void removeTarefa(Socket socket, Tarefa tarefa){
        tarefas.get(socket).remove(tarefa);
    }
}
