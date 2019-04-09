package team06;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;

public class PWM_Servo {

	private TPU_PWM servo;

	public PWM_Servo(boolean useTPUAB, int channel, int pwmPeriod, int higTtime) {

		servo = new TPU_PWM(useTPUAB, channel, pwmPeriod, higTtime);

	}

	public void servoupdate(int higTtime) {
		servo.update(higTtime);
	}

}
