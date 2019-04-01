/*
 * @Author Chris
 * @version 2019.03.15
 */

package team02.vorlagen;

import team02.IO;
import team02.Konstanten;

public class Fahren implements IO
{
    /**
     * Auftrag:
     * Folgende Methode müssen integriert und getestet werden:
     * -Vorwärts fahren
     * -Drehen (an ort und stelle)
     * -Kurve fahren(Radius, BahnGeschwindigkeit) Skizze machen!"*"
     *
     * "*"Sehr herausfordernd!
     */

    /**
     * Konstruktor
     */
    public Fahren()
    {

    }

    /**
     * Dreht einen gewissen Winkel
     * @param turningSpeed RAD positiv im GUZ,negativ im UZ
     */
    public static void drehe(double turningSpeed)
    {
        MOTOR_links .updateSpeed( turningSpeed);
        MOTOR_rechts.updateSpeed(-turningSpeed);
    }

    /**
     * Fahre geradeaus
     * @param speed Geschwindigkeit in m/s
     */
    public static void geradeaus(double speed)
    {
        MOTOR_links .updateSpeed(speed);
        MOTOR_rechts.updateSpeed(speed);
    }

    /**
     * Fahre Kurve
     * @param radius	 (positiv = GUZ, negativ = UZ)
     * @param bahnGeschw (positiv = vorwaerts, negativ = rueckwaerts)
     */
    public static void kurveFahren(double radius, double bahnGeschw)
    {
    	if (Math.abs(radius)<0.001)
    	{
    		debugSystem.println("Kurve mit Radius kleiner 0.001m nicht erlaubt!");
    		return;
    	}
    	
    	double w = bahnGeschw/radius;
    	
    	double rRechts = Math.abs( radius + Konstanten.WHEEL_DISTANCE/2.0);
    	double rLinks  = Math.abs( radius - Konstanten.WHEEL_DISTANCE/2.0);
    	
    	double speedRechts = w*rRechts;
    	double speedLinks  = w*rLinks ;
    	
        MOTOR_links .updateSpeed(speedLinks);
        MOTOR_rechts.updateSpeed(speedRechts);
    }
    
    /**leichte Kurve zur Richtungskorrektur des Roboters
     * 
     * @param fahrtRichtung (vorwaerts = True, rueckwaerts = False)
     * @param drehSinn 		(GUZ 	   = True, UZ 		   = False)
     */
    public static void KorrekturKurve(boolean fahrtRichtung, boolean drehSinn)
    {
    		kurveFahren( Konstanten.LINE_FOLLOWER_RADIUS*(drehSinn? 1:-1), Konstanten.DRIVING_SPEED*(fahrtRichtung? 1:-1) );
    }
    
    

    /**
     * Update Geschwindigkeiten, wenn die Geschwindigkeit sich vom vorherigen Zyklus unterscheiden soll
     * zB wenn die Geschwindigkeit über den Mikrokontroller geändert wird
     */
    public static void update()
    {

    }
}
