/**
*@author Phil
*@version 2019.03.22
*
*Wurfmotor mit SM und SC
*
*
*/
package team06;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import ch.ntb.sysp.lib.SpeedController4DCMotor;

public class MotorSMSC implements IO, Variablen									
{
	
	private float ts;
	private float kp;
	private float tn;
	private float i;
	
	private boolean useTPUABPWM;
	private boolean useTPUABenc;	
	
	private int pwmChannel1;
	private int pwmChannel2;	
	private int encChannel;
	private int encChanne2;
	
	private int encTPR;
	
	static SpeedController4DCMotor motor;   //darf nicht static sein
	
	
	/**
	 * Motor Instanz erzeugen
	 */
	public MotorSMSC(float ts, int pwmChannel1, int pwmChannel2, boolean useTPUABPWM, int encChannel, 
				boolean useTPUAEnc, int encTPR, float umax, float i, float kp, float tn) {
		this.ts = ts;
		this.kp = kp;
		this.tn = tn;
		this.i = i;
		this.useTPUABPWM = useTPUABPWM;
		this.useTPUABenc = useTPUABenc;
		this.pwmChannel1 = pwmChannel1;
		this.pwmChannel2 = pwmChannel2;
		this.encChannel = encChannel;
		this.encChanne2 = encChanne2;
		this.encTPR = encTPR;
		
		motor = new SpeedController4DCMotor(ts, pwmChannel1, pwmChannel2, useTPUABPWM, encChannel, useTPUAEnc, encTPR, umax, i, kp, tn);
		motor.setDesiredSpeed(0);
		
		
	}
	
	/**
	 * Wurfgeschwindigkeit einstellen
	 */
	public void wurfgeschwindigkeit(float speed) {
		motor.setDesiredSpeed(speed);
		
	}
	
	/**
	 * Motor mit SpeedController starten
	 */
	public void schiessen() {
		motor.run();
	}
			
}
