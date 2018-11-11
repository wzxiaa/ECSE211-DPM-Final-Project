package ca.mcgill.ecse211.wifi;

import java.util.Map;

import ca.mcgill.ecse211.WiFiClient.WifiConnection;
import ca.mcgill.ecse211.game.GameParameter;
import lejos.hardware.Button;

/**
 * Example class using WifiConnection to communicate with a server and receive
 * data concerning the competition such as the starting corner the robot is
 * placed in.
 * 
 * Keep in mind that this class is an **example** of how to use the WiFi code;
 * you must use the WifiConnection class yourself in your own code as
 * appropriate. In this example, we simply show how to get and process different
 * types of data.
 * 
 * There are two variables you **MUST** set manually before trying to use this
 * code.
 * 
 * 1. SERVER_IP: The IP address of the computer running the server application.
 * This will be your own laptop, until the beta beta demo or competition where
 * this is the TA or professor's laptop. In that case, set the IP to
 * 192.168.2.3.
 * 
 * 2. TEAM_NUMBER: your project team number
 * 
 * Note: We System.out.println() instead of LCD printing so that full debug
 * output (e.g. the very long string containing the transmission) can be read on
 * the screen OR a remote console such as the EV3Control program via Bluetooth
 * or WiFi. You can disable printing from the WiFi code via
 * ENABLE_DEBUG_WIFI_PRINT (below).
 * 
 * @author Michael Smith, Tharsan Ponnampalam
 *
 */
public class WiFi {

	// ** Set these as appropriate for your team and current situation **
	private static final String SERVER_IP = "192.168.2.255";
	private static final int TEAM_NUMBER = 14;

	// Enable/disable printing of debug info from the WiFi class
	private static final boolean ENABLE_DEBUG_WIFI_PRINT = true;
	private static WifiConnection conn;

	public WiFi() {
		conn = new WifiConnection(SERVER_IP, TEAM_NUMBER, ENABLE_DEBUG_WIFI_PRINT);
	}

