package team06;

import ch.ntb.inf.deep.runtime.mpc555.driver.HLC1395Pulsed;

public class IRSensor {
	
	public HLC1395Pulsed hlc1395;
	
	
	
	public IRSensor(int ADR3PIN, int ADR2PIN, int ADR1PIN, int ADR0PIN, int TRGPIN, int ANPIN)
	{
		
		hlc1395 = HLC1395Pulsed.getInstance();
		hlc1395.init(ADR3PIN, ADR2PIN, ADR1PIN, ADR0PIN, TRGPIN, ANPIN);
		hlc1395.start();
	}
	
	
	public int distanzlesen(int pin)
	{
		return hlc1395.read(pin);
	}
	

}