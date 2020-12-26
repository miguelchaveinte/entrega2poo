package es.uva.es.poo.clases;

import java.time.temporal.ChronoUnit;

import com.rits.cloning.Cloner;

public class TBarco extends Simple {
	private int codigoSimple;
	private int [] tipoPack;
	private String inicioFechString;
	private String finFechString;
	public TBarco(Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		super(muelleOrigen, puertoOrigen, fechaIni, muelleDestino,puertoDestino,fechaFin);
		codigoSimple=0;
		tipoPack= new int []{0,0,0};
		inicioFechString=fechaIni;
		finFechString=fechaFin;
	}
	@Override
	public String getInicioFech() {
		return inicioFechString;
	}
	@Override
	public String getFinFech() {
		return finFechString;
	}
	@Override
	public int getCodigoSimple() {
		return codigoSimple;
	}
	@Override
	public int[] getTipoPack() {
		Cloner cloner=new Cloner();
		int [] clonadoTipoPack=cloner.deepClone(tipoPack);
		return clonadoTipoPack;
	}
	@Override
	public double costeTrayecto() {
		double coste=(int)ChronoUnit.DAYS.between(getFechaIni(),getFechaFin())*4000;
		return coste;
	}
}
