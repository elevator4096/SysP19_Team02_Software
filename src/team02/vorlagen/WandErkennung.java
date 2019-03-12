package team02.vorlagen;

import team02.IO;

public class WandErkennung {
	
	public WandErkennung() {

	}
	
	public void action() {
		
		IO.OUT_Test_Led.set(IO.IN_Taster1.get() && IO.IN_Taster2.get());
	}

}
