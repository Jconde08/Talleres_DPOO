package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {

	private ProductoAjustado productoAjustado;
    private ProductoMenu productoMenu;

    @BeforeEach
    public void setUp() {
        productoMenu = new ProductoMenu("Hamburguesa Sencilla", 10000);
        productoAjustado = new ProductoAjustado(productoMenu); 
    }

    @Test
    public void testGetNombre() {
        assertEquals("Hamburguesa Sencilla", productoAjustado.getNombre(), "El nombre debería ser 'Hamburguesa Sencilla'");
    }

    @Test
    public void testGetPrecio() {
        assertEquals(0, productoAjustado.getPrecio(), "El precio debería ser 0, según la implementación actual");
    }

    @Test
    public void testGenerarTextoFacturaSinIngredientes() {
        String expectedFactura = "Hamburguesa Sencilla\n            0\n";
        assertEquals(expectedFactura, productoAjustado.generarTextoFactura(), "El texto de la factura debería mostrar solo el producto base y un precio de 0");
    }
}