package team02.vorlagen;

import team02.*;

public class Motoren {
	
	public static PWM pwm = new PWM();
	
	static {
		
		/*
		Task t = new MotorSteuerung(); // Task erzeugen
		t.period = 50; // Task-Periode festlegen
		Task.install(t); // Task installieren
		
		pwm = new PWM();
		timer1 = new Timer();
		
		timer1.set(5000);
		zustand = 0;
		*/
		//anhalten();
	}
	
	public void anhalten()
	{
		pwm.setMotorLinksPWM(50);pwm.setMotorRechtsPWM(50);
	}
	
	public void vorwarts()
	{
		pwm.setMotorLinksPWM(55);pwm.setMotorRechtsPWM(55);
	}
	
	public void rechtsKurve()
	{
		pwm.setMotorLinksPWM(55);pwm.setMotorRechtsPWM(52);
	}
	
	public void linksKurve()
	{
		pwm.setMotorLinksPWM(52);pwm.setMotorRechtsPWM(55);
	}
	
	

}
