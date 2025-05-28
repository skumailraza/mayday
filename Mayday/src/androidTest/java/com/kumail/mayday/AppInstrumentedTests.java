package com.kumail.mayday;

import android.content.Context;
import android.hardware.Sensor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
// Import AndroidJUnit4 if you are using AndroidX Test libraries,
// otherwise, stick to android.support.test.runner.AndroidJUnit4
// For this project, based on build.gradle, it's the support library version.

import org.junit.Assert; // Standard JUnit assert
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*; // Standard JUnit asserts

// Removed AndroidTestCase and InstrumentationTestCase as direct base classes,
// using @RunWith(AndroidJUnit4.class) and standard test annotations.

@RunWith(AndroidJUnit4.class)
public class AppInstrumentedTests {

    private Context appContext;

    @Before
    public void setup() {
        appContext = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void useAppContext() throws Exception {
        Assert.assertEquals("com.kumail.mayday", appContext.getPackageName());
    }

    // This test is for AccelerometerManager.isSupported
    // It needs a Context. A MockContext might not be sufficient if the underlying
    // service call (getSystemService) needs a more complete Context.
    // InstrumentationRegistry.getTargetContext() is generally preferred for instrumented tests.
    @Test
    public void accelerometerManager_isSupported() {
        // The original test used a MockContext.
        // Using appContext from InstrumentationRegistry for better fidelity in instrumented tests.
        assertTrue(AccelerometerManager.isSupported(appContext));
    }

    // This test is for GPS functionality
    // The original test extended AndroidTestCase.
    @Test
    public void gpsTracker_functionalityTest() {
        LocationManager locationManager = (LocationManager) appContext.getSystemService(Context.LOCATION_SERVICE);
        assertNotNull("LocationManager should not be null", locationManager);

        // It's good practice to ensure the provider isn't null before using it.
        if (locationManager.getProvider("Test") != null) {
            locationManager.removeTestProvider("Test");
        }
        
        locationManager.addTestProvider("Test", false, false, false, false, false, false, false, Criteria.POWER_LOW, Criteria.ACCURACY_FINE);
        locationManager.setTestProviderEnabled("Test", true);

        Location location = new Location("Test");
        location.setLatitude(10.0);
        location.setLongitude(20.0);
        location.setTime(System.currentTimeMillis()); // It's good to set time
        location.setAccuracy(Criteria.ACCURACY_FINE); // And accuracy
        // For API level 17+ (which this project's compileSdkVersion 19 supports)
        // setting elapsedRealtimeNanos is also good.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            location.setElapsedRealtimeNanos(System.nanoTime());
        }


        locationManager.setTestProviderLocation("Test", location);

        // To actually test GPSTracker, you would typically instantiate your GPSTracker class here
        // and have it use this LocationManager (perhaps via dependency injection or a setter).
        // Then you would verify that your GPSTracker class receives and processes this test location.
        // For now, this test just sets up the test provider and location.
        // Example:
        // GPSTracker gpsTracker = new GPSTracker(appContext); // Assuming constructor takes context
        // Location fetchedLocation = gpsTracker.getLocation(); // Assuming getLocation triggers an update or returns last known
        // assertNotNull(fetchedLocation);
        // assertEquals(10.0, fetchedLocation.getLatitude(), 0.001);
        // assertEquals(20.0, fetchedLocation.getLongitude(), 0.001);


        // Clean up the test provider
        locationManager.removeTestProvider("Test");
    }

    // The original onSensorChangedEvent(SensorEvent event) from UnitTests.java
    // is not directly moved here as a @Test method.
    // If its functionality, tied to AccelerometerListener, needs instrumented testing,
    // a specific test involving AccelerometerListener and mock/real SensorEvents would be created.
}
