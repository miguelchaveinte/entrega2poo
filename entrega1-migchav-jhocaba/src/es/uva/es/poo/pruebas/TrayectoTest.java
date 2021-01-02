package es.uva.es.poo.pruebas;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import static org.junit.Assert.*;


import org.junit.Test;

import es.uva.es.poo.clases.*;
import es.uva.inf.poo.maps.GPSCoordinate;

public class TrayectoTest {

	@Test
	public void testTrayectoInicializador() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertNotNull(trayectoNuevo);
	}
	
	@Test
	public void testTrayectoInicializadorCompuesto() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto trayectoNuevo = new PackCamionTren(2, origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertNotNull(trayectoNuevo);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testTrayectoNoMuelleEnPuertoOrigen() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);

		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertNotNull(trayectoNuevo);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTrayectoNoMuelleEnPuertoDestino() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);

		
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertNotNull(trayectoNuevo);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMuelleOrigenNull() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto trayectoNuevo = new TTren(null, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertNotNull(trayectoNuevo);
	}
		
	@Test(expected=IllegalArgumentException.class)
	public void testMuelleDestinoNull()   {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "2020-11-19", null, destinoPuerto,"2020-12-31");
		assertNotNull(trayectoNuevo);

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPuertoOrigenNull()   {
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		destinoPuerto.addMuelle(destinoMuelle);
		Trayecto trayectoNuevo = new TTren(origenMuelle, null, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertNotNull(trayectoNuevo);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPuertoDestinoNull()   {
		Puerto origenPuerto = new Puerto("ES-BAR");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, null,"2020-12-31");
		assertNotNull(trayectoNuevo);
	}
	
	@Test(expected=DateTimeParseException.class)
	public void testFechaNull()   {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "", destinoMuelle, destinoPuerto,"");
		assertNotNull(trayectoNuevo);
	}
	
	@Test(expected=DateTimeParseException.class)
	public void testFechaInvalida()   {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "1111-123-12", destinoMuelle, destinoPuerto,"0000-00-00");
		assertNotNull(trayectoNuevo);
	}

	
	@Test
	public void testGetMuelleOrigen()  {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertEquals(trayectoNuevo.getMuelleOrigen(), origenMuelle);
	}
	
	@Test
	public void testGetMuelleDestino()  {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertEquals(trayectoNuevo.getMuelleDestino(), destinoMuelle);
	}
	
	@Test
	public void testGetPuertoOrigen()  {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertEquals(trayectoNuevo.getPuertoOrigen(), origenPuerto);
	}
	
	@Test
	public void testGetPuertoDestino()  {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertEquals(trayectoNuevo.getPuertoDestino(), destinoPuerto);
	}
	
	@Test
	public void testGetFechaInicio()  {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertEquals(trayectoNuevo.getFechaIni(), LocalDate.parse("2020-11-19"));
	}
	
	@Test
	public void testGetFechaFin()  {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertEquals(trayectoNuevo.getFechaFin(), LocalDate.parse("2020-12-31"));
	}

	@Test
	public void testFechaCorrecta()  {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "2021-05-19", destinoMuelle, destinoPuerto,"2021-05-30");
		assertTrue(trayectoNuevo.fechaCorrecta(LocalDate.now()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFechaCorrectaNull() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayectoNuevo.fechaCorrecta(null);
	}
	
	@Test
	public void testGetDistancia()  {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertEquals(trayectoNuevo.getDistancia(), 732.2205958333337, 0.0);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetDistanciaCoordenadaNull()  {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		Muelle origenMuelle=new Muelle(111, 12,null,'O',50);
		Muelle destinoMuelle=new Muelle(111, 15,null,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayectoNuevo.getDistancia();
	}
	
	@Test
	public void testCosteTrayectoTrayecto()  {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen = new GPSCoordinate(41.345, 2.14167); //Barcelona
		Muelle origenMuelle =new Muelle(111, 20,coordenadaOrigen,'O',5);
		GPSCoordinate coordenadaDestino =new GPSCoordinate(39.4457, -0.319878); //Valencia
		Muelle destinoMuelle =new Muelle(111, 20,coordenadaDestino,'O',5);
		
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto trayectoNuevo = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");

		double precioTrayecto=20+12.5*coordenadaOrigen.getDistanceTo(coordenadaDestino);
		
		assertEquals(trayectoNuevo.costeTrayecto(),precioTrayecto,0.0);
	}
	
	@Test
	public void testCosteTrayectoCompuesto()  {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen = new GPSCoordinate(41.345, 2.14167); //Barcelona
		Muelle origenMuelle =new Muelle(111, 20,coordenadaOrigen,'O',5);
		GPSCoordinate coordenadaDestino =new GPSCoordinate(39.4457, -0.319878); //Valencia
		Muelle destinoMuelle =new Muelle(111, 20,coordenadaDestino,'O',5);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Trayecto trayectoNuevoCompuesto=new PackCamionBarco(0,origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		double precioTrayecto= (double)ChronoUnit.DAYS.between(LocalDate.parse("2020-11-19"),LocalDate.parse("2020-12-31"))*4000;
		assertEquals(trayectoNuevoCompuesto.costeTrayecto(),precioTrayecto*0.85,0.0);
	}
	
	@Test
	public void testgetTipoPackCamion() {
		int[] packPrueba=new int [] {0,0,0};
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Trayecto trayectoPack = new TCamion(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertTrue(Arrays.equals(trayectoPack.getTipoPack(), packPrueba));
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
		Trayecto trayectoPack = new PackCamionBarco(1,origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertTrue(Arrays.equals(trayectoPack.getTipoPack(), packPrueba));
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
	public void testGetCodigoSimplePackCamionBarco() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Trayecto trayectoPack = new PackCamionBarco(0,origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		assertEquals(trayectoPack.getCodigoSimple(), 0);
	}
	
	
	@Test
	public void testInfoTrayecto() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Trayecto trayectoPack = new PackCamionTren(1,origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayectoPack.infoTrayecto();
	}
	
	
}
