package team02.loris;

import team02.IO;
import team02.Systeme;

public class Pos_Wechsel_test {
	
	private static Zustand        zustand = Zustand.Standby;
	private static boolean ersteFahrt = true;
	
    private enum Zustand
    {
        Test,
        Start,
        
        PosAtoB,
        PosBtoC,
        PosCtoD,
        
        StartWeg1,
        StartWeg2,
        
        
        NachGegner11, NachGegner12, NachGegner13,
        NachGegner24, NachGegner23, NachGegner22, NachGegner21,
        
        AnPosB,
        AnPosC,
        AnPosD,
        
        Ebene4,
        Ebene3,
        Ebene2,
        Ebene1,
        
        Traverse3,
        Traverse2,
        Traverse1,
        
        FahreZuWandEbene2,
        FahreZuWandEbene3,
        WandEbene2,
        WandEbene3,

        Standby,
        Fehler;
        
    	/*
        private static Zustand[] vals = values();
        public Zustand next() { return vals[(this.ordinal()+1) % vals.length]; }
        */
    }
    
    private static double Distanz_G1 = 0.05;
    private static double Distanz_G2 = 0.168;
    private static double Distanz_G3 = 0.168;
    private static double Distanz_G4 = 0.05;
    private static double Distanz_Linie = 0.2;
    
    public static void fahre_zu_Pos1()
    {
    	ersteFahrt = true;
        zustand = Zustand.PosAtoB;
    }

    public static void fahre_zu_Pos2()
    {
    	ersteFahrt = false;
    	zustand = Zustand.PosBtoC;
    }

    public static boolean pos1_erreicht()
    {
        return (zustand == Zustand.AnPosB)&&(!Systeme.bewegungsSystem.istInBewegung());
    }

    public static boolean pos2_erreicht()
    {
        return (zustand == Zustand.AnPosD)&&(!Systeme.bewegungsSystem.istInBewegung());
    }
    
    
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
    
    public static void bewege()
    {
    	switch(zustand)
        {
    		case PosAtoB:
    		{
    			zustand = Zustand.StartWeg1;
    			break;
    		}
    		case PosBtoC:
    		{
    			Systeme.bewegungsSystem.drehe90GradUZ();
    			zustand = Zustand.StartWeg2;
    			break;
    		}
    		case PosCtoD:
    		{
    			zustand = Zustand.StartWeg1;
    			break;
    		}
    	
            case StartWeg1:
            {	
	            Systeme.gegnerSystem.resetGegnerErkennung();
	            Systeme.bewegungsSystem.fahreFreiBisDistanz(true, Distanz_G1);
            	zustand = Zustand.NachGegner11;
            	break;
            } 			
            case NachGegner11:
            {
                if (Systeme.gegnerSystem.warGegnerRechts())
                {
                    Systeme.bewegungsSystem.fahreFreiBisDistanz(true, Distanz_G2);
                    zustand = Zustand.NachGegner12;
                }
                else
                {
                    Systeme.bewegungsSystem.drehe90GradUZ();
                    zustand = Zustand.Traverse1;
                }
                Systeme.gegnerSystem.resetGegnerErkennung();
                break;
            }
            case NachGegner12:
            {
                if (Systeme.gegnerSystem.warGegnerRechts())
                {
                    Systeme.bewegungsSystem.fahreFreiBisDistanz(true, Distanz_G3);
                    zustand = Zustand.NachGegner13;
                }
                else
                {
                    Systeme.bewegungsSystem.drehe90GradUZ();
                    zustand = Zustand.Traverse1;
                }
                Systeme.gegnerSystem.resetGegnerErkennung();
                break;
            }   
            case NachGegner13:
            {
                if (Systeme.gegnerSystem.warGegnerRechts())
                {
                	IO.debug.println("Error: Gegner 13 gesehen");
                    zustand = Zustand.Fehler;
                }
                else
                {
                    Systeme.bewegungsSystem.drehe90GradUZ();
                    zustand = Zustand.Traverse1;
                }
                Systeme.gegnerSystem.resetGegnerErkennung();
                break;
            }
            case Traverse1:
            {
            	Systeme.bewegungsSystem.fahreFreiBisDistanz(false,Distanz_Linie);
            	zustand = Zustand.Ebene2;
            	break;
            }
            case Ebene2:
            {
                Systeme.bewegungsSystem.drehe90GradUZ();
                zustand = Zustand.FahreZuWandEbene2;
                break;
            }
            case FahreZuWandEbene2:
            {
                Systeme.bewegungsSystem.folgeLinieBisWandRueckwaerts();
                zustand = Zustand.WandEbene2;
                break;
            }
            case WandEbene2:
            {
                Systeme.bewegungsSystem.drehe90GradGUZ();
                if(ersteFahrt) zustand = Zustand.AnPosB; else zustand = Zustand.AnPosD;
                break;
            }
            case StartWeg2:
            {	
	            Systeme.gegnerSystem.resetGegnerErkennung();
	            Systeme.bewegungsSystem.fahreFreiBisDistanz(true, Distanz_G1);
            	zustand = Zustand.NachGegner24;
            	break;
            }
            case NachGegner24:
            {
            	//Wir muessen zwingend weiterfahren da Partnerroboter im Weg steht
                Systeme.bewegungsSystem.fahreFreiBisDistanz(true, Distanz_G2);
                zustand = Zustand.NachGegner23;
                Systeme.gegnerSystem.resetGegnerErkennung();
                break;
            }
            case NachGegner23:
            {
	            if (Systeme.gegnerSystem.warGegnerLinks())
	            {
	                Systeme.bewegungsSystem.fahreFreiBisDistanz(true, Distanz_G3);
	                zustand = Zustand.NachGegner22;
	            }
	            else
	            {
	                Systeme.bewegungsSystem.drehe90GradGUZ();
	                zustand = Zustand.Traverse2;
	            }
	            Systeme.gegnerSystem.resetGegnerErkennung();
	            break;
            }
            case NachGegner22:
            {
	            if (Systeme.gegnerSystem.warGegnerLinks())
	            {
	                Systeme.bewegungsSystem.fahreFreiBisDistanz(true, Distanz_G4);
	                zustand = Zustand.NachGegner21;
	            }
	            else
	            {
	                Systeme.bewegungsSystem.drehe90GradGUZ();
	                zustand = Zustand.Traverse2;
	            }
	            Systeme.gegnerSystem.resetGegnerErkennung();
	            break;
            }
            case NachGegner21:
            {
	            if (Systeme.gegnerSystem.warGegnerLinks())
	            {
                	IO.debug.println("Error: Gegner 21 gesehen");
                    zustand = Zustand.Fehler;
	            }
	            else
	            {
	                Systeme.bewegungsSystem.drehe90GradGUZ();
	                zustand = Zustand.Traverse2;
	            }
	            Systeme.gegnerSystem.resetGegnerErkennung();
	            break;
            }
            case Traverse2:
            {
            	Systeme.bewegungsSystem.fahreFreiBisDistanz(false,Distanz_Linie);
            	zustand = Zustand.Ebene3;
            	break;
            }
            case Ebene3:
            {
                Systeme.bewegungsSystem.drehe90GradUZ();
                zustand = Zustand.FahreZuWandEbene3;
                break;
            }
            case FahreZuWandEbene3:
            {
                Systeme.bewegungsSystem.folgeLinieBisWandRueckwaerts();
                zustand = Zustand.WandEbene3;
                break;
            }
            case WandEbene3:
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
