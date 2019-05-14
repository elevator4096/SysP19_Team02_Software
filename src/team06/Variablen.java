/**
 * @Author Phil
 * @version 2019.03.22
 */
package team06;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;

public class Variablen {

	// Speed für Wurfmoroten
	public static float speedpassgegner = (float) (100 * Math.PI); // in s^-1 (Radiant)
	public static float speedkorbschuss = (float) (150 * Math.PI); // in s^-1 (Radiant)

	// Speed für Fahrmotoren
	public static float speedgeradeaus = (float) (1 * Math.PI); // in s^-1 (Radiant)
	public static float speedrückwärts = (float) (-0.5 * Math.PI); // in s^-1 (Radiant)
	public static float speedmotorausen = (float) (0.5 * Math.PI); // in s^-1 (Radiant)
	public static float speedmotorinnen = (float) (0.5 * Math.PI); // in s^-1 (Radiant)

	// Inkrement der Fahrmotoren
	public static int kurve90grad = 20;

	// highTime für den Servomotor
	public static int pwmPeriod = 20000000 / TPU_PWM.tpuTimeBase;
	public static int highTime = 1000000;
	public static int hightTimeoffen = 2000000 / TPU_PWM.tpuTimeBase;
	public static int hightTimehalten = 1200000 / TPU_PWM.tpuTimeBase;

	// Task Zeit
	public static int TASK_PERIOD = 100;
	public static float ts = 0.01f;
	public static float TASK_PERIOD_MOTOR = Variablen.ts * 1000;

	// BodenSensor Wert
	public static int aufliniemin = 7; // Wert anpassen
	public static int aufliniemax = 12; // Wert anpassen

	// boolean
	public static boolean hatball = false;
	public static boolean anWand = false; // besser jeder einzeln?
	public static boolean schalter1 = false; // false = ??
	public static boolean schalter2 = false; // false = ??

	public Variablen() {
	}
}
