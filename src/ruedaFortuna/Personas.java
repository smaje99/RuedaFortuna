package ruedaFortuna;
import javax.swing.*;
public class Personas extends Thread {
    private DefaultListModel model;
    private JLabel juga;
    private int jugador;
    public Personas(DefaultListModel model, JLabel juga) {
        this.model = model;
        this.juga = juga;
        jugador = 1;
    }
    @Override
    public void run() {
        while (Reloj.funciona) {
            //Mientras el reloj funcione se generaran nuevos jugadores
            model.addElement("Jugador " + (jugador++));
            juga.setText("Faltan " + model.size() + " Jugador(es)");
            try {
                //Espera entre un rango de 1.57s y 2.57s para crear un nuevo jugador
                Thread.sleep((long) (Math.random() * 1000) + 1570);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}