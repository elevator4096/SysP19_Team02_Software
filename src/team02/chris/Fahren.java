/**
 * @Author Chris
 * @version 2019.03.05
 */

package team02.chris;

import team02.IO;

public class Fahren implements IO
{

    public Fahren()
    {

    }


    /**
     *
     * @param i RAD positiv im GUZ,negativ im UZ
     */
    public void drehe(int i)
    {
        MOTOR_links.updateSpeed((float)i);
        MOTOR_rechts.updateSpeed((float)-i);
    }

    public void vorwaerts(int i)
    {
        MOTOR_links.updateSpeed((float)i);
        MOTOR_links.updateSpeed((float)i);
    }

    public void update()
    {

    }
}
