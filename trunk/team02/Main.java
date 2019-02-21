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
	
	public WirelessConnection wifi;
	public OutputStream stream;
	public Output out;
	public Input in;
	public PWM pwm;
	public HLC demo;
	public Debug debug;

	
	static final int ZYL = 13;
	
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
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	
	//Konstruktor
	public Main() throws Exception
	{
		wifi = new WirelessConnection();
		stream = new OutputStream();
		out = new Output();
		in = new Input();
		pwm = new PWM();
		demo = new HLC();
		debug = new Debug();
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
