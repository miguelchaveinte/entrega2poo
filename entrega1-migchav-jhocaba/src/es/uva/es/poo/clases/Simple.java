package es.uva.es.poo.clases;

public abstract class Simple extends Trayecto {
	public Simple(Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		super(muelleOrigen, puertoOrigen, fechaIni, muelleDestino,puertoDestino,fechaFin);
	}
	@Override
	public abstract double costeTrayecto();
	public abstract int [] getTipoPack();
	public abstract int getCodigoSimple();
	public abstract String getInicioFech();
	public abstract String getFinFech();
}
