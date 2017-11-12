package br.com.uerj.modelo;

import java.io.Serializable;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Wrapper para facilitar o uso da matriz
 */
public class Matriz implements Serializable{
    private SortedSet<Celula> celulas;

    public Matriz() {
        celulas = new TreeSet<>();
    }

    public SortedSet<Celula> getCelulas() {
        return celulas;
    }

    public void setCelulas(SortedSet<Celula> celulas) {
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
    public SortedSet<Celula> getLinhas(int linha){
        Comparator<Celula> byTimestamp = Comparator.comparingLong(Celula::getLinha);
        Supplier<TreeSet<Celula>> supplier = () -> new TreeSet<Celula>(byTimestamp);
        return this.celulas.stream().filter(celula -> celula.getLinha() == linha).collect(Collectors.toCollection(supplier));
    }

    /**
     * Devolve todas as celulas da coluna
     * @param coluna
     * @return celulas especificas da coluna
     */
    public SortedSet<Celula> getColunas(int coluna){
        Comparator<Celula> byTimestamp = Comparator.comparingLong(Celula::getLinha);
        Supplier<TreeSet<Celula>> supplier = () -> new TreeSet<Celula>(byTimestamp);
        return this.celulas.stream().filter(celula -> celula.getColuna() == coluna).collect(Collectors.toCollection(supplier));
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

    /**
     * Wrapper para facilitar o entendimento de linha e coluna da matriz
     */
    public class Celula implements Serializable{
        private int linha, coluna, valor;

        public Celula() {
        }

        public Celula(int linha, int coluna, int valor) {
            this.linha = linha;
            this.coluna = coluna;
            this.valor = valor;
        }

        public int getLinha() {
            return linha;
        }

        public void setLinha(int linha) {
            this.linha = linha;
        }

        public int getColuna() {
            return coluna;
        }

        public void setColuna(int coluna) {
            this.coluna = coluna;
        }

        public int getValor() {
            return valor;
        }

        public void setValor(int valor) {
            this.valor = valor;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Celula)) return false;

            Celula celula = (Celula) o;

            if (getLinha() != celula.getLinha()) return false;
            if (getColuna() != celula.getColuna()) return false;
            return getValor() == celula.getValor();
        }

        @Override
        public int hashCode() {
            int result = getLinha();
            result = 31 * result + getColuna();
            result = 31 * result + getValor();
            return result;
        }

        @Override
        public String toString() {
            return "celula{" +
                    "linha=" + linha +
                    ", coluna=" + coluna +
                    ", valor=" + valor +
                    '}';
        }
    }
}