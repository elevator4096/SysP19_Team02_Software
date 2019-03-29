/*
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

import static team02.IO.*;

import team02.IO;

public class LinienSensoren {


	/**
	 * Initialize HLC1395Pulsed driver for HLC_Anzahl_Sens sensors and start reading values
	 */
	public LinienSensoren()
	{
		HLC_1395_PULSED.init(HLC_Anzahl_Sens,HLC_Code_Sens,HLC_AN_chan);
		HLC_1395_PULSED.start();
	}
	
	/**
	 * Erkennt ob sich der Sensor auf einer Vertikal stehenden Linie befindet (beide Schwarz)
	 */
	public boolean istLinieVorne(LinienSensor linienSensor)
	{
		return linienSensor.istLinieVorne();
	}
	
	/**
	 * Erkennt ob sich nur Links vom Sensor eine Linie befindet
	 */
	public boolean istLinieLinks(LinienSensor linienSensor)
	{
		return linienSensor.istLinieLinks();
	}
	
	/**
	 * Erkennt ob sich nur Rechts vom Sensor eine Linie befindet
	 */
	public boolean istLinieRechts(LinienSensor linienSensor)
	{
		return linienSensor.istLinieRechts();
	}

	public void update()
	{

	}

}
