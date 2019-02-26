package team02;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.ppc32.Task;



public class WandErkennung extends Task  {

	public final byte ledPin = 10; // Pin fuer LED festlegen
	public final byte taster1Pin = 5;
	public final byte taster2Pin = 6;
	MPIOSM_DIO led, taster1, taster2;
	
	public WandErkennung() {
		led 	= new MPIOSM_DIO(ledPin, true); // Pin initialisieren
		taster1 = new MPIOSM_DIO(taster1Pin, false); // Pin initialisieren
		taster2 = new MPIOSM_DIO(taster2Pin, false); // Pin initialisieren
		
		//led.set(false); // LED ausschalten
	}
	
	public void action() {
		
		led.set(taster1.get() && taster2.get());
	}
	
	static { // Klassenkonstruktor
		Task t = new WandErkennung(); // Task erzeugen
		t.period = 500; // Task-Periode festlegen
		Task.install(t); // Task installieren
	}

}

