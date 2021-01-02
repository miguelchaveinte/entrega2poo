package es.uva.es.poo.pruebas;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import org.junit.Test;

import es.uva.es.poo.clases.*;
import es.uva.inf.poo.maps.GPSCoordinate;

public class PackCamionBarcoTest {

	@Test
	public void testConstructorPackCamionBarco() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		PackCamionBarco trayectoBarco = new PackCamionBarco(0, origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertNotNull(trayectoBarco);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorPackCamionBarcoTipoTrayectoInvalido() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		PackCamionBarco trayectoBarco = new PackCamionBarco(3, origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
	}
	
	
	@Test
	public void testGetCodigoSimplePackCamionBarco() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		PackCamionBarco trayectoTren = new PackCamionBarco(1, origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertEquals(trayectoTren.getCodigoSimple(), 1);
	}
	
	@Test
	public void testgetTipoPackCamionBarco() {
		int[] packPrueba=new int [] {1,0,1};
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		PackCamionBarco trayectoPack = new PackCamionBarco(1,origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertTrue(Arrays.equals(trayectoPack.getTipoPack(), packPrueba));
	}
	
	@Test
	public void testCosteTrayectoPackCamionBarco() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		PackCamionBarco trayectoBarco = new PackCamionBarco(0, origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		
		double precioTrayecto = (double)ChronoUnit.DAYS.between(LocalDate.parse("2020-11-19"),LocalDate.parse("2020-12-31"))*4000;
		assertEquals(trayectoBarco.costeTrayecto(),precioTrayecto*0.85,0.0);
	}
	
	@Test
	public void testCosteTrayectoPackCamionBarco1() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		PackCamionBarco trayectoTren = new PackCamionBarco(1, origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		
		double coste=20+12.5*coordenadaOrigen.getDistanceTo(coordenadaDestino);
		assertEquals(trayectoTren.costeTrayecto(), coste,0.0);
	}
	
	@Test
	public void testCosteTrayectoPackCamionBarco2() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		PackCamionBarco trayectoCamion = new PackCamionBarco(2, origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		
		double coste=200+4.5*coordenadaOrigen.getDistanceTo(coordenadaDestino);
		assertEquals(trayectoCamion.costeTrayecto(), coste,0.0);
	}
	
	

}
