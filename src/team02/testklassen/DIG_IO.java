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
	
	public static void test()
	{
		test_allOUT();
	}
	
    /**
     * Schreibt den Aktuellen Zustand auf die Serielle Schnittstelle
     * @param in der IO der ausgelesen werden soll
     */
    public static void getMPIOSM_DIO(MPIOSM_DIO in)
    {
        debug.print("Eingang ist: ");
        debug.print(in.get());
        debug.println();
    }

    /**
     * Schreibt den Aktuellen Zustand auf die Serielle Schnittstelle
     * @param in der IO der ausgelesen werden soll
     */
    public static void getMDASM(MDASM_DIO in)
    {
        debug.print("Eingang ist: ");
        debug.print(in.get());
        debug.println();
    }

    /**
     * Setzt den Ausgang und gibt den Zustand auf die serielle Schnittstelle aus
     * @param out der Ausgang der gesetzt werden soll
     * @param b True(High) oder False(Low)
     */
    public static void setMPIOSM_DIO(MPIOSM_DIO out, boolean b)
    {
        out.set(b);
        debug.print("Ausgang ist: ");
        debug.print(out.get());
        debug.println();
    }

    /**
     * Setzt den Ausgang und gibt den Zustand auf die serielle Schnittstelle aus
     * @param out der Ausgang der gesetzt werden soll
     * @param b True(High) oder False(Low)
     */
    public static void setMPIOSM_DIO(MDASM_DIO out, boolean b)
    {
        out.set(b);
        debug.print("Ausgang ist: ");
        debug.print(out.get());
        debug.println();
    }

    /**
     * Invertiert die Ausgaenge!
     */
    public static void LED_Test()
    {
    	debug.println("Test");
    	OUT_LED1.set(!OUT_LED1.get());
    	OUT_LED2.set(!OUT_LED2.get());
    	OUT_LED3.set(!OUT_LED3.get());
    	OUT_LED4.set(!OUT_LED4.get());
    	OUT_LED5.set(!OUT_LED5.get());
    }
    
    public static void LED1_TEST()
    {
    	OUT_LED1.set(!OUT_LED1.get());
    }
    
    public static void get()
    {
    	debug.print(OUT_LED1.get());
    	debug.println("");
    }

    public static void test_allOUT()
    {
        MDASM_DIO prevMDASM = null;
        for(MDASM_DIO out : IO.OUT_MDASM_DIO)
        {
            prevMDASM.set(false);
            long x = System.currentTimeMillis();
            out.set(true);
            prevMDASM = out;
            while(System.currentTimeMillis()< x+1000);
            {
                //Warte 1S
            }
        }
        MPIOSM_DIO prevMPIOSM = null;
        for(MPIOSM_DIO out : IO.OUT_MPIOSM_DIO)
        {
            prevMPIOSM.set(false);
            long x = System.currentTimeMillis();
            out.set(true);
            prevMPIOSM = out;
            while(System.currentTimeMillis()< x+1000);
            {
                //Warte 1S
            }
        }
    }

    public static void test_allIN()
    {
        for(MPIOSM_DIO in : IO.IN_MPIOSM_DIO)
        {
            debug.print("IN: ");
            debug.print(in.get());
            debug.println("\t");
        }
    }

}
