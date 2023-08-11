package proyecto;

import java.util.Scanner;
import proyecto.Amigo;

public class Aplicacion {
    private static Amigo[] amigos = new Amigo[0];
    private static Movimiento[] movimientos = new Movimiento[0];

    private static void cargarAmigosIniciales() {
        amigos = new Amigo[]{new Amigo("Guillermo"), new Amigo("Tavo"), new Amigo("David"), new Amigo("Greivin"), new Amigo("Joshua"), new Amigo("Andres")};
    }

    private static void cargarMovimientosIniciales() {
        movimientos = new Movimiento[]{new Movimiento("Comida", new Amigo[]{amigos[0], amigos[1]}, amigos[0], 10, "Dólares")};
    }

    private static void agregarAmigo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre del nuevo amigo: ");
        String nombre = sc.nextLine();

        if (nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        Amigo[] nuevoArray = new Amigo[amigos.length + 1];
        System.arraycopy(amigos, 0, nuevoArray, 0, amigos.length);
        nuevoArray[amigos.length] = new Amigo(nombre);
        amigos = nuevoArray;

        System.out.println(nombre + " ha sido agregado!");
    }

    private static void registrarMovimiento() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la descripcion del movimiento: ");
        String descripcion = sc.nextLine();

        if (descripcion.trim().isEmpty()) {
            System.out.println("La descripción no puede estar vacía.");
            return;
        }

        System.out.println("Seleccione a los amigos que participaron (separados por coma):");
        for (int i = 0; i < amigos.length; i++) {
            System.out.println((i + 1) + ". " + amigos[i].getNombre());
        }

        String[] indicesSeleccionados = sc.nextLine().split(",");
        Amigo[] participantes = new Amigo[indicesSeleccionados.length];
        for (int i = 0; i < indicesSeleccionados.length; i++) {
            try {
                participantes[i] = amigos[Integer.parseInt(indicesSeleccionados[i].trim()) - 1];
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Entrada no válida.");
                return;
            }
        }

        System.out.println("¿Quién pagó?");
        for (int i = 0; i < amigos.length; i++) {
            System.out.println((i + 1) + ". " + amigos[i].getNombre());
        }

        int indiceQuienPago;
        try {
            indiceQuienPago = Integer.parseInt(sc.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida.");
            return;
        }

        System.out.print("¿Cuál fue el monto total? ");
        double montoTotal;
        try {
            montoTotal = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida para el monto.");
            return;
        }

        Movimiento nuevoMovimiento = new Movimiento(descripcion, participantes, amigos[indiceQuienPago], montoTotal, "Dólares");
        Movimiento[] nuevoArrayMovimientos = new Movimiento[movimientos.length + 1];
        System.arraycopy(movimientos, 0, nuevoArrayMovimientos, 0, movimientos.length);
        nuevoArrayMovimientos[movimientos.length] = nuevoMovimiento;
        movimientos = nuevoArrayMovimientos;

        System.out.println("Movimiento registrado exitosamente!");
    }

    private static void verCuentasAmigo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione a un amigo:");
        for (int i = 0; i < amigos.length; i++) {
            System.out.println((i + 1) + ". " + amigos[i].getNombre());
        }

        int indiceSeleccionado;
        try {
            indiceSeleccionado = Integer.parseInt(sc.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida.");
            return;
        }

        if (indiceSeleccionado < 0 || indiceSeleccionado >= amigos.length) {
            System.out.println("Índice fuera de rango.");
            return;
        }

        Amigo a = amigos[indiceSeleccionado];
        System.out.println("\nCuentas de " + a.getNombre() + ": " + a.getSaldo());
    }
}