	public void loadParameters() {

		// Connect to server and get the data, catching any errors that might occur
		try {
			/*
			 * getData() will connect to the server and wait until the user/TA presses the
			 * "Start" button in the GUI on their laptop with the data filled in. Once it's
			 * waiting, you can kill it by pressing the upper left hand corner button
			 * (back/escape) on the EV3. getData() will throw exceptions if it can't connect
			 * to the server (e.g. wrong IP address, server not running on laptop, not
			 * connected to WiFi router, etc.). It will also throw an exception if it
			 * connects but receives corrupted data or a message from the server saying
			 * something went wrong. For example, if TEAM_NUMBER is set to 1 above but the
			 * server expects teams 17 and 5, this robot will receive a message saying an
			 * invalid team number was specified and getData() will throw an exception
			 * letting you know.
			 */

			/**
			 * parameter names for reference when getting from Map Green_LL_y lower left
			 * corner green y Green_LL_x lower left corner green x GreenTeam TNR_UR_x upper
			 * right of red tunnel x TNR_UR_y upper right of red tunnel y TG_y green ring
			 * set y TG_x green ring set x TR_x red ring set x TR_y red ring set y
			 * Island_LL_y lower left island y GreenCorner starting corner TNR_LL_x lower
			 * left corner of red tunnel x Island_LL_x lower left corner of island TNR_LL_y
			 * lower left corner of red tunnel y Red_UR_x upper right corner of Red Zone x
			 * Red_UR_y upper right corner of Red Zone y TNG_UR_y upper right corner of
			 * green tunnel y TNG_UR_x upper right corner of green tunnel x TNG_LL_y lower
			 * left corner of green tunnel y TNG_LL_x lower left corner of green tunnel x
			 * Green_UR_y upper right corner of green zone y Green_UR_x upper right corner
			 * of green zone x RedTeam red team number Red_LL_x lower left corner of red
			 * zone x Red_LL_y lower left corner of red zone y RedCorner Red starting corner
			 * Island_UR_x upper right corner of island x Island_UR_y upper right corner of
			 * island y
			 */

			/*
			 * Parameters needed for beta demo: 1. GreenTeam 2. GreenCorner (starting in
			 * corner 1) 3. Green_LL (x,y) - lower left hand corner of green zone 4.
			 * Green_UR (x,y) - upper right hand corner of green zone 5. Island_UR (x,y) -
			 * lower left hand corner of the island 6. Island_LL (x,y) - upper right hand
			 * corner of the island 7. TNG_LL (x,y) - lower left hand corner of the tunnel
			 * footprint 8. TNG_UR (x,y) - upper right hand corner of the tunnel footprint
			 * 9. TG (x,y) - location of the ring tree
			 */

			Map data = conn.getData();

			int greenTeam = ((Long) data.get("GreenTeam")).intValue();
			GameParameter.GreenTeam = greenTeam;

			int greenCorner = ((Long) data.get("GreenCorner")).intValue();
			GameParameter.GreenCorner = greenCorner;

			int greenLowerLeftX = ((Long) data.get("Green_LL_x")).intValue();
			GameParameter.Green_LL[0] = greenLowerLeftX;
			int greenLowerLeftY = ((Long) data.get("Green_LL_y")).intValue();
			GameParameter.Green_LL[1] = greenLowerLeftY;

			int greenUpperRightX = ((Long) data.get("Green_UR_x")).intValue();
			GameParameter.Green_UR[0] = greenUpperRightX;
			int greenUpperRightY = ((Long) data.get("Green_UR_y")).intValue();
			GameParameter.Green_UR[1] = greenUpperRightY;

			int islandLowerLeftX = ((Long) data.get("Island_LL_x")).intValue();
			GameParameter.Island_LL[0] = islandLowerLeftX;
			int islandLowerLeftY = ((Long) data.get("Island_LL_y")).intValue();
			GameParameter.Island_LL[1] = islandLowerLeftY;

			int islandUpperRightX = ((Long) data.get("Island_UR_x")).intValue();
			GameParameter.Island_UR[0] = islandUpperRightX;
			int islandUpperRightY = ((Long) data.get("Island_UR_y")).intValue();
			GameParameter.Island_UR[1] = islandUpperRightY;

			int upperRightCornerGreenTunnelX = ((Long) data.get("TNG_UR_x")).intValue();
			GameParameter.TNG_RR[0] = upperRightCornerGreenTunnelX;
			int upperRightCornerGreenTunnelY = ((Long) data.get("TNG_UR_y")).intValue();
			GameParameter.TNG_RR[1] = upperRightCornerGreenTunnelY;

			int lowerLeftCornerGreenTunnelX = ((Long) data.get("TNG_LL_x")).intValue();
			GameParameter.TNG_LL[0] = lowerLeftCornerGreenTunnelX;
			int lowerLeftCornerGreenTunnelY = ((Long) data.get("TNG_LL_y")).intValue();
			GameParameter.TNG_LL[1] = lowerLeftCornerGreenTunnelY;

			int greenRingSetX = ((Long) data.get("TG_x")).intValue();
			GameParameter.TG[0] = greenRingSetX;
			int greenRingSetY = ((Long) data.get("TG_y")).intValue();
			GameParameter.TG[1] = greenRingSetY;

			/*
			 * GameParameter.RedTeam = ((Long) data.get("RedTeam")).intValue();
			 * GameParameter.GreenTeam = ((Long) data.get("GreenTeam")).intValue();
			 * 
			 * //Red_LL int lowerLeftCornerRedZoneX = ((Long)
			 * data.get("Red_LL_x")).intValue(); int lowerLeftCornerRedZoneY = ((Long)
			 * data.get("Red_LL_y")).intValue(); GameParameter.Red_LL[0] =
			 * lowerLeftCornerRedZoneX; GameParameter.Red_LL[1] = lowerLeftCornerRedZoneY;
			 * 
			 * //Red_UR int upperRightCornerRedX = ((Long) data.get("Red_UR_x")).intValue();
			 * int upperRightCornerRedY = ((Long) data.get("Red_UR_y")).intValue();
			 * GameParameter.Red_UR[0] = upperRightCornerRedX; GameParameter.Red_UR[1] =
			 * upperRightCornerRedY;
			 * 
			 * //TNR_LL_RED int lowerLeftRedTunnelX = ((Long)
			 * data.get("TNR_LL_x")).intValue(); int lowerLeftRedTunnelY = ((Long)
			 * data.get("TNR_LL_y")).intValue(); GameParameter.TNR_LL_RED[0] =
			 * upperRightCornerRedX; GameParameter.TNR_LL_RED[1] = upperRightCornerRedY;
			 * 
			 * //TNR_UR_RED int upperRightRedTunnelX = ((Long)
			 * data.get("TNR_UR_x")).intValue(); int upperRightRedTunnelY = ((Long)
			 * data.get("TNR_UR_y")).intValue(); GameParameter.TNR_UR_RED[0] =
			 * upperRightCornerRedX; GameParameter.TNR_UR_RED[1] = upperRightCornerRedY;
			 * 
			 * int redCorner = ((Long) data.get("RedCorner")).intValue();
			 * GameParameter.RedCorner = redCorner;
			 * 
			 * 
			 * 
			 * int greenLowerLeftX = ((Long) data.get("Green_LL_x")).intValue(); int
			 * greenLowerLeftY = ((Long) data.get("Green_LL_y")).intValue();
			 * 
			 * int greenRingSetX = ((Long) data.get("TG_x")).intValue(); int greenRingSetY =
			 * ((Long) data.get("TG_y")).intValue(); int redRingSetX = ((Long)
			 * data.get("TR_x")).intValue(); int redRingSetY = ((Long)
			 * data.get("TR_y")).intValue(); int islandLowerLeftX = ((Long)
			 * data.get("Island_LL_x")).intValue(); int islandLowerLeftY = ((Long)
			 * data.get("Island_LL_y")).intValue(); //int redCorner = ((Long)
			 * data.get("RedCorner")).intValue(); int greenCorner = ((Long)
			 * data.get("GreenCorner")).intValue(); int lowerLeftCornerRedX = ((Long)
			 * data.get("TNR_LL_x")).intValue(); int lowerLeftCornerRedY = ((Long)
			 * data.get("TNR_LL_y")).intValue(); int upperRightCornerGreenTunnelX = ((Long)
			 * data.get("TNG_UR_x")).intValue(); int upperRightCornerGreenTunnelY = ((Long)
			 * data.get("TNG_UR_y")).intValue(); int lowerLeftCornerGreenTunnelX = ((Long)
			 * data.get("TNG_LL_x")).intValue(); int lowerLeftCornerGreenTunnelY = ((Long)
			 * data.get("TNG_LL_y")).intValue(); int upperRightCornerGreenZoneX = ((Long)
			 * data.get("Green_UR_x")).intValue(); int upperRightCornerGreenZoneY = ((Long)
			 * data.get("Grenn_UR_y")).intValue();
			 * 
			 * int upperRightCornerIslandX = ((Long) data.get("Island_UR_x")).intValue();
			 * int upperRightCornerIslandY = ((Long) data.get("Island_UR_y")).intValue();
			 * 
			 * int tn_ll_x = ((Long) data.get("TNR_LL_x")).intValue(); if (tn_ll_x < 5) {
			 * System.out.println("Red Tunnel LL corner X < 5"); } else {
			 * System.out.println("Red Tunnel LL corner X >= 5"); }
			 */

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		// Wait until user decides to end program
		Button.waitForAnyPress();
	}
}
