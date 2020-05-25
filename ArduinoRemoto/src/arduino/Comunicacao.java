package arduino;

import javax.swing.JOptionPane;
import processing.serial.*;
import processing.core.*;
import tela.TelaPrincipal;

public class Comunicacao extends PApplet{
    
    Serial serial;
    
    public static String[] listarPortas(){
        return Serial.list();
    }
    
    public boolean conectar(TelaPrincipal tela, String porta, int velocidade){
        try{
            serial = new Serial(this, porta, velocidade);
            return true;
        } catch (RuntimeException n){
            JOptionPane.showMessageDialog(tela, "Erro ao se conectar com a porta \"" + porta + "\"", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public void desconectar(String porta, int velocidade){
        try{
            serial = new Serial(this, porta, velocidade);
            serial.dispose();
        } catch (RuntimeException n){
        }
    }
    
    public String ler(){
        if (serial.available() > 0) {
            return serial.readString();
        }
        return null;
    }
}
