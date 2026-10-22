package Controlador;
import VIsta.VIsta;
import Modelo.Numeros;

/**
 * Controlador
 * @author Sneyder yessid rondon infante
 * @since 05/10/2025
 */
public class Practica {
    private static final int CANCELAR = -1;
    private final Numeros modelo;
    private final VIsta vista;
    /**
     * @param modelo lógica sobre la informacion
     * @param vista clase encargada de toda la entrada y salida por consola
     */
    public Practica(Numeros modelo, VIsta vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    /**
     * Muestra el menú principal una y otra vez hasta que el usuario elija salir.
     */
    public void iniciar() {
        int opcion;
        do {
            opcion = vista.menuPrincipal();
            switch (opcion) {
                case 1:
                    crearLlenar();
                    break;
                case 2:
                    mostrar();
                    break;
                case 3:
                    modificar();
                    break;
                case 4:
                    eliminar();
                    break;
                case 5:
                    sumatoria();
                    break;
                case 0:
                    vista.mostrarMensaje("chaolinn pingüin.");
                    break;
                default:
                    vista.mostrarMensaje("Opcion no valida.");
            }
        } while (opcion != 0);
    }
    private void crearLlenar() {
        switch (vista.menuCrearLlenar()) {
            case 1:
                crear();
                break;
            case 2:
                llenar();
                break;
            case 0:
                break;
            default:
                vista.mostrarMensaje("Opcion no valida.");
        }
    }
    private void crear() {
        modelo.crear(vista.pedirTamano());
        vista.mostrarMensaje("Arreglo creado con " + modelo.tamano() + " posiciones.");
    }
    private void llenar() {
        if (!hayDatos()) {
            return;
        }
        
        int tipoLlenado = vista.pedirTipoLlenado();
        
        switch (tipoLlenado) {
            case 1:
                modelo.llenar(vista.pedirValores(modelo.tamano()));
                vista.mostrarMensaje("Arreglo lleno de forma manual.");
                break;
            case 2:
                modelo.llenarRandom();
                vista.mostrarMensaje("Arreglo lleno con numeros aleatorios.");
                break;
            default:
                vista.mostrarMensaje("Opción no válida. Cancelando llenado.");
                break;
        }
    }    
    private void mostrar() {
        if (hayDatos()) {
            vista.mostrarArreglo(modelo.obtenerDatos());
        }
    }
    private void modificar() {
        if (!hayDatos()) {
            return;
        }
        vista.mostrarArreglo(modelo.obtenerDatos());
        int pos = seleccionarPosicion("modificar");
        if (pos == CANCELAR) {
            return;
        }
        modelo.modificar(pos, vista.pedirNuevoNumero());
        vista.mostrarMensaje("Numero modificado.");
    }   
    private void eliminar() {
        switch (vista.menuEliminar()) {
            case 1:
                eliminarDato();
                break;
            case 2:
                eliminarArreglo();
                break;
            case 0:
                break;
            default:
                vista.mostrarMensaje("Opcion no valida.");
        }
    }
    private void eliminarDato() {
        if (!hayDatos()) {
            return;
        }
        vista.mostrarArreglo(modelo.obtenerDatos());
        int pos = seleccionarPosicion("eliminar");
        if (pos == CANCELAR) {
            return;
        }
        modelo.eliminarDato(pos);
        vista.mostrarMensaje("Numero eliminado.");
    }
    private void eliminarArreglo() {
        if (hayArreglo() && vista.confirmarEliminarArreglo()) {
            modelo.eliminarArreglo();
            vista.mostrarMensaje("Arreglo eliminado.");
        }
    }
    private void sumatoria() {
        if (hayDatos()) {
            vista.mostrarSumatoria(modelo.sumatoria());
        }
    }
    /**
     * Pide posiciones hasta que el usuario elija una existente y la confirme.
     * Una posición fuera de rango o una confirmación negativa vuelven a
     * preguntar.
     */
    private int seleccionarPosicion(String accion) {
        while (true) {
            int pos = vista.pedirPosicion();
            if (pos == CANCELAR) {
                vista.mostrarMensaje("Operacion cancelada.");
                return CANCELAR;
            }
            if (!modelo.posicionValida(pos)) {
                vista.mostrarMensaje("Esa posicion esta fuera de los limites; ingresa una nueva.");
                continue;
            }
            if (vista.confirmarPosicion(accion, pos, modelo.obtener(pos))) {
                return pos;
            }
        }
    }
    private boolean hayArreglo() {
        if (!modelo.estaCreado()) {
            vista.mostrarMensaje("Primero debes crear el arreglo (1. Crear / llenar > 1. Crear).");
            return false;
        }
        return true;
    }
    private boolean hayDatos() {
        if (!hayArreglo()) {
            return false;
        }
        if (modelo.tamano() == 0) {
            vista.mostrarMensaje("El arreglo esta vacio.");
            return false;
        }
        return true;
    }
}
