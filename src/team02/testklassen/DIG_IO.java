/*
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
     * Invertiert die Ausgaenge!
     */
    public static void LED_Test()
    {
    	debugSystem.println("Test");
    	OUT_LED1.set(!OUT_LED1.get());
    	OUT_LED2.set(!OUT_LED2.get());
    	OUT_LED3.set(!OUT_LED3.get());
    	OUT_LED4.set(!OUT_LED4.get());
    	OUT_LED5.set(!OUT_LED5.get());
    }
    
    public static void get()
    {
    	debugSystem.print(OUT_LED1.get());
    	debugSystem.println("");
    }
}
