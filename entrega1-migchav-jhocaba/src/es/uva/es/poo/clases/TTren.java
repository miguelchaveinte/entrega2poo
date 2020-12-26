package es.uva.es.poo.clases;
import com.rits.cloning.Cloner;

import es.uva.inf.poo.maps.GPSCoordinate;

public class TTren extends Simple{
		private int [] tipoPack;
		private int codigoSimple;
		private String inicioFechString;
		private String finFechString;
		public TTren(Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
			super(muelleOrigen, puertoOrigen, fechaIni, muelleDestino,puertoDestino,fechaFin);
			tipoPack= new int []{0,0,0};
			codigoSimple=1;
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
			return cloner.deepClone(tipoPack);
		}
		@Override
		public double costeTrayecto() {
			GPSCoordinate coordenadaOrigen = getMuelleOrigen().getCoordenada();
			GPSCoordinate coordenadaDestino=getMuelleDestino().getCoordenada();
			return 20+12.5*coordenadaOrigen.getDistanceTo(coordenadaDestino);
		}
}
