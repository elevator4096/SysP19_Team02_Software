package team02.loris;

public class LinienSensor {

	/**Erkennt ob beide Sensoren Schwarz sehen */
	public boolean istLinieVorne()
	{
		return true;
	}
	
	/**Erkennt ob ausschliesslich der linke Sensor Schwarz sieht */
	public boolean istLinieLinks()
	{
		return true;
	}
	
	/**Erkennt ob ausschliesslich der rechte Sensor Schwarz sieht */
	public boolean istLinieRechts()
	{
		return true;
	}
	
	/** gibt einen kalibrierten Helligkeitswert zwischen 0 und 100% zurück */
	private int getHelligkeit(int sensorNr)
	{
		return 0;
	}
	
	/** gibt einen unkalibrierten Rohen Sensorwert zurück */
	private int getRawValue()
	{
		return 0;
	}

}
