/**
 * @Author Chris
 * @version 2019.03.05
 */
package team02.chris;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import team02.IO;
import team02.Konstanten;

import static team02.chris.Zustand.*;

public class Main extends Task implements IO
{

    private Zustand zustand = SETUP;
    private Zustand letzter_Zustand;
    Fahren fahren;

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
        IN_Taster1.set(true);
        fahren = new Fahren();
    }

    public void action()
    {
        if (Konstanten.DEBUG)
        {
            fahren.drehe(1);
        }



        //Fehler erkannt
        if(false)
        {
            letzter_Zustand = zustand;
            zustand = FEHLER;
        }
        zustand();
    }

    private void zustand()
    {
        switch (zustand)
        {
            case SETUP:                     //Dies ist der Startzustand
            {
                setup();
                break;
            }

            case SPIEL_BEGINNT:             //Kommentar
            {
                spiel_beginnt();
                break;
            }
            case ROB_HAT_KEIN_BALL:         //Roboter hat keinen Ball
            {

                rob_hat_kein_ball();
                break;
            }
            case ROB_FAEHRT:                //Roboter faehrt
            {
                rob_faehrt();
                break;
            }
            case ROB_HAT_BALL:              //Roboter hat Ball
            {
                rob_hat_ball();
                break;
            }
            case ROB_POSITION_ERREICHT:     //Roboter hat Position erreicht
            {
                rob_position_erreicht();
                break;
            }
            case KORB_WURF:                 //Korb Wurf
            {
                korb_wurf();
                break;
            }
            case KURZER_WURF:               //Kurzer Wurf
            {
                kurzer_wurf();
                break;
            }
            case LANGER_WURF:               //Langer Wurf
            {
                langer_wurf();
                break;
            }
            case ENDE:
            {
                ende();
                break;
            }
            case FEHLER:
            {
                fehler();
                break;
            }

            default:
            {
                zustand = FEHLER;
                break;
            }
        }
    }

    private void setup()
    {
        if(false)                   //Weiterschaltbedingung
        {
            zustand = SPIEL_BEGINNT;
        }
    }

    private void spiel_beginnt()
    {
        if(false)
        {
            zustand = ROB_HAT_BALL;
        }
        else if(false)
        {
            zustand = ROB_HAT_KEIN_BALL;
        }
    }

    private void rob_hat_kein_ball()
    {
        if (false)
        {
            zustand = ROB_FAEHRT;
        }
    }

    private void rob_faehrt()
    {
        if(false)
        {
            zustand = ROB_POSITION_ERREICHT;
        }
    }

    private void rob_hat_ball()
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
    }

    private void rob_position_erreicht()
    {
        if(false)
        {
            zustand = ROB_HAT_BALL;
        }
    }

    private void korb_wurf()
    {
        if(false)
        {
            zustand = ENDE;
        }
    }

    private void kurzer_wurf()
    {
        if(false)
        {
            zustand = ROB_HAT_KEIN_BALL;
        }
    }

    private void langer_wurf()
    {
        if(false)
        {
            zustand = ROB_HAT_KEIN_BALL;
        }
    }

    private void ende()
    {

    }

    private void fehler()
    {

    }
}
