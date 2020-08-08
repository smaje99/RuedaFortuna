package ruedaFortuna;
import javax.swing.*;
public class Reloj extends Thread {
    private JLabel label;
    private int hora, min, seg;
    private boolean ac;
    public static boolean funciona = true;//por medio de esta variable estatica se detendrá todo el programa o seguira funcionando
    public Reloj(JLabel label) {
        this.label = label;
        hora = min = seg = 0;
        ac = true;
    }
    public void time(String... time) {
        //recibe un arreglo en los parametros y se ubican a sus repectivas correspondientes
        hora = Integer.parseInt(time[0]);
        min = Integer.parseInt(time[1]);
        seg = Integer.parseInt(time[2]);
    }
    @Override
    public void run() {
        //Temporizador con formatos de nuemeros cero
        for (int i = hora; i >= 0; i--) {
            for (int j = (ac) ? min : 59; j >= 0; j--) {
                for (int k = (ac) ? seg : 59; k >= 0; k--) {
                    label.setText(((i < 10) ? "0" : "") + i + ":" + ((j < 10) ? "0" : "") + j + ":" + ((k < 10) ? "0" : "") + k);
                    try {
                        ac = false;
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
        Reloj.funciona = false;
    }
}