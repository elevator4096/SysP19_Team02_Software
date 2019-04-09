/*
 * @author Chris
 * @version 2019.03.27
 */
package team02.testklassen;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import team02.IO;
import team02.Konstanten;
import team02.Systeme;

/**
 *
 */
public class Test_Task extends Task implements IO, Systeme
{

    static
    {
        try
        {
            Task task = new Test_Task();
            task.period = (int) Konstanten.TASK_PERIOD*1000;
            Task.install(task);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public Test_Task()
    {
    	debug.println("Test Task laeuft!");
    }

    public void action()
    {
    	/*
    	OUT_LED1.set(!OUT_LED1.get());
    	debugSystem.println("------");
        gegnerSystem.test();
        debugSystem.update();
        */
    	
    	//DIG_IO.test();
    	
    	
    	//Test_LinienSensoren.test();
    	
    	//Systeme.wurfSystem.update();
    	//Test_Wurfsystem.test();
    	AN_IO.test();
    	
    	
    	
    }

}
