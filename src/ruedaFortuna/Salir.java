package ruedaFortuna;
import javax.swing.*;
public class Salir extends Thread {
    private JTextArea noti;
    private DefaultListModel modelRueda;
    public Salir(JTextArea noti, DefaultListModel modelRueda) {
        this.noti = noti;
        this.modelRueda = modelRueda;
    }
    @Override
    public void run() {
        if (!modelRueda.isEmpty()) {
            try {
                long i = (long) ((Math.random() * 2.45) * 10000) + 3850;
                Thread.sleep(i);
                if (Reloj.funciona) noti.insert("Salió " + modelRueda.remove(0) + '\n', 0);
            } catch (InterruptedException e) {

            }
        }
    }
}