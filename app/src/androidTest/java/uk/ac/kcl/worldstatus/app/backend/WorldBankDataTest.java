package uk.ac.kcl.worldstatus.app.backend;

import android.util.Log;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;

import static uk.ac.kcl.worldstatus.app.backend.WorldBankData.findCountry;

public class WorldBankDataTest extends TestCase {

    public void testFindCountry() throws Exception {
        // use 2012
        // SL.UEM.TOTL.ZS - Unemployment
        // SP.URB.TOTL.ZS - Urban population
        // IC.TAX.TOTL.CP.ZS - Tax Rate
        // AG.LND.FRST.ZS - Forest Area
        // FP.CPI.TOTL.ZG - Inflation
        // SE.SEC.ENRR - Secondary school
        // EG.USE.COMM.FO.ZS - Fossil fuel
        // GC.XPN.TOTL.GD.ZS - Expense

        // dummy test map
        HashMap<String, Integer> testerMap = new HashMap<String, Integer>();
        testerMap.put("SL.UEM.TOTL.ZS", 1);
        testerMap.put("SP.URB.TOTL.ZS", 2);
        testerMap.put("IC.TAX.TOTL.CP.ZS", 3);
        testerMap.put("AG.LND.FRST.ZS", 3);
        testerMap.put("GC.XPN.TOTL.GD.ZS", 3);

        LegacyDataGrabber lDG = findCountry(testerMap); // this returns a LegacyDataGrabber obj.

        assertEquals("Congo, Dem. Rep.", lDG.getName());
    }

}