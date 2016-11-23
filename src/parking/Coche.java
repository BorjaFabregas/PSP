package parking;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Borja Fabregas
 */
public class Coche extends Thread {

    private final int tiempo;
    private final Semaphore s;

    public Coche(Semaphore s, int tiempo) {
        this.s = s;
        this.tiempo = tiempo;
    }

    public void aparcar() {
        System.out.println("Coche aparcado, quedan " + s.availablePermits()+ " plazas libres");
        try {
            sleep(tiempo * 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void salir() {
        System.out.println("Un coche sale del aparcamiento, quedan "+ (s.availablePermits() + 1) + " plazas libres");
    }

    @Override
    public void run() {
        try {
            s.acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        aparcar();
        salir();
        s.release();
    }

}

class Parking {

    public static void main(String[] args) {
        Semaphore s = new Semaphore(5);
        Coche[] coches = new Coche[10];
        for (int i = 0; i < coches.length; i++) {
            coches[i] = new Coche(s, i);
            coches[i].start();
        }
    }
}
