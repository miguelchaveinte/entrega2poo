package es.uva.es.poo.clases;

import com.rits.cloning.Cloner;

public class FlatRack extends Contenedor {
	private int espacio;
	public int[] codigoTransporte;
	public FlatRack(String identificador,double peso ,String unidPeso,double carga,double volumen,String unidVol) {
		super(identificador,peso,unidPeso,carga, volumen,unidVol,false);
		espacio=2;
		codigoTransporte=new int [] {1,1,0};
	}

	@Override
	public int getEspacio() {
		return espacio;
	}
	@Override
	public int[] getCodigoTransporte() {
		Cloner cloner=new Cloner();
		int [] clonadoCodigoTransporte=cloner.deepClone(codigoTransporte);
		return clonadoCodigoTransporte;
	}
}
