package tda367.paybike.model;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
        /**
        * Instrumented test, which will execute on an Android device.
        *
        * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
        */
public class PositionTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("tda367.paybike", appContext.getPackageName());

    }

}