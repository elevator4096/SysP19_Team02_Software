package team02;

import ch.ntb.inf.deep.runtime.ppc32.Task;



public class WandErkennung extends Task  {


	
	public WandErkennung() {

	}
	
	public void action() {
		
		IO.testLed.set(IO.taster1.get() && IO.taster2.get());
	}
	
	static { // Klassenkonstruktor
		Task t = new WandErkennung(); // Task erzeugen
		t.period = 500; // Task-Periode festlegen
		Task.install(t); // Task installieren
	}

}

