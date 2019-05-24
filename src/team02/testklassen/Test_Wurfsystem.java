package team02.testklassen;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import team02.IO;
import team02.Systeme;
import team02.vorlagen.*;

public class Test_Wurfsystem implements IO, Systeme
{
	public static long lastTime = Integer.MIN_VALUE;
	public static int counter=0;
	public static boolean richtung = true;
	
	public static void test()
	{
		//02.04.19 -> Funktioniert!
		if (Task.time() > lastTime+8000)
		{
			counter++;
			switch(counter)
			{
			case 1:
				Systeme.wurfSystem.zylinderSpannen(90);
				IO.debug.println("Wurfsystem Fall 1");
				break;
			case 2:
				Systeme.wurfSystem.zylinderSpannen(10); //Korbschuss 10
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
		
		Systeme.wurfSystem.update();
		
		
	}	
	
	public static void spannen0()
	{
		Systeme.wurfSystem.zylinderSpannen(0);
	}
	public static void spannen25()
	{
		Systeme.wurfSystem.zylinderSpannen(25);
	}
	
	public static void spannen50()
	{
		Systeme.wurfSystem.zylinderSpannen(50);
	}
	
	public static void spannen90()
	{
		Systeme.wurfSystem.zylinderSpannen(90);
	}
	
	public static void spannen20()
	{
		Systeme.wurfSystem.zylinderSpannen(20);
	}
	
	public static void spannen8() //2 Linien
	{
		Systeme.wurfSystem.zylinderSpannen(8);
	}
	
	public static void spannen40()
	{
		Systeme.wurfSystem.zylinderSpannen(40); //1Linie
	}
	
	public static void loesen()
	{
		Systeme.wurfSystem.magnetEntmagnetisieren();
	}
	
	public static void spannen()
	{
		Systeme.wurfSystem.magnetMagnetisieren();
	}
	

}
