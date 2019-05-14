package team02.chris;

import team02.IO;
import team02.Systeme;

public class Pos_Wechsel implements Systeme
{
	private static boolean posBErreicht = false; // Main pos1
	private static boolean posCErreicht = false;
	private static boolean posDErreicht = false; // Main pos2
	
	
				   
	
    private enum Zustand
    {
        Test,
        Start,
        Start_Pos1,
        Start_Pos2,
        
        Gegner34, Gegner33, Gegner32, Gegner31,
        Gegner24, Gegner23, Gegner22, Gegner21,
        Gegner14, Gegner13, Gegner12, Gegner11,
        
        Ebene4,
        Ebene3,
        Ebene2,
        Ebene1,
        
        Traverse3,
        Traverse2,
        Traverse1,
        
        FahrezuWand,
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
        return posBErreicht;
    }

    public static boolean pos2_erreicht()
    {
        return posDErreicht;
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
     * Fahre bis zum ersten Gegner
     */
    public static void start_pos2()
    {
        //Entry
        if(!entry_flag)
        {
            Systeme.gegnerSystem.resetGegnerErkennung();
            Systeme.bewegungsSystem.drehe90GradUZ();
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
                zustand = Zustand.Gegner12;
            }
            if(!gegnerGesehen)
            {
                zustand = Zustand.Traverse1;
            }
            entry_flag = false;
        }
    }

    /**
     * Hoehe Gegner 12:
     */
    public static void gegner12()
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
                zustand = Zustand.Gegner13;
            }
            if(!gegnerGesehen)
            {
                zustand = Zustand.Traverse1;
            }
            entry_flag = false;
        }
    }

    /**
     * Hoehe Gegner 13:
     */
    public static void gegner13()
    {
    	//Entry
        boolean gegnerGesehen = false;
        if(!entry_flag)
        {
            if (Systeme.gegnerSystem.warGegnerRechts())
            {
                IO.debug.println("Error: Gegner 13 gesehen");
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
            zustand = Zustand.Ebene2;
            entry_flag = false;
        }


    }

    public static void ebene2()
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
        	
        	//setzt die jeweils naechste Position auf true 
        	if(posBErreicht) {
        		if(posCErreicht)
        		{
        			posDErreicht = true;
        		}
        		else
        		{
        			posCErreicht = true;
        		}
        		
        	}else {
        		posBErreicht = true;
        	}
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

            case Gegner11:
            {
                gegner11();
                break;
            }

            case Gegner12:
            {
                gegner12();
                break;
            }

            case Gegner13:
            {
                gegner13();
                break;
            }

            case Traverse1:
            {
                traverse1();
                break;
            }

            case Ebene2:
            {
                ebene2();
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
            
            case Start_Pos2:
            {
                start_pos2();
                break;
            }

            case Gegner24:
            {
                gegner24();
                break;
            }

            case Gegner23:
            {
                gegner23();
                break;
            }

            case Gegner22:
            {
                gegner22();
                break;
            }

            case Traverse2:
            {
                traverse2();
                break;
            }

            case Ebene3:
            {
                ebene3();
                break;
            }


            default:
            {
                break;
            }
        }
    }
}
