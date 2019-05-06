package team06.phil;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;
import team06.Variablen;

public class PWM_Servo_M3 {

	private TPU_PWM servo;
	private int pwm = Variablen.hightTimehalten;

	public PWM_Servo_M3(boolean useTPUAB, int channel, int pwmPeriod, int higTtime) {

		servo = new TPU_PWM(useTPUAB, channel, pwmPeriod, higTtime);
		

	}

	public void servogeschlossen() {
		pwm = Variablen.hightTimehalten;
	}
	
	public void servooffen() {
		pwm = Variablen.hightTimeoffen;
	}
	
	public void update()
	{
		servo.update(pwm);
	}
	
}
