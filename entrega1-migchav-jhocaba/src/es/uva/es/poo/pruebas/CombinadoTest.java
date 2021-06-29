package es.uva.es.poo.pruebas;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;
import es.uva.es.poo.clases.*;
import es.uva.inf.poo.maps.GPSCoordinate;

public class CombinadoTest {	
	
	@SuppressWarnings("unused")
	@Test
	public void testConstructorPackCamionTren() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Combinado trayecto = new PackCamionTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
	}
	
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
		Simple trayectoTren= new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayecto.addTrayecto(trayectoTren);
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
		Combinado trayecto = new PackCamionTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
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
		
		Combinado trayecto = new PackCamionTren(origenMuelleCombinado, origenPuertoCombinado, "2020-11-19", destinoMuelleCombinado, destinoPuertoCombinado,"2020-12-31");
		Simple trayectoTren = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayecto.addTrayecto(trayectoTren);
		trayecto.addTrayecto(trayectoTren);
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
		Combinado trayecto = new PackCamionTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		Simple trayectoTren = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
	
		
		Puerto origenPuertoCombinado = new Puerto("ES-MAD");
		Puerto destinoPuertoCombinado = new Puerto("ES-VIT");
		GPSCoordinate coordenadaOrigenCombinado=new GPSCoordinate(80.5,81.5);
		Muelle origenMuelleCombinado=new Muelle(111, 34,coordenadaOrigen,'O',70);
		GPSCoordinate coordenadaDestinoCombinado=new GPSCoordinate(23.5,56.5);
		Muelle destinoMuelleCombinado=new Muelle(111, 10,coordenadaDestino,'O',48);
		origenPuertoCombinado.addMuelle(origenMuelleCombinado);
		destinoPuertoCombinado.addMuelle(destinoMuelleCombinado);
		
		Simple trayectoMas = new TCamion(origenMuelleCombinado, origenPuertoCombinado, "2020-11-19", destinoMuelleCombinado, destinoPuertoCombinado,"2020-12-31");
		
		trayecto.addTrayecto(trayectoTren);
		trayecto.addTrayecto(trayectoMas);
		
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testAddTrayectoCamionTrenSize() {
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
		
		Combinado trayecto = new PackCamionTren(origenMuelleCombinado, origenPuertoCombinado, "2020-11-19", destinoMuelleCombinado, destinoPuertoCombinado,"2020-12-31");
		Simple trayectoTren1 = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		Simple trayectoCamion1 = new TCamion(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		Simple trayectoTren2= new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");

		trayecto.addTrayecto(trayectoTren1);
		trayecto.addTrayecto(trayectoCamion1);
		trayecto.addTrayecto(trayectoTren2);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testAddTrayectoCamionTrenEnBarco() {
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
	@Test(expected=IllegalArgumentException.class)
	public void testAddTrayectoCamionTrenTrenx2() {
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
		
		Combinado trayecto = new PackCamionTren(origenMuelleCombinado, origenPuertoCombinado, "2020-11-19", destinoMuelleCombinado, destinoPuertoCombinado,"2020-12-31");		
		
		Simple trayectoTren1 = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		Simple trayectoTren2 = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayecto.addTrayecto(trayectoTren1);
		trayecto.addTrayecto(trayectoTren2);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testAddTrayectoCamionTrenCamionx2() {
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
		
		Combinado trayecto = new PackCamionTren(origenMuelleCombinado, origenPuertoCombinado, "2020-11-19", destinoMuelleCombinado, destinoPuertoCombinado,"2020-12-31");		
		
		Simple trayectoCamion1 = new TCamion(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		Simple trayectoCamion2 = new TCamion(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayecto.addTrayecto(trayectoCamion1);
		trayecto.addTrayecto(trayectoCamion2);
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
		Combinado trayecto = new PackCamionTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		Simple trayectoTren = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayecto.addTrayecto(trayectoTren);
		List<Simple> trayectosPack=trayecto.getTrayectosPack();
		assertEquals(trayectosPack.get(0).getDistancia(),trayectoTren.getDistancia(),0.1);
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
	
	@SuppressWarnings("unused")
	@Test
	public void testCostePack() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Combinado trayecto = new PackCamionTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		Simple trayectoTren = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayecto.addTrayecto(trayectoTren);
		double costeTren=10*trayectoTren.getDistancia()*1.852;
		assertEquals(trayecto.costeTrayecto(),costeTren,0.1);
	}
	@SuppressWarnings("unused")
	@Test
	public void testCostePackBarco() {
		Puerto origenPuerto = new Puerto("ES-BAR");
		Puerto destinoPuerto = new Puerto("ES-VAL");
		GPSCoordinate coordenadaOrigen=new GPSCoordinate(40.5,40.5);
		Muelle origenMuelle=new Muelle(111, 12,coordenadaOrigen,'O',50);
		GPSCoordinate coordenadaDestino=new GPSCoordinate(50.5,50.5);
		Muelle destinoMuelle=new Muelle(111, 15,coordenadaDestino,'O',50);
		origenPuerto.addMuelle(origenMuelle);
		destinoPuerto.addMuelle(destinoMuelle);
		Combinado trayecto = new PackCamionTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		Simple trayectoTren = new TTren(origenMuelle, origenPuerto, "2020-11-19", destinoMuelle, destinoPuerto,"2020-12-31");
		trayecto.addTrayecto(trayectoTren);
		double precio=10*trayectoTren.getDistancia()*1.852;
		assertEquals(trayecto.costeTrayecto(),precio,0.1);
	}
}
		

