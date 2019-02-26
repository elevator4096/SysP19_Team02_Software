package team02;

import ch.ntb.inf.deep.runtime.ppc32.Task;

public class WurfSystem extends Task{
	public static PWM pwm;
	public static int counter;
	
	
	public void action() {
		
		counter++;
		
		if (counter>10) {
			counter = 0;
		}
		
		//pwm.setChan0(counter*10);
		
	}
	
	static 
	{
		
		Task t = new WurfSystem(); // Task erzeugen
		t.period = 500; // Task-Periode festlegen
		Task.install(t); // Task installieren
		
		pwm = new PWM();
		counter = 0;
		
		//pwm.setChan0(10);
		pwm.setChan0(50);
		//pwm.setChan0(100);
	}
}
