package ca.mcgill.ecse211.game;

import java.util.Map;

import ca.mcgill.ecse211.WiFiClient.WifiConnection;
import ca.mcgill.ecse211.localization.LightLocalizer;
import ca.mcgill.ecse211.localization.UltrasonicLocalizer;
import ca.mcgill.ecse211.odometer.Odometer;
import ca.mcgill.ecse211.odometer.OdometerExceptions;
import ca.mcgill.ecse211.threads.LightPoller;
import ca.mcgill.ecse211.threads.RGBPoller;
import ca.mcgill.ecse211.threads.SensorData;
import ca.mcgill.ecse211.threads.ThreadControl;
import ca.mcgill.ecse211.threads.UltrasonicPoller;
import ca.mcgill.ecse211.wifi.WiFi;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

/**
 * This the main class for the ring game. It sets up the wifi connection for
 * parameters and starts the game accordingly.
 * 
 * @author Ajay Patel
 * @author Fandi Yi
 * @author Lucas Bellido
 * @author Tianzhu Fu
 * @author Nicolas Abdelnour
 * @author Wenzong Xia
 *
 */

public class RingGame {

	// ** Set these as appropriate for your team and current situation **
	private static final String SERVER_IP = "192.168.2.2";
	private static final int TEAM_NUMBER = 14;

	// Enable/disable printing of debug info from the WiFi class
	private static final boolean ENABLE_DEBUG_WIFI_PRINT = true;
	private static WifiConnection conn;

	/**
	 * This variable stores a ThreadController instance that controls our ultrasonic
	 * sensor
	 */
	public static ThreadControl usPoller;

	/**
	 * This variable stores a ThreadController instance that controls our RGB sensor
	 */
	private static ThreadControl rgbPoller;

	/**
	 * This variable stores a ThreadController instance that controls our light
	 * sensor
	 */
	private static ThreadControl lightPoller;

