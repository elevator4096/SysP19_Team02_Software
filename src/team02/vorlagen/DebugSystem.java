/*
 * @Author Chris
 * @version 2019.03.14
 */


package team02.vorlagen;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import team02.IO;
import java.lang.System;

import java.io.PrintStream;

public class DebugSystem implements IO
{

	public static SCI sci1;
    /**
     * Gibt die aktuellen Zustände auf der definierten System.out Schnittstelle aus
     * Auftrag:
     * Es sollen die Zustände von Ein/Ausgängen auf die Serielle schnittstelle ausgegeben werden.
     * Pseudo Code Beispiel(Syntax wahrscheinlich nicht korrekt)
     * system.out.print("DIG_IO x ");
     * system.out.print(eingangx.get());
     * dH der Output stream muss zuerst initialisiert werden.
     * Sonst kann nichts auf die serielle Schnittstelle ausgegeben werden
     */

    private static DebugSystem debugSystem;

    private DebugSystem()
    {

            sci1 = SCI.getInstance(SCI.pSCI1);
            sci1.start(9600, SCI.NO_PARITY, (short) 8);
            //Hook SCI1.out on System.out
            System.out = new PrintStream(sci1.out);
            System.err = System.out;
            System.out.println("Debug System Aktiv!");
    }

    /**
     * Methdo um Singleton zu erzeugen
     * @return die Debug Instanz
     */
    public static DebugSystem getInstance()
    {
        if(debugSystem==null)
        {
            debugSystem= new DebugSystem();
        }
        return debugSystem;
    }

    /**
     *
     * @param s gibt ein String aus
     */
    public void println(String s)
    {

        System.out.println(s);
    }

    /**
     *
     * @param i gibt ein Integer aus
     */
    public void println(int i)
    {

        System.out.println(i);
    }

    /**
     *
     * @param d gibt ein Double aus
     */
    public void println(double d)
    {

        System.out.println(d);
    }

    /**
     *
     * @param s gibt ein String aus
     */
    public void print(String s)
    {
        System.out.print(s);
    }

    /**
     *
     * @param o gibt die ObjetID aus
     */
    public void print(Object o)
    {
        System.out.print(o.toString());
    }

    /**
     * Zeilenumbruch
     */
    public void println()
    {
        System.out.println();
    }

    /**
     * update
     */
    public void update()
    {

    }

    
    public void println(boolean b)
    {
    	if(b)
    	{
    		System.out.println("TRUE");
    	}else
    	{
    		System.out.println("FALSE");
    	}
    }
    /**
     * Experimentell, liest die serielle Schnittstelle aus
     * @return int aus dem Seriellen Puffer
     */
    public int read()
    {
    	try {
    	return sci1.read();
    	}catch(Exception e)
    	{
    		return -1;
    	}
    }
}
