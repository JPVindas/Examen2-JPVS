package proyecto;

public class Amigo {
    private String nombre;
    private double saldo;

    // Constructor que solo toma el nombre y asigna un saldo de 0 por defecto
    public Amigo(String nombre) {
        this(nombre, 0.0);
    }

    public Amigo(String nombre, double saldo) {
        this.nombre = nombre;
        this.saldo = saldo;
    }
    
    public String getNombre() {
        return nombre;
    }

    // Este método actualiza el saldo
    // Si el monto es positivo, aumenta el saldo. Si es negativo, disminuye el saldo.
    public void actualizarSaldo(double monto) {
        this.saldo += monto;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return "Amigo{" +
                "nombre='" + nombre + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
