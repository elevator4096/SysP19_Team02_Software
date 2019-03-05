package team02;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import ch.ntb.inf.deep.runtime.mpc555.Timer;

public class WurfSystem extends Task{
	public static PWM pwm;
	private static Timer timer1;
	private static int zustand = 0;
	private static long letzteSpannZeit=0;
	Task t = new WurfSystem(); 		 // Task erzeugen
	
	public WurfSystem()
	{
		pwm = new PWM();
		pwm.setWurfZylinderPWM(0);
		letzteSpannZeit = System.currentTimeMillis();
	}
	
	public void zylinderSpannen(int pwmValue)
	{
		pwm.setWurfZylinderPWM(pwmValue);
		letzteSpannZeit = System.currentTimeMillis();
	}
	
	public void ballWerfen()
	{
		magnetEntmagnetisieren();
		
		t.period = Konstanten.WURF_ZEIT; 
		Task.install(t); 				 // Task installieren um Magnet automatisch zu Magnetisieren
	}
	
	public boolean zylinderGespannt()
	{
		return ( System.currentTimeMillis() > (letzteSpannZeit+Konstanten.SPANN_ZEIT));
	}
	
	public void magnetEntmagnetisieren()
	{
		IO.wurfMagnet.set(true);
	}
	
	public void magnetMagnetisieren()
	{
		IO.wurfMagnet.set(false);
	}
	
	
	public void action() {
		
		magnetMagnetisieren();
		Task.remove(t);
		
		/*
		//Testprogramm
		if (timer1.expired())
		{
			zustand++;
			switch(zustand) {
			case 1:
				pwm.setWurfZylinderPWM(30);
				timer1.set(5000);
				break;
			case 2:
				if(IO.ballDetektor.get())
				{
					IO.wurfMagnet.set(true);
					timer1.set(1000);
				}
				break;
			case 3:
				IO.wurfMagnet.set(false);
				pwm.setWurfZylinderPWM(80);
				zustand = 0;
				timer1.set(10000);
				break;
			}
		}
		*/
		
	}
	
	static 
	{
		/*
		// Einfahrzeit ca 12 Sekunden
		Task t = new WurfSystem(); // Task erzeugen
		t.period = 500; // Task-Periode festlegen
		Task.install(t); // Task installieren
		
		pwm = new PWM();
		timer1 = new Timer();
		timerv
		zustand = 0;
		pwm.setWurfZylinderPWM(80);
		*/
		
	}
}
