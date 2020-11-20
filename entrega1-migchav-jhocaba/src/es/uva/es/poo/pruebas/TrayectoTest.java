package es.uva.es.poo.pruebas;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import es.uva.es.poo.clases.Muelle;
import es.uva.es.poo.clases.Puerto;
import es.uva.es.poo.clases.Trayecto;
import es.uva.inf.poo.maps.GPSCoordinate;

public class TrayectoTest {

	@Test
	public void testTrayectoInicializador() {
		Muelle origenmuelle = new Muelle();
		Muelle destinomuelle = new Muelle();
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
		assertNotNull(nuevo);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMuelleOrigenNull() {
		Muelle origenmuelle = null;
		Muelle destinomuelle = new Muelle();
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMuelleDestinoNull()   {
		Muelle origenmuelle = new Muelle();
		Muelle destinomuelle = null;
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPuertoOrigenNull()   {
		Muelle origenmuelle = new Muelle();
		Muelle destinomuelle = new Muelle();
		Puerto origenpuerto = null;
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
	}
	@Test
	public void testSetPuertoFinal() {
		Muelle origenmuelle = new Muelle();
		Muelle destinomuelle = new Muelle();
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
		Puerto finalrecorrido = new Puerto();
		nuevo.setPuertoFinal(finalrecorrido);
		
		assertEquals(nuevo.getPuertoFinal(), finalrecorrido);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetPuertoFinalNull()  {
		Muelle origenmuelle = new Muelle();
		Muelle destinomuelle = new Muelle();
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
		Puerto finalrecorrido = null;
	
		nuevo.setPuertoFinal(null);
	}
	
	@Test
	public void testGetPuertoFinal() {
		Muelle origenmuelle = new Muelle();
		Muelle destinomuelle = new Muelle();
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Puerto finalrecorrido = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
		assertNull(nuevo.getPuertoFinal());
	}
	@Test(expected=IllegalArgumentException.class)
	public void testPuertoDestinoNull()   {
		Muelle origenmuelle = new Muelle();
		Muelle destinomuelle = new Muelle();
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = null;
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
	}
	
	@Test(expected=DateTimeParseException.class)
	public void testFechaNull()   {
		Muelle origenmuelle = new Muelle();
		Muelle destinomuelle = new Muelle();
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto,"", destinomuelle, destinopuerto,"");
	}
	@Test(expected=DateTimeParseException.class)
	public void testFechaInvalida()   {
		Muelle origenmuelle = new Muelle();
		Muelle destinomuelle = new Muelle();
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto,"2001-31-12", destinomuelle, destinopuerto,"");
	}
	
	@Test
	public void testGetMuelleOrigen()  {
		Muelle origenmuelle = new Muelle();
		Muelle destinomuelle = new Muelle();
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
		assertEquals(nuevo.getMuelleOrigen(), origenmuelle);
	}
	
	@Test
	public void testGetMuelleDestino()  {
		Muelle origenmuelle = new Muelle();
		Muelle destinomuelle = new Muelle();
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
		assertEquals(nuevo.getMuelleDestino(), destinomuelle);
	}
	
	@Test
	public void testGetPuertoOrigen()  {
		Muelle origenmuelle = new Muelle();
		Muelle destinomuelle = new Muelle();
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
		assertEquals(nuevo.getPuertoOrigen(), origenpuerto);
	}
	
	@Test
	public void testGetPuertoDestino()  {
		Muelle origenmuelle = new Muelle();
		Muelle destinomuelle = new Muelle();
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
		assertEquals(nuevo.getPuertoDestino(), destinopuerto);
	}
	
	@Test
	public void testGetFechaInicio()  {
		Muelle origenmuelle = new Muelle();
		Muelle destinomuelle = new Muelle();
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
		assertEquals(nuevo.getFechaIni(), LocalDate.parse("2020-11-19"));
	}
	
	@Test
	public void testGetFechaFin()  {
		Muelle origenmuelle = new Muelle();
		Muelle destinomuelle = new Muelle();
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
		assertEquals(nuevo.getFechaFin(), LocalDate.parse("2020-12-31"));
	}
	
	@Test
	public void testFechaCorrecta()  {
		Muelle origenmuelle = new Muelle();
		Muelle destinomuelle = new Muelle();
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
		assertTrue(nuevo.fechaCorrecta(LocalDate.now()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFechaCorrectaNull() {
		Muelle origenmuelle = null;
		Muelle destinomuelle = new Muelle();
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
		nuevo.fechaCorrecta(null);
	}
	
	@Test
	public void testGetDistancia()  {
		GPSCoordinate coordenadaorigen = new GPSCoordinate(41.345, 2.14167); //Barcelona
		Muelle origenmuelle =new Muelle(20,coordenadaorigen,'F',5);
		GPSCoordinate coordenadadestino =new GPSCoordinate(39.4457, -0.319878); //Valencia
		Muelle destinomuelle =new Muelle(20,coordenadadestino,'F',5);
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
		
		assertEquals(nuevo.getDistancia(), 160.30315751787168, 0.0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetDistanciaCoordenadaNull()  {
		Muelle origenmuelle =new Muelle(20,null,'F',5);
		Muelle destinomuelle =new Muelle(20,null,'F',5);
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
		nuevo.getDistancia();
	}
	
	//TODO TestInfoTrayecto
	@Test
	public void testCosteTrayecto()  {
		GPSCoordinate coordenadaorigen = new GPSCoordinate(41.345, 2.14167); //Barcelona
		Muelle origenmuelle =new Muelle(20,coordenadaorigen,'F',5);
		GPSCoordinate coordenadadestino =new GPSCoordinate(39.4457, -0.319878); //Valencia
		Muelle destinomuelle =new Muelle(20,coordenadadestino,'F',5);
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-18", destinomuelle, destinopuerto,"2020-11-30");
		double coste=nuevo.costeTrayecto(100, 100);
		assertEquals(coste, 3.56257737267718E7 ,0.0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCosteTrayectoPrecioNegativo()  {
		GPSCoordinate coordenadaorigen = new GPSCoordinate(41.345, 2.14167);
		Muelle origenmuelle =new Muelle(20,coordenadaorigen,'F',5);
		GPSCoordinate coordenadadestino =new GPSCoordinate(39.4457, -0.319878);
		Muelle destinomuelle =new Muelle(20,coordenadadestino,'F',5);
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-12-31");
		
		assertEquals(nuevo.costeTrayecto(-5, -5), 0, 0.0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCosteTrayectoPrecioNulo()  {
		GPSCoordinate coordenadaorigen = new GPSCoordinate(41.345, 2.14167);
		Muelle origenmuelle =new Muelle(20,coordenadaorigen,'F',5);
		GPSCoordinate coordenadadestino =new GPSCoordinate(39.4457, -0.319878);
		Muelle destinomuelle =new Muelle(20,coordenadadestino,'F',5);
		Puerto origenpuerto = new Puerto();
		Puerto destinopuerto = new Puerto();
		Trayecto nuevo = new Trayecto(origenmuelle, origenpuerto, "2020-11-19", destinomuelle, destinopuerto,"2020-11-29");
		
		assertEquals(nuevo.costeTrayecto(0, 0), 0, 0.0);
	}
	
	
	
}