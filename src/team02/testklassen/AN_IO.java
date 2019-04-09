/*
 * @author Chris
 * @version 2019.03.27
 */
package team02.testklassen;

import ch.ntb.inf.deep.runtime.mpc555.driver.QADC_AIN;
import team02.IO;

public class AN_IO implements IO
{
	public static void test()
	{
		printAN_IN(false,0);
	}

    /**
     *
     * @param ADCPort true ADC A, false ADC B
     * @param sensorIndex welcher Sensor
     */
    public static void printAN_IN(boolean ADCPort, int sensorIndex)
    {
        QADC_AIN.init(ADCPort);
        debug.print("Port: ");
        if(ADCPort)
        {
        	debug.print("A");
        }
        else
        {
            debug.print("B");
        }
        debug.print("Sensor Index: ");
        debug.print(sensorIndex);
        debug.print("Wert :");
        debug.println(QADC_AIN.read(ADCPort,sensorIndex));
    }

}
