package es.uva.es.poo.pruebas;

import static org.junit.Assert.*;
import es.uva.es.poo.clases.*;

import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;

public class MuelleTest {

	@Test
	public void testMuelleInicializador() {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(12,coordenada,'O',50);
		assertNotNull(añadir);
	}
	@Test
	public void testMuelleInicializadorDos(){
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(12,coordenada,'F',50);
		assertNotNull(añadir);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testMuelleCoordenadaNull() {
		GPSCoordinate coordenada=null;
		Muelle añadir=new Muelle(12,coordenada,'O',50);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testMuelleIdentificadoNoValido()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(100,coordenada,'O',50);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testMuelleEstadoNoValido() {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'o',50);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testMuellePlazasNoValidas()   {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',0);
	}
	@Test
	public void testGetIdMuelle()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		assertEquals(añadir.getIdMuelle(),20);
	}
	@Test
	public void testGetEstado()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		assertFalse(añadir.getEstado());
	}
	@Test
	public void testGetNumPlazas()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		assertEquals(añadir.getNumPlazas(),5);
	}
	@Test
	public void testGetEstadoPlaza()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		assertEquals(añadir.getListPlazas().get(0).getEstadoPlaza(),"vacia");
	}
	@Test
	public void testGetListPlazas() {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		assertNotNull(añadir.getListPlazas());
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetPlazas()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		añadir.setPlazas(-5);
	}
	
	@Test
	public void testGetCoordenada()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		assertEquals(añadir.getCoordenada(),coordenada);
	}
	@Test
	public void testSetEstPlazaVacia()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		añadir.setEstPlazaVacia();
		assertEquals(añadir.getEstadoPlaza(),"vacia");
	}
	@Test
	public void testSetEstPlazaSemi()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		añadir.setEstPlazaSemi();
		assertEquals(añadir.getEstadoPlaza(),"semillena");
	}
	@Test
	public void testSetEstPlazaLlena()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		añadir.setEstPlazaLlena();
		assertEquals(añadir.getEstadoPlaza(),"llena");
	}
	@Test
	public void testSetNivelUno()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		añadir.setNivelUno(prueba);
		assertNotEquals(prueba,null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetNivelUnoContenedorNull()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=null;
		añadir.setNivelUno(prueba);
	}
	@Test
	public void testSetNivelDos()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		añadir.setNivelDos(prueba);
		assertNotEquals(prueba,null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetNivelDosContenedorNull()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=null;
		añadir.setNivelDos(prueba);
	}
	@Test
	public void testSetNivelTres()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		añadir.setNivelTres(prueba);
		assertNotEquals(prueba,null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetNivelTresContenedorNull()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=null;
		añadir.setNivelTres(prueba);
	}
	@Test
	public void testSetNivelCuatro()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		añadir.setNivelCuatro(prueba);
		assertNotEquals(prueba,null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetNivelCuatroContenedorNull()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=null;
		añadir.setNivelCuatro(prueba);
	}
	@Test
	public void testAsignarPlaza()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		añadir.asignarPlaza(prueba, 2);
		assertEquals(añadir.getListPlazas().get(2).getNivelUno(),prueba);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testAsignarPlazaContenedorNull()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		añadir.asignarPlaza(null, 2);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testAsignarPlazaContenedorIdNulo()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		añadir.asignarPlaza(prueba, 2);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testAsignarPlazaPlazaNegativa()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		añadir.asignarPlaza(prueba, -1);
	}
	@Test
	public void testSacarContenedor()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		añadir.asignarPlaza(prueba, 2);
		Contenedor salida=añadir.sacarContenedor("DUXU1234568");
		assertEquals(salida,prueba);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSacarContenedorIdInvalido()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		añadir.asignarPlaza(prueba, 2);
		Contenedor salida=añadir.sacarContenedor("DUUXU1234568");
	}
	@Test
	public void testEstadoPlaza()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		añadir.asignarPlaza(prueba, 2);
		String resultado=añadir.estadoPlazas();
		assertEquals(resultado,"4/1/0");
	}
	@Test
	public void testGetPlaza()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		añadir.asignarPlaza(prueba, 2);
		int resultado=añadir.getPlaza("DUXU1234568");
		assertEquals(resultado,2);
	}	
	@Test(expected=IllegalArgumentException.class)
	public void testGetPlazaIdInvalido()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		añadir.asignarPlaza(prueba, 2);
		int resultado=añadir.getPlaza("DUXU12345685");
	}
	@Test
	public void testGetNivelPlaza()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		añadir.asignarPlaza(prueba, 2);
		String resultado=añadir.getNivelPlaza("DUXU1234568");
		assertEquals(resultado,"Ese contenedor se encuentra en la plaza 2 y en el nivel 1");
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetNivelPlazaIdInvalido()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle añadir=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("DUXU1234568",500.0,200.0,100.0);
		prueba.setTecho();
		añadir.asignarPlaza(prueba, 2);
		String resultado=añadir.getNivelPlaza("DUXU12345685");
	}
}
