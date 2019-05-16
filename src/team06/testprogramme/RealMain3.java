/*
 * 		Servo TPU-A 3
 * 		Servo TPU-A 1
 * 		Servo TPU-A 0
 * 		Servo TPU-A 2
 * 
 * 		Taster	MPIOB 7
 * 		Taster	MPIOB 9
 * 		Taster	MPIOB 11
 * 		Taster	MPIOB 12
 * 		Taster	MPIOB 10
 * 		Taster	MPIOB 8
 * 
 * 		Motor1 	TPU-A	4
 * 		Motor1 	TPU-A	5
 * 		Encoder1 TPU-A	10
 * 		Encoder1 TPU-A	11
 * 
 * 		Motor2 	TPU-A	8
 * 		Motor2 	TPU-A	9
 * 		Encoder2 TPU-A	6
 * 		Encoder2 TPU-A	7
 */


/*
 * Speed Optimization && drive Optimization*
 * slow servo angle*
 * stacking height
 * IR Sensor, not drive fwd*
 */

package mainTask;

import java.io.PrintStream;

import Motors.DCMotorEncoderDrive;
import Motors.DCMotorEncoderLift;
import Motors.ServoMotor;
import Sensors.LimitSwitchold;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.*;
import ch.ntb.inf.deep.runtime.util.CmdInt;
import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.RN131;

public class RealMain3 extends Task
{
	long time;
	long setTimeStamp = 0;
	
	boolean useWifi=false;
	
	boolean switch1State;
	boolean switch2State;
	
	private RN131 wifi;	
	
	LimitSwitchold switchWhite;
	LimitSwitchold switchRed;
	
	public static boolean switchIsEnabled = false;
	
	ServoMotor ServoGripper;
	ServoMotor ServoGuide;
	ServoMotor ServoAngle;
	
	DCMotorEncoderDrive dcmDrive;
	DCMotorEncoderLift dcmLift;
	
	int state = 0;
	int stateInCase = 0;
	int wifiState = 0;
	
	long pos=0;
	
	public boolean init = false;
	public boolean case3 = false;
	public boolean alreadyTookBeacon = false;
	public boolean truevariable = true;
	public int counter = 0;
	public int counterCase7 = 0;
	public boolean noMoreBricks = false;
	public boolean countDown = false;
	public boolean towerBeacon = false;
	
	public int angleOpen = 45;
	public int angleClose = 34;
	
	public int cmd;
	
