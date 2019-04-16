/**
 * @Author Phil
 * @version 2019.03.26
 */
package team06;

import java.io.PrintStream;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import team06.system.WlanSystem;
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

		Instanzen.wurfSystem.motorstarten();
		// Instanzen.fahrSystem.motorstarten();
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
		try {
			task = new Main();
			task.period = Variablen.TASK_PERIOD;
			Task.install(task);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Main Static gestartet");

	}

}