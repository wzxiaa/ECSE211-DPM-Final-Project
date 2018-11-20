package ca.mcgill.ecse211.game;

import ca.mcgill.ecse211.localization.LightLocalizer;
import ca.mcgill.ecse211.localization.UltrasonicLocalizer;
import ca.mcgill.ecse211.odometer.OdometerExceptions;

/**
 * This class is used to run tests for different components of the robot
 * 
 * @author Ajay Patel
 * @author Fandi Yi
 * @author Lucas Bellido
 * @author Tianzhu Fu
 * @author Nicolas Abdelnour
 * @author Wenzong Xia
 *
 */
public class test {

	public enum testType {
		LocalizationTest, NavigationToTunnelTest, NavigationThroughTunnelTest, NaviagtionToRingSetTest,
		RingColorDetectionTest, RingRetrievalTest;
	}

	/**
	 * This is constructor for the test class
	 * 
	 * @param type the type of test
	 */
	public test() {

	}

	public void localizationTest() throws OdometerExceptions {
		Navigation navigation = new Navigation(Game.leftMotor, Game.rightMotor);
		LightLocalizer lgLoc = new LightLocalizer(navigation, Game.leftMotor, Game.rightMotor);
		UltrasonicLocalizer usLoc = new UltrasonicLocalizer(navigation, Game.leftMotor, Game.rightMotor);
		usLoc.localize();
		lgLoc.localize(GameParameter.SC);
	}

	public void NavigationToTunnelTest() throws OdometerExceptions {
		Navigation navigation = new Navigation(Game.leftMotor, Game.rightMotor);
		LightLocalizer lgLoc = new LightLocalizer(navigation, Game.leftMotor, Game.rightMotor);
		UltrasonicLocalizer usLoc = new UltrasonicLocalizer(navigation, Game.leftMotor, Game.rightMotor);
		usLoc.localize();
		lgLoc.localize(GameParameter.SC);
		navigation.goToTunnel(GameParameter.Green_LL, GameParameter.Green_UR, GameParameter.startingCorner);
	}

	public void RingRetrievalTest() throws OdometerExceptions {
		Navigation navigation = new Navigation(Game.leftMotor, Game.rightMotor);
		RingRetrieval ringRetrieval = new RingRetrieval(Game.leftMotor, Game.rightMotor, Game.elbowMotor,
				Game.foreArmMotor);
		navigation.approachRingSetForColorDetection();
		navigation.approachRingSetForRingRetrieval(); // move 1.5cm
		ringRetrieval.grabUpperRing();
		ringRetrieval.grabLowerRing();
	}
	
	public void RingColorDetectionTest() throws OdometerExceptions {
		Navigation navigation = new Navigation(Game.leftMotor, Game.rightMotor);
		RingRetrieval ringRetrieval = new RingRetrieval(Game.leftMotor, Game.rightMotor, Game.elbowMotor,
				Game.foreArmMotor);
		ColorDetector colorDetector = new ColorDetector(Game.leftMotor, Game.rightMotor, Game.elbowMotor,
				Game.foreArmMotor);
		navigation.approachRingSetForColorDetection();
		colorDetector.ringScanTest();
		while(true) {
			colorDetector.detectColor();
		}
	}
}
