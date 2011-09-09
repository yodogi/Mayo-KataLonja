package org.kata;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

import org.kata.Lonja;
import org.kata.Furgon;

/*
 * Recordad, el objetivo es responder a nuestro amigo emprendedor dónde debería vender esa carga de pescado y marisco para obtener el mayor beneficio.
 * 
 */
public class LonjaTest {
	
	private Lonja lonja;
	private Furgon furgoneta;
	private Map<String, Integer> precioVenta;

	@Before
	public void setUp() {
		lonja = new Lonja();
		
		/*
		 *  Las distancias hasta los posibles destinos son:
		 * 
	     * 		a MADRID : 800 Km
	     * 		a BARCELONA : 1100 Km
	     * 		a LISBOA : 600 Km
		 */
		lonja.defineDistancia("MADRID", 800);
		lonja.defineDistancia("BARCELONA", 1100);
		lonja.defineDistancia("LISBOA", 600);
		
		/*
		 *  Precio de las correspondientes bolsas
		 *	para ello conoce los precios de venta en los diferentes mercados locales:
		 *	
		 *	€/Kg        | MADRID | BARCELONA | LISBOA
		 *	Vieiras     |  500   |    450    |  600
		 *	Pulpo       |    0   |    120    |  100
		 *	Centollos   |  450   |      0    |  500
		 * 
		 */
		precioVenta = lonja.obtienePrecioVentaPorKg("MADRID");
		precioVenta.put("Vieras", 500);
		precioVenta.put("Pulpo", 0);
		precioVenta.put("Centollos", 450);
		
		precioVenta = lonja.obtienePrecioVentaPorKg("BARCELONA");
		precioVenta.put("Vieras", 450);
		precioVenta.put("Pulpo", 120);
		precioVenta.put("Centollos", 0);
		
		precioVenta = lonja.obtienePrecioVentaPorKg("LISBOA");
		precioVenta.put("Vieras", 600);
		precioVenta.put("Pulpo", 100);
		precioVenta.put("Centollos", 500);
		
		furgoneta = new Furgon();
	}
	
	@Test
	public void firstTrip() throws Exception {
		/*
		 * Carga de la forgoneta
		 * 
		 * Para el primer viaje ha comprado 50 Kg de vieiras, 100 Kg de pulpo y otros 50 Kg de centollos,
		 *  
		 */
		furgoneta.cargadaCon("Vieras", 50);
		furgoneta.cargadaCon("Pulpo", 100);
		furgoneta.cargadaCon("Centollos", 50);
		
		// pero se pregunta dónde debería vender esta carga para obtener el máximo beneficio.
		float maximoBeneficio = 0;
		String mejorDestino = "ningún lugar";
		Iterator<String> it = lonja.destinosIterator();
		while (it.hasNext()) {
		    String destino = it.next();
			float beneficio = lonja.calculaBeneficio(furgoneta, destino);
			if (beneficio > maximoBeneficio) {
				mejorDestino = destino;
				maximoBeneficio = beneficio;
			}
		}
		System.out.println("Benificio obtenido en " + mejorDestino + ": " + maximoBeneficio);

        assertEquals("Destino de mayor valor beneficio erroneo", "LISBOA", mejorDestino);
        assertEquals("Valor de beneficio incorrecto", 59895, Math.round(maximoBeneficio));
	}
	
}