	public RealMain3() throws Exception{
		time = System.currentTimeMillis();
		
		switch1State = false;
		
		
		switch2State = false;
		
		switchWhite = new LimitSwitchold(9);
		switchRed = new LimitSwitchold(7);
		
		ServoGripper = new ServoMotor(3, 90, 30, 81);	// Pin, DefaultPos, MinPos, MaxPos
		ServoGuide = new ServoMotor(1,66, -30, 66);	// Pin, DefaultPos, MinPos, MaxPos
		ServoAngle = new ServoMotor(0, 15, 7, 30);
		
		dcmLift = new DCMotorEncoderLift();  // Pins for testboard
		dcmDrive = new DCMotorEncoderDrive(); //switchpin, int fqdpin, int pwm1, int pwm2
		
		SCI sci = SCI.getInstance(SCI.pSCI2);
		sci.start(115200, SCI.NO_PARITY, (short)8);

		wifi = new RN131(sci.in , sci.out, new MPIOSM_DIO(5, true));
		
		
		
	}

	
	public void action(){
		
		
	
	if((switchRed.getSwitchInputs() == true) && (switch1State == false)) {
		useWifi = true;
		switch1State = true;
		
	}else if ((switchWhite.getSwitchInputs() == true) && (switch1State == false)) {
		useWifi = false;
		switch1State = true;
	}
		
		
		
	
	if(switch1State == true) {
		
		if (useWifi) {
			cmd = -1;
			if (wifi.connected()) {
	              CmdInt.Type type = wifi.cmd.readCmd();
	              if (type == CmdInt.Type.Cmd) {
	                   cmd = wifi.cmd.getInt();
	              }
	         }

		}
					
		switch (state) {
			case 0:  //Init
				//System.out.print("RealposLift = : ");
				//System.out.println(dcmLift.getActualPos());
				if (init == false) {
					init = true;
					
					wifiState = 310;
					//System.out.println("Case 0	//Init");
					
					ServoGuide.setPosition(-14);	//Working Pos
					//System.out.println("// Open Cube Guide");
					
					dcmLift.setZeroSwitch();
					//System.out.println("// Move Gripper down until Switch");
	
					ServoAngle.setPosition(8);	//Working Pos.
					//System.out.println("// AngleServo to working Pos");
					
					ServoGripper.setPosition(angleOpen);	//Open: 45 // Close: 36
					//System.out.println("// Open Gripper");
					
				}

				if ((!useWifi) && (switchRed.getSwitchInputs() == true)) {
					if ((switchWhite.getSwitchInputs() == true) && (switchIsEnabled == false)){
						state = 9;
						switchIsEnabled = true;
					} else if (switchWhite.getSwitchInputs() == false) {
						 switchIsEnabled = false;
					}
				}else if (useWifi) {
					if(useWifi) {
						wifi.cmd.writeCmd(400);
					}


						//System.out.println(cmd);
						if (cmd == 800) {
							countDown = true;
							state = 9;	
						} else {
							//System.out.println("error Case 1");
						}
		
					
				}
				
			break;
			
			
			
			
			case 1:  //Set Gripper Height
				//System.out.print("RealposLift = : ");
				//System.out.println(dcmLift.getActualPos());

	
				if (counter == 1) {
					dcmLift.setTargetPos(0); 
					//System.out.println("Case 1	//Set Gripper Height Towertop");
					state = 3;
				} else if (counter == 2){
					dcmLift.setTargetPos(-1300000); // WERT NOCH ANPASSEN FÜR GENAUE HÖHE DER STACKING POSITION
					//System.out.println("Case 1	//Set Gripper Height First Position with Beacon");
					state = 2;
					// 18.5mm differenz von oberen zu unterem Stein 
				} else if (counter == 3) {
					dcmLift.setTargetPos(-2700000); // WERT NOCH ANPASSEN FÜR GENAUE HÖHE DER STACKING POSITION
					//System.out.println("Case 1	//Set Gripper Height Second Position");
					state = 2;
				} else if (counter >= 4) {
					dcmLift.setTargetPos(-3200000); // WERT NOCH ANPASSEN FÜR GENAUE HÖHE DER STACKING POSITION
					//System.out.println("Case 1	//Set Gripper Height Second Position");
					state = 2;
				}
				
					
			break;
		
			
			
			
			case 2:		//******************************************************Case 2 check if area clear / waiting for start signal*************************************************************************
				if(useWifi) {
					wifiState = 314;	
				}
				
				//System.out.print("RealposLift = : ");
				//System.out.println(dcmLift.getActualPos());
				
				//System.out.println("Case 2	//Check if Area Clear");
				
				if (!useWifi) {
					if ((switchWhite.getSwitchInputs() == true) && (switchIsEnabled == false)){
						state = 3;
						switchIsEnabled = true;
					} else if (switchWhite.getSwitchInputs() == false) {
						 switchIsEnabled = false;
					}
				}else if (useWifi) {
					if(useWifi) {
						wifi.cmd.writeCmd(300);
					}


						//System.out.println(cmd);
						if (cmd == 316) {
							state = 3;	
							
						} else {
							//System.out.println("Waiting for clear area");
							wifi.cmd.writeCmd(wifiState);
							state = 2;
						}
						
						if(cmd == 318) {
							state = 3;
							noMoreBricks = true;
						}
		
					
					
					
				}
			break;
			
			case 3:		//******************************************************Case 3 drive forward*************************************************************************
				ServoAngle.setPosition(8);	//Working Pos.
				//System.out.println("// AngleServo to working Pos");
				
				if(case3 == false) {
					case3 = true;
					//System.out.println("Case 3	// Drive forward");
					
					if(towerBeacon == false) {
						//System.out.println("angle -14");
						ServoGuide.setPosition(-14);	//Working Pos
						towerBeacon = true;
					}
					
					if(towerBeacon == true && counter > 1) {
						//System.out.println("angle 10");
						ServoGuide.setPosition(-14);	//Working Pos
					}
					
					dcmDrive.setZeroSwitch();
					if(useWifi) {
						wifiState = 311;	
					}	
				}
				
				if (useWifi && dcmDrive.getSwitchDrive() == true) {
					//System.out.println("next state");
					state = 4;
				}
				
				if(useWifi) {
					wifi.cmd.writeCmd(wifiState);
				}
				
				if (!useWifi) {
					if ((switchWhite.getSwitchInputs() == true) && (switchIsEnabled == false)){
						state = 4;
						switchIsEnabled = true;
					}else if (switchWhite.getSwitchInputs() == false) {
						 switchIsEnabled = false;
					}
				}
			break;	
				
			
			
			
			case 4:		//******************************************************Case 4 grab or stack*************************************************************************
				//System.out.print("RealposLift= : ");
				//System.out.println(dcmLift.getActualPos());
				time = System.currentTimeMillis();
				
				ServoAngle.setPosition(8);	//Working Pos.
				//System.out.println("// AngleServo to working Pos");
				
				case3 = false;
				
				if (truevariable) {
					setTimeStamp = time;
					truevariable = false;
				}
				
				if (counter == 1) {
					ServoGripper.setPosition(angleClose);	// Close Gripper 
					//System.out.println("Case 4 // Close Gripper");
					
					
					if (time >= setTimeStamp + 100000) { // 100000 = 0.1s
					dcmLift.setTargetPos(-200000);
					//System.out.println("Case 4 // Lift Tower");
					//ServoGuide.setPosition(66);	//deploy	//Close Cube Guide
					state = 5;
					truevariable = true;
					}
					
				} else if (counter == 2 && dcmLift.getActualPos() < -1250000 ) {
					dcmLift.setTargetPos(-600000); // 
					//System.out.println("Case 4 // Stacking with beacon");
					wifiState = 312;
					stateInCase = 1;

				
				} else if (counter == 3 && dcmLift.getActualPos() < -2600000) {
					dcmLift.setTargetPos(-2000000); // 
					//System.out.println("Case 4 // Stacking with package");
					stateInCase = 2;
					
				} else if (counter >= 4 && dcmLift.getActualPos() < -2800000) {
					dcmLift.setTargetPos(-2700000); // 
					//System.out.println("Case 4 // Stacking with last package");
					stateInCase = 9;
					
				}
		
				
				
				if (stateInCase == 1 && (dcmLift.getActualPos() > -1050000) && (dcmLift.getActualPos() < - 400000) && (time >= setTimeStamp + 2000000)) {
					
					
					dcmLift.setTargetPos(-800000);
					
					if (time >= setTimeStamp + 2500000) {
						ServoGripper.setPosition(angleOpen);	// Open Gripper
						//System.out.println("Case 4 // Open Gripper");
						dcmLift.setZeroSwitch();
						//System.out.println("Case 4 // Lower grabbing position");
						stateInCase = 3;
					}
						
				}
							
				
				
				if (stateInCase == 2 && dcmLift.getActualPos() > -4000000 && dcmLift.getActualPos() < - 1000000 && time >= setTimeStamp + 2000000)  {
					
					dcmLift.setTargetPos(-2100000);
					
					if (time >= setTimeStamp + 2500000) {
						ServoGripper.setPosition(angleOpen);	// Open Gripper
						//System.out.println("Case 4 // Open Gripper");
						
						dcmLift.setTargetPos(-2810000); //
						
						truevariable = true;
						
						state = 11;
						stateInCase = 3;
					}
					
				}
					
					if (stateInCase == 9 && dcmLift.getActualPos() > -4000000 && dcmLift.getActualPos() < - 1000000 && time >= setTimeStamp + 2000000)  {
						
						dcmLift.setTargetPos(-2800000);
						
						if (time >= setTimeStamp + 2500000) {
							ServoGripper.setPosition(angleOpen);	// Open Gripper
							//System.out.println("Case 4 // Open Gripper");
							
							dcmLift.setTargetPos(-2810000); //
							
							truevariable = true;
							
							state = 11;
							stateInCase = 3;
						}
					

					
//					dcmLift.setZeroSwitch();
//					System.out.println("Case 3 // Lower grabbing position");
				}
				
				if (stateInCase == 3 && dcmLift.getActualPos() < 2000 && dcmLift.getActualPos() > -2000 && dcmDrive.getSwitchDrive() == true)  { // && time >= timestamp + 1000000{
					ServoGripper.setPosition(angleClose);	// Close Gripper
					//System.out.println("Case 4 // Close Gripper");
					//ServoGuide.setPosition(66);	//deploy	//Close Cube Guide
					
					dcmLift.setTargetPos(-400000);
					//System.out.println("Case 4 // Lift package");
					state = 5;
					truevariable = true;
				}	

				
				if (!useWifi) {
					if ((switchWhite.getSwitchInputs() == true) && (switchIsEnabled == false)){
						state = 5;
						switchIsEnabled = true;
					} else if (switchWhite.getSwitchInputs() == false) {
						 switchIsEnabled = false;
					}
				}
			break;
			
			
			
			
			
			case 5:		//******************************************************Case 5 drive backwards*************************************************************************
				time = System.currentTimeMillis();
				
				if(useWifi) {
					wifiState = 315;	
				}
				alreadyTookBeacon = true;
				//System.out.println("Case 5	//drive bwd");
				dcmDrive.setTargetPos(800000);
				
				if (truevariable) {
					setTimeStamp = time;
					truevariable = false;
				}
				
				if(time >= setTimeStamp +2000000) {
					ServoGuide.setPosition(66);
				}
				
				wifiState = 313;
				
				if (dcmDrive.getActualPos() < 802000 && dcmDrive.getActualPos() > 798000) {
					state = 6;
					truevariable = true;
				}
			break;
			
			
			
			
			case 6:  //******************************************************Case Decide Time*************************************************************************
				//System.out.println("Case 6	//decide time");
				
				if(useWifi) {
					wifiState = 314;	
				}
				
				if (!useWifi) {
					if ((switchWhite.getSwitchInputs() == true) && (switchIsEnabled == false)){
						state = 9;
						switchIsEnabled = true;
					} else if (switchRed.getSwitchInputs() == true) {
						state = 7;
						switchIsEnabled = true;
					} else if (switchWhite.getSwitchInputs() == false) {
						switchIsEnabled = false;
					} else if (switchRed.getSwitchInputs() == false) {
						switchIsEnabled = false;
					}
				}
				
				if (true) {
					if(noMoreBricks == false) {// *4 depends on t.period // && timeCounter <= (100*4)
						state = 9;
					}
					
					if(noMoreBricks == true) {
						state = 7;
					}
					
				}
				
			break;
			
			
			
			
			case 7:		//******************************************************Case 7 deploy*************************************************************************
				//System.out.println("Case 7	//deploy");
				//System.out.println(dcmDrive.getActualPos());
				//System.out.println(dcmLift.getActualPos());
				
				if(useWifi) {
					wifiState = 316; // evtl 18	
					wifi.cmd.writeCmd(wifiState);
				}


				counterCase7++;
						
				if (counterCase7 >= 38) {		
					state = 8;
				}else if (counterCase7 >= 35) {
					ServoGripper.setPosition(60); //Open wide
				}else if (counterCase7 >= 30) {
					ServoGripper.setPosition(38);	//Open small
				}else if (counterCase7 == 25) {
					// dcmDrive.setTargetPos(1000000);
					dcmLift.setTargetPos(-2600000);
					// ServoAngle.setPosition(8);	//work Pos//10
				}else if (counterCase7 >= 4) {
					
					ServoAngle.setPosition(7 + counterCase7);	//deploy pos//26
				}else{
					ServoGuide.setPosition(66);	//deploy	//Close Cube Guide
					
				}
					
				
				
			
				
			break;
			
			case 8:		//******************************************************Case 8 finish*************************************************************************  
				System.out.println("Case 8	//Finish");
				if(useWifi) {
					wifiState = 802;	// finish signal is 801/802!!! 802 LED begin to lightrotation
					wifi.cmd.writeCmd(wifiState);
				}

			break;
			
			case 9:		//******************************************************Case 9 counter*************************************************************************
				counter++;
				state = 1;
			break;
			
			case 10:
				time = System.currentTimeMillis();	
				
				ServoGripper.setPosition(angleClose);	// Close Gripper
				//System.out.println("Case 10 // Close Gripper");
				
				dcmLift.setTargetPos(-3200000);
				//System.out.println("Case 4 // Lift package");
			
				dcmDrive.setTargetPos(-10000);
				//System.out.println("Case 10 // Move forward to regrab tower");
				
				dcmLift.setTargetPos(-2700000);
				
				if (truevariable == true ) {
					setTimeStamp = time;
					truevariable = false;
				}
				
				if (time >= setTimeStamp + 1000000) {
					
					ServoGripper.setPosition(angleOpen);	// Open Gripper
					//System.out.println("Case 10 // Open Gripper");
				
					dcmDrive.setZeroSwitch();
					//System.out.println("Case 10 // Move forward to zero");
					
				}
					
				if (stateInCase == 3 && dcmDrive.getActualPos() < 2000 && dcmDrive.getActualPos() > -2000) {
					state = 11;
					
					if(noMoreBricks == false && dcmDrive.getSwitchDrive() == true) {
						// dcmLift.setZeroSwitch();
						dcmLift.setTargetPos(-500000);
						//System.out.println("Case 11 // Lower grabbing position");
						stateInCase = 3;
						truevariable = true;
						if (truevariable == true ) {
							setTimeStamp = time;
							truevariable = false;
						}
					}
					
					if(noMoreBricks == true && dcmDrive.getSwitchDrive() == true) {
						dcmLift.setTargetPos(-2500000);
						noMoreBricks = true;
						stateInCase = 5;
						truevariable = true;
						
						if (truevariable == true ) {
							setTimeStamp = time;
							truevariable = false;
						}
					}
					
				}
				
				if (stateInCase == 3 && dcmLift.getActualPos() < -300000 && dcmLift.getActualPos() > -700000 && time >= setTimeStamp + 1000000) {
					
					ServoGripper.setPosition(angleClose);	// Close Gripper
					//System.out.println("Case 11 // Close Gripper");
					//ServoGuide.setPosition(66);	//deploy	//Close Cube Guide
					
					dcmLift.setTargetPos(-800000);
					//System.out.println("Case 11 // Lift package");
					state = 5;
					truevariable = true;
					stateInCase = 5;
				}
				
				if(stateInCase == 5 && dcmLift.getActualPos() > -2900000 && dcmLift.getActualPos() < - 2300000 && noMoreBricks == true && time >= setTimeStamp + 1000000) {
					
					
					if (time >= setTimeStamp + 1000000) {
						ServoGripper.setPosition(angleClose);
						
						
						if (time >= setTimeStamp + 1500000) {
							dcmLift.setTargetPos(-2800000);
							//System.out.println("Case 11 // higher grabbing position");
							state = 5;
							truevariable = true;
						}
					}
				}
					
			break;
			
			
			
			case 11:	//******************************************************Case 11 another pressing*************************************************************************
				/*System.out.println("Case 11 // Nachdrücken");
				System.out.print("RealposLift= : ");
				System.out.println(dcmLift.getActualPos());
				
				System.out.println("time = : ");
				System.out.println(time);
				System.out.println("SetTimeStamp = : ");
				System.out.println(setTimeStamp);*/
				
				time = System.currentTimeMillis();
				
				if (truevariable == true ) {
					setTimeStamp = time;
					truevariable = false;
				}
				
				if (stateInCase == 3 && dcmLift.getActualPos() < -2700000 && time >= setTimeStamp + 2000000) {
					
					ServoGripper.setPosition(angleClose);	// Close Gripper
					//System.out.println("Case 11 // Close Gripper");
					
					if (time >= setTimeStamp + 200000) {
						dcmLift.setTargetPos(-2650000);
						//System.out.println("Case 11 // Nachdrücken");
						stateInCase = 4;
						truevariable = true;
						
						if (truevariable == true ) {
							setTimeStamp = time;
							truevariable = false;
						
						}
					
					}
				}

				if (stateInCase == 4 && dcmLift.getActualPos() > -2800000 && dcmLift.getActualPos() < - 2200000 && time >= setTimeStamp + 1000000) {

					dcmLift.setTargetPos(-2700000);
					
					if (time >= setTimeStamp + 1500000) {
						ServoGripper.setPosition(angleOpen);	// Open Gripper
						//System.out.println("Case 11 // Open Gripper");
						if (dcmDrive.getSwitchDrive() == false) {
							state = 10;
							stateInCase = 3;
							truevariable = true;
						}
					}
					
					if(noMoreBricks == false && time >= setTimeStamp + 1700000 && dcmDrive.getSwitchDrive() == true) {
						// dcmLift.setZeroSwitch();
						dcmLift.setTargetPos(-500000);
						//System.out.println("Case 11 // Lower grabbing position");
						stateInCase = 3;
						truevariable = true;
						if (truevariable == true ) {
							setTimeStamp = time;
							truevariable = false;
						}
					}
						
					if(noMoreBricks == true && time >= setTimeStamp + 1700000 && dcmDrive.getSwitchDrive() == true) {
						dcmLift.setTargetPos(-2500000);
						noMoreBricks = true;
						stateInCase = 5;
						truevariable = true;
						
						if (truevariable == true ) {
							setTimeStamp = time;
							truevariable = false;
						}
						
					}
					
					
					
				}
					
					
	
					
				
				
				if (stateInCase == 3 && dcmLift.getActualPos() < -300000 && dcmLift.getActualPos() > -700000 && time >= setTimeStamp + 1000000) {
					
					ServoGripper.setPosition(angleClose);	// Close Gripper
					//System.out.println("Case 11 // Close Gripper");
					//ServoGuide.setPosition(66);	//deploy	//Close Cube Guide
					
					dcmLift.setTargetPos(-800000);
					//System.out.println("Case 11 // Lift package");
					state = 5;
					truevariable = true;
					stateInCase = 5;
				}
				
				if(stateInCase == 5 && dcmLift.getActualPos() > -2900000 && dcmLift.getActualPos() < - 2300000 && noMoreBricks == true && time >= setTimeStamp + 1000000) {
					
					
					if (time >= setTimeStamp + 1000000) {
						ServoGripper.setPosition(angleClose);
						
						
						if (time >= setTimeStamp + 1500000) {
							dcmLift.setTargetPos(-2800000);
							//System.out.println("Case 11 // higher grabbing position");
							state = 5;
							truevariable = true;
						}
					}
				}
				
				
			break;
			
			}
		}
	}
	
	static{
		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(19200, SCI.NO_PARITY, (short)8);
		System.out = new PrintStream(sci1.out);
		
		try{
			Task t = new RealMain3();
			t.period = 250;
			Task.install(t);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
