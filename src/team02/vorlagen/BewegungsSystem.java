/*
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

import ch.ntb.inf.deep.runtime.mpc555.driver.QADC_AIN;
import team02.HalteBedingung;
import team02.IO;
import team02.Konstanten;
import team02.ZustandBewegung;
import team02.dominique.*;

public class BewegungsSystem implements IO
{
	private static BewegungsSystem bewegungsSystem;
	
	
	private ZustandBewegung zustandBewegung 		= ZustandBewegung.STOP;
	private HalteBedingung 	halteBedingung			= HalteBedingung .BIS_NICHTS;		
	private boolean 		bewegungsRichtung		= false; //vorwaerts 	= true
	private boolean 		drehRichtung			= false; //GUZ		= true
	private boolean 		inBewegung 				= false;
	private double			zielDrehWinkel			= 0.0; // Kann beliebige Werte annehmen( springt nicht von 2PI auf 0)
	private double			zielDistanz				= 0.0;

	private BewegungsSystem()
	{
		debug.println("Bewegungssystem Aktiv!");
		IO.HLC_1395_PULSED.init(IO.HLC_Anzahl_Sens,IO.HLC_Code_Sens,IO.HLC_AN_chan);
		IO.HLC_1395_PULSED.start();
		
		QADC_AIN.init(true);
		QADC_AIN.init(false);
	}

	public static BewegungsSystem getInstance()
	{
		if(bewegungsSystem==null)
		{
			bewegungsSystem = new BewegungsSystem();
		}
		return bewegungsSystem;
	}
	
    /** fahre Distanz geradeaus
     * @param richtung true = vorwaerts
     * @param distanz
     */
	public void fahreFreiBisDistanz(boolean richtung,double distanz)
	{	
		setBewegung(ZustandBewegung.FAHRE_FREI, richtung, false, -1, HalteBedingung.BIS_DISTANZ_ERREICHT,distanz,0.0);
	}
	
    /**
     * kreuzungsPos = LINIE_VORNE,LINIE_MITTE,LINIE_HINTEN
     * @param richtung true = vorwaerts
     * @param kreuzungsPos ?
     */
	public void fahreFreiBisKreuzung(boolean richtung,int kreuzungsPos)
	{	
		setBewegung(ZustandBewegung.FAHRE_FREI, richtung, false, kreuzungsPos, HalteBedingung.BIS_NICHTS,0.0,0.0);
	}

    /**
     * kreuzungsPos = LINIE_VORNE,LINIE_MITTE,LINIE_HINTEN
     * @param kreuzungsPos ?
     */
	public void folgeLinieBisKreuzung(boolean richtung,int kreuzungsPos)
	{	
		setBewegung(ZustandBewegung.FOLGE_LINIE, richtung, false, kreuzungsPos, HalteBedingung.BIS_NICHTS,0.0,0.0);
	}

    /**
     * folgt Rueckwaerts der Linie bis an Wand angelangt 
     */
	public void folgeLinieBisWandRueckwaerts()
	{	
		setBewegung(ZustandBewegung.FOLGE_LINIE, false, false, -1, HalteBedingung.BIS_WAND,0.0,0.0);
	}

    /**
     * richtet den Roboter gerade an der Wand aus
     */
	public void richteAnWandAus()
	{		
		setBewegung(ZustandBewegung.RICHTE_AN_WAND_AUS, false, false, -1, HalteBedingung.BIS_WAND_HINTEN,0.0,0.0);
	}

    /**
     * dreht den Roboter in Korbwurfrichtung
     */
	public void dreheZuKorb()
	{
		setBewegung(ZustandBewegung.DREHE, false, drehRichtung, -1, HalteBedingung.BIS_DREHWINKEL_ERREICHT,0.0,Konstanten.DREHWINKEL_KORB);
	}
	
	/**
     * folgt der Linie eine gewisse Distanz
     * @param richtung vorwaerts = true
     * @param distanz
     */
	public void folgeLinie(boolean richtung, int distanz)
	{	
		setBewegung(ZustandBewegung.FOLGE_LINIE, false, false, -1, HalteBedingung.BIS_DISTANZ_ERREICHT,distanz,0.0);
	}
	
	/**
     * Drehe exakt 90 Grad nach rechts auf Linie
     */
	public void drehe90GradUZ()
	{	
		setBewegung(ZustandBewegung.DREHE, false, false, -1, HalteBedingung.BIS_LINIE_SENKRECHT,0.0,0.0);
	}
	
	/**
     * Drehe exakt 90 Grad nach links auf Linie
     */
	public void drehe90GradGUZ()
	{
		setBewegung(ZustandBewegung.DREHE, false, true, -1, HalteBedingung.BIS_LINIE_SENKRECHT,0.0,0.0);
	}
	
	/**
     * drehe Ungenau in UZ
     */
	public void dreheUngenauUZ(double winkel)
	{
		setBewegung(ZustandBewegung.DREHE, false, false, -1, HalteBedingung.BIS_DREHWINKEL_ERREICHT,0.0,winkel);
	}
	
	/**
     * drehe Ungenau in GUZ
     */
	public void dreheUngenauGUZ(double winkel)
	{
		setBewegung(ZustandBewegung.DREHE, false, true, -1, HalteBedingung.BIS_DREHWINKEL_ERREICHT,0.0,winkel);
	}


    /**
     * setzt alle Parameter des gewuenschten Bewegungszustandes
     */
	private void setBewegung(ZustandBewegung zustandBewegung, boolean richtung, boolean drehRichtung, int kreuzungsPos, HalteBedingung halteBedingung, double distanz, double drehWinkel)
	{
		this.zustandBewegung 	= zustandBewegung;
		this.bewegungsRichtung 	= richtung;
		this.drehRichtung		= drehRichtung;
		
		this.zielDistanz		= Fahren.getDistanz() + distanz    *(richtung    ? 1:-1);
		this.zielDrehWinkel		= Fahren.getPhi()	  + drehWinkel *(drehRichtung? 1:-1);

		if (kreuzungsPos != -1) { 
			switch(kreuzungsPos)
			{
			case Konstanten.LINIE_VORNE : 
				this.halteBedingung	= HalteBedingung.BIS_KREUZUNG_VORNE;
				break;
			case Konstanten.LINIE_MITTE: 
				this.halteBedingung	= HalteBedingung.BIS_KREUZUNG_MITTE;
				break;
			case Konstanten.LINIE_HINTEN: 
				this.halteBedingung	= HalteBedingung.BIS_KREUZUNG_HINTEN;
				break;
			default:
				IO.debug.print("BewegungsSystem: ungueltige kreuzungsPos ");
				IO.debug.println(kreuzungsPos);
			}
		}
		else
		{
			this.halteBedingung = halteBedingung;
		}
	}


	
	/**folge Linie in Fahrtrichtung
	 * 
	 * @param fahrtRichtung TODO: Hier ausfüllen!
	 */
	public void folgeLinie(boolean fahrtRichtung)
	{
		if (fahrtRichtung)
		{
			//Vorwaertsfahrt
			if (IO.LINE_Sensor_Vorne.istLinieLinks())
			{
				//Korrigiere im GegenUhrzeigersinn
				Fahren.KorrekturKurve(fahrtRichtung, true);
			}
			else if (IO.LINE_Sensor_Vorne.istLinieRechts())
			{
				//Korrigiere im Uhrzeigersinn
				Fahren.KorrekturKurve(fahrtRichtung, false);
			}
			else
			{
				Fahren.geradeaus(Konstanten.DRIVING_SPEED);
			}
		}
		else
		{
			//Rueckwaertsfahrt
			if (IO.LINE_Sensor_Hinten.istLinieLinks())
			{
				//Korrigiere im Uhrzeigersinn
				Fahren.KorrekturKurve(fahrtRichtung, false);
			}
			else if (IO.LINE_Sensor_Hinten.istLinieRechts())
			{
				//Korrigiere im GegenUhrzeigersinn
				Fahren.KorrekturKurve(fahrtRichtung, true);
			}
			else
			{
				Fahren.geradeaus(-Konstanten.DRIVING_SPEED);
			}
		}
	}
	
    /**
     * Pruefe ob der Roboter noch eine Bewegung durchfuehrt
     * @return
     */
	public boolean istInBewegung()
	{
		return inBewegung;
	}

	/**
	 * TODO: Hier fehlt was!
	 * @return
	 */
	private boolean istHalteBedingungErfuellt()
	{
		switch(halteBedingung)
		{
		case BIS_KREUZUNG_VORNE:
			return IO.LINE_Sensor_Vorne.istLinieVorne(); 
		case BIS_KREUZUNG_MITTE:
			return IO.LINE_Sensor_Rechts.istLinieLinks() || IO.LINE_Sensor_Links.istLinieRechts();
		case BIS_KREUZUNG_HINTEN:
			return IO.LINE_Sensor_Hinten.istLinieVorne();
		
		case BIS_DISTANZ_ERREICHT:
			debug.println("BIS_DISTANZ_ERREICHT");
			debug.print("DISTANZ: ");debug.print(Fahren.getDistanz());debug.print(bewegungsRichtung);debug.print("Ziel: ");debug.print(zielDistanz);debug.println("");
			return  ( bewegungsRichtung && Fahren.getDistanz()>=zielDistanz ) || ( !bewegungsRichtung && Fahren.getDistanz()<=zielDistanz ); 

		case BIS_DREHWINKEL_ERREICHT:
			return  ( ( drehRichtung && Fahren.getPhi()>=zielDrehWinkel ) || ( !drehRichtung && Fahren.getPhi()<=zielDrehWinkel ) ); 
		
		
		case BIS_LINIE_SENKRECHT:
			if(drehRichtung) 	return IO.LINE_Sensor_Vorne.istLinieRechts() || IO.LINE_Sensor_Hinten.istLinieRechts();	
			else 				return IO.LINE_Sensor_Vorne.istLinieLinks () || IO.LINE_Sensor_Hinten.istLinieLinks ();

			
		case BIS_WAND:
			return WandErkennung.istWandIrgendwo();
			
		case BIS_WAND_HINTEN:
			return WandErkennung.istWandVorne();
		
		case BIS_WAND_LINKS:
			return WandErkennung.istWandLinks();
		
		case BIS_WAND_RECHTS:
			return WandErkennung.istWandRechts();
		
		case BIS_NICHTS:
			return true;
			
		default:
			IO.debug.print("BewegungsSystem: nicht implementierte Haltebedingung!");
		}
		
		return false;
	}

	/**
	 * TODO: Hier fehlt was!
	 */
	private void bewege()
	{
		switch(zustandBewegung)
		{
		case FAHRE_FREI:
			Fahren.geradeaus(Konstanten.DRIVING_SPEED);
			break;
		case FOLGE_LINIE:
			folgeLinie(bewegungsRichtung);
			break;			
		case DREHE:
			Fahren.drehe(Konstanten.TURNING_SPEED*(drehRichtung? 1:-1) );
			break;		
		case STOP:
			Fahren.stop();
			break;
		case RICHTE_AN_KORB_AUS:
			Fahren.drehe(Konstanten.TURNING_SPEED*(drehRichtung? 1:-1) );
			break;

		case RICHTE_AN_WAND_AUS:
			if(!WandErkennung.istWandLinks()) {
				//Wenn Wand Links langsame Drehung um Linkes Rad im GegenUhrzeigersinn
				Fahren.kurveFahren(Konstanten.WHEEL_DISTANCE/2 , Konstanten.SLOW_SPEED);
			}
			else {
				//Wenn Wand Rechts langsame Drehung um Rechtes Rad im Uhrzeigersinn
				Fahren.kurveFahren(-Konstanten.WHEEL_DISTANCE/2 , Konstanten.SLOW_SPEED);
			}
			break;
			
		default:
			IO.debug.print("BewegungsSystem: nicht implementierter Zustand!");
		}
	}
	
	/**
	 * update wird zyklisch aufgerufen
	 * Stoppt die Bewegung des Roboters sobald Zielposition erreicht wurde  
	 */
	public void update()
	{
		Fahren.update();
		
		if (istHalteBedingungErfuellt())
		{
			inBewegung 		= false;
			zustandBewegung = ZustandBewegung.STOP;
			halteBedingung 	= HalteBedingung.BIS_NICHTS;
		}
		
		
		bewege();
		
	}
	
	

}
