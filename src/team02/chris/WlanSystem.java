/*
 * @Author Chris
 * @version 2019.03.13
 */

package team02.chris;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.RN131;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import ch.ntb.inf.deep.runtime.util.CmdInt;
import team02.IO;
import team02.ZustandWifi;

public class WlanSystem implements IO
{
    private static RN131 wifi;
    private static long lastTasktime;
    private static int partnerState = -1;
    private static int ownState;
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
    public static boolean connected()
    {
        return wifi.connected();

    }

    /**
     * Sende ein Integer
     * @param zustandWifi Zustand Welcher gesendet wird
     */
    public static void setOwnState(int zustandWifi)
    {
    	ownState = zustandWifi;
    }

    /**
     * Lade Integer aus dem Puffer
     * Achtund!!! getInt Löscht Integer aus dem RingArray
     * @return gibt Integer aus dem Puffer aus
     */
    public static int getPartnerState()
    {
        return partnerState;
    }

    public static void update()
    {
        getData();
        sendData();
        sendHeartbeat();
    }

    private static void getData()
    {

        //Schleife die solange durchläuft wie States im Array sind, der letzte wird zwischengespeichert und kann über
        //die Methode getPartnerState geholt werden
        if(wifi.connected())
        {
        	if(partnerState==-1)
        	{
        		partnerState=0;
        	}
            while(wifi.cmd.readCmd() == CmdInt.Type.Cmd)
            {
            	int state = wifi.cmd.getInt();
            	if(state >= 0)
            	{
            		partnerState =wifi.cmd.getInt();
            	}
            }
        }else {
        	partnerState = -1;
        }
    }
    
    private static void sendData()
    {
    	if(lastTasktime +1000 < Task.time())
    	{
    		wifi.cmd.writeCmd(ownState);
    		lastTasktime = Task.time();
    	}
    }
    
    private static void sendHeartbeat()
    {
    	wifi.cmd.writeCmd(-2);
    }
  
}
