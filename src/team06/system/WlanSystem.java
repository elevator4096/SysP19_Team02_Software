/**
 * @author Phil
 * @version 2019.04.01
*/

package team06.system;

import java.io.PrintStream;
import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.RN131;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import ch.ntb.inf.deep.runtime.util.CmdInt;
import team06.Variablen;

public class WlanSystem extends Task {
	private static WlanSystem task;
	private RN131 wifi;

	public WlanSystem() {
		
		System.out.println("WlanSystem_gestartet");
		
		period = Variablen.TASK_PERIOD_WlAN;

		try {
			SCI sci2 = SCI.getInstance(SCI.pSCI2);
			sci2.start(115200, SCI.NO_PARITY, (short) 8);
			wifi = new RN131(sci2.in, sci2.out, new MPIOSM_DIO(11, true));
		} catch (Exception e) {
			System.out.println("Fehler WlanSystem");
		}
	}

	/**
	 * WLan Status abfragen und Status ausgeben
	 */
	public void action() {
		System.out.print(wifi.getState().toString());
		if (wifi.connected()) {
			System.out.print("\t(connected)\t");
		} else
			System.out.print("\t(not connected)\t");
		CmdInt.Type type = wifi.cmd.readCmd();
		if (type == CmdInt.Type.Cmd) {
			System.out.print("command=");
			System.out.print(wifi.cmd.getInt());
		}
		System.out.println();
	}

	/**
	 * Int senden
	 */
	public void sendCmd(int code) {
		if (task.wifi.connected())
			task.wifi.cmd.writeCmd(code);
	}

	/**
	 * Int zurückgeben
	 */
	public int gibcmd() {
		CmdInt.Type type = wifi.cmd.readCmd();
		if (task.wifi.connected() && type == CmdInt.Type.Cmd) {
		}
		return -1;
	}

	static {
		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(19200, SCI.NO_PARITY, (short) 8);
		System.out = new PrintStream(sci1.out);
		System.err = new PrintStream(sci1.out);
		System.out.println("WlanSystem");

		try {
			task = new WlanSystem();
			Task.install(task);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}