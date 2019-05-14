package team02.chris;

import team02.IO;
import team02.Systeme;

public class Pos_Wechsel implements Systeme
{
	private static boolean pos1Erreicht = false;
	private static boolean pos2Erreicht = false;
				   
	
    private enum Zustand
    {
        Test,
        Start,
        Start_Pos1,
        Start_Pos2,
        Gegner11,
        Gegner12,
        Gegner13,
        Gegner14,
        Gegner21,
        Gegner22,
        Gegner23,
        Gegner24,
        Gegner31,
        Gegner32,
    	Gegner33,
    	Gegner34,
        

        Ebene1,
        FahrezuWand,
        Traverse1,
        Drehe1,
        Wand,


        Standby,
        Fehler
    }

    private static double Distanz_G1 = 0.05;

    private static double Distanz_G2 = 0.168;

    private static double Distanz_G3 = 0.168;

    private static double Distanz_Linie = 0.2;

    private static boolean entry_flag;

    private static Zustand zustand = Zustand.Standby;

    public static void fahre_zu_Pos1()
    {
        zustand = Zustand.Start_Pos1;
    }

    public static void fahre_zu_Pos2()
    {
        zustand = Zustand.Start_Pos1;
    }

    public static boolean pos1_erreicht()
    {
        return pos1Erreicht;
    }

    public static boolean pos2_erreicht()
    {
        return pos2Erreicht;
    }

    /**
     * Fahre bis zum ersten Gegner
     */
    public static void start_pos1()
    {
        //Entry
        if(!entry_flag)
        {
            Systeme.gegnerSystem.resetGegnerErkennung();
            Systeme.bewegungsSystem.fahreFreiBisDistanz(true, Distanz_G1);
            entry_flag =true;
        }

        //Exit
        if(!Systeme.bewegungsSystem.istInBewegung())
        {
            zustand = Zustand.Gegner11;
            entry_flag = false;
        }
    }

    /**
     * Hoehe Gegner 11: Entscheid ob weiter fahren (wenn Gegner vorhanden ist) oder zum drehen
     */
    public static void gegner11()
    {
        //Entry
        boolean gegnerGesehen = false;
        if(!entry_flag)
        {
            if (Systeme.gegnerSystem.warGegnerRechts())
            {
                Systeme.bewegungsSystem.fahreFreiBisDistanz(true, Distanz_G2);
                gegnerGesehen = true;
            }
            else
            {
                Systeme.bewegungsSystem.drehe90GradUZ();
                gegnerGesehen = false;
            }
            entry_flag = true;
        }

        //Exit
        if(!Systeme.bewegungsSystem.istInBewegung())
        {
            if(gegnerGesehen)
            {
                zustand = Zustand.Gegner21;
            }
            if(!gegnerGesehen)
            {
                zustand = Zustand.Traverse1;
            }
            entry_flag = false;
        }
    }

    /**
     * Hoehe Gegner 21:
     */
    public static void gegner21()
    {
    	   //Entry
        boolean gegnerGesehen = false;
        if(!entry_flag)
        {
            if (Systeme.gegnerSystem.warGegnerRechts())
            {
                Systeme.bewegungsSystem.fahreFreiBisDistanz(true, Distanz_G3);
                gegnerGesehen = true;
            }
            else
            {
                Systeme.bewegungsSystem.drehe90GradUZ();
                gegnerGesehen = false;
            }
            entry_flag = true;
        }

        //Exit
        if(!Systeme.bewegungsSystem.istInBewegung())
        {
            if(gegnerGesehen)
            {
                zustand = Zustand.Gegner3;
            }
            if(!gegnerGesehen)
            {
                zustand = Zustand.Traverse1;
            }
            entry_flag = false;
        }
    }

    /**
     * Hoehe Gegner 31:
     */
    public static void gegner31()
    {
    	//Entry
        boolean gegnerGesehen = false;
        if(!entry_flag)
        {
            if (Systeme.gegnerSystem.warGegnerRechts())
            {
                IO.debug.println("Error: 3 Gegner gesehen");
                gegnerGesehen = true;
            }
            else
            {
                Systeme.bewegungsSystem.drehe90GradUZ();
                gegnerGesehen = false;
            }
            entry_flag = true;
        }

        //Exit
        if(!Systeme.bewegungsSystem.istInBewegung())
        {
            if(gegnerGesehen)
            {
                zustand = Zustand.Fehler;
            }
            if(!gegnerGesehen)
            {
                zustand = Zustand.Traverse1;
            }
            entry_flag = false;
        }
    }

    /**
     * 1. Gegnerreihe Traversieren
     */
    public static void traverse1()
    {
        //Entry
        if(!entry_flag)
        {
            Systeme.bewegungsSystem.fahreFreiBisDistanz(false,Distanz_Linie);
            entry_flag = true;
        }

        //Exit
        if(!Systeme.bewegungsSystem.istInBewegung())
        {
            zustand = Zustand.Ebene1;
            entry_flag = false;
        }


    }

    public static void ebene1()
    {
        //Entry
        if(!entry_flag)
        {
            Systeme.bewegungsSystem.drehe90GradUZ();
            entry_flag = true;
        }

        //Exit
        if(!Systeme.bewegungsSystem.istInBewegung())
        {
            zustand = Zustand.FahrezuWand;
            entry_flag = false;
        }
    }

    public static void fahreZuWand()
    {
        //Entry
        if(!entry_flag)
        {
            Systeme.bewegungsSystem.folgeLinieBisWandRueckwaerts();
            entry_flag = true;
        }

        //Exit
        if(!Systeme.bewegungsSystem.istInBewegung())
        {
            entry_flag = false;
            zustand = Zustand.Wand;
        }
    }

    public static void wand()
    {
        //Entry
        if(!entry_flag)
        {
            Systeme.bewegungsSystem.drehe90GradGUZ();
            entry_flag = true;
        }

        //Exit
        if(!Systeme.bewegungsSystem.istInBewegung())
        {
        	IO.debug.println("Position 1 erreicht");
        	pos1Erreicht = true;
            entry_flag = false;
        }
    }

    public static void test()
    {
        zustand = Zustand.Start_Pos1;
    }

    public static void update()
    {
        switch(zustand)
        {
            case Start_Pos1:
            {
                start_pos1();
                break;
            }

            case Gegner1:
            {
                gegner1();
                break;
            }

            case Gegner2:
            {
                gegner2();
                break;
            }

            case Gegner3:
            {
                gegner3();
                break;
            }

            case Traverse1:
            {
                traverse1();
                break;
            }

            case Ebene1:
            {
                ebene1();
                break;
            }

            case FahrezuWand:
            {
                fahreZuWand();
                break;
            }

            case Wand:
            {
                wand();
                break;
            }

            default:
            {
                break;
            }
        }
    }
}
