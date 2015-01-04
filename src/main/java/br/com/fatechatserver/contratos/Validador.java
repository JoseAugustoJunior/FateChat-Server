
package br.com.fatechatserver.contratos;

public interface Validador<E> {
    
    boolean isValido(E obj);
    
}
