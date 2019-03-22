/**
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

import team02.IO;

public class WandErkennung
{
	
	public WandErkennung()
	{

	}

	/**
	 * Ist eine Wand vorne
	 * @return ist eine Wand vorne
	 */
	public boolean istWandVorne()
	{
		return IO.IN_Taster_1.get() && IO.IN_Taster_2.get();
	}

	/**
	 * Ist eine Wand links
	 * @return ist eine Wand links
	 */
	public boolean istWandLinks()
	{
		return IO.IN_Taster_1.get() && !IO.IN_Taster_2.get();
	}

	/**
	 * Ist eine Wand rechts
	 * @return ist eine Wand rechts
	 */
	public boolean istWandRechts()
	{
		return !IO.IN_Taster_1.get() && IO.IN_Taster_2.get();
	}

	/**
	 * Alte Testmethode
	 */
	public void action()
	{
		//IO.OUT_Test_Led.set(IO.IN_Taster_1.get() && IO.IN_Taster_2.get());
	}

}
