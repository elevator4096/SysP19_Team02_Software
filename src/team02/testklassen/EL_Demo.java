package team02.testklassen;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import team02.IO;

public class EL_Demo extends Task implements IO
{
	
	private boolean b1;
	
	static
    {
            Task task = new EL_Demo();
            task.period = (int) (200);
            Task.install(task);
            
    }
	
	public void action()
	{
		if(!IN_Taster_1.get())
		{
			OUT_LED1.set(true);
			OUT_LED2.set(false);
		}
		if(!IN_Taster_2.get())
		{
			OUT_LED1.set(false);
			OUT_LED2.set(true);
		}
	}
	
	private EL_Demo()
	{
		b1 = false;
	}
}
