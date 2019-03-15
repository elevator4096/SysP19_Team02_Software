package team02.vorlagen;

import team02.IO;

public class Sharp {
	int ADCKanal = 0;
	
	/** Konstruktor: ADC Kanal festlegen */
	public Sharp(int AdcChannel)
	{
		this.ADCKanal = AdcChannel;
		//IO.ADC_B.init(false);
	}
	
	/** Distanz messen */
	public int getDistanz()
	{	
		int distanz = 0;
		//return IO.ADC_B.read(false, 0);
		return distanz;
	}
	
	

}
