package br.com.uerj.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Wrapper para facilitar o uso da matriz
 */
public class Matriz implements Serializable{
    private Set<Celula> celulas;

    public Matriz() {
        celulas = new HashSet<>();
    }

    public Set<Celula> getCelulas() {
        return celulas;
    }

    public void setCelulas(Set<Celula> celulas) {
        this.celulas = celulas;
    }

    /**
     * Adiciona um valor na celula (linha/coluna) da matriz
     * @param linha
     * @param coluna
     * @param valor
     */
    public void addCelula(int linha, int coluna, int valor){
        celulas.add(new Celula(linha, coluna, valor));
    }

    /**
     * remove uma celula da matriz de acordo com a linha e coluna especificada
     * @param linha
     * @param coluna
     */
    public void removeCelula(int linha, int coluna){
        this.celulas.remove(this.getCelula(linha, coluna));
    }

    /**
     * Devolve a celula de acordo com a linha e coluna especificada
     * @param linha
     * @param coluna
     * @return A celula especifia da linha e coluna
     */
    public Celula getCelula(int linha, int coluna){
        return (Celula)this.celulas.stream().filter(celula -> celula.getLinha() == linha && celula.getColuna() == coluna).toArray()[0];
    }

    /**
     * Devolve todas as celulas da linha
     * @param linha
     * @return celulas especificas da linha
     */
    public Set<Celula> getLinhas(int linha){
        return this.celulas.stream().filter(celula -> celula.getLinha() == linha).collect(Collectors.toSet());
    }

    /**
     * Devolve todas as celulas da coluna
     * @param coluna
     * @return celulas especificas da coluna
     */
    public Set<Celula> getColunas(int coluna){
        return this.celulas.stream().filter(celula -> celula.getColuna() == coluna).collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matriz)) return false;

        Matriz matriz = (Matriz) o;

        return getCelulas().equals(matriz.getCelulas());
    }

    @Override
    public int hashCode() {
        return getCelulas().hashCode();
    }

    @Override
    public String toString() {
        return "Matriz{" +
                "celulas=" + celulas +
                '}';
    }


}