package gestionesauna;

import java.util.*;

public class Palestra {
	private String nome;
	private Vector<Sauna> saune;
	
	public Palestra(String nome) {
		if(nome != null)
			this.nome = nome;
		else
			throw new IllegalArgumentException("nome cannot be null");
		
		this.saune = new Vector<Sauna>();
	}

	public String getNome() {
		return nome;
	}

	public Vector<Sauna> getSaune() {
		return saune;
	}
	
	public void addSauna(Sauna s) {
		saune.add(s);
	}
	
	public Vector<Sauna> getSauneDisponibili(Prenotazione p) {
		Vector<Sauna> r = new Vector<Sauna>();
		for(Sauna s : saune) {
			if(s.isDisponibile(p))
				r.add(s);
		}
		return r;
	}

	@Override
	public String toString() {
		return "Palestra [nome=" + nome + ", numeroSaune=" + saune.size() + "]";
	}
}
