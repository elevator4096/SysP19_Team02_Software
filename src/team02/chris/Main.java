/*
 * Das ist die "Hauptklasse" des Roboter Programms
 * Alle weiteren Klassen werden hier aufgerufen
 * @Author Chris
 * @version 2019.03.24
 */
package team02.chris;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import exchange.WlanSystem;
import exchange.ZustandWifi;
import team02.*;
import team02.vorlagen.*;

import static team02.Zustand.*;

public class Main extends Task implements IO, Systeme
{

    private Zustand zustand = SETUP;
    private Zustand letzter_Zustand;
    private boolean bsetup,bpass1,bpass2,bpass3,bpass4,bpass5,bpass6,bbewegen1,bbewegen2,bpos1,bpos2;

    
    /**
     * Initialisieren des Tasks
     */
    static
    {
            Task task = new Main();
            task.period = (int)Konstanten.TASK_PERIOD*1000;
            Task.install(task);
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
        update();
        if (Konstanten.DEBUG)         //wird nur aufgerufen wenn Debug aktiviert ist
        {
            debug.println("Debug aktiv");
        }

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
            case Pass1:
            {
                pass1();
                break;
            }
            case Pass2:
            {
                pass2();
                break;
            }
            case Pass3:
            {
                pass3();
                break;
            }
            case Pass4:
            {
                pass4();
                break;
            }
            case Pass5:
            {
                pass5();
                break;
            }
            case Pass6:
            {
                pass6();
                break;
            }
            case Bewegen1:
            {
                bewegen1();
                break;
            }
            case Bewegen2:
            {
                bewegen2();
                break;
            }
            case Pos1:
            {
                pos1();
                break;
            }
            case Pos2:
            {
                pos2();
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
        Systeme.wurfSystem.Wandauf();
        if(IO.IN_Laser_2.get())                   //Weiterschaltbedingung
        {
            zustand = Pass2;
        } else {
            zustand = Pass1;
        }
    }


    /**
     * Zusatz Pass falls Partnerteam den Ball hat
     */
    private void pass1()
    {
        //Entry Aktion
        if(!bpass1)
        {
            Systeme.wurfSystem.zylinderSpannen(Konstanten.Langer_Wurf);
            bpass1 = true;
        }

        if(Systeme.wurfSystem.zylinderGespannt())
        {
            WlanSystem.setOwnState(ZustandWifi.FANG_BEREIT);
        }
        //Exit Bedingung
        if(IO.IN_Laser_2.get())
        {
            //Exit Aktion
            zustand = Pass2;
        }
    }

    /**
     * 1. Regulaerer Pass, lang, Wir
     */
    private void pass2()
    {
        if(!bpass2)
        {
            Systeme.wurfSystem.zylinderSpannen(Konstanten.Langer_Wurf);
            bpass2 = true;
        }

        if(Systeme.wurfSystem.zylinderGespannt() && WlanSystem.getPartnerState()==ZustandWifi.FANG_BEREIT)
        {
            Systeme.wurfSystem.ballWerfen();
            zustand = Bewegen1;
        }


    }

    /**
     * Positionswechsel
     */
    private void bewegen1()
    {
        if(!bbewegen1)
        {

            bbewegen1 = true;
        }

    }

    /**
     * 2. Pass, kurz, Partnerteam
     */
    private void pass3()
    {
        if(!bpass3)
        {

            bpass3 = true;
        }

    }

    /**
     * 3. Pass, kurz, Wir
     */
    private void pass4()
    {
        if(!bpass4)
        {

            bpass4 = true;
        }
    }

    /**
     * 4. Pass, kurz, Partnerteam
     */
    private void pass5()
    {
        if(!bpass5)
        {

            bpass5 = true;
        }

    }

    /**
     * Korbwurf
     */
    private void pass6()
    {
        if(!bpass6)
        {

            bpass6 = true;
        }
    }



    /**
     * Positionswechsel
     */
    private void bewegen2()
    {
        if(!bbewegen2)
        {

            bbewegen2 = true;
        }
    }

    /**
     * Aktion an 1. Pos
     */
    private void pos1()
    {
        if(!bpos1)
        {

            bpos1 = true;
        }
    }

    /**
     * Aktion an 2. Pos
     */
    private void pos2()
    {
        if(!bpos2)
        {

            bpos2 = true;
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
    	//ALLE Systeme updaten(sollte nur zu Testzwecken entfernt werden)
    	IO.debug.update();
    	Systeme.gegnerSystem.update();
    	Systeme.wurfSystem.update();
    	Systeme.bewegungsSystem.update();
    	Systeme.wlanSystem.update();
    }
}
