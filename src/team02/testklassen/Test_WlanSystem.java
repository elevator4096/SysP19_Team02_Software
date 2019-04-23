/*
 * @author Chris
 * @version 2019.03.27
 */
package team02.testklassen;

import exchange.WlanSystem;
import exchange.ZustandWifi;
import team02.IO;
import team02.Konstanten;
import team02.Systeme;

public class Test_WlanSystem implements IO, Konstanten, Systeme {

    public void test()
    {
        if(WlanSystem.getOwnState() == ZustandWifi.SETUP)
        {
            WlanSystem.setOwnState(ZustandWifi.TEST);
            debug.println("Test Zustand gesetzt");
        }
        if(WlanSystem.getPartnerState() == ZustandWifi.TEST)
        {
            WlanSystem.setOwnState(ZustandWifi.TEST_PASSED);
            debug.println("Test Empfangen");
        }
        if(WlanSystem.getPartnerState() == ZustandWifi.TEST_PASSED)
        {
            debug.println("Wifi Test okay");
        }
    }
}
