package ca.mcgill.ecse211.threads;

import ca.mcgill.ecse211.odometer.OdometerExceptions;
import lejos.robotics.SampleProvider;

/**
 * This class polls a light sensor that is used to detect colored rings.
 * 
 */
public class RGBPoller extends LightPoller{

  /**
   * This constructor creates an instance of the RGBPoller class to provide color data from an light
   * sensor to our robot.
   * 
   * @param us A SampleProvider class instance that helps us to store an array of ultrasonic sensor
   *        data.
   * @param usData An array to store light data.
   * @param cont A SensorData object that is used to process color data.
   * @throws OdometerExceptions
   */
  public RGBPoller(SampleProvider us[], float[][] usData, SensorData cont) throws OdometerExceptions {
    super(us, usData, cont);
  }

  @Override
  protected void runMethod() {
    us[0].fetchSample(lgData[0], 0); // acquire data at offset 0
    // get RGB values from buffer, multiply by 100 for convenience and allow it to be cast to int
    float r = (lgData[0][0]); // extract from buffer, cast to int
    float g = (lgData[0][1]); // extract from buffer, cast to int
    float b = (lgData[0][2]); // extract from buffer, cast to int
    cont.setRGB(r, g, b); // now take action depending on value
  }
}
