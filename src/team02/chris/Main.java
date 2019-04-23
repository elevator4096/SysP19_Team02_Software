/*
 * Das ist die "Hauptklasse" des Roboter Programms
 * Alle weiteren Klassen werden hier aufgerufen
 * @Author Chris
 * @version 2019.03.24
 */
package team02.chris;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import team02.*;
import team02.vorlagen.*;
import team02.Systeme;

import static team02.Zustand.*;

public class Main extends Task implements IO, Systeme
{

    private Zustand zustand = SETUP;
    private Zustand letzter_Zustand;

    /**
     * Initialisieren des Tasks
     */
    static
    {
        try
        {
            Task task = new Main();
            task.period = (int)Konstanten.TASK_PERIOD*1000;
            Task.install(task);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Konstruktor
     */
    public Main()
    {	
    	
    }

    /**
     * Methode die Zyklisch aufgerufen wird
     */
    public void action()
    {
        OUT_LED1.set(!OUT_LED1.get());

        update();
        if (Konstanten.DEBUG)         //wird nur aufgerufen wenn Debug aktiviert ist
        {
            debug.println("Debug aktiv");
        }

        if (Konstanten.TEST)
        {
            debug.println("Test aktiv");
        }

        //Test

        //Fehler erkannt
        if(false)
        {

            zustand = FEHLER;
        }

        zustand();                  //Zustaende gemaess Zustandsdiagramm
        letzter_Zustand = zustand;
    }

    /**
     * Schrittkette
     */
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

    /**
     * Setup Methode
     */
    private void setup()
    {
        if(false)                   //Weiterschaltbedingung
        {
            zustand = SPIEL_BEGINNT;
        }
    }

    /**
     * Spiel beginnt Zustand
     */
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

    /**
     * Rob Hat kein Ball Zustand
     */
    private void rob_hat_kein_ball()
    {
        if (false)
        {
            zustand = ROB_FAEHRT;
        }
    }

    /**
     * Rob Faehrt Zustand
     */
    private void rob_faehrt()
    {
        if(false)
        {
            zustand = ROB_POSITION_ERREICHT;
        }
    }

    /**
     * Rob Hat Ball Zustand
     */
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

    /**
     * Rob Position Erreicht Zustand
     */
    private void rob_position_erreicht()
    {
        if(false)
        {
            zustand = ROB_HAT_BALL;
        }
    }

    /**
     * Korb Wurf Zustand
     */
    private void korb_wurf()
    {
        if(false)
        {
            zustand = ENDE;
        }
    }

    /**
     * Kurzer Wurf Zustand
     */
    private void kurzer_wurf()
    {
        if(false)
        {
            zustand = ROB_HAT_KEIN_BALL;
        }
    }

    /**
     * Langer Wurf Zustand
     */
    private void langer_wurf()
    {
        if(false)
        {
            zustand = ROB_HAT_KEIN_BALL;
        }
    }

    /**
     * End Zustand
     */
    private void ende()
    {

    }

    /**
     * Fehler Zustand
     */
    private void fehler()
    {

    }

    /**
     * Ruft alle anderen update methoden auf
     */
    private void update()
    {
    	IO.debug.update();
        Systeme.wlanSystem.update();
        Systeme.bewegungsSystem.update();
        Systeme.gegnerSystem.update();
        Systeme.wurfSystem.update();
    }
}
