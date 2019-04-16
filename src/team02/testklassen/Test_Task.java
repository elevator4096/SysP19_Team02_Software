/*
 * @author Chris
 * @version 2019.03.27
 */
package team02.testklassen;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import exchange.WlanSystem;
import exchange.ZustandWifi;
import team02.IO;
import team02.Konstanten;
import team02.Systeme;
import team02.vorlagen.DebugSystem;
import team02.vorlagen.Fahren;

/**
 *
 */
public class Test_Task extends Task implements IO, Systeme, Konstanten
{
    static
    {
            Task task = new Test_Task();
            task.period = (int) (Konstanten.TASK_PERIOD*1000);
            Task.install(task);
    }


    public Test_Task()
    {
    	debug.println("Test Task laeuft!");
    }

    public void action()
    {
    	wlanSystem.setOwnState(ZustandWifi.FAHREN);
    	wlanSystem.update();
    	debug.println(wlanSystem.getPartnerState());
    }

}
