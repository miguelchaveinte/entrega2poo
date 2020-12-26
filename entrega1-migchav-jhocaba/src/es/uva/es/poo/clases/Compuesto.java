package es.uva.es.poo.clases;

public abstract class Compuesto extends Trayecto {
	private int codigoSimple;
	public Compuesto(int tipoTrayecto,Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		super(muelleOrigen, puertoOrigen, fechaIni, muelleDestino,puertoDestino,fechaFin);
		codigoSimple=tipoTrayecto;
	}
	@Override
	public abstract double costeTrayecto();
	public abstract int [] getTipoPack();
	public abstract String getInicioFech();
	public abstract String getFinFech();
	@Override
	public int getCodigoSimple() {
		return codigoSimple;
	}
}
