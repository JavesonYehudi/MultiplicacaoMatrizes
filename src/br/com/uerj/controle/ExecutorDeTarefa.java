package br.com.uerj.controle;

import br.com.uerj.modelo.ResultadoTarefa;
import br.com.uerj.modelo.Tarefa;

import java.net.Socket;
import java.util.concurrent.Callable;

public class ExecutorDeTarefa implements Callable<ResultadoTarefa>{
    private Tarefa tarefa;
    private Socket socket;

    public ExecutorDeTarefa() {
    }

    public ExecutorDeTarefa(Tarefa tarefa, Socket socket) {
        this.tarefa = tarefa;
        this.socket = socket;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    @Override
    public ResultadoTarefa call() {
        int resultado = 0;

        try{
            for (int i = 0; i < tarefa.getColunas().size(); i++)
                resultado += tarefa.getCelulaDaLinha(i).getValor() * tarefa.getCelulaDaColuna(i).getValor();

            Sacola.removeTarefa(socket, tarefa);
            return new ResultadoTarefa(getTarefa(), resultado, true);
       }catch (Exception e){
            return new ResultadoTarefa(getTarefa(), 0, false, e.getMessage());
        }

    }
}
