package es.uva.es.poo.clases;

import com.rits.cloning.Cloner;

public class Estandar extends Contenedor {
	private int espacio;
	private int[] codigoTransporte;
	
	public Estandar(String identificador,double peso ,String unidPeso,double carga,double volumen,String unidVol) {
		super(identificador,peso,unidPeso,carga, volumen,unidVol,true);
		espacio=1;
		codigoTransporte=new int []{1,1,1};

	}
	@Override
	public int getEspacio() {
		return espacio;
	}
	@Override
	public int[] getCodigoTransporte() {
		Cloner cloner=new Cloner();
		return cloner.deepClone(codigoTransporte);
	}
}



