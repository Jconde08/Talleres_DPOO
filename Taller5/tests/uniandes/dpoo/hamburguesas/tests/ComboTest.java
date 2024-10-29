package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {

	private Combo combo;
    private ProductoMenu producto1;
    private ProductoMenu producto2;
    private ArrayList<ProductoMenu> itemsCombo;

    @BeforeEach
    public void setUp() {
        producto1 = new ProductoMenu("Hamburguesa", 10000);
        producto2 = new ProductoMenu("Papas Fritas", 5000);
        itemsCombo = new ArrayList<>();
        itemsCombo.add(producto1);
        itemsCombo.add(producto2);

        combo = new Combo("Combo Sencillo", 0.90, itemsCombo);
    }

    @Test
    public void testGetNombre() {
        assertEquals("Combo Sencillo", combo.getNombre(), "El nombre del combo debería ser 'Combo Sencillo'");
    }

    @Test
    public void testGetPrecio() {
        int precioSinDescuento = producto1.getPrecio() + producto2.getPrecio(); 
        int precioConDescuento = (int) (precioSinDescuento * 0.90); 
        assertEquals(precioConDescuento, combo.getPrecio(), "El precio del combo debería ser 90% del precio total de los productos");
    }

    @Test
    public void testGenerarTextoFactura() {
        String expectedFactura = "Combo Combo Sencillo\n" +
                                 " Descuento: 0.9\n" +
                                 "            13500\n"; 
        assertEquals(expectedFactura, combo.generarTextoFactura(), "El texto de la factura del combo debería estar correctamente formateado");
    }
}
