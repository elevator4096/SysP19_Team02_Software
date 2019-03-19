package team02;

import team02.vorlagen.*;

import java.text.DecimalFormat;

public interface Systems
{
    BewegungsSystem bewegungsSystem = new BewegungsSystem();
    DebugSystem debugSystem = new DebugSystem();
    GegnerSystem gegnerSystem = new GegnerSystem();

    WlanSystem wlanSystem = new WlanSystem();
    WurfSystem wurfSystem = new WurfSystem();
}
