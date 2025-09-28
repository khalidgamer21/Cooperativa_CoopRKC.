package modelo;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Cooperativa {
    private List<Socio> socios;

    public Cooperativa() {
        this.socios = new ArrayList<>();
    }

    public void registrarSocio(Socio socio) {
        socios.add(socio);

    }

    public void abrirCuenta(Socio socio, Cuenta cuenta) {
       boolean existe = socio.getCuentas().stream()
               .anyMatch(c -> c.getNumeroCuenta().equals(cuenta.getNumeroCuenta()));

         if (existe) {
            JOptionPane.showMessageDialog(null, "ERROR, el socio ya tiene una cuenta con el este numero."+cuenta.getNumeroCuenta());
         }else {
                socio.agregarCuenta(cuenta);
                JOptionPane.showMessageDialog(null, "Cuenta abierta exitosamente para el socio: " + socio.getNombre());
            }
    }

    public List<Socio> getSocios() {
        return socios;
    }

}
