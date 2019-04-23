/*
 * @author Loris
 */ 
package team02.testklassen;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import team02.IO;
import team02.vorlagen.Fahren;
import team02.Systeme;

public class Test_BewegungsSystem implements Systeme
{
	public static long lastTime = Task.time();
	public static int counter=0;
	public static boolean richtung = true;
	
	public static void test()
	{
		if (Task.time() > lastTime+5000)
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
			
			lastTime = Task.time();
		}
		
		if(richtung) folgeLinieVorwaerts();
		else folgeLinieRueckwaerts();
		
		Systeme.bewegungsSystem.update();
	}
	
	public static void fahreVorwaerts1m()
	{
		Systeme.bewegungsSystem.fahreFreiBisDistanz(true, 1);
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
