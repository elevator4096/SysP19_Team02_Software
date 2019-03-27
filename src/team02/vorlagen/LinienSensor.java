/**
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

import team02.IO;
import team02.Konstanten;

public class LinienSensor {
	private int sensorLeftIndex, sensorRightIndex;  
	
	/** Konstruktor: */
	public LinienSensor(int sensorLeftIndex,int sensorRightIndex)
	{
		this.sensorLeftIndex 	= sensorLeftIndex;
		this.sensorRightIndex 	= sensorRightIndex;
	}

	/**
	 * Erkennt ob beide Sensoren Schwarz sehen
	 * @return ist Linie vorne
	 */
	public boolean istLinieVorne()
	{
		return IO.HLC_1395_PULSED.read(sensorLeftIndex)<Konstanten.HLC_GRENZWERT_SCHWARZ && IO.HLC_1395_PULSED.read(sensorRightIndex)<Konstanten.HLC_GRENZWERT_SCHWARZ;
	}
	
	/**
	 * Erkennt ob ausschliesslich der linke Sensor Schwarz sieht
	 * @return ist Linie Links
	 */
	public boolean istLinieLinks()
	{
		return IO.HLC_1395_PULSED.read(sensorLeftIndex)<Konstanten.HLC_GRENZWERT_SCHWARZ && IO.HLC_1395_PULSED.read(sensorRightIndex)>Konstanten.HLC_GRENZWERT_WEISS;
	}
	
	/**
	 * Erkennt ob ausschliesslich der rechte Sensor Schwarz sieht
	 * @return ist Linie Rechts
	 */
	public boolean istLinieRechts()
	{
		return IO.HLC_1395_PULSED.read(sensorLeftIndex)>Konstanten.HLC_GRENZWERT_WEISS && IO.HLC_1395_PULSED.read(sensorRightIndex)<Konstanten.HLC_GRENZWERT_SCHWARZ;
	}
	
	public int getHelligkeitRechts()
	{
		int value = IO.HLC_1395_PULSED.read(sensorRightIndex);
		return value; 
	}
	
	public int getHelligkeitLinks()
	{
		int value = IO.HLC_1395_PULSED.read(sensorLeftIndex);
		return value; 
	}

}
