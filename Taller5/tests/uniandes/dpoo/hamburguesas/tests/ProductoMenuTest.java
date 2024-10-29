package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import org.junit.jupiter.api.BeforeEach;

public class ProductoMenuTest {

    private ProductoMenu producto;

    @BeforeEach
    public void setUp() {
        producto = new ProductoMenu("Hamburguesa Sencilla", 10000);
    }

    @Test
    public void testGetNombre() {
        assertEquals("Hamburguesa Sencilla", producto.getNombre(), "El nombre del producto debería ser 'Hamburguesa Sencilla'");
    }

    @Test
    public void testGetPrecio() {
        assertEquals(10000, producto.getPrecio(), "El precio del producto debería ser 10000");
    }

    @Test
    public void testGenerarTextoFactura() {
        String expectedFactura = "Hamburguesa Sencilla\n            10000\n";
        assertEquals(expectedFactura, producto.generarTextoFactura(), "El texto de la factura debería estar correctamente formateado");
    }

    @Test
    public void testGenerarTextoFacturaConNombreLargo() {
        ProductoMenu productoLargo = new ProductoMenu("Hamburguesa Super Deluxe con Queso Extra y Papas Grandes", 15000);
        String expectedFactura = "Hamburguesa Super Deluxe con Queso Extra y Papas Grandes\n            15000\n";
        assertEquals(expectedFactura, productoLargo.generarTextoFactura(), "El texto de la factura debería manejar nombres largos correctamente");
    }

    @Test
    public void testProductoPrecioCero() {
        ProductoMenu productoGratis = new ProductoMenu("Agua", 0);
        assertEquals(0, productoGratis.getPrecio(), "El precio debería ser 0 para productos gratis");
        String expectedFactura = "Agua\n            0\n";
        assertEquals(expectedFactura, productoGratis.generarTextoFactura(), "El texto de la factura debería incluir productos con precio 0");
    }

    @Test
    public void testProductoPrecioNegativo() {
        ProductoMenu productoInvalido = new ProductoMenu("Producto Inválido", -500);
        assertEquals(-500, productoInvalido.getPrecio(), "El precio negativo debería manejarse como se ingresó");
        String expectedFactura = "Producto Inválido\n            -500\n";
        assertEquals(expectedFactura, productoInvalido.generarTextoFactura(), "El texto de la factura debería incluir productos con precio negativo, si es necesario");
    }
}
