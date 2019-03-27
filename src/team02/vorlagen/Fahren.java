/**
 * @Author Chris
 * @version 2019.03.15
 */

package team02.vorlagen;

import team02.IO;

public class Fahren implements IO
{
    /**
     * Auftrag:
     * Folgende Methode müssen integriert und getestet werden:
     * -Vorwärts fahren
     * -Drehen (an ort und stelle)
     * -Drehe, bis ein DIG_IO kommt, benötigt evtl die Update methode,
     * dh, es muss zyklisch überprüft werden, ob der DIG_IO da ist.
     * -Kurve fahren(Radius, Winkelgeschwindigkeit) Skizze machen!"*"
     *
     * "*"Sehr herausforderend!
     */

    /**
     * Konstruktor
     */
    public Fahren()
    {

    }

    /**
     * Dreht einen gewissen Winkel
     * @param i RAD positiv im GUZ,negativ im UZ
     */
    public static void drehe(int i)
    {
        MOTOR_links.updateSpeed((float)i);
        MOTOR_rechts.updateSpeed((float)-i);
    }

    /**
     * Fahre Vorwärts
     * @param i Geschwindigkeit vorwärts
     */
    public static void vorwaerts(int i)
    {
        MOTOR_links.updateSpeed((float)i);
        MOTOR_links.updateSpeed((float)i);
    }

    /**
     * Update Geschwindigkeiten, wenn die Geschwindigkeit sich vom vorherigen Zyklus unterscheiden soll
     * zB wenn die Geschwindigkeit über den Mikrokontroller geändert wird
     */
    public static void update()
    {

    }
}
