package team06;

import java.io.PrintStream;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import team06.system.WlanSystem;
import team06.system.WurfSystem;

public class Main extends Task implements IO {

	private WurfSystem wurfSystem;
	private WlanSystem wlanSystem;

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

	/**
	 * Konstruktor
	 */
	public Main() {
		wurfSystem = WurfSystem.getInstance();
		wlanSystem = WlanSystem.getInstance();
		System.out.println("Main Konstruktor gestartet");

	}
	
	public void instanz() {
	
	}

	/**
	 * Methode die Zyklisch aufgerufen wird
	 */
	public void action() {

		System.out.println("Main action");
		//wurfSystem = WurfSystem.getInstance();

	}

	/**
	 * Task initialisieren
	 */

}
