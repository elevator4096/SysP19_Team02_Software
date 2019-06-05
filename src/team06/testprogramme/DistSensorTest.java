package team06.testprogramme;

import java.io.PrintStream;
import ch.ntb.inf.deep.runtime.mpc555.driver.HLC1395Pulsed;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;

public class DistSensorTest extends Task {
// I/Os to use:

	private final int ADR3PIN = -1; // not used
	private final int ADR2PIN = 12;//12; // not used 12
	private final int ADR1PIN = 11; // MPIOB7
	private final int ADR0PIN = 15; // MPIOB6
	private final int TRGPIN = 14; // MPIOB5
	private final int ANPIN = 59; // A:AN59
	private HLC1395Pulsed hlc1395;

	public void action() {
		for (int i = 0; i < 7; i++) {
			System.out.print(hlc1395.read(i));
			System.out.print("\t");
		}
		System.out.println();
	}

	public DistSensorTest() {
		hlc1395 = HLC1395Pulsed.getInstance();
// Initialize sensors
		hlc1395.init(ADR3PIN, ADR2PIN, ADR1PIN, ADR0PIN, TRGPIN, ANPIN);
// Start reading
		hlc1395.start();
	}

	static {
// 1) Initialize SCI1 (9600 8N1)
		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(9600, SCI.NO_PARITY, (short) 8);
// 2) Use SCI1 for stdout
		System.out = new PrintStream(sci1.out);
// Initialize task
		Task t = new DistSensorTest();
		t.period = 500;
		Task.install(t);
	}
}