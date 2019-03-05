package team02;

import ch.ntb.inf.deep.runtime.mpc555.Timer;
import ch.ntb.inf.deep.runtime.ppc32.Task;


public class MotorSteuerung extends Task {
	public static PWM pwm;
	private static Timer timer1;
	private static int zustand = 0;
	
	
	public void action() {
		
		
		if (timer1.expired())
		{
			zustand++;
			switch(zustand) {
			case 1:
				pwm.setMotorLinksPWM(60);pwm.setMotorRechtsPWM(60);
				timer1.set(1000);
				break;
			case 2:
				pwm.setMotorLinksPWM(100);pwm.setMotorRechtsPWM(100);
				timer1.set(1000);
				break;
			case 3:
				pwm.setMotorLinksPWM(40);pwm.setMotorRechtsPWM(60);
				zustand = 0;
				timer1.set(5000);
				break;
			}
		}
		
		
	}
	
	
	static {
		
		Task t = new MotorSteuerung(); // Task erzeugen
		t.period = 50; // Task-Periode festlegen
		Task.install(t); // Task installieren
		
		pwm = new PWM();
		timer1 = new Timer();
		
		timer1.set(5000);
		zustand = 0;
		
		pwm.setMotorLinksPWM(50);
		pwm.setMotorRechtsPWM(50);
	}
	
	

}
