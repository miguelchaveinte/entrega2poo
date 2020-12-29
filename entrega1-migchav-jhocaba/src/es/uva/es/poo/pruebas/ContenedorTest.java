package es.uva.es.poo.pruebas;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uva.es.poo.clases.*;
import es.uva.inf.poo.maps.GPSCoordinate;

public class ContenedorTest {

	@Test
	public void testConstructorEstandar() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		assertNotNull(container);
	}
	
	@Test
	public void testConstructorRefrigerado() {
		Contenedor container=new Refrigerado("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		assertNotNull(container);
	}
	
	@Test
	public void testConstructorFlatRack() {
		Contenedor container =new FlatRack("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		assertNotNull(container);
	}
	
	/**
	//ComprobarIdentificador
	@Test
	public void testComprobarIdentificador() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		String identificador="CSQU3054383";
		container.comprobarIdentificador(identificador);	
	}	
	
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarIdentificadorIdMasLargo() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		String identificador="CSSQU3054383";
		container.comprobarIdentificador(identificador);	
	}

	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarIdentificadorMinusculas() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		String identificador="CSsQU3054383";
		container.comprobarIdentificador(identificador);	
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarIdentificadorCodigoInvalido() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		String identificador="CSSQZ3054383";
		container.comprobarIdentificador(identificador);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarIdentificadorSerieInvalida() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		String identificador="CSSUZ30543583";
		container.comprobarIdentificador(identificador);	
	}
	**/
	/**
	//ObtenerDigitoControl
	@Test
	public void obtenerDigitoControl() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		String identificador="CSQU3054383";
		int digitoControl=container.obtenerDigitoControl(identificador);
		assertEquals(digitoControl,3);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void obtenerDigitoControlIdInvalido() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		String identificador="CSQU38054383";
		int digitoControl=container.obtenerDigitoControl(identificador);
		assertEquals(digitoControl,3);
	}
	**/
		
	@Test
	public void testSetDestinoFinal() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		Puerto puertoPrueba=new Puerto("ES-MAD");
		container.setDestinoFinal(puertoPrueba);
		assertEquals(container.getDestinoFinal(), puertoPrueba);
	}
	
	//ComprobarUnidadesPesoVolumen
	@Test
	public void testComprobarUnidadesPesoKg() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		container.comprobarUnidadesPeso(500.0, "Kg");
	}
	
	@Test
	public void testComprobarUnidadesPesolb() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		container.comprobarUnidadesPeso(500.0, "lb");
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesPesolbNegativo() {
		Contenedor container=new Estandar("CSQU3054383",-500.0,"Kg",200.0,100.0,"ft3");
		container.comprobarUnidadesPeso(-500.0, "Kg");
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesPesoNoValidas() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kb",200.0,100.0,"ft3");
		container.comprobarUnidadesPeso(500.0, "Kb");
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesPesoNoValida() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"lg",200.0,100.0,"ft3");
		container.comprobarUnidadesPeso(500.0, "lg");
	}
	
	@Test
	public void testComprobarUnidadesPesoKilos() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		assertEquals(container.getPesoKilo(), 500.0, 0.0);
	}
	
	@Test
	public void testComprobarUnidadesPesoLibras() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"lb",200.0,100.0,"ft3");
		assertEquals(container.getPesoLibra(), 499.99999999999994, 0.0);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesPesoLibrasNegativo() {
		Contenedor container=new Estandar("CSQU3054383",-500.0,"lb",200.0,100.0,"ft3");
		assertEquals(container.getPesoLibra(), -499.99999999999994, 0.0);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesPesoVacio() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"",200.0,100.0,"ft3");
		container.comprobarUnidadesPeso(500.0, "");
	}
	
	@Test
	public void testConviertePesoKilo() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		container.conviertePesoKilo(500.0);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testConviertePesoKiloNegativo() {
		Contenedor container=new Estandar("CSQU3054383",-500.0,"Kg",200.0,100.0,"ft3");
		container.conviertePesoKilo(-500.0);
	}
	
	@Test
	public void testSetPesoKilo() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		container.setPesoKilo(500.0);
		assertEquals(container.getPesoKilo(), 500.0, 0.0);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testSetPesoKiloNegativo() {
		Contenedor container=new Estandar("CSQU3054383",-500.0,"Kg",200.0,100.0,"ft3");
		container.setPesoKilo(-500.0);
	}
	
	@Test
	public void testComprobarUnidadesVolumenm3() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		container.comprobarUnidadesVolumen(500.0, "m3");
	}
	
	@Test
	public void testComprobarUnidadesVolumenft3() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		container.comprobarUnidadesVolumen(500.0, "ft3");
	}
		
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesVolumenNoValidas() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kb",200.0,100.0,"ft3");
		container.comprobarUnidadesVolumen(500.0, "mf4");
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesVolumenNoValida() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"lg",200.0,100.0,"ft3");
		container.comprobarUnidadesVolumen(500.0, "fm3");
	}
	
	@Test
	public void testComprobarUnidadesVolumenMetros() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		assertEquals(container.getVolumenMetros(), 100.0, 0.0);
	}
	
	@Test
	public void testComprobarUnidadesVolumenPies() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,300.0,"ft3");
		assertEquals(container.getVolumenPies(), 300, 0.0);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesVolumenVacio() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"");
		container.comprobarUnidadesVolumen(500.0, "");
	}
	
	@Test
	public void testConvierteVolumenMetros() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,300.0,"ft3");
		container.convierteVolumenMetros(300.0);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testConvierteVolumenMetrosNegativo() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,300.0,"ft3");
		container.convierteVolumenMetros(-300);
	}
	
	@Test
	public void testSetVolumenMetros() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		container.setVolumenMetros(100.0);
		assertEquals(container.getVolumenMetros(), 100.0, 0.0);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testSetSetVolumenMetrosNegativo() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,-100.0,"m3");
		container.convierteVolumenMetros(-100.0);
	}
	
	//GETTERS
	@Test
	public void testGetVolumenMetros() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,300.0,"m3");
		assertEquals(container.getVolumenMetros(), 300.0, 0.0);
	}
	
	@Test
	public void testGetVolumenPies() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		assertEquals(container.getVolumenPies(),100.00000000000001, 0.0);
	}
	
	@Test
	public void testGetPesoKilo() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		assertEquals(container.getPesoKilo(), 500.0, 0.0);
	}
	
	@Test
	public void testGetPesoLibra() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		assertEquals(container.getPesoLibra(), 1102.31, 0.0);
	}
	
	@Test
	public void testGetIdentificador() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		assertEquals(container.getIdentificador(),"CSQU3054383");
	}
	
	@Test
	public void testSetRecogida() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		container.setRecogida();
		assertTrue(container.getEstado());
	}
	@Test
	public void testSetTransito() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		container.setTransito();
		assertFalse(container.getEstado());
	}
	
	@Test
	public void testGetEstado() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		assertFalse(container.getEstado());
	}
	
	@Test
	public void testSetTecho() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		container.setTecho();
		assertTrue(container.getTecho());
	}
	@Test
	public void testSetNoTecho() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		container.setNoTecho();
		assertFalse(container.getTecho());
	}

	@Test
	public void testGetTecho() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		assertTrue(container.getTecho());
	}
	
	//HacerTrayecto
	@Test
	public void testHacerTrayecto() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		Puerto destino=new Puerto("ES-MAD");
		container.hacerTrayecto(destino);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testHacerTrayectoPuertoNull() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		Puerto destino=null;
		container.hacerTrayecto(destino);
	}
	
	@Test
	public void testHacerViajesSimple() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		
		Puerto origenpuerto = new Puerto("ES-BAR");
		Puerto destinopuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50); //Muelle
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);

		origenMuelle.asignarPlaza(container, 0);
		origenpuerto.addMuelle(origenMuelle);
		
		Trayecto nuevoTrayecto = new TCamion(origenMuelle, origenpuerto, "2020-11-19", destinoMuelle, destinopuerto,"2020-12-31");
		
		container.hacerViajes(nuevoTrayecto);
	}
	
	@Test
	public void testHacerViajesCompuesto() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		
		Puerto origenpuerto = new Puerto("ES-BAR");
		Puerto destinopuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50); //Muelle
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);

		origenMuelle.asignarPlaza(container, 0);
		origenpuerto.addMuelle(origenMuelle);
		
		Trayecto nuevoTrayecto = new PackCamionBarco(1, origenMuelle, origenpuerto, "2020-11-19", destinoMuelle, destinopuerto,"2020-12-31");
		
		container.hacerViajes(nuevoTrayecto);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testHacerViajesTrayectoNull() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		
		Puerto origenpuerto = new Puerto("ES-BAR");
		Puerto destinopuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50); //Muelle
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);

		origenMuelle.asignarPlaza(container, 0);
		origenpuerto.addMuelle(origenMuelle);
		
		Trayecto nuevoTrayecto = null;
		container.hacerViajes(nuevoTrayecto);
	}
	
	//Aqui no da error seria origenigualfin
	@Test(expected=IllegalArgumentException.class) 
	public void testHacerViajesDestinoIgualFin() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50); //Muelle
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);

		origenMuelle.asignarPlaza(container, 0);
		origenPuerto.addMuelle(origenMuelle);
		
		container.setDestinoFinal(destinoPuerto);
		
		Trayecto nuevoTrayecto = new TCamion(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayecto);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testHacerViajeNoEstaElContenedor() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50); //Muelle
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		
		Trayecto nuevoTrayecto = new TCamion(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayecto);
	}
	
	@Test
	public void testPrecio() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
	
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		
		origenMuelle.asignarPlaza(container, 0);
		origenPuerto.addMuelle(origenMuelle);
		
		container.hacerTrayecto(destinoPuerto);
		Trayecto nuevoTrayecto = new TCamion(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayecto);
		
		double precioTrayecto=200+4.5*coordenadaOrigen.getDistanceTo(coordenadaDestino);
		assertEquals(container.precio(),precioTrayecto,0.0);
	}
	
	@Test
	public void testGetEspacioEstandar() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		assertEquals(container.getEspacio(), 1);
	}
	
	@Test
	public void testGetEspacioRefrigerado() {
		Contenedor container=new Refrigerado("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		assertEquals(container.getEspacio(), 1);
	}
	
	@Test
	public void testGetEspacioFlatRack() {
		Contenedor container=new FlatRack("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		assertEquals(container.getEspacio(), 2);
	}

	@Test
	public void testGetCodigoTransporteEstandar() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		int[] codigoPrueba = new int[] {1,1,1};
		
		assertTrue(Arrays.equals(container.getCodigoTransporte(),codigoPrueba));
	}
	
	@Test
	public void testGetCodigoTransporteRefrigerado() {
		Contenedor container=new Refrigerado("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		int[] codigoPrueba=new int [] {1,0,1};
		System.out.println(container.getCodigoTransporte()==codigoPrueba);
		assertTrue(Arrays.equals(container.getCodigoTransporte(),codigoPrueba));
	}
	
	@Test
	public void testGetCodigoTransporteFlatRack() {
		Contenedor container=new FlatRack("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		int[] codigoPrueba = new int [] {1,1,0};;
		
		assertTrue(Arrays.equals(container.getCodigoTransporte(),codigoPrueba));
	}	
}