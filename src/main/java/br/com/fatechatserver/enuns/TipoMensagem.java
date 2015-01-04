package br.com.fatechatserver.enuns;

import br.com.fatechatserver.abstracoes.Enviador;
import br.com.fatechatserver.beans.Servidor;
import br.com.fatechatserver.enviadores.EnviadorMensagens;
import br.com.fatechatserver.enviadores.EnviadorNomes;

public enum TipoMensagem {
    
    TEXTO {
        @Override
        public Enviador getEnviador(final Servidor servidor) {            
            return new EnviadorMensagens(servidor);
        }

    }, NOME {
        @Override
        public Enviador getEnviador(final Servidor servidor) {
            return new EnviadorNomes(servidor);
        }     
    };
 
    
    public abstract Enviador getEnviador(final Servidor servidor);
    
}
