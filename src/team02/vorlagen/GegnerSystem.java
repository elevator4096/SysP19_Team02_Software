/*
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

import ch.ntb.inf.deep.runtime.mpc555.driver.QADC_AIN;
import team02.IO;
import team02.Konstanten;

public class GegnerSystem implements IO
{

	private static GegnerSystem gegnerSystem;
	/**
     * diagrammDummy erzeugt k�nstlich Abhaengigkeiten fuer Klassendiagramm
     * */
	protected void diagrammDummy(Sharp sharp)
	{
	}
	
	private GegnerSystem()
	{
		QADC_AIN.init(false);
	}

	public static GegnerSystem getInstance()
	{
		if(gegnerSystem==null)
		{
			gegnerSystem = new GegnerSystem();
		}
		return gegnerSystem;
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
		return (AN_Sharp3.getDistanz() < 0.06);
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
	

	public void update()
	{

	}

	/**
	 * Testmethode die vom Testtask aufgerufen wird
	 * @author Chris
	 */
	public void test()
	{
		if(Konstanten.TEST)
		{
			update();
			int i = QADC_AIN.read(false,1);
			int x = (int)AN_Sharp1.getDistanz();
			debugSystem.println(i);
			debugSystem.println(x);
		}
		else
		{
			debugSystem.println("Test nicht aktiv");
		}
	}


}
