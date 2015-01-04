package br.com.fatechatserver.contratos;

public interface Conversor<T, E> {
    
    E converter(T obj) throws Exception;
    
}
