package es.uva.es.poo.pruebas;

import es.uva.es.poo.clases.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PuertoTest {

	@Test
	public void testConstructorBien() {
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
		String identidad="ESMAD";
		Puerto prueba=new Puerto(identidad);
	}
	@Test
	public void getLocalidad() {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		
		assertEquals(prueba.getLocalidad(),"MAD");
	}

}

