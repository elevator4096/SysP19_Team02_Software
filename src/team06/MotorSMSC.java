/**
*@author Phil
*@version 2019.03.22
*
*Wurfmotor mit SM und SC
*
*
*/
package team06;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_FQD;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import ch.ntb.sysp.lib.SpeedController4DCMotor;

public class MotorSMSC {

	public SpeedController4DCMotor motor;

	/**
	 * Motor Instanz erzeugen
	 */
	public MotorSMSC(float ts, int pwmChannel1, int pwmChannel2, boolean useTPUABPWM, int encChannel,
			boolean useTPUAEnc, int encTPR, float umax, float i, float kp, float tn) {

		motor = new SpeedController4DCMotor(ts, pwmChannel1, pwmChannel2, useTPUABPWM, encChannel, useTPUAEnc, encTPR,
				umax, i, kp, tn);

		motor.setDesiredSpeed(2);

		System.out.println("Motor erzeugt");

	}

	/**
	 * Drehzahl einstellen
	 */
	public void setdrehzahl(float speed) {
		motor.setDesiredSpeed(speed);

	}

	/**
	 * Motor mit SpeedController starten
	 */
	public void motorstarten() {
		motor.run();
	}

	// Methode muss noch angepasst werden (Rechnung)
	/**
	 * Aktuelle Distanz zurückgeben
	 */
	public void gibInkrement() {

		motor.getActualPosition();

	}

}
