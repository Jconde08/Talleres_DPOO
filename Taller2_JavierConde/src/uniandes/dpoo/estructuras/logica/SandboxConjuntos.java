package uniandes.dpoo.estructuras.logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class SandboxConjuntos {
    
    private NavigableSet<String> arbolCadenas;

    
    public SandboxConjuntos() {
        arbolCadenas = new TreeSet<>();
    }

   
    public List<String> getCadenasComoLista() {
        return new ArrayList<>(arbolCadenas);
    }

    
    public List<String> getCadenasComoListaInvertida() {
        return new ArrayList<>(arbolCadenas.descendingSet());
    }

    
    public String getPrimera() {
        return arbolCadenas.isEmpty() ? null : arbolCadenas.first();
    }

   
    public String getUltima() {
        return arbolCadenas.isEmpty() ? null : arbolCadenas.last();
    }

    
    public Collection<String> getSiguientes(String cadena) {
        return arbolCadenas.tailSet(cadena, true);
    }

    
    public int getCantidadCadenas() {
        return arbolCadenas.size();
    }

    
    public void agregarCadena(String cadena) {
        arbolCadenas.add(cadena);
    }

   
    public void eliminarCadena(String cadena) {
        arbolCadenas.remove(cadena);
    }


    public void eliminarCadenaSinMayusculasOMinusculas(String cadena) {
        arbolCadenas.removeIf(c -> c.equalsIgnoreCase(cadena));
    }

    
    public void eliminarPrimera() {
        if (!arbolCadenas.isEmpty()) {
            arbolCadenas.pollFirst();
        }
    }

   
    public void reiniciarConjuntoCadenas(List<Object> objetos) {
        arbolCadenas.clear();
        for (Object objeto : objetos) {
            arbolCadenas.add(objeto.toString());
        }
    }

    
    public void volverMayusculas() {
        NavigableSet<String> nuevoConjunto = new TreeSet<>();
        for (String cadena : arbolCadenas) {
            nuevoConjunto.add(cadena.toUpperCase());
        }
        arbolCadenas = nuevoConjunto;
    }

   
    public TreeSet<String> invertirCadenas() {
        return new TreeSet<>(arbolCadenas.descendingSet());
    }

    
    public boolean compararElementos(String[] otroArreglo) {
        return arbolCadenas.containsAll(Arrays.asList(otroArreglo));
    }
}