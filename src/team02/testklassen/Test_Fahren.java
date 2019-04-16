package team02.testklassen;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import team02.IO;
import team02.Konstanten;
import team02.vorlagen.Fahren;

public class Test_Fahren implements IO, Konstanten
{
	public static long lastTime = Task.time();
	public static int counter=0;
	
	public static double speed = 0;
	public static double radius =0;
	
	public static void test()
	{
		/*
		if (Task.time() > lastTime+5000)
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
			
			lastTime = Task.time();
		}
		*/
		Fahren.update();
		
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

	public static void kurveFahren()
	{
		Fahren.kurveFahren(radius,speed);
	}
	
	public static void stop()
	{
		Fahren.kurveFahren(0.1, 0);
	}
	
	public static void testKurve()
	{
		radius = 0.2;
		int counter =0;
		long start = Task.time();
		long last = start;
		while(start + 15000 > Task.time())
		{
			if(Task.time() > last)
			{
				speed += 0.001;
				kurveFahren();
				last += 100;
			}

			
		}
		stop();
	}

}
