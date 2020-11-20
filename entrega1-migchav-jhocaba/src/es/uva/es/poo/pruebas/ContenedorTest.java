package es.uva.es.poo.pruebas;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import static org.junit.Assert.*;

import org.junit.Test;

import es.uva.es.poo.clases.*;
import es.uva.inf.poo.maps.GPSCoordinate;

public class ContenedorTest {

	@Test
	public void testConstructor() {
		Contenedor contenedorPrueba=new Contenedor("CSQU3054383","500-Kg",200.0,"100-ft3",false);
		assertNotNull(contenedorPrueba);
	}
	@Test
	public void testConstructorVolumenMetros() {
		Contenedor contenedorPrueba=new Contenedor("CSQU3054383","500-lb",200.0,"100-m3",false);
		assertNotNull(contenedorPrueba);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorCodigoControlFalso() {
		Contenedor contenedorPrueba=new Contenedor("CSQU3054385","500-Kg",200.0,"100-m3",false);
		assertNotNull(contenedorPrueba);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorIdentificadorFalso() {
		Contenedor contenedorPrueba=new Contenedor("CSQUU3054383","500-Kg",-200.0,"100-m3",false);
		assertNotNull(contenedorPrueba);
	}
	@Test
	public void testComprobarUnidadesPesoKilos() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "400-Kg", 500.75, "300-m3", true);
		assertEquals(contenedor.getPesoKilo(), 400.0, 0.0);
	}
	
