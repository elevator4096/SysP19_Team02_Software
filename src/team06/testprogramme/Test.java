package team06.testprogramme;

import java.io.PrintStream;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import team06.aurelia.Main;
import team06.Variablen;

public class Test extends Task {

	public Test() {
		System.out.println("Main Konstruktor gestartet");
	}

	public static void schreiben() {
		System.out.println("schreiben");
	}

	public void action() {
		System.out.println("test");

	}

	static {

		Task task = new Main();
		task.period = Variablen.TASK_PERIOD;
		Task.install(task);

		{
			SCI sci1 = SCI.getInstance(SCI.pSCI1);
			sci1.start(9600, SCI.NO_PARITY, (short) 8);
			// Hook SCI1.out on System.out
			System.out = new PrintStream(sci1.out);
		}

		System.out.println("test test test test");

	}

}