	public static void main(String[] args) {
		/*
		WifiConnection conn = new WifiConnection(SERVER_IP, TEAM_NUMBER, ENABLE_DEBUG_WIFI_PRINT);
		try {
			Map data = conn.getData();

			int greenTeam = ((Long) data.get("GreenTeam")).intValue();

			int redTeam = ((Long) data.get("GreenTeam")).intValue();

			if (greenTeam != -1) {
				
			} else if (redTeam != -1) {
				
			}

			int greenCorner = ((Long) data.get("GreenCorner")).intValue();
			GameParameter.GreenCorner = greenCorner;

			int greenLowerLeftX = ((Long) data.get("Green_LL_x")).intValue();
			GameParameter.Green_LL[0] = greenLowerLeftX;
			int greenLowerLeftY = ((Long) data.get("Green_LL_y")).intValue();
			GameParameter.Green_LL[1] = greenLowerLeftY;

			int greenUpperRightX = ((Long) data.get("Green_UR_x")).intValue();
			GameParameter.Green_UR[0] = greenUpperRightX;
			int greenUpperRightY = ((Long) data.get("Green_UR_y")).intValue();
			GameParameter.Green_UR[1] = greenUpperRightY;

			int islandLowerLeftX = ((Long) data.get("Island_LL_x")).intValue();
			GameParameter.Island_LL[0] = islandLowerLeftX;
			int islandLowerLeftY = ((Long) data.get("Island_LL_y")).intValue();
			GameParameter.Island_LL[1] = islandLowerLeftY;

			int islandUpperRightX = ((Long) data.get("Island_UR_x")).intValue();
			GameParameter.Island_UR[0] = islandUpperRightX;
			int islandUpperRightY = ((Long) data.get("Island_UR_y")).intValue();
			GameParameter.Island_UR[1] = islandUpperRightY;

			int upperRightCornerGreenTunnelX = ((Long) data.get("TNG_UR_x")).intValue();
			GameParameter.TNG_RR[0] = upperRightCornerGreenTunnelX;
			int upperRightCornerGreenTunnelY = ((Long) data.get("TNG_UR_y")).intValue();
			GameParameter.TNG_RR[1] = upperRightCornerGreenTunnelY;

			int lowerLeftCornerGreenTunnelX = ((Long) data.get("TNG_LL_x")).intValue();
			GameParameter.TNG_LL[0] = lowerLeftCornerGreenTunnelX;
			int lowerLeftCornerGreenTunnelY = ((Long) data.get("TNG_LL_y")).intValue();
			GameParameter.TNG_LL[1] = lowerLeftCornerGreenTunnelY;

			int greenRingSetX = ((Long) data.get("TG_x")).intValue();
			GameParameter.TG[0] = greenRingSetX;
			int greenRingSetY = ((Long) data.get("TG_y")).intValue();
			GameParameter.TG[1] = greenRingSetY;

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
*/
		// Setting up the game
		try {

			test.loadTestCase3();

			preparation();

			// test.loadTestCase2();
			// test.loadTestCase3();
			// test.loadTestCase4();
			// test.loadTestCase5();
			// test.loadTestCase6();
			// test.loadTestCase7();
			// test.loadTestCase8();

			runGame();
			//
			// runTest(test.testType.RingRetrievalTest); // LocalizationTest,
			// NavigationToTunnelTest, NavigationThroughTunnelTest, NaviagtionToRingSetTest,
			// RingColorDetectionTest, RingRetrievalTest
		} catch (OdometerExceptions e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method sets up ports for the two light sensors at the back and the light
	 * sensor at the front for color detection. Ï
	 * 
	 * @throws OdometerExceptions
	 */
	public static void preparation() throws OdometerExceptions {
		// Motor Objects, and Robot related parameters
		Port usPort = LocalEV3.get().getPort("S2");
		// initialize multiple light ports in main
		Port[] lgPorts = new Port[3];
		// Light sensor sensor stuff
		lgPorts[0] = LocalEV3.get().getPort("S4"); // S4
		lgPorts[1] = LocalEV3.get().getPort("S3"); // s3
		EV3ColorSensor[] lgSensors = new EV3ColorSensor[2];
		for (int i = 0; i < lgSensors.length; i++) {
			lgSensors[i] = new EV3ColorSensor(lgPorts[i]);
		}
		Odometer odometer = Odometer.getOdometer(Game.leftMotor, Game.rightMotor, Game.TRACK, Game.WHEEL_RAD);
		// Sensor Related Stuff
		SensorData sensorData = SensorData.getSensorData();
		// Ultrasonic sensor stuff
		@SuppressWarnings("resource")
		SensorModes usSensor = new EV3UltrasonicSensor(usPort);
		SampleProvider usDistance = usSensor.getMode("Distance");
		float[] usData = new float[usDistance.sampleSize()];
		SampleProvider backLight[] = new SampleProvider[2];
		backLight[0] = lgSensors[0].getRedMode();
		backLight[1] = lgSensors[1].getRedMode();
		TextLCD lcd = LocalEV3.get().getTextLCD();
		lcd.clear();

		Thread odoThread = new Thread(odometer);
		odoThread.start();

		usPoller = new UltrasonicPoller(usDistance, usData, sensorData);
		Thread usThread = new Thread(usPoller);
		usThread.start();
		lightPoller = new LightPoller(backLight, new float[2][backLight[1].sampleSize()], sensorData);
		Thread lightThread = new Thread(lightPoller);
		lightThread.start();

		// set uo the light sensor for color detection
		Port rgbPort = LocalEV3.get().getPort("S1");
		EV3ColorSensor rgbSensor = new EV3ColorSensor(rgbPort);
		SampleProvider frontlight[] = new SampleProvider[1];
		frontlight[0] = rgbSensor.getRGBMode();
		rgbPoller = new RGBPoller(frontlight, new float[2][frontlight[0].sampleSize()], sensorData);
		Thread rgbThread = new Thread(rgbPoller);
		rgbThread.start();

		// setting up the coordinates for the starting corner
		GameParameter.generateStartingCorner();

	}

	/**
	 * This method is to set up various components of the robot and is called after
	 * the games prepared
	 * 
	 * @throws OdometerExceptions
	 */
	public static void runGame() throws OdometerExceptions {
		// Start localizing
		final Navigation navigation = new Navigation(Game.leftMotor, Game.rightMotor);
		final UltrasonicLocalizer usLoc = new UltrasonicLocalizer(navigation, Game.leftMotor, Game.rightMotor);
		final LightLocalizer lgLoc = new LightLocalizer(navigation, Game.leftMotor, Game.rightMotor);
		final RingRetrieval ringRetrieval = new RingRetrieval(Game.leftMotor, Game.rightMotor, Game.elbowMotor,
				Game.foreArmMotor);
		final ColorDetector colorDetector = new ColorDetector(Game.leftMotor, Game.rightMotor, Game.elbowMotor,
				Game.foreArmMotor);
		// final RingSearcher searcher = new RingSearcher(storageMotor, rodMotor);
		// spawn a new Thread to avoid localization from blocking
		(new Thread() {
			public void run() {

				// perform ultrasonic localization
				usLoc.localize();
				// perform light localization
				lgLoc.localize(GameParameter.SC); // navigate to the tunnel entrance
				navigation.goToTunnel(GameParameter.TNG_LL, GameParameter.TNG_RR);
				// go through the tunnel
				navigation.goThroughTunnel(GameParameter.TNG_LL, GameParameter.TNG_RR); // navigate to the ring set (two
																						// tiles away from the ring set)
				// go to the ring set
				navigation.goToRingSet(GameParameter.TG);
				// perform ring retrieval
				detectAndGrabRing(navigation, ringRetrieval, colorDetector);
				// navigate back to the starting point
				navigation.moveBackToStartingPoint(GameParameter.TNG_LL, GameParameter.TNG_RR);
				// drop the ring
				ringRetrieval.dropRings();

			}
		}).start();
	}

	/**
	 * This method runs the component testing based on the type of test user selects
	 * 
	 * @param testType
	 * @throws OdometerExceptions
	 */
	public static void runTest(test.testType testType) throws OdometerExceptions {
		test tests = new test();
		switch (testType) {
		case LocalizationTest:
			tests.localizationTest();
		case NavigationToTunnelTest:
			tests.NavigationToTunnelTest();
		case RingRetrievalTest:
			tests.RingRetrievalTest();
		case RingColorDetectionTest:
			tests.RingColorDetectionTest();
		case RingDrop:
			tests.RingDrop();
		}
	}

	/**
	 * This method performs ring detection and ring retrieval for each side of the
	 * ring set.
	 * 
	 * @param nav
	 * @param rr
	 * @param cd
	 */
	public static void detectAndGrabRing(Navigation nav, RingRetrieval rr, ColorDetector cd) {

		// check for the side where the robot approaches the ring set
		nav.approachRingSetForColorDetection();

		cd.scanUpperRing();

		nav.approachRingSetForRingRetrieval();

		rr.grabUpperAndLowerRing();

		nav.backOffFromTree();

		/////////////////////////////

		for (int i = 0; i < 2; i++) {

			nav.moveToNextSideOfTree();

			cd.scanUpperRing();

			nav.approachRingSetForRingRetrieval();

			rr.grabUpperAndLowerRing();

			nav.backOffFromTree();

		}

		nav.exitRingSearch();

	}
}
