/**
 * @Author Phil
 * @version 2019.03.22
 */
package team06;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;

public interface Variablen
{
	
	
	static float speedpassgegner = 1;   // in s^-1 (Radiant)
	static float speedkorbschuss = 1;	// in s^-1 (Radiant)
	
	
	static int TASK_PERIOD = 200;
	static int TASK_PERIOD_WlAN = 500;
	static int TASK_PERIOD_SMSC = 500;
	

}
