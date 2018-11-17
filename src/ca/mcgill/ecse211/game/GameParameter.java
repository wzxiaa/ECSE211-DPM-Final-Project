package ca.mcgill.ecse211.game;

import lejos.hardware.Sound;

/**
 * This class contains all the game parameter for the competition
 * 
 * @author Ajay Patel
 * @author Fandi Yi
 * @author Lucas Bellido
 * @author Tianzhu Fu
 * @author Nicolas Abdelnour
 * @author Wenzong Xia
 *
 */
public enum GameParameter {
	INSTANCE;

	// This is an enumeration class for the headings of the tunnel
	public enum TunnelHeading {
		NORTH, SOUTH, EAST, WEST
	}

	public static int RedTeam = -1;

	public static int GreenTeam = 14;

	public static int RedCorner = -1;

	public static int GreenCorner = 1;

	public static int[] Red_LL = new int[2];

	public static int[] Red_UR = new int[2];

	public static int[] TNR_LL = new int[2];

	public static int[] TNR_RR = new int[2];

	public static int[] Green_LL = { 2, 0 };

	public static int[] Green_UR = { 8, 3 };

	public static int[] IslandG_LL = { 0, 5 };

	public static int[] IslandG_UR = { 6, 8 };
	
	public static int[] IslandR_LL = { 0, 5 };

	public static int[] IslandR_UR = { 6, 8 };

	public static int[] TNG_LL = { 2, 3 };

	public static int[] TNG_RR = { 3, 5 };

	public static int[] SC = new int[3];

	public static int[] TR = new int[2];

	public static int[] TG = { 5, 7 };

	public static double startingHeading;
	
	// Parameters used by the methods after checking the team number
	public static int startingCorner = -1;
	
	public static int[] startingRegion_LL = new int[2];
	
	public static int[] startingRegion_UR = new int[2];
	
	public static int[] Tunnel_LL = new int[2];
	
	public static int[] Tunnel_UR = new int[2];
	
	public static int[] Island_LL = new int[2];
	
	public static int[] Island_UR = new int[2];
	
	public static int[] ringSet = new int[2];

	/**
	 * This method generate the starting coordinates of the robot after its
	 * localizations based on its starting corner
	 */
	public static void generateStartingCorner() {
		if (GreenTeam > 0) {
			if (GreenCorner == 0) {
				SC[0] = 1;
				SC[1] = 1;
				SC[2] = 0;
			} else if (GreenCorner == 1) {
				SC[0] = Green_UR[0] - 1;
				SC[1] = 1;
				SC[2] = 270;
			} else if (GreenCorner == 2) {
				SC[0] = Green_UR[0] - 1;
				SC[1] = Green_UR[1] - 1;
				SC[2] = 180;
			} else if (GreenCorner == 3) {
				SC[0] = 1;
				SC[1] = Green_UR[1] - 1;
				SC[2] = 90;
			}
		}
	}

	/**
	 * This method computes the heading of the tunnel based on the TNG_LL and TNG_UR
	 * 
	 * @param TNG_LL
	 * @param TNG_UR
	 * @return tunnelHeading
	 */
	public static TunnelHeading determineTunnelHeading(int[] TN_LL, int[] TN_UR) {
		if (GreenCorner == 0) {
			if (TN_UR[1] > Green_UR[1]) {
				return TunnelHeading.NORTH;
			} else {
				return TunnelHeading.EAST;
			}
		} else if (GreenCorner == 1) {
			if (TN_UR[1] > Green_UR[1]) {
				return TunnelHeading.NORTH;
			} else {
				return TunnelHeading.WEST;
			}
		} else if (GreenCorner == 2) {
			if (TNG_LL[1] < Green_LL[1]) {
				return TunnelHeading.SOUTH;
			} else {
				return TunnelHeading.WEST;
			}
		} else if (GreenCorner == 3) {
			if (TNG_LL[1] < Green_LL[1]) {
				return TunnelHeading.SOUTH;
			} else {
				return TunnelHeading.EAST;
			}
		}
		return null;
	}

}
