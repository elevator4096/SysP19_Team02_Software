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
	private boolean gegner_re,gegner_li,gegner_vo;

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
		}
		return gegnerSystem;
	}
	
	/**
	 * loescht erkannte Gegner
	 **/
	public void resetGegnerErkennung()
	{
		gegner_li = false;
		gegner_re = false;
		gegner_vo = false;
	}
	
	/**
	 * Prueft ob sich ein Gegner vorne befand
	 * @return war Gegner vorne
	 */
	public boolean warGegnerVorne()
	{
		return gegner_vo;
	}
	
	/**
	 *  Prueft ob sich ein Gegner links befand
	 * @return war Gegner Links
	 */
	public boolean warGegnerLinks()
	{
		return gegner_li;
	}
	
	/**
	 * Prueft ob sich ein Gegner rechts befand
	 * @return war Gegner Rechts
	 */
	public boolean warGegnerRechts()
	{
		return gegner_re;
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
		return (AN_Sharp2.getDistanz() < 0.06);
	}
	
	/**
	 * Prueft ob sich momentan ein Gegner rechts befindet
	 * @return ist ein Gegner Rechts
	 */
	public boolean istGegnerRechts()
	{
		return (AN_Sharp1.getDistanz() < 0.06);
	}
	

	public void update()
	{
		if(istGegnerLinks())
		{
			gegner_li = true;
		}
		if(istGegnerRechts())
		{
			gegner_re = true;
		}
		if(istGegnerVorne())
		{
			gegner_vo = true;
		}
	}
}
