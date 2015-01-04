package br.com.fatechatserver.enviadores;

import br.com.fatechatserver.abstracoes.Enviador;
import br.com.fatechatserver.beans.Mensagem;
import br.com.fatechatserver.beans.Servidor;
import br.com.fatechatserver.contratos.Validador;
import br.com.fatechatserver.enuns.ValidadoresNomeEnum;
import java.util.Arrays;

public class EnviadorNomes extends Enviador{

    public EnviadorNomes(Servidor servidor) {
        super(servidor);
    }

    @Override
    public void enviar(Mensagem msg) {
        
       
        ValidadoresNomeEnum[] validadores = ValidadoresNomeEnum.values();
        for (ValidadoresNomeEnum validador : validadores) {
            boolean isValido = validador.get().isValido(msg.getNomeEmissor());
            
            if(!isValido){
                servidor.removerClienteDaListaPorNome(msg.getNomeEmissor());
                return;
            }        
        }     
        
        servidor.notificarEntradaDoCliente(msg.getNomeEmissor());
    }
    
}
