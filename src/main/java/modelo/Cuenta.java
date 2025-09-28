package modelo;

// Se declara la clase "Cuenta" como abstracta.
public abstract class Cuenta {


    //Atributo privado: almacena el número de cuenta.
    private String numeroCuenta;

    //Atributo protegido: almacena el saldo actual de la cuenta.
    protected double saldo;

    public Cuenta() {
    }

    public Cuenta(double saldo, String numeroCuenta) {
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * Métodos de acceso (Getters).
     */

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    // Métodos abstractos (sin cuerpo).
    public abstract void depositar(double saldo);

    public abstract void retirar(double monto);

    @Override
    //Devuelve una cadena de texto con la información de la cuenta.
    public String toString() {

        return "Cuenta " + numeroCuenta + " | Saldo: " + saldo;
    }

}
