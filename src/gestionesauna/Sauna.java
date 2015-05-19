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
		/* La strategia di calcolo implementata in questo metodo non � perfetta,
		 * poich� sottostima la disponibilit�. In taluni casi la disponibilit�
		 * potrebbe essere garantita, ma il metodo restituisce falso. D'altra parte
		 * non avviene mai che il metodo risponda vero quando non ci sono posti disponibili.
		 * 
		 * La strategia � stata comunque adottata per due ragioni.
		 * 
		 * La prima ragione � che � decisamente pi� intuitiva rispetto ad un calcolo preciso.
		 * La seconda ragione � che sottostima la disponibilit�: i guadagni potrebbero essere
		 * incrementati con un calcolo pi� preciso, d'altra parte la clientela non sar� mai
		 * insoddisfatta, poich�, se il metodo restituisce vero, la disponibilit� � certa.
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
