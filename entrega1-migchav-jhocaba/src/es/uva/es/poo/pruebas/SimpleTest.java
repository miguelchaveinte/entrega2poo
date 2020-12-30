package es.uva.es.poo.pruebas;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import org.junit.Test;
import es.uva.es.poo.clases.*;
import es.uva.inf.poo.maps.GPSCoordinate;

public class SimpleTest {	
	@Test
	public void testCosteTrayectoBarco() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Simple trayectoBarco = new TBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		
		double precioTrayecto = (double)ChronoUnit.DAYS.between(LocalDate.parse("2020-11-19"),LocalDate.parse("2020-12-31"))*4000;
		assertEquals(trayectoBarco.costeTrayecto(),precioTrayecto,0.0);
	}
	
	@Test
	public void testCosteTrayectoTren() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Simple trayectoTren = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		
		double precioTrayecto=20+12.5*coordenadaOrigen.getDistanceTo(coordenadaDestino);
		assertEquals(trayectoTren.costeTrayecto(),precioTrayecto,0.0);
	}
	
	@Test
	public void testCosteTrayectoCamion() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Simple trayectoCamion = new TCamion(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		
		double precioTrayecto=200+4.5*coordenadaOrigen.getDistanceTo(coordenadaDestino);
		assertEquals(trayectoCamion.costeTrayecto(),precioTrayecto,0.0);
	}
	
	@Test
	public void testGetTipoPack() {
		int[] codigoPrueba=new int [] {0,0,0};
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Simple trayectoBarco = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertTrue(Arrays.equals(trayectoBarco.getTipoPack(), codigoPrueba));
	}
	
	@Test
	public void testGetCodigoSimpleBarco() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Trayecto trayectoBarco = new TBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertEquals(trayectoBarco.getCodigoSimple(), 0);
	}
	
	@Test
	public void testGetCodigoSimpleTren() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Simple trayectoTren = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertEquals(trayectoTren.getCodigoSimple(), 1);
	}
	
	@Test
	public void testGetCodigoSimpleCamion() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Simple trayectoCamion = new TCamion(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertEquals(trayectoCamion.getCodigoSimple(), 2);
	}
	
	@Test
	public void testGetInicioFech() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Simple trayectoCamion = new TCamion(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertEquals(trayectoCamion.getInicioFech(), "2020-11-19");
	}
	
	@Test
	public void testGetFinFech() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Simple trayectoCamion = new TCamion(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertEquals(trayectoCamion.getFinFech(), "2020-12-31");
	}	
}
