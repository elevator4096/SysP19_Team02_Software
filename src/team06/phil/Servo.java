package team06.phil;

import java.io.PrintStream;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;
import ch.ntb.inf.deep.runtime.ppc32.Task;

public class Servo extends Task {
	private final int testChannel = 4;
	private final boolean useTPUA = false;

// pwmPeriod in TimeBase Unit (50’000 ns)

	private final int pwmPeriod = 20000000 / TPU_PWM.tpuTimeBase;       // alt 20000000
	public int highTime = 1000000;  //alt 1000000
	private TPU_PWM pwm;

	public static boolean direction = true;

	public Servo() {
		pwm = new TPU_PWM(useTPUA, testChannel, pwmPeriod, highTime);
//		period = 2000; // Periodenlaenge des Tasks in ms
//		Task.install(this);
	}

	public void action() {

		if (direction == true) {
			highTime = highTime + 40000;
			pwm.update(highTime / TPU_PWM.tpuTimeBase);
			System.out.println("new Position: ");
			System.out.println(highTime);
		} else if (direction == false) {
			highTime = highTime - 40000;
			pwm.update(highTime / TPU_PWM.tpuTimeBase);
			System.out.println("new Position: ");
			System.out.println(highTime);
		}

		if (highTime >= 2350000) {   //alt 2600000
			direction = false;
		} else if (highTime <= 650000) { // alt 1000000
			direction = true;
		}

//		pwm.update(2200000 / TPU_PWM.tpuTimeBase);
		
	}

	static { // Task Initialisierung
		Task task = new Servo();
		task.period = 100;   // alt 100
		Task.install(task);

		// SCI initialize SCI_1 (9600 8N1)
		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(9600, SCI.NO_PARITY, (short) 8);

		// Standard SCI1 for standard Output
		System.out = new PrintStream(sci1.out);
	}
}