package team06;

import ch.ntb.inf.deep.runtime.ppc32.Task;

public class TaskMotor extends Task {

	/**
	 * Konstrukor, um den TaskMotor zu initialiseren
	 */
	public TaskMotor() {

	}

	/**
	 * Methode, die zyklisch aufgerufen wird
	 */
	public void action()

	{
		Instanzen.wurfSystem.motorstarten();
		Instanzen.fahrSystem.motorstarten();
		Instanzen.servoMotor.update();

	}

	static {

		Task t = new TaskMotor();
		t.period = (int) (Variablen.TASK_PERIOD_MOTOR);
		Task.install(t);

	}

}
