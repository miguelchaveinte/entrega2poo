package es.uva.es.poo.pruebas;

import static org.junit.Assert.*;
import es.uva.es.poo.clases.*;

import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;

public class MuelleTest {

	@Test
	public void testMuelleInicializador() {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(12,coordenada,'O',50);
		assertNotNull(add);
	}
	@Test
	public void testMuelleInicializadorDos(){
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(12,coordenada,'F',50);
		assertNotNull(add);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testMuelleCoordenadaNull() {
		GPSCoordinate coordenada=null;
		Muelle add=new Muelle(12,coordenada,'O',50);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testMuelleIdentificadoNoValido()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(100,coordenada,'O',50);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testMuelleEstadoNoValido() {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'o',50);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testMuellePlazasNoValidas()   {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',0);
	}
	@Test
	public void testGetIdMuelle()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		assertEquals(add.getIdMuelle(),20);
	}
	@Test
	public void testGetEstado()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		assertFalse(add.getEstado());
	}
	@Test
	public void testGetNumPlazas()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		assertEquals(add.getNumPlazas(),5);
	}
	@Test
	public void testGetEstadoPlaza()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		assertEquals(add.getListPlazas().get(0).getEstadoPlaza(),"vacia");
	}
	@Test
	public void testGetListPlazas() {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		assertNotNull(add.getListPlazas());
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetPlazas()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		add.setPlazas(-5);
	}
	
	@Test
	public void testGetCoordenada()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		assertEquals(add.getCoordenada(),coordenada);
	}
	@Test
	public void testSetEstPlazaVacia()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		add.setEstPlazaVacia();
		assertEquals(add.getEstadoPlaza(),"vacia");
	}
	@Test
	public void testSetEstPlazaSemi()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		add.setEstPlazaSemi();
		assertEquals(add.getEstadoPlaza(),"semillena");
	}
	@Test
	public void testSetEstPlazaLlena()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		add.setEstPlazaLlena();
		assertEquals(add.getEstadoPlaza(),"llena");
	}
	@Test
	public void testSetNivelUno()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		add.setNivelUno(prueba);
		assertNotEquals(prueba,null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetNivelUnoContenedorNull()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=null;
		add.setNivelUno(prueba);
	}
	@Test
	public void testSetNivelDos()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		add.setNivelDos(prueba);
		assertNotEquals(prueba,null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetNivelDosContenedorNull()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=null;
		add.setNivelDos(prueba);
	}
	@Test
	public void testSetNivelTres()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		add.setNivelTres(prueba);
		assertNotEquals(prueba,null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetNivelTresContenedorNull()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=null;
		add.setNivelTres(prueba);
	}
	@Test
	public void testSetNivelCuatro()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		add.setNivelCuatro(prueba);
		assertNotEquals(prueba,null);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetNivelCuatroContenedorNull()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=null;
		add.setNivelCuatro(prueba);
	}
	@Test
	public void testAsignarPlaza()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		add.asignarPlaza(prueba, 2);
		assertEquals(add.getListPlazas().get(2).getNivelUno(),prueba);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testAsignarPlazaContenedorNull()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		add.asignarPlaza(null, 2);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testAsignarPlazaContenedorIdNulo()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor();
		add.asignarPlaza(prueba, 2);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testAsignarPlazaPlazaNegativa()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		add.asignarPlaza(prueba, -1);
	}
	@Test
	public void testSacarContenedor()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(111,20,coordenada,'F',20);
		Contenedor prueba1=new Estandar("CUQU3054387",500,"Kg",200.0,100,"m3");
		Contenedor prueba=new Estandar("CSQU3054383",500,"Kg",200.0,100,"m3");
		Contenedor prueba2=new Estandar("CUQU3054345",500,"Kg",200.0,100,"m3");
		Contenedor prueba3=new Estandar("CUQU3054330",500,"Kg",200.0,100,"m3");
		//Contenedor prueba4=new FlatRack ("CUQU3054330","400-Kg",200.0,"100-m3");
		add.asignarPlaza(prueba, 2);
		add.asignarPlaza(prueba1, 2);
		add.asignarPlaza(prueba2, 2);
		add.asignarPlaza(prueba3, 2);
		add.asignarPlaza(prueba3, 2);
		add.asignarPlaza(prueba2, 2);
		System.out.println(add.getListPlazas());
		//add.asignarPlaza(prueba4, 3);
		System.out.println(add.getListPlazas());
		for(int i=0;i<4;i++) {
		System.out.println(add.getListPlazas().get(2).get(i).getIdentificador());
		}
		Contenedor salida=add.sacarContenedor("CSQU3054383");
		for(int i=0;i<add.getListPlazas().get(2).size();i++) {
		System.out.println(add.getListPlazas().get(2).get(i).getIdentificador()+"/"+i);
		}
		assertEquals(salida,prueba);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSacarContenedorIdInvalido()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		add.asignarPlaza(prueba, 2);
		Contenedor salida=add.sacarContenedor("CSUQU3054383");
	}
	@Test
	public void testEstadoPlaza()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		add.asignarPlaza(prueba, 2);
		String resultado=add.estadoPlazas();
		assertEquals(resultado,"4/0/1");
	}
	@Test
	public void testGetPlaza()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",true);
		prueba.setTecho();
		add.asignarPlaza(prueba, 2);
		int resultado=add.getPlaza("CSQU3054383");
		assertEquals(resultado,2);
	}	
	@Test(expected=IllegalArgumentException.class)
	public void testGetPlazaIdInvalido()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		Contenedor hola=new Estandar("CSQU3054383","500-Kg",200.0,"100-m3",false);
		System.out.println(hola.getClass().getSimpleName());
		//Contenedor prueba=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",false);
		hola.setTecho();
		add.asignarPlaza(hola, 2);
		int resultado=add.getPlaza("CSQU3054385");
	}
	@Test
	public void testGetNivelPlaza()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",true);
		prueba.setTecho();
		add.asignarPlaza(prueba, 2);
		String resultado=add.getNivelPlaza("CSQU3054383");
		assertEquals(resultado,"Ese contenedor se encuentra en la plaza 2 y en el nivel 1");
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetNivelPlazaIdInvalido()  {
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle add=new Muelle(20,coordenada,'F',5);
		Contenedor prueba=new Contenedor("CSQU3054383","500-Kg",200.0,"100-m3",true);
		prueba.setTecho();
		add.asignarPlaza(prueba, 2);
		String resultado=add.getNivelPlaza("CSQU3054385");
	}
}
