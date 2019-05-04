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
import team02.vorlagen.BewegungsSystem;
import team02.vorlagen.DebugSystem;
import team02.vorlagen.Fahren;

/**
 *
 */
public class Test_Task extends Task implements IO, Systeme, Konstanten
{
	public static long x =0;
	
    static
    {
            Task task = new Test_Task();
            task.period = (int) (Konstanten.TASK_PERIOD*1000);
            Task.install(task);
    }


    public Test_Task()
    {
    	debug.println("Test Task laeuft!");
    	//Test_BewegungsSystem.fahreVorwaertsMeter(1);
    	//Test_BewegungsSystem.fahreVorwaertsMeter(1);
    	//bewegungsSystem.dreheUngenauGUZ(0.5*Math.PI);
    	//bewegungsSystem.dreheUngenauGUZ(0.6*Math.PI);
    	//bewegungsSystem.dreheUngenauUZ(0.5*Math.PI);
    	//bewegungsSystem.fahreFreiBisDistanz(false, 0.1);
    }

    public void action()
    {
    	
    	//ALLE Systeme updaten(sollte nur zu Testzwecken entfernt werden)
    	IO.debug.update();
    	Systeme.gegnerSystem.update();
    	Systeme.wurfSystem.update();
    	Systeme.bewegungsSystem.update();
    	Systeme.wlanSystem.update();
    	
    
    	//IO.MOTOR_links.updateSpeed(0.1);
    	//IO.MOTOR_rechts.updateSpeed(0.1);
    	
   
    	
    	//Systeme.wurfSystem.zylinderSpannen(90);
    	//Systeme.wurfSystem.ballWerfen();
        
    
    	
    	
    	/*
    	wlanSystem.setOwnState(ZustandWifi.FAHREN);
    	wlanSystem.update();
    	debug.println(wlanSystem.getPartnerState());
    	resetLED();
    	switch(wlanSystem.getPartnerState())
    	{
    	case -1:{
    		//Kein Host
    		OUT_LED1.set(true);
    		break;
    		}
    	case 0:{
    		//Verbindung zu Router
    		OUT_LED2.set(true);
    		break;
    	}
    	case 1:{
    		//Befehl empfangen
    		OUT_LED3.set(true);
    		break;
    	}
    	case 2:{
    		//Befehl empfangen
    		OUT_LED4.set(true);
    	}
    	default:
    	{
    		//Alle anderen Befehle
    		OUT_LED5.set(true);
    	}
    	}
    	
    	//Fahren.kurveFahren(0.100, Konstanten.DRIVING_SPEED);
    	//wurfSystem.zylinderSpannen(50);
    	//Test_Wurfsystem.test();
    	//Test_Fahren.rueckwaerts();
    	
    	//Systeme.bewegungsSystem.update();
    	*/
    	//Test_Motor.printDistanzen();
    	
    	
    	//Demo.run();
    	
    	Test_SensorenAktoren.testAblaufDemo();
    	
    	
    }
    
    private static void resetLED()
    {
    	OUT_LED1.set(false);
    	OUT_LED2.set(false);
    	OUT_LED3.set(false);
    	OUT_LED4.set(false);
    	OUT_LED5.set(false);
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
    	
    	/*
    	Fahren.update();
    	Test_Fahren.vorwaerts();
    	Test_Motor.printDistanzen();
    	 */
    	
     

}
