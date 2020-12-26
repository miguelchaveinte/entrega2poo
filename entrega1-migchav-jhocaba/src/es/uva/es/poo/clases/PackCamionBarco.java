package es.uva.es.poo.clases;
import com.rits.cloning.Cloner;
public class PackCamionBarco extends Compuesto {
	private String inicioFechString;
	private String finFechString;
	private int[] tipoPack;
	private int tipoTrayecto;
	private int codigoSimple;
	public PackCamionBarco(int tipoTrayecto,Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		super(tipoTrayecto,muelleOrigen, puertoOrigen, fechaIni, muelleDestino,puertoDestino,fechaFin);
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
		//TODO:SI ES DIFERENTE DE 0,2 EXCEPCION
		if(tipoTrayecto==2) {
			Trayecto trayectoTren=new TCamion(super.getMuelleOrigen(), super.getPuertoOrigen(), getInicioFech(), super.getMuelleDestino(), super.getPuertoDestino(),getFinFech());
			return trayectoTren.costeTrayecto();
		}
		else {
			Trayecto trayectoCamion=new TBarco(super.getMuelleOrigen(), super.getPuertoOrigen(), getInicioFech(), super.getMuelleDestino(), super.getPuertoDestino(),getFinFech());
			return trayectoCamion.costeTrayecto()*0.85;
		}
			
	}
}
