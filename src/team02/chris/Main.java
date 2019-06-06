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
import team02.vorlagen.Pos_Wechsel_V2;

import static team02.Zustand.*;

public class Main extends Task implements IO, Systeme
{
	//TODO: Zylinder vollstaendig einfahren um zu fangen
    private Zustand zustand = SETUP;
    private Zustand letzter_Zustand;
    private boolean entry_flag;
    private int lastSystemTime;
	 boolean b = false;
    
    /**
     * Initialisieren des Tasks
     */
    static
    {
            Task task = new Main();
            task.period = (int)(Konstanten.TASK_PERIOD*1000);
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
            fehler();
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
            case START:
            {
            	start();
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
        //Entry
        if(!entry_flag)
        {
            entry_flag = true;
            WlanSystem.setOwnState(ZustandWifi.SETUP);
            lastSystemTime = Task.time();
            Systeme.wurfSystem.zylinderSpannen(Konstanten.Holen);
        }

        //Exit
        if(Task.time() > lastSystemTime + 5000)                   //Weiterschaltbedingung
        {
        	entry_flag = false;
        	zustand = Zustand.START;
        }
    }
    
    
    private void start() {
        //Entry
        if(!entry_flag)
        {
        	Systeme.wurfSystem.zylinderSpannen(Konstanten.Langer_Wurf);
        	 lastSystemTime = Task.time();
        	 debug.println("Start entry");
        	 Systeme.wurfSystem.Wandauf();  
            entry_flag = true;
        }


        //Exit
        if(IO.IN_Laser_1.get() && WlanSystem.getPartnerState() == ZustandWifi.START && Task.time() > lastSystemTime + 2000)                   //Weiterschaltbedingung
        {        	 
        	debug.println("Start exit 1");
            entry_flag =false;
            zustand = Pass2;
        } else if (!IO.IN_Laser_1.get() && WlanSystem.getPartnerState() == ZustandWifi.START && Task.time() > lastSystemTime + 2000)
        {
        	debug.println("Start exit 2");
            zustand = Pass1;
            entry_flag = false;
        }
    }

    /**
     * Zusatz Pass falls Partnerteam den Ball hat
     */
    private void pass1()
    {
        //Entry
        if(!entry_flag)
        {
        	lastSystemTime = Task.time();
            //Systeme.wurfSystem.zylinderSpannen(Konstanten.Langer_Wurf);
            entry_flag = true;
        }

        if(Systeme.wurfSystem.zylinderGespannt())
        {
            WlanSystem.setOwnState(ZustandWifi.FANG_BEREIT);
        }
        //Exit
        if(IO.IN_Laser_1.get() && Task.time() > lastSystemTime + 2000)
        {
            //Exit Aktion
            zustand = Pass2;
            entry_flag = false;
        }
    }

    /**
     * 1. Regulaerer Pass, lang, Wir
     */
    private void pass2()
    {
        //Entry
        if(!entry_flag)
        {
            //Systeme.wurfSystem.zylinderSpannen(Konstanten.Langer_Wurf);
            entry_flag = true;
            WlanSystem.setOwnState(ZustandWifi.WURF_BEREIT);
        }

        //Exit
        if(Systeme.wurfSystem.zylinderGespannt() && WlanSystem.getPartnerState()==ZustandWifi.FANG_BEREIT)
        {
            Systeme.wurfSystem.ballWerfen();

            zustand = Bewegen1;
            entry_flag = false;
        }
    }

    /**
     * Positionswechsel
     */
    private void bewegen1()
    {
        //Entry
        if(!entry_flag)
        {
        	Systeme.wurfSystem.zylinderSpannen(Konstanten.Holen);
            WlanSystem.setOwnState(ZustandWifi.FAHREN);
            Pos_Wechsel_V2.fahre_zu_Pos1();
            lastSystemTime = Task.time();
            entry_flag = true;
        }

        
        if(Task.time() > lastSystemTime + 6000) {
        	Systeme.wurfSystem.zylinderSpannen(Konstanten.Langer_Wurf);
        }
        
        //Exit
        if(Pos_Wechsel_V2.pos1_erreicht())
        {
            zustand = Pos1;
            entry_flag = false;
        }
    }

    /**
     * Aktion an 1. Pos
     */
    private void pos1()
    {
        //Entry
        if(!entry_flag)
        {
        	WlanSystem.setOwnState(ZustandWifi.FANG_BEREIT);   
            entry_flag = true;
        }
        
       
        if(IO.IN_Laser_1.get() && !b) {
        	b = true;
        	lastSystemTime = Task.time();
        }

        //Exit
        if(b && Task.time() > lastSystemTime + 2000)
        {
            Systeme.wurfSystem.zylinderSpannen(Konstanten.Kurzer_Wurf);
            zustand = Pass4;
            b = false;
            entry_flag = false;
        }
    }

    /**
     * 2. Pass, kurz, Partnerteam
     */
    private void pass3()
    {
        //Entry
        if(!entry_flag)
        {
            entry_flag = true;
        }

        //Exit
        if(false)
        {
        	zustand = Pass4;
            entry_flag = false;
        }
    }


    /**
     * 3. Pass, kurz, Wir
     */
    private void pass4()
    {
        //Entry
        if(!entry_flag)
        {
        	WlanSystem.setOwnState(ZustandWifi.WURF_BEREIT);   
            entry_flag = true;
        }


        //Exit
        if(Systeme.wurfSystem.zylinderGespannt() && IO.IN_Laser_1.get() && WlanSystem.getPartnerState() == ZustandWifi.FANG_BEREIT)
        {
            Systeme.wurfSystem.ballWerfen();
            zustand = Bewegen2;
            entry_flag = false;
        }
    }

    /**
     * Positionswechsel
     */
    private void bewegen2()
    {
        //Entry
        if(!entry_flag)
        {
        	WlanSystem.setOwnState(ZustandWifi.FAHREN);   
            Pos_Wechsel_V2.fahre_zu_Pos2();
            lastSystemTime = Task.time();
            Systeme.wurfSystem.zylinderSpannen(Konstanten.Holen);
            entry_flag = true;
        }
        
        if(Task.time() > lastSystemTime + 6000) {
        	Systeme.wurfSystem.zylinderSpannen(Konstanten.Langer_Wurf);
        }
        
        //Exit
        if(Pos_Wechsel_V2.pos2_erreicht())
        {
            zustand = Pos2;
            entry_flag = false;
        }
    }

    /**
     * Aktion an 2. Pos
     */
    private void pos2()
    {
        //Entry
        if(!entry_flag)
        {
        	WlanSystem.setOwnState(ZustandWifi.FANG_BEREIT);   
            entry_flag = true;
        }
        
        if(IO.IN_Laser_1.get() && !b) {
        	b = true;
        	lastSystemTime = Task.time();
        }


        //Exit
        if(b && Task.time() > lastSystemTime + 2000)
        {
            Systeme.wurfSystem.zylinderSpannen(Konstanten.Kurzer_Wurf);
            zustand = Pass6;
            entry_flag = false;
        }


    }

    /**
     * 4. Pass, kurz, Partnerteam
     */
    private void pass5()
    {
        //Entry
        if(!entry_flag)
        {
            entry_flag = true;
        }

        //Exit
        if(false)
        {
            entry_flag = false;
        }
    }

    /**
     * Korbwurf
     */
    private void pass6()
    {
        //Entry
        if(!entry_flag)
        {
            //Systeme.bewegungsSystem.dreheZuKorb();
            entry_flag = true;
        }

        //Exit
        if(!Systeme.bewegungsSystem.istInBewegung())
        {
            Systeme.wurfSystem.ballWerfen();
            zustand = ENDE;
            entry_flag = false;
        }
    }


    /**
     * End Zustand
     */
    private void ende()
    {
        //Entry
        if(!entry_flag)
        {
            debug.print("Well done Roberto!");
            entry_flag = true;
        }

        //Exit
        if(false)
        {
            entry_flag = true;
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
    	WlanSystem.update();
    	Pos_Wechsel_V2.update();
    }
}
