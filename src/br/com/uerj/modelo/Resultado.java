package br.com.uerj.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Resultado implements Serializable{
    private List<ResultadoTarefa> resultados = new ArrayList<>();

    public List<ResultadoTarefa> getResultados() {
        return resultados;
    }

    public void setResultados(List<ResultadoTarefa> resultados) {
        this.resultados = resultados;
    }

    public void addResultado(ResultadoTarefa resultado){
        resultados.add(resultado);
    }

    public ResultadoTarefa getResultadoTarefa(int index){
        return resultados.get(index);
    }

    @Override
    public String toString() {
        return "Resultado{" +
                "resultados=" + resultados +
                '}';
    }
}
