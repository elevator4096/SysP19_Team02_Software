/*
 * @author Chris
 * @version 2019.03.27
 */
package team02.testklassen;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import exchange.WlanSystem;
import exchange.ZustandWifi;
import team02.IO;
import team02.Konstanten;
import team02.Systeme;
import team02.vorlagen.DebugSystem;
import team02.vorlagen.Fahren;

/**
 *
 */
public class Test_Task extends Task implements IO, Systeme, Konstanten
{
    static
    {
            Task task = new Test_Task();
            task.period = (int) (Konstanten.TASK_PERIOD*1000);
            Task.install(task);
    }


    public Test_Task()
    {
    	debug.println("Test Task laeuft!");
    }

    public void action()
    {
    	/*
    	OUT_LED1.set(!OUT_LED1.get());
    	debugSystem.println("------");
        gegnerSystem.test();
        debugSystem.update();
        */
    	
    	//DIG_IO.test();
    	
    	
    	//Test_LinienSensoren.test();
    	//debug.println(debug.read());
    	//Test_Sharp.test();
    	//Test_BewegungsSystem.folgeLinieRueckwaerts();
    	//Test_Fahren.vorwaerts();

    
    	wlanSystem.setOwnState(ZustandWifi.FAHREN);
    	wlanSystem.update();
    	debug.println(wlanSystem.getPartnerState());

    	//Test_Motor.setLeftSpeed0();
    	
    	//Test_Motor.setLeftSpeed50();
    	
    	//wlanSystem.setOwnState(2);
    	//wlanSystem.update();
    	//debug.println(wlanSystem.getPartnerState());
    	//debug.println("Hallo");
    	
    	//Test_Fahren.dreheGUZ(); 		//ok!
    	//Test_Fahren.dreheUZ();  		//ok!
    	//Test_Fahren.vorwaerts();  	//ok!
    	//Test_Fahren.rueckwaerts();  	//ok!
    	
    	Fahren.kurveFahren(Konstanten.WHEEL_DISTANCE, 0.1);
    	
    	/*
    	wlanSystem.setOwnState(2);
    	wlanSystem.update();
    	debug.println(wlanSystem.getPartnerState());
    	*/
    	
    	//IO.MOTOR_links.updateSpeed(0.1);
    }

}
