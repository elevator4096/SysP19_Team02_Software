/**
 * @author Phil
 * @version 2019.03.22
*/

package team06;

import ch.ntb.inf.deep.runtime.mpc555.driver.HLC1395Pulsed;
import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.QADC_AIN;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_FQD;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;
import ch.ntb.sysp.lib.SpeedController4DCMotor;
import team06.system.*;

public class Instanzen {
	 
	public static MotorSMSC sMSC_WurfMotor1;
	public static MotorSMSC sMSC_WurfMotor2;
	
	public static MotorSMSC sMSC_FahrMotorlinks;
	public static MotorSMSC sMSC_FahrMotorrechts;
	
	public static WlanSystem wlanSystem;
	public static WurfSystem wurfSystem;
	public static FahrSystem fahrSystem;

	public Instanzen() {

		// Motoren Wurfsysten erzeugen
		sMSC_WurfMotor1 = new MotorSMSC(Variablen.TASK_PERIOD/1000f, 0, 1, true, 2, true, 64, 12f, 3249f / 196f, 1f,
				0.01f); // frequenz an task anpassen (1.zahl)
//		sMSC_WurfMotor2 = new MotorSMSC(0.001f, 0, 1, true, 2, true, 64, 12f, 3249f / 196f, 1f,
//				0.01f); // frequenz an task anpassen (1.zahl)
//
//		// Motoren Fahrsystem erzeugen
//		sMSC_FahrMotorlinks = new MotorSMSC(0.001f, 0, 1, true, 2, true, 64, 12f, 3249f / 196f,
//				1f, 0.01f); // frequenz an task anpassen (1.zahl)
//		sMSC_FahrMotorrechts = new MotorSMSC(0.001f, 0, 1, true, 2, true, 64, 12f, 3249f / 196f,
//				1f, 0.01f); // frequenz an task anpassen (1.zahl)

		// Wurfsystem erzeugen
		wlanSystem = new WlanSystem();
		wurfSystem = new WurfSystem();
		//fahrSystem = new FahrSystem();

	}
}
