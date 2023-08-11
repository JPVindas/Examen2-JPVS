package proyecto;

public class Movimiento {
    private String descripcion;
    private Amigo[] participantes;
    private Amigo quienPago;
    private double montoTotal;
    private String moneda;

    public Movimiento(String descripcion, Amigo[] participantes, Amigo quienPago, double montoTotal, String moneda) {
        this.descripcion = descripcion;
        this.participantes = participantes;
        this.quienPago = quienPago;
        this.montoTotal = montoTotal;
        this.moneda = moneda;
        actualizarSaldos();
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Amigo[] getParticipantes() {
        return participantes;
    }

    public Amigo getQuienPago() {
        return quienPago;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setParticipantes(Amigo[] participantes) {
        this.participantes = participantes;
    }

    public void setQuienPago(Amigo quienPago) {
        this.quienPago = quienPago;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    private void actualizarSaldos() {
        if (participantes == null || participantes.length == 0) {
            return; // No actualizamos si no hay participantes
        }

        double montoPorAmigo = montoTotal / participantes.length;
        for (Amigo a : participantes) {
            if (a.getNombre().equals(quienPago.getNombre())) {
                a.actualizarSaldo(montoTotal - montoPorAmigo);
            } else {
                a.actualizarSaldo(-montoPorAmigo);
            }
        }
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "descripcion='" + descripcion + '\'' +
                ", quienPago=" + (quienPago != null ? quienPago.getNombre() : "N/A") + 
                ", montoTotal=" + montoTotal +
                '}';
    }
}