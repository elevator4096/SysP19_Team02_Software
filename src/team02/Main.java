/**
*Das ist die Hauptroutine des Programms
*
*@author chris
*@version 19.01.13.0
*/
package team02;

import ch.ntb.inf.deep.runtime.ppc32.Task;

public class Main extends Task implements Konstanten
{
	//Variablen
	
	public static WirelessConnection wifi;
	public static OutputStream stream;
	public static Output out;
	public static Input in;
	public static PWM pwm;
	public static HLC demo;
	public static Debug debug;
	
	static int x = 0;
	static int y = 1;
	static int z = 0;
	
	
	//Initialisere einen Teil der Variablen
	static
	{
		try
		{
			Task task = new Main();
			task.period = Konstanten.TASK_PERIOD;
			Task.install(task);
			wifi = new WirelessConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		stream = new OutputStream();
		out = new Output();
		in = new Input();
		pwm = new PWM();
		demo = new HLC();
		debug = new Debug();

	}
	
	
	//Konstruktor
	public Main() throws Exception
	{

	}
	
	//Methode, die Zyklisch aufgerufen wird
	public void action()
	{
		if (Konstanten.DEBUG)
		{
			debug.run();
		}
	}
}
