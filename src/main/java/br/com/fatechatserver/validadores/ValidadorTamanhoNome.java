package br.com.fatechatserver.validadores;

import br.com.fatechatserver.contratos.Validador;

public class ValidadorTamanhoNome implements Validador<String> {

    @Override
    public boolean isValido(String nome) {

        return !((nome.length() > 12)
                || nome.isEmpty());
    }

}
