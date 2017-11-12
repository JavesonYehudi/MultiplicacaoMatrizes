package br.com.uerj.controle;

import br.com.uerj.modelo.Tarefa;

import java.util.concurrent.Callable;

public class ExecutorDeTarefa implements Callable<Integer>{
    private Tarefa tarefa;

    public ExecutorDeTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    @Override
    public Integer call() throws Exception {
        int resultado = 0;

        for (int i = 0; i < tarefa.getColunas().size(); i++) {
            resultado += tarefa.getCelulaDaLinha(i).getValor() * tarefa.getCelulaDaColuna(i).getValor();
        }

        return resultado;
    }
}
