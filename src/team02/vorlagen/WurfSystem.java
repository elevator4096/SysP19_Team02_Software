/*
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import team02.IO;
import team02.Konstanten;

public class WurfSystem implements IO
{


	private static long letzteSpannZeit=0;
	private static long letzteEntmagnetisierZeit=0;

	private static WurfSystem wurfSystem;

	 /*
	 * Konstruktor: Initialisiert das Wurfsystem
	 */
	private WurfSystem()
	{
		setWurfZylinderPWM(0);
		letzteSpannZeit = Task.time();
	}

	/**
	 * Methode, um WurfSystem Singleton zu erzeugen
	 * @return WurfSystem Instanz
	 */
	public static WurfSystem getInstance()
	{
		if(wurfSystem==null)
		{
			wurfSystem= new WurfSystem();
		}
		return wurfSystem;
	}

	/**
	 * Bringt den Zylinder auf Zielposition
     * @param pwmValue setzt neue pwmValue
	 * */
	public void zylinderSpannen(int pwmValue)
	{
		setWurfZylinderPWM(pwmValue);
		letzteSpannZeit = Task.time();
	}

	/**
	 * Wirft den Ball durch entmagnetisieren des HalteMagneten
	 * */
	public void ballWerfen()
	{
		magnetEntmagnetisieren();
	}
	
	/**
	 * Abfragen ob Zylinder Zielposition erreicht hat
     * @return Hat Zylinder Position erreicht
	 * */
	public boolean zylinderGespannt()
	{
		return (Task.time() > (letzteSpannZeit+Konstanten.SPANN_ZEIT));
	}
	
	/**
	 * WurfSystem update Funktion wird zyklisch aufgerufen
	 */
	public void update()
	{
		if ( Task.time() > (letzteEntmagnetisierZeit+Konstanten.WURF_ZEIT) )
		{
			magnetMagnetisieren();
		}
		if(Task.time() > letzteEntmagnetisierZeit +Konstanten.WURF_ZEIT && IO.OUT_Magnet_Wand.get())
		{
			IO.OUT_Magnet_Wand.set(false);
		}
	}
	
	/**
	 * Magnetspule kurzzeitig entmagnetisieren (durch EINSCHALTEN des EntmagnetisierElektroMagneten)
	 */
	public void magnetEntmagnetisieren()
	{
		IO.OUT_Magnet_Ausloeser.set(true);
		letzteEntmagnetisierZeit = Task.time();
	}
	
	/**
	 * Magnetspule dauerhaft magnetisieren (durch AUSSCHALTEN des EntmagnetisierElektroMagneten)
	 */
	public void magnetMagnetisieren() {
		IO.OUT_Magnet_Ausloeser.set(false);
	}

	/**
	 * faehrt den Wurfzylinder auf ZielPosition in %
	 * @param i ZielPosition in %
	 */
	private void setWurfZylinderPWM(double i)
	{
		double d;
		d = ((double)(i))/100;
		d = d*(IO.PERIOD_WurfZyl_MAX-IO.PERIOD_WurfZyl_MIN)+IO.PERIOD_WurfZyl_MIN;
		IO.PWM_WurfZylinder.update((int)d);
	}

	public void Wandauf()
	{
		IO.OUT_Magnet_Wand.set(true);
		letzteEntmagnetisierZeit = Task.time();
	}
}
