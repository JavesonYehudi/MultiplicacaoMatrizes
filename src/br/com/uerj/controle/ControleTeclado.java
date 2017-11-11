package br.com.uerj.controle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ControleTeclado {

    private static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

    public static String obterTexto() throws IOException {
        return entrada.readLine();
    }

    public static int obterInteiro() throws IOException, NumberFormatException {
        return new Integer(obterTexto());
    }

    public static float obterFlutuante() throws IOException {
        return new Float(obterTexto());
    }

}