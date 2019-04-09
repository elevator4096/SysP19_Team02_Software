package team06.testprogramme;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.ppc32.Task;

public class FlashLight extends Task {
	public final byte ledPin = 9; // Pin fuer LED festlegen
	MPIOSM_DIO led;

	public FlashLight() {

		led = new MPIOSM_DIO(ledPin, true); // Pin initialisieren
		led.set(false); // LED ausschalten
	}

	public void action() {
		led.set(!led.get()); // LED “toggeln”
	}

	static { // Klassenkonstruktor
		Task t = new FlashLight(); // Task erzeugen
		t.period = 500; // Task-Periode festlegen
		Task.install(t); // Task installieren
	}
}
