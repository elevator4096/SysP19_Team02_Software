package team06.testprogramme;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_DIO;
import ch.ntb.inf.deep.runtime.ppc32.Task;

public class TPUDIO extends Task {
	public final byte ledPin = 2;
	// Pin fuer LED festlegen
	TPU_DIO led;

	public TPUDIO() {

		led = new TPU_DIO(true, ledPin, true); // Pin initialisieren
		led.set(false); // LED ausschalten
	}

	public void action() {
		led.set(!led.get()); // LED “toggeln”
	}

	static { // Klassenkonstruktor
		Task t = new TPUDIO(); // Task erzeugen
		t.period = 500; // Task-Periode festlegen
		Task.install(t); // Task installieren
	}
}
