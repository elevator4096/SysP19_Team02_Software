/*
 * @Author Chris
 * @version 2019.03.15
 */

package team02.vorlagen;

import team02.IO;
import team02.Konstanten;

public class Fahren implements IO, Konstanten
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
     * Drehe an Ort und Stelle mit einer gewissen Geschwindigkeit
     * @param turningSpeed m/s positiv im GUZ,negativ im UZ
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
     * @param radius	 (positiv = UZ, negativ = GUZ)
     * @param v (positiv = vorwaerts, negativ = rueckwaerts)
     */
    public static void kurveFahren(double radius, double v)
    {
        double vr;      //Rechte Bahngeschwindigkeit
    	double vl;      //Linke Bahngeschwindigkeit
        double a = Konstanten.WHEEL_DISTANCE/2;


        if(Math.abs(radius) <= 0.0001)
        {
            vr = v;
            vl = -v;
        }else {
            vr = (v * (radius + a)) / radius;
            vl = (v * (radius - a)) / radius;
        }

        if(vr>Konstanten.MAX_SPEED)
        {
            vr = Konstanten.MAX_SPEED;
            vl = (vr*(radius-2*a))/radius;
        }

        if(vr<-Konstanten.MAX_SPEED)
        {
            vr = -Konstanten.MAX_SPEED;
            vl = (vr*(radius-2*a))/radius;
        }

        if(vl>Konstanten.MAX_SPEED)
        {
            vl = Konstanten.MAX_SPEED;
            vr = (vr*(radius+2*a))/radius;
        }

        if(vl<-Konstanten.MAX_SPEED)
        {
            vl = -Konstanten.MAX_SPEED;
            vr = (vr*(radius+2*a))/radius;
        }


        MOTOR_links.updateSpeed(vl);
        MOTOR_rechts.updateSpeed(vr);
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
    
    public static void stop()
    {
        MOTOR_links .updateSpeed(0);
        MOTOR_rechts.updateSpeed(0);
    }
    

    /**
     * Update Geschwindigkeiten, wenn die Geschwindigkeit sich vom vorherigen Zyklus unterscheiden soll
     * zB wenn die Geschwindigkeit über den Mikrokontroller geändert wird
     */
    public static void update()
    {

    }
}
