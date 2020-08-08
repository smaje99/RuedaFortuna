package ruedaFortuna;
import javax.swing.*;
public class RuedaRusa {
    private DefaultListModel modelPersona, modelRueda;
    private JTextArea noti;
    private JLabel juga;
    public RuedaRusa(DefaultListModel modelPersona, DefaultListModel modelRueda, JTextArea noti, JLabel juga) {
        this.modelPersona = modelPersona;
        this.modelRueda = modelRueda;
        this.noti = noti;
        this.juga = juga;
    }
    public synchronized void entrar() {
        //Por medio de este método sincronizado un jugador encolado entra a la rueda
        if (!modelPersona.isEmpty() && modelRueda.size() < 5) {
            modelRueda.addElement(modelPersona.remove(0));
            noti.insert("Entró " + modelRueda.lastElement() + '\n', 0);
            juga.setText("Faltan " + modelPersona.size() + " Jugador(es)");
            Salir salir = new Salir(noti, modelRueda);
            salir.start();
        }
    }
}