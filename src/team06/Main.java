/**
 * @Author Phil
 * @version 2019.03.26
 */
package team06;

import java.io.PrintStream;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import team06.system.WurfSystem;

public class Main extends Task {

	private static Main task;

	public Instanzen instanz;

	/**
	 * Konstruktor Instanz erzugen
	 */
	public Main() {

		instanz = new Instanzen();
		System.out.println("Main Konstruktor gestartet");

	}

	/**
	 * Methode, die zyklisch aufgerufen wird
	 */
	public void action() {
		Instanzen.wurfSystem.gibweg();
	}

	/**
	 * Spielablauf mit Switch/Case
	 */
	public void spielablauf() {

	}

	/**
	 * Task initialisieren/ SCI_OUT
	 */
	static {
		
		task = new Main();
		task.period = Variablen.TASK_PERIOD;
		Task.install(task);

		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(9600, SCI.NO_PARITY, (short) 8);
		// Hook SCI1.out on System.out
		System.out = new PrintStream(sci1.out);

		System.out.println("Main Static gestartet");

	}

}
