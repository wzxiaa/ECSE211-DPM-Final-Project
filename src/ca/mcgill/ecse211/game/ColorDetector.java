
package ca.mcgill.ecse211.game;

import java.util.Arrays;

import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import ca.mcgill.ecse211.odometer.*;
import ca.mcgill.ecse211.threads.SensorData;

/**
 * This class implements the color detection functionality of the robot
 * including the rotation of the elbowMotor and the foreArm moto
 * 
 * @author Ajay Patel
 * @author Fandi Yi
 * @author Lucas Bellido
 * @author Tianzhu Fu
 * @author Nicolas Abdelnour
 * @author Wenzong Xia
 * 
 */

public class ColorDetector {

	static EV3LargeRegulatedMotor leftMotor;
	static EV3LargeRegulatedMotor rightMotor;
	private static NXTRegulatedMotor elbowMotor;
	private static EV3MediumRegulatedMotor foreArmMotor;
	private SensorData rgbData;
	private TextLCD display = LocalEV3.get().getTextLCD();

	
	private int foreArmSpeed = 150;
	private int elbowMotorRotationSpeed = 20;

	private Color currentColor;

	// average NORMALIZED values of RGB for each ring. Data was collected by the
	// test engineer.
	// all color data to be used in the class are NORMALIZED

	private double mBlueR = 0.1732410055;
	private double mBlueG = 0.6778531281;
	private double mBlueB = 0.7144947101;
	private double mGreenR = 0.4777487339;
	private double mGreenG = 0.8592604804;
	private double mGreenB = 0.1828320925;
	private double mYellowR = 0.8541708187;
	private double mYellowG = 0.5005476676;
	private double mYellowB = 0.140869603;
	private double mOrangeR = 0.9547663589;
	private double mOrangeG = 0.2766071505;
	private double mOrangeB = 0.1091314998;
	private double mEmptyR = 0.1345000000;
	private double mEmptyB = 0.0855000000;
	private double mEmptyG = 0.0122500000;

	private float R, G, B;
	private double nR, nG, nB;
	private double dBlue, dGreen, dYellow, dOrange, dEmpty;

	public enum Color {
		Blue, Green, Yellow, Orange, Other
	}

	/*
	 * Calculations explanation: 1- Calculate mean normalized RGB data for each ring
	 * 2- Collect RGB color sample using the sample. 3- Normalize the collected
	 * sample. 4- Calculate the deviation from the mean data for each ring. 5- The
	 * ring with the smallest deviation is the found color.
	 */

	public ColorDetector(EV3LargeRegulatedMotor leftMotor, EV3LargeRegulatedMotor rightMotor,
			NXTRegulatedMotor elbowMotor, EV3MediumRegulatedMotor foreArmMotor) throws OdometerExceptions {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		this.elbowMotor = elbowMotor;
		this.foreArmMotor = foreArmMotor;
		this.rgbData = SensorData.getSensorData();
	}

	public static void rotateToScan() {

	}

	public static void rotateToGrab() {

	}

	public static void rotateSensor() {

	}

	private static int convertDistance(double radius, double distance) {
		return (int) ((180.0 * distance) / (Math.PI * radius));
	}

	private static int convertAngle(double radius, double width, double angle) {
		return convertDistance(radius, Math.PI * width * angle / 360.0);
	}

	public void detectRing() {

	}

	public void ringDetection() {
		leftMotor.rotate(convertDistance(Game.WHEEL_RAD, 19.05), true);
		rightMotor.rotate(convertDistance(Game.WHEEL_RAD, 19.05), false);
	}

	public void getColor(float r, float g, float b) {

		// return currentColor;
	}

	public void detectColor() {
		while (true) {
			display.clear();
			R = rgbData.getRGB()[0];
			G = rgbData.getRGB()[1];
			B = rgbData.getRGB()[2];

			display.drawString("0: " + R, 0, 1);
			display.drawString("1: " + G, 0, 2);
			display.drawString("2: " + B, 0, 3);
			// display.drawString("Co: " + currentBrightness, 0, 4);
			getColor(R,G,B);
			
			// normalize
			nR = R / (Math.sqrt(R * R + G * G + B * B));
			nG = G / (Math.sqrt(R * R + G * G + B * B));
			nB = B / (Math.sqrt(R * R + G * G + B * B));
			
			//calculate deviation for each ring
			dBlue = Math.sqrt(Math.pow(nR - mBlueR, 2) + Math.pow(nG - mBlueG, 2) + Math.pow(nB - mBlueB, 2));
			dGreen = Math.sqrt(Math.pow(nR - mGreenR, 2) + Math.pow(nG - mGreenG, 2) + Math.pow(nB - mGreenB, 2));
			dYellow = Math.sqrt(Math.pow(nR - mYellowR, 2) + Math.pow(nG - mYellowG, 2) + Math.pow(nB - mYellowB, 2));
			dOrange = Math.sqrt(Math.pow(nR - mOrangeR, 2) + Math.pow(nG - mOrangeG, 2) + Math.pow(nB - mOrangeB, 2));
			dEmpty = Math.sqrt(Math.pow(nR - mEmptyR, 2) + Math.pow(nG - mEmptyG, 2) + Math.pow(nB - mEmptyB, 2));
			
			double[] list = {dBlue, dGreen, dYellow, dOrange, dEmpty};

			//sorted array
			Arrays.sort(list);

			//print list[0] which is going to the detected color
			if(list[0] == dBlue) {
				display.drawString("Blue detected", 0, 5);
				Sound.beep();
				break;
			}
			
			if(list[0] == dGreen) {
				display.drawString("Green detected", 0, 5);
				Sound.beep();
				Sound.beep();
				break;
			}

			if(list[0] == dYellow) {
				display.drawString("Yellow detected", 0, 5);
				Sound.beep();
				Sound.beep();
				Sound.beep();
				break;
			}

			if(list[0] == dOrange) {
				display.drawString("Orange detected", 0, 5);
				Sound.beep();
				Sound.beep();
				Sound.beep();
				Sound.beep();
				break;
			}
			
			if (list[0] == dEmpty) {
				display.drawString("Empty", 0, 5);
				Sound.buzz();
				rotateSensor();
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			display.clear();
		}	
	}
	
	
	public void scanUpperRing() {
		foreArmMotor.setSpeed(70);
		elbowMotor.setSpeed(50);
		elbowMotor.rotate(-145);
		foreArmMotor.rotate(-25);
		detectColor();
		foreArmMotor.rotate(25);
		elbowMotor.rotate(145);
		foreArmMotor.stop();
		elbowMotor.stop();
	}
	
	public void ringScanTest() {
		foreArmMotor.setSpeed(70);
		elbowMotor.setSpeed(50);
		elbowMotor.rotate(-142);
		foreArmMotor.rotate(-25);
	}

}