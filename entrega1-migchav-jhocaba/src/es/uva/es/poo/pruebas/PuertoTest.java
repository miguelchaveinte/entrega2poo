package es.uva.es.poo.pruebas;

import es.uva.es.poo.clases.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PuertoTest {

	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorMinuscula() {
		String identidad="ES-mAD";
		Puerto prueba=new Puerto(identidad);
	}

}

