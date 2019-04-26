package team06.phil;

import java.io.PrintStream;
import java.lang.System;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import ch.ntb.sysp.lib.SpeedController4DCMotor;

public class SpeedControllerExample extends Task {
	protected static final boolean TPU_A = false;
	protected static final int TPU_PWM_CH0 = 1;
	protected static final int TPU_PWM_CH1 = 2;
	protected static final int TPU_FQD_A = 3;
	/* Controller parameters */
	protected static final float ts = 0.001f;
	protected static final float kp = 0.0001f;            // Testboar 1f             // Fahrmotor 1f              // Wurfmotor 0.0001f;
	protected static final float tn = 0.0051f;            // Testboard 0.01          // Fahrmotor 0.008f          // Wurfmotor 0.0051f
	/* Ticks per rotation from encoder datasheet */
	protected static final int ticksPerRotation = 32;    // Testboard 64             // Fahrmotor 256             //  Wurfmotor 32
	protected static final float gearRatio = 1f;         //Testboard 3249f / 196f    // Fahrmotor 91f/1f          //   Wurfmotor  1f / 1f
	protected static final float motorVoltage = 12f;
	static SpeedController4DCMotor motor;

	public SpeedControllerExample() {

		System.out.println("Speedcontroller gestartet");

	}

	public void action() {

		motor.run();
		// System.out.println(motor.getActualPosition());
		// System.out.println("static start");
	}

	public int gibweg() {
		return (int) (motor.getActualPosition() / (2 * Math.PI));
	}
	
	public int gibgeschwindigkeit()
	{
		return (int)(motor.getActualSpeed()/(2*Math.PI)*60);
	}
	
	public static void drehzahl()
	{
	motor.setDesiredSpeed((float)(200*Math.PI));   // 150*pi == 5400 //  200*pi max --> 6000
	}
	
	public static void zwanzig()
	{
	motor.setDesiredSpeed((float)(20*Math.PI));  
	}
	
	public static void nul()
	{
	motor.setDesiredSpeed((float)(0*Math.PI));   
	}

	static {
		// Create controller
		motor = new SpeedController4DCMotor(ts, TPU_PWM_CH0, TPU_PWM_CH1, TPU_A, TPU_FQD_A, TPU_A, ticksPerRotation,
				motorVoltage, gearRatio, kp, tn);
		// Set desired speed
		// full turn once per second

		 //motor.setDesiredSpeed((float)(50*Math.PI));
		 motor.setDesiredSpeed((float) (0));

		// Initialize task
		Task t = new SpeedControllerExample();
		t.period = (int) (ts * 1000);
		Task.install(t);

	}
}