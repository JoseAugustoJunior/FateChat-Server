package br.com.fatechatserver.abstracoes;

import br.com.fatechatserver.beans.Cliente;
import java.io.IOException;

public abstract class Distribuidor extends Thread {
    
    protected final Cliente cliente;

    public Distribuidor(Cliente cliente) throws IOException {
        this.cliente = cliente;
    }

    @Override
    public abstract void run();
          
    
}
