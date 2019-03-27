package team02;

import ch.ntb.inf.deep.runtime.mpc555.driver.QADC_AIN;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import team02.chris.WlanSystem;
import team02.vorlagen.BewegungsSystem;
import team02.vorlagen.DebugSystem;
import team02.vorlagen.GegnerSystem;
import team02.vorlagen.WurfSystem;

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
            task.period = Konstanten.TASK_PERIOD;
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
    	debugSystem.println("Test Task läuft!");
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
