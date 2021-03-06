package team06.testprogramme;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_DIO;
import ch.ntb.inf.deep.runtime.ppc32.Task;

public class TPUDIO extends Task {
	public final byte ledPin = 3;
	// Pin fuer LED festlegen
	TPU_DIO leda, ledb, ledc, ledd, lede, ledf, ledg, leddp;
	int counter = 0;

	public TPUDIO() {

		leda = new TPU_DIO(false, 11, true); // Pin initialisieren
		ledb = new TPU_DIO(false, 9, true);
		ledc = new TPU_DIO(false, 5, true);
		ledd = new TPU_DIO(false, 3, true);
		lede = new TPU_DIO(false, 1, true);
		ledf = new TPU_DIO(false, 13, true);
		ledg = new TPU_DIO(false, 15, true);
		leddp = new TPU_DIO(false, 7, true);

		leda.set(false); // LED ausschalten
		ledb.set(false);
		ledc.set(false);
		ledd.set(false); // LED ausschalten
		lede.set(false);
		ledf.set(false);
		ledg.set(false); // LED ausschalten
		leddp.set(false);

	}

	public void action() {
		leddp.set(!leddp.get()); // LED �toggeln�
		// led.set(!led.get()); // LED �toggeln�

		// kleines N (n)
		if (counter == 0) {
			leda.set(true); // LED ausschalten
			ledb.set(true);
			ledc.set(false);
			ledd.set(true); // LED ausschalten
			lede.set(false);
			ledf.set(true);
			ledg.set(false); // LED ausschalten
			leddp.set(true);
		}
        
		// E
		if (counter == 1) {
			leda.set(false); // LED ausschalten
			ledb.set(true);
			ledc.set(true);
			ledd.set(false); // LED ausschalten
			lede.set(false);
			ledf.set(false);
			ledg.set(false); // LED ausschalten
			leddp.set(true);
		}

		// F
		if (counter == 2) {
			leda.set(false); // LED ausschalten
			ledb.set(true);
			ledc.set(true);
			ledd.set(true); // LED ausschalten
			lede.set(false);
			ledf.set(false);
			ledg.set(false); // LED ausschalten
			leddp.set(true);
		}

//		if (counter > 3) {
//			leddp.set(!leddp.get()); // LED �toggeln�
//
//		}
		counter++;

		if (counter == 5) {
			counter = 0;
		}

	}

	static { // Klassenkonstruktor
		Task t = new TPUDIO(); // Task erzeugen
		t.period = 200; // Task-Periode festlegen
		Task.install(t); // Task installieren
	}
}
