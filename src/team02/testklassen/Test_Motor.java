/*
 * @author Chris
 * @version 2019.03.27
 */
package team02.testklassen;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;
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

    
    public static void setLeftSpeed50()
    {
		double d;
		d = ((double)(50))/100;
		d = d*IO.PERIOD_Motoren;
    	IO.MOTOR_links.updateSpeed((int)d);
    }
    
    public static void setLeftSpeed75()
    {
		double d;
		d = ((double)(75.0))/100;
		d = d*IO.PERIOD_Motoren;
    	IO.MOTOR_links.updateSpeed((int)d);
    }
    
    
    public static void setLeftSpeed0()
    {
    	IO.MOTOR_links.updateSpeed(0);
    }
    
    public static void setRightSpeed50()
    {
		double d;
		d = ((double)(50))/100;
		d = d*IO.PERIOD_Motoren;
    	IO.MOTOR_rechts.updateSpeed((int)d);
    }
}
