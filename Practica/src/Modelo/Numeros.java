package Modelo;
import java.util.Random;
/**
 * Modelo 
 * El arreglo empieza sin crear.
 * @author Sneyder yessid rondon infante
 * @since 05/10/2025
 */
public class Numeros {
    private int[] datos;
    /**
     * Crea sin arreglo.
     */
    public Numeros() {
    }
    /**
     * @param tamano cantidad de posiciones, mayor que cero
     * @throws IllegalArgumentException si (tamano) es menor o igual a cero
     */
    public void crear(int tamano) {
        if (tamano <= 0) {
            throw new IllegalArgumentException("El tamano debe ser mayor que cero: " + tamano);
        }
        datos = new int[tamano];
    }
    /**
     * Indica si hay un arreglo disponible.
     * @return {true} si fue creado y no se ha eliminado
     */
    public boolean estaCreado() {
        return datos != null;
    }
    /**
     * Cantidad actual de posiciones; disminuye cada vez que se elimina un dato.
     * @return número de elementos del arreglo
     * @throws IllegalStateException si el arreglo no ha sido creado
     */
    public int tamano() {
        verificarCreado();
        return datos.length;
    }
    /**
     * Comprueba que una posición esté dentro del arreglo.     
     * @param pos posición a revisar, empezando en 0
     * @return { true} si hay arreglo y { pos} está entre 0 y { tamano() - 1}; {false} en cualquier otro caso
     */
    public boolean posicionValida(int pos) {
        return datos != null && pos >= 0 && pos < datos.length;
    }
    /**
     * Lee el valor de una posición.     
     * @param pos posición a leer
     * @return valor almacenado en { pos}
     * @throws IllegalStateException si el arreglo no ha sido creado
     * @throws IndexOutOfBoundsException si {pos} no es válida
     */
    public int obtener(int pos) {
        verificarPosicion(pos);
        return datos[pos];
    }
    /**
     * Entrega una copia del contenido, de modo que quien la reciba no pueda
     * alterar el arreglo interno.
     * @return copia de los elementos, en el mismo orden
     * @throws IllegalStateException si el arreglo no ha sido creado
     */
    public int[] obtenerDatos() {
        verificarCreado();
        return datos.clone();
    }
    /**
     * Reemplaza todo el contenido del arreglo.     *
     * @param valores nuevos valores; debe tener exactamente {tamano()} elementos
     * @throws IllegalStateException si el arreglo no ha sido creado
     * @throws IllegalArgumentException si la longitud de {valores} no coincide con el tamaño
     */
    public void llenar(int[] valores) {
        verificarCreado();
        if (valores.length != datos.length) {
            throw new IllegalArgumentException(
                    "Se esperaban " + datos.length + " valores y llegaron " + valores.length);
        }
        System.arraycopy(valores, 0, datos, 0, datos.length);
    }
    /**
     * Cambia el valor de una posición.
     * @param pos posición a modificar
     * @param valor nuevo valor
     * @throws IllegalStateException si el arreglo no ha sido creado
     * @throws IndexOutOfBoundsException si {pos} no es válida
     */
    public void modificar(int pos, int valor) {
        verificarPosicion(pos);
        datos[pos] = valor;
    }
    /**
     * Elimina el dato de una posición.
     * @param pos posición a eliminar
     * @throws IllegalStateException si el arreglo no ha sido creado
     * @throws IndexOutOfBoundsException si {pos} no es válida
     */
    public void eliminarDato(int pos) {
        verificarPosicion(pos);
        int[] reducido = new int[datos.length - 1];
        System.arraycopy(datos, 0, reducido, 0, pos);
        System.arraycopy(datos, pos + 1, reducido, pos, datos.length - pos - 1);
        datos = reducido;
    }
    /**
     * Elimina el arreglo completo. Después de esto hay que volver a llamar a
     * {crear(int)} para poder usarlo.
     */
    public void eliminarArreglo() {
        datos = null;
    }
    /**
     * Suma todos los elementos. El acumulador es {long} para que la suma
     * no desborde aunque los elementos sean valores grandes.
     * @return suma de los elementos; 0 si el arreglo no tiene elementos
     * @throws IllegalStateException si el arreglo no ha sido creado
     */
    public long sumatoria() {
        verificarCreado();
        long suma = 0;
        for (int numero : datos) {
            suma += numero;
        }
        return suma;
    }
    private void verificarCreado() {
        if (datos == null) {
            throw new IllegalStateException("El arreglo no ha sido creado");
        }
    }
    private void verificarPosicion(int pos) {
        verificarCreado();
        if (!posicionValida(pos)) {
            throw new IndexOutOfBoundsException("Posicion fuera de limites: " + pos);
        }
    }
    /**
     * Llena el arreglo con números aleatorios entre 1 y 100.
     * @throws IllegalStateException si el arreglo no ha sido creado
     */
   public void llenarRandom() {
       verificarCreado();
       Random rand = new Random();
       int[] valoresAleatorios = new int[datos.length];
       for (int i = 0; i < valoresAleatorios.length; i++) {
           valoresAleatorios[i] = rand.nextInt(100) + 1; // Rango de 1 a 100
       }
       llenar(valoresAleatorios);
   }
}
