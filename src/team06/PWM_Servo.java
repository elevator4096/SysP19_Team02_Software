package team06;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;

public class PWM_Servo {

	private TPU_PWM servo;
	private int pwm = Variablen.hightTimehalten;

	/**
	 *Konstruktor, um den Servomotor zu initialisieren
	 */
	public PWM_Servo(boolean useTPUAB, int channel, int pwmPeriod, int higTtime) {
		servo = new TPU_PWM(useTPUAB, channel, pwmPeriod, higTtime);
	}

	/**
	 * Methode, um den Servomotor zu schliessen
	 */
	public void servogeschlossen() {
		pwm = Variablen.hightTimehalten;
	}
	
	/**
	 * Methode, um den Servomotor zu öffnen
	 */
	public void servooffen() {
		pwm = Variablen.hightTimeoffen;
	}
	
	/**
	 * Methode, die zyklisch von der Main aufgerufen wird
	 */
	public void update()
	{
		servo.update(pwm);
	}
	
}
