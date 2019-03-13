package team02.loris;

import ch.ntb.inf.deep.runtime.mpc555.driver.HLC1395Pulsed;

public class LinienSensoren {
	static HLC1395Pulsed sense;
	
	public LinienSensoren()
	{
		// Initialize HLC1395Pulsed driver for 4 sensors and start reading values
		sense = HLC1395Pulsed.getInstance();
		sense.init(4, 0x50076, 59); // initialize 4 sensors (addrPin0 = MPIOB6, addrPin1 = MPIOB7, trgPin = MPIOB5, analogInPin = AN59)
		sense.start();
	}
	
	public boolean istLinieVorne(LinienSensor linienSensor)
	{
		return true;
	}
	
	public boolean istLinieLinks(LinienSensor linienSensor)
	{
		return true;
	}
	
	public boolean istLinieRechts(LinienSensor linienSensor)
	{
		return true;
	}
	
	
	

}
