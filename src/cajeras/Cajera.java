package cajeras;

/**
 *
 * @author Borja Fabregas
 */
class Cliente {

    private int[] carroCompra;
    private String nombre;

    public Cliente(String nombre, int[] compra) {
        this.nombre = nombre;
        this.carroCompra = compra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int[] getCarroCompra() {
        return carroCompra;
    }

    public void setCarroCompra(int[] carroCompra) {
        this.carroCompra = carroCompra;
    }
}

public class Cajera extends Thread {

    private String nombre;
    private Cliente cliente;
    private long tiempoInicial;

    public Cajera(String nombre, Cliente cliente, long tiempoInicio) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.tiempoInicial = tiempoInicio;
    }

    @Override
    public void run() {
        
        for (int i = 0; i < cliente.getCarroCompra().length; i++) {
            try {
                Thread.sleep(cliente.getCarroCompra()[i]*1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }            
            System.out.println(this.nombre + "  cobra el producto " + (i + 1) + " del cliente " + cliente.getNombre() + " y lleva " + (System.currentTimeMillis() - this.tiempoInicial) / 1000 + "segundos");
        }
        System.out.println(this.nombre + " ha acabado de cobrar en " + (System.currentTimeMillis() - this.tiempoInicial) / 1000 + " segundos.");
    }

}

class main {

    public static void main(String[] args) {
        long tiempoInicial = System.currentTimeMillis();
        Cliente borja = new Cliente("Borja", new int[]{1, 4, 2});
        Cliente pepe = new Cliente("Pepe", new int[]{2, 2, 1});
        Cajera paqui = new Cajera("Paqui", borja, tiempoInicial);
        Cajera loli = new Cajera("Loli", pepe, tiempoInicial);
        
        System.out.println("SUPERMERCADOS EUSA");
        
        paqui.start();
        loli.start();
    }
}
