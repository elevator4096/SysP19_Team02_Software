package team06.testprogramme;

import java.io.PrintStream;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import team06.aurelia.Main;
import team06.Variablen;
import team06.aurelia.Main;

public class Test extends Task {

	SpeedControllerExample controller;

	public Test() {

		controller = new SpeedControllerExample();
		System.out.println("Main Konstruktor gestartet");
	}

	public void action() {
		System.out.print("Umdrehungen  >");
		System.out.print(controller.gibweg());
		System.out.print("        Geschwindigkeit  >");
		System.out.println(controller.gibgeschwindigkeit());
		
		

	}

	static {

		Task task = new Test();
		task.period = 500;
		Task.install(task);

		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(9600, SCI.NO_PARITY, (short) 8);
		// Hook SCI1.out on System.out
		System.out = new PrintStream(sci1.out);
		System.out.println("static start");

	}

}
