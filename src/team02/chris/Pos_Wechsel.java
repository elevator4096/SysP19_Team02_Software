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

    private static boolean entry_flag;

    private static Zustand zustand = Zustand.Standby;

    public static void fahre_zu_Pos1()
    {
        if(!entry_flag)
        {
            zustand = Zustand.Start_Pos1;
            entry_flag = true;
        }

        if(false)
        {
            entry_flag = false;
        }
    }

    public static void fahre_zu_Pos2()
    {
        if(!entry_flag)
        {
            zustand = Zustand.Start_Pos1;
            entry_flag = true;
        }

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


    public static void start_pos1()
    {
        if(!entry_flag)
        {
            Systeme.gegnerSystem.resetGegnerErkennung();
            Systeme.bewegungsSystem.fahreFreiBisDistanz(true, Distanz_G1);
            entry_flag =true;
        }

        if(!Systeme.bewegungsSystem.istInBewegung())
        {
            zustand = Zustand.Gegner1;
        }
        if(false)
        {
            entry_flag = false;
        }
    }

    public static void gegner1()
    {
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
            entry_flag = false;

    }

    public static void gegner2()
    {

        if(!entry_flag)
        {
            entry_flag = true;
        }

        if(false)
        {
            entry_flag = false;
        }
    }

    public static void gegner3()
    {

    }

    public static void traverse1()
    {

        if(!entry_flag)
        {
            Systeme.bewegungsSystem.fahreFreiBisDistanz(false,Distanz_Linie);
            entry_flag = true;
        }

        if(!Systeme.bewegungsSystem.istInBewegung())
        {
            zustand = Zustand.Ebene1;
            entry_flag = false;
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
