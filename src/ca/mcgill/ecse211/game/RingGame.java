package ca.mcgill.ecse211.game;

import java.util.Map;

import ca.mcgill.ecse211.WiFiClient.WifiConnection;
import ca.mcgill.ecse211.localization.LightLocalizer;
import ca.mcgill.ecse211.localization.UltrasonicLocalizer;
import ca.mcgill.ecse211.odometer.Odometer;
import ca.mcgill.ecse211.odometer.OdometerExceptions;
import ca.mcgill.ecse211.threads.LightPoller;
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

public class RingGame {
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
		WiFi wifi = new WiFi();
		wifi.loadParameters();
		// Setting up the game
		try {
			preparation();
			runGame();
		} catch (OdometerExceptions e) {
			e.printStackTrace();
		}

	}

	public static void preparation() throws OdometerExceptions {
		// Motor Objects, and Robot related parameters
		Port usPort = LocalEV3.get().getPort("S1");
		// initialize multiple light ports in main
		Port[] lgPorts = new Port[3];

		// Light sensor sensor stuff
		lgPorts[0] = LocalEV3.get().getPort("S4");
		lgPorts[1] = LocalEV3.get().getPort("S3");
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
		Display display = new Display(lcd);
		// STEP 1: LOCALIZE to (1,1)
		// ButtonChoice left or right
		lcd.clear();
		lcd.drawString("<  Left  |  Right >", 0, 0);
		lcd.drawString(" falling | rising  ", 0, 1);
		lcd.drawString("  edge   |  edge   ", 0, 2);
		lcd.drawString("        \\/        ", 0, 3);

		// Start odometer and odometer display
		Thread odoThread = new Thread(odometer);
		odoThread.start();
		Thread odoDisplay = new Thread(display);
		odoDisplay.start();
		// Start ultrasonic and light sensors
		usPoller = new UltrasonicPoller(usDistance, usData, sensorData);
		Thread usThread = new Thread(usPoller);
		usThread.start();
		lightPoller = new LightPoller(backLight, new float[2][backLight[1].sampleSize()], sensorData);
		Thread lightThread = new Thread(lightPoller);
		lightThread.start();
		
		// setting up the coordinates for the starting corner
		GameParameter.generateStartingCorner();

		// Thread fLgPoller1 = new RGBPoller(frontLight, new
		// float[frontLight.sampleSize()],
		// sensorData);
	}

	/**
	 * This method is called when the after the robot has been prepared and is ready
	 * to compete
	 * 
	 * @throws OdometerExceptions
	 */
	public static void runGame() throws OdometerExceptions {
		final int buttonChoice = Button.waitForAnyPress(); // Record choice (left or right press)
		// Start localizing
		final Navigation navigation = new Navigation(Game.leftMotor, Game.rightMotor);
		final UltrasonicLocalizer usLoc = new UltrasonicLocalizer(navigation, Game.leftMotor, Game.rightMotor);
		final LightLocalizer lgLoc = new LightLocalizer(navigation, Game.leftMotor, Game.rightMotor);
		// final RingSearcher searcher = new RingSearcher(storageMotor, rodMotor);
		// spawn a new Thread to avoid localization from blocking
		(new Thread() {
			public void run() {
				usLoc.localize(buttonChoice);
				lgLoc.localize(GameParameter.SC);
				
				//navigation.goToTunnel(GameParameter.TNR_LL_RED, GameParameter.TNR_UR_RED);
				navigation.goThroughTunnel(GameParameter.TNR_LL_RED, GameParameter.TNR_UR_RED);
				Sound.beepSequence();
				//navigation.travelTo(GameParameter.TNR_UR_RED[0],GameParameter.TNR_UR_RED[0]);
			}
		}).start();
	}
}
