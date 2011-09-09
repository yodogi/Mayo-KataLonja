package org.kata;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Lonja 
{
	
	Map<String, Integer> distancias;
	Map<String, Map<String, Integer>> valoresCompra;
	
	public Lonja() {
		distancias = new HashMap<String, Integer>();
		valoresCompra = new HashMap<String, Map<String, Integer>>();
	}
	
	public void defineDistancia(String destino, int km) {
		distancias.put(destino, new Integer(km));
		valoresCompra.put(destino, new HashMap<String, Integer>());
	}
	
	public Map<String, Integer> obtienePrecioVentaPorKg(String destino) {
		return valoresCompra.get(destino);
	}
	
	public float calculaBeneficio(Furgon furgoneta, String destino) throws Exception {
		Integer distancia = distancias.get(destino);
		
		if (distancia == null) throw new Exception("Destino de ventas indefinido");
		
		// Hay que tener en cuenta que tan sólo cargar la furgoneta le cuesta 5 € más 2 € por cada Km recorrido.
		int gasto = 5 + (2 * distancia);
		
		// Hay que tener en cuenta que los compradores estiman que el valor de compra de la carga se deprecia en un 1% por cada 100 Km recorridos.
		float indiceDepreciacion = distancia / 100;
		
		float beneficio = 0;
		Map<String, Integer> valoresCompraDestino = valoresCompra.get(destino);
		
		Iterator<String> productoIt = valoresCompraDestino.keySet().iterator();
		while (productoIt.hasNext()) {
			String producto = productoIt.next();
			int valorCompra =  valoresCompraDestino.get(producto) * furgoneta.getCarga(producto);
			beneficio += valorCompra - valorCompra * indiceDepreciacion / 100;
		}
		
		return beneficio - gasto;
	}
	
	public Iterator<String> destinosIterator() {
		return distancias.keySet().iterator();
	}
	
}
