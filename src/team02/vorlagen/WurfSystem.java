/**
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

import team02.IO;
import team02.Konstanten;

public class WurfSystem
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
		letzteSpannZeit = System.currentTimeMillis();
	}


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
		letzteSpannZeit = System.currentTimeMillis();
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
		return ( System.currentTimeMillis() > (letzteSpannZeit+Konstanten.SPANN_ZEIT));
	}
	
	/**
	 * WurfSystem update Funktion wird zyklisch aufgerufen
	 */
	public void update()
	{
		if ( System.currentTimeMillis() > (letzteEntmagnetisierZeit+Konstanten.WURF_ZEIT) )
		{
			magnetMagnetisieren();
		}
	}
	
	/**
	 * Magnetspule kurzzeitig entmagnetisieren (durch EINSCHALTEN des EntmagnetisierElektroMagneten)
	 */
	private void magnetEntmagnetisieren()
	{
		IO.OUT_Magnet_Ausloeser.set(true);
		letzteEntmagnetisierZeit = System.currentTimeMillis();
	}
	
	/**
	 * Magnetspule dauerhaft magnetisieren (durch AUSSCHALTEN des EntmagnetisierElektroMagneten)
	 */
	private void magnetMagnetisieren() {
		IO.OUT_Magnet_Ausloeser.set(false);
	}
	
	private void setWurfZylinderPWM(int i)
	{
		double d;
		d = ((double)(i))/100;
		d = d*IO.PERIOD_WurfZyl;
		IO.PWM_WurfZylinder.update((int)d);
	}
}
