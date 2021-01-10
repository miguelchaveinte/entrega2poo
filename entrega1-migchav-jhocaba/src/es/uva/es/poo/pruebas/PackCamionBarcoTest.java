package es.uva.es.poo.pruebas;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;
import es.uva.es.poo.clases.*;
import es.uva.inf.poo.maps.GPSCoordinate;

public class PackCamionBarcoTest {	
	

	@SuppressWarnings("unused")
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
		Combinado trayecto = new PackCamionBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testAddTrayectoCamionTren() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Combinado trayecto = new PackCamionTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		Simple trayectoBarco = new TBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayecto.addTrayecto(trayectoBarco);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testAddTrayectoCamionBarco() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Combinado trayecto = new PackCamionBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		Simple trayectoBarco = new TBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayecto.addTrayecto(trayectoBarco);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testAddTrayectoCamionTrenNull() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Combinado trayecto = new PackCamionBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayecto.addTrayecto(null);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testAddTrayectoCamionTrenContenido() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Puerto origenPuertoCombinado = new Puerto("ES-MAD");
		Puerto destinoPuertoCombinado = new Puerto("ES-VIT");
		GPSCoordinate coordenadaOrigenCombinado=new GPSCoordinate(80.5,81.5);
		Muelle origenMuelleCombinado=new Muelle(111, 34,coordenadaOrigen,'O',70);
		GPSCoordinate coordenadaDestinoCombinado=new GPSCoordinate(23.5,56.5);
		Muelle destinoMuelleCombinado=new Muelle(111, 10,coordenadaDestino,'O',48);
		origenPuertoCombinado.addMuelle(origenMuelleCombinado);
		destinoPuertoCombinado.addMuelle(destinoMuelleCombinado);
		
		Combinado trayecto = new PackCamionBarco(origenMuelleCombinado, origenPuertoCombinado, "2020-11-19", destinoMuelleCombinado, destinoPuertoCombinado,"2020-12-31");
		Simple trayectoBarco = new TBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayecto.addTrayecto(trayectoBarco);
		trayecto.addTrayecto(trayectoBarco);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testAddTrayectoCamionTrenRealizado() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Combinado trayecto = new PackCamionBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		Simple trayectoBarco = new TBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
	
		
		Puerto origenPuertoCombinado = new Puerto("ES-MAD");
		Puerto destinoPuertoCombinado = new Puerto("ES-VIT");
		GPSCoordinate coordenadaOrigenCombinado=new GPSCoordinate(80.5,81.5);
		Muelle origenMuelleCombinado=new Muelle(111, 34,coordenadaOrigen,'O',70);
		GPSCoordinate coordenadaDestinoCombinado=new GPSCoordinate(23.5,56.5);
		Muelle destinoMuelleCombinado=new Muelle(111, 10,coordenadaDestino,'O',48);
		origenPuertoCombinado.addMuelle(origenMuelleCombinado);
		destinoPuertoCombinado.addMuelle(destinoMuelleCombinado);
		
		Simple trayectoMas = new TBarco(origenMuelleCombinado, origenPuertoCombinado, "2020-11-19", destinoMuelleCombinado, destinoPuertoCombinado,"2020-12-31");
		
		trayecto.addTrayecto(trayectoBarco);
		trayecto.addTrayecto(trayectoMas);
		
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testContainsTrayecto() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Combinado trayecto = new PackCamionBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		Simple trayectoBarco = new TBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayecto.addTrayecto(trayectoBarco);
		assertTrue(trayecto.containsTrayecto(trayectoBarco));
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testContainsTrayectoNull() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Combinado trayecto = new PackCamionBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		Simple trayectoBarco = new TBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayecto.addTrayecto(trayectoBarco);
		assertFalse(trayecto.containsTrayecto(null));
	}
	@SuppressWarnings("unused")
	@Test
	public void testGetTrayectoPack() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Combinado trayecto = new PackCamionBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		Simple trayectoBarco = new TBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayecto.addTrayecto(trayectoBarco);
		List<Simple> trayectosPack=trayecto.getTrayectosPack();
		assertEquals(trayectosPack.get(0).costeTrayecto(),trayectoBarco.costeTrayecto(),0.1);
	}	
	@SuppressWarnings("unused")
	@Test
	public void testTrayectoRealizado() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Combinado trayecto = new PackCamionBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		Simple trayectoBarco = new TBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayecto.addTrayecto(trayectoBarco);
		assertTrue(trayecto.trayectoRealiazado());
	}
	@Test
	public void testCosteTrayectoPack()  {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen = new GPSCoordinate(41.345, 2.14167); //Barcelona
		Muelle origenMuelle =new Muelle(111, 20,coordenadaOrigen,'O',5);
		GPSCoordinate coordenadaDestino =new GPSCoordinate(39.4457, -0.319878); //Valencia
		Muelle destinoMuelle =new Muelle(111, 20,coordenadaDestino,'O',5);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Combinado trayectoNuevoCompuesto=new PackCamionBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		Simple enBarco=new TBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayectoNuevoCompuesto.addTrayecto(enBarco);		
		assertEquals(trayectoNuevoCompuesto.costeTrayecto(),enBarco.costeTrayecto()*0.85,0.0);
	}
	@Test
	public void testCosteTrayectoPackNoBarco()  {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen = new GPSCoordinate(41.345, 2.14167); //Barcelona
		Muelle origenMuelle =new Muelle(111, 20,coordenadaOrigen,'O',5);
		GPSCoordinate coordenadaDestino =new GPSCoordinate(39.4457, -0.319878); //Valencia
		Muelle destinoMuelle =new Muelle(111, 20,coordenadaDestino,'O',5);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		
		Combinado trayectoNuevoCompuesto=new PackCamionBarco(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		Simple enTren=new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayectoNuevoCompuesto.addTrayecto(enTren);		
		assertEquals(trayectoNuevoCompuesto.costeTrayecto(),enTren.costeTrayecto(),0.0);
	}
}
