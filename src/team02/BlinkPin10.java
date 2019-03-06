package team02;

import ch.ntb.inf.deep.runtime.ppc32.Task;

public class BlinkPin10 extends Task  {

	/*
	public final byte ledPin = IO.ledPin; // Pin fuer LED festlegen
	MPIOSM_DIO led;
	*/
	public BlinkPin10() {
		//led = new MPIOSM_DIO(ledPin, true); // Pin initialisieren
		IO.OUT_Test_Led.set(false); // LED ausschalten
	}
	
	public void action() {
		IO.OUT_Test_Led.set(!IO.OUT_Test_Led.get()); // LED �toggeln�
	}
	
	static { // Klassenkonstruktor
		Task t = new BlinkPin10(); // Task erzeugen
		t.period = 500; // Task-Periode festlegen
		Task.install(t); // Task installieren
	}

}

