package gestionesauna;

public class SaunaAltaTemperatura extends Sauna {
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

	public SaunaAltaTemperatura(String numero, int capienza) {
		super(numero, costoOrarioStandard, capienza);
		// TODO Auto-generated constructor stub
	}

}
