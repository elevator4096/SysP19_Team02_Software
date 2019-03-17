package team02.vorlagen;

import team02.IO;

public class WandErkennung {
	
	public WandErkennung() {

	}

	/**
	 *
	 * @return ist eine Wand vorne
	 */
	public boolean istWandVorne()
	{
		return IO.IN_Taster1.get() && IO.IN_Taster2.get();
	}

	/**
	 *
	 * @return ist eine Wand links
	 */
	public boolean istWandLinks()
	{
		return IO.IN_Taster1.get() && !IO.IN_Taster2.get();
	}

	/**
	 *
	 * @return ist eine Wand rechts
	 */
	public boolean istWandRechts()
	{
		return !IO.IN_Taster1.get() && IO.IN_Taster2.get();
	}

	/**
	 * Alte Testmethode
	 */
	public void action() {
		
		IO.OUT_Test_Led.set(IO.IN_Taster1.get() && IO.IN_Taster2.get());
	}

}
