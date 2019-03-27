/**
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

import team02.dominique.WandErkennung;
import team02.loris.LinienSensoren;

public class Orientierung {
	
	/**
     * diagrammDummy erzeugt k�nstlich Abhaengigkeiten fuer Klassendiagramm
     * */
	protected void diagrammDummy()
	{
		WandErkennung wandErkennung;
		LinienSensoren 	linienSensoren 	;
		Motor 			motor;
	}
	
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
	public int[] getXYpos()
	{
		int[] x = {0,0} ;
		return x;
	}
	
	/**
	 * Aus EncoderPositionen und letzter bekannter Ausrichtung berechnete Ausrichtung zum Spielfeld
	 * (im Normalfall 0,90, 180 oder 270 Grad)
	 * @return aktuelle Ausrichtung zum Spielfeld
	 */
	public int getAusrichtungSpielfeld()
	{
		return 0;
	}
	
	/**
	 * Gibt Ausrichtung relativ zur Wand an, berechnet aus Taster und EncoderPositionen 
	 * @return aktuelle Ausrichtung zur Wand
	 */
	public int getAusrichtungWand()
	{
		return 0;
	}

}
