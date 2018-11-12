package ca.mcgill.ecse211.game;

import ca.mcgill.ecse211.odometer.Odometer;
import ca.mcgill.ecse211.odometer.OdometerExceptions;
import ca.mcgill.ecse211.threads.LightPoller;
import ca.mcgill.ecse211.threads.SensorData;
import ca.mcgill.ecse211.threads.ThreadControl;
import ca.mcgill.ecse211.threads.UltrasonicPoller;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public enum Game {
	INSTANCE;

	/**
	 * Motor object instance that allows control of the left motor connected to port
	 * D
	 */
	public static final EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("C"));
//c
	/**
	 * Motor object instance that allows control of the right motor connected to
	 * port C
	 */
	public static final EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("D"));
	
	/**
	 * 
	 */
	public static final NXTRegulatedMotor elbowMotor = new NXTRegulatedMotor(LocalEV3.get().getPort("B"));
	
	/**
	 * 
	 */
	public static final EV3MediumRegulatedMotor foreArmMotor = new EV3MediumRegulatedMotor(LocalEV3.get().getPort("A"));;
	
//d
	/**
	 * This variable stores the length of a tile in cm
	 */
	public static final double TILE = 30.48;

	/**
	 * This variable stores the radius of our wheels in cm
	 */
	public static final double WHEEL_RAD = 2.15;

	/**
	 * This variable holds the track distance between the center of the wheels in cm
	 * (measured and adjusted based on trial and error)
	 */
	public static final double TRACK = 11.5;

	/**
	 * This variable stores the distance between the light sensor and center of the
	 * robot in cm
	 */
	public static final double SEN_DIS = 10.5;


}
