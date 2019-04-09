package team06.testprogramme;

import java.io.PrintStream;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import ch.ntb.sysp.lib.SpeedController4DCMotor;

public class SpeedControllerExample extends Task {
	protected static final boolean TPU_A = false;
	protected static final int TPU_PWM_CH0 = 11;
	protected static final int TPU_PWM_CH1 = 12;
	protected static final int TPU_FQD_A = 3;
	/* Controller parameters */
	protected static final float ts = 0.001f;
	protected static final float kp = 1f;
	protected static final float tn = 0.01f;
	/* Ticks per rotation from encoder datasheet */
	protected static final int ticksPerRotation = 64;
	protected static final float gearRatio = 3249f / 196f;
	protected static final float motorVoltage = 12f;
	static SpeedController4DCMotor motor;

	public void action() {
		motor.run();
		System.out.println(motor.getActualSpeed());
	}

	static {
// Create controller
		motor = new SpeedController4DCMotor(ts, TPU_PWM_CH0, TPU_PWM_CH1, TPU_A, TPU_FQD_A, TPU_A, ticksPerRotation,
				motorVoltage, gearRatio, kp, tn);
// Set desired speed
// full turn once per second
		motor.setDesiredSpeed(0);
// Initialize task
		Task t = new SpeedControllerExample();
		t.period = (int) (ts * 1000);
		Task.install(t);
		
		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(9600, SCI.NO_PARITY, (short) 8);

		System.out = new PrintStream(sci1.out);
	}
}