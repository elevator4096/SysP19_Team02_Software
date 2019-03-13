package team02;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;

public interface Konstanten
{
	//Debug Konstanten
	final boolean DEBUG 	= true;

	//Programm Parameter
	final int TASK_PERIOD 	= 200;
	final int TIME_1S		= 1000;

	//Wifi Parameter
	//Befehle
	final int BEW_ZYL 		= 0b1101;
	final int WERFE 		= 0b1110;


	final float GEAR_RATIO = 1.0f/86f;
	final int TICKS_PER_ROUND = 128;
	final float WHEEL_DIAMETER = 45.5f; 					//in mm
	final float WHEEL_DISTANCE = 100f;						//Horizontaler Abstand der RĂ¤der
	final int MOT_1_PWM = 980_000/ TPU_PWM.tpuTimeBase;
	final int MOT_2_PWM = 980_000/ TPU_PWM.tpuTimeBase;


	final int PWM_Period = 0;


	final float MAX_ROUNDS = 7_000/60;		//1/s


	final double SPANN_ZEIT = 0;
	final double WURF_ZEIT = 0;


}
