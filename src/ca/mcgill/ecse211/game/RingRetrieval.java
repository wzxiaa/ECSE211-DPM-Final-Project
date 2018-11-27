package ca.mcgill.ecse211.game;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.NXTRegulatedMotor;

/**
 * This class contains methods for the robot to perform ring retrieval by using
 * the elbowMotor and the foreArm motor.
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

	private int ACCELERATION = 3000;
	private int foreArmSpeed = 60;
	private int elbowMotorRotationSpeed = 40;
	private int sidesOfTree = 4; // this is the variable for how many sides of tree to check
	private int FORWARD_SPEED = 200;

	/**
	 * This RingRetrieval class constructor sets up the robot to perform ring
	 * retrieval
	 * 
	 * @param leftMotor
	 * @param rightMotor
	 * @param elbowMotor
	 * @param foreArmMotor
	 */
	public RingRetrieval(EV3LargeRegulatedMotor leftMotor, EV3LargeRegulatedMotor rightMotor,
			NXTRegulatedMotor elbowMotor, EV3MediumRegulatedMotor foreArmMotor) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		this.elbowMotor = elbowMotor;
		this.foreArmMotor = foreArmMotor;
		elbowMotor.setAcceleration(ACCELERATION);
		foreArmMotor.setAcceleration(ACCELERATION);
	}

	/**
	 * This method makes the robot to grab the upper ring
	 */
	public void grabUpperRing() {
		foreArmMotor.setSpeed(foreArmSpeed);
		elbowMotor.setSpeed(elbowMotorRotationSpeed);
		foreArmMotor.rotate(30);
		elbowMotor.rotate(-113);// 105
		foreArmMotor.rotate(-70);
		foreArmMotor.rotate(40);	
		// elbowMotor.rotate(-105);//105
	}

	/**
	 * This method makes the robot to grab the lower ring
	 */
	public void grabLowerRing() {
		//leftMotor.rotate(-convertDistance(Game.WHEEL_RAD, 1), true);
		//rightMotor.rotate(-convertDistance(Game.WHEEL_RAD, 1), false);
		foreArmMotor.setSpeed(foreArmSpeed);
		elbowMotor.setSpeed(elbowMotorRotationSpeed);
		foreArmMotor.rotate(30);
		elbowMotor.rotate(-33);
		foreArmMotor.rotate(-60);
		// after the foreArm is attached to the lower ring, the robot moves back for
		// 10cm to drag the ring off the rack
		// leftMotor.rotate(-convertDistance(Game.WHEEL_RAD, 10), true);
		// rightMotor.rotate(-convertDistance(Game.WHEEL_RAD,10), false);
		foreArmMotor.rotate(60);
		elbowMotor.rotate(146);
		foreArmMotor.rotate(-30);
		

	}

	/**
	 * This method is the wrapper method to grab both the upper ring and lower ring
	 */
	public void grabUpperAndLowerRing() {
		armMotorReset();

		leftMotor.stop();
		rightMotor.stop();
		leftMotor.setSpeed(FORWARD_SPEED);
		rightMotor.setSpeed(FORWARD_SPEED);
		leftMotor.setAcceleration(ACCELERATION);
		rightMotor.setAcceleration(ACCELERATION);

		grabUpperRing();
		grabLowerRing();

		elbowMotor.stop();
		foreArmMotor.stop();

	}

	public void protectRing() {
		//leftMotor.rotate(-convertDistance(Game.WHEEL_RAD, 1), true);
		//rightMotor.rotate(-convertDistance(Game.WHEEL_RAD, 1), false);
		foreArmMotor.setSpeed(250);
		elbowMotor.setSpeed(elbowMotorRotationSpeed);
		foreArmMotor.rotate(30);
		elbowMotor.rotate(-130);
		foreArmMotor.rotate(-45);
		// after the foreArm is attached to the lower ring, the robot moves back for
		// 10cm to drag the ring off the rack
		// leftMotor.rotate(-convertDistance(Game.WHEEL_RAD, 10), true);
		// rightMotor.rotate(-convertDistance(Game.WHEEL_RAD,10), false);
	}
	
	public void unprotectRing() {
		armMotorReset();
		foreArmMotor.rotate(25);
		elbowMotor.rotate(130);

	}
	
	
	public void grabLastRing() {
		armMotorReset();
		grabUpperRing();
		
		foreArmMotor.setSpeed(foreArmSpeed);
		elbowMotor.setSpeed(elbowMotorRotationSpeed);
		foreArmMotor.rotate(30);
		elbowMotor.rotate(-34);
		foreArmMotor.rotate(-60);
	}
	
	
	public void dropRings() {
		
		elbowMotor.rotate(130);
		foreArmMotor.rotate(15);
		
		motorReset(leftMotor, rightMotor);
		
		leftMotor.setSpeed(400);
		rightMotor.setSpeed(400);
		leftMotor.rotate(convertAngle(Game.WHEEL_RAD, Game.TRACK, 30), true);
		rightMotor.rotate(-convertAngle(Game.WHEEL_RAD, Game.TRACK, 30), false);
		
		leftMotor.rotate(-convertAngle(Game.WHEEL_RAD, Game.TRACK, 30), true);
		rightMotor.rotate(convertAngle(Game.WHEEL_RAD, Game.TRACK, 30), false);
		
		leftMotor.rotate(convertAngle(Game.WHEEL_RAD, Game.TRACK, 30), true);
		rightMotor.rotate(-convertAngle(Game.WHEEL_RAD, Game.TRACK, 30), false);
		
		leftMotor.rotate(-convertAngle(Game.WHEEL_RAD, Game.TRACK, 30), true);
		rightMotor.rotate(convertAngle(Game.WHEEL_RAD, Game.TRACK, 30), false);
		
		/*
		armMotorReset();
		elbowMotor.setSpeed(100);
		foreArmMotor.setSpeed(80);

		// drop upper ring
		elbowMotor.rotate(-45);
		foreArmMotor.rotate(-20);
		elbowMotor.rotate(-90);

		foreArmMotor.rotate(20);
		elbowMotor.rotate(135);

		// drop Lower ring
		// elbowMotor.rotate(10);

		elbowMotor.rotate(-130);
		foreArmMotor.rotate(-70);
		elbowMotor.rotate(-10);
		foreArmMotor.rotate(180);

		foreArmMotor.rotate(-110);
		elbowMotor.rotate(137);
		*/
	}

	public void grabRingTest() {
		
		elbowMotor.setSpeed(elbowMotorRotationSpeed);
		foreArmMotor.setSpeed(foreArmSpeed);

		elbowMotor.resetTachoCount();
		foreArmMotor.resetTachoCount();

		foreArmMotor.rotateTo(30);
		elbowMotor.rotateTo(-113);
		foreArmMotor.rotateTo(-20);
		foreArmMotor.rotateTo(30);
		elbowMotor.rotateTo(0);
		foreArmMotor.rotateTo(0);

		elbowMotor.rotateTo(0);
	}

	/**
	 * This method resets the left and right motor of the robot to prevent it from crashing
	 * 
	 * @param leftMotor
	 * @param rightMotor
	 */
	public void motorReset(EV3LargeRegulatedMotor leftMotor, EV3LargeRegulatedMotor rightMotor) {
		// reset the motors
		for (EV3LargeRegulatedMotor motor : new EV3LargeRegulatedMotor[] { leftMotor, rightMotor }) {
			motor.stop();
			motor.setAcceleration(ACCELERATION);
		}
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void armMotorReset() {
		elbowMotor.stop();
		foreArmMotor.stop();
		elbowMotor.setAcceleration(ACCELERATION);
		foreArmMotor.setAcceleration(ACCELERATION);
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
