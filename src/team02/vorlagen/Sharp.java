package team02.vorlagen;

import team02.IO;

public class Sharp {
	
	public Sharp()
	{
		IO.ADC_B.init(false);
	}
	
	public int readLeft()
	{	
		return IO.ADC_B.read(false, 0);
	}

}
