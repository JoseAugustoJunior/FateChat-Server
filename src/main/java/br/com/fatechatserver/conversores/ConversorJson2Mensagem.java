package br.com.fatechatserver.conversores;

import br.com.fatechatserver.beans.Mensagem;
import br.com.fatechatserver.contratos.Conversor;
import com.google.gson.Gson;

public class ConversorJson2Mensagem implements Conversor<String, Mensagem>{

    @Override
    public Mensagem converter(String json) throws Exception {
        
        Gson gson = new Gson();
        
        Mensagem msg = gson.fromJson(json, Mensagem.class);
        
        return msg;
    }
    
    
    
}
