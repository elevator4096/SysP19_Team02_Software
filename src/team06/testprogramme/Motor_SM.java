package team06.testprogramme;



	import java.io.PrintStream;

	import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
	import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
	import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;
	import ch.ntb.inf.deep.runtime.ppc32.Task;


	public class Motor_SM {

		private int highTimeR = 0; 
		private int highTimeL = 0;
		private final static int pwmPeriod = 50000 / TPU_PWM.tpuTimeBase;
		private static TPU_PWM pwmR;
		private static TPU_PWM pwmL;
		private static Motor_SM sm;
		
		public static boolean direction = true;
		
		
		// constructor
		private Motor_SM() {

			pwmR = new TPU_PWM(true, 4, pwmPeriod, 0);
			pwmL = new TPU_PWM(true, 5, pwmPeriod, 0);
			
			
			System.out.println("System Start");
					
		}
		
		
		
		// Loop (task function)
		public void action() {	
			
		}
		

		
		private static void left() {
			pwmL.update(pwmPeriod);
			pwmR.update(0);
		}
		
		private static void right() {
			pwmL.update(0);
			pwmR.update(pwmPeriod);
		}
			
		private static void leftHalf() {
			pwmL.update(pwmPeriod / 2);
			pwmR.update(0);
		}
		
		private static void rightHalf() {
			pwmL.update(0);
			pwmR.update(pwmPeriod / 2);
		}
		
		private static void stop() {
			pwmL.update(0);
			pwmR.update(0);
		}
		
		static {
			
			// SCI initialize SCI_1 (9600 8N1)
			SCI sci1 = SCI.getInstance(SCI.pSCI1);
			sci1.start(9600, SCI.NO_PARITY, (short) 8);
			
			// Standard SCI1 for standard Output
			System.out = new PrintStream(sci1.out);
			
			
			sm = new Motor_SM();
			
			// Task properties
//			Motor_SM task = new Motor_SM();	// Generate the task
//			task.period=100; 							// Period length
//			Task.install(task); 						// Installation of the task
			
		}
		
		
}
