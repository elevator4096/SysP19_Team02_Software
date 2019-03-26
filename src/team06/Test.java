package team06;

import java.io.PrintStream;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;

public class Test extends Task implements IO {
	
	
	public Test() {
		System.out.println("Main Konstruktor gestartet");
		schreiben();
	}
	
	
	public static void schreiben() {
		System.out.println("schreiben");
	}
	
	
	

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
