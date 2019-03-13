package team02.loris;

import team02.IO;

public class Sharp {
	int ADCKanal = 0;
	
	public Sharp(int AdcChannel)
	{
		this.ADCKanal = AdcChannel;
		//IO.ADC_B.init(false);
	}
	
	public int getDistanz()
	{	
		int distanz = 0;
		//return IO.ADC_B.read(false, 0);
		return distanz;
	}
	
	

}
