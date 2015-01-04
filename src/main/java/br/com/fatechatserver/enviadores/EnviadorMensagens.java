package br.com.fatechatserver.enviadores;

import br.com.fatechatserver.abstracoes.Enviador;
import br.com.fatechatserver.beans.Mensagem;
import br.com.fatechatserver.beans.Servidor;

public class EnviadorMensagens extends Enviador {

    public EnviadorMensagens(Servidor servidor) {
        super(servidor);
    }

    @Override
    public void enviar(Mensagem mensagem) {

        servidor.EnviarBroadcast(mensagem);
        System.out.println(mensagem.getNomeEmissor() + ": " + mensagem.getConteudo());

    }

}
