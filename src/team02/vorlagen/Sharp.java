/**
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

import team02.IO;

public class Sharp {
	int ADCKanal = 0;
	
	/**
	 * Konstruktor: ADC Kanal festlegen
	 * @param AdcChannel setzt ADCChannel
	 */
	public Sharp(int AdcChannel)
	{
		this.ADCKanal = AdcChannel;
		//IO.ADC_B.init(false);
	}
	
	/**
	 * Distanz messen
	 * @return aktuelle Distanz
	 */
	public int getDistanz()
	{	
		int distanz = 0;
		//return IO.ADC_B.read(false, 0);
		return distanz;
	}
	
	

}
