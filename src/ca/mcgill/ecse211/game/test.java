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
		RingColorDetectionTest, RingRetrievalTest, RingDrop;
	}

	/**
	 * This is constructor for the test class
	 * gggggggggbn
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
		navigation.goToTunnel(GameParameter.Green_LL, GameParameter.Green_UR);
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
	
	public void RingDrop() throws OdometerExceptions {
		RingRetrieval ringRetrieval = new RingRetrieval(Game.leftMotor, Game.rightMotor, Game.elbowMotor, 
				Game.foreArmMotor);
		for(int i=0; i<15; i++) {
			ringRetrieval.dropRings();
		}
	}
	
	
	/**
	 * starting corner 0
	 * 
	 *   *   *   *   *   *   *   *   * 
	   
	 * 				                 *
	  
	 * 				       (6,6)     *
	  
	 * 		 | (3,5)                 *
	  
	 * 	     |	 |	                 *
	       
	 *     (2,3) |	                 *
	        
	 * 				                 *
	  
	 * (1,1)                         *
	  
	 *   *   *   *   *   *   *   *   *
	 */
	
	public static void loadTestCase1() {
		GameParameter.GreenCorner = 0;
		GameParameter.Green_LL[0] = 0;
		GameParameter.Green_LL[1] = 0;
		GameParameter.Green_UR[0] = 3;
		GameParameter.Green_UR[1] = 3;
		GameParameter.TNG_LL[0] = 2;
		GameParameter.TNG_LL[1] = 3;
		GameParameter.TNG_RR[0] = 3;
		GameParameter.TNG_RR[1] = 5;
		GameParameter.IslandG_LL[0] = 2;
		GameParameter.IslandG_LL[1] = 5;
		GameParameter.IslandG_UR[0] = 8;
		GameParameter.IslandG_UR[1] = 8;
		GameParameter.TG[0] = 6;
		GameParameter.TG[1] = 6;
	}
	
	/**
	 * starting corner 0
	 * 
	 *   *   *   *   *   *   *   *   * 
	   
	 * 				                 *
	  
	 * 				       (6,6)     *
	  
	 * 				                 *
	  
	 * 	     		                 *
	       
	 *     		------ (5,3)         *
	        
	 * 		   (3,2)----             *
	  
	 * (1,1)                         *
	  
	 *   *   *   *   *   *   *   *   *
	 */
	
	public static void loadTestCase2() {
		GameParameter.GreenCorner = 0;
		GameParameter.Green_LL[0] = 0;
		GameParameter.Green_LL[1] = 0;
		GameParameter.Green_UR[0] = 3;
		GameParameter.Green_UR[1] = 3;
		GameParameter.TNG_LL[0] = 3;
		GameParameter.TNG_LL[1] = 2;
		GameParameter.TNG_RR[0] = 5;
		GameParameter.TNG_RR[1] = 3;
		GameParameter.IslandG_LL[0] = 5;
		GameParameter.IslandG_LL[1] = 2;
		GameParameter.IslandG_UR[0] = 8;
		GameParameter.IslandG_UR[1] = 8;
		GameParameter.TG[0] = 6;
		GameParameter.TG[1] = 6;
	}
	
	/**
	 * starting corner 1
	 * 
	 *   *   *   *   *   *   *   *   * 
	   
	 * 				                 *
	  
	 * 				       (6,6)     *
	  
	 * 				                 *
	  
	 * 	     		                 *
	       
	 *     		------ (5,3)         *
	        
	 * 		   (3,2)----             *
	  
	 *                         (7,1) *
	  
	 *   *   *   *   *   *   *   *   *
	 */
	
	public static void loadTestCase3() {
		GameParameter.GreenCorner = 1;
		GameParameter.Green_LL[0] = 5;
		GameParameter.Green_LL[1] = 0;
		GameParameter.Green_UR[0] = 8;
		GameParameter.Green_UR[1] = 3;
		GameParameter.TNG_LL[0] = 3;
		GameParameter.TNG_LL[1] = 2;
		GameParameter.
	     TNG_RR[0] = 5;
		GameParameter.TNG_RR[1] = 3;
		GameParameter.IslandG_LL[0] = 0;
		GameParameter.IslandG_LL[1] = 0;
		GameParameter.IslandG_UR[0] = 3;
		GameParameter.IslandG_UR[1] = 8;
		GameParameter.TG[0] = 2;
		GameParameter.TG[1] = 6;
	}
	
	/**
	 * starting corner 1
	 * 
	 *   *   *   *   *   *   *   *   * 
	   
	 * 				                 *
	  
	 * 	   (2,6)	                 *
	  
	 * 				 |  (5,5)        *
	  
	 * 	     		 |   |           *
	       
	 *     		   (4,3) |           *
	        
	 * 		                         *
	  
	 *                         (7,1) *
	  
	 *   *   *   *   *   *   *   *   *
	 */
	
	public static void loadTestCase4() {
		GameParameter.GreenCorner = 1;
		GameParameter.Green_LL[0] = 4;
		GameParameter.Green_LL[1] = 0;
		GameParameter.Green_UR[0] = 8;
		GameParameter.Green_UR[1] = 3;
		GameParameter.TNG_LL[0] = 4;
		GameParameter.TNG_LL[1] = 3;
		GameParameter.TNG_RR[0] = 5;
		GameParameter.TNG_RR[1] = 5;
		GameParameter.IslandG_LL[0] = 0;
		GameParameter.IslandG_LL[1] = 5;
		GameParameter.IslandG_UR[0] = 5;
		GameParameter.IslandG_UR[1] = 8;
		GameParameter.TG[0] = 2;
		GameParameter.TG[1] = 6;
	}
	
	/**
	 * starting corner 2
	 * 
	 *   *   *   *   *   *   *   *   * 
	   
	 * 				           (7,7) *
	  
	 * 	         	                 *
	  
	 * 		     |  (4,5)        	 *
	  
	 * 	         |   |               *
	       
	 *     	   (3,3) |               *
	        
	 * 		               (6,2)     *
	      
	 *                               *
	  
	 *   *   *   *   *   *   *   *   *
	 */
	
	public static void loadTestCase5() {
		GameParameter.GreenCorner = 2;
		GameParameter.Green_LL[0] = 3;
		GameParameter.Green_LL[1] = 5;
		GameParameter.Green_UR[0] = 8;
		GameParameter.Green_UR[1] = 8;
		GameParameter.TNG_LL[0] = 3;
		GameParameter.TNG_LL[1] = 3;
		GameParameter.TNG_RR[0] = 4;
		GameParameter.TNG_RR[1] = 5;
		GameParameter.IslandG_LL[0] = 3;
		GameParameter.IslandG_LL[1] = 0;
		GameParameter.IslandG_UR[0] = 8;
		GameParameter.IslandG_UR[1] = 3;
		GameParameter.TG[0] = 6;
		GameParameter.TG[1] = 2;
	}
	
	/**
	 * starting corner 2
	 * 
	 *   *   *   *   *   *   *   *   * 
	   
	 * 				           (7,7) *
	  
	 * 	         	                 *
	  
	 * 		     ----- (5,5)         *
	  
	 * 	       (3,4) ------	         *
	       
	 *     	                         *
	        
	 * 	(1,2)                        *
	      
	 *                               *
	  
	 *   *   *   *   *   *   *   *   *
	 */
	
	public static void loadTestCase6() {
		GameParameter.GreenCorner = 2;
		GameParameter.Green_LL[0] = 5;
		GameParameter.Green_LL[1] = 4;
		GameParameter.Green_UR[0] = 8;
		GameParameter.Green_UR[1] = 8;
		GameParameter.TNG_LL[0] = 3;
		GameParameter.TNG_LL[1] = 4;
		GameParameter.TNG_RR[0] = 5;
		GameParameter.TNG_RR[1] = 5;
		GameParameter.IslandG_LL[0] = 0;
		GameParameter.IslandG_LL[1] = 0;
		GameParameter.IslandG_UR[0] = 3;
		GameParameter.IslandG_UR[1] = 5;
		GameParameter.TG[0] = 1;
		GameParameter.TG[1] = 2;
	}
	
	
	/**
	 * starting corner 3
	 * 
	 *   *   *   *   *   *   *   *   * 
	   
	 * (1,7)			             *
	  
	 * 	         	                 *
	  
	 * 		 ----- (4,5)             *
	  
	 * 	   (2,4) ------	             *
	       
	 *     	                         *
	        
	 * 	                    (6,2)    *
	      
	 *                               *
	  
	 *   *   *   *   *   *   *   *   *
	 */
	
	public static void loadTestCase7() {
		GameParameter.GreenCorner = 3;
		GameParameter.Green_LL[0] = 0;
		GameParameter.Green_LL[1] = 4;
		GameParameter.Green_UR[0] = 2;
		GameParameter.Green_UR[1] = 8;
		GameParameter.TNG_LL[0] = 2;
		GameParameter.TNG_LL[1] = 4;
		GameParameter.TNG_RR[0] = 4;
		GameParameter.TNG_RR[1] = 5;
		GameParameter.IslandG_LL[0] = 4;
		GameParameter.IslandG_LL[1] = 0;
		GameParameter.IslandG_UR[0] = 8;
		GameParameter.IslandG_UR[1] = 5;
		GameParameter.TG[0] = 6;
		GameParameter.TG[1] = 2;
	}
	
	/**
	 * starting corner 3
	 * 
	 *   *   *   *   *   *   *   *   * 
	   
	 * (1,7)			             *
	  
	 * 	         	                 *
	  
	 * 		         |  (5,5)        *
	  
	 * 	             |   |           *
	       
	 *     	       (4,3) |           *
	        
	 * 	                             *
	      
	 *      (2,1)                    *
	  
	 *   *   *   *   *   *   *   *   *
	 */
	
	public static void loadTestCase8() {
		GameParameter.GreenCorner = 3;
		GameParameter.Green_LL[0] = 0;
		GameParameter.Green_LL[1] = 5;
		GameParameter.Green_UR[0] = 5;
		GameParameter.Green_UR[1] = 8;
		GameParameter.TNG_LL[0] = 4;
		GameParameter.TNG_LL[1] = 3;
		GameParameter.TNG_RR[0] = 5;
		GameParameter.TNG_RR[1] = 5;
		GameParameter.IslandG_LL[0] = 0;
		GameParameter.IslandG_LL[1] = 0;
		GameParameter.IslandG_UR[0] = 5;
		GameParameter.IslandG_UR[1] = 3;
		GameParameter.TG[0] = 2;
		GameParameter.TG[1] = 1;
	}
	
	
}
