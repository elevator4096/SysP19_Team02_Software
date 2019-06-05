/*
 * @Author Chris
 * @version 2019.03.13
 */

package exchange;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.RN131;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import ch.ntb.inf.deep.runtime.util.CmdInt;
import team02.IO;

public class WlanSystem implements IO
{
    private static RN131 wifi;
    private static long lastTasktime;
    private static int partnerState = ZustandWifi.NO_ROUTER_CONNECTION;
    private static int cPartnerState = ZustandWifi.NO_ROUTER_CONNECTION;
    private static int ownState = ZustandWifi.SETUP;
    private static int counter =0;
    private static WlanSystem wlanSystem;
    private static int maxLoss = 1;

    /**
     * Konstruktor für die Wlan Verbindung
     *
     */
    private WlanSystem(MPIOSM_DIO reset)
    {
        try {
            SCI sci = SCI.getInstance(SCI.pSCI2);
            sci.start(115200, SCI.NO_PARITY, (short) 8);
            wifi = new RN131(sci.in, sci.out, reset);
            debug.println("Wlan erstellt!");
        }
        catch (Exception e)
        {
            debug.println("Fehler WLAN!");
        }
    }

    /**
     * Statische Methode um WlanSystem Singleton zu erstelllen
     * @return WlanSystem
     */
    public static WlanSystem getInstance(MPIOSM_DIO reset)
    {
        if(wlanSystem==null)
        {
            wlanSystem = new WlanSystem(reset);
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


    public static int getOwnState()
    {
        return ownState;
    }

    /**
     * Lade Integer aus dem Puffer
     * Achtung!!! getInt Löscht Integer aus dem RingArray
     * @return gibt Integer aus dem Puffer aus
     */
    public static int getPartnerState()
    {
        return partnerState;
    }
    
    /**
     * Setzt den Partnerzustand
     * Nur fuer Testzwecke!
     * @param state Partnerzustand
     */
    public static void setPartnerState(int state)
    {
        partnerState = state;
    }

    /**
     * Wird benoetigt um die anderen Methoden aufzurufen
     */
    public static void update()
    {
        getData();
        compensateLoss();
        sendData();
        sendHeartbeat();
    }

    /**
     * Aktuellen Zustand aus dem Array auslesen
     */
    private static void getData()
    {

        //Schleife die solange durchläuft wie States im Array sind, der letzte wird zwischengespeichert und kann über
        //die Methode getPartnerState geholt werden
        if(wifi.connected())
        {
        	if(partnerState==ZustandWifi.NO_ROUTER_CONNECTION)
        	{
        		partnerState=0;
        	}
            while(wifi.cmd.readCmd() == CmdInt.Type.Cmd)
            {
            	int state = wifi.cmd.getInt();
            	if(state >= 0)
            	{
            		//partnerState =wifi.cmd.getInt();
                    partnerState = state;
            		cPartnerState = partnerState;
            		counter = 0;
            	}
            }
        }else {
        	partnerState = ZustandWifi.NO_ROUTER_CONNECTION;
        }
    }

    /**
     * Zyklisch den Zustand senden
     */
    private static void sendData()
    {
    	if(lastTasktime +999 < Task.time())
    	{
    		wifi.cmd.writeCmd(ownState);
    		lastTasktime = Task.time();
    	}
    }

    /**
     * Heartbeat senden
     */
    private static void sendHeartbeat()
    {
        /*if(lastTasktime +499 < Task.time())
	    {
    	    wifi.cmd.writeCmd(ZustandWifi.HEARTBEAT);
	    }*/
    }

    private static void compensateLoss()
    {
        if(partnerState==ZustandWifi.NO_ROUTER_CONNECTION)
        {
            counter++;
        }
        if(counter>maxLoss)
        {
            cPartnerState = partnerState;
        }
    }

    /**
     *
     * @return der verlustbereinigte Wert
     */
    public static int getcPartnerState()
    {
        return cPartnerState;
    }
   
}
