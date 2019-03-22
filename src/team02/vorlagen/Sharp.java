/**
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

import ch.ntb.inf.deep.runtime.mpc555.driver.QADC_AIN;
import team02.IO;

public class Sharp {
	
	/**
	 * Distanz messen
	 * @return aktuelle Distanz
	 */
	public static int getDistanz(boolean ADCPort,int sensorIndex, double kalibrierWert)
	{	
		int distanz = QADC_AIN.read(ADCPort,sensorIndex);
		return distanz;

	}
	
	

}
