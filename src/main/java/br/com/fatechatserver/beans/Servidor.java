package br.com.fatechatserver.beans;

import br.com.fatechatserver.enuns.TipoMensagem;
import br.com.fatechatserver.fabricas.Fabrica;
import br.com.fatechatserver.impl.DistribuidorImpl;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

    public static final int PORTA_PADRAO = 5000;
    public static final String EMISSOR_SERVIDOR = "SERVIDOR";

    @SuppressWarnings("FieldMayBeFinal")
    private List<Cliente> clientes;

    private final ServerSocket servidorSocket;

    public Servidor() throws IOException {

        servidorSocket = new ServerSocket(PORTA_PADRAO);
        clientes = new ArrayList<>();

    }

    public void subir() throws Exception {

        System.out.println("Servidor escutando na porta " + servidorSocket.getLocalPort() + " ...");

        while (true) {

            System.out.println("Aguardando nova conexão ao servidor.");
            Socket socketCliente = servidorSocket.accept();
            System.out.println("O IP " + socketCliente.getInetAddress().getHostAddress()
                    + " enviou requisição para participar do chat.");

            Cliente cliente = Fabrica.fabricarCliente(socketCliente);
            clientes.add(cliente);

            new Thread(new DistribuidorImpl(cliente, this)).start();
        }

    }

    public void EnviarBroadcast(Mensagem mensagem) {

        clientes.stream()
                .forEach(c -> {
                    c.getEscritor().println(mensagem.toJson());
                    c.getEscritor().flush();
                });

    }

    public void notificarEntradaDoCliente(String nomeCliente) {

        Mensagem mensagem = new Mensagem(TipoMensagem.TEXTO,
                nomeCliente + " conectou-se ao chat.", EMISSOR_SERVIDOR);

        clientes.stream()
                .filter(c -> !c.getNome().equals(nomeCliente))
                .forEach(c -> {
                    c.getEscritor().println(mensagem.toJson());
                    c.getEscritor().flush();
                });

    }

    public void notificarSaidaDoCliente(String nomeCliente) {

        Mensagem mensagem = new Mensagem(TipoMensagem.TEXTO,
                nomeCliente + " desconectou-se do chat.", EMISSOR_SERVIDOR);

        clientes.stream()
                .filter(c -> !c.getNome().equals(nomeCliente))
                .forEach(c -> {
                    c.getEscritor().println(mensagem.toJson());
                    c.getEscritor().flush();
                });

    }

    public void addClienteNaLista(Cliente cliente) {
        clientes.add(cliente);
    }

    public void removerClienteDaLista(Cliente cliente) {
        clientes.remove(cliente);
    }
    
    public void removerClienteDaListaPorNome(String nome){
        clientes.removeIf(c -> c.getNome().equals(nome));
    }
    

}
