package br.com.fatechatserver.abstracoes;

import br.com.fatechatserver.beans.Mensagem;
import br.com.fatechatserver.beans.Servidor;

public abstract class Enviador {

    protected final Servidor servidor;
    
    public Enviador(Servidor servidor) {
        this.servidor = servidor;
    }
   
    
    public abstract void enviar(Mensagem mensagem);
    
        
}
