package team02.chris;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.RN131;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.util.CmdInt;

public class WlanSystem
{
    private RN131 wifi;


    public WlanSystem() throws Exception
    {
        SCI sci = SCI.getInstance(SCI.pSCI2);
        sci.start(115200, SCI.NO_PARITY, (short)8);
        wifi = new RN131(sci.in , sci.out, new MPIOSM_DIO(11, true));
    }

    public boolean connected()
    {
        if(wifi.connected())
        {
            return true;
        }
        return false;

    }

    public void send(int i)
    {
        if(wifi.connected())
            wifi.cmd.writeCmd(i);
    }

    public int get()
    {
        if(wifi.connected())
        {
            if(wifi.cmd.readCmd() == CmdInt.Type.Cmd)
                return wifi.cmd.getInt();
        }
        return -1;
    }
}
