package team02.testklassen;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import team02.IO;
import team02.Systeme;

public class Test_Wurfsystem {
	
	public static long lastTime = Task.time();
	public static int counter=0;
	public static boolean richtung = true;
	
	public static void test()
	{
		
		if (Task.time() > lastTime+10000)
		{
			counter++;
			switch(counter)
			{
			case 1:
				Systeme.wurfSystem.zylinderSpannen(90);
				IO.debug.println("Wurfsystem Fall 1");
				break;
			case 2:
				Systeme.wurfSystem.zylinderSpannen(20);
				IO.debug.println("Wurfsystem Fall 2");
				break;
			case 3:
				Systeme.wurfSystem.ballWerfen();
				IO.debug.println("Wurfsystem Fall 3");
				break;
			}
			
			lastTime = Task.time();
		}
		
		//Systeme.wurfSystem.zylinderSpannen(90);
	}	

}
