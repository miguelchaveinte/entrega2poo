package es.uva.es.poo.pruebas;

import java.util.Arrays;
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
		Contenedor container=new Refrigerado("CSQU3055380",500.0,"Kg",200.0,100.0,"ft3");
		assertNotNull(container);
	}
	
	@Test
	public void testConstructorFlatRack() {
		Contenedor container =new FlatRack("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		assertNotNull(container);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorIdentificadorInvalido() {
		Contenedor container=new Estandar("AAAJ3054383",500.0,"Kg",200.0,100.0,"ft3");		
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorIdentificadorMinusculas() {
		Contenedor container=new Estandar("CSqU3054383",500.0,"Kg",200.0,100.0,"ft3");		
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorIdentificadorMenosNumeros() {
		Contenedor container=new Estandar("CSQU354383",500.0,"Kg",200.0,100.0,"ft3");		
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorIdentificadorMasLongitud() {
		Contenedor container=new Estandar("CSQU30543836",500.0,"Kg",200.0,100.0,"ft3");		
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorIdentificadorMenosLongitud() {
		Contenedor container=new Estandar("CSQU305438",500.0,"Kg",200.0,100.0,"ft3");		
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorCargaNegativa() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",-200.0,100.0,"ft3");
	}
	
	@Test
	public void testSetDestinoFinal() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		Puerto puertoPrueba=new Puerto("ES-MAD");
		container.hacerTrayecto(puertoPrueba);
		assertEquals(container.getDestinoFinal(), puertoPrueba);
	}
	
	@Test
	public void testGetCarga() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		assertEquals(container.getCarga(), 200.0,0.0);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testComprobarUnidadesPesoKg() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testComprobarUnidadesPesolb() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"lb",200.0,100.0,"ft3");
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesPesolbNegativo() {
		Contenedor container=new Estandar("CSQU3054383",-500.0,"Kg",200.0,100.0,"ft3");
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesPesoNoValidas() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kb",200.0,100.0,"ft3");
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesPesoNoValida() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"lg",200.0,100.0,"ft3");
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
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesPesoLibrasNegativo() {
		Contenedor container=new Estandar("CSQU3054383",-500.0,"lb",200.0,100.0,"ft3");
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetPesoLibrasNegativo() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"lb",200.0,100.0,"ft3");
		container.setPesoLibra(-300.0);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesPesoVacio() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"",200.0,100.0,"ft3");
	}
	
	@Test
	public void testConviertePesoKilo() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		container.setPesoLibra(500.0);
		assertEquals(container.getPesoLibra(),499.99999999999994,0.0);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testConviertePesoKiloNegativo() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		container.setPesoKilo(-500.0);
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
	
	@SuppressWarnings("unused")
	@Test
	public void testComprobarUnidadesVolumenm3() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testComprobarUnidadesVolumenft3() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
	}
		
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesVolumenNoValidas() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"fT3");
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesVolumenNoValida() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"fm3");
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testSetVolumenPiesNegativo() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"ft3");
		container.setVolumenPies(-300.0);
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
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarUnidadesVolumenVacio() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"",200.0,100.0,"");
	}
	
	@Test
	public void testConvierteVolumenMetros() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,300.0,"ft3");
		container.setVolumenMetros(300.0);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testConvierteVolumenMetrosNegativo() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,300.0,"ft3");
		container.setVolumenMetros(-300);
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
		container.setVolumenMetros(-100.0);
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
		Contenedor containerNoTecho=new FlatRack("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		assertTrue(container.getTecho());
		assertFalse(containerNoTecho.getTecho());
	}
	@Test
	public void testSetNoTecho() {
		Contenedor container=new FlatRack("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		assertFalse(container.getTecho());
	}

	@Test
	public void testGetTecho() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		assertTrue(container.getTecho());
	}
	

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
		Puerto destino=new Puerto("ES-MAD");
		container.hacerTrayecto(destino);
		
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);

		origenMuelle.asignarPlaza(container, 0);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto nuevoTrayectoSimple = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayectoSimple);
	}
	
	@Test
	public void testHacerViajesSimpleEstadoRecogida() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3"); 
		Puerto destino=new Puerto("ES-MAD");
		container.hacerTrayecto(destino);
		
		Puerto origenPuerto = new Puerto("ES-BAR");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);

		origenMuelle.asignarPlaza(container, 0);
		origenPuerto.addMuelle(origenMuelle);
		destino.addMuelle(destinoMuelle);
		
		Trayecto nuevoTrayectoSimple = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destino,"2020-12-31");
		container.hacerViajes(nuevoTrayectoSimple);
		assertTrue(container.getEstado());
	}

	
	@Test
	public void testHacerViajesPackCamionTren() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3"); 
		
		Puerto destino=new Puerto("ES-MAD");
		container.hacerTrayecto(destino);
		
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);

		origenMuelle.asignarPlaza(container, 1);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto nuevoTrayectoPack = new PackCamionTren(2,origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayectoPack);
		
		Trayecto nuevoTrayectoSimpleCamion = new TCamion(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayectoSimpleCamion);
		
		Trayecto nuevoTrayectoSimpleTren = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayectoSimpleTren);
	}
	
	@Test
	public void testHacerViajesPackCamionBarco() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3"); 
		
		Puerto destino=new Puerto("ES-MAD");
		container.hacerTrayecto(destino);
		
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);

		origenMuelle.asignarPlaza(container, 0);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto nuevoTrayectoPack = new PackCamionBarco(1,origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayectoPack);
		
		Trayecto nuevoTrayectoSimpleCamion = new TCamion(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayectoSimpleCamion);
		
		Trayecto nuevoTrayectoSimpleBarco = new TBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayectoSimpleBarco);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testHacerViajesSimpleTrayectoInvalido() {
		Contenedor container=new Refrigerado("CSQU3054383",500.0,"Kg",200.0,100.0,"m3"); 
		Puerto destino=new Puerto("ES-MAD");
		container.hacerTrayecto(destino);
		
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);

		origenMuelle.asignarPlaza(container, 0);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto nuevoTrayectoSimple = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayectoSimple);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testHacerViajesSimpleRepetido() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3"); 
		
		Puerto destino=new Puerto("ES-MAD");
		container.hacerTrayecto(destino);
		
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);

		origenMuelle.asignarPlaza(container, 0);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto nuevoTrayectoSimple = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayectoSimple);
		container.hacerViajes(nuevoTrayectoSimple);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testHacerViajesTrayectoNull() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		
		Puerto destino=new Puerto("ES-MAD");
		container.hacerTrayecto(destino);
		
		Puerto origenpuerto = new Puerto("ES-BAR");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);

		origenMuelle.asignarPlaza(container, 0);
		origenpuerto.addMuelle(origenMuelle);
		
		Trayecto nuevoTrayecto = null;
		container.hacerViajes(nuevoTrayecto);
	}
	

	@Test(expected=IllegalArgumentException.class) 
	public void testHacerViajesOrigenIgualFin() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		
		Puerto destino=new Puerto("ES-MAD");
		container.hacerTrayecto(destino);
		
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50); //Muelle
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);

		origenMuelle.asignarPlaza(container, 0);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		container.hacerTrayecto(origenPuerto);
		
		Trayecto nuevoTrayecto = new TCamion(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayecto);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testHacerViajeNoEstaElContenedor() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		
		Puerto destino=new Puerto("ES-MAD");
		container.hacerTrayecto(destino);
		
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50); //Muelle
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		
		Trayecto nuevoTrayecto = new TCamion(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayecto);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testHacerViajeNoPosibleInfraetructuraOrigen() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		
		Puerto destino=new Puerto("ES-MAD");
		container.hacerTrayecto(destino);
		
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(100, 12,coordenadaOrigen,'O',50); //Muelle
		
		origenMuelle.asignarPlaza(container, 0);
		origenPuerto.addMuelle(origenMuelle);
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto nuevoTrayecto = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayecto);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testHacerViajeNoPosibleInfraetructuraDestino() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		
		Puerto destino=new Puerto("ES-MAD");
		container.hacerTrayecto(destino);
		
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50); //Muelle
		
		origenMuelle.asignarPlaza(container, 0);
		origenPuerto.addMuelle(origenMuelle);
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(100, 15,coordenadaDestino,'O',50);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto nuevoTrayecto = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayecto);
	}
	
	@Test
	public void testPrecioTrayectoSimple() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		
		Puerto destino=new Puerto("ES-MAD");
		container.hacerTrayecto(destino);
		
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		
		origenMuelle.asignarPlaza(container, 0);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		container.hacerTrayecto(destinoPuerto);
		Trayecto nuevoTrayecto = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayecto);
		
		double precioTrayecto = 20+12.5*coordenadaOrigen.getDistanceTo(coordenadaDestino);
		assertEquals(container.precio(),precioTrayecto,0.0);
	}
	
	@Test
	public void testPrecioTrayectoCompuesto() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		
		Puerto destino=new Puerto("ES-MAD");
		container.hacerTrayecto(destino);
		
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		
		origenMuelle.asignarPlaza(container, 0);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto nuevoTrayectoSimple = new TCamion(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayectoSimple);
		
		Trayecto nuevoTrayecto = new PackCamionTren(1, origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayecto);
		
		double precio1 = 200+4.5*coordenadaOrigen.getDistanceTo(coordenadaDestino);
		double precioTrayecto=10*coordenadaOrigen.getDistanceTo(coordenadaDestino) + precio1;

		assertEquals(container.precio(),precioTrayecto,0.0);
	}
	
	@Test
	public void testPrecioTrayectoCompuestoDescuentos() {
		Contenedor container=new Estandar("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		
		Puerto destino=new Puerto("ES-MAD");
		container.hacerTrayecto(destino);
		
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		
		origenMuelle.asignarPlaza(container, 0);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto nuevoTrayectoSimple = new TCamion(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		
		Trayecto nuevoTrayecto = new PackCamionTren(1, origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		container.hacerViajes(nuevoTrayecto);
		
		container.hacerViajes(nuevoTrayectoSimple);
		
		
		double precio1=10*coordenadaOrigen.getDistanceTo(coordenadaDestino);
		double precioTrayecto = 10*coordenadaOrigen.getDistanceTo(coordenadaDestino)+precio1;
		
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
		assertTrue(Arrays.equals(container.getCodigoTransporte(),codigoPrueba));
	}
	
	@Test
	public void testGetCodigoTransporteFlatRack() {
		Contenedor container=new FlatRack("CSQU3054383",500.0,"Kg",200.0,100.0,"m3");
		int[] codigoPrueba = new int [] {1,1,0};
		assertTrue(Arrays.equals(container.getCodigoTransporte(),codigoPrueba));
	}	
}