package team02;

public class Debug implements Konstanten
{
	static int x =1;
	
	
	public Debug()
	{
		
	}
	
	public void run()
	{
		if(Konstanten.TIME_1S/Konstanten.TASK_PERIOD==x)
		{
			System.out.println("Debug Enabled");
			x=1;
		}
		
		x++;
	}
}
