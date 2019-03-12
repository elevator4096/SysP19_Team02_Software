package team02;

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
