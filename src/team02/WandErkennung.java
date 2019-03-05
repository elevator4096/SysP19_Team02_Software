package team02;

import ch.ntb.inf.deep.runtime.ppc32.Task;



public class WandErkennung extends Task  {


	
	public WandErkennung() {

	}
	
	public void action() {
		
		IO.OUT_Test_Led.set(IO.IN_Taster1.get() && IO.IN_Taster2.get());
	}
	
	static { // Klassenkonstruktor
		Task t = new WandErkennung(); // Task erzeugen
		t.period = 500; // Task-Periode festlegen
		Task.install(t); // Task installieren
	}

}

