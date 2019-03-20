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
		
		return IO.sense.read(sensorLeftIndex)<Konstanten.HLC_GRENZWERT_SCHWARZ && IO.sense.read(sensorRightIndex)<Konstanten.HLC_GRENZWERT_SCHWARZ;
	}
	
	/**
	 * Erkennt ob ausschliesslich der linke Sensor Schwarz sieht
	 * @return ist Linie Links
	 */
	public boolean istLinieLinks()
	{
		return IO.sense.read(sensorLeftIndex)<Konstanten.HLC_GRENZWERT_SCHWARZ && IO.sense.read(sensorRightIndex)<Konstanten.HLC_GRENZWERT_WEISS;
	}
	
	/**
	 * Erkennt ob ausschliesslich der rechte Sensor Schwarz sieht
	 * @return ist Linie Rechts
	 */
	public boolean istLinieRechts()
	{
		return IO.sense.read(sensorRightIndex)<Konstanten.HLC_GRENZWERT_SCHWARZ && IO.sense.read(sensorLeftIndex)<Konstanten.HLC_GRENZWERT_WEISS;
	}

}
