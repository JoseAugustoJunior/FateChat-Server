package br.com.fatechatserver.main;

import br.com.fatechatserver.beans.Servidor;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        try {
            Servidor servidor = new Servidor();
            JOptionPane.showMessageDialog(null, "Servidor subindo ...");
            servidor.subir();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro no Servidor: " 
                    + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

}
