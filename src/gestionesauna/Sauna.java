package gestionesauna;

import java.util.*;

public abstract class Sauna {
	private String numero;
	private double costoOrario;
	private int capienza;
	private Vector<Prenotazione> prenotazioni;
	
	public Sauna(String numero, double costoOrario, int capienza) {
		if (numero != null)
			this.numero = numero;
		else
			throw new IllegalArgumentException("numero cannot be null");
		
		if(costoOrario > 0)
			this.costoOrario = costoOrario;
		else
			throw new IllegalArgumentException("costoOrario must be positive");
		
		if(capienza > 0)
			this.capienza = capienza;
		else
			throw new IllegalArgumentException("capienza must be positive");
		
		this.prenotazioni = new Vector<Prenotazione>();
	}

	public String getNumero() {
		return numero;
	}

	public double getCostoOrario() {
		return costoOrario;
	}

	public int getCapienza() {
		return capienza;
	}

	public Vector<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}
	
	public void addPrenotazione(Prenotazione p) throws SaunaNotDisponibileException {
		if(isDisponibile(p)) {
			prenotazioni.add(p);
			p.conferma(this);
		}
		else
			throw new SaunaNotDisponibileException("Posti non disponibili");
		return;
	}
	
	public boolean isDisponibile(Prenotazione p) {
		/* La strategia di calcolo implementata in questo metodo non è perfetta,
		 * poichè sottostima la disponibilità. In taluni casi la disponibilità
		 * potrebbe essere garantita, ma il metodo restituisce falso. D'altra parte
		 * non avviene mai che il metodo risponda vero quando non ci sono posti disponibili.
		 * 
		 * La strategia è stata comunque adottata per due ragioni.
		 * 
		 * La prima ragione è che è decisamente più intuitiva rispetto ad un calcolo preciso.
		 * La seconda ragione è che sottostima la disponibilità: i guadagni potrebbero essere
		 * incrementati con un calcolo più preciso, d'altra parte la clientela non sarà mai
		 * insoddisfatta, poichè, se il metodo restituisce vero, la disponibilità è certa.
		 */
		Vector<Prenotazione> prenotazioniSovrapposte = new Vector<Prenotazione>();
		int postiDisponibili = this.capienza;
		
		for(Prenotazione q : prenotazioni) {
			if ( !p.getDal().isAfter(q.getAl()) && !p.getAl().isBefore(q.getDal()))
			{
				prenotazioniSovrapposte.addElement(q);
			}
		}
		
		for(Prenotazione q : prenotazioniSovrapposte) {
			postiDisponibili -= q.getPostiRichiesti();
		}
			
		return (postiDisponibili >= p.getPostiRichiesti());
	}

	@Override
	public String toString() {
		return "Sauna [numero=" + numero + ", costoOrario="
				+ costoOrario + ", capienza=" + capienza
				+ ", numeroPrenotazioni=" + prenotazioni.size() + "]";
	}	
}
