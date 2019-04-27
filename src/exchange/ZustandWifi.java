package exchange;

public interface ZustandWifi 
{
    int WURF_BEREIT 			=	0;
    int FANG_BEREIT				=	1;
    int FAHREN					=	2;
    int ERROR					=	3;
    int SETUP 					=  99;
    int NO_ROUTER_CONNECTION 	=  -1;
    int HEARTBEAT 				=  -2;
    int TEST 					=  42;
    int TEST_PASSED 			=  43;
    
    //Demo Wifi-Zustaende
    int DEMO_WARTEN		 		= 100;
    int DEMO_ZYLINDER_SPANNEN	= 101;
    int DEMO_FAHREN_WEIT 		= 102;
    int DEMO_DREHEN_90_GUZ 		= 103;
    int DEMO_FAHREN_KURZ 		= 104;
    int DEMO_FAHREN_WAND		= 105;
    int DEMO_WERFEN				= 106;

    
}
