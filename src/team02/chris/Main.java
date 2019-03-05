/**
 * @Author Chris
 * @version 2019.03.05
 */
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


    public Main()
    {

    }

    public void action()
    {
        if (Konstanten.DEBUG)
        {

        }

        switch (zustand)
        {
            case SETUP:
            {

                break;
            }

            case SPIEL_BEGINNT:
            {

                break;
            }
            case ROB_HAT_KEIN_BALL:
            {

                break;
            }
            case ROB_FAEHRT:
            {

                break;
            }
            case ROB_HAT_BALL:
            {

                break;
            }
            case ROB_POSITION_ERREICHT:
            {

                break;
            }
            case KORB_WURF:
            {

                break;
            }
            case KURZER_WURF:
            {

                break;
            }
            case LANGER_WURF:
            {

                break;
            }
        }

    }

}
