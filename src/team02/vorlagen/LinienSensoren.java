/**
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

import ch.ntb.inf.deep.runtime.mpc555.driver.HLC1395Pulsed;

public class LinienSensoren {
	
	public LinienSensoren()
	{
		/**
		 * Initialize HLC1395Pulsed driver for 4 sensors and start reading values
		 */
		sense = HLC1395Pulsed.getInstance();
		sense.init(4, 0x50076, 59); // initialize 4 sensors (addrPin0 = MPIOB6, addrPin1 = MPIOB7, trgPin = MPIOB5, analogInPin = AN59)
		sense.start();
	}
	
	/**
	 * Erkennt ob sich der Sensor auf einer Vertikal stehenden Linie befindet (beide Schwarz)
	 */
	public boolean istLinieVorne(LinienSensor linienSensor)
	{
		return true;
	}
	
	/**
	 * Erkennt ob sich nur Links vom Sensor eine Linie befindet
	 */
	public boolean istLinieLinks(LinienSensor linienSensor)
	{
		return true;
	}
	
	/**
	 * Erkennt ob sich nur Rechts vom Sensor eine Linie befindet
	 */
	public boolean istLinieRechts(LinienSensor linienSensor)
	{
		return true;
	}
	
	
	

}
