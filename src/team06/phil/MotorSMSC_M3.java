/**
*@author Phil
*@version 2019.03.22
*
*Wurfmotor mit SM und SC
*
*
*/
package team06.phil;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_FQD;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import ch.ntb.sysp.lib.SpeedController4DCMotor;

public class MotorSMSC_M3 {

	public SpeedController4DCMotor motor;

	/**
	 * Motor Instanz erzeugen
	 */
	public MotorSMSC_M3(float ts, int pwmChannel1, int pwmChannel2, boolean useTPUABPWM, int encChannel,
			boolean useTPUAEnc, int encTPR, float umax, float i, float kp, float tn) {

		motor = new SpeedController4DCMotor(ts, pwmChannel1, pwmChannel2, useTPUABPWM, encChannel, useTPUAEnc, encTPR,
				umax, i, kp, tn);

		//motor.setDesiredSpeed((float)(Math.PI));
		motor.setDesiredSpeed((float)(0));
		

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

	/**
	 * Aktuelle Umdrehungen zurückgeben
	 */
	public float gibUmdrehungen() {
		
//		return (int) (motor.getActualPosition() / (2 * Math.PI));
		return  motor.getActualPosition();

	}
	
	/**
	 * Aktuelle Geschwindigkeit in 1/min
	 */
	public int gibGeschwindigkeit()
	{
		return (int)(motor.getActualSpeed()/(2*Math.PI)*60);
	}

}
