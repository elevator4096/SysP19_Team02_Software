/**
 * @Author Chris
 * @version 2019.03.13
 */

package team02.chris;

import team02.IO;

public class Fahren implements IO
{
    /**
     * Auftrag:
     * Folgende Methode müssen integriert und getestet werden:
     * -Vorwärts fahren
     * -Drehen (an ort und stelle)
     * -Kurve fahren(Radius, Winkelgeschwindigkeit) Skizze machen!"*"
     *
     * "*"Sehr herausforderend!
     */


    public Fahren()
    {

    }

    /**
     *
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
     * Update Geschwindigkeiten
     */
    public static void update()
    {

    }
}
