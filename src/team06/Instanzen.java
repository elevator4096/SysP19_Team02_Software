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
	
	public static PWM_Servo servoMotor;

	public static IRSensor iRSensor;

	public static Endschalter endSchalter1;
	public static Endschalter endSchalter2;

	public static WlanSystem wlanSystem;
	public static WurfSystem wurfSystem;
	public static FahrSystem fahrSystem;
	public static OrientierSystem orientierSystem;

	public Instanzen() {

		// Motoren Wurfsysten erzeugen TPUA
		sMSC_WurfMotor1 = new MotorSMSC(Variablen.PERIOD_SMSC, 4, 5, true, 6, true, 64, 12f, 3249f / 196f, 1f, 0.1f); // frequenz
																														// an
																														// task
																														// anpassen
																														// (1.zahl)
//		sMSC_WurfMotor2 = new MotorSMSC(0.001f, 0, 1, true, 2, true, 64, 12f, 3249f / 196f, 1f,
//				0.01f); // frequenz an task anpassen (1.zahl)
//
//		// Motoren Fahrsystem erzeugen
//		sMSC_FahrMotorlinks = new MotorSMSC(0.001f, 0, 1, true, 2, true, 64, 12f, 3249f / 196f,
//				1f, 0.01f); // frequenz an task anpassen (1.zahl)
//		sMSC_FahrMotorrechts = new MotorSMSC(0.001f, 0, 1, true, 2, true, 64, 12f, 3249f / 196f,
//				1f, 0.01f); // frequenz an task anpassen (1.zahl)
		
		// PWM_Servo TPU
		servoMotor = new PWM_Servo(false, 4, Variablen.pwmPeriod, Variablen.hightTimehalten);

		// IR-Sensoren erzeugen MPIOB
		iRSensor = new IRSensor(-1, -1, -1, -1, -1, 59);

		// Enschalter erzeugen MPIOB
		endSchalter1 = new Endschalter(6, false); // false für input
		endSchalter2 = new Endschalter(7, false); // false für input

		// Wurfsystem erzeugen
		wlanSystem = new WlanSystem();
		wurfSystem = new WurfSystem();
		// fahrSystem = new FahrSystem();
		orientierSystem = new OrientierSystem();

	}
}
