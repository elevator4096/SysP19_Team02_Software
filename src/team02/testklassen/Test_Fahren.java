package team02.testklassen;

import team02.Konstanten;
import team02.vorlagen.Fahren;

public class Test_Fahren {
	public static long lastTime = System.currentTimeMillis();  
	public static int counter=0; 
	
	public static void test()
	{
		if (System.currentTimeMillis() > lastTime+5000)
		{
			counter++;
			switch(counter)
			{
			case 1:
				vorwaerts();
				break;
			case 2:
				rueckwaerts();
				break;
			case 3:
				dreheGUZ();
				break;
			case 4:
				dreheUZ();
				break;
			case 5:
				break;
			}
			
			lastTime = System.currentTimeMillis();
		}
		
	}
	
	public static void vorwaerts()
	{
		Fahren.geradeaus( Konstanten.DRIVING_SPEED);
	}
	
	public static void rueckwaerts()
	{
		Fahren.geradeaus(-Konstanten.DRIVING_SPEED);
	}
	
	public static void dreheGUZ()
	{
		Fahren.drehe(Konstanten.TURNING_SPEED);
	}
	
	public static void dreheUZ()
	{
		Fahren.drehe(-Konstanten.TURNING_SPEED);
	}

}
