package team02.loris;

import team02.IO;

public class WandErkennung {
	
	public WandErkennung() {

	}
	
	public boolean istWandVorne()
	{
		return IO.IN_Taster1.get() && IO.IN_Taster2.get();
	}
	
	public boolean istWandLinks()
	{
		return IO.IN_Taster1.get() && !IO.IN_Taster2.get();
	}
	
	public boolean istWandRechts()
	{
		return !IO.IN_Taster1.get() && IO.IN_Taster2.get();
	}
	
	public void action() {
		
		IO.OUT_Test_Led.set(IO.IN_Taster1.get() && IO.IN_Taster2.get());
	}

}
