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
	
	public static ToFSensor toFSensor;

	public static WurfSystem wurfSystem;
	public static FahrSystem fahrSystem;
	public static OrientierSystem orientierSystem;

	public static TaskMotor taskMotor;

	public Instanzen() {

		taskMotor = new TaskMotor();

//		// Motoren Wurfsysten erzeugen TPUA
		sMSC_WurfMotor1 = new MotorSMSC(Variablen.ts, 1, 3, true, 12, true, 32, 12f, 1f / 1f, 0.001f, 0.0051f);
		sMSC_WurfMotor2 = new MotorSMSC(Variablen.ts, 0, 2, true, 14, true, 32, 12f, 1f / 1f, 0.001f, 0.0051f);


//		// Motoren Fahrsystem erzeugen
		sMSC_FahrMotorlinks = new MotorSMSC(Variablen.ts, 5, 7, true, 8, true, 256, 12f, 91f/1f, 1f, 0.008f);
		sMSC_FahrMotorrechts = new MotorSMSC(Variablen.ts, 4, 6, true, 10, true, 256, 12f, 91f/1f, 1f, 0.008f);	

//		// PWM_Servo TPU
		servoMotor = new PWM_Servo(false, 4, Variablen.pwmPeriod, Variablen.highTime);

//		// IR-Sensoren erzeugen MPIOB
		iRSensor = new IRSensor(-1, 12, 11, 15, 14, 59);

//		// Endschalter erzeugen MPIOB
		endSchalter1 = new Endschalter(6, false); // false für input
		endSchalter2 = new Endschalter(7, false); // false für input
		
//		// ToF-Sensoren erzeugen
		toFSensor = new ToFSensor();

//		// Systeme erzeugen
		// wurfSystem = new WurfSystem();
		// fahrSystem = new FahrSystem();
		// orientierSystem = new OrientierSystem();

	}
}
