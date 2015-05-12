package test;


import java.time.LocalDateTime;

import gestionesauna.*;

public class Test {

	public static void main(String[] args) throws SaunaNotDisponibileException {
		System.out.println("Test Gestione Saune\n");
		
		Palestra virgin = new Palestra("Virgin Perugia");
		
		Sauna calda = null;
		
		// -------------------------------------------------------------
		System.out.print("Test01a..");
		try {
			calda = new SaunaAltaTemperatura("127", 10);
		}
		catch (IllegalArgumentException e) {
			System.out.println("ok");
		}
		
		// -------------------------------------------------------------
		System.out.print("Test01b..");
		
		SaunaAltaTemperatura.setCostoOrarioStandard(3);
		calda = new SaunaAltaTemperatura("127", 10);
		
		System.out.println("ok");
		
		virgin.addSauna(calda);
		
		Cliente coccia = new Cliente("Rita", "Coccia", "rita@coccia.com", "1234567");
		
		Prenotazione p =  null;
		
		// -------------------------------------------------------------
		System.out.print("Test02...");
		try {
			p = new Prenotazione(
					coccia,
					LocalDateTime.of(2015, 05, 12, 00, 00),
					LocalDateTime.of(2015, 05, 13, 00, 00),
					2);
			System.out.println("ERROR");
		}
		catch(IllegalArgumentException e) {
			System.out.println("ok");
		}
		
		// -------------------------------------------------------------
		System.out.print("Test03a..");
		p = new Prenotazione(
				coccia,
				LocalDateTime.of(2015, 05, 13, 10, 00),
				LocalDateTime.of(2015, 05, 13, 14, 00),
				7);
		
		calda.addPrenotazione(p);
		
		if(calda.getPrenotazioni().size() == 1)
			System.out.println("ok");
		else
			System.out.println("ERROR");
		
		// -------------------------------------------------------------
		System.out.print("Test03b..");
		p = new Prenotazione(
				coccia,
				LocalDateTime.of(2015, 05, 13, 13, 00),
				LocalDateTime.of(2015, 05, 13, 16, 00),
				2);
		
		calda.addPrenotazione(p);
		
		if(calda.getPrenotazioni().size() == 2)
			System.out.println("ok");
		else
			System.out.println("ERROR");
		
		// -------------------------------------------------------------
		System.out.print("Test03c..");
		p = new Prenotazione(
				coccia,
				LocalDateTime.of(2015, 05, 13, 10, 00),
				LocalDateTime.of(2015, 05, 13, 17, 00),
				2);
		
		try {
			calda.addPrenotazione(p);
			System.out.println("ERROR");
		}
		catch(SaunaNotDisponibileException e) {
			System.out.println("ok");
		}
		
		// -------------------------------------------------------------
		System.out.print("Test04a..");
		try {
			p = new Prenotazione(
				coccia,
				LocalDateTime.of(2015, 05, 12, 00, 00),
				LocalDateTime.of(2015, 05, 11, 00, 00),
				2);
			System.out.println("ERROR");
		}
		catch(IllegalArgumentException e) {
			System.out.println("ok");
		}
		
		// -------------------------------------------------------------
		System.out.print("Test04b..");
		try {
			p = new Prenotazione(
				coccia,
				LocalDateTime.of(2015, 05, 12, 00, 00),
				LocalDateTime.of(2015, 05, 15, 00, 00),
				0);
			System.out.println("ERROR");
		}
		catch(IllegalArgumentException e) {
			System.out.println("ok");
		}
		
		// -------------------------------------------------------------
		System.out.print("Test05...");
		if(coccia.getPrenotazioni().size() == 2)
			System.out.println("ok");
		else
			System.out.println("ERROR");
		
		// -------------------------------------------------------------
		System.out.print("Test06...");
		SaunaBassaTemperatura.setCostoOrarioStandard(3.9);
		SaunaBassaTemperatura fredda = new SaunaBassaTemperatura("250", 5);
		virgin.addSauna(fredda);
		
		/* Una prenotazioni con i medesimi dati è già stata aggiunta con successo
		 * per la sauna calda. Ci attendiamo, quindi, che l'unica sauna disponibile
		 * risulti fredda.
		 */
		p = new Prenotazione(
				coccia,
				LocalDateTime.of(2015, 05, 13, 13, 00),
				LocalDateTime.of(2015, 05, 13, 16, 00),
				2);
		
		if(virgin.getSauneDisponibili(p).size() == 1) {
			if(virgin.getSauneDisponibili(p).get(0) == fredda)
				System.out.println("ok");
			else
				System.out.println("ERROR");
		}
		else {
			System.out.println("ERROR");
		}
	}

}
