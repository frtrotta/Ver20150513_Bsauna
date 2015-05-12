package gestionesauna;

public class SaunaBassaTemperatura extends Sauna {
	private static double costoOrarioStandard;
	
	public static void setCostoOrarioStandard(double c) {
		if(c > 0)
			costoOrarioStandard = c;
		else
			throw new IllegalArgumentException("c must be positive");
	}
	
	public static double getCostoOrarioStandard() {
		return costoOrarioStandard; 
	}
	public SaunaBassaTemperatura(String numero, int capienza) {
		super(numero, costoOrarioStandard, capienza);
	}

}
