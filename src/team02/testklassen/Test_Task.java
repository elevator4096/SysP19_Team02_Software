/*
 * @author Chris
 * @version 2019.03.27
 */
package team02.testklassen;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import team02.IO;
import team02.Konstanten;
import team02.chris.WlanSystem;
import team02.vorlagen.BewegungsSystem;
import team02.vorlagen.GegnerSystem;
import team02.vorlagen.WurfSystem;

/**
 *
 */
public class Test_Task extends Task implements IO
{
    private WurfSystem wurfSystem;
    private BewegungsSystem bewegungsSystem;
    private GegnerSystem gegnerSystem;
    private WlanSystem wlanSystem;

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
        //wurfSystem = WurfSystem.getInstance();
        //bewegungsSystem = BewegungsSystem.getInstance();
        gegnerSystem = GegnerSystem.getInstance();
        //wlanSystem = WlanSystem.getInstance();
    	debugSystem.println("Test Task l�uft!");
    }

    public void action()
    {
    	/*
    	OUT_LED1.set(!OUT_LED1.get());
    	debugSystem.println("------");
        gegnerSystem.test();
        debugSystem.update();
        */
        
        LINES_Sensoren.test();
    }

}
