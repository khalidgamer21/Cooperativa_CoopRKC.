package modelo;

import java.util.ArrayList;
import java.util.List;

//Clase "Socio".
public class Socio {

    private String nombre, cedula;

    //lista de cuentas que pertenece al socio.
    private List<Cuenta> cuentas;

    //Constructor vacío. Permite crear un objeto sin inicializar valores.
    public Socio() {
    }

    /**
     * Constructor de la clase Socio.
     * Inicializa los atributos: nombre, cedula y Cuentas de la clase Socio.
     */
    public Socio(String nombre, String cedula, List<Cuenta> cuentas) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.cuentas = new ArrayList<>();
    }

    /**
     * Métodos de acceso (Getters).
     */
    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    // Método que agrega una cuenta nueva a la lista del socio.
    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    @Override

    //Devuelve una cadena de texto con la información de la Socio.
    public String toString() {
        return "Socio: " + nombre + " | Cédula No: " + cedula;
    }
}
