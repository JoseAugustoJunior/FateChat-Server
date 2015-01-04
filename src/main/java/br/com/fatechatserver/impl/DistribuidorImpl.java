package br.com.fatechatserver.impl;

import br.com.fatechatserver.abstracoes.Distribuidor;
import br.com.fatechatserver.abstracoes.Enviador;
import br.com.fatechatserver.beans.Cliente;
import br.com.fatechatserver.beans.Mensagem;
import br.com.fatechatserver.beans.Servidor;
import br.com.fatechatserver.contratos.Conversor;
import br.com.fatechatserver.contratos.Validador;
import br.com.fatechatserver.conversores.ConversorJson2Mensagem;
import br.com.fatechatserver.enuns.TipoMensagem;
import br.com.fatechatserver.enuns.ValidadoresNomeEnum;
import br.com.fatechatserver.execoes.ValidacaoException;
import java.io.IOException;
import java.util.NoSuchElementException;

public class DistribuidorImpl extends Distribuidor {

    private final Servidor servidor;
    private final Conversor<String, Mensagem> j2m;

    public DistribuidorImpl(Cliente cliente, Servidor servidor)
            throws IOException {
        super(cliente);
        this.servidor = servidor;
        this.j2m = new ConversorJson2Mensagem();
    }

    @Override
    @SuppressWarnings("null")
    public void run() {

        @SuppressWarnings("UnusedAssignment")
        String jsonEnviado = null;
        Mensagem msg = null;

        try {
            while ((jsonEnviado = cliente.getLeitor().nextLine()) != null) {

                msg = j2m.converter(jsonEnviado);

                if (msg.getTipo() == TipoMensagem.NOME) {
                    cliente.setNome(msg.getNomeEmissor());
                    ValidarNome(msg);
                }

                Enviador enviador = msg.getTipo().getEnviador(servidor);
                enviador.enviar(msg);

            }
        } catch (ValidacaoException e3) {
            System.err.println(e3.getMessage());
            servidor.notificarSaidaDoCliente(msg.getNomeEmissor());
            servidor.removerClienteDaLista(cliente);
        } catch (NoSuchElementException e2) {
            servidor.notificarSaidaDoCliente(msg.getNomeEmissor());
            servidor.removerClienteDaLista(cliente);
        } catch (Exception e) {
            System.err.println("Erro ao enviar mesagens: " + e.getMessage());
        }

        //TODO: rever catchs
    }

    private void ValidarNome(Mensagem mensagem) throws ValidacaoException {

        ValidadoresNomeEnum[] validadores = ValidadoresNomeEnum.values();

        for (ValidadoresNomeEnum validadorEnum : validadores) {
            Validador<String> validador = validadorEnum.get();
            if (!validador.isValido(mensagem.getNomeEmissor())) {
                throw new ValidacaoException(validador.getClass()
                        .getSimpleName() + " -  " + mensagem.getNomeEmissor()
                        + " Rejeitado na validaçao");
            }
        }

    }

}
