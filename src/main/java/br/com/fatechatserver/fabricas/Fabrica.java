package br.com.fatechatserver.fabricas;

import br.com.fatechatserver.beans.Cliente;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public abstract class Fabrica  {

    
    public static Cliente fabricarCliente(Socket socketCliente) throws Exception {

        PrintWriter escritor = new PrintWriter(socketCliente.getOutputStream());
        Scanner leitor = new Scanner(socketCliente.getInputStream());
        Cliente cliente = new Cliente(escritor, leitor, Cliente.NOME_INDEFINIDO);

        return cliente;
    }

}
