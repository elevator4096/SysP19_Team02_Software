/**
 * @author Chris
 * @version 2019.03.27
 */
package team02.testklassen;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import team02.IO;

public class DIG_IO implements IO
{
    /**
     * Schreibt den Aktuellen Zustand auf die Serielle Schnittstelle
     * @param in der IO der ausgelesen werden soll
     */
    public void getMPIOSM_DIO(MPIOSM_DIO in)
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
    public void setMPIOSM_DIO(MPIOSM_DIO out, boolean b)
    {
        out.set(b);
        debugSystem.print("Ausgang ist: ");
        debugSystem.print(out.get());
        debugSystem.println();
    }

    public void LED_Test()
    {

    }
}
