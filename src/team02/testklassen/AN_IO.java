/**
 * @author Chris
 * @version 2019.03.27
 */
package team02.testklassen;

import ch.ntb.inf.deep.runtime.mpc555.driver.QADC_AIN;
import team02.IO;

public class AN_IO implements IO
{

    /**
     *
     * @param ADCPort true ADC A, false ADC B
     * @param sensorIndex welcher Sensor
     */
    public static void getAN_IN(boolean ADCPort, int sensorIndex)
    {
        QADC_AIN.init(ADCPort);
        debugSystem.print("Port: ");
        if(ADCPort)
        {
            debugSystem.print("A");
        }
        else
        {
            debugSystem.print("B");
        }
        debugSystem.print("Sensor Index: ");
        debugSystem.print(sensorIndex);
        debugSystem.print("Wert :");
        debugSystem.println(QADC_AIN.read(ADCPort,sensorIndex));
    }

}
