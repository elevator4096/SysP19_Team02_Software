/*
 * @author Chris
 * @version 2019.03.27
 */
package team02.testklassen;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;
import team02.IO;
import team02.Konstanten;

public class Test_Motor implements IO, Konstanten
{
	
	public static void printDistanzen()
	{
		;debug.print("Motor Distanzen: ");debug.print(IO.MOTOR_links.getDistanz());debug.print(" ");debug.println(IO.MOTOR_rechts.getDistanz());
	}
	
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
    	IO.MOTOR_links.updateSpeed(0.11);
    }
    
    public static void setLeftSpeed75()
    {

    	IO.MOTOR_links.updateSpeed(0.16);
    }
    
    public static void setRightSpeed75()
    {

    	IO.MOTOR_rechts.updateSpeed(0.16);
    }
    
    public static void setRightSpeed100()
    {

    	IO.MOTOR_rechts.updateSpeed(0.32);
    }
    
    
    public static void setLeftSpeed0()
    {
    	IO.MOTOR_links.updateSpeed(0);
    }
    
    public static void setRightSpeed50()
    {
    	IO.MOTOR_rechts.updateSpeed(0.11);
    }
    
    public static void setRightSpeedm50()
    {
    	IO.MOTOR_rechts.updateSpeed(-0.11);
    }
    
    public static void setRightSpeed0()
    {
    	IO.MOTOR_rechts.updateSpeed(0);
    }
    
}
