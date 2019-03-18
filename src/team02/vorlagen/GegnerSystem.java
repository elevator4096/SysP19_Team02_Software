/**
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

import team02.IO;

public class GegnerSystem {
	
	/**
     * diagrammDummy erzeugt k�nstlich Abhaengigkeiten fuer Klassendiagramm
     * */
	private void diagrammDummy(Sharp sharp)
	{
	}
	
	public GegnerSystem()
	{
		
	}
	
	/**
	 * loescht erkannte Gegner
	 **/
	public void resetGegnerErkennung()
	{
		
	}
	
	/**
	 * Prueft ob sich ein Gegner vorne befand
	 * @return war Gegner vorne
	 */
	public boolean warGegnerVorne()
	{
		return true;
	}
	
	/**
	 *  Prueft ob sich ein Gegner links befand
	 * @return war Gegner Links
	 */
	public boolean warGegnerLinks()
	{
		return true;
	}
	
	/**
	 * Prueft ob sich ein Gegner rechts befand
	 * @return war Gegner Rechts
	 */
	public boolean warGegnerRechts()
	{
		return true;
	}
	
	/**
	 * Prueft ob sich momentan ein Gegner vorne befindet
	 * @return ist ein Gegner vorne
	 */
	public boolean istGegnerVorne()
	{	
		return (IO.SHARP_Vorne.getDistanz() < 6);
	}
	
	/**
	 * Prueft ob sich momentan ein Gegner links befindet
	 * @return ist ein Gegner Links
	 */
	public boolean istGegnerLinks()
	{
		return true;
	}
	
	/**
	 * Prueft ob sich momentan ein Gegner rechts befindet
	 * @return ist ein Gegner Rechts
	 */
	public boolean istGegnerRechts()
	{
		return true;
	}
	
	


}