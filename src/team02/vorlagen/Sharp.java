/*
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

import ch.ntb.inf.deep.runtime.mpc555.driver.QADC_AIN;
import team02.IO;
import team02.Konstanten;

public class Sharp {
	
	/**
	 * Distanz messen
	 * @return aktuelle Distanz
	 */
	
	private boolean ADCPort;
	private int sensorIndex;
	private double kalibrierWert;
	
	public Sharp(boolean ADCPort,int sensorIndex, double kalibrierWert)
	{
		this.ADCPort = ADCPort;
		this.sensorIndex = sensorIndex;
		this.kalibrierWert = kalibrierWert;
	}

	/**
	 * TODO: Hier ausfüllen
	 * @return
	 */
	public double getDistanz()
	{	
		double messwert = QADC_AIN.read(ADCPort,sensorIndex);
		double distanz  = Konstanten.m_SHARP / (messwert + Konstanten.n_SHARP) - Konstanten.k_SHARP;
		return distanz;

	}
	
	

}
