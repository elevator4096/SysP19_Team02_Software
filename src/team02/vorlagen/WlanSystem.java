/**
 * @Author Chris
 * @version 2019.03.13
 */

package team02.vorlagen;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.RN131;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.util.CmdInt;

public class WlanSystem
{
    private RN131 wifi;

    /**
     * Konstruktor für die Wlan Verbindung
     *
     * @throws Exception
     */
    public WlanSystem() throws Exception
    {
        SCI sci = SCI.getInstance(SCI.pSCI2);
        sci.start(115200, SCI.NO_PARITY, (short)8);
        wifi = new RN131(sci.in , sci.out, new MPIOSM_DIO(11, true));
    }

    /**
     * Teste, ob eine Verbindung besteht
     * @return True Connected, False Not Connected
     */
    public boolean connected()
    {
        if(wifi.connected())
        {
            return true;
        }
        return false;

    }

    /**
     * Sende ein Integer
     * @param i Integer welcher gesendet wird
     */
    public void send(int i)
    {
        if(wifi.connected())
            wifi.cmd.writeCmd(i);
    }

    /**
     * Lade Integer aus dem Puffer
     * Achtund!!! getInt Löscht Integer aus dem RingArray
     * @return gibt Integer aus dem Puffer aus
     */
    public int get()
    {
        if(wifi.connected())
        {
            if(wifi.cmd.readCmd() == CmdInt.Type.Cmd)
                return wifi.cmd.getInt();
        }
        return -1;
    }

    public void update()
    {

    }
}
