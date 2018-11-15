package ca.mcgill.ecse211.game;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class RingRetrieval {
	
	private EV3LargeRegulatedMotor leftMotor;
	private EV3LargeRegulatedMotor rightMotor;
	private NXTRegulatedMotor elbowMotor;
	private EV3MediumRegulatedMotor foreArmMotor;
	
	private int foreArmSpeed = 150;
	private int elbowMotorRotationSpeed = 20;
	
	public RingRetrieval(EV3LargeRegulatedMotor leftMotor, EV3LargeRegulatedMotor rightMotor, NXTRegulatedMotor elbowMotor, EV3MediumRegulatedMotor foreArmMotor) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		this.elbowMotor = elbowMotor;
		this.foreArmMotor = foreArmMotor;
	}

	
	public void grabUpperRing() {
		foreArmMotor.setSpeed(foreArmSpeed);
		elbowMotor.setSpeed(50);
		foreArmMotor.rotate(75);
<<<<<<< HEAD
		elbowMotor.rotate(-101);//105
=======
		elbowMotor.rotate(-105);//105
>>>>>>> 5b845a7412d0f0e6350078d48d5ea4885969fae7
		foreArmMotor.rotate(-115);
		foreArmMotor.rotate(40);
	}
	
	public void grabLowerRing() {
		foreArmMotor.setSpeed(250);
		elbowMotor.setSpeed(50);
		foreArmMotor.rotate(75);
		elbowMotor.rotate(-36);
		
		foreArmMotor.rotate(-100);
		
		leftMotor.rotate(-convertDistance(Game.WHEEL_RAD, 10), true);
		rightMotor.rotate(-convertDistance(Game.WHEEL_RAD,10), false);
		
		foreArmMotor.rotate(100);
		
		elbowMotor.rotate(137);
		foreArmMotor.rotate(-75);
	}	
	
	/**
	 * This method allows the conversion of a distance to the total rotation of each
	 * wheel need to cover that distance.
	 * 
	 * @param radius   The radius of our wheels
	 * @param distance The distance traveled
	 * @return A converted distance
	 */
	public static int convertDistance(double radius, double distance) {
		return (int) ((180.0 * distance) / (Math.PI * radius));
	}

	/**
	 * This method allows the conversion of an angle value
	 * 
	 * @param radius   The radius of our wheels
	 * @param distance The distance traveled
	 * @param angle    The angle to convert
	 * @return A converted angle
	 */
	private static int convertAngle(double radius, double width, double angle) {
		return convertDistance(radius, Math.PI * width * angle / 360.0);
	}
	
}
