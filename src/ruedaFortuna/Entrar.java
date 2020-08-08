package ruedaFortuna;
public class Entrar extends Thread {
    private RuedaRusa rusa;
    public Entrar(RuedaRusa rusa) {
        this.rusa = rusa;
    }
    @Override
    public void run() {
        //Mientras el reloj este en funcionamiento se va a tratar de llenar la rueda hasta su limite
        while (Reloj.funciona) {
            rusa.entrar();
        }
    }
}