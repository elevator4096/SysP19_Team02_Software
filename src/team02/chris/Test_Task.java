package team02.chris;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import team02.IO;
import team02.Konstanten;
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
    private DebugSystem debugSystem;


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
        debugSystem = DebugSystem.getInstance();

    }

    public void action()
    {
        gegnerSystem.test();
        debugSystem.update();
    }

}
