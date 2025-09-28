package transacciones;

import modelo.Cuenta; // Importa la clase Cuneta desde el paquete Modelo

public class Retiro implements Transaccion {
    private Cuenta cuenta; // Cuenta de la cual se retira el dinero
    private double monto; // Monto a retirar

    public Retiro(Cuenta cuenta, double monto) {
        this.cuenta = cuenta;
        this.monto = monto;
    }

    @Override
    public void ejecutar() {cuenta.retirar(monto);}

    @Override
    public double getMonto() {return monto;}
}
