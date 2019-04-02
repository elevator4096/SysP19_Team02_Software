package team06.testprogramme;

// Verknüpft mit MotPos
// Periode von Task muss hoch sein --> sonst Messfehler der Differenz

import java.io.PrintStream;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_FQD;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;
import ch.ntb.inf.deep.runtime.ppc32.Task;

public class FQD2 extends Task {

	// private MotPos sm;

	final byte pin4 = 4;
	private TPU_FQD fqd;
	final boolean TPUA = true;

	private int umdrehungen = 0;
	private short diff = 0;
	private short oldpos = 0;
	private short pos = 0;
	private long longpos = 0;

	// constructor
	public FQD2() {

		// sm = new MotPos();
		fqd = new TPU_FQD(TPUA, pin4);
		System.out.println("System Start");

	}

	// Loop (task function)
	public void action() {

		System.out.println(fqd.getPosition());
		pos = fqd.getPosition();
		diff = (short) (pos - oldpos);
		longpos = longpos + diff;
		oldpos = pos;

		int umdrehungen = (int) (longpos / 4243.592);

		System.out.print("> ");
		System.out.print(longpos);
		System.out.print("       Umdrehungen: ");
		System.out.println(umdrehungen);

	}

	static {

		// SCI initialize SCI_1 (9600 8N1)
		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(9600, SCI.NO_PARITY, (short) 8);

		// Standard SCI1 for standard Output
		System.out = new PrintStream(sci1.out);

//			 Task properties
		FQD2 task = new FQD2(); // Generate the task
		task.period = 100; // Period length
		Task.install(task); // Installation of the task

	}

}
