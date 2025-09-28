package modelo;

import javax.swing.*;


//"CuentaAhorros" es una subclase de "Cuenta".
public class CuentaAhorros extends Cuenta {

    // Atributo adicional exclusivo de la cuenta de ahorros "intereses".
    private double intereses;

    //Constructor vacío. Permite crear un objeto sin inicializar valores.
    public CuentaAhorros() {
    }

    /**
     * Constructor de la clase CuentaAhorros.
     * Llama a los atributos de la clase padre (Cuenta) para asignar el número de cuenta y el saldo inicial.
     * Además, inicializa el atributo "intereses", propio de la clase CuentaAhorros.
     */

    public CuentaAhorros(double saldo, String numeroCuenta, double intereses) {
        super(saldo, numeroCuenta);
        this.intereses = intereses;
    }

    /**
     * Método de acceso (Getter).
     */

    public double getIntereses() {
        return intereses;
    }

    // Método para aplicar los intereses al saldo.
    public void aplicarIntereses() {
        saldo += saldo * intereses;
    }

    //Método rettirar aquí se define cómo retirar dinero en una cuenta de ahorros.
    public void retirar(double monto) {

        while (monto > saldo) {
            JOptionPane.showMessageDialog(null,
                    "Error su saldo es insuficiente en la cuenta " + getNumeroCuenta() +
                            "\nSaldo actual: " + getSaldo(),
                    "Error de Retiro", JOptionPane.ERROR_MESSAGE);


            String nuevoMonto = JOptionPane.showInputDialog(
                    null,
                    "Digite un nuevo monto a retirar.\nSaldo disponible: " + getSaldo(),
                    "Nuevo Retiro",
                    JOptionPane.QUESTION_MESSAGE
            );


            if (nuevoMonto == null || nuevoMonto.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Operacion cancelada.",
                        "Cancelar", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                //Convierte lo ingresado en número coen el método "parseDouble".
                monto = Double.parseDouble(nuevoMonto);

            } catch (NumberFormatException e) {

                //Sino es un número válido, se cancela la operación.
                JOptionPane.showMessageDialog(null,
                        "Monto invalido. Intente de nuevo.",
                        "Error de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        //Si el monto es válido se descuenta.
        saldo -= monto;
        JOptionPane.showMessageDialog(null,
                "Retiro exitoso.\nNuevo saldo en la cuenta " + getNumeroCuenta() +
                        ": " + getSaldo(),
                "Retiro Exitoso", JOptionPane.INFORMATION_MESSAGE);
    }

    //Método que suma el monto al saldo actual.
    public void depositar(double monto) {

        saldo += monto;
    }

    //Devuelve una cadena de texto con la información de la CuentaAhorros.
    public String toString() {
        return "Cuenta de Ahorros No: " + getNumeroCuenta() + " | Saldo: " + saldo + " | Intereses: " + intereses;
    }
}
