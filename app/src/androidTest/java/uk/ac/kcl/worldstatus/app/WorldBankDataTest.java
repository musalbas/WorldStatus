package uk.ac.kcl.worldstatus.app;

import junit.framework.Assert;
import junit.framework.TestCase;

public class WorldBankDataTest extends TestCase {

    public void testGetIndicatorData() throws Exception {
        WorldBankData.getIndicatorData("USA", "AG.LND.ARBL.ZS", 1960, 2014);
        Assert.assertTrue("hi", true);
    }
}