package app;//organizo mi clase dentro del paquete app

// Importo las clases que voy a necesitar de otros paquetes
import modelo.Cooperativa;
import modelo.Socio;
import modelo.CuentaAhorros;
import transacciones.Deposito;
import transacciones.Retiro;
import modelo.Cuenta;



// Importo librerías para manejar ventanas emergentes


import javax.swing.JOptionPane;// JOptionPane lo uso para mostrar ventanas emergentes
public class App { //Clase principaldonde arranctodo el programa.
    public static void main(String[] args) { // Metodo main = punto de inicio del programa

        Cooperativa coop = new Cooperativa();// Creo un objeto cooperativa que va a guardar todos los socios y sus cuentas

        int opcion;// Variable para guardar lo que el usuario elija del menu
        do { // Uso un do-while para que el menu se repita hasta que se elija salir


            // Aquí está mi menu en un bloque de texto para que se vea mas organizado
            String menu = """
                        --- MENU COOPERATIVA ---
                        1. Registrar socio
                        2. Abrir cuenta de ahorros
                        3. Realizar deposito
                        4. Realizar retiro
                        5. Listar socios (Stream)
                        6. Filtrar cuentas con saldo > 500000 (Stream)
                        7. Total de saldos en la cooperativa (Stream)
                        8. Intereses generados en cuentas de ahorro
                        9. Salir
                        ->Seleccione una opcion<-:
                        """;

            // Muestro el menú con JOptionPane y guardo la opción en un int
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            // Switch para decidir qué hacer segun la opcion elegida
            switch (opcion) {
                // Opción 1: en la opcion uno registramos a un socio
                case 1 -> {

                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del socio:"); // Pido el nombre
                    String cedula = JOptionPane.showInputDialog("Ingrese la cedula del socio:"); // Pido la cedula
                    Socio socio = new Socio(nombre, cedula);// Creo el socio
                    coop.registrarSocio(socio);// Lo registro en la cooperativa
                    JOptionPane.showMessageDialog(null, "Socio registrado exitosamente.");
                }

                // Opción 2: Abrir cuenta de ahorros
                case 2 -> {
                    String cedula = JOptionPane.showInputDialog("Ingrese la cedula del socio:"); // Pido la cédula para buscar al socio
                    Socio socio = coop.getSocios().stream()
                            .filter(s -> s.getCedula().equals(cedula))// Busco al socio con esa cédula
                            .findFirst()
                            .orElse(null);// Si no lo encuentro, devuelve null

                    if (socio != null) { // Si el socio existe
                        String numCuenta = JOptionPane.showInputDialog("Numero de cuenta:");
                        double saldo = Double.parseDouble(JOptionPane.showInputDialog("Saldo inicial:"));
                        double interes = Double.parseDouble(JOptionPane.showInputDialog("Interes (ej: 0.02):"));
                        // Le abro una cuenta de ahorros al socio
                        coop.abrirCuenta(socio, new CuentaAhorros(numCuenta, saldo, interes));
                        JOptionPane.showMessageDialog(null, "Cuenta creada correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Socio no encontrado.");
                    }
                }
                // Opción 3: Realizar depósito
                case 3 -> {
                    String cedula = JOptionPane.showInputDialog("Cedula del socio:");
                    String numCuenta = JOptionPane.showInputDialog("Numero de cuenta:");
                    double monto = Double.parseDouble(JOptionPane.showInputDialog("Monto a depositar:"));
                    // Busco al socio
                    Socio socio = coop.getSocios().stream()
                            .filter(s -> s.getCedula().equals(cedula))
                            .findFirst()
                            .orElse(null);
                    // Busco la cuenta del socio
                    if (socio != null) {
                        Cuenta cuenta = socio.getCuentas().stream()
                                .filter(c -> c.getNumeroCuenta().equals(numCuenta))
                                .findFirst()
                                .orElse(null);

                        if (cuenta != null) {
                            new Deposito(cuenta, monto).ejecutar();// Hago el depósito
                            JOptionPane.showMessageDialog(null, "💰 Deposito exitoso.\nSaldo actual: " + cuenta.getSaldo());
                        } else {
                            JOptionPane.showMessageDialog(null, "Cuenta no encontrada.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Socio no encontrado.");
                    }
                }
                // Opción 4: Realizar retiro
                case 4 -> {
                    String cedula = JOptionPane.showInputDialog("Cedula del socio:");
                    String numCuenta = JOptionPane.showInputDialog("Numero de cuenta:");
                    double monto = Double.parseDouble(JOptionPane.showInputDialog("Monto a retirar:"));
                    // Busco al socio
                    Socio socio = coop.getSocios().stream()
                            .filter(s -> s.getCedula().equals(cedula))
                            .findFirst()
                            .orElse(null);
                    // Busco la cuenta
                    if (socio != null) {
                        Cuenta cuenta = socio.getCuentas().stream()
                                .filter(c -> c.getNumeroCuenta().equals(numCuenta))
                                .findFirst()
                                .orElse(null);

                        if (cuenta != null) {
                            new Retiro(cuenta, monto).ejecutar();// Hago el retiro
                            JOptionPane.showMessageDialog(null, "\nSaldo actual: " + cuenta.getSaldo());
                        } else {
                            JOptionPane.showMessageDialog(null, "Cuenta no encontrada.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Socio no encontrado.");
                    }
                }
                // Opción 5: Listar socios usando Stream
                case 5 -> {
                    String socios = coop.getSocios().stream()
                            .map(Socio::getNombre)// Con map saco solo el nombre
                            .reduce("", (a, b) -> a + "\n" + b);// Con reduce unotodo en un string

                    JOptionPane.showMessageDialog(null, "Lista de socios:\n" + socios);
                }
                // Opción 6: Filtrar cuentas con saldo mayor a 500.000
                case 6 -> {
                    String cuentas = coop.getSocios().stream()
                            .flatMap(s -> s.getCuentas().stream())// flatMap = todas las cuentas de todos los socios
                            .filter(c -> c.getSaldo() > 500000)// filtro las que tengan saldo mayor a 500000
                            .map(c -> "Cuenta " + c.getNumeroCuenta() + " → Saldo: " + c.getSaldo())// convierto a texto
                            .reduce("", (a, b) -> a + "\n" + b);

                    JOptionPane.showMessageDialog(null, "Cuentas con saldo > 500000:\n" + cuentas);
                }
                // Opción 7: Total de saldos
                case 7 -> {
                    double total = coop.getSocios().stream()
                            .flatMap(s -> s.getCuentas().stream())// saco todas las cuentas
                            .mapToDouble(Cuenta::getSaldo)// convierto a double
                            .sum();// las sumo todas

                    JOptionPane.showMessageDialog(null, " Total de saldos en la cooperativa: " + total);
                }


                case 8 -> {
                    System.out.println("\n=== Intereses generados en cuentas de ahorro ===");

                    // Recorremos todos los socios y todas sus cuentas
                    coop.getSocios().stream()
                            .flatMap(s -> s.getCuentas().stream()) // me da todas las cuentas de todos los socios
                            .filter(c -> c instanceof CuentaAhorros) // solo tomo las cuentas de ahorro
                            .map(c -> (CuentaAhorros) c) // le digo a mi programa que c es de tipo CuentaAhorros
                            .forEach(c -> {
                                double interesGenerado = c.getSaldo() * c.getIntereses(); // hace la cuenta del interes
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Cuenta: " + c.getNumeroCuenta() +
                                                "\nSaldo actual: " + c.getSaldo() +
                                                "\nInterés aplicado: " + (c.getIntereses() * 100) + "%" +
                                                "\nGanancia por interés: " + interesGenerado,
                                            "Intereses de la Cuenta",
                                            JOptionPane.INFORMATION_MESSAGE
                                );
                            });

                }
                // Opción 9: Ver intereses de las cuentas de ahorro
                case 9 -> JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                // Opción por defecto: si no es ninguna de las anteriores, es inválida
                default -> JOptionPane.showMessageDialog(null, "Opción inválida.");

            }


        }while (opcion != 9); // El menú se repite hasta que elija salir (opción 9)

    }



}
