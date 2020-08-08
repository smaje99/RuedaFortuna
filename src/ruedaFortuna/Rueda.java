package ruedaFortuna;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Rueda extends JFrame implements ChangeListener {
    private JSpinner hora, minuto, segundo;
    private JLabel reloj;
    public Rueda() {
        super("Rueda de la Fortuna");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        init();
        pack();
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        new Rueda().setVisible(true);
    }
    private void init() {
        hora = new JSpinner(new SpinnerNumberModel(0, 0, 24, 1));
        minuto = new JSpinner(new SpinnerNumberModel(0, 0, 60, 1));
        segundo = new JSpinner(new SpinnerNumberModel(0, 0, 60, 1));
        JPanel box = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        box.setBorder(BorderFactory.createTitledBorder("Temporizador (h,m,s)"));
        box.add(hora);
        box.add(minuto);
        box.add(segundo);
        reloj = new JLabel("00:00:00", SwingConstants.CENTER);
        reloj.setFont(new Font(Font.MONOSPACED, Font.BOLD, 72));
        reloj.setBorder(BorderFactory.createEtchedBorder());
        DefaultListModel modelRueda = new DefaultListModel();
        JList list = new JList(modelRueda);
        list.setBorder(BorderFactory.createTitledBorder("Rueda Rusa"));
        JTextArea noti = new JTextArea(5, 14);
        noti.setEditable(false);
        noti.setBorder(BorderFactory.createTitledBorder("Notificaciones"));
        DefaultListModel modelPersona = new DefaultListModel();
        JList espera = new JList(modelPersona);
        espera.setBorder(BorderFactory.createTitledBorder("Jugadores Esperando"));
        JLabel juga = new JLabel("Faltan 0 Jugador(es)", SwingConstants.CENTER);
        JButton button = new JButton("Iniciar");
        button.addActionListener(e -> {
            Reloj r = new Reloj(reloj);
            r.time(hora.getValue().toString(), minuto.getValue().toString(), segundo.getValue().toString());
            r.start();
            Personas personas = new Personas(modelPersona, juga);
            personas.start();
            RuedaRusa rusa = new RuedaRusa(modelPersona, modelRueda, noti, juga);
            Entrar entrar = new Entrar(rusa);
            entrar.start();
        });
        hora.addChangeListener(this);
        minuto.addChangeListener(this);
        segundo.addChangeListener(this);
        juga.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        Constrains.addCompX(box, this, 0, 0, 1, 1, 1, 15, 15, 10, 10, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(button, this, 0, 1, 1, 1, 1, 0, 15, 10, 15, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(reloj, this, 1, 0, 1, 2, 1, 15, 10, 10, 15, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Constrains.addComp(new JScrollPane(espera), this, 0, 2, 1, 2, 1, 1, 10, 15, 10, 10, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        Constrains.addComp(new JScrollPane(noti), this, 1, 2, 1, 1, 1, 1, 10, 10, 10, 15, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        Constrains.addComp(new JScrollPane(list), this, 1, 3, 1, 1, 1, 1, 10, 10, 10, 15, GridBagConstraints.WEST, GridBagConstraints.BOTH);
        Constrains.addCompX(juga, this, 0, 4, 2, 1, 1, 10, 15, 15, 15, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        reloj.setText((((int) hora.getValue() < 10) ? "0" : "") + (int) hora.getValue() + ":" + (((int) minuto.getValue() < 10) ? "0" : "") + (int) minuto.getValue() + ":" + (((int) segundo.getValue() < 10) ? "0" : "") + (int) segundo.getValue());
    }
}