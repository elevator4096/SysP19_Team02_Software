/**
 * @Author Phil
 * @version 2019.03.25
 */

package team06.system;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.RN131;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.util.CmdInt;
import team06.ZustandWifi;

public class WlanSystem {
	private RN131 wifi;
	private ZustandWifi partnerState;
	private static WlanSystem wlanSystem;

	/**
	 * Konstruktor f�r die Wlan Verbindung
	 */
	private WlanSystem()

	{
		try {
			SCI sci = SCI.getInstance(SCI.pSCI2);
			sci.start(115200, SCI.NO_PARITY, (short) 8);
			wifi = new RN131(sci.in, sci.out, new MPIOSM_DIO(11, true));
		} catch (Exception e) {

		}
	}

	
	/**
	 * Instanz erzeugen
	 */
	public static WlanSystem getInstance() {
		if (wlanSystem == null) {
			wlanSystem = new WlanSystem();
		}
		return wlanSystem;
	}

	/**
	 * Teste, ob eine Verbindung besteht
	 * 
	 * @return True Connected, False Not Connected
	 */
	public boolean connected() {
		if (wifi.connected()) {
			return true;
		}
		return false;

	}

	/**
	 * Sende ein Integer
	 * 
	 * @param zustandWifi Zustand Welcher gesendet wird
	 */
	public void setOwnState(ZustandWifi zustandWifi) {
		wifi.cmd.writeCmd(zustandWifi.number);
	}

	/**
	 * Lade Integer aus dem Puffer Achtund!!! getInt Löscht Integer aus dem
	 * RingArray
	 * 
	 * @return gibt Integer aus dem Puffer aus
	 */
	public ZustandWifi getPartnerState() {
		return partnerState;
	}

	/**
	 * Schleife die solange durchl�uft, wie States im Array sind. Der letzte wird
	 * zwischengespeichert und kann �ber die Methode getPartnerstate geholt werden
	 */
	public void update() {
		if (wifi.connected()) {
			while (wifi.cmd.readCmd() == CmdInt.Type.Cmd) {
				int data = wifi.cmd.getInt();
				for (ZustandWifi zw : ZustandWifi.values()) {
					if (zw.number == data) {
						partnerState = zw;
						break;
					}
				}
			}
		}
	}
}
