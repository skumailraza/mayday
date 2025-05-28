# Mayday: Emergency Detection and Notification App

Mayday is an Android application designed to assist you in critical situations. When activated, it automatically pinpoints your location and dispatches emergency SMS notifications to your pre-selected contacts.

## Key Features

*   **Emergency Activation:** Users can trigger emergency alerts through a designated action within the app. (This is a generic description as the exact mechanism isn't specified).
*   **Location Sharing:** Automatically retrieves your current GPS or network-based location for inclusion in emergency messages.
*   **SMS Notifications:** Sends SMS alerts to your designated emergency contacts.
*   **User Registration & Verification:** Enables users to register and verify their information for dependable emergency communication.
*   **Background Service:** A background service may be used to monitor for emergency triggers or ensure the app's readiness. (Based on the assumption that `BgService` might be for this purpose).
*   **In-App Guidance:** Offers instructions on setting up and utilizing the application effectively.

## How to Use

1.  **Register:** Launch the app and register your details, including your emergency contacts.
2.  **Verification:** Verify your information as prompted by the app.
3.  **Set Up (if applicable):** Review the in-app instructions for any specific setup required for emergency detection or triggers.
4.  **Trigger Alert:** In an emergency, activate the Mayday alert (describe how, e.g., by pressing a button in the app, or if there are other mechanisms).
5.  **Notification:** The app will then send an SMS with your location to your registered emergency contacts.

## Building from Source

This project is a standard Android application and uses Gradle for building.

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-repository-url/mayday.git # Replace with the actual URL
    cd mayday
    ```
2.  **Import into Android Studio:**
    - Open Android Studio.
    - Click on "Open an existing Android Studio project".
    - Navigate to the cloned `mayday` directory and select it.
    - Android Studio will handle the rest of the setup (downloading Gradle, dependencies, etc.).
3.  **Build the project:**
    - Once imported, you can build the project using the "Build" menu in Android Studio (e.g., "Make Project" or "Build Bundle(s) / APK(s)").
    - Alternatively, you can build from the command line using Gradle wrapper:
      ```bash
      ./gradlew assembleDebug # For a debug build
      ```
      or
      ```bash
      ./gradlew assembleRelease # For a release build
      ```
    (Note: Release builds will require setting up signing configurations.)

## Running Tests

This project includes unit tests and instrumented tests.

**1. Using Android Studio:**
   - **Unit Tests (located in `Mayday/src/test/java`):**
     - Right-click on the test file (e.g., `AppUnitTests.java`) or a specific test class/method in the Project view.
     - Select "Run 'TestName'".
     - Results will appear in the Run window.
   - **Instrumented Tests (located in `Mayday/src/androidTest/java`):**
     - Ensure you have an Android device or emulator connected and configured.
     - Right-click on the test file (e.g., `AppInstrumentedTests.java`) or a specific test class/method.
     - Select "Run 'TestName'".
     - Results will appear in the Run window.

**2. Using Gradle commands from the terminal:**
   - Navigate to the root directory of the project (where `gradlew` is located).
   - **To run unit tests:**
     ```bash
     ./gradlew testDebugUnitTest
     ```
     (Or `./gradlew test` if you only have one build variant or want to run for all variants).
     Test reports are usually generated in `Mayday/build/reports/tests/testDebugUnitTest/`.
   - **To run instrumented tests:**
     ```bash
     ./gradlew connectedDebugAndroidTest
     ```
     (Or `./gradlew connectedAndroidTest` to run for all variants). This requires a connected device or running emulator.
     Test reports are usually generated in `Mayday/build/reports/androidTests/connected/`.

## Permissions Used

This app requests the following permissions to function correctly:

*   **`android.permission.ACCESS_FINE_LOCATION`**:
    *   **Purpose:** To access your precise geographical location (GPS and network-based).
    *   **Why it's needed:** Essential for providing accurate location information to your emergency contacts during an emergency.
*   **`android.permission.INTERNET`**:
    *   **Purpose:** To allow the app to access the internet.
    *   **Why it's needed:** May be used for location services (e.g., network-based location, geocoding), future features, or communication with a backend service (if any).
*   **`android.permission.SEND_SMS`**:
    *   **Purpose:** To allow the app to send SMS messages.
    *   **Why it's needed:** This is the core mechanism for notifying your emergency contacts.
*   **`android.permission.READ_PHONE_STATE`**:
    *   **Purpose:** To access phone state, including phone number, current cellular network information, the status of any ongoing calls, and a list of any PhoneAccounts registered on the device.
    *   **Why it's needed:** This might be used to detect specific triggers (e.g., if emergency calls are made) or to ensure the app functions correctly during calls or network changes. (The exact use case might need further clarification from the source code if a more specific reason is desired).

## Contributing

Contributions are welcome! If you'd like to improve Mayday, please follow these steps:

1.  **Fork the repository.**
2.  **Create a new branch** for your feature or bug fix:
    ```bash
    git checkout -b feature/your-feature-name
    ```
    or
    ```bash
    git checkout -b fix/your-bug-fix-name
    ```
3.  **Make your changes.**
4.  **Commit your changes** with a clear and descriptive commit message.
5.  **Push your branch** to your forked repository.
6.  **Create a Pull Request** to the main Mayday repository.

Please ensure your code follows the existing style and that you test your changes thoroughly.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for full details.
