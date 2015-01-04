package br.com.fatechatserver.validadores;

import static br.com.fatechatserver.beans.Servidor.EMISSOR_SERVIDOR;
import br.com.fatechatserver.contratos.Validador;

public class ValidadorNomeIgualServidor implements Validador<String>{

    @Override
    public boolean isValido(String nome) {
        
        return !nome.equalsIgnoreCase(EMISSOR_SERVIDOR);
        
    }
    
}
