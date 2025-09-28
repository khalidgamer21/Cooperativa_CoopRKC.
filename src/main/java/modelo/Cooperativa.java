package modelo;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Cooperativa {// Clase Cooperativa → aquí guardo y manejo todos los socios


    // Lista de socios, es privada para protegerla (encapsulamiento)
    private List<Socio> socios;


    // Constructor de la clase → se ejecuta cuando creo una Cooperativa

    public Cooperativa() {
        this.socios = new ArrayList<>();// Inicializo la lista vacía donde voy a guardar los socios
    }
    // Metodo para registrar un socio en la cooperativa
    public void registrarSocio(Socio socio) {
        socios.add(socio);// Lo agrego a la lista de socios

    }
    // Metodo para abrir una cuenta y asociarla a un socio específico
    public void abrirCuenta(Socio socio, Cuenta cuenta) {//saco todas las cuentas del socio

        boolean existe = socio.getCuentas().stream()//saco todas las cuentas del socio y con stream convierto la lista en un flujo de datos para trabajarla
               .anyMatch(c -> c.getNumeroCuenta().equals(cuenta.getNumeroCuenta())); // revisa si existe al menos una cuenta con el mismo número y Si encuentra coincidencia, "existe" será true, si no, será false.

         if (existe) {// Si ya existe una cuenta con ese número → muestro un error
            JOptionPane.showMessageDialog(null, "ERROR, el socio ya tiene una cuenta con el este numero."+cuenta.getNumeroCuenta());
         }else {
             // Si no existe, agrego la nueva cuenta a la lista de cuentas del socio

             socio.agregarCuenta(cuenta);

                JOptionPane.showMessageDialog(null, "Cuenta abierta exitosamente para el socio: " + socio.getNombre());
            }
    }
    // Meodo para obtener la lista completa de socios
    public List<Socio> getSocios() {
        return socios;// Devuelvo la lista (aunque es privada, la expongo con este metdo público)
    }

}
