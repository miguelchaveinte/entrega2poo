package es.uva.es.poo.pruebas;

import es.uva.es.poo.clases.*;
import es.uva.inf.poo.maps.GPSCoordinate;

import static org.junit.Assert.*;

import java.util.List;

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
	public void testaddMuelle() {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5); 
		Muelle add=new Muelle(12,coordenada,'O',50);
		prueba.addMuelle(add);  
		
		assertEquals(prueba.muellesOperativos().get(0),add);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testaddMuelleNull() {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		Muelle add=null;
		prueba.addMuelle(add);
	}
	@Test
	public void testEliminarMuelle() {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(12,coordenada,'O',50);
		prueba.addMuelle(add);
		prueba.eliminarMuelle(12);	
		assertTrue(prueba.muellesOperativos().isEmpty());
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testEliminarMuelleNoEncontrado(){
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(12,coordenada,'O',50);
		prueba.addMuelle(add);
		prueba.eliminarMuelle(36);	
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testEliminarMuelleIdentidadNoValida()  {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(12,coordenada,'O',50);
		prueba.addMuelle(add);
		prueba.eliminarMuelle(100);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testEliminarMuelleNoVacio()  {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(12,coordenada,'O',50);
		Contenedor contenedorToAdd=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		add.asignarPlaza(contenedorToAdd, 5);
		prueba.addMuelle(add);
		prueba.eliminarMuelle(12);
	}
	@Test
	public void testGetCompleto() {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(12,coordenada,'O',50);
		prueba.addMuelle(add);
		boolean completo=prueba.getCompleto();
		prueba.eliminarMuelle(12);
		boolean completo2=prueba.getCompleto();
		assertFalse(completo);
		assertTrue(completo2);
	}
	
	@Test
	public void testMuellesOperativos()  {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(111,20,coordenada,'F',20);
		prueba.addMuelle(add);
		Contenedor prueba1=new Estandar("CUQU3054387","500-Kg",200.0,"100-m3");
		Contenedor prueba0=new Estandar("CSQU3054383","700-Kg",200.0,"100-m3");
		Contenedor prueba2=new Estandar("CUQU3054345","100-Kg",200.0,"100-m3");
		Contenedor prueba3=new Estandar("CUQU3054330","400-Kg",200.0,"100-m3");
		add.asignarPlaza(prueba0, 2);
		System.out.println(prueba0);
		System.out.println(prueba0.getIdentificador());
		add.asignarPlaza(prueba1, 2);
		add.asignarPlaza(prueba2, 2);
		add.asignarPlaza(prueba3, 2);
		List<Muelle> lista=prueba.getListaMuelles();
		System.out.println(lista.get(0).getListPlazas());
		System.out.println(lista.get(0).getListPlazas().get(2).get(0).getIdentificador());
		//prueba.eliminarMuelle(12);
		//Muelle add2=new Muelle(12,coordenada,'F',50);
		//prueba.addMuelle(add2);
		//assertTrue(prueba.muellesOperativos().isEmpty());
	}
	@Test
	public void testMuellesEspacio()  {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(12,coordenada,'O',50);
		prueba.addMuelle(add);
		assertFalse(prueba.muellesEspacio().isEmpty());
		prueba.eliminarMuelle(12);
		assertTrue(prueba.muellesEspacio().isEmpty());
	}
	
	@Test
	public void testMuellesCerca()  {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(12,coordenada,'O',50);
		prueba.addMuelle(add);
		GPSCoordinate coordenadaDistancia=new GPSCoordinate(40.0,40.0);
		GPSCoordinate coordenadaDistancia2=new GPSCoordinate(40.5,40.5);
		double distancia=4000.0;
		assertFalse(prueba.muellesCerca(coordenadaDistancia, distancia).isEmpty());
		assertFalse(prueba.muellesCerca(coordenadaDistancia2, distancia).isEmpty());
		double distanciaNo=25.0;
		assertTrue(prueba.muellesCerca(coordenadaDistancia, distanciaNo).isEmpty());
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testMuellesCercaCoordenadaNull()  {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(12,coordenada,'O',50);
		prueba.addMuelle(add);
		GPSCoordinate coordenadaDistancia=null;
		double distancia=4000.0;
		prueba.muellesCerca(coordenadaDistancia, distancia);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testMuellesCercaDistanciaNoValida()  {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(12,coordenada,'O',50);
		prueba.addMuelle(add);
		GPSCoordinate coordenadaDistancia=new GPSCoordinate(40.0,40.0);
		double distancia=-4000.0;
		prueba.muellesCerca(coordenadaDistancia, distancia);
	}
	
	
	
}

