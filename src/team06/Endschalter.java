package team06;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;

public class Endschalter {

	public MPIOSM_DIO schalter;


	/**
	 * Konstruktor, um den Endschalter zu initialisieren
	 */
	public Endschalter(int pin, boolean inout) {			
		schalter = new MPIOSM_DIO(pin, inout);
	}

	/**
	 * Methode, um den Schalterzustand zurückzugeben
	 */
	public boolean schalterzustand() {			// true = ??, false = ??
		return schalter.get();
	}

}
