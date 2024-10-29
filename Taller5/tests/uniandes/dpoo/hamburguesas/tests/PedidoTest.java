package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {

	private Pedido pedido;
    private ProductoMenu producto1;
    private ProductoMenu producto2;

    @BeforeEach
    public void setUp() {
        pedido = new Pedido("Juan Pérez", "Calle 123");
        producto1 = new ProductoMenu("Hamburguesa", 10000);
        producto2 = new ProductoMenu("Papas Fritas", 5000);
    }

    @Test
    public void testGetIdPedido() {
        int id = pedido.getIdPedido();
        assertEquals(id, Pedido.numeroPedidos - 1, "El ID del pedido debería corresponder con el número de pedidos incrementado");
    }

    @Test
    public void testGetNombreCliente() {
        assertEquals("Juan Pérez", pedido.getNombreCliente(), "El nombre del cliente debería ser 'Juan Pérez'");
    }

    @Test
    public void testAgregarProducto() {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);
        assertEquals(2, pedido.productos.size(), "El pedido debería tener 2 productos agregados");
    }

    @Test
    public void testGetPrecioNetoPedido() {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);
        assertEquals(15000, pedido.getPrecioTotalPedido(), "El precio neto del pedido debería ser la suma de los productos: 15000");
    }

    @Test
    public void testGetPrecioIVAPedido() {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);
        assertEquals(2850, pedido.getPrecioIVAPedido(), "El IVA debería ser el 19% de 15000, es decir 2850");
    }

    @Test
    public void testGetPrecioTotalPedido() {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);
        assertEquals(17850, pedido.getPrecioTotalPedido(), "El precio total debería ser la suma del precio neto y el IVA: 17850");
    }

    @Test
    public void testGenerarTextoFactura() {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        String expectedFactura = "Cliente: Juan Pérez\n" +
                                 "Dirección: Calle 123\n" +
                                 "----------------\n" +
                                 "Hamburguesa\n            10000\n" +
                                 "Papas Fritas\n            5000\n" +
                                 "----------------\n" +
                                 "Precio Neto:  15000\n" +
                                 "IVA:          2850\n" +
                                 "Precio Total: 17850\n";

        assertEquals(expectedFactura, pedido.generarTextoFactura(), "El texto de la factura debería coincidir con el formato esperado");
    }

    @Test
    public void testGuardarFactura() throws FileNotFoundException {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        File archivo = new File("factura_test.txt");
        pedido.guardarFactura(archivo);

        assertTrue(archivo.exists(), "El archivo de la factura debería haberse creado");

        archivo.delete();
    }
}

