package es.uva.es.poo.clases;

import es.uva.inf.poo.maps.GPSCoordinate;
import com.rits.cloning.Cloner;

public class PackCamionTren extends Compuesto{
	private String inicioFechString;
	private String finFechString;
	private int[] tipoPack ;
	private int codigoSimple;
	public PackCamionTren(int tipoTrayecto,Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		super(tipoTrayecto,muelleOrigen, puertoOrigen, fechaIni, muelleDestino,puertoDestino,fechaFin);
		inicioFechString=fechaIni;
		finFechString=fechaFin;
		//this.tipoTrayecto=tipoTrayecto;
		tipoPack= new int []{0,1,1};
		codigoSimple=tipoTrayecto;
	}

	public String getInicioFech() {
		return inicioFechString;
	}
	public String getFinFech() {
		return finFechString;
	}
	@Override
	public int getCodigoSimple() {
		return codigoSimple;
	}
	@Override
	public int [] getTipoPack() {
		Cloner cloner=new Cloner();
		int [] clonadoTipoPack=cloner.deepClone(tipoPack);
		return clonadoTipoPack;
	}
	@Override
	public double costeTrayecto() {
			GPSCoordinate coordenadaOrigen = getMuelleOrigen().getCoordenada();
			GPSCoordinate coordenadaDestino=getMuelleDestino().getCoordenada();
			double coste=coordenadaOrigen.getDistanceTo(coordenadaDestino)*10;	
			return coste;
			
	}
}
