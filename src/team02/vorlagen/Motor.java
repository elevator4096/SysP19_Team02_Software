/*
 * @Author Chris
 * @version 2019.03.15
 */

package team02.vorlagen;

import ch.ntb.inf.deep.runtime.mpc555.driver.MDASM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_FQD;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;
import team02.IO;
import team02.Konstanten;

public class Motor implements IO, Konstanten {
    private TPU_FQD fqd;
    private TPU_PWM pwm;
    private int pwm_time;
    private MDASM_DIO out;
    private boolean inverted 	 = false;
    private short   lastEncPos   = 0;
    private double  distanz		 = 0;
    private int		realEncPos	 = 0;

    public void update()
    {
    	calcDistanz();
    }
    
    /**
     * Konstruktor für ein Motor
     * @param fqd FQD Objekt, welches zum Motor gehört
     * @param pwm PWM Objekt, welches zum MOtor gehört
     * @param pwm_time pwm_time Periodendauer
     */
    public Motor(TPU_FQD fqd, TPU_PWM pwm, int pwm_time, boolean inverted, MDASM_DIO out) {
        this.fqd = fqd;
        this.pwm = pwm;
        this.pwm_time = pwm_time;
        this.inverted = inverted;
        this.out = out;
    }

    /**
     * Setze Geschwindigkeit, kann positiv oder negativ werden
     * @param d Geschwindigkeit in m/s (max 0.213m/s)
     */
    public void updateSpeed(double d) {
    	pwm.update(calculateDutyCycle(d));
    }

    /**
     * Interne Methode zum berechnen der Periodendauer
     * @param d Geschwindigkeit in m/s
     * @return high Time fuer die aktuelle PERIOD_Motoren
     */
    private int calculateDutyCycle(double d) {
        

        if (d > Konstanten.MAX_SPEED) {
            d = Konstanten.MAX_SPEED;
        }
        if (d < -Konstanten.MAX_SPEED) {
            d = -Konstanten.MAX_SPEED;
        }
        d = (inverted)? d:-d;

        if(d<0)
        {
            out.set(true);
        }
        else
        {
            out.set(false);
        }

        //int duty_cycle = (int) (PERIOD_Motoren * (d + Konstanten.MAX_SPEED) / (2 * Konstanten.MAX_SPEED));
        return (int)(PERIOD_Motoren*(Math.abs(d)/Konstanten.MAX_SPEED));
    }

    /**
     * Berechnet die die zurückgelegte Distanz
     */
    private void calcDistanz()
    {
    	
    	short temp 	=  fqd.getPosition();
    	realEncPos += (short)(temp-lastEncPos);
    	lastEncPos  =  temp;
    	distanz 	=  DISTANCE_PER_TICK*realEncPos;  
    }

    /**
     *
     * @return die zurückgelegte Distanz
     */
    public double getDistanz()
    {
    	calcDistanz();
    	return distanz;
    }

    /**
     * Setzt die zurückgelegte Distanz zurück
     */
    public void resetDistanz()
    {
    	distanz 	= 0;
    	realEncPos 	= 0;
    	lastEncPos 	= 0;
    	fqd.setPosition(0);
    }
}
