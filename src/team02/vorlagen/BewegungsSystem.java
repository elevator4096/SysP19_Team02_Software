package team02.vorlagen;

import team02.ZustandBewegung;

public class BewegungsSystem {
	private boolean inBewegung = false;
	private ZustandBewegung zustandBewegung = ZustandBewegung.STOP;
	
	//kreuzungsPos = LINIE_VORNE,LINIE_MITTE,LINIE_HINTEN
	public void fahreFreiBisKreuzungVorwaerts(int kreuzungsPos)
	{
		zustandBewegung = ZustandBewegung.FAHRE_FREI_BIS_KREUZUNG_VORWAERTS;
	}
	
	//kreuzungsPos = LINIE_VORNE,LINIE_MITTE,LINIE_HINTEN
	public void fahreFreiBisKreuzungRueckwaerts(int kreuzungsPos)
	{
		zustandBewegung = ZustandBewegung.FAHRE_FREI_BIS_KREUZUNG_RUECKWAERTS;
	}
	
	//kreuzungsPos = LINIE_VORNE,LINIE_MITTE,LINIE_HINTEN
	public void folgeLinieBisKreuzungVorwaerts(int kreuzungsPos)
	{
		zustandBewegung = ZustandBewegung.FOLGE_LINIE_BIS_KREUZUNG_VORWAERTS;
	}
	
	//kreuzungsPos = LINIE_VORNE,LINIE_MITTE,LINIE_HINTEN
	public void folgeLinieBisKreuzungRueckwaerts(int kreuzungsPos)
	{
		zustandBewegung = ZustandBewegung.FOLGE_LINIE_BIS_KREUZUNG_RUECKWAERTS;
	}
	
	public void folgeLinieBisWandVorwaerts()
	{
		zustandBewegung = ZustandBewegung.FOLGE_LINIE_BIS_WAND_VORWAERTS;
	}
	
	public void richteAnWandAus()
	{
		zustandBewegung = ZustandBewegung.RICHTE_AN_WAND_AUS;
	}	
	
	public void dreheZuKorb()
	{
		zustandBewegung = ZustandBewegung.DREHE_ZU_KORB;
	}
	
	
	
	
	/**Distanz = 0 -> fahre unendlich */
	public void folgeLinieVorwaerts(int distanz)
	{
		zustandBewegung = ZustandBewegung.FOLGE_LINIE_VORWAERTS;
	}
	
	/**Muss nicht zwingend wirklich Linie folgen!*/
	public void folgeLinieRueckwaerts(int distanz)
	{
		zustandBewegung = ZustandBewegung.FOLGE_LINIE_RUECKWAERTS;
	}
	
	/**Drehe exakt 90 Grad nach rechts auf Linie*/
	public void drehe90GradRechts()
	{
		zustandBewegung = ZustandBewegung.DREHE_90GRAD_RECHTS;
	}
	
	/**Drehe exakt 90 Grad nach links auf Linie*/
	public void drehe90GradLinks()
	{
		zustandBewegung = ZustandBewegung.DREHE_90GRAD_LINKS;
	}
	
	/**drehe Ungenau in Grad (Gegenuhrzeigersinn positiv!)*/
	public void dreheUngenau(int winkel)
	{
		zustandBewegung = ZustandBewegung.DREHE_UNGENAU;
	}
	
	public void fahreFreiVorwaerts(int speed)
	{
		
	}
	
	public void fahreFreiRueckwaerts(int speed)
	{
		
	}
	
	
	// Prüfe ob der Roboter noch eine Bewegung durchfuehrt
	public boolean istInBewegung()
	{
		return inBewegung;
	}
	
	/**
	 * update wird zyklisch aufgerufen
	 * 
	 * Stoppt die Bewegung des Roboters sobald Zielposition erreicht wurde  
	 */
	public void update()
	{
		
	}
	
	

}
