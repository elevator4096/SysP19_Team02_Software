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
    private boolean bsetup,bpass1,bpass2,bpass3,bpass4,bpass5,bpass6,bbewegen1,bbewegen2,bpos1,bpos2,ende;

    
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

            case ENDE:
            {
                ende();
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
        if(!bsetup)
        {
            Systeme.wurfSystem.Wandauf();
        }
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
            WlanSystem.setOwnState(ZustandWifi.FAHREN);
            Pos_Wechsel.fahre_zu_Pos1();
            bbewegen1 = true;
        }

        if(Pos_Wechsel.pos1_erreicht())
        {
            zustand = Pos1;
        }

    }

    /**
     * Aktion an 1. Pos
     */
    private void pos1()
    {
        if(!bpos1)
        {
            Systeme.wurfSystem.zylinderSpannen(Konstanten.Kurzer_Wurf);
            bpos1 = true;
        }

        if(Systeme.wurfSystem.zylinderGespannt())
        {
            WlanSystem.setOwnState(ZustandWifi.FANG_BEREIT);
            zustand = Pass4;
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

        if(Systeme.wurfSystem.zylinderGespannt() && IO.IN_Laser_2.get() && WlanSystem.getPartnerState() == ZustandWifi.FANG_BEREIT)
        {
            Systeme.wurfSystem.ballWerfen();
            zustand = Bewegen2;
        }
    }

    /**
     * Positionswechsel
     */
    private void bewegen2()
    {
        if(!bbewegen2)
        {
            Pos_Wechsel.fahre_zu_Pos2();
            bbewegen2 = true;
        }

        if(Pos_Wechsel.pos2_erreicht())
        {
            zustand = Pos2;
        }
    }

    /**
     * Aktion an 2. Pos
     */
    private void pos2()
    {
        if(!bpos2)
        {
            Systeme.wurfSystem.zylinderSpannen(Konstanten.Korb_Wurf);
            bpos2 = true;
        }

        if(Systeme.wurfSystem.zylinderGespannt())
        {
            WlanSystem.setOwnState(ZustandWifi.FANG_BEREIT);
            zustand = Pass6;
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
            Systeme.bewegungsSystem.dreheZuKorb();
            bpass6 = true;
        }

        if(!Systeme.bewegungsSystem.istInBewegung())
        {
            Systeme.wurfSystem.ballWerfen();
            zustand = ENDE;
        }
    }


    /**
     * End Zustand
     */
    private void ende()
    {
        if(!ende)
        {
            debug.print("Well done Robert!");
            ende = true;
        }
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
