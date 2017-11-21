package br.com.uerj.modelo;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

public class Tarefa implements Serializable{
    private Set<Celula> linhas, colunas;

    public Tarefa(Set<Celula> linhas, Set<Celula> colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
    }

    public Set<Celula> getLinhas() {
        return linhas;
    }

    public void setLinhas(Set<Celula> linhas) {
        this.linhas = linhas;
    }

    public Set<Celula> getColunas() {
        return colunas;
    }

    public void setColunas(Set<Celula> colunas) {
        this.colunas = colunas;
    }

    public Celula getCelulaDaLinha(int index){
        return (Celula) this.getLinhas().stream().filter(linha -> linha.getColuna() == index).collect(Collectors.toSet()).toArray()[0];
    }

    public Celula getCelulaDaColuna(int index){
        return (Celula) this.getColunas().stream().filter(coluna -> coluna.getLinha() == index).collect(Collectors.toSet()).toArray()[0];
    }

    public void removeCelulaDaLinha(int index){
        this.linhas.remove((Celula)this.getLinhas().stream().filter(linha -> linha.getColuna() == index).collect(Collectors.toSet()).toArray()[0]);
    }

    public void removeCelulaDaColuna(int index){
        this.colunas.remove((Celula)this.getColunas().stream().filter(coluna -> coluna.getLinha() == index).collect(Collectors.toSet()).toArray()[0]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tarefa)) return false;

        Tarefa tarefa = (Tarefa) o;

        if (!getLinhas().equals(tarefa.getLinhas())) return false;
        return getColunas().equals(tarefa.getColunas());
    }

    @Override
    public int hashCode() {
        int result = getLinhas().hashCode();
        result = 31 * result + getColunas().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "linhas=" + linhas +
                ", colunas=" + colunas +
                '}';
    }
}
