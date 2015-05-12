package gestionesauna;

@SuppressWarnings("serial")
public class SaunaNotDisponibileException extends Exception {
	SaunaNotDisponibileException() {
		super();
	}
	
	SaunaNotDisponibileException(String message) {
		super(message);
	}
}
