package team06.testprogramme;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import ch.ntb.sysp.lib.SpeedController4DCMotor;

public class Speedcontroller extends Task {
	protected static final boolean TPU_A = false;
	protected static final int TPU_PWM_CH0 = 2;
	protected static final int TPU_PWM_CH1 = 3;
	protected static final int TPU_FQD_A = 4;
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
	}

	static {
// Create controller
		motor = new SpeedController4DCMotor(ts, TPU_PWM_CH0, TPU_PWM_CH1, TPU_A, TPU_FQD_A, TPU_A, ticksPerRotation,
				motorVoltage, gearRatio, kp, tn);
// Set desired speed
// full turn once per second
		motor.setDesiredSpeed(0);
// Initialize task
		Task t = new Speedcontroller();
		t.period = (int) (ts * 1000);
		Task.install(t);
	}
	
	
}