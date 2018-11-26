package ca.mcgill.ecse211.game;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.NXTRegulatedMotor;

/**
 * This class contains methods for the robot to perform ring retrieval by using the 
 * elbowMotor and the foreArm motor. 
 * 
 * @author Ajay Patel
 * @author Fandi Yi
 * @author Lucas Bellido
 * @author Tianzhu Fu
 * @author Nicolas Abdelnour
 * @author Wenzong Xia
 *
 */
public class RingRetrieval {
	
	private EV3LargeRegulatedMotor leftMotor;
	private EV3LargeRegulatedMotor rightMotor;
	private NXTRegulatedMotor elbowMotor;
	private EV3MediumRegulatedMotor foreArmMotor;
	
	private int foreArmSpeed = 150;
	private int elbowMotorRotationSpeed = 50;
	private int sidesOfTree = 4;	// this is the variable for how many sides of tree to check
	
	/**
	 * This RingRetrieval class constructor sets up the robot to perform ring retrieval
	 * 
	 * @param leftMotor
	 * @param rightMotor
	 * @param elbowMotor
	 * @param foreArmMotor
	 */
	public RingRetrieval(EV3LargeRegulatedMotor leftMotor, EV3LargeRegulatedMotor rightMotor, NXTRegulatedMotor elbowMotor, EV3MediumRegulatedMotor foreArmMotor) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		this.elbowMotor = elbowMotor;
		this.foreArmMotor = foreArmMotor;
	}

	/**
	 * This method makes the robot to grab the upper ring
	 */
	public void grabUpperRing() {
		foreArmMotor.setSpeed(foreArmSpeed);
		elbowMotor.setSpeed(elbowMotorRotationSpeed);
		foreArmMotor.rotate(30);
		elbowMotor.rotate(-113);//105
		foreArmMotor.rotate(-70);
		foreArmMotor.rotate(40);	
		//elbowMotor.rotate(-105);//105
	}
	
	/**
	 * This method makes the robot to grab the lower ring
	 */
	public void grabLowerRing() {
		leftMotor.rotate(-convertDistance(Game.WHEEL_RAD, 2), true);
		rightMotor.rotate(-convertDistance(Game.WHEEL_RAD, 2), false);
		foreArmMotor.setSpeed(250);
		elbowMotor.setSpeed(elbowMotorRotationSpeed);
		foreArmMotor.rotate(30);
		elbowMotor.rotate(-34);
		foreArmMotor.rotate(-55);
		// after the foreArm is attached to the lower ring, the robot moves back for 10cm to drag the ring off the rack
		//leftMotor.rotate(-convertDistance(Game.WHEEL_RAD, 10), true);
		//rightMotor.rotate(-convertDistance(Game.WHEEL_RAD,10), false);
		foreArmMotor.rotate(45);
		elbowMotor.rotate(147);
		foreArmMotor.rotate(-20);
	}	
	
	
	/**
	 * This method is the wrapper method to grab both the upper ring and lower ring
	 */
	public void grabUpperAndLowerRing() {
		grabUpperRing();
		grabLowerRing();
		elbowMotor.stop();
		foreArmMotor.stop();
		
	}
	
	public void dropRings() {
		elbowMotor.setSpeed(100);
		foreArmMotor.setSpeed(80);
		
		// drop upper ring
		elbowMotor.rotate(-45);
		foreArmMotor.rotate(-20);
		elbowMotor.rotate(-90);
		
		foreArmMotor.rotate(20);
		elbowMotor.rotate(135);
		
		// drop Lower ring
		//elbowMotor.rotate(10);
		
		elbowMotor.rotate(-130);
		foreArmMotor.rotate(-70);
		elbowMotor.rotate(-10);
		foreArmMotor.rotate(180);
		
		foreArmMotor.rotate(-110);
		elbowMotor.rotate(137);
	}
	
	
	
	/**
	 * This method enables the robot to grab for both the upper ring and lower ring for each side of the tree (4 sides)
	 */
	public void grabRingForEachSide(int numOfSides) {
		
		
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
