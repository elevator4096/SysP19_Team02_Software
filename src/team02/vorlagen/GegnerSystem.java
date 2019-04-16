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
	private boolean warGegnerL, warGegnerR, warGegnerH;

	private static GegnerSystem gegnerSystem;
	
	private GegnerSystem()
	{
		QADC_AIN.init(false);
		resetGegnerErkennung();
	}

	public static GegnerSystem getInstance()
	{
		if(gegnerSystem==null)
		{
			gegnerSystem = new GegnerSystem();
			gegnerSystem.resetGegnerErkennung();
		}
		return gegnerSystem;
	}
	
	/**
	 * loescht erkannte Gegner
	 **/
	public void resetGegnerErkennung()
	{
		warGegnerL = false;
		warGegnerR = false;
		warGegnerH = false;
	}
	
	/**
	 * Prueft ob sich ein Gegner hinten befand
	 * @return war Gegner vorne
	 */
	public boolean warGegnerHinten()
	{
		return warGegnerH;
	}
	
	/**
	 *  Prueft ob sich ein Gegner links befand
	 * @return war Gegner Links
	 */
	public boolean warGegnerLinks()
	{
		return warGegnerL;
	}
	
	/**
	 * Prueft ob sich ein Gegner rechts befand
	 * @return war Gegner Rechts
	 */
	public boolean warGegnerRechts()
	{
		return warGegnerH;
	}

	/**
	 * Prueft ob sich momentan ein Gegner links befindet
	 * @return ist ein Gegner Links
	 */
	public boolean istGegnerLinks()
	{
		return (AN_Sharp1.getDistanz() < Konstanten.SHARP_GRENZWERT);
	}
	
	/**
	 * Prueft ob sich momentan ein Gegner hinten befindet
	 * @return ist ein Gegner vorne
	 */
	public boolean istGegnerHinten()
	{	
		return (AN_Sharp2.getDistanz() < Konstanten.SHARP_GRENZWERT);
	}

	
	/**
	 * Prueft ob sich momentan ein Gegner rechts befindet
	 * @return ist ein Gegner Rechts
	 */
	public boolean istGegnerRechts()
	{
		return (AN_Sharp3.getDistanz() < Konstanten.SHARP_GRENZWERT);
	}
	

	public void update()
	{
		if(istGegnerLinks())
		{
			warGegnerL = true;
		}
		if(istGegnerRechts())
		{
			warGegnerR = true;
		}
		if(istGegnerHinten())
		{
			warGegnerH = true;
		}
	}
}
