package br.com.fatechatserver.beans;

import java.io.PrintWriter;
import java.util.Scanner;

public class Cliente {
    
    public static final String NOME_INDEFINIDO = "";

    private PrintWriter escritor;
    private Scanner leitor;
    private String nome;

    public Cliente(PrintWriter escritor, Scanner leitor, String nome) {
        this.escritor = escritor;
        this.leitor = leitor;
        this.nome = nome;
    }
    
    
    public PrintWriter getEscritor() {
        return escritor;
    }

    public void setEscritor(PrintWriter escritor) {
        this.escritor = escritor;
    }

    public Scanner getLeitor() {
        return leitor;
    }

    public void setLeitor(Scanner leitor) {
        this.leitor = leitor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public boolean isNomeIndefinido(){
        
        return nome.equalsIgnoreCase(NOME_INDEFINIDO);
    }

}
