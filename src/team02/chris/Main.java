
package team02.chris;

import ch.ntb.inf.deep.runtime.ppc32.Task;


/**
 *
 */
public class Main extends Task
{

    private Zustand zustand = Zustand.SETUP;


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





}
