package practica;
import Controlador.Practica;
import VIsta.VIsta;
import Modelo.Numeros;
/**
 *
 * @author SNeyder yessid rondon infante
 */
public class App {
    /**
     * Punto de entrada: arma el modelo, la vista y el controlador y arranca el menú.
     *
     * @param args argumentos de línea de comandos; no se usan
     */
    public static void main(String[] args) {
        new Practica(new Numeros(), new VIsta()).iniciar();
    }    
}

