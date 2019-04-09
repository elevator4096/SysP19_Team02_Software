/**
 * @Author Phil
 * @version 2019.03.22
 */
package team06;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;

public class Variablen {

	// Kommunikation / Status Wifi
	public static int WURF_BEREIT = 0;
	public static int FANG_BEREIT = 1;
	public static int FAHREN = 2;
	public static int ERROR = 3;

	// Speed für Wurfmoroten
	public static float speedpassgegner = 1; // in s^-1 (Radiant)
	public static float speedkorbschuss = 1; // in s^-1 (Radiant)

	// Speed für Fahrmotoren
	public static float speedgeradeaus = 1;
	public static float speedrückwärts = 1;
	public static float speedmotorausen = 1; // in s^-1 (Radiant)
	public static float speedmotorinnen = 1; // in s^-1 (Radiant)

	// Inkrement der Fahrmotoren
	public static int kurve90grad = 20;
	
	// highTime für den Servomotor
	public static int pwmPeriod = 20000000/TPU_PWM.tpuTimeBase;
	public static int hightTimehalten = 2600000/TPU_PWM.tpuTimeBase;
	public static int hightTimeoffen = 1000000/TPU_PWM.tpuTimeBase;

	// Task Zeit
	public static int TASK_PERIOD = 100;
	public static int TASK_PERIOD_WlAN = 500;
	public static int PERIOD_SMSC = TASK_PERIOD / 1000;

	// BodenSensor Wert
	public static int aufliniemin = 100; // Wert anpassen
	public static int aufliniemax = 150; // Wert anpassen

	public Variablen() {

	}
}
