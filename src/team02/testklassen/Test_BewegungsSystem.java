package team02.testklassen;

import team02.IO;
import team02.vorlagen.Fahren;

public class Test_BewegungsSystem {
	public static long lastTime = System.currentTimeMillis();  
	public static int counter=0;
	public static boolean richtung = true;
	
	public static void test()
	{
		if (System.currentTimeMillis() > lastTime+5000)
		{
			counter++;
			switch(counter)
			{
			case 1:
				richtung = true;
				break;
			case 2:
				richtung = false;
				break;
			case 3:
				break;
			}
			
			lastTime = System.currentTimeMillis();
		}
		
		if(richtung) folgeLinieVorwaerts();
		else folgeLinieRueckwaerts();
		
		IO.bewegungsSystem.update();
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
