package team02.chris;

import team02.Systeme;

public class Pos_Wechsel implements Systeme
{
    private enum Zustand
    {
        Test,
        Start,
        Start_Pos1,
        Start_Pos2,
        Gegner1,
        Gegner2,
        Gegner3,

        Ebene1,
        FahrezuWand,
        Traverse1,
        Drehe1,
        Wand,


        Standby,
    }

    private static double Distanz_G1 = 0.05;

    private static double Distanz_G2 = 0.168;

    private static double Distanz_G3 = 0.168;

    private static double Distanz_Linie = 0.2;

    private static boolean entry_flag;

    private static Zustand zustand = Zustand.Standby;

    public static void fahre_zu_Pos1()
    {
        //Entry
        if(!entry_flag)
        {
            zustand = Zustand.Start_Pos1;
            entry_flag = true;
        }

        //Exit
        if(false)
        {
            entry_flag = false;
        }
    }

    public static void fahre_zu_Pos2()
    {
        //Entry
        if(!entry_flag)
        {
            zustand = Zustand.Start_Pos1;
            entry_flag = true;
        }

        //Exit
        if(false)
        {
            entry_flag = false;
        }
    }

    public static boolean pos1_erreicht()
    {
        return true;
    }

    public static boolean pos2_erreicht()
    {
        return true;
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
            zustand = Zustand.Gegner1;
        }
        if(false)
        {
            entry_flag = false;
        }
    }

    /**
     * Hoehe Gegner 1: Entscheid ob weiter fahren (wenn Gegner vorhanden ist) oder zum drehen
     */
    public static void gegner1()
    {
        //Entry
        boolean b = false;
        if(!entry_flag)
        {
            if (Systeme.gegnerSystem.warGegnerRechts())
            {
                Systeme.bewegungsSystem.fahreFreiBisDistanz(true, Distanz_G2);
                b=false;
            }
            else
            {
                Systeme.bewegungsSystem.drehe90GradUZ();
                b = true;
            }
            entry_flag = true;
        }

        //Exit
        if(!Systeme.bewegungsSystem.istInBewegung())
        {
            if(!b)
            {
                zustand = Zustand.Gegner2;
            }
            if(b)
            {
                zustand = Zustand.Traverse1;
            }
            entry_flag = false;
        }
    }

    /**
     * Hoehe Gegner 2:
     */
    public static void gegner2()
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
     * Hoehe Gegner 3:
     */
    public static void gegner3()
    {

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
            Systeme.bewegungsSystem.drehe90GradGUZ();
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
        }

        //Exit
        if(!Systeme.bewegungsSystem.istInBewegung())
        {
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
