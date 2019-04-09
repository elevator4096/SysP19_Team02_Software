package team06.testprogramme;

import java.io.PrintStream;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;

public class MPIOB extends Task {

	MPIOSM_DIO led;

	public MPIOB() {
		led = new MPIOSM_DIO(9, false); // Pin initialisieren
	}

	public void action() {
		System.out.println("Wert");
		System.out.println(led.get());
		//led.set(!led.get());
		
	}

	static { // Klassenkonstruktor
		Task t = new MPIOB(); // Task erzeugen
		t.period = 400; // Task-Periode festlegen
		Task.install(t); // Task installieren

		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(9600, SCI.NO_PARITY, (short) 8);

		System.out = new PrintStream(sci1.out);
	}
}