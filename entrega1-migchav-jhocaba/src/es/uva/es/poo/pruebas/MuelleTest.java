package es.uva.es.poo.pruebas;

import static org.junit.Assert.*;
import es.uva.es.poo.clases.*;

import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;

public class MuelleTest {

	@Test
	public void testMuelleInicializador() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(12,coordenada,'O',50);
		Muelle añadir2=new Muelle(12,coordenada,'F',50);
		assertNotNull(añadir);
		assertNotNull(añadir2);
	}
	@Test
	public void testMuelleInicializadorDos() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(12,coordenada,'F',50);
		assertNotNull(añadir);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testMuelleCoordenadaNull() throws Exception  {
		GPSCoordinate coordenada=null;
		Muelle añadir=new Muelle(12,coordenada,'O',50);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testMuelleIdentificadoNoValido() throws Exception  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(100,coordenada,'O',50);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testMuelleEstadoNoValido() throws Exception  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'o',50);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testMuellePlazasNoValidas() throws Exception  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',0);
	}
	@Test
	public void testGetIdMuelle() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		assertEquals(añadir.getIdMuelle(),20);
	}
	@Test
	public void testGetEstado() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		assertFalse(añadir.getEstado());
	}
	@Test
	public void testGetNumPlazas() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		assertEquals(añadir.getNumPlazas(),5);
	}
	@Test
	public void testGetEstadoPlaza() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		assertEquals(añadir.getListPlazas().get(0).getEstadoPlaza(),"vacia");
	}
	@Test
	public void testGetListPlazas()throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		assertNotNull(añadir.getListPlazas());
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetPlazas() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		añadir.setPlazas(-5);
	}
	
	@Test
	public void testGetCoordenada() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		assertEquals(añadir.getCoordenada(),coordenada);
	}
	@Test
	public void testSetEstPlazaVacia() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		añadir.setEstPlazaVacia();
		assertEquals(añadir.getEstadoPlaza(),"vacia");
	}
	@Test
	public void testSetEstPlazaSemi() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		añadir.setEstPlazaSemi();
		assertEquals(añadir.getEstadoPlaza(),"semillena");
	}
	@Test
	public void testSetEstPlazaLlena() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		añadir.setEstPlazaLlena();
		assertEquals(añadir.getEstadoPlaza(),"llena");
	}
	@Test
	public void testSetNivelUno() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		añadir.setNivelUno(prueba);
		assertNotEquals(prueba,null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetNivelUnoContenedorNull() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=null;
		añadir.setNivelUno(prueba);
	}
	@Test
	public void testSetNivelDos() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		añadir.setNivelDos(prueba);
		assertNotEquals(prueba,null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetNivelDosContenedorNull() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=null;
		añadir.setNivelDos(prueba);
	}
	@Test
	public void testSetNivelTres() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		añadir.setNivelTres(prueba);
		assertNotEquals(prueba,null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetNivelTresContenedorNull() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=null;
		añadir.setNivelTres(prueba);
	}
	@Test
	public void testSetNivelCuatro() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		añadir.setNivelCuatro(prueba);
		assertNotEquals(prueba,null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetNivelCuatroContenedorNull() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=null;
		añadir.setNivelCuatro(prueba);
	}
	@Test
	public void testAsignarPlaza() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		añadir.asignarPlaza(prueba, 2);
		assertEquals(añadir.getListPlazas().get(2).getNivelUno(),prueba);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testAsignarPlazaContenedorNull() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		añadir.asignarPlaza(null, 2);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testAsignarPlazaContenedorIdNulo() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		añadir.asignarPlaza(prueba, 2);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testAsignarPlazaPlazaNegativa() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		añadir.asignarPlaza(prueba, -1);
	}
	@Test
	public void testSacarContenedor() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		añadir.asignarPlaza(prueba, 2);
		Contenedor salida=añadir.sacarContenedor("DUXU1234568");
		assertEquals(salida,prueba);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSacarContenedorIdInvalido() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		añadir.asignarPlaza(prueba, 2);
		Contenedor salida=añadir.sacarContenedor("DUUXU1234568");
	}
	@Test
	public void testEstadoPlaza() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		añadir.asignarPlaza(prueba, 2);
		String resultado=añadir.estadoPlazas();
		assertEquals(resultado,"4/1/0");
	}
	@Test
	public void testGetPlaza() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		añadir.asignarPlaza(prueba, 2);
		int resultado=añadir.getPlaza("DUXU1234568");
		assertEquals(resultado,2);
	}	
	@Test(expected=IllegalArgumentException.class)
	public void testGetPlazaIdInvalido() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		añadir.asignarPlaza(prueba, 2);
		int resultado=añadir.getPlaza("DUXU12345685");
	}
	@Test
	public void testGetNivelPlaza() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		añadir.asignarPlaza(prueba, 2);
		String resultado=añadir.getNivelPlaza("DUXU1234568");
		assertEquals(resultado,"Ese contenedor se encuentra en la plaza 2 y en el nivel 1");
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetNivelPlazaIdInvalido() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		añadir.asignarPlaza(prueba, 2);
		String resultado=añadir.getNivelPlaza("DUXU12345685");
	}
}
