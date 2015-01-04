package br.com.fatechatserver.enuns;

import br.com.fatechatserver.contratos.Validador;
import br.com.fatechatserver.validadores.ValidadorNomeIgualServidor;
import br.com.fatechatserver.validadores.ValidadorTamanhoNome;

public enum ValidadoresNomeEnum {
    
    NOME_IGUAL_DO_SERVIDOR {
        @Override
        public Validador get() {
            return new ValidadorNomeIgualServidor();
        }
    },
    TAMANHO_DO_NOME {
        @Override
        public Validador get() {
            return new ValidadorTamanhoNome();
        }
    };
    
    
    public abstract Validador get();
    
}
