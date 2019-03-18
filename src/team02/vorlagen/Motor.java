/**
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


    /**
     * Konstruktor für ein Motor
     * @param fqd FQD Objekt, welches zum Motor gehört
     * @param pwm PWM Objekt, welches zum MOtor gehört
     * @param pwm_time pwm_time Periodendauer
     */
    public Motor(TPU_FQD fqd, TPU_PWM pwm, int pwm_time) {
        this.fqd = fqd;
        this.pwm = pwm;
        this.pwm_time = pwm_time;
    }

    /**
     * Setze Geschwindigkeit, kann positiv oder negativ werden
     * @param f Geschwindigkeit in mm/s
     */
    public void updateSpeed(float f) {
        pwm.update(PERIOD_Motoren, calculateDutyCycle(f));
    }

    /**
     * Interne Methode zum berechnen der Periodendauer
     * @param d Geschwindigkeit in mm/s
     * @return Duty Cycle in 0..1, muss mit der Periodendauer multipliziert werden
     */
    private int calculateDutyCycle(double d) {
        double maxSpeed = MAX_ROUNDS * GEAR_RATIO * WHEEL_DIAMETER * Math.PI;

        if (d > maxSpeed) {
            d = maxSpeed;
        }
        if (d < -maxSpeed) {
            d = -maxSpeed;
        }

        int duty_cycle = (int) (PERIOD_Motoren * (d + maxSpeed) / (2 * maxSpeed));
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
