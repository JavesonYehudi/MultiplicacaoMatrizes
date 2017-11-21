package br.com.uerj.modelo;

import java.io.Serializable;

/**
 * Wrapper para facilitar o entendimento de linha e coluna da matriz
 */
public class Celula implements Serializable {
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
        return true;
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