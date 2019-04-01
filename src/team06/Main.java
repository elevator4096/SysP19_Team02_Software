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

public class Main extends Task{
	
	private Instanzen instanz;

	/**
	 * Konstruktor
	 * Instanz erzugen
	 */
	public Main() {
		
		instanz = new Instanzen();
		
		
	}

	/**
	 * Methode die Zyklisch aufgerufen wird
	 */
	public void action() {

		Instanzen.wurfSystem.motorstarten();
	}

	/**
	 * Task initialisieren/ SCI_OUT
	 */
	static {
		try {
			Task task = new Main();
			task.period = Variablen.TASK_PERIOD;
			Task.install(task);

		} catch (Exception e) {
			e.printStackTrace();
		}

		{
			SCI sci1 = SCI.getInstance(SCI.pSCI1);
			sci1.start(9600, SCI.NO_PARITY, (short) 8);
			// Hook SCI1.out on System.out
			System.out = new PrintStream(sci1.out);
		}

		System.out.println("Main Static gestartet");

	}

}
