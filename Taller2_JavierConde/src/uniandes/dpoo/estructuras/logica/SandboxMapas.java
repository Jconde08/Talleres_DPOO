package uniandes.dpoo.estructuras.logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SandboxMapas {
    private Map<String, String> mapaCadenas;

    public SandboxMapas() {
        mapaCadenas = new HashMap<>();
    }

    public List<String> getValoresComoLista() {
        List<String> valores = new ArrayList<>(mapaCadenas.values());
        Collections.sort(valores);
        return valores;
    }

    public List<String> getLlavesComoListaInvertida() {
        List<String> llaves = new ArrayList<>(mapaCadenas.keySet());
        llaves.sort(Collections.reverseOrder());
        return llaves;
    }

    public String getPrimera() {
        if (mapaCadenas.isEmpty()) {
            return null;
        }
        return Collections.min(mapaCadenas.keySet());
    }

    public String getUltima() {
        if (mapaCadenas.isEmpty()) {
            return null;
        }
        return Collections.max(mapaCadenas.values());
    }

    public Collection<String> getLlaves() {
        Set<String> llavesMayusculas = new HashSet<>();
        for (String llave : mapaCadenas.keySet()) {
            llavesMayusculas.add(llave.toUpperCase());
        }
        return llavesMayusculas;
    }

    public int getCantidadCadenasDiferentes() {
        Set<String> valoresUnicos = new HashSet<>(mapaCadenas.values());
        return valoresUnicos.size();
    }

    public void agregarCadena(String cadena) {
        String llave = new StringBuilder(cadena).reverse().toString();
        mapaCadenas.put(llave, cadena);
    }

    public void eliminarCadenaConLLave(String llave) {
        mapaCadenas.remove(llave);
    }

    public void eliminarCadenaConValor(String valor) {
        mapaCadenas.values().removeIf(v -> v.equals(valor));
    }

    public void reiniciarMapaCadenas(List<Object> objetos) {
        mapaCadenas.clear();
        for (Object obj : objetos) {
            String cadena = obj.toString();
            String llave = new StringBuilder(cadena).reverse().toString();
            mapaCadenas.put(llave, cadena);
        }
    }

    public void volverMayusculas() {
        Map<String, String> nuevoMapa = new HashMap<>();
        for (Map.Entry<String, String> entry : mapaCadenas.entrySet()) {
            nuevoMapa.put(entry.getKey().toUpperCase(), entry.getValue());
        }
        mapaCadenas = nuevoMapa;
    }

    public boolean compararValores(String[] otroArreglo) {
        Set<String> valoresMapa = new HashSet<>(mapaCadenas.values());
        for (String cadena : otroArreglo) {
            if (!valoresMapa.contains(cadena)) {
                return false;
            }
        }
        return true;
    }
}