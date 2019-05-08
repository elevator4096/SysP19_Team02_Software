package team02.chris;

public class Pos_Wechsel
{
    private enum Zustand_Ablauf
    {
        Test,
        Start,

    }
    private static Zustand_Ablauf ablauf 					= Zustand_Ablauf.Test;

    public static void fahre_zu_Pos1()
    {
        switch(ablauf)
        {
            case Test:
            {

            }

            case Start:
            {


            }
        }
    }

    public static void fahre_zu_Pos2()
    {

    }

    public static boolean pos1_erreicht()
    {
        return true;
    }

    public static boolean pos2_erreicht()
    {
        return true;
    }

}
