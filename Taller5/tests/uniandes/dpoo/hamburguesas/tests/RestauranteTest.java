package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    public void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
        restaurante.iniciarPedido("Carlos", "Calle 123");
        Pedido pedido = restaurante.getPedidoEnCurso();
        assertNotNull(pedido, "Debería haber un pedido en curso");
        assertEquals("Carlos", pedido.getNombreCliente(), "El nombre del cliente debería ser 'Carlos'");
    }

    @Test
    public void testIniciarPedidoConPedidoEnCurso() throws YaHayUnPedidoEnCursoException {
        restaurante.iniciarPedido("Carlos", "Calle 123");
        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
            restaurante.iniciarPedido("Pedro", "Calle 456");
        }, "Debería lanzarse YaHayUnPedidoEnCursoException si ya hay un pedido en curso");
    }

    @Test
    public void testCerrarYGuardarPedido() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException {
        restaurante.iniciarPedido("Carlos", "Calle 123");
        Pedido pedido = restaurante.getPedidoEnCurso();
        restaurante.cerrarYGuardarPedido();

        assertNull(restaurante.getPedidoEnCurso(), "No debería haber un pedido en curso después de cerrar el pedido");
    }

    @Test
    public void testCerrarYGuardarPedidoSinPedidoEnCurso() {
        assertThrows(NoHayPedidoEnCursoException.class, () -> {
            restaurante.cerrarYGuardarPedido();
        }, "Debería lanzarse NoHayPedidoEnCursoException si no hay un pedido en curso");
    }

    @Test
    public void testCargarIngredientes() throws IOException, NumberFormatException, HamburguesaException {
        File archivoIngredientes = new File("src/test/resources/ingredientes.txt");
        restaurante.cargarInformacionRestaurante(archivoIngredientes, null, null);
        ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
        assertFalse(ingredientes.isEmpty(), "La lista de ingredientes no debería estar vacía después de cargar el archivo");
    }

    @Test
    public void testCargarMenu() throws IOException, NumberFormatException, HamburguesaException {
        File archivoMenu = new File("src/test/resources/menu.txt");
        restaurante.cargarInformacionRestaurante(null, archivoMenu, null);
        ArrayList<ProductoMenu> menuBase = restaurante.getMenuBase();
        assertFalse(menuBase.isEmpty(), "La lista de productos del menú no debería estar vacía después de cargar el archivo");
    }

    @Test
    public void testCargarCombos() throws IOException, NumberFormatException, HamburguesaException {
        File archivoCombos = new File("src/test/resources/combos.txt");
        restaurante.cargarInformacionRestaurante(null, null, archivoCombos);
        ArrayList<Combo> menuCombos = restaurante.getMenuCombos();
        assertFalse(menuCombos.isEmpty(), "La lista de combos no debería estar vacía después de cargar el archivo");
    }

    @Test
    public void testIngredienteRepetido() throws IOException {
        File archivoIngredientesRepetidos = new File("src/test/resources/ingredientes_repetidos.txt");
        assertThrows(ProductoRepetidoException.class, () -> {
            restaurante.cargarInformacionRestaurante(archivoIngredientesRepetidos, null, null);
        }, "Debería lanzarse ProductoRepetidoException si un ingrediente está repetido");
    }

    @Test
    public void testProductoFaltanteEnCombo() throws IOException {
        File archivoCombosConFaltantes = new File("src/test/resources/combos_faltantes.txt");
        assertThrows(ProductoFaltanteException.class, () -> {
            restaurante.cargarInformacionRestaurante(null, null, archivoCombosConFaltantes);
        }, "Debería lanzarse ProductoFaltanteException si un combo tiene un producto faltante");
    }

    @Test
    public void testGetPedidos() {
        ArrayList<Pedido> pedidos = restaurante.getPedidos();
        assertNotNull(pedidos, "La lista de pedidos cerrados no debería ser null");
        assertEquals(0, pedidos.size(), "Inicialmente no debería haber pedidos cerrados");
    }
}