package VIsta;
import java.util.Scanner;

/**
 * Vista del patrón.
 * @author Sneyder yessid rondon infante
 * @since 05/10/2025
 */
public class VIsta {
    private final Scanner entrada = new Scanner(System.in);
    /**
     * Crea la vista leyendo de la entrada estándar.
     */
    public VIsta() {
    }
    /**
     * Muestra el menú principal.
     * @return opción elegida: 1 crear/llenar, 2 mostrar, 3 modificar,
     * 4 eliminar, 5 sumatoria, 0 salir
     */
    public int menuPrincipal() {
        return mostrarMenu("MENU", "Salir",
                "Crear / llenar", "Mostrar", "Modificar", "Eliminar", "Sumatoria");
    }
    /**
     * Muestra el submenú de creación y llenado.
     * @return opción elegida: 1 crear, 2 llenar, 0 volver
     */
    public int menuCrearLlenar() {
        return mostrarMenu("CREAR / LLENAR", "Volver", "Crear", "Llenar");
    }
    /**
     * Muestra el submenú de eliminación.
     * @return opción elegida: 1 dato, 2 array, 0 volver
     */
    public int menuEliminar() {
        return mostrarMenu("ELIMINAR", "Volver", "Dato", "Array");
    }
    /**
     * Pide el tamaño del arreglo hasta recibir uno mayor que cero.
     * @return tamaño positivo
     */
    public int pedirTamano() {
        while (true) {
            int tamano = pedirEntero("Ingresa el tamano del arreglo: ");
            if (tamano > 0) {
                return tamano;
            }
            mostrarMensaje("El tamano debe ser mayor que cero.");
        }
    }
    /**
     * Pide un número por cada posición, en orden.
     * @param cantidad cuántos números pedir
     * @return los números digitados, en el orden en que se pidieron
     */
    public int[] pedirValores(int cantidad) {
        int[] valores = new int[cantidad];
        for (int i = 0; i < cantidad; i++) {
            valores[i] = pedirEntero("Ingresa el numero de la posicion " + i + ": ");
        }
        return valores;
    }
    /**
     * Pide una posición. No comprueba que exista en el arreglo; eso lo decide
     * el controlador con ayuda del modelo.
     * @return posición digitada; -1 significa que el usuario quiere cancelar
     */
    public int pedirPosicion() {
        return pedirEntero("Ingresa la posicion (-1 para cancelar): ");
    }
    /**
     * Pide el valor que reemplazará a uno existente.
     * @return nuevo valor
     */
    public int pedirNuevoNumero() {
        return pedirEntero("Ingresa el nuevo numero: ");
    }
    /**
     * Muestra el valor de una posición y pregunta si es la que se quiere.
     * @param accion verbo que describe lo que se hará, por ejemplo {@code "modificar"}
     * @param pos posición elegida
     * @param valor valor que hay en esa posición
     * @return {@code true} si el usuario confirma, {@code false} si no
     */
    public boolean confirmarPosicion(String accion, int pos, int valor) {
        return confirmar("El numero que desea " + accion
                + " esta en la posicion " + pos + " = " + valor);
    }
    /**
     * Pregunta si realmente se debe borrar el arreglo completo.
     * @return {@code true} si el usuario confirma, {@code false} si no
     */
    public boolean confirmarEliminarArreglo() {
        return confirmar("Se va a eliminar el arreglo completo");
    }
    /**
     * Imprime el contenido del arreglo en una sola línea.
     * @param datos elementos a mostrar
     */
    public void mostrarArreglo(int[] datos) {
        System.out.print("Los numeros son: ");
        for (int numero : datos) {
            System.out.print(numero + " ");
        }
        System.out.println();
    }
    /**
     * Imprime el resultado de la sumatoria.
     * @param suma valor calculado por el modelo
     */
    public void mostrarSumatoria(long suma) {
        System.out.println("La sumatoria del arreglo es: " + suma);
    }
    /**
     * Imprime un aviso en una línea.
     * @param mensaje texto a mostrar
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    private int mostrarMenu(String titulo, String opcionCero, String... opciones) {
        System.out.println();
        System.out.println("===== " + titulo + " =====");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        System.out.println("0. " + opcionCero);
        return pedirEntero("Elige una opcion: ");
    }
    private boolean confirmar(String pregunta) {
        while (true) {
            int respuesta = pedirEntero(pregunta + " (1 para si / 0 para no): ");
            if (respuesta == 1) {
                return true;
            }
            if (respuesta == 0) {
                return false;
            }
            mostrarMensaje("Responde 1 para si o 0 para no.");
        }
    }
    private int pedirEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(entrada.nextLine().trim());
            } catch (NumberFormatException e) {
                mostrarMensaje("Eso no es un numero entero valido.");
            }
        }
    }
    /**
     * Pregunta al usuario el método preferido para rellenar el arreglo.
     * @return 1 para Manual, 2 para Random, cualquier otro número para cancelar
     */
    public int pedirTipoLlenado() {
        System.out.println("\nComo deseas llenar el arreglo?");
        System.out.println("1. Manual (Ingresar datos uno a uno)");
        System.out.println("2. Random (Generar valores aleatorios)");
        return pedirEntero("Seleccione una opcion: ");
    }
}
