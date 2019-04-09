/*
 * @Author Chris
 * @version 2019.03.13
 */

package team02.chris;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.RN131;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.util.CmdInt;
import team02.IO;
import team02.ZustandWifi;

public class WlanSystem implements IO
{
    private RN131 wifi;
    private int partnerState;
    private static WlanSystem wlanSystem;

    /**
     * Konstruktor für die Wlan Verbindung
     *
     */
    private WlanSystem()
    {
        try {
            SCI sci = SCI.getInstance(SCI.pSCI2);
            sci.start(115200, SCI.NO_PARITY, (short) 8);
            wifi = new RN131(sci.in, sci.out, new MPIOSM_DIO(11, true));
        }
        catch (Exception e)
        {
            debug.println("Fehler WLAN!");
        }
    }

    public static WlanSystem getInstance()
    {
        if(wlanSystem==null)
        {
            wlanSystem = new WlanSystem();
        }
        return wlanSystem;
    }

    /**
     * Teste, ob eine Verbindung besteht
     * @return True Connected, False Not Connected
     */
    public boolean connected()
    {
        return wifi.connected();

    }

    /**
     * Sende ein Integer
     * @param zustandWifi Zustand Welcher gesendet wird
     */
    public void setOwnState(int zustandWifi)
    {
        wifi.cmd.writeCmd(zustandWifi);
    }

    /**
     * Lade Integer aus dem Puffer
     * Achtund!!! getInt Löscht Integer aus dem RingArray
     * @return gibt Integer aus dem Puffer aus
     */
    public int getPartnerState()
    {
        return partnerState;
    }

    public void update()
    {

        getData();
    }

    private void getData()
    {

        //Schleife die solange durchläuft wie States im Array sind, der letzte wird zwischengespeichert und kann über
        //die Methode getPartnerState geholt werden
        if(wifi.connected())
        {
            while(wifi.cmd.readCmd() == CmdInt.Type.Cmd)
            {
                partnerState =wifi.cmd.getInt();
            }
        }
    }
}
