package team02;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.ppc32.Task;

public class BlinkPin10 extends Task  {

	/*
	public final byte ledPin = IO.ledPin; // Pin fuer LED festlegen
	MPIOSM_DIO led;
	*/
	public BlinkPin10() {
		//led = new MPIOSM_DIO(ledPin, true); // Pin initialisieren
		IO.testLed.set(false); // LED ausschalten
	}
	
	public void action() {
		IO.testLed.set(!IO.testLed.get()); // LED “toggeln”
	}
	
	static { // Klassenkonstruktor
		Task t = new BlinkPin10(); // Task erzeugen
		t.period = 500; // Task-Periode festlegen
		Task.install(t); // Task installieren
	}

}

