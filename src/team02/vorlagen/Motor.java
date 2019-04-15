/*
 * @Author Chris
 * @version 2019.03.15
 */

package team02.vorlagen;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_FQD;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;
import team02.IO;
import team02.Konstanten;

public class Motor implements IO, Konstanten {
    private TPU_FQD fqd;
    private TPU_PWM pwm;
    private int pwm_time;
    private boolean inverted = false;


    /**
     * Konstruktor für ein Motor
     * @param fqd FQD Objekt, welches zum Motor gehört
     * @param pwm PWM Objekt, welches zum MOtor gehört
     * @param pwm_time pwm_time Periodendauer
     */
    public Motor(TPU_FQD fqd, TPU_PWM pwm, int pwm_time, boolean inverted) {
        this.fqd = fqd;
        this.pwm = pwm;
        this.pwm_time = pwm_time;
        this.inverted = inverted;
    }

    /**
     * Setze Geschwindigkeit, kann positiv oder negativ werden
     * @param d Geschwindigkeit in m/s (max 0.213m/s)
     */
    public void updateSpeed(double d) {
        d = (inverted)? d:-d;
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

        int duty_cycle = (int) (PERIOD_Motoren * (d + Konstanten.MAX_SPEED) / (2 * Konstanten.MAX_SPEED));
        return duty_cycle;
    }

    /**
     * Gibt aktuelle Encoder Position zurück
     * @return aktuelle Encoder Position
     */
    public short getEncPos()
    {
        return fqd.getPosition();
    }

    /**
     * Setzt die aktuelle Encoder Position, zB zum Referenzieren
     * @param i Ueberschreibt den aktuellen Wert mit i
     */
    public void setEncPos(int i)
    {
        fqd.setPosition(i);
    }
}
