package team02.testklassen;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import team02.IO;

public class DIG_IO implements IO
{
    public void getMPIOSM_DIO(MPIOSM_DIO in)
    {
        debugSystem.print("Eingang ist: ");
        debugSystem.print(in.get());
        debugSystem.println();
    }


    public void setMPIOSM_DIO(MPIOSM_DIO out, boolean b)
    {
        out.set(b);
        debugSystem.print("Ausgang ist: ");
        debugSystem.print(out.get());
        debugSystem.println();
    }
}
