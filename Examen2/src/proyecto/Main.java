package proyecto;
//Indicaciones para el correcto funcionamiento del programa.
//Para correr el proyecto se debe decir que la cantidad inicial de movimientos debe  ser de 0 para q así lo movimientos iniciales se establezcan en 0 
//Posteriormente si se quiere agregar amigos se agregaran a la lista
//Luego al entrar a las cuentas de los amigos se registra los movimientos ue se han hecho 

public class Main {

    public static void main(String[] args) {
        GestorAmigos.cargarAmigosIniciales();
        GestorAmigos.cargarMovimientosIniciales();

        mostrarAmigosYSaldo();  // Llamada al nuevo método

        mostrarMenu();
    }

    // Nuevo método para mostrar los amigos y su saldo
    private static void mostrarAmigosYSaldo() {
        System.out.println("Amigos cargados inicialmente:");
        for (Amigo amigo : GestorAmigos.getAmigos()) {  
            System.out.printf("%s tiene un saldo de: %s\n", amigo.getNombre(), amigo.getSaldo());
        }
    }

    private static void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Agregar Amigo");
            System.out.println("2. Registrar Movimiento");
            System.out.println("3. Ver cuentas de un Amigo");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = GestorAmigos.sc.nextInt();
                GestorAmigos.sc.nextLine(); 

                switch (opcion) {
                    case 1:
                        GestorAmigos.agregarAmigo();
                        break;
                    case 2:
                        GestorAmigos.registrarMovimiento();
                        break;
                    case 3:
                        GestorAmigos.verCuentasAmigo();
                        break;
                    case 4:
                        System.out.println("Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error en la entrada. Por favor, ingrese un número válido.");
                GestorAmigos.sc.nextLine(); // Reset the scanner
                opcion = 0;
            }
        } while (opcion != 4);
    }
}
