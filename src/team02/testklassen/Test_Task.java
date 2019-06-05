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
import team02.vorlagen.Pos_Wechsel_V2;

/**
 *
 */
public class Test_Task extends Task implements IO, Systeme, Konstanten
{
	public static long x =0;
	
	private static int delta =0;
	
	private int counter =0;
	
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
    	//IO.OUT_Magnet_Wand.set(false);
    	
    	//bewegungsSystem.folgeLinieBisWandRueckwaerts();
    }

    public void action()
    {
    	//counter++;
    	IO.debug.update();
    	
    	Systeme.gegnerSystem.update();
    	Systeme.wurfSystem.update();
    	Systeme.bewegungsSystem.update();
    	Systeme.wlanSystem.update();
    	Pos_Wechsel_V2.update();
    	
    	//IO.MOTOR_links.update();
    	//IO.MOTOR_rechts.update();
    	//debug.println(IO.OUT_Magnet_Ausloeser.get());
    	Demo.run();
    	//debug.println(IO.AN_Sharp3.getDistanz());debug.print(" "); debug.println(Systeme.gegnerSystem.warGegnerRechts());
    	
    	/*
    	debug.print("Motor Distanzen: ");debug.print(IO.MOTOR_links.getDistanz());debug.print(" ");debug.println(IO.MOTOR_rechts.getDistanz());
    	*/
    	//IO.MOTOR_rechts.updateSpeed(0.1);
    	//IO.MOTOR_rechts.update();
    	//debug.print("Motor Distanzen: ");debug.print(IO.MOTOR_links.getDistanz());debug.print(" ");debug.println(IO.MOTOR_rechts.getDistanz());
    	//debug.println(Fahren.getPhi()*180/3.1415926);
    	//debug.println(wlanSystem.getcPartnerState());
    	
    	
    	if(counter % 10 == 0)
    	{
    		//debug.print("Helligkeit: ");debug.print(IO.LINE_Sensor_Hinten.getHelligkeitLinks());debug.print(" ");debug.println(IO.LINE_Sensor_Hinten.getHelligkeitRechts());
    		//debug.print("Linienpos: ");debug.print(IO.LINE_Sensor_Hinten.istLinieLinks());debug.print(" ");debug.print(IO.LINE_Sensor_Hinten.istLinieRechts());debug.print(" ");debug.println(IO.LINE_Sensor_Hinten.istLinieVorne());
    		//Test_Sharp.test();
    		/*
    		debug.print("Hinten: ");
    		debug.print(Systeme.gegnerSystem.istGegnerHinten());
    		debug.print("   Links: ");
    		debug.print(Systeme.gegnerSystem.istGegnerLinks());
    		debug.print("   Rechts: ");
    		debug.println(Systeme.gegnerSystem.istGegnerRechts());
    		*/
    	}
    	
    	//Fahren.kurveFahren(-0.2, -Konstanten.DRIVING_SPEED);
    	
    	//Fahren.drehe(0.1);
    	//Fahren.kurveFahren( Konstanten.LINE_FOLLOWER_RADIUS, Konstanten.DRIVING_SPEED );
    	//Fahren.update();

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
