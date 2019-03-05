package team02;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import ch.ntb.inf.deep.runtime.mpc555.Timer;

public class WurfSystem extends Task{
	public static PWM pwm;
	private static Timer timer1;
	private static int zustand = 0;
	private static long letzteSpannZeit=0;
	private static long letzteEntmagnetisierZeit=0;
	Task t = new WurfSystem(); 		 // Task erzeugen
	
	//Konstruktor: Initialisiert das Wurfsystem
	public WurfSystem()
	{
		pwm = new PWM();
		pwm.setWurfZylinderPWM(0);
		letzteSpannZeit = System.currentTimeMillis();
	}
	
	// Bringt den Zylinder auf Zielposition
	public void zylinderSpannen(int pwmValue)
	{
		pwm.setWurfZylinderPWM(pwmValue);
		letzteSpannZeit = System.currentTimeMillis();
	}
	
	// Wirft den Ball durch entmagnetisieren des HalteMagneten
	public void ballWerfen()
	{
		magnetEntmagnetisieren();
	}
	
	//Abfragen ob Zylinder Zielposition erreicht hat
	public boolean zylinderGespannt()
	{
		return ( System.currentTimeMillis() > (letzteSpannZeit+Konstanten.SPANN_ZEIT));
	}
	
	//WurfSystem update Funktion wird zyklisch aufgerufen
	public void update()
	{
		if ( System.currentTimeMillis() > (letzteEntmagnetisierZeit+Konstanten.WURF_ZEIT) )
		{
			magnetMagnetisieren();
		}
	}
	
	// Magnetspule kurzzeitig entmagnetisieren (durch EINSCHALTEN des EntmagnetisierElektroMagneten)
	private void magnetEntmagnetisieren()
	{
		IO.wurfMagnet.set(true);
		letzteEntmagnetisierZeit = System.currentTimeMillis();
	}
	
	// Magnetspule dauerhaft magnetisieren (durch AUSSCHALTEN des EntmagnetisierElektroMagneten)
	private void magnetMagnetisieren() {
		IO.wurfMagnet.set(false);
	}
	
	
	public void action() {
		
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
