package org.kata;

import java.util.HashMap;
import java.util.Map;

public class Furgon {
	
	Map<String, Integer> productos;
	
	public Furgon() {
		productos = new HashMap<String, Integer>();
	}
	
	public void cargadaCon(String producto, int kg) {
		productos.put(producto, new Integer(kg));
	}

	public Integer getCarga(String producto) {
		return productos.get(producto);
	}

}
