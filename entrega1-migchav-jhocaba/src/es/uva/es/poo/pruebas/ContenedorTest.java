package es.uva.es.poo.pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uva.es.poo.clases.*;

public class ContenedorTest {

	@Test
	public void testComprobarIdentificador() {
		Contenedor contenedor=new Contenedor();
		String identificador="CSQU3054383";
		contenedor.comprobarIdentificador(identificador);	
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarIdentificadorIdMasLargo() {
		Contenedor contenedor=new Contenedor();
		String identificador="CSSQU3054383";
		contenedor.comprobarIdentificador(identificador);	
	}

	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarIdentificadorMinusculas() {
		Contenedor contenedor=new Contenedor();
		String identificador="CSsQU3054383";
		contenedor.comprobarIdentificador(identificador);	
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarIdentificadorCodigoInvalido() {
		Contenedor contenedor=new Contenedor();
		String identificador="CSSQZ3054383";
		contenedor.comprobarIdentificador(identificador);	
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testComprobarIdentificadorSerieInvalida() {
		Contenedor contenedor=new Contenedor();
		String identificador="CSSUZ30543583";
		contenedor.comprobarIdentificador(identificador);	
	}
}
