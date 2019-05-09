package team06.phil;

import java.io.PrintStream;
import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.RN131;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import ch.ntb.inf.deep.runtime.util.CmdInt;

public class WLan_2 extends Task {

	private static WLan_2 task;
	private RN131 wifi;

	public WLan_2() throws Exception {
		period = 500;
		SCI sci = SCI.getInstance(SCI.pSCI2);
		sci.start(115200, SCI.NO_PARITY, (short) 8);
		wifi = new RN131(sci.in, sci.out, new MPIOSM_DIO(11, true));
	}

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
		} else if (type == CmdInt.Type.Code) {
			System.out.print("code=");
			System.out.print(wifi.cmd.getInt());
		} else if (type == CmdInt.Type.Unknown) {
			System.out.print("unknown(");
			System.out.print(wifi.cmd.getHeader());
			System.out.print(")=");
			System.out.print(wifi.cmd.getInt());
		}
		System.out.println();
	}

	public static void reset() {
		task.wifi.reset();
	}

	public static void sendCmd() {
		if (task.wifi.connected())
			task.wifi.cmd.writeCmd(123);
	}

	public static void sendCode() {
		if (task.wifi.connected())
			task.wifi.cmd.writeCmd(CmdInt.Type.Code, 456);
	}

	public static void sendOther() {
		if (task.wifi.connected())
			task.wifi.cmd.writeCmd((byte) 0xab, 789);
	}

	static {
		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(19200, SCI.NO_PARITY, (short) 8);
		System.out = new PrintStream(sci1.out);
		System.err = new PrintStream(sci1.out);
		System.out.println("WifiDemo");
		try {
			task = new WLan_2();
			Task.install(task);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}