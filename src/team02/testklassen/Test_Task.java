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
import team02.vorlagen.WurfSystem;
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
    	wlanSystem.setOwnState(ZustandWifi.FAHREN);
    	wlanSystem.update();
    	debug.println(wlanSystem.getPartnerState());
    	*/
    	
    	//Fahren.kurveFahren(0.100, Konstanten.DRIVING_SPEED);
    	//wurfSystem.zylinderSpannen(50);
    	
    	//Test_Wurfsystem.test();
    	Test_BewegungsSystem.fahreVorwaerts1m();
    }
    
    /*old Tests
     	//Test_Fahren.dreheGUZ(); 		//ok!
    	//Test_Fahren.dreheUZ();  		//ok!
    	//Test_Fahren.vorwaerts();  	//ok!
    	//Test_Fahren.rueckwaerts();  	//ok!

     	Fahren.kurveFahren(Konstanten.WHEEL_DISTANCE, 0.1);

     	
    	wlanSystem.setOwnState(2);
    	wlanSystem.update();
    	debug.println(wlanSystem.getPartnerState());
    	*/

     	//IO.MOTOR_links.updateSpeed(0.1);		
    	
     

}
