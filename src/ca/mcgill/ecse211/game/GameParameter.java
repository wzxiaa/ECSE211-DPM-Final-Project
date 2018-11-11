package ca.mcgill.ecse211.game;

/**
 * This class contains all the game parameter for the competition
 *
 */
public enum GameParameter {
	INSTANCE;
	
	public enum TunnelHeading {NORTH, SOUTH, EAST, WEST}

	public static int RedTeam = -1;

	public static int GreenTeam = -1;

	public static int RedCorner = -1;

	public static int GreenCorner = -1;
	
	/*
	 * Red island
	 */
	public static int[] Red_LL = new int[2];

	public static int[] Red_UR = new int[2];
	
	/*
	 * Red tunnel
	 */
	public static int[] TNR_LL_RED = new int[2];
	
	public static int[] TNR_UR_RED = new int[2];

	/*
	 * Green island
	 */
	public static int[] Green_LL = { 0 , 0 };

	public static int[] Green_UR = { 4 , 4 };
	
	/*
	 * Island 
	 */
	public static int[] Island_LL = new int[2];

	public static int[] Island_UR = new int[2];

	/*
	 * Green tunnel
	 */
	public static int[] TNG_LL;

	public static int[] TNG_RR;
	

	public static int[] SC = new int[3];
	
	/*
	 * Ring set
	 */
	public static int[] TR = new int[2];
	
	public static int[] TG = new int[2];
	
	public static double startingHeading;

	public static void generateStartingCorner() {
		if (GreenTeam > 0) {
			if (GreenCorner == 0) {
				SC[0] = 1;
				SC[1] = 1;
				SC[2] = 0;
			} else if (GreenCorner == 1) {
				SC[0] = Red_UR[0] - 1;
				SC[1] = 1;
				SC[2] = 270;
			} else if (GreenCorner == 2) {
				SC[0] = Red_UR[0] - 1;
				SC[1] = Red_UR[1] - 1;
				SC[2] = 180;
			} else if (GreenCorner == 3) {
				SC[0] = 1;
				SC[1] = Red_UR[1] - 1;
				SC[2] = 90;
			}
		}
	}	
	
	public static TunnelHeading determineTunnelHeading(int GreenCorner, int[] TNG_LL, int[] TNG_UR) {
		if(GreenCorner == 0) {
			if(TNG_UR[1] == GameParameter.Island_LL[1]) {
				return TunnelHeading.NORTH;
			} else if (TNG_UR[0] == GameParameter.Island_LL[0]) {
				
			}
		}
		return null;
	}
	
	
}
