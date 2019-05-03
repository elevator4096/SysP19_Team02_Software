/*
 * @author Chris
 * @version 2019.03.27
 */
package team02.testklassen;

import ch.ntb.inf.deep.runtime.mpc555.driver.MDASM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.ppc32.Task;
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
    
    public static void LED2_TEST()
    {
    	OUT_LED2.set(!OUT_LED2.get());
    }
    
    public static void LED3_TEST()
    {
    	OUT_LED3.set(!OUT_LED3.get());
    }
    
    public static void LED4_TEST()
    {
    	OUT_LED4.set(!OUT_LED4.get());
    }
    
    public static void LED5_TEST()
    {
    	OUT_LED5.set(!OUT_LED5.get());
    }
    
    public static void MAG_TEST_WAND()
    {
    	OUT_Magnet_Wand.set(OUT_Magnet_Wand.get());
    }
    
    public static void MAG_TEST_AUS()
    {
    	OUT_Magnet_Ausloeser.set(OUT_Magnet_Ausloeser.get());
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
            long x = Task.time();
            out.set(true);
            prevMDASM = out;
            while(Task.time()< x+1000);
            {
                //Warte 1S
            }
        }
        MPIOSM_DIO prevMPIOSM = null;
        for(MPIOSM_DIO out : IO.OUT_MPIOSM_DIO)
        {
            prevMPIOSM.set(false);
            long x = Task.time();
            out.set(true);
            prevMPIOSM = out;
            while(Task.time()< x+1000);
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

    public static void Test_Sharp()
    {
    	debug.println("--------------------");
    	debug.println(AN_Sharp1.getDistanz());
    	debug.println(AN_Sharp2.getDistanz());
    	debug.println(AN_Sharp3.getDistanz());
    }
    
    public static void Test_Tast()
    {
    	debug.println("---------");
    	debug.print("Taster 1: ");
    	debug.println(IN_Taster_1.get());
    	debug.print("Taster 2: ");
    	debug.println(IN_Taster_2.get());
    	debug.print("Laser 1: ");
    	debug.println(IN_Laser_1.get());
    	debug.print("Laser 2: ");
    	debug.println(IN_Laser_2.get());
    	debug.print("Taster 1 konf: ");
    	debug.println(IN_Taster_konf1.get());
    	debug.print("Taster 2 konf: ");
    	debug.println(IN_Taster_konf2.get());
    }
}
