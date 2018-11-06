package navigation;


public class Navigation extends Thread {

	public Navigation(Odometer odometer, EV3LargeRegulatedMotor leftMotor, EV3LargeRegulatedMotor rightMotor) {

	}

	// This method causes the robot to travel to the absolute field location (x, y),
	// specified in tile points
	public static void travelTo(double x, double y) {

	}

	// This method causes the robot to turn (on point) to the absolute heading theta
	public static void turnTo(double theta) {

	}

	public void run() {

	}

	// This method returns true if another thread has called travelTo() or turnTo()
	// and the method has yet to return; false otherwise
	// static boolean navigating = false;

	private static boolean isNavigating() {

	}

	/**
	 * This method allows the conversion of a distance to the total rotation of each
	 * wheel need to cover that distance.
	 * 
	 * @param radius
	 * @param distance
	 * @return
	 */
	
	public static int convertDistance(double radius, double distance) {
		return (int) ((180.0 * distance) / (Math.PI * radius));
	}

	private static int convertAngle(double radius, double width, double angle) {
		return convertDistance(radius, Math.PI * width * angle / 360.0);
	}

}
