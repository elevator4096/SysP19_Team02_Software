package team02.testklassen;

import team02.IO;

public class Test_BewegungsSystem {
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
				folgeLinieVorwaerts();
				break;
			case 2:
				folgeLinieRueckwaerts();
				break;
			case 3:
				break;
			}
			
			lastTime = System.currentTimeMillis();
		}
		
	}
	
	public static void folgeLinieVorwaerts()
	{
		IO.bewegungsSystem.folgeLinie(true);
	}
	
	public static void folgeLinieRueckwaerts()
	{
		IO.bewegungsSystem.folgeLinie(false);
	}
	
	
	
}
