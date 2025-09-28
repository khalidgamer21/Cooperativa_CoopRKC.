package transacciones;

// Interfaz para las transacciones bancarias (Define el contrato que deben segir todas las transacciones)
public interface Transaccion {
    void ejecutar();
    double getMonto();
}
