package team02.vorlagen;

import team02.IO;
import team02.Systeme;

public class Pos_Wechsel_V2 {
	
	private static Zustand zustand = Zustand.Standby;
	private static boolean ersteFahrt = true;
	
    private enum Zustand
    {   
    	Pos0toA,	//fahre von Startposition(erste Linie) zu Position A (an Wand)
        PosAtoB,	// Fahre von Position A zu Position B
        PosBtoC, 	// Fahre von Position B zu Position C
        PosCtoD, 	// Fahre von Position C zu Position D
        
        StartWeg1, 	// Starte Fahrt Weg1 ( von PosA zu PosB oder von PosC zu PosD)  
        StartWeg2, 	// Starte Fahrt Weg2 ( von PosB zu PosC)
        
        NachGegner11, NachGegner12, NachGegner13,					// Zustand NACH dem passieren einer Gegnerposition (Gegner von Weg1)
        NachGegner24, NachGegner23, NachGegner22, NachGegner21,		// Zustand NACH dem passieren einer Gegnerposition (Gegner von Weg2)
        
        AnPosB, AnPosC, AnPosD,								// Zielposition erreicht (ACHTUNG: Roboter kann noch in einer Drehbewegung sein -> kein Stillstand garantiert )
        
        TraverseZuEbene2, TraverseZuEbene3,							// Ebene wird gewechselt
        
        Ebene2, Ebene3,												// Ebene erreicht
        
        FahreZuWandEbene2, FahreZuWandEbene3,						// Fahrt rueckwaerts bis zur Wand 
        WandEbene2, WandEbene3,										// Wand erreicht (Fahre zu Wand_Abstand)
        WandAbstandEbene2, WandAbstandEbene3,						// Wand_Abstand erreicht 

        Standby,													// Warten auf Befehle
        Fehler;														// Ein Fehler ist aufgetreten
    }
    
    private static double Distanz_Wand_Abstand 	= 0.015;    
    
    private static double Distanz_G1 		= 0.080; 
    private static double Distanz_G1_kurz 	= Distanz_G1-Distanz_Wand_Abstand; 
    private static double Distanz_G2 		= 0.168;
    private static double Distanz_G3 		= 0.140;
    private static double Distanz_G4 		= 0.120;
    private static double Distanz_Linie 	= 0.260;
    
    /**
     * Fahre autonom zu WurfPosition1
     */
    public static void fahre_zu_Pos1()
    {
    	ersteFahrt = true;
        zustand = Zustand.Pos0toA;
    }
    
    /**
     * Fahre autonom zu WurfPosition2
     */
    public static void fahre_zu_Pos2()
    {
    	ersteFahrt = false;
    	zustand = Zustand.PosBtoC;
    }
    
    /**
     *  Wurde WurfPosition1 erreicht?
     * return WurfPosition1 erreicht
     */
    public static boolean pos1_erreicht()
    {
        return (zustand == Zustand.AnPosB)&&(!Systeme.bewegungsSystem.istInBewegung());
    }
    
    /**
     *  Wurde WurfPosition2 erreicht?
     * return WurfPosition2 erreicht
     */
    public static boolean pos2_erreicht()
    {
        return (zustand == Zustand.AnPosD)&&(!Systeme.bewegungsSystem.istInBewegung());
    }
    
	/**
	 * Update Methode
	 * Fuehrt die naechste Bewegungsaktion aus sofern sich der Roboter im Stillstand befindet
	 */
    public static void update()
    {
    	//Zum Schrittweise debuggen
    	//if(zustand == Zustand.NachGegner12) continue;
    	
    	//Wenn PosC erreicht fahre automatisch weiter zu PosD 
    	if(zustand== Zustand.AnPosC)
    	{
    		zustand = Zustand.PosCtoD;
    	}
    	
    	if (!Systeme.bewegungsSystem.istInBewegung()) bewege();
    }    
    
