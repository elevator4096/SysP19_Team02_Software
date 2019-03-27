/**
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

    /**
     * Gibt die aktuellen Zustände auf der definierten System.out Schnittstelle aus
     * Auftrag:
     * Es sollen die Zustände von Ein/Ausgängen auf die Serielle schnittstelle ausgegeben werden.
     * Pseudo Code Beispiel(Syntax wahrscheinlich nicht korrekt)
     * system.out.print("Eingang x ");
     * system.out.print(eingangx.get());
     * dH der Output stream muss zuerst initialisiert werden.
     * Sonst kann nichts auf die serielle Schnittstelle ausgegeben werden
     */

    private static DebugSystem debugSystem;

    private DebugSystem()
    {

            SCI sci1 = SCI.getInstance(SCI.pSCI1);
            sci1.start(9600, SCI.NO_PARITY, (short) 8);
            //Hook SCI1.out on System.out
            System.out = new PrintStream(sci1.out);
            System.out.println("Debug System Aktiv!");
    }

    public static DebugSystem getInstance()
    {
        if(debugSystem==null)
        {
            debugSystem= new DebugSystem();
        }
        return debugSystem;
    }

    public static void println(String s)
    {

        System.out.println(s);
    }
    
    public static void println(int i)
    {

        System.out.println(i);
    }

    public void print(String s)
    {
        System.out.print(s);
    }

    public void update()
    {

    }
}
