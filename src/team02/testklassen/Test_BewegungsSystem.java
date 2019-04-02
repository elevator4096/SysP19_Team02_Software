/*
 * @author Loris
 */ 
package team02.testklassen;

import team02.IO;
import team02.vorlagen.Fahren;
import team02.Systeme;

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
		
		Systeme.bewegungsSystem.update();
	}
	
	public static void folgeLinieVorwaerts()
	{
		Systeme.bewegungsSystem.folgeLinie(true);
	}
	
	public static void folgeLinieRueckwaerts()
	{
		Systeme.bewegungsSystem.folgeLinie(false);
	}
	
	
	
}
