package team06.aurelia;

import java.io.PrintStream;
import java.text.DecimalFormat;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_FQD;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import ch.ntb.sysp.lib.SpeedController4DCMotor;

public class SpeedControllerDCMotor extends Task {

	// controller parameters
	static final float ts = 0.1f;
	static final float kp = 1f;
	static final float tn = 0.01f;

	// Ticks per roation from encoder datasheet
	static final int ticksPerRotation = 64;
	static final float gearRation = 3249f / 196f;
	static final float motorVoltage = 12f;

	static SpeedController4DCMotor motor;

	// Drehzahl
	private static TPU_FQD fqd;

	private int umdrehungen = 0;
	private short diff = 0;
	private short oldpos = 0;
	private short pos = 0;
	private long longpos = 0;

	public SpeedControllerDCMotor() {

		System.out.println("SpeedControllerDCMotor Class Start");

		fqd = new TPU_FQD(true, 6);

	}

	public void action() {

		motor.run();

		currentspeed();

	}

	public void currentspeed() {

		System.out.println(fqd.getPosition());
		pos = fqd.getPosition();
		diff = (short) (pos - oldpos);
		longpos = longpos + diff;
		oldpos = pos;

		int umdrehungen = (int) (longpos / 4243.592);

		System.out.print("> ");
		System.out.print(longpos);
//		System.out.print("       Umdrehungen: ");
//		System.out.println(umdrehungen);

	}

	public static void setDisiredSpeed() {

		motor.setDesiredSpeed((float) (30));

	}

	static {

		// SCI initialize SCI_1 (9600 8N1)
		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(9600, SCI.NO_PARITY, (short) 8);

		// Standard SCI1 for standard Output
		System.out = new PrintStream(sci1.out);

		motor = new SpeedController4DCMotor(ts, 4, 5, true, 6, true, ticksPerRotation, motorVoltage, gearRation, kp,
				tn);
		motor.setDesiredSpeed((float) (2 * Math.PI));

		// Task properties
		SpeedControllerDCMotor task = new SpeedControllerDCMotor(); // Generate the task
		task.period = (int) (ts * 1000); // Period length
		Task.install(task); // Installation of the task

	}
}
