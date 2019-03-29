/*
 * @author Chris
 * @version 2019.03.27
 */
package team02.testklassen;

import team02.IO;

public class Test_Motor implements IO
{
    public static void setLeftSpeed(int speed)
    {
    	IO.MOTOR_links.updateSpeed(speed);
    }

    public static void setRightSpeed(int speed)
    {
    	IO.MOTOR_rechts.updateSpeed(speed);
    }

}
