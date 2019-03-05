/**
 * @Author Chris
 * @version 2019.03.05
 */
package team02.chris;

import ch.ntb.inf.deep.runtime.ppc32.Task;

import static team02.chris.Zustand.*;


/**
 *
 */
public class Main extends Task
{

    private Zustand zustand = SETUP;


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
            case SETUP:                     //Dies ist der Startzustand
            {

                if(false)                   //Weiterschaltbedingung
                {
                    zustand = SPIEL_BEGINNT;
                }
                break;
            }

            case SPIEL_BEGINNT:             //Kommentar
            {

                if(false)
                {
                    zustand = ROB_HAT_BALL;
                }
                else if(false)
                {
                    zustand = ROB_HAT_KEIN_BALL;
                }
                break;
            }
            case ROB_HAT_KEIN_BALL:         //Roboter hat keinen Ball
            {

                if (false)
                {
                    zustand = ROB_FAEHRT;
                }
                break;
            }
            case ROB_FAEHRT:                //Roboter faehrt
            {
                if(false)
                {
                    zustand = ROB_POSITION_ERREICHT;
                }

                break;
            }
            case ROB_HAT_BALL:              //Roboter hat Ball
            {
                if(false)
                {
                    zustand = KORB_WURF;
                }
                else if(false)
                {
                    zustand = LANGER_WURF;
                }
                else if (false)
                {
                    zustand = KURZER_WURF;
                }
                break;
            }
            case ROB_POSITION_ERREICHT:     //Roboter hat Position erreicht
            {

                if(false)
                {
                    zustand = ROB_HAT_BALL;
                }
                break;
            }
            case KORB_WURF:                 //Korb Wurf
            {
                if(false)
                {
                    zustand = ENDE;
                }

                break;
            }
            case KURZER_WURF:               //Kurzer Wurf
            {

                if(false)
                {
                    zustand = ROB_HAT_KEIN_BALL;
                }
                break;
            }
            case LANGER_WURF:               //Langer Wurf
            {
                if(false)
                {
                    zustand = ROB_HAT_KEIN_BALL;
                }
                break;
            }
            case ENDE:
            {
                break;
            }
        }

    }

}
