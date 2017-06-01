package com.kumail.mayday;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.test.IsolatedContext;
import android.test.mock.MockContext;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @RunWith(AndroidJUnit4.class)
    public class ExampleInstrumentedTest {
        @Test
        public void useAppContext() throws Exception {
            // Context of the app under test.
            Context appContext = InstrumentationRegistry.getTargetContext();

            Assert.assertEquals("com.kumail.mayday", appContext.getPackageName());
        }
    }

    private SensorManager sensorManager;
    private float x,y,z;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 7000;

    @Test
    public void onSensorChanged(){

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    return;
                }

                last_x = x;
                last_y = y;
                last_z = z;
                assertTrue(speed < SHAKE_THRESHOLD);
            }
        }



    public void onSensorChangedEvent(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    return;
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }

    }

    @RunWith(AndroidJUnit4.class)
    public class InstrumentationTestCaseTest extends InstrumentationTestCase {

        Context context;

        public void setUp() throws Exception {
            super.setUp();

            context = new MockContext();

            assertNotNull(context);

        }
        @Test
        public void testSomething() {
            assertTrue(AccelerometerManager.isSupported(context));
        }

    }


}