package es.uva.es.poo.pruebas;

import es.uva.es.poo.clases.*;
import es.uva.inf.poo.maps.GPSCoordinate;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

public class PuertoTest {

	@Test
	public void testConstructor() {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		
		assertNotNull(prueba);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorMasLongitud() {
		String identidad="ES-MADD";
		Puerto prueba=new Puerto(identidad);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorMenosLongitud() {
		String identidad="ES-MA";
		Puerto prueba=new Puerto(identidad);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorMinusculaPais() {
		String identidad="Es-MAD";
		Puerto prueba=new Puerto(identidad);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorMinusculaLocalidad() {
		String identidad="ES-MAd";
		Puerto prueba=new Puerto(identidad);
	}
	
	@Test(expected=NullPointerException.class) 
	public void testConstructorNull() {
		String identidad=null;
		Puerto prueba=new Puerto(identidad);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorSinGuion() {
		String identidad="ESMADD";
		Puerto prueba=new Puerto(identidad);
	}
	@Test
	public void testGetLocalidad() {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		
		assertEquals(prueba.getLocalidad(),"MAD");
	}
	@Test
	public void testGetPais() {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		
		assertEquals(prueba.getPais(),"ES");
	}
	@Test
	public void testAñadirMuelle() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(12,coordenada,'O',50);
		prueba.añadirMuelle(añadir);
		
		assertEquals(prueba.muellesOperativos().get(0),añadir);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testAñadirMuelleNull() {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		Muelle añadir=null;
		prueba.añadirMuelle(añadir);
	}
	@Test
	public void testEliminarMuelle() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(12,coordenada,'O',50);
		prueba.añadirMuelle(añadir);
		prueba.eliminarMuelle(20);
		prueba.eliminarMuelle(12);	
		assertTrue(prueba.muellesOperativos().isEmpty());
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testEliminarMuelleIdentidadNoValida() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(12,coordenada,'O',50);
		prueba.añadirMuelle(añadir);
		prueba.eliminarMuelle(100);
	}
	@Test
	public void testGetCompleto() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(12,coordenada,'O',50);
		prueba.añadirMuelle(añadir);
		boolean completo=prueba.getCompleto();
		prueba.eliminarMuelle(12);
		boolean completo2=prueba.getCompleto();
		assertFalse(completo);
		assertTrue(completo2);
	}
	
	@Test
	public void testMuellesOperativos() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(12,coordenada,'O',50);
		prueba.añadirMuelle(añadir);
		assertFalse(prueba.muellesOperativos().isEmpty());
		prueba.eliminarMuelle(12);
		Muelle añadir2=new Muelle(12,coordenada,'F',50);
		prueba.añadirMuelle(añadir2);
		assertTrue(prueba.muellesOperativos().isEmpty());
	}
	@Test
	public void testMuellesEspacio() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(12,coordenada,'O',50);
		prueba.añadirMuelle(añadir);
		assertFalse(prueba.muellesEspacio().isEmpty());
		prueba.eliminarMuelle(12);
		assertTrue(prueba.muellesEspacio().isEmpty());
	}
	
	@Test
	public void testMuellesCerca() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(12,coordenada,'O',50);
		prueba.añadirMuelle(añadir);
		GPSCoordinate coordenadaDistancia=new GPSCoordinate(40.0,40.0);
		GPSCoordinate coordenadaDistancia2=new GPSCoordinate(40.5,40.5);
		double distancia=4000.0;
		assertFalse(prueba.muellesCerca(coordenadaDistancia, distancia).isEmpty());
		assertFalse(prueba.muellesCerca(coordenadaDistancia2, distancia).isEmpty());
		double distanciaNo=25.0;
		assertTrue(prueba.muellesCerca(coordenadaDistancia, distanciaNo).isEmpty());
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testMuellesCercaCoordenadaNull() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(12,coordenada,'O',50);
		prueba.añadirMuelle(añadir);
		GPSCoordinate coordenadaDistancia=null;
		double distancia=4000.0;
		prueba.muellesCerca(coordenadaDistancia, distancia);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testMuellesCercaDistanciaNoValida() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(12,coordenada,'O',50);
		prueba.añadirMuelle(añadir);
		GPSCoordinate coordenadaDistancia=new GPSCoordinate(40.0,40.0);
		double distancia=-4000.0;
		prueba.muellesCerca(coordenadaDistancia, distancia);
	}
	
	
	
}

