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

    private static double phi =0;
    private static long impRe, impLi;

    private static Fahren fahren;

    /**
     * Konstruktor
     */
    private Fahren()
    {

    }

    public static Fahren getInstance()
    {
        if(fahren == null)
        {
            fahren = new Fahren();
        }
        return fahren;
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
    * @param radius	 (positiv = GUZ, negativ = UZ)
    * @param bahnGeschw (positiv = vorwaerts, negativ = rueckwaerts)
    */
   public static void kurveFahren(double radius, double bahnGeschw)
   {
	   double a = WHEEL_DISTANCE/2.0;
	   	if (Math.abs(radius)<0.001)
	   	{
	   		//DebugSystem.println("Kurve mit Radius kleiner 0.001m nicht erlaubt!");
	   		return;
	   	}
	   	
	   	double w = bahnGeschw/Math.abs(radius);
	   	
	   	double rRechts = Math.abs( radius + a*signum(w));
	   	double rLinks  = Math.abs( radius - a*signum(w));
	   	
	   	double vr = w*rRechts;
	   	double vl  = w*rLinks ;

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
     * Berechne den aktuellen Winkel gegenüber der x-Achse
     */
    public static void calcphi()
    {

        long deltali = impLi - MOTOR_links.getEncPos();
        long deltare = impRe - MOTOR_rechts.getEncPos();
        double phili = (Konstanten.WHEEL_DIAMETER * Math.PI * deltali) / (Konstanten.GEAR_RATIO * Konstanten.TICKS_PER_ROUND - (Konstanten.WHEEL_DISTANCE / 2));
        double phire = (Konstanten.WHEEL_DIAMETER * Math.PI * deltare) / (Konstanten.GEAR_RATIO * Konstanten.TICKS_PER_ROUND - (Konstanten.WHEEL_DISTANCE / 2));

        double deltaphi = phili - phire;

        phi += deltaphi;
    }

    public static void set0()
    {
        MOTOR_links.setEncPos(0);
        MOTOR_rechts.setEncPos(0);
        impLi = 0;
        impRe = 0;
    }
    
    private static double signum(double d)
    {
    	return (d>=0)? 1:-1;
    }
    

    /**
     * Update Geschwindigkeiten, wenn die Geschwindigkeit sich vom vorherigen Zyklus unterscheiden soll
     * zB wenn die Geschwindigkeit über den Mikrokontroller geändert wird
     */
    public static void update()
    {

    }
}
