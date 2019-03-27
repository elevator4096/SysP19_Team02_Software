/**
 * @Author Chris
 * @version 2019.03.13
 */

package team02.chris;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.RN131;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.util.CmdInt;
import team02.ZustandWifi;

public class WlanSystem
{
    private RN131 wifi;
    private ZustandWifi partnerState;
    private static WlanSystem wlanSystem;

    /**
     * Konstruktor für die Wlan Verbindung
     *
     * @throws Exception
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
    public void setOwnState(ZustandWifi zustandWifi)
    {
        wifi.cmd.writeCmd(zustandWifi.number);
    }

    /**
     * Lade Integer aus dem Puffer
     * Achtund!!! getInt Löscht Integer aus dem RingArray
     * @return gibt Integer aus dem Puffer aus
     */
    public ZustandWifi getPartnerState()
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
                int data =wifi.cmd.getInt();
                for(ZustandWifi zw: ZustandWifi.values())
                {
                    if(zw.number == data)
                    {
                        partnerState = zw;
                        break;
                    }
                }
            }
        }
    }
}