    /**
     *  Schrittkette um die naechste Bewegungsaktion des Roboters auszufuehren
     * (Darf nur aufgerufen werden falls sich der Roboter im Stillstand befindet)
     */
    public static void bewege()
    {
    	//IO.debug.print("Pos_Wechsel_V2:Zustand: " ); IO.debug.println(zustand.ordinal()); 
    	switch(zustand)
        {
			case Pos0toA:	// Fahre von Position 0 zu Position A
			{
				Systeme.bewegungsSystem.folgeLinieBisWandRueckwaerts();
				zustand = Zustand.PosAtoB;
				break;
			}
    		case PosAtoB:	// Fahre von Position A zu Position B
    		{
    			zustand = Zustand.StartWeg1;
    			break;
    		}
    		case PosBtoC:	// Fahre von Position B zu Position C
    		{
    			Systeme.bewegungsSystem.drehe90GradUZ();
    			zustand = Zustand.StartWeg2;
    			break;
    		}
    		case PosCtoD:	// Fahre von Position C zu Position D
    		{
    			zustand = Zustand.StartWeg1;
    			break;
    		}
    	
            case StartWeg1: // Starte Fahrt Weg1 ( von PosA zu PosB oder von PosC zu PosD)
            {	
	            Systeme.gegnerSystem.resetGegnerErkennung();
	            Systeme.bewegungsSystem.folgeLinieBisDistanzUngebremst(true, Distanz_G1);
            	zustand = Zustand.NachGegner11;
            	break;
            } 			
            case NachGegner11: // Zustand NACH dem passieren von Gegner11 (Gegner von Weg1)
            {
                if (Systeme.gegnerSystem.warGegnerRechts())
                {
                    Systeme.bewegungsSystem.folgeLinieBisDistanzUngebremst(true, Distanz_G2);
                    zustand = Zustand.NachGegner12;
                }
                else
                {
                    Systeme.bewegungsSystem.drehe90GradUZ();
                    zustand = Zustand.TraverseZuEbene2;
                }
                Systeme.gegnerSystem.resetGegnerErkennung();
                break;
            }
            case NachGegner12: // Zustand NACH dem passieren von Gegner12 (Gegner von Weg1)
            {
                if (Systeme.gegnerSystem.warGegnerRechts())
                {
                    Systeme.bewegungsSystem.folgeLinieBisDistanz(true, Distanz_G3);
                    zustand = Zustand.NachGegner13;
                }
                else
                {
                    Systeme.bewegungsSystem.drehe90GradUZ();
                    zustand = Zustand.TraverseZuEbene2;
                }
                Systeme.gegnerSystem.resetGegnerErkennung();
                break;
            }   
            case NachGegner13: // Zustand NACH dem passieren von Gegner13 (Gegner von Weg1)
            {
                if (Systeme.gegnerSystem.warGegnerRechts())
                {
                	IO.debug.println("Error: Gegner 13 gesehen");
                    //zustand = Zustand.Fehler;
                	
                	Systeme.bewegungsSystem.drehe90GradUZ();
                	zustand = Zustand.TraverseZuEbene2;
                }
                else
                {
                    Systeme.bewegungsSystem.drehe90GradUZ();
                    zustand = Zustand.TraverseZuEbene2;
                }
                Systeme.gegnerSystem.resetGegnerErkennung();
                break;
            }
            case TraverseZuEbene2: // wechsle zu Ebene2
            {
            	Systeme.bewegungsSystem.fahreFreiBisDistanz(true,Distanz_Linie);
            	zustand = Zustand.Ebene2;
            	break;
            }
            case Ebene2: // Ebene2 erreicht
            {
                Systeme.bewegungsSystem.drehe90GradUZ();
                zustand = Zustand.FahreZuWandEbene2;
                break;
            }
            case FahreZuWandEbene2: // Fahrt rueckwaerts bis zur Wand auf Ebene2
            {
                Systeme.bewegungsSystem.folgeLinieBisWandRueckwaerts();
                zustand = Zustand.WandEbene2;
                break;
            }
            case WandEbene2: // Wand Ebene2 erreicht (Fahre zu Wand_Abstand)
            {
                Systeme.bewegungsSystem.fahreFreiBisDistanz(true, Distanz_Wand_Abstand);
                zustand = Zustand.WandAbstandEbene2;
                break;
            }
            case WandAbstandEbene2: // Ebene2 Wand_Abstand erreicht 
            {
            	if(ersteFahrt) Systeme.bewegungsSystem.drehe90GradGUZ();
                if(ersteFahrt) zustand = Zustand.AnPosB; else zustand = Zustand.AnPosD;
                break;
            } 
            case StartWeg2:	// Starte Fahrt Weg2 ( von PosB zu PosC)		
            {	
	            Systeme.gegnerSystem.resetGegnerErkennung();
	            Systeme.bewegungsSystem.folgeLinieBisDistanzUngebremst(true, Distanz_G1_kurz);
            	zustand = Zustand.NachGegner24;
            	break;
            }
            case NachGegner24: // Zustand NACH dem passieren von Gegner24 (Gegner von Weg2)
            {
            	//Wir muessen zwingend weiterfahren da Partnerroboter im Weg steht
                Systeme.bewegungsSystem.folgeLinieBisDistanzUngebremst(true, Distanz_G2);
                zustand = Zustand.NachGegner23;
                Systeme.gegnerSystem.resetGegnerErkennung();
                break;
            }
            case NachGegner23: // Zustand NACH dem passieren von Gegner23 (Gegner von Weg2)
            {
	            if (Systeme.gegnerSystem.warGegnerLinks())
	            {
	                Systeme.bewegungsSystem.folgeLinieBisDistanzUngebremst(true, Distanz_G3);
	                zustand = Zustand.NachGegner22;
	            }
	            else
	            {
	                Systeme.bewegungsSystem.drehe90GradGUZ();
	                zustand = Zustand.TraverseZuEbene3;
	            }
	            Systeme.gegnerSystem.resetGegnerErkennung();
	            break;
            }
            case NachGegner22: // Zustand NACH dem passieren von Gegner22 (Gegner von Weg2)
            {
	            if (Systeme.gegnerSystem.warGegnerLinks())
	            {
	                Systeme.bewegungsSystem.folgeLinieBisDistanz(true, Distanz_G4);
	                zustand = Zustand.NachGegner21;
	            }
	            else
	            {
	                Systeme.bewegungsSystem.drehe90GradGUZ();
	                zustand = Zustand.TraverseZuEbene3;
	            }
	            Systeme.gegnerSystem.resetGegnerErkennung();
	            break;
            }
            case NachGegner21: // Zustand NACH dem passieren von Gegner21 (Gegner von Weg2)
            {
	            if (Systeme.gegnerSystem.warGegnerLinks())
	            {
                	//IO.debug.println("Error: Gegner 21 gesehen");
                    //zustand = Zustand.Fehler;
	                Systeme.bewegungsSystem.drehe90GradGUZ();
	                zustand = Zustand.TraverseZuEbene3;
	            }
	            else
	            {
	                Systeme.bewegungsSystem.drehe90GradGUZ();
	                zustand = Zustand.TraverseZuEbene3;
	            }
	            Systeme.gegnerSystem.resetGegnerErkennung();
	            break;
            }
            case TraverseZuEbene3: // wechsle zu Ebene3
            {
            	Systeme.bewegungsSystem.fahreFreiBisDistanz(true,Distanz_Linie);
            	zustand = Zustand.Ebene3;
            	break;
            }
            case Ebene3: // Ebene3 erreicht
            {
                Systeme.bewegungsSystem.drehe90GradGUZ();
                zustand = Zustand.FahreZuWandEbene3;
                break;
            }
            case FahreZuWandEbene3: // Fahrt rueckwaerts bis zur Wand auf Ebene3
            {
                Systeme.bewegungsSystem.folgeLinieBisWandRueckwaerts();
                zustand = Zustand.WandEbene3;
                break;
            }
            
            case WandEbene3: // Wand Ebene3 erreicht (Fahre zu Wand_Abstand)
            {
                zustand = Zustand.WandAbstandEbene3;
                break;
            }
            case WandAbstandEbene3: // Ebene2 Wand_Abstand erreicht 
            {
            	zustand = Zustand.AnPosC;
                break;
            }          
            
            default:
            {
                break;
            }
        }
    }

}
