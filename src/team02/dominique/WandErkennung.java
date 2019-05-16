/**
 * @author dominique
 * @version 2019.03.17
 */
package team02.dominique;

import team02.IO;

public class WandErkennung implements IO
{
	
	public WandErkennung()
	{

	}

	/**
	 * Ist eine Wand vorne
	 * @return ist eine Wand vorne
	 */
	public static boolean istWandVorne()
	{
		return !IO.IN_Taster_1.get() && !IO.IN_Taster_2.get();
	}

	/**
	 * Ist eine Wand links
	 * @return ist eine Wand links
	 */
	public static boolean istWandLinks()
	{
		return !IO.IN_Taster_1.get();
	}

	/**
	 * Ist eine Wand rechts
	 * @return ist eine Wand rechts
	 */
	public static boolean istWandRechts()
	{
		return !IO.IN_Taster_2.get();
	}
	
	/**
	 * Ist eine Wand links oder rechts
	 * @return ist eine Wand irgendwo
	 */
	public static boolean istWandIrgendwo()
	{
		return !IO.IN_Taster_1.get() || !IO.IN_Taster_2.get();
	}

}
