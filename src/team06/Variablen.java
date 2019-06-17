/**
 * @Author Phil
 * @version 2019.03.22
 */
package team06;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;

public class Variablen {

	// Speed für Wurfmotoren
	public static float speedpassgegnerkurz = (float) (75 * Math.PI); // in s^-1 (Radiant)
	public static float speedpassgegnerlang = (float) (105 * Math.PI); // in s^-1 (Radiant)
	public static float speedkorbschuss = (float) (150 * Math.PI); // in s^-1 (Radiant)

	// highTime für den Servomotor
	public static int pwmPeriod = 20000000 / TPU_PWM.tpuTimeBase;
	public static int highTime = 1000000;
	public static int hightTimeoffen = 1800000 / TPU_PWM.tpuTimeBase;
	public static int hightTimehalten = 1100000 / TPU_PWM.tpuTimeBase;

	// Task Zeit
	public static int TASK_PERIOD = 50;
	public static float ts = 0.01f;
	public static float TASK_PERIOD_MOTOR = Variablen.ts * 1000;

	// boolean
	public static boolean hatball = false;
	public static boolean schalterlinks = false; // true = Wand
	public static boolean schalterrechts = false; // true = Wand
	public static boolean gegnerlinks = false;
	public static boolean gegnerrechts = false;
	public static boolean gegnervorne = false;
	public static boolean ir_vl = false; // false = weiss
	public static boolean ir_vr = false; // false = weiss
	public static boolean ir_hl = false; // false = weiss
	public static boolean ir_hr = false; // false = weiss
	

	// Spiel
	public static int gefangen = 0;
	public static int geworfen = 0;

}
