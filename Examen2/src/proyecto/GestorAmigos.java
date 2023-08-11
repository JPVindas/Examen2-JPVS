

package proyecto;

import java.util.InputMismatchException;
import java.util.Scanner;
import proyecto.Movimiento;

public class GestorAmigos {
    public static Amigo[] amigos = new Amigo[0]; // Inicialización
    public static Movimiento[] movimientos = new Movimiento[0]; // Inicialización
    private static int contadorAmigos = 0;
    public static final Scanner sc = new Scanner(System.in);
    private String nombre;
    

    public GestorAmigos(String nombre) {
        this.nombre = nombre;
    }

    public static Amigo[] getAmigos() {
        return amigos;
    }

    public static Movimiento[] getMovimientos() {
        return movimientos;
    }

    public static int getContadorAmigos() {
        return contadorAmigos;
    }

    public String getNombre() {
        return nombre;
    }

    public static void setAmigos(Amigo[] amigos) {
        GestorAmigos.amigos = amigos;
    }

    public static void setMovimientos(Movimiento[] movimientos) {
        GestorAmigos.movimientos = movimientos;
    }

    public static void setContadorAmigos(int contadorAmigos) {
        GestorAmigos.contadorAmigos = contadorAmigos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static void verCuentasAmigo() {
        if (amigos == null || amigos.length == 0) {
            System.out.println("No hay amigos registrados.");
            return;
        }

        System.out.println("Selecciona un amigo para ver sus cuentas:");
        for (int i = 0; i < amigos.length; i++) {
            System.out.println((i+1) + ". " + amigos[i].getNombre());
        }

        int indiceSeleccionado;
        while (true) {
            try {
                indiceSeleccionado = sc.nextInt() - 1;
                sc.nextLine(); // Limpia el buffer del scanner

                if (indiceSeleccionado < 0 || indiceSeleccionado >= amigos.length) {
                    System.out.println("Por favor, ingrese un número válido.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                sc.nextLine();
            }
        }

        Amigo amigoSeleccionado = amigos[indiceSeleccionado];
        System.out.println("Cuentas de " + amigoSeleccionado.getNombre() + ":");
        System.out.println("Saldo pendiente: " + amigoSeleccionado.getSaldo());

        if (movimientos != null) { 
            System.out.println("Movimientos en los que " + amigoSeleccionado.getNombre() + " ha participado:");
            boolean tieneMovimientos = false;
            for (Movimiento m : movimientos) {
                for (proyecto.Amigo a : m.getParticipantes()) {
                    if (a.equals(amigoSeleccionado)) {
                        System.out.println("- " + m.getDescripcion() + ": " + m.getMontoTotal());
                        tieneMovimientos = true;
                        break;
                    }
                }
            }
            if (!tieneMovimientos) {
                System.out.println("No hay movimientos registrados para " + amigoSeleccionado.getNombre());
            }
        }
    }

    public static void cargarMovimientosIniciales() {
    if (movimientos == null) {
        movimientos = new Movimiento[0];
    }

    if (amigos == null || amigos.length == 0) {
        System.out.println("Primero debes agregar amigos antes de registrar movimientos.");
        return;
    }

    System.out.println("¿Cuántos movimientos iniciales deseas agregar?");
    int cantidadMovimientos;
    while (true) {
        try {
            cantidadMovimientos = sc.nextInt();
            sc.nextLine(); 
            break;
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un número válido.");
            sc.nextLine(); 
        }
    }

    Movimiento[] nuevosMovimientos = new Movimiento[cantidadMovimientos];
    for (int i = 0; i < cantidadMovimientos; i++) {
        System.out.println("Introduce la descripción del movimiento " + (i + 1) + ":");
        String descripcion = sc.nextLine();
        System.out.println("Introduce el monto total del movimiento:");
        double montoTotal = sc.nextDouble();
        sc.nextLine(); 

        
        Amigo quienPago = amigos[0];

        nuevosMovimientos[i] = new Movimiento(descripcion, amigos, quienPago, montoTotal, descripcion);
    }

    // Combinamos los arrays de movimientos
    Movimiento[] combinado = new Movimiento[movimientos.length + cantidadMovimientos];
    System.arraycopy(movimientos, 0, combinado, 0, movimientos.length);
    System.arraycopy(nuevosMovimientos, 0, combinado, movimientos.length, nuevosMovimientos.length);
    movimientos = combinado;
}

    static void agregarAmigo() {
    System.out.println("Introduce el nombre del amigo:");
    String nombre = sc.nextLine();
    System.out.println("Introduce el saldo inicial:");
    double saldo = sc.nextDouble();
    sc.nextLine(); // Limpia el buffer del scanner

    Amigo nuevoAmigo = new Amigo(nombre, saldo);

    // Expandir el array de amigos para agregar el nuevo amigo
    Amigo[] temp = new Amigo[amigos.length + 1];
    for (int i = 0; i < amigos.length; i++) {
        temp[i] = amigos[i];
    }
    temp[amigos.length] = nuevoAmigo;
    amigos = temp;
}

    static void registrarMovimiento() {
    // Usamos el scanner global en lugar de crear uno nuevo
    System.out.println("Registra un nuevo movimiento.");

    // Descripción del movimiento
    System.out.print("Introduce la descripción del movimiento: ");
    String descripcion = sc.nextLine();

    // Lista de amigos
    System.out.println("Selecciona a los amigos involucrados (introduce los números separados por comas):");
    for (int i = 0; i < amigos.length; i++) {
        System.out.println((i + 1) + ". " + amigos[i].getNombre());
    }

    // Obtener amigos seleccionados
    String[] seleccionados = sc.nextLine().split(",");
    Amigo[] amigosSeleccionados = new Amigo[seleccionados.length];
    for (int i = 0; i < seleccionados.length; i++) {
        int indice = Integer.parseInt(seleccionados[i].trim()) - 1; 
        amigosSeleccionados[i] = amigos[indice];
    }

    // Determinar quién pagó
    System.out.println("¿Quién pagó?");
    for (int i = 0; i < amigosSeleccionados.length; i++) {
        System.out.println((i + 1) + ". " + amigosSeleccionados[i].getNombre());
    }
    int indiceQuienPago = sc.nextInt() - 1;
    Amigo amigoQuePago = amigosSeleccionados[indiceQuienPago];
    sc.nextLine();  

    // Monto total del movimiento
    System.out.print("Introduce el monto total: ");
    double monto = sc.nextDouble();
    sc.nextLine();  

    // Crear el nuevo movimiento
    Movimiento nuevoMovimiento = new Movimiento(descripcion, amigos, amigoQuePago, monto, descripcion);

    // Agregar el movimiento al array de movimientos
    Movimiento[] nuevosMovimientos = new Movimiento[movimientos.length + 1];
    System.arraycopy(movimientos, 0, nuevosMovimientos, 0, movimientos.length);
    nuevosMovimientos[movimientos.length] = nuevoMovimiento;
    movimientos = nuevosMovimientos;

    System.out.println("Movimiento registrado exitosamente!");
}


    static void cargarAmigosIniciales() {
    if (amigos.length == 0) { // Cambio la condición para verificar si el arreglo está vacío.
        amigos = new Amigo[]{
            new Amigo("Guillermo", 0.0),
            new Amigo("Tavo", 0.0),
            new Amigo("David", 0.0),
            new Amigo("Greivin", 0.0),
            new Amigo("Joshua", 0.0),
            new Amigo("Andres", 0.0)
        };
      }
    }
}


