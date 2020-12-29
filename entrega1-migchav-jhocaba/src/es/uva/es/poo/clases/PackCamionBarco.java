package es.uva.es.poo.clases;
import com.rits.cloning.Cloner;
public class PackCamionBarco extends Compuesto {
	static final double DESCUENTO_BARCO=0.85;
	private String inicioFechString;
	private String finFechString;
	private int[] tipoPack;
	private int tipoTrayecto;
	private int codigoSimple;
	
	//TODO:JAVADOC 
	public PackCamionBarco(int tipoTrayecto,Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		super(muelleOrigen, puertoOrigen, fechaIni, muelleDestino,puertoDestino,fechaFin);
		if(tipoTrayecto!=2 && tipoTrayecto!=1 && tipoTrayecto!=0) throw new IllegalArgumentException("El tipo trayecto no es ni 0, ni 1,ni 2, es decir ni barco ni tren ni camion");
		inicioFechString=fechaIni;
		finFechString=fechaFin;
		this.tipoTrayecto=tipoTrayecto;
		tipoPack= new int []{1,0,1};
		codigoSimple=tipoTrayecto;
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
		return cloner.deepClone(tipoPack);
	}
	@Override
	public double costeTrayecto() {
		if(tipoTrayecto==2) {
			Trayecto trayectoCamion=new TCamion(super.getMuelleOrigen(), super.getPuertoOrigen(), getInicioFech(), super.getMuelleDestino(), super.getPuertoDestino(),getFinFech());
			return trayectoCamion.costeTrayecto();
		}
		else if (tipoTrayecto==0){
			Trayecto trayectoBarco=new TBarco(super.getMuelleOrigen(), super.getPuertoOrigen(), getInicioFech(), super.getMuelleDestino(), super.getPuertoDestino(),getFinFech());
			return trayectoBarco.costeTrayecto()*DESCUENTO_BARCO;
		}
		else {
			Trayecto trayectoTren=new TTren(super.getMuelleOrigen(), super.getPuertoOrigen(), getInicioFech(), super.getMuelleDestino(), super.getPuertoDestino(),getFinFech());
			return trayectoTren.costeTrayecto();
		}		
	}
}
