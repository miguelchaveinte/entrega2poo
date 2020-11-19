package es.uva.es.poo.pruebas;

import static org.junit.Assert.*;
import es.uva.es.poo.clases.*;

import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;

public class MuelleTest {

	@Test
	public void testMuelleInicializador() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(12,coordenada,'O',50);
		Muelle a�adir2=new Muelle(12,coordenada,'F',50);
		assertNotNull(a�adir);
		assertNotNull(a�adir2);
	}
	@Test
	public void testMuelleInicializadorDos() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(12,coordenada,'F',50);
		assertNotNull(a�adir);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testMuelleCoordenadaNull() throws Exception  {
		GPSCoordinate coordenada=null;
		Muelle a�adir=new Muelle(12,coordenada,'O',50);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testMuelleIdentificadoNoValido() throws Exception  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(100,coordenada,'O',50);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testMuelleEstadoNoValido() throws Exception  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'o',50);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testMuellePlazasNoValidas() throws Exception  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',0);
	}
	@Test
	public void testGetIdMuelle() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		assertEquals(a�adir.getIdMuelle(),20);
	}
	@Test
	public void testGetEstado() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		assertFalse(a�adir.getEstado());
	}
	@Test
	public void testGetNumPlazas() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		assertEquals(a�adir.getNumPlazas(),5);
	}
	@Test
	public void testGetEstadoPlaza() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		assertEquals(a�adir.getListPlazas().get(0).getEstadoPlaza(),"vacia");
	}
	@Test
	public void testGetListPlazas()throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		assertNotNull(a�adir.getListPlazas());
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetPlazas() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		a�adir.setPlazas(-5);
	}
	
	@Test
	public void testGetCoordenada() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		assertEquals(a�adir.getCoordenada(),coordenada);
	}
	@Test
	public void testSetEstPlazaVacia() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		a�adir.setEstPlazaVacia();
		assertEquals(a�adir.getEstadoPlaza(),"vacia");
	}
	@Test
	public void testSetEstPlazaSemi() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		a�adir.setEstPlazaSemi();
		assertEquals(a�adir.getEstadoPlaza(),"semillena");
	}
	@Test
	public void testSetEstPlazaLlena() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		a�adir.setEstPlazaLlena();
		assertEquals(a�adir.getEstadoPlaza(),"llena");
	}
	@Test
	public void testSetNivelUno() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		a�adir.setNivelUno(prueba);
		assertNotEquals(prueba,null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetNivelUnoContenedorNull() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=null;
		a�adir.setNivelUno(prueba);
	}
	@Test
	public void testSetNivelDos() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		a�adir.setNivelDos(prueba);
		assertNotEquals(prueba,null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetNivelDosContenedorNull() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=null;
		a�adir.setNivelDos(prueba);
	}
	@Test
	public void testSetNivelTres() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		a�adir.setNivelTres(prueba);
		assertNotEquals(prueba,null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetNivelTresContenedorNull() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=null;
		a�adir.setNivelTres(prueba);
	}
	@Test
	public void testSetNivelCuatro() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		a�adir.setNivelCuatro(prueba);
		assertNotEquals(prueba,null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetNivelCuatroContenedorNull() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=null;
		a�adir.setNivelCuatro(prueba);
	}
	@Test
	public void testAsignarPlaza() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		a�adir.asignarPlaza(prueba, 2);
		assertEquals(a�adir.getListPlazas().get(2).getNivelUno(),prueba);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testAsignarPlazaContenedorNull() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		a�adir.asignarPlaza(null, 2);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testAsignarPlazaContenedorIdNulo() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		a�adir.asignarPlaza(prueba, 2);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testAsignarPlazaPlazaNegativa() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		a�adir.asignarPlaza(prueba, -1);
	}
	@Test
	public void testSacarContenedor() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		a�adir.asignarPlaza(prueba, 2);
		Contenedor salida=a�adir.sacarContenedor("DUXU1234568");
		assertEquals(salida,prueba);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSacarContenedorIdInvalido() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		a�adir.asignarPlaza(prueba, 2);
		Contenedor salida=a�adir.sacarContenedor("DUUXU1234568");
	}
	@Test
	public void testEstadoPlaza() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		a�adir.asignarPlaza(prueba, 2);
		String resultado=a�adir.estadoPlazas();
		assertEquals(resultado,"4/1/0");
	}
	@Test
	public void testGetPlaza() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		a�adir.asignarPlaza(prueba, 2);
		int resultado=a�adir.getPlaza("DUXU1234568");
		assertEquals(resultado,2);
	}	
	@Test(expected=IllegalArgumentException.class)
	public void testGetPlazaIdInvalido() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		a�adir.asignarPlaza(prueba, 2);
		int resultado=a�adir.getPlaza("DUXU12345685");
	}
	@Test
	public void testGetNivelPlaza() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		a�adir.asignarPlaza(prueba, 2);
		String resultado=a�adir.getNivelPlaza("DUXU1234568");
		assertEquals(resultado,"Ese contenedor se encuentra en la plaza 2 y en el nivel 1");
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetNivelPlazaIdInvalido() throws Exception {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		a�adir.asignarPlaza(prueba, 2);
		String resultado=a�adir.getNivelPlaza("DUXU12345685");
	}
}
