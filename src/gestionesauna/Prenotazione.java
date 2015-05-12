package gestionesauna;

import java.time.*;

public class Prenotazione {
	private Sauna sauna;
	private Cliente cliente;
	private LocalDateTime dal;
	private LocalDateTime al;
	private int postiRichiesti;
	
	public Prenotazione(Cliente cliente, LocalDateTime dal, LocalDateTime al, int postiRichiesti) {
		if(cliente != null)
			this.cliente = cliente;
		else
			throw new IllegalArgumentException("cliente cannot be null");
		
		if(dal != null)
			this.dal = dal;
		else
				throw new IllegalArgumentException("dal cannot be null");
		
		if(al != null) {
			if(al.isAfter(dal)) {
				if(al.toLocalDate().equals(dal.toLocalDate()))
					this.al = al;
				else
					throw new IllegalArgumentException("al and dal must refer to the same day");
			}
			else
				throw new IllegalArgumentException("al must be after dal");
		}
		else
			throw new IllegalArgumentException("al cannot be null");
		
		if(postiRichiesti > 0)
			this.postiRichiesti = postiRichiesti;
		else
				throw new IllegalArgumentException("posti richiesti must bu positive");
		
	}

	public Sauna getSauna() {
		return sauna;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public LocalDateTime getDal() {
		return dal;
	}

	public LocalDateTime getAl() {
		return al;
	}
	
	public int getPostiRichiesti() {
		return postiRichiesti;
	}
	
	public int getNumeroOre() {
		return (int)Duration.between(dal.toLocalTime(), al.toLocalTime()).toHours();
	}
	
	public double getRicavoTotale() {
		double r = 0;
		if(this.sauna != null) {
			r = getNumeroOre() * this.sauna.getCostoOrario();
		}
		return r;
	}
	
	/***
	 * Conferma la prenotazione per la sauna.
	 * Registra la prenotazione tra quelle del cliente.
	 * @param c
	 */
	public void conferma(Sauna s) {
		if(s != null)
			this.sauna = s;
		else
			throw new IllegalArgumentException("sauna cannot be null");
		cliente.addPrenotazione(this);
	}

	@Override
	public String toString() {
		return "Prenotazione [sauna=" + sauna + ", cliente=" + cliente
				+ ", dal=" + dal + ", al=" + al + "]";
	}
}
