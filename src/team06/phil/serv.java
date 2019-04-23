package team06.phil;


import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;
import ch.ntb.inf.deep.runtime.ppc32.Task;

public class serv extends Task {
	private final int testChannel = 1;
	private final boolean useTPUA = false;
// pwmPeriod in TimeBase Unit (50’000 ns)
	private final int pwmPeriod = 50000 / TPU_PWM.tpuTimeBase;
	public int highTime = 1500000/ TPU_PWM.tpuTimeBase;
	private TPU_PWM pwm;

	public serv() {
		pwm = new TPU_PWM(useTPUA, testChannel, pwmPeriod, highTime);
		period = 2000; // Periodenlaenge des Tasks in ms
		Task.install(this);
	}

	public void action() {
		//highTime = (highTime + pwmPeriod / 4) % pwmPeriod;
		pwm.update(highTime);
	}

	static { // Task Initialisierung
		new serv();
	}
}