	@Test
	public void testComprobarUnidadesPesoLibras() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "400-Kg", 500.75, "300-m3", true);
		assertEquals(contenedor.getPesoLibra(), 800, 00.0);
	}
	
	@Test
	public void testComprobarUnidadesVolumenMetros() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "400-Kg", 500.75, "300-m3", true);
		assertEquals(contenedor.getVolumenMetros(), 300.0, 0.0);
	}
	
	@Test
	public void testComprobarUnidadesVolumenPies() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "400-Kg", 500.75, "300-m3", true);
		assertEquals(contenedor.getVolumenPies(), 10500, 0.0);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesPesoNull() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "", 500.75, "300-m3", true);
		contenedor.comprobarUnidadesPeso("");
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesVolumenNull() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "400-Kg", 500.75, "", true);
		contenedor.comprobarUnidadesVolumen("");
	}
	
	@Test
	public void testConviertePesoKilo() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "400-lb", 500.75, "300-m3", true);
		contenedor.conviertePesoKilo(400.0);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testConviertePesoKiloNegativo() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "400-Kg", 500.75, "300-m3", true);
		contenedor.conviertePesoKilo(-400.0);
	}
	
	@Test
	public void testSetPesoKilo() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "400-Kg", 500.75, "300-ft3", true);
		contenedor.setPesoKilo(400.0);
		assertEquals(contenedor.getPesoKilo(), 400.0, 0.0);
		
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testSetPesoKiloNegativo() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "400-Kg", 500.75, "300-m3", true);
		contenedor.conviertePesoKilo(-400.0);
	}
	
	@Test
	public void testconvierteVolumenMetros() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "400-lb", 500.75, "300-m3", true);
		contenedor.convierteVolumenMetros(300.0);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testConvierteVolumenMetrosNegativo() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "400-Kg", 500.75, "300-m3", true);
		contenedor.convierteVolumenMetros(-300);
	}
	
	@Test
	public void testSetVolumenMetros() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "400-Kg", 500.75, "300-ft3", true);
		contenedor.setVolumenMetros(400.0);
		assertEquals(contenedor.getVolumenMetros(), 400.0, 0.0);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testSetSetVolumenMetrosNegativo() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "400-Kg", 500.75, "300-m3", true);
		contenedor.convierteVolumenMetros(-400.0);
	}
	
	@Test
	public void testGetVolumenMetros() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "400-Kg", 500.75, "300-m3", true);
		assertEquals(contenedor.getVolumenMetros(), 300, 0.0);
	}
	
	@Test
	public void testGetVolumenPies() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "400-Kg", 500.75, "300-m3", true);
		assertEquals(contenedor.getVolumenPies(),10500, 0.0);
	}
	
	@Test
	public void testGetPesoKilo() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "400-Kg", 500.75, "300-m3", true);
		assertEquals(contenedor.getPesoKilo(), 400, 0.0);
	}
	
	@Test
	public void testGetPesoLibra() {
		Contenedor contenedor = new Contenedor("CSQU3054383", "400-Kg", 500.75, "300-m3", true);
		assertEquals(contenedor.getPesoLibra(), 800, 0.0);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorPesoInvalido() {
		Contenedor contenedorPrueba=new Contenedor("CSQU3054385","500-Kgg",200.0,"100-m3",false);
		assertNotNull(contenedorPrueba);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorPesoNegativo() {
		Contenedor contenedorPrueba=new Contenedor("CSQU3054383","-500-Kg",200.0,"100-ft3",false);
		assertNotNull(contenedorPrueba);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorVolumenNegativo() {
		Contenedor contenedorPrueba=new Contenedor("CSQU3054383","500-Kg",200.0,"-100-ft3",false);
		assertNotNull(contenedorPrueba);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorVolumenInvalido() {
		Contenedor contenedorPrueba=new Contenedor("CSQU3054385","500-Kg",200.0,"100-m33",false);
		assertNotNull(contenedorPrueba);
	}
	@Test
	public void testComprobarIdentificador() {
		Contenedor contenedor=new Contenedor();
		String identificador="CSQU3054383";
		contenedor.comprobarIdentificador(identificador);	
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarIdentificadorIdMasLargo() {
		Contenedor contenedor=new Contenedor();
		String identificador="CSSQU3054383";
		contenedor.comprobarIdentificador(identificador);	
	}

	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarIdentificadorMinusculas() {
		Contenedor contenedor=new Contenedor();
		String identificador="CSsQU3054383";
		contenedor.comprobarIdentificador(identificador);	
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarIdentificadorCodigoInvalido() {
		Contenedor contenedor=new Contenedor();
		String identificador="CSSQZ3054383";
		contenedor.comprobarIdentificador(identificador);	
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarIdentificadorSerieInvalida() {
		Contenedor contenedor=new Contenedor();
		String identificador="CSSUZ30543583";
		contenedor.comprobarIdentificador(identificador);	
	}
	@Test
	public void obtenerDigitoControl() {
		Contenedor contenedor=new Contenedor();
		String identificador="CSQU3054383";
		int digitoControl=contenedor.obtenerDigitoControl(identificador);
		assertEquals(digitoControl,3);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void obtenerDigitoControlIdInvalido() {
		Contenedor contenedor=new Contenedor();
		String identificador="CSQU38054383";
		int digitoControl=contenedor.obtenerDigitoControl(identificador);
		assertEquals(digitoControl,3);
	}
	@Test
	public void testGetIdentificador() {
		Contenedor pruebaContenedor=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		assertEquals(pruebaContenedor.getIdentificador(),"CSQU3054383");
	}
	@Test
	public void testSetRecogida() {
		Contenedor pruebaContenedor=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		pruebaContenedor.setRecogida();
		assertTrue(pruebaContenedor.getEstado());
	}
	@Test
	public void testSetTransito() {
		Contenedor pruebaContenedor=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		pruebaContenedor.setTransito();
		assertFalse(pruebaContenedor.getEstado());
	}
	@Test
	public void testGetEstado() {
		Contenedor pruebaContenedor=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		assertFalse(pruebaContenedor.getEstado());
	}
	@Test
	public void testSetTecho() {
		Contenedor pruebaContenedor=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		pruebaContenedor.setTecho();
		assertTrue(pruebaContenedor.getTecho());
	}
	@Test
	public void testSetNoTecho() {
		Contenedor pruebaContenedor=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		pruebaContenedor.setNoTecho();
		assertFalse(pruebaContenedor.getTecho());
	}
	@Test
	public void testGetTecho() {
		Contenedor pruebaContenedor=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		assertFalse(pruebaContenedor.getTecho());
	}
	@Test
	public void testHacerTrayecto() {
		Contenedor pruebaContenedor=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		Puerto destino=new Puerto("ES-MAD");
		pruebaContenedor.hacerTrayecto(destino);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testHacerTrayectoNull() {
		Contenedor pruebaContenedor=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		Puerto destino=null;
		pruebaContenedor.hacerTrayecto(destino);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testHacerTrayectoAtributosNull() {
		Contenedor pruebaContenedor=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		Puerto destino=new Puerto();
		pruebaContenedor.hacerTrayecto(destino);
	}
	@Test
	public void testHacerViaje() {
		Contenedor pruebaContenedor=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		Puerto destino=new Puerto("ES-MAD");
		GPSCoordinate coordenadaMuelle=new GPSCoordinate(40.5,40.5);
		Muelle destinoMuelle=new Muelle(12,coordenadaMuelle,'O',50);
		destino.addMuelle(destinoMuelle);
		
		pruebaContenedor.hacerTrayecto(destino);
		Puerto puertoOrigen=new Puerto("ES-VAL");
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadirOrigen=new Muelle(20,coordenada,'F',5);
		añadirOrigen.asignarPlaza(pruebaContenedor, 2);
		puertoOrigen.addMuelle(añadirOrigen);
		

		pruebaContenedor.hacerViajes(pruebaContenedor, puertoOrigen, destino, destinoMuelle, "2020-11-19", "2020-12-31");
		assertTrue(pruebaContenedor.getEstado());
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testHacerViajeNoEstaElContenedor() {
		Contenedor pruebaContenedor=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		Puerto destino=new Puerto("ES-MAD");
		GPSCoordinate coordenadaMuelle=new GPSCoordinate(40.5,40.5);
		Muelle destinoMuelle=new Muelle(12,coordenadaMuelle,'O',50);
		destino.addMuelle(destinoMuelle);
		
		pruebaContenedor.hacerTrayecto(destino);
		Puerto puertoOrigen=new Puerto("ES-VAL");
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadirOrigen=new Muelle(20,coordenada,'F',5);
		añadirOrigen.asignarPlaza(pruebaContenedor, 2);
		puertoOrigen.addMuelle(añadirOrigen);
		
		Contenedor contenedorNoEsta=new Contenedor("TTNU9843220","500-Kg",200.0,"100-m3",false);
		

		pruebaContenedor.hacerViajes(contenedorNoEsta, puertoOrigen, destino, destinoMuelle, "2020-11-19", "2020-12-31");
		assertTrue(pruebaContenedor.getEstado());
	}
	@Test(expected=DateTimeParseException.class) 
	public void testHacerViajeNull() {
		Contenedor pruebaContenedor=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		Puerto destino=new Puerto("ES-MAD");
		GPSCoordinate coordenadaMuelle=new GPSCoordinate(40.5,40.5);
		Muelle destinoMuelle=new Muelle(12,coordenadaMuelle,'O',50);
		destino.addMuelle(destinoMuelle);
		
		pruebaContenedor.hacerTrayecto(destino);
		Puerto puertoOrigen=new Puerto("ES-VAL");
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadirOrigen=new Muelle(20,coordenada,'F',5);
		añadirOrigen.asignarPlaza(pruebaContenedor, 2);
		puertoOrigen.addMuelle(añadirOrigen);
		

		pruebaContenedor.hacerViajes(pruebaContenedor, puertoOrigen, destino, destinoMuelle, "2020-31-19", "2020-12-31");
		assertTrue(pruebaContenedor.getEstado());
	}
	@Test
	public void testPrecio() {
		Contenedor pruebaContenedor=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		Puerto destino=new Puerto("ES-MAD");
		GPSCoordinate coordenadaMuelle=new GPSCoordinate(40.5,40.5);
		Muelle destinoMuelle=new Muelle(12,coordenadaMuelle,'O',50);
		destino.addMuelle(destinoMuelle);
		
		pruebaContenedor.hacerTrayecto(destino);
		Puerto puertoOrigen=new Puerto("ES-VAL");
		GPSCoordinate coordenada=new GPSCoordinate(40.0,40.0);
		Muelle añadirOrigen=new Muelle(20,coordenada,'F',5);
		añadirOrigen.asignarPlaza(pruebaContenedor, 2);
		puertoOrigen.addMuelle(añadirOrigen);
		

		pruebaContenedor.hacerViajes(pruebaContenedor, puertoOrigen, destino, destinoMuelle, "2020-11-19", "2020-12-31");
		double precio=pruebaContenedor.Precio(100, 100);
		assertEquals(precio,2.9376347692557964E7,0.0);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testPrecioMillaNegativo() {
		Contenedor pruebaContenedor=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		Puerto destino=new Puerto("ES-MAD");
		GPSCoordinate coordenadaMuelle=new GPSCoordinate(40.5,40.5);
		Muelle destinoMuelle=new Muelle(12,coordenadaMuelle,'O',50);
		destino.addMuelle(destinoMuelle);
		
		pruebaContenedor.hacerTrayecto(destino);
		Puerto puertoOrigen=new Puerto("ES-VAL");
		GPSCoordinate coordenada=new GPSCoordinate(40.0,40.0);
		Muelle añadirOrigen=new Muelle(20,coordenada,'F',5);
		añadirOrigen.asignarPlaza(pruebaContenedor, 2);
		puertoOrigen.addMuelle(añadirOrigen);
		

		pruebaContenedor.hacerViajes(pruebaContenedor, puertoOrigen, destino, destinoMuelle, "2020-11-19", "2020-12-31");
		double precio=pruebaContenedor.Precio(-100, 100);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testPrecioDiaNegativo() {
		Contenedor pruebaContenedor=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		Puerto destino=new Puerto("ES-MAD");
		GPSCoordinate coordenadaMuelle=new GPSCoordinate(40.5,40.5);
		Muelle destinoMuelle=new Muelle(12,coordenadaMuelle,'O',50);
		destino.addMuelle(destinoMuelle);
		
		pruebaContenedor.hacerTrayecto(destino);
		Puerto puertoOrigen=new Puerto("ES-VAL");
		GPSCoordinate coordenada=new GPSCoordinate(40.0,40.0);
		Muelle añadirOrigen=new Muelle(20,coordenada,'F',5);
		añadirOrigen.asignarPlaza(pruebaContenedor, 2);
		puertoOrigen.addMuelle(añadirOrigen);
		

		pruebaContenedor.hacerViajes(pruebaContenedor, puertoOrigen, destino, destinoMuelle, "2020-11-19", "2020-12-31");
		double precio=pruebaContenedor.Precio(100, -100);
	}
	
}
