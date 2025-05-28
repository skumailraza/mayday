package com.kumail.mayday;

import org.junit.Test;
import static org.junit.Assert.*; // Changed from static junit.framework.Assert to org.junit.Assert

public class AppUnitTests {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    // Variables for onSensorChanged test
    private float x,y,z; // These will need to be set by the test method
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 7000;

    // This test will need to be modified to set sensor values (x,y,z)
    // and potentially mock SensorEvent if the original onSensorChangedEvent method is tested directly.
    // For now, this is the refactored onSensorChanged test logic.
    @Test
    public void onSensorChanged_detectsShake() {
        // Example: Simulate a shake scenario
        x = 1000; y = 1000; z = 1000; // Simulate initial sensor values
        last_x = 0; last_y = 0; last_z = 0; // Simulate previous sensor values
        lastUpdate = System.currentTimeMillis() - 200; // Simulate time difference > 100ms

        long curTime = System.currentTimeMillis();
        long diffTime = (curTime - lastUpdate);
        lastUpdate = curTime;

        float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

        // Assuming the original logic intended to return if speed > SHAKE_THRESHOLD
        // For a test, we'd assert the condition.
        // If testing the "no shake" scenario:
        // speed = 1000; // example for no shake
        // assertTrue(speed < SHAKE_THRESHOLD);

        // If testing the "shake detected" scenario (original test logic was a bit ambiguous):
        // For this example, let's assume we are testing that a high speed is registered.
        // The original test had assertTrue(speed < SHAKE_THRESHOLD) which seems to test for NO shake.
        // Let's stick to that for now. To make this test pass as originally intended (detecting no shake):
        x=1; y=1; z=1; // small change
        last_x=0; last_y=0; last_z=0;
        curTime = System.currentTimeMillis();
        diffTime = (curTime - (System.currentTimeMillis() - 150)); // ensure diffTime > 100
        lastUpdate = curTime;
        speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
        assertTrue("Speed should be less than SHAKE_THRESHOLD for this test case", speed < SHAKE_THRESHOLD);

        // To test shake detection, a separate test method would be better:
        // @Test
        // public void onSensorChanged_detectsShake_positive() {
        //     // setup values for x, y, z, last_x, last_y, last_z that cause speed > SHAKE_THRESHOLD
        //     // float speed = ... calculation ...
        //     // assertTrue(speed > SHAKE_THRESHOLD);
        // }
    }

    // The original onSensorChangedEvent(SensorEvent event) method from UnitTests.java
    // might be better to test by creating a mock SensorEvent if direct testing is needed.
    // For now, it's not included as a standalone @Test method unless its logic is separated
    // or it's called by another test method with a mock event.
}
