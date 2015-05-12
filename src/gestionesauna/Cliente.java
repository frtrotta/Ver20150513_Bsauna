package gestionesauna;

import java.util.*;

public class Cliente {
	private String nome;
	private String cognome;
	private String email;
	private String codice;
	private Vector<Prenotazione> prenotazioni;
	
	public Cliente(String nome, String cognome, String email, String codice) {
		if(nome != null)
			this.nome = nome;
		else
			throw new IllegalArgumentException("nome cannot be null");
		
		if(cognome != null)
			this.cognome = cognome;
		else
				throw new IllegalArgumentException("cognome cannot be null");
		
		if(email != null)
			this.email = email;
		else
			throw new IllegalArgumentException("email cannot be null");
		
		if(codice != null)
			this.codice = codice;
		else
			throw new IllegalArgumentException("codice cannot be null");
		
		this.prenotazioni = new Vector<Prenotazione>();
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getEmail() {
		return email;
	}

	public String getCodice() {
		return codice;
	}

	public Vector<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}
	
	public void addPrenotazione(Prenotazione p) {
		prenotazioni.addElement(p);
	}
	
	public int getNumeroTotaleOre() {
		int r=0;
		for (Prenotazione p : prenotazioni) {
			r += p.getNumeroOre();
		}
		return r;
	}
	
	public double getRicavoTotalePrenotazioni() {
		double r=0;
		for (Prenotazione p : prenotazioni) {
			r += p.getRicavoTotale();
		}
		return r;
	}
}
