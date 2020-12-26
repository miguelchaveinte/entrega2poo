package es.uva.es.poo.clases;

public abstract class Compuesto extends Trayecto {
	public Compuesto(Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		super(muelleOrigen, puertoOrigen, fechaIni, muelleDestino,puertoDestino,fechaFin);
	}
	@Override
	public abstract double costeTrayecto();
	public abstract int [] getTipoPack();
	public abstract String getInicioFech();
	public abstract String getFinFech();
	public abstract int getCodigoSimple(); 
}
