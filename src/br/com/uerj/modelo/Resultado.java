package br.com.uerj.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Resultado implements Serializable{
    private List<Integer> resultados = new ArrayList<>();

    public List<Integer> getResultados() {
        return resultados;
    }

    public void setResultados(List<Integer> resultados) {
        this.resultados = resultados;
    }

    public void addResultado(int resultado){
        resultados.add(resultado);
    }

    @Override
    public String toString() {
        return "Resultado{" +
                "resultados=" + resultados +
                '}';
    }
}
