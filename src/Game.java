
import lejos.hardware.*;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.*;
import lejos.robotics.SampleProvider;

public class Game {
	
	
	
	
	// setting up ports for motors
	//left C right D
	public static final EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("D"));
	public static final EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("C"));
	// setting up track length and wheel radius for the robot
	
	//A
	public static final NXTRegulatedMotor armMotor = new NXTRegulatedMotor(LocalEV3.get().getPort("A"));
	
	public static final double axleWidth = 11.8, wheelRadius = 2.2;
	public static final double TILE_SIZE = 31.48;
	
	
	public static void main(String[] args) {
		armMotor.setAcceleration(500);
		leftMotor.setAcceleration(500);
		rightMotor.setAcceleration(500);
		
		leftMotor.setSpeed(30);
		rightMotor.setSpeed(30);
		armMotor.setSpeed(20);	
		armMotor.rotateTo(-145);
		
		leftMotor.rotate(convertDistance(wheelRadius, 10), true);
		rightMotor.rotate(convertDistance(wheelRadius, 10), false);
		
		
		leftMotor.rotate(convertDistance(wheelRadius, 15), true);
		rightMotor.rotate(convertDistance(wheelRadius, 15), false);
		armMotor.rotateTo(-95);
		leftMotor.rotate(convertDistance(wheelRadius, -15), true);
		rightMotor.rotate(convertDistance(wheelRadius, -15), false);
		armMotor.rotateTo(-220);
		try {
			Thread.sleep(300);
		} catch (Exception e) {
			
		}
		leftMotor.rotate(convertDistance(wheelRadius, -10), true);
		rightMotor.rotate(convertDistance(wheelRadius, -10), false);
		armMotor.rotateTo(0);
		
	}
	
	
	
	/**
	 * Converts travel distance to number of degrees of rotation of robot's wheel.
	 * 
	 * @param radius - radius of robot's wheel (cm)
	 * @param distance - distance needed to be traveled (cm)
	 * @return number of degrees of wheel rotation (degrees)
	 */
	private static int convertDistance(double radius, double distance) {
		return (int) ((180.0 * distance) / (Math.PI * radius));
	}
	
	/**
	 * Converts robot rotation to number of degrees each wheel must turn (in opposite directions).
	 * 
	 * @param radius - robot wheel radius (cm)
	 * @param width - robot axle width (cm)
	 * @param angle - amount of robot rotation (radians)
	 * @return number of degrees of wheel rotation (degrees)
	 */
	private static int convertAngle(double radius, double width, double angle) {
		return convertDistance(radius, Math.PI * width * angle / 360.0);
	

	}
}
