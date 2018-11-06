package threads;

import odometer.OdometerExceptions;
import odometer.Odometer;
import lejos.robotics.SampleProvider;

/**
 * This class polls a light sensor that is used to detect colored rings.
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


  protected void runMethod() {
    us[0].fetchSample(lgData[0], 0); // acquire data at offset 0
    // get RGB values from buffer, multiply by 100 for convenience and allow it to be cast to int
    int r = (int) (lgData[0][0] * 100); // extract from buffer, cast to int
    int g = (int) (lgData[0][1] * 100); // extract from buffer, cast to int
    int b = (int) (lgData[0][2] * 100); // extract from buffer, cast to int
    cont.setRGB(r, g, b); // now take action depending on value
  }
}
