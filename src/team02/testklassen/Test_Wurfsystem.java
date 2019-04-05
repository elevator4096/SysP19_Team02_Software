package team02.testklassen;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import team02.IO;
import team02.Systeme;

public class Test_Wurfsystem implements IO
{
	public static long lastTime = Integer.MIN_VALUE;
	public static int counter=0;
	public static boolean richtung = true;
	
	public static void test()
	{
		//02.04.19 -> Funktioniert!
		if (Task.time() > lastTime+10000)
		{
			counter++;
			switch(counter)
			{
			case 1:
				Systeme.wurfSystem.zylinderSpannen(95);
				IO.debug.println("Wurfsystem Fall 1");
				break;
			case 2:
				Systeme.wurfSystem.zylinderSpannen(40);
				IO.debug.println("Wurfsystem Fall 2");
				break;
			case 3:
				Systeme.wurfSystem.ballWerfen();
				IO.debug.println("Wurfsystem Fall 3");
				break;
			case 4:
				Systeme.wurfSystem.zylinderSpannen(80);
				IO.debug.println("Wurfsystem Fall 4");
				break;
			}
			
			lastTime = Task.time();
		}
		
		
		
	}	

}
