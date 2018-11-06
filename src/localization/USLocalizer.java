package localization;
import odometer.Odometer;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;

/**
 * Performs heading localization using the ultrasonic sensor and the walls of the field.
 * @author Wenzong
 * @author Lucas
 */
public class USLocalizer {
	public static int rotateSpeed = 50;
	private Odometer odometer;
	private EV3LargeRegulatedMotor leftMotor, rightMotor;
	final TextLCD display = LocalEV3.get().getTextLCD();
	
	// constants
	private int filterControl = 0;
	private static final int FILTER_OUT = 35;
	private int d = 40;	// drop-off point
	private int k = 1;	// error margin
	
	// variables
	private int distance;
	
	  /**
	   * This is the class constructor for a class that helps to localize our robot with an ultrasonic
	   * sensor
	   * 
	   * @param leftMotor A EV3LargeRegularedMotor object instance that allows control of the left motor
	   * @param rightMotor A EV3LargeRegularedMotor object instance that allows control of the right
	   *        motor
	   * @param odometer A odometer instance
	   */
	public USLocalizer(EV3LargeRegulatedMotor leftMotor, EV3LargeRegulatedMotor rightMotor, 
			Odometer odometer) {
		this.odometer = odometer;
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
	}
	
	
	/**
	 * This is the method to perform ultrasonic localization using falling edge when the robot's initial
	 * is facing away from the wall
	 */
	public void fallingEdge() {
		/*
		double alpha, beta = 0;
		//set the speed and acceleration
		this.leftMotor.setSpeed(rotateSpeed);
		this.rightMotor.setSpeed(rotateSpeed);
		this.leftMotor.setAcceleration(500);
		this.rightMotor.setAcceleration(500);
		
		// avoid finding falling edge too early
		leftMotor.rotate(convertAngle(GameParamter.wheelRadius, GameParameter.axleWidth, 45), true);
		rightMotor.rotate(-convertAngle(GameParameter.wheelRadius, Lab5.axleWidth, 45), false);
		
		// rotate clockwise
		this.leftMotor.forward();
		this.rightMotor.backward();
		
		// keep turning until wall drops away
		while (this.distance >= d - k) {
			 //keep turning
		}
		
		alpha = this.odometer.getTheta();
		this.leftMotor.setSpeed(0);
		this.rightMotor.setSpeed(0);
		Sound.beep();
		
		this.leftMotor.setSpeed(rotateSpeed);
		this.rightMotor.setSpeed(rotateSpeed);
		
		// rotate in opposite direction to avoid finding falling edge too early
		leftMotor.rotate(-convertAngle(Lab5.wheelRadius, Lab5.axleWidth, 45), true);
		rightMotor.rotate(convertAngle(Lab5.wheelRadius, Lab5.axleWidth, 45), false);
		
		// turn counterclockwise until next falling edge
		this.leftMotor.backward();
		this.rightMotor.forward();
		
		while (this.distance >= d - k) {
			// keep turning
		}
		
		beta = this.odometer.getTheta();
		this.leftMotor.setSpeed(0);
		this.rightMotor.setSpeed(0);
		Sound.beep();
		
		this.leftMotor.setSpeed(rotateSpeed);
		this.rightMotor.setSpeed(rotateSpeed);
		
		this.odometer.setTheta(0);
		this.localiseAngle(alpha, beta);
		Sound.beep();
		*/
	}

	
	/**
	 * This is the method to perform ultrasonic localization using rising edge when the robot's initial
	 * is facing towards the wall
	 */
	public void risingEdge() {
		/*
		double alpha, beta = 0;
		//set the speed and acceleration
		this.leftMotor.setSpeed(rotateSpeed);
		this.rightMotor.setSpeed(rotateSpeed);
		this.leftMotor.setAcceleration(500);
		this.rightMotor.setAcceleration(500);
		
		//rotate clockwise for 45 degree to avoid detecting the wall too early
		leftMotor.rotate(convertAngle(Lab5.wheelRadius, Lab5.axleWidth, 45), true);
		rightMotor.rotate(-convertAngle(Lab5.wheelRadius, Lab5.axleWidth, 45), false);
		
		// rotate clockwise
		this.leftMotor.forward();
		this.rightMotor.backward();
		
		// keep turning until wall drops away
		while (this.distance <= d + k) {
			 //keep turning
		}
		//record the heading when the first rising edge is detected
		alpha = this.odometer.getTheta();
		this.leftMotor.setSpeed(0);
		this.rightMotor.setSpeed(0);
		Sound.beep();
		
		this.leftMotor.setSpeed(rotateSpeed);
		this.rightMotor.setSpeed(rotateSpeed);
		
		// avoid finding rising edge too early
		leftMotor.rotate(-convertAngle(Lab5.wheelRadius, Lab5.axleWidth, 45), true);
		rightMotor.rotate(convertAngle(Lab5.wheelRadius, Lab5.axleWidth, 45), false);
		
		// turn counterclockwise until next rising edge
		this.leftMotor.backward();
		this.rightMotor.forward();
		
		while (this.distance <= d + k) {
			// keep turning
		}
		beta = this.odometer.getTheta();
		this.leftMotor.setSpeed(0);
		this.rightMotor.setSpeed(0);
		Sound.beep();
		
		this.leftMotor.setSpeed(rotateSpeed);
		this.rightMotor.setSpeed(rotateSpeed);
		
		// rotate to 0deg via short path
		this.localiseAngle(alpha, beta);
		this.odometer.setTheta(0);
		Sound.beep();
		*/
	}
	
	/**
	 * This method is to localize the robot according to the angles get from the odometer when the two falling edge/
	 * rising edge is detected and it calculated the angle the robot needs to turn to. 
	 * @param angle1
	 * @param angle2
	 */
	public void localiseAngle(double angle1, double angle2) {
		/*
		double deltaTh = 0;
		double[] odometer = { 0, 0, 0 };

		if (angle1 < angle2) {
			deltaTh = 45 - (angle1 + angle2) / 2;
		} else {
			deltaTh = 225 - (angle1 + angle2) / 2;
		}
		double heading = this.odometer.getTheta();
		
		deltaTh += heading;
		
		if (deltaTh >= 360) {
			deltaTh -= 360;
		}
		// Turn left
		this.leftMotor.rotate(convertAngle(Lab5.wheelRadius, Lab5.axleWidth, deltaTh), true);
		this.rightMotor.rotate(-convertAngle(Lab5.wheelRadius, Lab5.axleWidth, deltaTh), false);

		display.drawString("deltaTheta: " + deltaTh, 0, 6);
		*/
	}
	
	
	/**
	 * Turns robot the specified amount.
	 * 
	 * @param theta - number of radians to turn. theta > 0 => clockwise, theta < 0 => counterclockwise
	 */
	public void turnTo(double theta) {		
		/*
		//convert to degrees
		theta = Math.toDegrees(theta);
		
		//turn to calculated angle
		int rotation = convertAngle(Lab5.wheelRadius, Lab5.axleWidth, theta);
		
		// rotate the appropriate direction (sign on theta accounts for direction)
		this.leftMotor.rotate(rotation, true);
		this.rightMotor.rotate(-rotation, false);
		
		this.leftMotor.stop();
		this.rightMotor.stop();
		*/
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
