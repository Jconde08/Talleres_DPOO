package uniandes.dpoo.estructuras.logica;

import java.util.HashMap;
import java.util.Arrays;

public class SandboxArreglos {
    private int[] arregloEnteros;
    private String[] arregloCadenas;

    public SandboxArreglos() {
        arregloEnteros = new int[]{};
        arregloCadenas = new String[]{};
    }

    public int[] getCopiaEnteros() {
        return arregloEnteros.clone();
    }

    public String[] getCopiaCadenas() {
        return arregloCadenas.clone();
    }

    public int getCantidadEnteros() {
        return arregloEnteros.length;
    }

    public int getCantidadCadenas() {
        return arregloCadenas.length;
    }

    public void agregarEntero(int entero) {
        int[] nuevoArreglo = new int[arregloEnteros.length + 1];
        System.arraycopy(arregloEnteros, 0, nuevoArreglo, 0, arregloEnteros.length);
        nuevoArreglo[nuevoArreglo.length - 1] = entero;
        arregloEnteros = nuevoArreglo;
    }

    public void agregarCadena(String cadena) {
        String[] nuevoArreglo = new String[arregloCadenas.length + 1];
        System.arraycopy(arregloCadenas, 0, nuevoArreglo, 0, arregloCadenas.length);
        nuevoArreglo[nuevoArreglo.length - 1] = cadena;
        arregloCadenas = nuevoArreglo;
    }

    public void eliminarEntero(int valor) {
        int count = 0;
        for (int i : arregloEnteros) {
            if (i == valor) count++;
        }
        int[] nuevoArreglo = new int[arregloEnteros.length - count];
        int index = 0;
        for (int i : arregloEnteros) {
            if (i != valor) {
                nuevoArreglo[index++] = i;
            }
        }
        arregloEnteros = nuevoArreglo;
    }

    public void eliminarCadena(String cadena) {
        int count = 0;
        for (String s : arregloCadenas) {
            if (s.equals(cadena)) count++;
        }
        String[] nuevoArreglo = new String[arregloCadenas.length - count];
        int index = 0;
        for (String s : arregloCadenas) {
            if (!s.equals(cadena)) {
                nuevoArreglo[index++] = s;
            }
        }
        arregloCadenas = nuevoArreglo;
    }

    public void insertarEntero(int entero, int posicion) {
        int[] nuevoArreglo = new int[arregloEnteros.length + 1];
        if (posicion < 0) posicion = 0;
        if (posicion > arregloEnteros.length) posicion = arregloEnteros.length;
        System.arraycopy(arregloEnteros, 0, nuevoArreglo, 0, posicion);
        nuevoArreglo[posicion] = entero;
        System.arraycopy(arregloEnteros, posicion, nuevoArreglo, posicion + 1, arregloEnteros.length - posicion);
        arregloEnteros = nuevoArreglo;
    }

    public void eliminarEnteroPorPosicion(int posicion) {
        if (posicion < 0 || posicion >= arregloEnteros.length) return;
        int[] nuevoArreglo = new int[arregloEnteros.length - 1];
        System.arraycopy(arregloEnteros, 0, nuevoArreglo, 0, posicion);
        System.arraycopy(arregloEnteros, posicion + 1, nuevoArreglo, posicion, arregloEnteros.length - posicion - 1);
        arregloEnteros = nuevoArreglo;
    }

    public void reiniciarArregloEnteros(double[] valores) {
        arregloEnteros = new int[valores.length];
        for (int i = 0; i < valores.length; i++) {
            arregloEnteros[i] = (int) valores[i];
        }
    }

    public void reiniciarArregloCadenas(Object[] objetos) {
        arregloCadenas = new String[objetos.length];
        for (int i = 0; i < objetos.length; i++) {
            arregloCadenas[i] = objetos[i].toString();
        }
    }

    public void volverPositivos() {
        for (int i = 0; i < arregloEnteros.length; i++) {
            if (arregloEnteros[i] < 0) {
                arregloEnteros[i] *= -1;
            }
        }
    }

    public void organizarEnteros() {
        Arrays.sort(arregloEnteros);
    }

    public void organizarCadenas() {
        Arrays.sort(arregloCadenas);
    }

    public int contarApariciones(int valor) {
        int count = 0;
        for (int i : arregloEnteros) {
            if (i == valor) count++;
        }
        return count;
    }

    public int contarApariciones(String cadena) {
        int count = 0;
        for (String s : arregloCadenas) {
            if (s.equalsIgnoreCase(cadena)) count++;
        }
        return count;
    }

    public int[] buscarEntero(int valor) {
        int count = contarApariciones(valor);
        int[] posiciones = new int[count];
        int index = 0;
        for (int i = 0; i < arregloEnteros.length; i++) {
            if (arregloEnteros[i] == valor) {
                posiciones[index++] = i;
            }
        }
        return posiciones;
    }

    public int[] calcularRangoEnteros() {
        if (arregloEnteros.length == 0) return new int[]{};
        int min = arregloEnteros[0];
        int max = arregloEnteros[0];
        for (int i : arregloEnteros) {
            if (i < min) min = i;
            if (i > max) max = i;
        }
        return new int[]{min, max};
    }

    public HashMap<Integer, Integer> calcularHistograma() {
        HashMap<Integer, Integer> histograma = new HashMap<>();
        for (int i : arregloEnteros) {
            histograma.put(i, histograma.getOrDefault(i, 0) + 1);
        }
        return histograma;
    }

    public int contarEnterosRepetidos() {
        HashMap<Integer, Integer> histograma = calcularHistograma();
        int count = 0;
        for (int ocurrencias : histograma.values()) {
            if (ocurrencias > 1) count++;
        }
        return count;
    }

    public boolean compararArregloEnteros(int[] otroArreglo) {
        return Arrays.equals(arregloEnteros, otroArreglo);
    }

    public boolean mismosEnteros(int[] otroArreglo) {
        int[] copiaOriginal = arregloEnteros.clone();
        int[] copiaOtro = otroArreglo.clone();
        Arrays.sort(copiaOriginal);
        Arrays.sort(copiaOtro);
        return Arrays.equals(copiaOriginal, copiaOtro);
    }

    public void generarEnteros(int cantidad, int minimo, int maximo) {
        arregloEnteros = new int[cantidad];
        for (int i = 0; i < cantidad; i++) {
            arregloEnteros[i] = minimo + (int) (Math.random() * (maximo - minimo + 1));
        }
    }
}