
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class ColorDetector {
	
	  private static final Port lightPort = LocalEV3.get().getPort("S3");
	  private SensorModes lightSensor = new EV3ColorSensor(lightPort);
	  private SampleProvider colorSample = lightSensor.getMode("RGB");			// provides samples from this instance
	  private float[] colorvalues = new float[colorSample.sampleSize()];			// buffer in which data are returned
	  private static final TextLCD lcd = LocalEV3.get().getTextLCD();

	  double thetaX1, thetaX2, thetaY1, thetaY2;
	  
	  private Odometer odometer;
	  
	  private static Color currentColor;
	  private static Color targetColor;
	  
	  private static boolean isDetected;
	  //private Navigation nav;
	  
	  //private SampleProvider colorSample;
	  private double previousBrightness;
	  private double currentBrightness;
	  //private float[] colorvalues;
	 
	  public static enum Color {
		  Orange, Blue, Yellow, Green, Other;
	  }
	 
	  //Navigation nav
	  public ColorDetector(Odometer odo, Color inputColor) {
		  this.odometer = odo;
		  this.targetColor = inputColor;
		  this.isDetected = false;
	  }
	
	  public void performColorDetection() {
		  while (!isDetected){
			ColorDisplay();
		  }	
		  if (currentColor == targetColor) {
			  Sound.beep();
		      lcd.drawString("Color: " + currentColor,0, 5);
		      Navigation.foundTargetedRing = true;
		  } else {
			  Sound.twoBeeps();
			  lcd.drawString("Color: " + currentColor,0, 5);
		  }
		   isDetected = false;
	  }
	  
	  private void ColorDisplay() {
		  // Trigger correction (When do I have information to correct?)
	      colorSample.fetchSample(colorvalues, 0);
	      currentBrightness = (colorvalues[0] + colorvalues[1] + colorvalues[2])*100;
	      
	      if(previousBrightness == -1) { //no set brightness
	    	  previousBrightness = currentBrightness;
	      }
	      
	      lcd.drawString("0: " +colorvalues[0]*100,0, 1);
	      lcd.drawString("1: " +colorvalues[1]*100,0, 2);
	      lcd.drawString("2: " +colorvalues[2]*100,0, 3);
	      lcd.drawString("Co: " +currentBrightness,0, 4);
	      lcd.drawString("Color: " + currentColor,0, 5);
	     
	      double R = colorvalues[0]*100;
	      double G = colorvalues[1]*100;
	      double B = colorvalues[2]*100;
	      
	    
	      double Rn = R/Math.sqrt(Math.pow(R,2) + Math.pow(G,2) + Math.pow(B,2));
	      double Gn = G/Math.sqrt(Math.pow(R,2) + Math.pow(G,2) + Math.pow(B,2));
	      double Bn = B/Math.sqrt(Math.pow(R,2) + Math.pow(G,2) + Math.pow(B,2));
	      
	      if (currentBrightness > 20 && currentBrightness <= 22) {//blue
	    	  clearDisplay();
	    	  lcd.drawString("OBJECT DETECTED", 0, 5);
	    	  lcd.drawString("BLUE RING", 0, 6);
	    	  currentColor = Color.Blue;
	    	  isDetected = true;
	      }
	      if (currentBrightness > 22 && currentBrightness <= 30) {//green
	    	  clearDisplay();
	    	  lcd.drawString("OBJECT DETECTED", 0, 5);
	    	  lcd.drawString("GREEN RING", 0, 6);
	    	  currentColor = Color.Green;
	    	  isDetected = true;
	      }
	      if (currentBrightness > 31) {//yellow
	    	  clearDisplay();
	    	  lcd.drawString("OBJECT DETECTED", 0, 5);
	    	  lcd.drawString("YELLOW RING", 0, 6);
	    	  currentColor = Color.Yellow;
	    	  isDetected = true;
	      }
	      if (currentBrightness > 11 && currentBrightness <= 17) {//orange
	    	  clearDisplay();
	    	  lcd.drawString("OBJECT DETECTED", 0, 5);
	    	  lcd.drawString("ORANGE RING", 0, 6);
	    	  currentColor = Color.Orange;
	    	  isDetected = true;
	      }
	      if (currentBrightness <= 10) {
	    	  //lcd.drawString("NOT DETECTED", 0, 6);
	    	  clearDisplay();
	      }

	      int buttonChoice;
			buttonChoice = Button.readButtons();
			if (buttonChoice == Button.ID_ESCAPE) {
				System.exit(0);
			}
			
	  }
	  
	  
		public static Color getColor() {
			if (currentColor != null) return currentColor;
			else return Color.Other;
		}
	  
	  public void clearDisplay() {
		  lcd.clear(5);
		  lcd.clear(6);
	  }
}