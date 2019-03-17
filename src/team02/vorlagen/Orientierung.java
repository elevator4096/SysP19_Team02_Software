/**
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

public class Orientierung {
	
	/**
	 * Konstruktor:
	 */
	public Orientierung()
	{
		
	}
	
	/**
	 * Wandabstand messen
	 * @return aktueller Wandabstand
	 */
	public int getWandAbstand()
	{
		return 0;
	}
	
	/**
	 * anzahl von Links ueberquerte vertikale Linien
	 * @return Anzahl überfahrene vertikale Linien
	 */
	public int getVertikaleLinienNr()
	{
		return 0;
	}
	
	/**
	 * anzahl von Unten ueberquerte horizontale Linien
	 * @return anzahl überfahrene horizontale Linien
	 */
	public int getHorizontaleLinienNr() 
	{
		return 0;
	}
	
	/**
	 * Aus EncoderPositionen und Liniennummern geschaetzte Position (zum debuggen)
	 * @return X und Y Position
	 */
	public int[] getGeschaetzteXYpos()
	{
		int[] x = {0,0} ;
		return x;
	}
	
	/**
	 * Aus EncoderPositionen und letzter bekannter Ausrichtung berechnete Ausrichtung
	 * (im Normalfall 0,90, 180 oder 270 Grad)
	 * @return aktuelle Ausrichtung
	 */
	public int getGeschaetzteAusrichtung()
	{
		return 0;
	}

}
