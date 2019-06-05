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
import exchange.WlanSystem;
import team06.system.*;

public class Instanzen {

	public static MotorSMSC sMSC_WurfMotor1;
	public static MotorSMSC sMSC_WurfMotor2;

	public static MotorSMSC sMSC_FahrMotorlinks;
	public static MotorSMSC sMSC_FahrMotorrechts;

	public static PWM_Servo servoMotor;

	public static IRSensor iRSensor;

	public static Endschalter endSchalterrechts;
	public static Endschalter endSchalterlinks;

	public static Siebenseg siebensegment;

	public static WurfSystem wurfSystem;
	public static FahrSystem fahrSystem;
	public static OrientierSystem orientierSystem;
	public static WlanSystem wlanSystem;

	public static TaskMotor taskMotor;

	public static VL6180X tofSensor;

	public Instanzen() {

		// ToF-Sensoren erzeugen
		tofSensor = new VL6180X(3, 9);

		// Motoren Wurfsysten erzeugen TPUA
		sMSC_WurfMotor1 = new MotorSMSC(Variablen.ts, 1, 3, true, 12, true, 32, 12f, 1f / 1f, 0.001f, 0.0051f);
		sMSC_WurfMotor2 = new MotorSMSC(Variablen.ts, 0, 2, true, 14, true, 32, 12f, 1f / 1f, 0.001f, 0.0051f);

		// Motoren Fahrsystem erzeugen
		sMSC_FahrMotorlinks = new MotorSMSC(Variablen.ts, 5, 7, true, 8, true, 256, 12f, 91f / 1f, 1f, 0.008f);
		sMSC_FahrMotorrechts = new MotorSMSC(Variablen.ts, 4, 6, true, 10, true, 256, 12f, 91f / 1f, 1f, 0.008f);

		// PWM_Servo TPU
		servoMotor = new PWM_Servo(false, 4, Variablen.pwmPeriod, Variablen.highTime);

		// IR-Sensoren erzeugen
		iRSensor = new IRSensor(-1, 12, 11, 15, 14, 59);

		// Endschalter erzeugen MPIOB
		endSchalterrechts = new Endschalter(6, false); // false für input
		endSchalterlinks = new Endschalter(7, false); // false für input

		// SiebenSegment erzeugen
		siebensegment = new Siebenseg();

		// Systeme erzeugen
		wlanSystem = WlanSystem.getInstance(new MPIOSM_DIO(8, true));
		wurfSystem = new WurfSystem();
		fahrSystem = new FahrSystem();
		orientierSystem = new OrientierSystem();

		// Task für Motor
		taskMotor = new TaskMotor();

	}
}
