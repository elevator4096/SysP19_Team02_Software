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

	public static float speedpassgegner = 1; // in s^-1 (Radiant)
	public static float speedkorbschuss = 1; // in s^-1 (Radiant)

	public static int TASK_PERIOD = 100;
	public static int TASK_PERIOD_WlAN = 500;
	public static int PERIOD_SMSC = TASK_PERIOD/1000;

	public Variablen() {

	}
}
