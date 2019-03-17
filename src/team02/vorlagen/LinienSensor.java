/**
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

public class LinienSensor {

	/**
	 * Erkennt ob beide Sensoren Schwarz sehen
	 * @return ist Linie vorne
	 */
	public boolean istLinieVorne()
	{
		return true;
	}
	
	/**
	 * Erkennt ob ausschliesslich der linke Sensor Schwarz sieht
	 * @return ist Linie Links
	 */
	public boolean istLinieLinks()
	{
		return true;
	}
	
	/**
	 * Erkennt ob ausschliesslich der rechte Sensor Schwarz sieht
	 * @return ist Linie Rechts
	 */
	public boolean istLinieRechts()
	{
		return true;
	}
	
	/**
	 * gibt einen kalibrierten Helligkeitswert zwischen 0 und 100% zur�ck
	 * @return gibt Helligkeit zurück
	 */
	private int getHelligkeit(int sensorNr)
	{
		return 0;
	}
	
	/**
	 * gibt einen unkalibrierten Rohen Sensorwert zur�ck
	 * @return gibt Wert zurück
	 */
	private int getRawValue()
	{
		return 0;
	}

}
