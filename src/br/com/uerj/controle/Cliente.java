package br.com.uerj.controle;

import br.com.uerj.modelo.Matriz;
import br.com.uerj.modelo.Resultado;
import br.com.uerj.modelo.Tarefa;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private ValoresDaMatriz valoresDaMatrizA, valoresDaMatrizB;
    private Matriz matrizA, matrizB;

    /**
     * Inicializa as matrizes A e B, obtendo os valores pelo usuario de quantidade de linhas e colunas <br>
     *     Apos a leitura de linhas e colunas, inicializa as leituras de valores das celulas das matrizes
     * @throws IOException
     */
    public void iniciaMatriz() throws IOException {
        iniciaLinhasEColunasDasMatrizes();

        matrizA = this.geraMatriz(valoresDaMatrizA, "A");
        matrizB = this.geraMatriz(valoresDaMatrizB, "B");
    }

    /**
     * Divide as tarefas
     * @return lista de tarefas a serem executadas
     */
    public List<Tarefa> divideTarefas(){
        List<Tarefa> tarefas = new ArrayList<>();

        for(int linha = 0; linha < valoresDaMatrizA.getQuantidadeDeLinhas(); linha++)
            for (int coluna = 0; coluna < valoresDaMatrizB.getQuantidadeDeColunas(); coluna++)
                tarefas.add(new Tarefa(matrizA.getLinhas(linha), matrizB.getColunas(coluna)));

        return tarefas;
    }

    /**
     * Inicializa as quantidades de linhas e colunas das matrizes A e B
     * @throws IOException
     */
    private void iniciaLinhasEColunasDasMatrizes() throws IOException {
        do{
            valoresDaMatrizA = lerLinhasEColunasDoTeclado("A");
            valoresDaMatrizB = lerLinhasEColunasDoTeclado("B");

            if(isInvalido()) {
                System.out.println("A quantidade de linhas de A deve ser igual a quantidade de colunas de B");
            }

        }while(isInvalido());
    }
    /**
     * Valida a quantidade de colunas da matriz A e a quantidade de linhas da matriz B
     * @return true se A == B
     */
    private boolean isInvalido() {
        return valoresDaMatrizA.getQuantidadeDeColunas() != valoresDaMatrizB.getQuantidadeDeLinhas();
    }

    /**
     * Le os valores das linhas e colunas da matriz e adiciona ao wrapper ValoresDaMatriz
     * @param matriz
     * @return ValoresDaMatriz
     * @throws IOException
     */
    private ValoresDaMatriz lerLinhasEColunasDoTeclado(String matriz) throws IOException {
        ValoresDaMatriz valoresDaMatriz = new ValoresDaMatriz();
        System.out.println("Digite a quantidade de linhas da matriz " + matriz);
        valoresDaMatriz.setQuantidadeDeLinhas(ControleTeclado.obterInteiro());
        System.out.println("Digite a quantidade de colunas da matriz " + matriz);
        valoresDaMatriz.setQuantidadeDeColunas(ControleTeclado.obterInteiro());

        return valoresDaMatriz;
    }

    /**
     * Inicializa a matriz de acordo com os ValoresDaMatriz(quantidades de linhas e colunas) <br>
     *     Lendo do usuario os valores das celulas
     * @param valoresDaMatriz
     * @param matrizId
     * @return
     * @throws IOException
     */
    private Matriz geraMatriz(ValoresDaMatriz valoresDaMatriz, String matrizId) throws IOException {
        System.out.println("Inicializando matriz " + matrizId + ".");
        Matriz matriz = new Matriz();
        for (int i = 0; i < valoresDaMatriz.getQuantidadeDeLinhas(); i++)
            for (int j = 0; j < valoresDaMatriz.getQuantidadeDeColunas(); j++){
                System.out.println("Digite o valor da celula de linha: " + i + " e coluna " + j + ":");
                matriz.addCelula(i, j, ControleTeclado.obterInteiro());
            }

        return matriz;
    }

    public Matriz geraMatrizResultante(Resultado resultado){
        Matriz matriz = new Matriz();
        int cont = 0;
        for (int i = 0; i < valoresDaMatrizA.getQuantidadeDeLinhas(); i++)
            for (int j = 0; j < valoresDaMatrizB.getQuantidadeDeColunas(); j++){
                matriz.addCelula(i, j, resultado.getResultadoTarefa(cont).getResultado());
                cont++;
            }

        return matriz;
    }

    /**
     * Wrapper para a leitura de quantidade de linhas e colunas da matriz
     */
    public class ValoresDaMatriz{
        private int quantidadeDeLinhas, quantidadeDeColunas;

        public int getQuantidadeDeLinhas() {
            return quantidadeDeLinhas;
        }

        public void setQuantidadeDeLinhas(int quantidadeDeLinhas) {
            this.quantidadeDeLinhas = quantidadeDeLinhas;
        }

        public int getQuantidadeDeColunas() {
            return quantidadeDeColunas;
        }

        public void setQuantidadeDeColunas(int quantidadeDeColunas) {
            this.quantidadeDeColunas = quantidadeDeColunas;
        }

        @Override
        public String toString() {
            return "ValoresDaMatriz{" +
                    "quantidadeDeLinhas=" + quantidadeDeLinhas +
                    ", quantidadeDeColunas=" + quantidadeDeColunas +
                    '}';
        }
    }
}
