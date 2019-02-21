/**
*
*
*@author chris
*@version 19.01.13.0
*/
package team02;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import ch.ntb.sysp.lib.SpeedController4DCMotor;

public class Motor extends Task
{
	static final boolean TPU_A = true;
	static final int TPU_PWM_CH0 = 0;
	static final int TPU_PWM_CH1 = 1;
	static final int TPU_FQD_A = 2;
	
	static final float ts = 0.001f;
	static final float kp = 1f;
	static final float tn = 0.01f;
	
	static final int ticksPerRotation = 64;
	static final float gearRatio = 3249f / 196f;
	static final float motorVoltage = 12f;
	
	static OutputStream stream;
	static SpeedController4DCMotor motor;
	
	static int z=0;
	
	static
	{
		motor = new SpeedController4DCMotor(ts, TPU_PWM_CH0, TPU_PWM_CH1, TPU_A, TPU_FQD_A, TPU_A, ticksPerRotation, motorVoltage, gearRatio, kp, tn);
		motor.setDesiredSpeed((float)(2*Math.PI*0.5));
		
		stream = new OutputStream();
		
		Task t = new Motor();
		t.period = (int) (ts*1000);
		Task.install(t);
	}
	
	public void action()
	{
		motor.run();
	}
	
}
