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

        Traverse1,
        Wand,

        Standby,
    }

    private static double Distanz_G1 = 0.05;

    private static double Distanz_G2 = 0.168;

    private static double Distanz_G3 = 0.168;

    private static boolean fPos1,fStart_Pos1,fGegner1,fPos2;

    private static Zustand zustand = Zustand.Standby;

    public static void fahre_zu_Pos1()
    {
        if(!fPos1)
        {
            zustand = Zustand.Start_Pos1;
            fPos1 = true;
        }
    }

    public static void fahre_zu_Pos2()
    {
        if(!fPos2)
        {
            zustand = Zustand.Start_Pos1;
            fPos2 = true;
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


    public static void start_pos1()
    {
        if(!fStart_Pos1)
        {
            Systeme.gegnerSystem.resetGegnerErkennung();
            Systeme.bewegungsSystem.fahreFreiBisDistanz(true, Distanz_G1);
            fStart_Pos1=true;
        }

        if(!Systeme.bewegungsSystem.istInBewegung())
        {
            zustand = Zustand.Gegner1;
        }
    }

    public static void gegner1()
    {
        if(!fGegner1) {
            if (Systeme.gegnerSystem.warGegnerRechts()) {
                Systeme.bewegungsSystem.fahreFreiBisDistanz(true, Distanz_G2);
            } else {

            }
            fGegner1 = true;
        }

        if(!Systeme.bewegungsSystem.istInBewegung())
        {
            zustand = Zustand.Gegner2;
        }
    }

    public static void gegner2()
    {

    }

    public static void gegner3()
    {

    }

    public static void traverse1()
    {

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

            default:
            {
                break;
            }
        }
    }
}
