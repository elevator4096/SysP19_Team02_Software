package team02;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import ch.ntb.inf.deep.runtime.mpc555.Timer;

public class WurfSystem extends Task{
	public static PWM pwm;
	private static Timer timer1;
	private static int zustand = 0;
	
	public void action() {
		
		if (timer1.expired())
		{
			zustand++;
			switch(zustand) {
			case 1:
				pwm.setWurfZylinderPWM(30);
				timer1.set(5000);
				break;
			case 2:
				IO.wurfMagnet.set(true);
				timer1.set(1000);
				break;
			case 3:
				IO.wurfMagnet.set(false);
				pwm.setWurfZylinderPWM(80);
				zustand = 0;
				timer1.set(10000);
				break;
			}
		}
		
	}
	
	static 
	{
		
		Task t = new WurfSystem(); // Task erzeugen
		t.period = 500; // Task-Periode festlegen
		Task.install(t); // Task installieren
		
		pwm = new PWM();
		timer1 = new Timer();
		
		timer1.set(5000);
		zustand = 0;
		
		//pwm.setChan0(10);
		pwm.setWurfZylinderPWM(80);
		//pwm.setChan0(100);
		
	}
}
