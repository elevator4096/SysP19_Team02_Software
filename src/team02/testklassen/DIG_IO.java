/**
 * @author Chris
 * @version 2019.03.27
 */
package team02.testklassen;

import ch.ntb.inf.deep.runtime.mpc555.driver.MDASM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import team02.IO;

public class DIG_IO implements IO
{
    /**
     * Schreibt den Aktuellen Zustand auf die Serielle Schnittstelle
     * @param in der IO der ausgelesen werden soll
     */
    public static void getMPIOSM_DIO(MPIOSM_DIO in)
    {
        debugSystem.print("Eingang ist: ");
        debugSystem.print(in.get());
        debugSystem.println();
    }

    /**
     * Schreibt den Aktuellen Zustand auf die Serielle Schnittstelle
     * @param in der IO der ausgelesen werden soll
     */
    public static void getMDASM(MDASM_DIO in)
    {
        debugSystem.print("Eingang ist: ");
        debugSystem.print(in.get());
        debugSystem.println();
    }

    /**
     * Setzt den Ausgang und gibt den Zustand auf die serielle Schnittstelle aus
     * @param out der Ausgang der gesetzt werden soll
     * @param b True(High) oder False(Low)
     */
    public static void setMPIOSM_DIO(MPIOSM_DIO out, boolean b)
    {
        out.set(b);
        debugSystem.print("Ausgang ist: ");
        debugSystem.print(out.get());
        debugSystem.println();
    }

    /**
     * Setzt den Ausgang und gibt den Zustand auf die serielle Schnittstelle aus
     * @param out der Ausgang der gesetzt werden soll
     * @param b True(High) oder False(Low)
     */
    public static void setMPIOSM_DIO(MDASM_DIO out, boolean b)
    {
        out.set(b);
        debugSystem.print("Ausgang ist: ");
        debugSystem.print(out.get());
        debugSystem.println();
    }

    /**
     * Achtung!!!! Dieser Test beinhaltet eine While Schleife, die zuerst komplett durchlaufen wird bis weitere
     * Befehle ausgefuehrt werden. Dauer: 1s!!
     */
    public static void LED_Test()
    {
        int i = 0;
        OUT_LED1.set(true);
        OUT_LED2.set(false);
        OUT_LED3.set(false);
        OUT_LED4.set(false);
        OUT_LED5.set(false);
        long l =  System.currentTimeMillis();
        while(l+1000 <  System.currentTimeMillis())
        {
            if(l+200 <  System.currentTimeMillis())
            {
                OUT_LED1.set(false);
                OUT_LED2.set(true);
                i++;
            }
            if(l+400 <  System.currentTimeMillis() && i==1)
            {
                OUT_LED2.set(false);
                OUT_LED3.set(true);
                i++;
            }
            if(l+600 <  System.currentTimeMillis() && i==2)
            {
                OUT_LED3.set(false);
                OUT_LED4.set(true);
                i++;
            }
            if(l+800 <  System.currentTimeMillis() && i==3)
            {
                OUT_LED4.set(false);
                OUT_LED5.set(true);
                i++;
            }

        }
        OUT_LED1.set(false);
        OUT_LED2.set(false);
        OUT_LED3.set(false);
        OUT_LED4.set(false);
        OUT_LED5.set(false);
    }
}
