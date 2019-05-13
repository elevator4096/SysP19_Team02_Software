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
        Traverse1,
        Wand,

        Standby,
    }

    private static double Distanz_G1 = 0.05;

    private static double Distanz_G2 = 0.168;

    private static double Distanz_G3 = 0.168;

    private static double Distanz_Linie = 0.2;

    private static boolean bflag;

    private static Zustand zustand = Zustand.Standby;

    public static void fahre_zu_Pos1()
    {
        if(!bflag)
        {
            zustand = Zustand.Start_Pos1;
            bflag = true;
        }

        if(false)
        {
            bflag = false;
        }
    }

    public static void fahre_zu_Pos2()
    {
        if(!bflag)
        {
            zustand = Zustand.Start_Pos1;
            bflag = true;
        }

        if(false)
        {
            bflag = false;
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
        if(!bflag)
        {
            Systeme.gegnerSystem.resetGegnerErkennung();
            Systeme.bewegungsSystem.fahreFreiBisDistanz(true, Distanz_G1);
            bflag=true;
        }

        if(!Systeme.bewegungsSystem.istInBewegung())
        {
            zustand = Zustand.Gegner1;
        }
        if(false)
        {
            bflag = false;
        }
    }

    public static void gegner1()
    {
        boolean b = false;
        if(!bflag)
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
            bflag = true;
        }



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
        }
            bflag = false;

    }

    public static void gegner2()
    {

        if(!bflag)
        {
            bflag = true;
        }

        if(false)
        {
            bflag = false;
        }
    }

    public static void gegner3()
    {

    }

    public static void traverse1()
    {

        if(!bflag)
        {
            Systeme.bewegungsSystem.fahreFreiBisDistanz(false,Distanz_Linie);
            bflag = true;
        }

        if(!Systeme.bewegungsSystem.istInBewegung())
        {
            zustand = Zustand.Ebene1;
            bflag = false;
        }


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
