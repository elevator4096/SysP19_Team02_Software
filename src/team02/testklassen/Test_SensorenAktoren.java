package team02.testklassen;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import team02.IO;
import team02.Konstanten;
import team02.Systeme;
import team02.vorlagen.Fahren;

/*
//Sensoren
IN_Taster_1
IN_Taster_2
IN_Laser_1
IN_Laser_2
IN_Taster_konf1
IN_Taster_konf2

Liniensensoren

AN_Sharp1
AN_Sharp2
AN_Sharp3

FQD_Links
FQD_Rechts

*/

/*
//Aktoren
OUT_Reset_Wlan
OUT_Magnet_Ausloeser
OUT_Magnet_Wand

WurfZylinder //WurfZylinder Spannen

OUT_FG_Speed
OUT_DIR_Speed1 //MOTOR_links
OUT_DIR_Speed2 //MOTOR_rechts

OUT_LED1
OUT_LED2
OUT_LED3
OUT_LED4
OUT_LED5

 */



public class Test_SensorenAktoren  implements IO, Konstanten, Systeme {
	public static long lastTime = Task.time();
	public static int counter=0;
	public static boolean richtung = true;
		
	public static void testSensoren()
	{
		debug.print("Taster_1 ");debug.print(IN_Taster_1.get());
		debug.print("\t Taster_2 ");debug.print(IN_Taster_2.get());
		debug.print("\t Laser_1 ");debug.print(IN_Laser_1.get());
		debug.print("\t Laser_2 ");debug.print(IN_Laser_2.get());
		debug.print("\t Taster_konf1 ");debug.print(IN_Taster_konf1.get());
		debug.print("\t Taster_konf2 ");debug.print(IN_Taster_konf2.get());
		debug.println("");
		
		debug.println("LinienSensoren ");
		for(int i = 0; i < 8; i++) {
			int y = IO.HLC_1395_PULSED.read(i);
			System.out.print(y);
			System.out.print('\t');
		}	
		debug.println("");
		
		debug.print("Sharp_1 ")   ;debug.print(AN_Sharp1.getDistanz());
		debug.print("\t Sharp_2 ");debug.print(AN_Sharp2.getDistanz());
		debug.print("\t Sharp_3 ");debug.print(AN_Sharp3.getDistanz());
		debug.println("");
		
		debug.print("EncoderL ")   ;debug.print(IO.MOTOR_links.getDistanz());
		debug.print("\t EncoderR ");debug.print(IO.MOTOR_rechts.getDistanz());
		debug.println("");
				
	}
	/*
	//Aktoren
	OUT_Magnet_Ausloeser
	OUT_Magnet_Wand

	WurfZylinder //WurfZylinder Spannen

	OUT_FG_Speed
	OUT_DIR_Speed1 //MOTOR_links
	OUT_DIR_Speed2 //MOTOR_rechts

	OUT_LED1
	OUT_LED2
	OUT_LED3
	OUT_LED4
	OUT_LED5

	 */
	
	public static void testAktoren()
	{
		
		if (Task.time() > lastTime+2000)
		{
			counter++;
			switch(counter)
			{
			case 1:
				debug.println("Warten");
				break;
			case 2:
				debug.println("Magnet Wand");
				IO.OUT_Magnet_Wand.set(true);
				break;
			case 3:
				IO.OUT_Magnet_Wand.set(false);
				
				debug.println("Magnet Ausloeser");
				Systeme.wurfSystem.ballWerfen();
				break;
			case 4:
				debug.println("Zylinder Spannen");
				Systeme.wurfSystem.zylinderSpannen(50);
				break;
			case 5:
				Systeme.wurfSystem.zylinderSpannen(80);
				
				debug.println("Fahren");
				Systeme.bewegungsSystem.fahreFreiBisDistanz(false, 0.5);
				break;
			case 6:
				debug.println("LEDS EIN");
				OUT_LED1.set(true);
				OUT_LED2.set(true);
				OUT_LED3.set(true);
				OUT_LED4.set(true);
				OUT_LED5.set(true);				
				break;
			
			case 7:
				debug.println("LEDS AUS");
				OUT_LED1.set(false);
				OUT_LED2.set(false);
				OUT_LED3.set(false);
				OUT_LED4.set(false);
				OUT_LED5.set(false);				
				break;
				
			case 8:
				debug.println("WIEDERHOLE");
				counter = 0;			
				break;
			}	
			
			lastTime = Task.time();
		}
		
		
		
	}

}
