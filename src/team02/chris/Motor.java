/**
 * @Author Chris
 * @version 2019.03.05
 */

package team02.chris;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_FQD;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;
import team02.IO;

public class Motor implements IO,Konstanten
{
    private TPU_FQD fqd;
    private TPU_PWM pwm;
    private int pwm_time;


    public Motor(TPU_FQD fqd, TPU_PWM pwm,int pwm_time)
    {
        this.fqd = fqd;
        this.pwm = pwm;
        this.pwm_time = pwm_time;
    }


    public void updateSpeed(float f)
    {
        pwm.update(PWM_Period,calculateDutyCycle(f));
    }


    private int calculateDutyCycle(double d)
    {
        double maxSpeed = MAX_ROUNDS*GEAR_RATIO*WHEEL_DIAMETER*Math.PI;

        if(d > maxSpeed)
        {
            d = maxSpeed;
        }
        if(d< -maxSpeed)
        {
            d = -maxSpeed;
        }

        int duty_cycle = (int)(PWM_Period*(d + maxSpeed)/(2*maxSpeed));

        return duty_cycle;
    }
    
}
